package com.techno.myapplication;

public class User {

    public String userId;
    public String fname;
    public String lname;
    public String email;
    public String pass;
    public String contact;
    public String imgname;

    public User()
    {
    }

    public User(String userId, String fname, String lname, String email, String pass, String contact,String imgname) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.contact = contact;
        this.imgname = imgname;
    }

    public String getUserId() {
        return userId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getContact() {
        return contact;
    }

    public String getImgname() {
        return imgname;
    }
}
