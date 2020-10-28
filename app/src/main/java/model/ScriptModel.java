package model;


public class ScriptModel {
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


