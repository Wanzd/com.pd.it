package com.pd.springboot.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.model.weather.vo.WeatherFO;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.adapter.WeatherAdapter;
import com.pd.springboot.dao.IWeatherDao;

@Named
public class WeatherService extends BaseService<WeatherFO, WeatherVO, IWeatherDao> {
    @Inject
    private WeatherAdapter weatherAdapter;

    public List<WeatherVO> getListByCityName(String cityName) throws BusinessException {
        return weatherAdapter.getListByCityName(cityName);
    }
}
