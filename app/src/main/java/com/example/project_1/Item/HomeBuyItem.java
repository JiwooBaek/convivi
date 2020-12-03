package com.example.project_1.Item;

import android.net.Uri;

public class HomeBuyItem {
    private String id;
    private String imageUrl;
    private String title;
    private String adress;
    private String currentNOP;
    private String targetNOP;

    public HomeBuyItem(String id, String imageUrl, String title, String adress, String currentNOP, String targetNOP) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.adress = adress;
        this.currentNOP = currentNOP;
        this.targetNOP = targetNOP;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCurrentNOP() {
        return currentNOP;
    }

    public void setCurrentNOP(String currentNOP) {
        this.currentNOP = currentNOP;
    }

    public String getTargetNOP() {
        return targetNOP;
    }

    public void setTargetNOP(String targetNOP) {
        this.targetNOP = targetNOP;
    }
}
