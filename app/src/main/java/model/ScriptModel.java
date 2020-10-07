package model;

public class Script {
    String uid;    //닉네임
    String title;   //제목
    int imgld;  //프로필 이미지?

    public Script(String uid, String title, int imgld){
        this.uid = uid;
        this.title = title;
        this.imgld = imgld;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImgld(int imgld) {
        this.imgld = imgld;
    }

    public String getuid(){
        return uid;
    }

    public String getTitle(){
        return title;
    }

    public int getImgld(){
        return imgld;
    }

}