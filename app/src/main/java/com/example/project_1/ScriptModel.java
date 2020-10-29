package com.example.project_1;


public class ScriptModel {
<<<<<<< HEAD:app/src/main/java/com/example/project_1/ScriptModel.java
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
=======
    public long idNum;           // 게시글 고유번호
    public String title;        //제목
    public String host;         //사용자uid
    public String description;  // 내용
    public int imgld;           //프로필 이미지?

    public ScriptModel() {
    }

    public ScriptModel(long idNum, String title, String host, String description, int imgld) {
        this.idNum = idNum;
        this.title = title;
        this.host = host;
        this.description = description;
        this.imgld = imgld;
    }
}

>>>>>>> 2bbd814ddc400a098469c031a300c7bed51e7634:app/src/main/java/model/ScriptModel.java

