package com.YiGeon.demo.repository;

import com.YiGeon.demo.domain.Store;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreRepository {
    private final String url = "jdbc:mysql://localhost:3306/test_database";
    private final String user = "root";
    private final String serverpassword = "leegun0626@";

    // 상점 내 모든 펭귄 조회
    public List<Store> findAllStorePenguins(){
        List<Store> storeList = new ArrayList<>();
        String sql = "SELECT * FROM store";

        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Store store = new Store();
                store.setId(rs.getInt("id"));
                store.setSpecies(rs.getString("species"));
                store.setIsland(rs.getString("island"));
                store.setSex(rs.getString("sex"));
                store.setPrice(rs.getInt("price"));
                store.setSellerId(rs.getString("seller_id"));
                storeList.add(store);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error Looking Up store list:" + e.getMessage());
        }
        return storeList;
    }

    // 특정 펭귄의 정보 조회
    public Store findById(int id) {
        String sql = "SELECT * FROM store WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Store store = new Store();
                    store.setId(rs.getInt("id"));
                    store.setSpecies(rs.getString("species"));
                    store.setIsland(rs.getString("island"));
                    store.setSex(rs.getString("sex"));
                    store.setPrice(rs.getInt("price"));
                    store.setSellerId(rs.getString("seller_id"));
                    return store;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error Individual store lookup: " + e.getMessage());
        }
        return null;
    }

    // 구매시 상점에서 삭제
    public void deleteStorePenguin(int id) {
        String sql = "DELETE FROM store WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting store sale: " + e.getMessage());
        }
    }

    // 판매 펭귄 등록
    public void insertStorePenguin(Store store) {
        String sql = "INSERT INTO store (species, island, sex, price, seller_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, serverpassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, store.getSpecies());
            pstmt.setString(2, store.getIsland());
            pstmt.setString(3, store.getSex());
            pstmt.setDouble(4, store.getPrice());
            pstmt.setString(5, store.getSellerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("상점 매물 등록 중 오류 발생: " + e.getMessage());
        }
    }
}
