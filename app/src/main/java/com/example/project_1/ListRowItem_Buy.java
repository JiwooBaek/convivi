package com.example.project_1;

public class ListRowItem_Buy {
    private String profile;
    private String title;
    private String description;
    private String currentNOP;
    private String targetNOP;

    public ListRowItem_Buy(String profile, String title, String description, String currentNOP, String targetNOP) {
        this.profile = profile;
        this.title = title;
        this.description = description;
        this.currentNOP = currentNOP;
        this.targetNOP = targetNOP;

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