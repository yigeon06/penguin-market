package com.YiGeon.demo.controller;

import com.YiGeon.demo.domain.Store;
import com.YiGeon.demo.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping // 상점 펭귄 전체 조회
    public List<Store> getStoreItems() {
        return storeService.getAllStoreItems();
    }

    @PostMapping("/buy/{storeId}") // 상점 펭귄 전체 조회
    public String buyPenguin(@PathVariable int storeId, @RequestParam String buyerId) {
        // 방금 우리가 만든 그 서비스(비즈니스 로직)를 호출합니다!
        storeService.buyPenguin(buyerId, storeId);
        return "성공적으로 펭귄을 입양했습니다! 마이페이지를 확인해 주세요.";
    }

    @PostMapping("/sell/{penguinId}") // 펭귄 판매
    public String sellPenguin(@PathVariable int penguinId,
                              @RequestParam String sellerId,
                              @RequestParam double hopePrice) {

        storeService.sellPenguin(sellerId, penguinId, hopePrice);
        return "펭귄이 분양소에 성공적으로 등록되었습니다!";
    }
}
