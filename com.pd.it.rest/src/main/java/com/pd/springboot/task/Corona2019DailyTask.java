package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.notEmpty;
import static com.pd.standard.itf.TaskConst.STATUS_INIT;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pd.it.common.exception.BusinessException;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;
import com.pd.springboot.service.TaskService;

/**
 * 每三分钟一次，扫描任务表，如果没有今天的新冠数据扫描任务，则新建一个任务
 * 
 * @author thinkpad
 *
 */
@Component
public class Corona2019DailyTask {
    private final static String TYPE = "corona2019Daily";

    @Inject
    private TaskService taskService;

    private Calculator cal = new Calculator();

    @Scheduled(cron = "0 0/3 * * * ?")
    public void create() throws BusinessException {
        if (!cal.haveTodayTask()) {
            cal.createTodayTask();
        }
    }

    private class Calculator {

        private boolean haveTodayTask() throws BusinessException {
            TaskFO taskFO = new TaskFO();
            taskFO.setType(TYPE);
            taskFO.setTaskKey(formatDate(new Date(), "yyyyMMdd"));

            TaskVO taskVO = taskService.queryInfo(taskFO);
            return notEmpty(taskVO);
        }

        private void createTodayTask() throws BusinessException {
            TaskVO taskVO = new TaskVO();
            taskVO.setType(TYPE);
            taskVO.setTaskKey(formatDate(new Date(), "yyyyMMdd"));
            taskVO.setStatus(STATUS_INIT);
            taskService.insertInfo(taskVO);
        }

    }

}
