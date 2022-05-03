package com.pd.movie.business;

import com.pd.movie.service.Movie66ysService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MovieBusiness {
    @Inject
    private Movie66ysService service;

    public void process() {
        service.process();
    }

}
