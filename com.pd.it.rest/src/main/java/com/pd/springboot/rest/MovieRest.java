package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.model.movie.vo.MovieFO;
import com.pd.model.movie.vo.MovieVO;
import com.pd.springboot.dao.IMovieDao;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("movieRest")
public class MovieRest extends BaseRest<MovieFO, MovieVO, IMovieDao> {

}
