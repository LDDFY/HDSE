package com.zzuli.service;

import com.zzuli.Constants;
import com.zzuli.search.Pager;
import com.zzuli.search.SearchEntity;
import com.zzuli.vo.WeiBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by LDDFY on 2017/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSearchService {
    @Autowired
    private SearchService searchService;

    @Test
    public void testGetPageInfo() {
        SearchEntity entity = new SearchEntity();
        entity.setKey("澳大利亚");
        entity.setType(Constants.CODE_TEXT);
        Pager<WeiBo> pager = new Pager<WeiBo>();
        pager.setCurrentPage(3);
        Pager<WeiBo> data = searchService.getPageInfo(entity, pager);

        System.out.println(data.toString());
    }

    @Test
    public void testGetEntityById() {
        // public WeiBo getEntityById(String key,String value,String collection);

    }
}
