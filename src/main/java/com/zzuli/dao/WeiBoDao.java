package com.zzuli.dao;

import com.zzuli.vo.WeiBo;

/**
 * @author LDDFY
 */

public interface WeiBoDao   {
    public WeiBo queryOneWeiBo(String key,String value,String collection);
}
