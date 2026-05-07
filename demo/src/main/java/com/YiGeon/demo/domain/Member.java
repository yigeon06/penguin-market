package com.YiGeon.demo.domain;

public class Member {
    private String ownerId;
    private String password;
    private  double assets;

    private String name;
    private String birthdate;
    private String phoneNumber;

    public Member() {}
    public Member(String ownerId, String password, double assets, String name, String birthdate, String phoneNumber) {
        this.ownerId = ownerId;
        this.password = password;
        this.assets = assets;
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPassword() {
        return password;
    }

    public double getAssets() {
        return assets;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAssets(double assets) {
        this.assets = assets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
