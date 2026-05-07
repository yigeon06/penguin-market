package com.YiGeon.demo.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "species", "island", "sex", "bodyMassG", "culmenLengthMm", "culmenDepthMm", "flipperLengthMm"})
public class Penguin {
    private int id;
    private String species;
    private String island;
    private double culmen_length_mm;
    private double culmen_depth_mm;
    private double flipper_length_mm;
    private double body_mass_g;
    private String sex;

    private String ownerId;
    private double price;

    private String noteDate;
    private String noteContent;


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getSpecies() {
        return species;
    }

    public String getIsland() {
        return island;
    }

    public double getCulmenLengthMm() {
        return culmen_length_mm;
    }

    public double getCulmenDepthMm() {
        return culmen_depth_mm;
    }

    public double getFlipperLengthMm() {
        return flipper_length_mm;
    }

    public double getBodyMassG() {
        return body_mass_g;
    }

    public String getSex() {
        return sex;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setIsland(String island) {
        this.island = island;
    }

    public void setCulmenLengthMm(double culmen_length_mm) {
        this.culmen_length_mm = culmen_length_mm;
    }

    public void setCulmenDepthMm(double culmen_depth_mm) {
        this.culmen_depth_mm = culmen_depth_mm;
    }

    public void setFlipperLengthMm(double flipper_length_mm) {
        this.flipper_length_mm = flipper_length_mm;
    }

    public void setBodyMassG(double body_mass_g) {
        this.body_mass_g = body_mass_g;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
