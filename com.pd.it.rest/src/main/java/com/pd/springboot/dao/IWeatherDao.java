package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.itf.IBaseDao;
import com.pd.model.weather.vo.WeatherFO;
import com.pd.model.weather.vo.WeatherVO;

@Mapper
public interface IWeatherDao extends IBaseDao<WeatherFO, WeatherVO> {}