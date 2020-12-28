package com.pd.springboot.task;

import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.pd.common.util.TaskTool;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.model.Task.ITaskService;
import com.pd.model.Task.TaskVO;

@EnableScheduling
public class TaskTableScanTask {

    @Inject
    private ITaskService service;

    @Scheduled(cron = "0 0/3 * * * ?")
    public void process() throws BusinessException {
        /* 扫描任务表：取状态为未完成或状态为可尝试的创建时间最早的一批任务 */
        List<TaskVO> taskList = service.queryList(null);

        /* 按任务类型调度任务执行 */
        for (TaskVO eachTask : taskList) {
            ResultVO<T> rsVO = TaskTool.execute(eachTask);
        }
    }

}
