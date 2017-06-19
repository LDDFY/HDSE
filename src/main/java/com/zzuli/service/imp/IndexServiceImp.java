package com.zzuli.service.imp;

import com.zzuli.index.CreateIndex;
import com.zzuli.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LDDFY
 */
@Service("indexService")
public class IndexServiceImp implements IndexService {

    @Autowired
    private CreateIndex createIndex;

    @Override
    public boolean createIndex() {
        return createIndex.IndexRun();
    }
}
