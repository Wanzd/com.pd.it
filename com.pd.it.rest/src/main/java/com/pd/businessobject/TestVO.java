package com.pd.businessobject;

import lombok.Data;

@Data
public class TestVO {
    private final String table = "test_t_";
    private String id;
    private String name;

    public String getTable() {
        return table + Math.abs(id.hashCode()) % 10;
    }
}
