package com.pd.model.datasource.vo;

import com.pd.businessobject.BaseResourceBO;

import lombok.Data;

@Data
public class DataSourceBO extends BaseResourceBO {
    private String driver;
    private String url;
    private String username;
    private String password;
    private String database;
}