package com.pd.springboot.task;

import com.pd.it.common.annotations.Log;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.itf.ITask;
import com.pd.job.business.JobBusiness;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

import static com.pd.it.common.util.StaticTool.success;

@Named
public class JobInfoParseTodayTask implements ITask {
    @Inject
    private JobBusiness jobBusiness;

    @Log
    @Override
    public ResultVO process() {
        MapVO fo = new MapVO();
        fo.put("creationDate", new Date());
        jobBusiness.init(fo);
        jobBusiness.process(fo);
        return success("JobInfoParseTodayTask success");
    }
}
