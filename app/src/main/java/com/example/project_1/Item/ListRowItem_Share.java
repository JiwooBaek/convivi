package com.example.project_1.Item;

public class ListRowItem_Share {
    private String profile;
    private String title;
    private String description;

    public ListRowItem_Share(String profile, String title, String description) {
        this.profile = profile;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
