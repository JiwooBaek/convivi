package com.example.project_1.Item;

public class HomeShareItem {
    private String id;
    private String imageUrl;
    private String title;
    private String adress;

    public HomeShareItem(String id, String imageUrl, String title, String adress) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.adress = adress;
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
}
