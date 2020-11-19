package com.example.project_1;

public class HomeBuyItem {
    private String id;
    private String profile;
    private String title;
    private String adress;
    private String currentNOP;
    private String targetNOP;

    public HomeBuyItem(String id, String profile, String title, String adress, String currentNOP, String targetNOP) {
        this.id = id;
        this.profile = profile;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
