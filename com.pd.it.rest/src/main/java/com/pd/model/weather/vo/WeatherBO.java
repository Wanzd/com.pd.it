package com.pd.model.weather.vo;

import com.pd.businessobject.BaseResourceBO;

import lombok.Data;

@Data
public class WeatherBO extends BaseResourceBO {
	private String province;
	private String city;
	private String weatherDate;
	private String digDate;
	private String type;
	private Integer high;
	private Integer low;
}