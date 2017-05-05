package com.zzuli.service;

import com.zzuli.search.Pager;
import com.zzuli.search.SearchEntity;
import com.zzuli.vo.WeiBo;

/**
 * Created by LDDFY on 2017/5/4.
 */
public interface SearchService {
    //分页查询
    public Pager<WeiBo> getPageInfo(SearchEntity entity, Pager<WeiBo> pager);
    //单个查询
    public WeiBo getEntityById(String key,String value,String collection);
}
