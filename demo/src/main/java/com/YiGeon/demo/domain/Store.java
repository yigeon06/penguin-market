package com.YiGeon.demo.domain;

public class Store {
    private int id;
    private String species;
    private String island;
    private String sex;
    private double price;
    private String sellerId; // 'Nature' 또는 판매하는 유저의 아이디

    public Store() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getIsland() { return island; }
    public void setIsland(String island) { this.island = island; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getSellerId() { return sellerId; }
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }
}