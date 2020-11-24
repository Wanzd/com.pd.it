package com.pd.businessobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class LookupItemBO extends BaseBO {
    @TableId(type = IdType.INPUT)
    private String typeCode;
    private String code;
    private String name;
    private String language;
}
