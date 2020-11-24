package com.pd.businessobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pd.standard.itf.IIdentity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SysMenuBO implements IIdentity<String> {
    private String pid;
    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private String language;
    private Double sortId;
    private String url;

    @TableField(exist = false)
    private String fullPath;
}
