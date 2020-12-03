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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public boolean isPhoneAuthFlag() {
        return phoneAuthFlag;
    }

    public void setPhoneAuthFlag(boolean phoneAuthFlag) {
        this.phoneAuthFlag = phoneAuthFlag;
    }
}


