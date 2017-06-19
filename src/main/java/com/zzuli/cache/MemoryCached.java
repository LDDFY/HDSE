package com.zzuli.cache;

import com.zzuli.Constants;
import com.zzuli.search.Pager;
import com.zzuli.search.SearchEntity;
import com.zzuli.vo.WeiBo;

/**
 * @author LDDFY
 */
public class MemoryCached {

    //用于缓存Map
    public static LRULinkedHashMap<String, Pager<WeiBo>> cacheMap = new LRULinkedHashMap<String, Pager<WeiBo>>(Constants.CACHE_SIZE);


    //从缓存中获取查询内容
    public static Pager<WeiBo> getWeiBoCache(SearchEntity entity, Pager<WeiBo> pager) {
        //key,type,pageNum
        String key = entity.getKey() + "," + entity.getType() + "," + pager.getCurrentPage();
        if (cacheMap.containsKey(key)) {
            return (Pager<WeiBo>) cacheMap.get(key);
        }
        return null;
    }

    //将查询内容放置到缓存中
    public static void putWeiBoCache(SearchEntity entity, Pager<WeiBo> pager){
        String key = entity.getKey() + "," + entity.getType() + "," + pager.getCurrentPage();
        cacheMap.put(key,pager);
    }
}
