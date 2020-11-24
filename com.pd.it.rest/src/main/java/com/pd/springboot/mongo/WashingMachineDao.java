package com.pd.springboot.mongo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.pd.businessobject.WashingMachineBO;

@Component
public class WashingMachineDao extends BaseMongoDao<WashingMachineBO> {
    @Resource
    private MongoTemplate mongoTemplate;

}