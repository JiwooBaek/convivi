package com.example.project_1.Item;

public class ListRowItem_Buy {
    private String id;
    private String image;
    private String title;
    private String description;
    private int currentNOP;
    private int targetNOP;

    public ListRowItem_Buy(String id, String image, String title, String description, int currentNOP, int targetNOP) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.currentNOP = currentNOP;
        this.targetNOP = targetNOP;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentNOP() {
        return currentNOP;
    }

    public void setCurrentNOP(int currentNOP) {
        this.currentNOP = currentNOP;
    }

    public int getTargetNOP() {
        return targetNOP;
    }

    public void setTargetNOP(int targetNOP) {
        this.targetNOP = targetNOP;
    }
}