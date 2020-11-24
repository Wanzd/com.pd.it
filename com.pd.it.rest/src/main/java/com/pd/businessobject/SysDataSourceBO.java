package com.pd.businessobject;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pd.standard.itf.IIdentity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@TableName("sys_datasource_t")
public class SysDataSourceBO implements Serializable, IIdentity<String> {
    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private String type;
    private String detail;
}
