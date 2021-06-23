package com.pd.springboot.rest;

import static com.pd.it.common.util.StaticTool.queryList;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.exception.BusinessException;
import com.pd.model.weather.vo.WeatherFO;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.dao.IWeatherDao;
import com.pd.standard.itf.RestConst;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping(RestConst.WEATHER)
public class WeatherRest extends BaseRest<WeatherFO, WeatherVO, IWeatherDao> {
	@Override
	public List queryListRest(@RequestBody(required = false) WeatherFO fo) throws BusinessException {
		fo.initDigDate();
		return queryList(bridge, fo);
	}
}
