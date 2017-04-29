package com.zzuli.vo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author LDDFY
 */
@Document
public class User implements Serializable {

    @Field("username")
    private String userName;
    @Field("usersex")
    private String userSex;
    @Field("userregion")
    private String userRegion;
    @Field("userbri")
    private String userBri;

    public User() {

    }

    public User(String userName, String userSex, String userRegion, String userBri) {
        this.userName = userName;
        this.userSex = userSex;
        this.userRegion = userRegion;
        this.userBri = userBri;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public String getUserBri() {
        return userBri;
    }

    public void setUserBri(String userBri) {
        this.userBri = userBri;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userRegion='" + userRegion + '\'' +
                ", userBri='" + userBri + '\'' +
                '}';
    }
}
