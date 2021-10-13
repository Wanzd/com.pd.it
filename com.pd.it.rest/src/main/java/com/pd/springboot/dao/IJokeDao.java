package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.itf.IBaseDao;
import com.pd.model.joke.vo.JokeFO;
import com.pd.model.joke.vo.JokeVO;

@Mapper
public interface IJokeDao extends IBaseDao<JokeFO, JokeVO> {}