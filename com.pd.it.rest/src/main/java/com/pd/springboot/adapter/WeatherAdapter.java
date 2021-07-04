package com.pd.springboot.adapter;

import static com.pd.it.common.util.StaticTool.addDate;
import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.toInt;
import static com.pd.it.common.util.StaticTool.toObj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.common.util.WebUtil;
import com.pd.it.common.exception.BusinessException;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.business.LookupBusiness;

@Named
public class WeatherAdapter {

	@Inject
	private LookupBusiness lookupBusiness;
	private Calculator cal = new Calculator();

	public List<WeatherVO> getListByCityName(String cityName) throws BusinessException {
		String url = formatStr("http://wthrcdn.etouch.cn/weather_mini?city=%s", cityName);
		String htmlStr = WebUtil.get(url, "UTF-8");
		return cal.parseWeatherList(htmlStr);
	}

	private class Calculator {
		private List<WeatherVO> parseWeatherList(String htmlStr) throws BusinessException {
			JSONObject parseObject = JSON.parseObject(htmlStr);
			JSONObject data = parseObject.getJSONObject("data");
			String city = data.getString("city").replaceAll("市", "");
			String province = lookupBusiness.getProvinceByCity(city);
			if (isEmpty(province)) {
				throw new BusinessException(formatStr("Not found province of [%s]", city));
			}
			String digDate = formatDate(new Date(), "yyyyMMdd");
			JSONArray forecast = data.getJSONArray("forecast");

			List<WeatherVO> rsList = new ArrayList<>();
			for (int index = 0, total = forecast.size(); index < total; index++) {
				JSONObject tmp = toObj(forecast.get(index), JSONObject.class);
				WeatherVO weatherVO = new WeatherVO();
				weatherVO.setCity(city);
				weatherVO.setProvince(province);
				weatherVO.setDigDate(digDate);
				weatherVO.setType(tmp.getString("type"));
				weatherVO.setWeatherDate(formatDate(addDate(new Date(), index), "yyyyMMdd"));
				weatherVO.setHigh(toInt(tmp.getString("high").replaceAll("高温 |℃", "")));
				weatherVO.setLow(toInt(tmp.getString("low").replaceAll("低温 |℃", "")));
				rsList.add(weatherVO);
			}
			return rsList;
		}
	}
}
