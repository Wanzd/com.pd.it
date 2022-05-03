package com.pd.springboot.dao;

import com.pd.it.common.itf.IBaseDao;
import com.pd.model.movie.vo.MovieFO;
import com.pd.model.movie.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMovieDao extends IBaseDao<MovieFO, MovieVO> {}