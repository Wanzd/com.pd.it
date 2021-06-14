package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.ZERO_STR;
import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.ne;
import static com.pd.it.common.util.StaticTool.notEmpty;
import static com.pd.standard.itf.TaskConst.STATUS_INIT;
import static com.pd.standard.itf.TaskConst.STATUS_SUCCESS;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.pd.businessobject.LookupVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.business.LookupBusiness;
import com.pd.springboot.service.TaskService;
import com.pd.springboot.service.WeatherService;
import com.pd.standard.itf.TaskConst;

/**
 * 每三分钟一次，扫描任务表，如果没有今天的新冠数据扫描任务，则新建一个任务
 * 
 * @author thinkpad
 *
 */
@Component
public class WeatherDailyTask {
    private final static String TYPE = TaskConst.WEATHER_DAILY;

    @Inject
    private TaskService taskService;
    @Inject
    private WeatherService weatherService;

    @Inject
    private LookupBusiness lookupBusiness;

    private Calculator cal = new Calculator();

    // @Scheduled(cron = "0/5 * * * * ?")
    public void create() throws BusinessException {
        TaskVO todayTask = cal.getTodayTask();
        if (isEmpty(todayTask)) {
            cal.createTodayTask();
        }
    }

    // @Scheduled(cron = "0/5 * * * * ?")
    public void execute() throws BusinessException {
        TaskVO todayTask = cal.getTodayTask();
        if (notEmpty(todayTask)) {
            // cal.executeTodayTask(todayTask);
        }
    }

    private class Calculator {

        private TaskVO getTodayTask() throws BusinessException {
            TaskFO taskFO = new TaskFO();
            taskFO.setType(TYPE);
            taskFO.setTaskKey(formatDate(new Date(), "yyyyMMdd"));

            return taskService.queryInfo(taskFO);
        }

        private void createTodayTask() throws BusinessException {
            TaskVO taskVO = new TaskVO();
            taskVO.setType(TYPE);
            taskVO.setTaskKey(formatDate(new Date(), "yyyyMMdd"));
            taskVO.setStatus(STATUS_INIT);
            taskService.insertInfo(taskVO);
        }

        private void executeTodayTask(TaskVO taskVO) throws BusinessException {
            if (ne(taskVO.getStatus(), ZERO_STR)) {
                return;
            }
            List<LookupVO> cityList = lookupBusiness.getCityList();
            for (LookupVO cityVO : cityList) {
                List<WeatherVO> weatherVO = weatherService.getListByCityName(cityVO.getName());
                if (isEmpty(weatherVO)) {
                    continue;
                }
                weatherService.insertList(weatherVO);
            }
            taskVO.setStatus(STATUS_SUCCESS);
            taskService.updateInfo(taskVO);
        }

    }

}
