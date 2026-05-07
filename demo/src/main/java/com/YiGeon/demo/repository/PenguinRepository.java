package com.YiGeon.demo.repository;

import com.YiGeon.demo.domain.Penguin;
import com.YiGeon.demo.dto.PenguinRequestDto;
import com.YiGeon.demo.dto.PenguinRecordDto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

@Repository
public class PenguinRepository {
    private final String url = "jdbc:mysql://localhost:3306/test_database";
    private final String user = "root";
    private final String serverpassword = "leegun0626@";

    public void insertPenguin(Penguin penguin){ // INSERT
        String sql = "INSERT INTO penguin (species, island, culmen_length_mm, culmen_depth_mm, flipper_length_mm, body_mass_g, sex, owner_id, price) VALUES(?, ?, ?, ?, ?, ?,?,?, ?)";
        try{
            Connection conn = null;
            conn = DriverManager.getConnection(url,user, serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, penguin.getSpecies());
            pstmt.setString(2, penguin.getIsland());
            pstmt.setDouble(3, penguin.getCulmenLengthMm());
            pstmt.setDouble(4, penguin.getCulmenDepthMm());
            pstmt.setDouble(5, penguin.getFlipperLengthMm());
            pstmt.setDouble(6, penguin.getBodyMassG());
            pstmt.setString(7, penguin.getSex());
            pstmt.setString(8,penguin.getOwnerId());
            pstmt.setDouble(9,penguin.getPrice());

            int result = pstmt.executeUpdate(); // 쿼리 결과 객체
            if(result > 0){
                System.out.print("Success INSERT! \n");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("INSERT ERROR! \n");
            e.printStackTrace();
        }
    }

    // Penguin Record에 INSERT
    public void insertPenguinRecord(int penguinId, PenguinRequestDto dto) {
        String sql = "INSERT INTO penguin_record (penguin_id, culmen_length_mm, culmen_depth_mm, flipper_length_mm, body_mass_g, note_date, note_content) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, penguinId);
            pstmt.setObject(2, dto.getCulmenLength());
            pstmt.setObject(3, dto.getCulmenDepth());
            pstmt.setObject(4, dto.getFlipperLength());
            pstmt.setObject(5, dto.getBodyMass());
            pstmt.setString(6, dto.getNoteDate());
            pstmt.setString(7, dto.getNoteContent());

            pstmt.executeUpdate();
            System.out.println(">> DB Insert: " + penguinId + "Added Penguin's timeline record.");
        } catch (Exception e) {
            System.out.println("RECORD INSERT ERROR!");
            e.printStackTrace();
        }
    }


    public List<Penguin> findAllByOwnerId(String ownerId){ // SELECT
        List<Penguin> penguins = new ArrayList<>();
        String sql = "SELECT * FROM penguin WHERE owner_id = ?";
        try{
            Connection conn = null;
            conn = DriverManager.getConnection(url,user, serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,ownerId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Penguin penguin = new Penguin();
                penguin.setId(rs.getInt("id"));
                penguin.setSpecies(rs.getString("species"));
                penguin.setIsland(rs.getString("island"));
                penguin.setCulmenLengthMm(rs.getDouble("culmen_length_mm"));
                penguin.setCulmenDepthMm(rs.getDouble("culmen_depth_mm"));
                penguin.setFlipperLengthMm(rs.getInt("flipper_length_mm"));
                penguin.setBodyMassG(rs.getInt("body_mass_g"));
                penguin.setSex(rs.getString("sex"));
                penguin.setOwnerId(rs.getString("owner_id"));
                penguin.setPrice(rs.getDouble("price"));
                penguin.setNoteDate(rs.getString("note_date"));
                penguin.setNoteContent(rs.getString("note_content"));

                penguins.add(penguin);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.print("SELECT ERROR! \n");
            e.printStackTrace();
        }
        return penguins;
    }

    public Penguin findById(String ownerId, int Id){
        String sql = "SELECT * FROM penguin WHERE owner_id = ? AND id = ?";
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(url, user, serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ownerId);
            pstmt.setInt(2, Id);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Penguin penguin = new Penguin();
                penguin.setId(rs.getInt("id"));
                penguin.setSpecies(rs.getString("species"));
                penguin.setIsland(rs.getString("island"));
                penguin.setCulmenLengthMm(rs.getDouble("culmen_length_mm"));
                penguin.setCulmenDepthMm(rs.getDouble("culmen_depth_mm"));
                penguin.setFlipperLengthMm(rs.getInt("flipper_length_mm"));
                penguin.setBodyMassG(rs.getInt("body_mass_g"));
                penguin.setSex(rs.getString("sex"));
                penguin.setOwnerId(rs.getString("owner_id"));
                penguin.setPrice(rs.getDouble("price"));
                penguin.setNoteDate(rs.getString("note_date"));
                penguin.setNoteContent(rs.getString("note_content"));
                return penguin;
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("SELECT ERROR! \n");
            e.printStackTrace();
        }
        return null;
    }

