package com.example.project_1;

public class HomeShareItem {
    private int profile;
    private String title;
    private String adress;

    public HomeShareItem(int profile, String title, String adress) {
        this.profile = profile;
        this.title = title;
        this.adress = adress;
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
}
