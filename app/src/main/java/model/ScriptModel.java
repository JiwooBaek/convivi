package model;


public class ScriptModel {
    public String title;   //제목
    public String host; //사용자uid
    public String description; // 내용
    public int imgld;  //프로필 이미지?

    public ScriptModel() {
    }

    public ScriptModel(String title, String host, String description, int imgld) {
        this.title = title;
        this.host = host;
        this.description = description;
        this.imgld = imgld;
    }
}


