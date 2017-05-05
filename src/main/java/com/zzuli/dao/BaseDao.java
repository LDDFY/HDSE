package com.zzuli.dao;

/**
 * @author LDDFY
 */
public interface BaseDao<T> {
    /**
     * mongo单个记录查询
     * @param key 查询字段
     * @param value 查询内容
     * @param collection 查询集合
     * @return
     */
    public T queryOne(String key, String value, String collection);
}
