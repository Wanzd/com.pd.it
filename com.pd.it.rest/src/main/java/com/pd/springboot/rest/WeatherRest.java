package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.model.weather.vo.WeatherFO;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.dao.IWeatherDao;
import com.pd.standard.itf.RestConst;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping(RestConst.WEATHER)
public class WeatherRest extends BaseRest<WeatherFO, WeatherVO, IWeatherDao> {

}
