package com.pd.springboot.redis.service;

import com.pd.it.common.itf.PlusService;
import com.pd.redis.businessobject.RedisFO;
import com.pd.redis.businessobject.RedisVO;
import com.pd.springboot.adaptor.impl.RedisAdaptor;
import com.pd.springboot.wiki.dao.IWikiPlusDao;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class RedisService {
    @Inject
    private RedisAdaptor redisAdaptor;

    public List<RedisVO> keys(RedisFO fo) {
        List<RedisVO> result=redisAdaptor.keys(fo.getKeys());
        return result;
    }
    public String get(String key) {
        return redisAdaptor.query(key);
    }
}
