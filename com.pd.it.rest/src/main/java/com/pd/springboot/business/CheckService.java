package com.pd.springboot.business;

import static com.pd.it.common.util.StaticTool.SUCCESS;
import static com.pd.it.common.util.StaticTool.success;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.it.common.annotations.ICheck;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.springboot.adaptor.impl.RedisAdaptor;

@Named
public class CheckService {
    @Inject
    private RedisAdaptor redisAdapter;

    public ResultVO check() {
        List<String> resultStrList = new ArrayList<>();
        resultStrList.add(validRedisSet());
        resultStrList.add(validRedisQuery());
        return success(resultStrList);
    }

    @ICheck
    public String validRedisSet() {
        redisAdapter.set("test", "test11");
        return SUCCESS;
    }

    @ICheck
    public String validRedisQuery() {
        redisAdapter.query("test");
        return SUCCESS;
    }
}
