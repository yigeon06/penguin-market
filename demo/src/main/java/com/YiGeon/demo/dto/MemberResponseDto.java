package com.YiGeon.demo.dto;

import com.YiGeon.demo.domain.Member;

public class MemberResponseDto {
    private String ownerId;
    private double assets;

    public MemberResponseDto(Member member) {
        this.ownerId = member.getOwnerId();
        this.assets = member.getAssets();
    }

    public String getOwnerId() { return ownerId; }
    public double getAssets() { return assets; }
}