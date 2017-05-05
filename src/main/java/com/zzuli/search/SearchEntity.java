package com.zzuli.search;

/**
 * Created by LDDFY on 2017/5/4.
 */
public class SearchEntity {
    private String type;
    private String key;
    private Integer currentPage;
    public SearchEntity( ) {

    }

    public SearchEntity(String type, String key, Integer currentPage) {
        this.type = type;
        this.key = key;
        this.currentPage = currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
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

    @Override
    public String toString() {
        return "SearchEntity{" +
                "type=" + type +
                ", key='" + key + '\'' +
                '}';
    }
}
