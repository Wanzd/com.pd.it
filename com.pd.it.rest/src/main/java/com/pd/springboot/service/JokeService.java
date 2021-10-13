package com.pd.springboot.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.model.joke.vo.JokeFO;
import com.pd.model.joke.vo.JokeVO;
import com.pd.model.weather.vo.WeatherFO;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.adapter.QiushibaikeAdapter;
import com.pd.springboot.dao.IJokeDao;
import com.pd.springboot.dao.IWeatherDao;

@Named
public class JokeService extends BaseService<JokeFO, JokeVO, IJokeDao> {
    @Inject
    private QiushibaikeAdapter qiushibaikeAdapter;

    public List<JokeVO> getRandomList() throws BusinessException {
        return qiushibaikeAdapter.getRandomList();
    }
}
