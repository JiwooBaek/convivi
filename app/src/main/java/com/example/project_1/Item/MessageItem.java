package com.example.project_1.Item;

public class MessageItem {

    String name;
    String message;
    String time;
    String imgURL;
    String host;      //uid

    public MessageItem(String name, String message, String time, String imgURL, String host) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.imgURL = imgURL;
        this.host = host;
    }

    //firebase DB에 객체로 값을 읽어올 때..
    //파라미터가 비어있는 생성자가 핑요함.
    public MessageItem() {
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}