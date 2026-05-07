package com.YiGeon.demo.dto;

public class PenguinRecordDto {
    private int recordId;
    private int penguinId;
    private Double culmenLength;
    private Double culmenDepth;
    private Double flipperLength;
    private Double bodyMass;
    private String noteDate;
    private String noteContent;

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public int getPenguinId() { return penguinId; }
    public void setPenguinId(int penguinId) { this.penguinId = penguinId; }

    public Double getCulmenLength() { return culmenLength; }
    public void setCulmenLength(Double culmenLength) { this.culmenLength = culmenLength; }

    public Double getCulmenDepth() { return culmenDepth; }
    public void setCulmenDepth(Double culmenDepth) { this.culmenDepth = culmenDepth; }

    public Double getFlipperLength() { return flipperLength; }
    public void setFlipperLength(Double flipperLength) { this.flipperLength = flipperLength; }

    public Double getBodyMass() { return bodyMass; }
    public void setBodyMass(Double bodyMass) { this.bodyMass = bodyMass; }

    public String getNoteDate() { return noteDate; }
    public void setNoteDate(String noteDate) { this.noteDate = noteDate; }

    public String getNoteContent() { return noteContent; }
    public void setNoteContent(String noteContent) { this.noteContent = noteContent; }
}