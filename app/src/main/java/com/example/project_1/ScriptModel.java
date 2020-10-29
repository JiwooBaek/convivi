package com.example.project_1;


public class ScriptModel {
    public String title;   //제목
    public String description; // 내용
    public String host; //uid
    public Integer image;  //프로필 이미지?

    public ScriptModel(){

    };
    public ScriptModel(String title, String description, String host, Integer image) {
        this.title = title;
        this.description = description;
        this.host = host;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHost() {
        return host;
    }

    public Integer getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}

//    public ScriptModel(String uid, String title, String description, int imgld){
//        this.uid = uid;
//        this.title = title;
//        this.description = description;
//        this.imgld = imgld;
//    }
//
//
//
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
//        result.put("title", title);
//        result.put("description",description);
//        result.put("imgld", imgld);
//        return result;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setImgld(int imgld) {
//        this.imgld = imgld;
//    }
//
//    public String getuid(){
//        return uid;
//    }
//
//    public String getTitle(){
//        return title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getImgld(){
//        return imgld;
//    }
//


