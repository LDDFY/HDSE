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
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestDao {
    @Autowired
    private WeiBoDao weiBoDao;

    @Test
    public void testWeiBoDao() {
        WeiBo weiBo = weiBoDao.queryOneWeiBo("id", "58f3293606296b2535db252f", "data");
        System.out.println("----------------->" + weiBo.toString());
    }

}
