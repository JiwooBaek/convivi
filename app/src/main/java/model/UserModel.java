package model;

public class UserModel {
    public String uid;
    public String name;
    public String emailAddress;
    public String imgURL;
    public boolean phoneAuthFlag;


    public UserModel() {

    }

    public UserModel(String uid, String name, String emailAddress, String imgURL) {
        this.uid = uid;
        this.name = name;
        this.emailAddress = emailAddress;
        this.imgURL = imgURL;
    }
}


