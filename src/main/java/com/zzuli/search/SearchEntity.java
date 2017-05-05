package com.zzuli.search;

/**
 * Created by LDDFY on 2017/5/4.
 */
public class SearchEntity {
    //搜索项
    private String type;
    //搜索关键词
    private String key;
    public SearchEntity( ) {

    }

    public SearchEntity(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
