package com.thomasgroup.karakadmin;

public class UsersData {

    private String Name, blood, phone;

    public UsersData(){

    }

    public UsersData(String name, String blood, String phone) {
        Name = name;
        this.blood = blood;
        this.phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
