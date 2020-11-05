package model;

public class BuyModel {
    public String idNum;            // 게시글 식별아이디
    public String title;            //제목
    public String description;      // 내용
    public String host;             //uid
    public String currentNOP; //현재 모인 인원
    public String targetNOP; // 목표 공구 인원


<<<<<<< HEAD
    public BuyModel() {
=======
    public BuyModel(){

    };
    public BuyModel(String idNum, String title, String description, String host, String currentNOP, String targetNOP) {
        this.idNum = idNum;
        this.title = title;
        this.description = description;
        this.host = host;
        this.currentNOP = currentNOP;
        this.targetNOP = targetNOP;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHost(String host) {
        this.host = host;
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
>>>>>>> d7dd4fbdf14f9c5f192c4e60dc97a139c81a3a46
    }
}