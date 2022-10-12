package com.pd.redis.businessobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class RedisVO {
    private String key;
    private String value;
}
