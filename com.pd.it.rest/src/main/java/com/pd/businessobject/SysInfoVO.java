package com.pd.businessobject;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("sys_info_t")
@NoArgsConstructor
public class SysInfoVO extends SysInfoBO {
    private String source;

    public SysInfoVO(String source, String key, String value) {
        this.source = source;
        setKey(key);
        setValue(value);
    }
}
