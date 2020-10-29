package model;

public class BuyModel {
    public String idNum;            // 게시글 식별아이디
    public String title;            //제목
    public String description;      // 내용
    public String host;             //uid
    public int currentNOP; //현재 모인 인원
    public int targetNOP; // 목표 공구 인원


    public BuyModel(){

    };
    public BuyModel(String idNum, String title, String description, String host, int currentNOP, int targetNOP) {
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

    public int getCurrentNOP() {
        return currentNOP;
    }

    public void setCurrentNOP(int currentNOP) {
        this.currentNOP = currentNOP;
    }

    public int getTargetNOP() {
        return targetNOP;
    }

    public void setTargetNOP(int targetNOP) {
        this.targetNOP = targetNOP;
    }
}