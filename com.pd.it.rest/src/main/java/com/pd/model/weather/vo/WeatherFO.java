package com.pd.model.weather.vo;

import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.isEmpty;

import java.util.Date;

import lombok.Data;

@Data
public class WeatherFO extends WeatherVO {
	public void initDigDate() {
		if (isEmpty(getDigDate())) {
			setDigDate(formatDate(new Date(), "yyyyMMdd"));
		}
	}
}