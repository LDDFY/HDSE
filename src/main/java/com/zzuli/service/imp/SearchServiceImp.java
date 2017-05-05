package com.zzuli.service.imp;

import com.zzuli.dao.WeiBoDao;
import com.zzuli.search.IndexSearch;
import com.zzuli.search.Pager;
import com.zzuli.search.SearchEntity;
import com.zzuli.service.SearchService;
import com.zzuli.vo.WeiBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LDDFY on 2017/5/4.
 */
@Service("searchService")
public class SearchServiceImp implements SearchService {

    @Autowired
    private WeiBoDao weiBoDao;

    @Autowired
    private IndexSearch indexSearch;

    @Override
    public Pager<WeiBo> getPageInfo(SearchEntity entity, Pager<WeiBo> pager) {

        return indexSearch.queryLucenePage(entity, pager);
    }

    @Override
    public WeiBo getEntityById(String key, String value, String collection) {
        return weiBoDao.queryOneWeiBo(key, value, collection);
    }
}
