package com.pd.businessobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupBO {
    private String type;
    private String parentCode;
    private String code;
    private String name;
    private Float sortId;
}
