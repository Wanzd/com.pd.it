package com.pd.springboot.task;

import com.pd.it.common.annotations.Log;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.itf.ITask;
import com.pd.movie.business.MovieBusiness;

import javax.inject.Inject;
import javax.inject.Named;

import static com.pd.it.common.util.StaticTool.success;

@Named
public class EipMovieTask implements ITask {
    @Inject
    private MovieBusiness business;

    @Log
    @Override
    public ResultVO process() {
        business.process();
        return success("MovieEipTask success");
    }
}
