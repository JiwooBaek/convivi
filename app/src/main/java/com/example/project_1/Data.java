package com.example.project_1;

public class Data {
    String name;    //닉네임
    String title;   //제목
    int imgld;  //프로필 이미지?

    public Data(String name, String title, int imgld){
        this.name = name;
        this.title = title;
        this.imgld = imgld;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImgld(int imgld) {
        this.imgld = imgld;
    }

    public String getName(){
        return name;
    }

    public String getTitle(){
        return title;
    }

    public int getImgld(){
        return imgld;
    }

}