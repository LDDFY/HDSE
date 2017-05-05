package com.zzuli.vo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author LDDFY
 */
@Document
public class User implements Serializable {

    @Field("name")
    private String name;
    @Field("gender")
    private String gender;
    @Field("region")
    private String region;
    @Field("birthdate")
    private String birthDate;

    public User() {

    }
    public User(String name, String gender, String region, String birthDate) {
        this.name = name;
        this.gender = gender;
        this.region = region;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