    // 데이터 상 모든 펭귄 데이터 READ
    public List<Penguin> findAllPenguin() {
        List<Penguin> penguins = new ArrayList<>();
        String sql = "SELECT * FROM penguin";

        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Penguin penguin = new Penguin();
                penguin.setId(rs.getInt("id"));
                penguin.setSpecies(rs.getString("species"));
                penguin.setIsland(rs.getString("island"));
                penguin.setCulmenLengthMm(rs.getDouble("culmen_length_mm"));
                penguin.setCulmenDepthMm(rs.getDouble("culmen_depth_mm"));
                penguin.setFlipperLengthMm(rs.getInt("flipper_length_mm"));
                penguin.setBodyMassG(rs.getInt("body_mass_g"));
                penguin.setSex(rs.getString("sex"));
                penguin.setOwnerId(rs.getString("owner_id"));
                penguin.setPrice(rs.getDouble("price"));
                penguin.setNoteDate(rs.getString("note_date"));
                penguin.setNoteContent(rs.getString("note_content"));

                penguins.add(penguin);
            }
        } catch (SQLException e) {
            System.out.println("SELECT ALL ERROR!");
            e.printStackTrace();
        }
        return penguins;
    }

    // 특정 펭귄의 모든 기록 불러오기
    public List<PenguinRecordDto> findRecordsByPenguinId(int penguinId) {
        List<PenguinRecordDto> records = new ArrayList<>();
        String sql = "SELECT * FROM penguin_record WHERE penguin_id = ? ORDER BY note_date DESC, created_at DESC";

        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, penguinId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                PenguinRecordDto record = new PenguinRecordDto();
                record.setRecordId(rs.getInt("record_id"));
                record.setPenguinId(rs.getInt("penguin_id"));
                record.setCulmenLength((Double) rs.getObject("culmen_length_mm"));
                record.setCulmenDepth((Double) rs.getObject("culmen_depth_mm"));
                record.setFlipperLength((Double) rs.getObject("flipper_length_mm"));
                record.setBodyMass((Double) rs.getObject("body_mass_g"));
                record.setNoteDate(rs.getString("note_date"));
                record.setNoteContent(rs.getString("note_content"));
                records.add(record);
            }
        } catch (Exception e) {
            System.out.println("RECORD SELECT ERROR!");
            e.printStackTrace();
        }
        return records;
    }

    public void deletePenguin(int id, String ownerId){ // DELETE
        try{
            String sql = "DELETE FROM penguin WHERE id =? AND owner_id = ?";
            Connection conn = DriverManager.getConnection(url,user, serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, ownerId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println(">> " + id + " Penguin deleted!");
            } else {
                System.out.println(">> Penguin No. " + id + " does not exist.");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Specific data DELETE ERROR! \n");
            e.printStackTrace();
        }
    }

    public void updatePenguinBasic(int id, PenguinRequestDto penguin){
        try {
            // 💡 1. 쿼리문에 업데이트할 항목들 추가
            String sql = "UPDATE penguin SET species = ?, island = ?, sex = ?, culmen_length_mm = ?, culmen_depth_mm = ?, flipper_length_mm = ?, body_mass_g = ?, note_date = ?, note_content = ? WHERE id = ? AND owner_id = ?";
            Connection conn = DriverManager.getConnection(url,user, serverpassword);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, penguin.getSpecies());
            pstmt.setString(2, penguin.getIsland());
            pstmt.setString(3, penguin.getSex());

            // 💡 2. setObject를 사용하면 null 값이 들어와도 DB에 안전하게 NULL로 저장됩니다.
            pstmt.setObject(4, penguin.getCulmenLength());
            pstmt.setObject(5, penguin.getCulmenDepth());
            pstmt.setObject(6, penguin.getFlipperLength());
            pstmt.setObject(7, penguin.getBodyMass());

            pstmt.setString(8, penguin.getNoteDate());
            pstmt.setString(9, penguin.getNoteContent());

            pstmt.setInt(10, id);
            pstmt.setString(11, penguin.getOwnerId());

            int result = pstmt.executeUpdate();
            if(result > 0){
                System.out.println(">> DB Update: " + id + " Penguin information modification complete!");
            } else {
                System.out.println(">> " + id + " Not found, Failed to modify penguin.");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("UPDATE ERROR! \n");
            e.printStackTrace();
        }
    }


}
