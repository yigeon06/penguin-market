package com.YiGeon.demo.controller;

import com.YiGeon.demo.domain.Member;
import com.YiGeon.demo.domain.Penguin;
import com.YiGeon.demo.dto.*;
import com.YiGeon.demo.service.MemberService;
import com.YiGeon.demo.service.PenguinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class PenguinController {
    private final PenguinService penguinService;
    private final MemberService memberService;

    public PenguinController(PenguinService penguinService, MemberService memberService){
        this.penguinService = penguinService;
        this.memberService = memberService;
    }

    // 회원가입
    @PostMapping("/api/member/signup")
    public String signup(@Valid @RequestBody SignupRequestDto signupDto) {
        Member newMember = new Member();
        newMember.setOwnerId(signupDto.getOwnerId());
        newMember.setPassword(signupDto.getPassword());
        newMember.setName(signupDto.getName());
        newMember.setBirthdate(signupDto.getBirthdate());
        newMember.setPhoneNumber(signupDto.getPhoneNumber());

        memberService.register(newMember);
        return signupDto.getOwnerId() + "님, 회원가입이 완료되었습니다!";
    }

    // 아이디 찾기
    @GetMapping("/api/member/find-id")
    public ResponseEntity<String> findId(@RequestParam String name, @RequestParam String birthdate, @RequestParam String phone_number) {
        // 성공도 실패도 아닌 상황(데이터 없음 등)을 위해 ResponseEntity 사용
        String foundId = memberService.findId(name, birthdate, phone_number);

        if (foundId != null) {
            return ResponseEntity.ok(foundId); // 200 OK와 함께 아이디 반환
        } else {
            // 일치하는 정보가 없을 경우 404 Not Found 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 회원 정보가 없습니다.");
        }
    }

    // 본인 확인
    @GetMapping("/api/member/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String ownerId, @RequestParam String name, @RequestParam String phone_number, @RequestParam String birthdate) {
        // 본인 확인
        boolean isVerified = memberService.verifyUserForPasswordReset(ownerId, name, phone_number, birthdate);

        if (isVerified) {
            return ResponseEntity.ok("본인 인증이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("입력하신 정보와 일치하는 계정이 없습니다.");
        }
    }

    // 비밀번호 재설정
    @PutMapping("/api/member/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody LoginRequestDto requestDto) {
        try {
            memberService.resetPassword(requestDto.getOwnerId(), requestDto.getPassword());
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 로그인
    @PostMapping("/api/member/login")
    public String login(@Valid @RequestBody LoginRequestDto loginDto){
        memberService.login(loginDto.getOwnerId(), loginDto.getPassword());
        return loginDto.getOwnerId() + "님, 환영합니다!";
    }

    // 아이디 중복 체크
    @GetMapping("/api/member/check-id")
    public boolean checkIdDuplication(@RequestParam String ownerId) {
        return memberService.isIdDuplicated(ownerId);
    }

    // 내 정보(이름, 잔액) 조회
    @GetMapping("/api/member/{ownerId}")
    public MemberResponseDto getMemberInfo(@PathVariable String ownerId) {
        Member member = memberService.getMemberInfo(ownerId);
        return new MemberResponseDto(member); // 비밀번호를 제외하고 DTO로 변환
    }

    // 전체 펭귄 데이터 조회
    @GetMapping("/api/penguins")
    public List<PenguinResponseDto> getAllPenguins(){
        return penguinService.getAllPenguins();
    }


    // 내 펭귄 목록 조회
    @GetMapping("/api/penguin/my/{ownerId}")
    public List<PenguinResponseDto> getMyPenguins(@PathVariable String ownerId) {
        return penguinService.getMyPenguins(ownerId);
    }

    // 새로운 펭귄 등록
    @PostMapping("/api/penguin")
    public String createPenguin(@Valid @RequestBody PenguinRequestDto requestDto){
        Penguin penguin = new Penguin();
        penguin.setSpecies(requestDto.getSpecies());
        penguin.setIsland(requestDto.getIsland());
        penguin.setSex(requestDto.getSex());
        penguin.setOwnerId(requestDto.getOwnerId());

        penguinService.addPenguin(penguin);
        return "새로운 펭귄 데이터가 등록되었습니다!";
    }

    // 특정 펭귄 삭제
    @DeleteMapping("/api/penguin/{id}")
    public String deletePenguin(@PathVariable int id, @RequestParam String ownerId){
        penguinService.deletePenguin(id, ownerId);
        return id + "번 펭귄 데이터가 삭제되었습니다.";
    }

    // 특정 펭귄 정보 수정
    @PutMapping("/api/penguin/{id}")
    public String updatePenguin(@PathVariable int id, @Valid @RequestBody PenguinRequestDto requestDto) {
        penguinService.updatePenguinBasic(id, requestDto);
        return id + "번 펭귄 데이터가 수정되었습니다.";
    }

    // 펭귄 기록(일지) 가져오기
    @GetMapping("/api/penguin/{id}/records")
    public ResponseEntity<List<PenguinRecordDto>> getPenguinRecords(@PathVariable int id) {
        List<PenguinRecordDto> records = penguinService.getPenguinRecords(id);
        return ResponseEntity.ok(records);
    }
}
