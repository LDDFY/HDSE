package com.zzuli.dao.ipm;

import com.zzuli.dao.WeiBoDao;
import com.zzuli.vo.WeiBo;
import org.springframework.stereotype.Repository;

/**
 * @author LDDFY
 */
@Repository("weiBoDao")
public class WeiBoDaoImp extends BaseDaoImp<WeiBo> implements WeiBoDao {

    @Override
    public WeiBo queryOneWeiBo(String key, String value, String collection) {
        return super.queryOne(key, value, collection);
    }
}
