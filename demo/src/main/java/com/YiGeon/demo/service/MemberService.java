package com.YiGeon.demo.service;

import com.YiGeon.demo.domain.Member;
import com.YiGeon.demo.repository.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public void register(Member member) {
        if(memberRepository.existsByOwnerId(member.getOwnerId())){
            throw new IllegalArgumentException("The ID is already in use.");
        }

        //입력받은 String 비밀번호를 암호화
        String hashedPassword = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
        member.setPassword(hashedPassword);

        memberRepository.insertMember(member);
    }

    // 아이디 찾기
    public String findId(String name, String birthdate, String phoneNumber) {
        return memberRepository.findIdByUserInfo(name, birthdate, phoneNumber);
    }

    // 본인 확인
    public boolean verifyUserForPasswordReset(String ownerId, String name, String phoneNumber, String birthdate) {
        return memberRepository.verifyUserForPasswordReset(ownerId, name, phoneNumber, birthdate);
    }

    // 비밀번호 재설정
    public void resetPassword(String ownerId, String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        boolean success = memberRepository.updatePassword(ownerId, hashedPassword);
        if (!success) {
            throw new IllegalArgumentException("비밀번호 변경 중 DB 오류가 발생했습니다.");
        }
    }

    //로그인 검증
    public Member login(String ownerId, String plainPassword) {
        Member member = memberRepository.findByOwnerId(ownerId);
        if(member == null || !BCrypt.checkpw(plainPassword, member.getPassword())){
            throw new IllegalArgumentException("ID or Password does not match.");
        }
        return member;
    }

    // 아이디 중복 확인
    public boolean isIdDuplicated(String ownerId) {
        return memberRepository.existsByOwnerId(ownerId);
    }

    // 내 정보(자산, 이름) 조회
    public Member getMemberInfo(String ownerId) {
        Member member = memberRepository.findByOwnerId(ownerId);
        if (member == null) {
            throw new IllegalArgumentException("Non-Existent member.");
        }
        return member;
    }
}
