package com.YiGeon.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank(message = "아이디는 반드시 입력해야 합니다.")
    private String ownerId;
    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    private String password;

    public String getOwnerId() {
        return ownerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
