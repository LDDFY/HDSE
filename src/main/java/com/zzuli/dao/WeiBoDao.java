package com.zzuli.dao;

import com.zzuli.search.Pager;
import com.zzuli.vo.WeiBo;

/**
 * @author LDDFY
 */

public interface WeiBoDao {
    //查询单个记录内容
    public WeiBo queryOneWeiBo(String key, String value, String collection);

    //查询分页信息
    public Pager<WeiBo> queryPageInfo(String key, String value, String collection);
}
