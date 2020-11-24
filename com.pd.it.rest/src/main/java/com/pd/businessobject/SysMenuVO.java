package com.pd.businessobject;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@TableName("sys_menu_t")
public class SysMenuVO extends SysMenuBO {
}
