package com.YiGeon.demo.service;

import com.YiGeon.demo.domain.Penguin;
import com.YiGeon.demo.dto.PenguinRecordDto;
import com.YiGeon.demo.dto.PenguinRequestDto;
import com.YiGeon.demo.dto.PenguinResponseDto;
import com.YiGeon.demo.repository.PenguinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class PenguinService {
    public final PenguinRepository PenguinRepository;

    public PenguinService(PenguinRepository PenguinRepository){ // 의존성 주입
        this.PenguinRepository = PenguinRepository;
    }

    // 내 펭귄 목록 조회
    public List<PenguinResponseDto> getMyPenguins(String ownerId){
        List<Penguin> penguins = PenguinRepository.findAllByOwnerId(ownerId);
        List<PenguinResponseDto> responseList = new ArrayList<>();

        for(Penguin p : penguins){
            responseList.add(new PenguinResponseDto(p));
        }
        return responseList;
    }

    // 서버 내 전체 펭귄 조회
    public List<PenguinResponseDto> getAllPenguins(){
        List<Penguin> penguins = PenguinRepository.findAllPenguin(); // MySQL에서 전체 데이터 가져오기

        List<PenguinResponseDto> responseList = new ArrayList<>(); // 프론트엔드로 보낼 빈 리스트

        for(Penguin p : penguins){
            responseList.add(new PenguinResponseDto(p)); // 안전한 Dto 상자로 하나씩 포장(owner_id, password 등 제외)
        }
        System.out.println("Complete All penguin data query (Total " + responseList.size() + " penguins)");
        return responseList;
    }

    // 내 펭귄 추가
    public void addPenguin(Penguin penguin){
        System.out.println("New penguin(" + penguin.getSpecies() + ") received a registration request.");
        PenguinRepository.insertPenguin(penguin); // MySQL에 저장
    }

    // 내 펭귄 삭제
    public void deletePenguin(int penguinId, String ownerId){
        System.out.println("Request to delete No. " + penguinId + " Penguin");
        PenguinRepository.deletePenguin(penguinId, ownerId);
    }

    // 내 펭귄 정보 수정
    public void updatePenguinBasic(int penguinId, PenguinRequestDto requestDto){
        System.out.println("No. " + penguinId + " Penguin's request for information modification");
        PenguinRepository.updatePenguinBasic(penguinId, requestDto); // 최신 정보 덮어쓰기
        PenguinRepository.insertPenguinRecord(penguinId, requestDto); // 관찰 일지 추가
    }

    // 기록 가져오기
    public List<PenguinRecordDto> getPenguinRecords(int penguinId) {
        return PenguinRepository.findRecordsByPenguinId(penguinId);
    }
}
