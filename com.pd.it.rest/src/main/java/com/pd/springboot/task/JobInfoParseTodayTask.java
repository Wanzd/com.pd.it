package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.error;
import static com.pd.it.common.util.StaticTool.str;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.common.calobject.TimerCO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.ITask;
import com.pd.springboot.business.JobBusiness;
import com.pd.standard.itf.TaskEnum;

@Named
public class JobInfoParseTodayTask implements ITask {
    @Inject
    private JobBusiness business;

    @Override
    public Object process() {
        try {
            TimerCO timer = new TimerCO(TaskEnum.JOB_INFO_PARSE_TODAY_TASK.getName());
            MapVO fo = new MapVO();
            fo.put("creationDate", new Date());
            business.init(fo);
            business.process(fo);
            timer.end();
            return str(timer);
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage(), null);
        }
    }
}
