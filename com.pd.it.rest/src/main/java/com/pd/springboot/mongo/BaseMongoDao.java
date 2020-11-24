package com.pd.springboot.mongo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.pd.base.exception.BusinessException;
import com.pd.standard.itf.IQueryInfoOperation;

@Component
public class BaseMongoDao<T> implements IQueryInfoOperation<T, T> {
    @Resource
    private MongoTemplate mongoTemplate;

    public int truncate() {
        Query query = new Query();
        mongoTemplate.remove(query);
        return 0;
    }

    public int insert(T vo) {
        mongoTemplate.save(vo);
        return 0;
    }

    @Override
    public T queryInfo(T in) throws BusinessException {
        return (T) mongoTemplate.findById(in, in.getClass());
    }

    @Override
    public T queryDetailInfo(T in) throws BusinessException {
        return null;
    }

    @Override
    public String queryJson(T in) throws BusinessException {
        return null;
    }
}