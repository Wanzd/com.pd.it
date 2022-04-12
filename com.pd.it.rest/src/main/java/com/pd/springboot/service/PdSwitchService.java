package com.pd.springboot.service;

import com.pd.businessobject.LookupItemFO;
import com.pd.it.common.util.ValidTool;
import com.pd.springboot.adaptor.IRedisAdaptor;
import com.pd.springboot.dao.ILookupItemDao;
import com.pd.standard.itf.RedisConst;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PdSwitchService implements IPdSwitchService {

    @Autowired
    private ILookupItemDao lookupItemDao;

    @Inject
    IRedisAdaptor redis;

    @Override
    public boolean getLookupSwtich(String code) {
        LookupItemFO lookupFO = new LookupItemFO();
        lookupFO.setTypeCode(RedisConst.LOOKUP_SWITCH);
        lookupFO.setCode(code);

        return ValidTool.isY(lookupItemDao.queryValue(lookupFO));
    }

    @Override
    public boolean getRedisSwtich(String code) {
        return ValidTool.isY(redis.query(code));
    }

    @Override
    public void refreshLookupSwitch() {
        redis.delete(RedisConst.LOOKUP_SWITCH);
    }
}
