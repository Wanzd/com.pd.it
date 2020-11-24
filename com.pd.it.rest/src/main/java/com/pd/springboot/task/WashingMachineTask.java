package com.pd.springboot.task;

import static com.pd.common.util.StaticTool.objToJsonStr;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.base.exception.BusinessException;
import com.pd.common.calobject.TimerCO;
import com.pd.springboot.business.WashingMachineBusiness;
import com.pd.standard.itf.ITask;

@Named
public class WashingMachineTask implements ITask {
    public static final String TITLE = "washingMachineTask";
    @Autowired
    private WashingMachineBusiness business;

    @Override
    public Object process() {
        TimerCO timer = new TimerCO(TITLE);
        try {
            business.init();
            business.process();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        timer.end();
        String preStr = objToJsonStr(timer);
        return preStr;
    }
}
