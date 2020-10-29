package com.example.project_1;

import android.net.Uri;

public class HomeShareItem {
    private String idNum;
    private String profile;
    private String title;
    private String adress;

    public HomeShareItem(String idNum, String profile, String title, String adress) {
        this.idNum = idNum;
        this.profile = profile;
        this.title = title;
        this.adress = adress;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
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
