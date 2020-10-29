package model;


public class BuyModel {
    public String idNum;            // 게시글 식별아이디
    public String title;            //제목
    public String description;      // 내용
    public String host;             //uid

    public BuyModel(){

    };
    public BuyModel(String idNum, String title, String description, String host) {
        this.idNum = idNum;
        this.title = title;
        this.description = description;
        this.host = host;
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

