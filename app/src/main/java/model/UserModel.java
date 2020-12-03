package model;

public class UserModel {
    public String uid;
    public String name;
    public String emailAddress;
    public String imgURL;
    public boolean phoneAuthFlag;
    public String address;
    public boolean addressFlag;


    public UserModel() {

    }

    public UserModel(String uid, String name, String emailAddress, String imgURL) {
        this.uid = uid;
        this.name = name;
        this.emailAddress = emailAddress;
        this.imgURL = imgURL;
    }

    public UserModel(String uid, String name, String emailAddress, String imgURL, boolean phoneAuthFlag, String address, boolean addressFlag) {
        this.uid = uid;
        this.name = name;
        this.emailAddress = emailAddress;
        this.imgURL = imgURL;
        this.phoneAuthFlag = phoneAuthFlag;
        this.address = address;
        this.addressFlag = addressFlag;
    }
}


