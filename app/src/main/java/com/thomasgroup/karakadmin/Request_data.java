package com.thomasgroup.karakadmin;

public class Request_data {

    private String blood_group,location,loginusername;
    private String message, mobilenumber, Image_downloadurl, patentname;


    public Request_data(){

    }

    public Request_data(String blood_group, String location, String loginusername, String message, String mobilenumber, String image_downloadurl, String patentname) {
        this.blood_group = blood_group;
        this.location = location;
        this.loginusername = loginusername;
        this.message = message;
        this.mobilenumber = mobilenumber;
        Image_downloadurl = image_downloadurl;
        this.patentname = patentname;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLoginusername() {
        return loginusername;
    }

    public void setLoginusername(String loginusername) {
        this.loginusername = loginusername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getImage_downloadurl() {
        return Image_downloadurl;
    }

    public void setImage_downloadurl(String image_downloadurl) {
        Image_downloadurl = image_downloadurl;
    }

    public String getPatentname() {
        return patentname;
    }

    public void setPatentname(String patentname) {
        this.patentname = patentname;
    }
}
