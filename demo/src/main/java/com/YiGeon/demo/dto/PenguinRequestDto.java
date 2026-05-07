package com.YiGeon.demo.dto;

import jakarta.validation.constraints.*;


public class PenguinRequestDto {
    @NotBlank(message = "펭귄 종류(species)는 반드시 입력해야 합니다.")
    public String species;

    @Size(min = 1,max=20, message = "서식지(island)는 최대 20글자입니다.")
    private String island;

    @Pattern(regexp = "MALE|FEMALE|NA", message = "남|여|NA 중 하나만 가능합니다.")
    private String sex;

    @NotBlank(message = "아이디는 반드시 입력해야 합니다.")
    private String ownerId;

    private Double culmenLength;
    private Double culmenDepth;
    private Double flipperLength;
    private Double bodyMass;
    private String noteDate;
    private String noteContent;


    public String getSpecies() {
        return species;
    }

    public String getIsland() {
        return island;
    }

    public String getSex() {
        return sex;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Double getCulmenLength() { return culmenLength; }

    public Double getCulmenDepth() { return culmenDepth; }

    public Double getFlipperLength() { return flipperLength; }

    public Double getBodyMass() { return bodyMass; }

    public String getNoteDate() { return noteDate; }

    public String getNoteContent() { return noteContent; }

}
