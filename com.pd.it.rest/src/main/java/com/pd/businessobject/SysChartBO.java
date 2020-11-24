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
@TableName("sys_chart_t")
public class SysChartBO implements Serializable, IIdentity<String> {
    @TableId(type = IdType.INPUT)
    private String id;
    private String jsonData;
}
