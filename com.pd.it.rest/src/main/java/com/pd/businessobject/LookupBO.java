package com.pd.businessobject;

import lombok.Data;

@Data
public class LookupBO {
    private String type;
    private String parentCode;
    private String code;
    private String name;
    private Float sortId;
}
