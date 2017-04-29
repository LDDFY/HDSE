package com.zzuli.dao;

/**
 * @author LDDFY
 */
public interface BaseDao<T> {

    public T queryOne(String key,String value,String collection);

}
