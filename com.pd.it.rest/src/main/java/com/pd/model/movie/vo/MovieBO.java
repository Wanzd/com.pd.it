package com.pd.model.movie.vo;

import com.pd.businessobject.BaseResourceBO;
import lombok.Data;

@Data
public class MovieBO extends BaseResourceBO {
    private String type;
	private String source;
	private String url;
	private String name;
	private String detail;
}