package com.example.chethangarapati.parkinglot;

public class UserDetails {

    private String nickname;
    private String Email;
    private String phonenumber;

    public UserDetails() {
    }

    public UserDetails(String nickname, String email, String phonenumber) {
        this.nickname = nickname;
        Email = email;
        this.phonenumber = phonenumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
