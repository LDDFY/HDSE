package com.zzuli.dao;

import com.zzuli.vo.WeiBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LDDFY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:springmvc-servlet.xml"})
public class TestDao {
    @Autowired
    private WeiBoDao weiBoDao;

    @Test
    public void testWeiBoDao() {
        WeiBo weiBo = weiBoDao.queryOneWeiBo("id", "58d61ef0a1f60e1b88ccabc2", "data");
        System.out.println("----------------->" + weiBo.toString());
    }

}
