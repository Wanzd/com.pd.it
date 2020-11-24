package com.pd.businessobject;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pd.standard.itf.IIdentity;

import lombok.Data;

@Data
@KeySequence(value = "fid_s")
public class LookupBO implements Serializable, IIdentity<String> {
    @TableId(type = IdType.INPUT)
    private String fid;
    private String lookupType;
    private String lookupCode;
    private String lookupText;

    @Override
    public String getId() {
        return fid;
    }

}
