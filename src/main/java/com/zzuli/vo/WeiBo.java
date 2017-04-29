package com.zzuli.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 *@author LDDFY
 */
@Document
public class WeiBo implements Serializable {
    @Id
    private String id;

    @Field("cotent")
    private String cotent;

    @Field("time")
    private String time;

    @Field("userinfo")
    private User userInfo;

    public WeiBo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUserInfo() {
        return userInfo;
    }


    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "WeiBo{" +
                "id='" + id + '\'' +
                ", cotent='" + cotent + '\'' +
                ", time='" + time + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
