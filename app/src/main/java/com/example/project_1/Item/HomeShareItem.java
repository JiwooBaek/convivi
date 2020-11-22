package com.example.project_1.Item;

public class HomeShareItem {
    private String id;
    private String profile;
    private String title;
    private String adress;

    public HomeShareItem(String id, String profile, String title, String adress) {
        this.id = id;
        this.profile = profile;
        this.title = title;
        this.adress = adress;
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
}
