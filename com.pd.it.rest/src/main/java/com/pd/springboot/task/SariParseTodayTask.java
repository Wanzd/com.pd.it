package com.pd.springboot.task;

import static com.pd.common.util.StaticTool.objToJsonStr;

import java.util.Date;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.MapVO;
import com.pd.common.calobject.TimerCO;
import com.pd.springboot.business.SariBusiness;
import com.pd.standard.itf.ITask;

@Named
public class SariParseTodayTask implements ITask {
    public static final String TITLE = "sariParseTodayTask";
    @Autowired
    private SariBusiness business;

    @Override
    public Object process() {
        TimerCO timer = new TimerCO(TITLE);
        MapVO fo = new MapVO();
        fo.put("creationDate", new Date());
        try {
            business.init(fo);
            business.process(fo);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        timer.end();
        String preStr = objToJsonStr(timer);
        return preStr;
    }
}
