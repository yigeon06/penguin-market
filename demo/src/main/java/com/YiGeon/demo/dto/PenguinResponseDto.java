package com.YiGeon.demo.dto;

import com.YiGeon.demo.domain.Penguin;

public class PenguinResponseDto {

    // 외부 공개 가능 정보 (id, 종,서식지,성별)
    private int id;
    private String species;
    private String island;
    private String sex;

    private Double culmenLength;
    private Double culmenDepth;
    private Double flipperLength;
    private Double bodyMass;
    private String noteDate;
    private String noteContent;

    public Double getCulmenLength() { return culmenLength; }
    public Double getCulmenDepth() { return culmenDepth; }
    public Double getFlipperLength() { return flipperLength; }
    public Double getBodyMass() { return bodyMass; }
    public String getNoteDate() { return noteDate; }
    public String getNoteContent() { return noteContent; }

    public PenguinResponseDto(Penguin penguin) {
        this.id = penguin.getId();
        this.species = penguin.getSpecies();
        this.island = penguin.getIsland();
        this.sex = penguin.getSex();
        this.culmenLength = penguin.getCulmenLengthMm();
        this.culmenDepth = penguin.getCulmenDepthMm();
        this.flipperLength = penguin.getFlipperLengthMm();
        this.bodyMass = penguin.getBodyMassG();
        this.noteDate = penguin.getNoteDate();
        this.noteContent = penguin.getNoteContent();
    }

    public String getSpecies() {
        return species;
    }

    public String getIsland() {
        return island;
    }

    public String getSex() {
        return sex;
    }

    public int getId() {
        return id;
    }
}
