package com.app.android.june.cryptonewsoffers;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by John.
 */
@IgnoreExtraProperties
public class User {

    public String fullName;
    public String gender;
    public String email;
    public String password;
    public String userId;

    public User() {
    }


    public User(String fullName, String gender, String email, String password, String userId) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




}