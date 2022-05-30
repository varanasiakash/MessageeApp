package com.example.internshipchatapp;

public class UsersCall {
    private String username;
    private String emailID;
    private String profile_pic ;

    //for firebase database
    public UsersCall()
    {

    }

    //constructors
    public UsersCall(String username, String emailID, String profile_pic) {
        this.username = username;
        this.emailID = emailID;
        this.profile_pic = profile_pic;
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
