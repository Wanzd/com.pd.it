package com.pd.model.joke.vo;

import com.pd.businessobject.BaseResourceBO;

import lombok.Data;

@Data
public class JokeBO extends BaseResourceBO {
    private String source;
    private String id;
    private String url;
    private String text;
    private Integer colorValue;
    private Integer discussValue;
}