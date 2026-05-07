package com.YiGeon.demo.service;

import com.YiGeon.demo.domain.Member;
import com.YiGeon.demo.domain.Penguin;
import com.YiGeon.demo.domain.Store;
import com.YiGeon.demo.repository.MemberRepository;
import com.YiGeon.demo.repository.PenguinRepository;
import com.YiGeon.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Objects;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final PenguinRepository penguinRepository;

    public StoreService(StoreRepository storeRepository, MemberRepository memberRepository, PenguinRepository penguinRepository) {
        this.storeRepository = storeRepository;
        this.memberRepository = memberRepository;
        this.penguinRepository = penguinRepository;
    }

    // 상점 모든 매물 조회
    public List<Store> getAllStoreItems() {
        return storeRepository.findAllStorePenguins();
    }

    // 펭귄 구매
    public void buyPenguin(String buyerId, int storeId){
        Store storeItem = storeRepository.findById(storeId); // 해당 펭귄이 존재하는지 확인
        if (storeItem == null) {
            throw new IllegalArgumentException("Already Sold or Doesn't Exist.");
        }

        Member buyer = memberRepository.findByOwnerId(buyerId); // 구매자 정보(잔액) 확인
        if(buyer.getAssets() < storeItem.getPrice()){
            throw new IllegalArgumentException("Lack of balance : (Current Assets: " + buyer.getAssets() + "￦)");
        }

        double currentAssets = buyer.getAssets();
        memberRepository.updateAssets(buyerId, (currentAssets - storeItem.getPrice())); // 결제 진행

        // 내 펭귄 목록에 등록
        Penguin myNewPenguin = new Penguin();
        myNewPenguin.setSpecies(storeItem.getSpecies());
        myNewPenguin.setIsland(storeItem.getIsland());
        myNewPenguin.setSex(storeItem.getSex());
        myNewPenguin.setPrice(storeItem.getPrice());
        myNewPenguin.setOwnerId(buyerId);
        penguinRepository.insertPenguin(myNewPenguin);

        // 상점에서 해당 펭귄 삭제
        storeRepository.deleteStorePenguin(storeId);

        System.out.println(">> Transaction completed: " + "[" + buyerId + "]" + storeItem.getSpecies() + "adopted");
    }

    // 펭귄 판매
    public void sellPenguin(String sellerId, int penguinId, Double hopePrice){
        Penguin salePenguin = penguinRepository.findById(sellerId, penguinId);
        if (salePenguin == null || !salePenguin.getOwnerId().equals(sellerId)) {
            throw new IllegalArgumentException("It's a non-existent Penguin");
        }

        Store newPenguin = new Store();
        newPenguin.setSellerId(sellerId);
        newPenguin.setIsland(salePenguin.getIsland());
        newPenguin.setSex(salePenguin.getSex());
        newPenguin.setSpecies(salePenguin.getSpecies());
        newPenguin.setPrice(hopePrice);

        storeRepository.insertStorePenguin(newPenguin);
        penguinRepository.deletePenguin(salePenguin.getId(), sellerId);

        System.out.println(">> Complete Registration: [" + sellerId + "] " + "[" + salePenguin.getSpecies() + "]" + hopePrice + "￦");
    }
}
