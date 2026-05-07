package com.YiGeon.demo.repository;

import com.YiGeon.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class MemberRepository {
    private final String url = "jdbc:mysql://localhost:3306/test_database";
    private final String user = "root";
    private final String serverpassword = "leegun0626@";


    // 회원 정보 저장(회원가입)
    public void insertMember(Member member) {
        String sql = "INSERT INTO member (owner_id, password, name, birthdate, phone_number) VALUES (?,?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(url,user,serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,member.getOwnerId());
            pstmt.setString(2,member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getBirthdate());
            pstmt.setString(5, member.getPhoneNumber());

            pstmt.executeUpdate();
            System.out.println("Success Member INSERT! (" + member.getOwnerId() + ")");
        } catch (Exception e) {
            System.out.println("Member INSERT ERROR!");
            e.printStackTrace();
        }
    }

    // 로그인 검증
    public Member findByOwnerId(String ownerId) {
        String sql = "SELECT * FROM member WHERE owner_id = ?";
        try(Connection conn = DriverManager.getConnection(url,user,serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, ownerId);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Member member = new Member(rs.getString("owner_id"), rs.getString("password"), rs.getDouble("assets"),
                            rs.getString("name"), rs.getString("birthdate"), rs.getString("phone_number"));
                    return member;
                }
            }
        } catch (Exception e) {
            System.out.println("Member SELECT ERROR!");
            e.printStackTrace();
        }
        return null;
    }

    //아이디 중복 체크
    public boolean existsByOwnerId(String ownerId){
        String sql = "SELECT COUNT(*) FROM member WHERE owner_id = ?";
        try(Connection conn = DriverManager.getConnection(url,user,serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, ownerId);

            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Member EXISTS CHECK ERROR!");
            e.printStackTrace();
        }
        return false;
    }

    // 아이디 찾기
    public String findIdByUserInfo(String name, String birthdate, String phoneNumber) {
        String sql = "SELECT owner_id FROM member WHERE name = ? AND birthdate = ? AND phone_number = ?";

        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, birthdate);
            pstmt.setString(3, phoneNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("owner_id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Find ID ERROR!");
            e.printStackTrace();
        }
        return null;
    }

    // 비밀번호 찾기 전용 본인 확인 (아이디, 이름, 전화번호, 생년월일 모두 일치하는지 검사)
    public boolean verifyUserForPasswordReset(String ownerId, String name, String phoneNumber, String birthdate) {
        String sql = "SELECT COUNT(*) FROM member WHERE owner_id = ? AND name = ? AND phone_number = ? AND birthdate = ?";

        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ownerId);
            pstmt.setString(2, name);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, birthdate);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // 일치하는 데이터가 1개 이상 있으면 true
                }
            }
        } catch (SQLException e) {
            System.out.println("Verify User ERROR!");
            e.printStackTrace();
        }
        return false;
    }

    // 비밀번호 재설정
    public boolean updatePassword(String ownerId, String newPassword) {
        String sql = "UPDATE member SET password = ? WHERE owner_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, ownerId);

            int result = pstmt.executeUpdate();
            return result > 0; // 업데이트 성공 시 true
        } catch (SQLException e) {
            System.out.println("Update Password ERROR!");
            e.printStackTrace();
        }
        return false;
    }

    public void updateAssets(String ownerId, double ChangeAssets){
        String sql = "UPDATE member SET assets = ? WHERE owner_id = ?";
        try(Connection conn = DriverManager.getConnection(url,user,serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, ChangeAssets);
            pstmt.setString(2, ownerId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println(">> UPDATE Success: " + ownerId + "assets have been updated.");
            }
        } catch (Exception e) {
            System.out.println("Member UPDATE ERROR!");
            e.printStackTrace();
        }
    }
}
