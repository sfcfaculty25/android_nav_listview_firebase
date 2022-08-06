package com.techno.myapplication;

public class Data
{
    public String userid;
    public String username;
    public String useremail;
    public String userpass;

    public Data()
    {
    }

    //Alt + insert
    public Data(String userid, String username, String useremail, String userpass) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.userpass = userpass;
    }

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUserpass() {
        return userpass;
    }
}
