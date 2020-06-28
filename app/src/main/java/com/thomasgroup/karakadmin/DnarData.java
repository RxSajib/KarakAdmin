package com.thomasgroup.karakadmin;

public class DnarData {

    private String time, login_name, donar_profile_imageURL, donar_post;
    private String  donar_number, donar_name, donar_location, donar_gender;
    private String donar_bloodgroup, date;

    private DnarData(){

    }

    public DnarData(String time, String login_name, String donar_profile_imageURL, String donar_post, String donar_number, String donar_name, String donar_location, String donar_gender, String donar_bloodgroup, String date) {
        this.time = time;
        this.login_name = login_name;
        this.donar_profile_imageURL = donar_profile_imageURL;
        this.donar_post = donar_post;
        this.donar_number = donar_number;
        this.donar_name = donar_name;
        this.donar_location = donar_location;
        this.donar_gender = donar_gender;
        this.donar_bloodgroup = donar_bloodgroup;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getDonar_profile_imageURL() {
        return donar_profile_imageURL;
    }

    public void setDonar_profile_imageURL(String donar_profile_imageURL) {
        this.donar_profile_imageURL = donar_profile_imageURL;
    }

    public String getDonar_post() {
        return donar_post;
    }

    public void setDonar_post(String donar_post) {
        this.donar_post = donar_post;
    }

    public String getDonar_number() {
        return donar_number;
    }

    public void setDonar_number(String donar_number) {
        this.donar_number = donar_number;
    }

    public String getDonar_name() {
        return donar_name;
    }

    public void setDonar_name(String donar_name) {
        this.donar_name = donar_name;
    }

    public String getDonar_location() {
        return donar_location;
    }

    public void setDonar_location(String donar_location) {
        this.donar_location = donar_location;
    }

    public String getDonar_gender() {
        return donar_gender;
    }

    public void setDonar_gender(String donar_gender) {
        this.donar_gender = donar_gender;
    }

    public String getDonar_bloodgroup() {
        return donar_bloodgroup;
    }

    public void setDonar_bloodgroup(String donar_bloodgroup) {
        this.donar_bloodgroup = donar_bloodgroup;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
