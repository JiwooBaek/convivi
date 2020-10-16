package com.example.project_1.Model;

public class User {

    private String uid;
    private String id;
    private String username;
    private String imageURL;

    public User(String uid, String id, String username, String imageURL) {
        this.uid = uid;
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
