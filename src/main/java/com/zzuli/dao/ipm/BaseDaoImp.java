package com.zzuli.dao.ipm;

import com.zzuli.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.ParameterizedType;

/**
 * @author LDDFY
 */

public class BaseDaoImp<T> implements BaseDao {

    @Autowired
    private MongoOperations mongoOperations;

    protected Class<T> clazz;

    public BaseDaoImp() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * mongoDB单条内容查询
     * @param key 查询字段
     * @param value 查询内容
     * @param collection 查询集合
     * @return
     */
    @Override
    public T queryOne(String key, String value, String collection) {
        return mongoOperations.findOne(new Query(Criteria.where(key).is(value)), clazz, collection);
    }
}
