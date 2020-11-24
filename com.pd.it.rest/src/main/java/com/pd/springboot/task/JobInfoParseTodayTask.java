package com.pd.springboot.task;

import static com.pd.common.util.StaticTool.objToJsonStr;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.businessobject.MapVO;
import com.pd.common.calobject.TimerCO;
import com.pd.springboot.business.JobBusiness;
import com.pd.standard.itf.ITask;
import com.pd.standard.itf.TaskEnum;

@Named
public class JobInfoParseTodayTask implements ITask {
    @Inject
    private JobBusiness business;

    @Override
    public Object process() {
        TimerCO timer = new TimerCO(TaskEnum.JOB_INFO_PARSE_TODAY_TASK.getName());
        MapVO fo = new MapVO();
        fo.put("creationDate", new Date());
        business.init(fo);
        business.process(fo);
        timer.end();
        return objToJsonStr(timer);
    }
}
