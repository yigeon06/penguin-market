package com.YiGeon.demo.dto;

import com.YiGeon.demo.domain.Penguin;
import jakarta.validation.constraints.NotBlank;

public class SignupRequestDto {
    @NotBlank private String ownerId;
    @NotBlank private String password;
    @NotBlank private String name;
    @NotBlank private String birthdate;
    @NotBlank private String phoneNumber;

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
