package com.pd.model.datasource.vo;

import com.pd.businessobject.BaseResourceBO;

import lombok.Data;

@Data
public class CopyTableVO extends BaseResourceBO {
    private String fromDataSourceId;
    private String fromTable;

    private String toDataSourceId;
    private String toTable;
}