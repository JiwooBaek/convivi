package com.example.project_1;

public class HomeBuyItem {
    private int profile;
    private String title;
    private String adress;
    private String currentNOP;
    private String targetNOP;

    public HomeBuyItem(int profile, String title, String adress, String currentNOP, String targetNOP) {
        this.profile = profile;
        this.title = title;
        this.adress = adress;
        this.currentNOP = currentNOP;
        this.targetNOP = targetNOP;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
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
