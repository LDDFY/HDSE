package com.zzuli.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @author LDDFY
 */
@Document
public class WeiBo implements Serializable {
    @Id
    private String id;

    @Field("reposts_count")
    private String repostsCount;

    @Field("comments_count")
    private String commentsCount;

    @Field("location")
    private String location;

    @Field("date")
    private String date;

    @Field("text")
    private String text;

    @Field("comments")
    private List<String> comments;

    @Field("likes_count")
    private String likesCount;

    @Field("userinfo")
    private User userInfo;

    public WeiBo() {
    }

    public WeiBo(String id, String repostsCount, String commentsCount, String location, String date, String text, List<String> comments, String likesCount, User userInfo) {
        this.id = id;
        this.repostsCount = repostsCount;
        this.commentsCount = commentsCount;
        this.location = location;
        this.date = date;
        this.text = text;
        this.comments = comments;
        this.likesCount = likesCount;
        this.userInfo = userInfo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(String repostsCount) {
        this.repostsCount = repostsCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
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
                ", repostsCount='" + repostsCount + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", likesCount='" + likesCount + '\'' +
                ", userInfo=" + userInfo +
                ", comments='" + comments + '\'' +
                '}';
    }
}
