package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.ZERO_STR;
import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.ne;
import static com.pd.it.common.util.StaticTool.notEmpty;
import static com.pd.it.common.util.StaticTool.str;
import static com.pd.standard.itf.TaskConst.STATUS_INIT;
import static com.pd.standard.itf.TaskConst.STATUS_SUCCESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.common.calobject.TimerCO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.ITask;
import com.pd.model.joke.vo.JokeVO;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;
import com.pd.springboot.business.LookupBusiness;
import com.pd.springboot.service.JokeService;
import com.pd.springboot.service.TaskService;
import com.pd.standard.itf.TaskConst;
import com.pd.standard.itf.TaskEnum;

/**
 * 每三分钟一次，扫描任务表，如果没有今天的新冠数据扫描任务，则新建一个任务
 * 
 * @author thinkpad
 *
 */
@Named
public class JokeDailyTask implements ITask {
    private final static String TYPE = TaskConst.JOKE_DAILY;

    @Inject
    private TaskService taskService;
    @Inject
    private JokeService jokeService;

    @Inject
    private LookupBusiness lookupBusiness;

    private Calculator cal = new Calculator();

    @Override
    public Object process() throws BusinessException {
        TimerCO timer = new TimerCO(TaskEnum.JOKE_INFO_PARSE_TODAY_TASK.getName());
        TaskVO todayTask = cal.getTodayTask();
        if (isEmpty(todayTask)) {
            cal.createTodayTask();
        }
        todayTask = cal.getTodayTask();
        if (notEmpty(todayTask)) {
            cal.executeTodayTask(todayTask);
        }
        timer.end();
        return str(timer);
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
            List<JokeVO> tmpList = new ArrayList<>();
            List<JokeVO> jokeList = jokeService.getRandomList();
            if (isEmpty(jokeList)) {
                return;
            }
            tmpList.addAll(jokeList);
            if (tmpList.size() >= 500) {
                jokeService.insertList(tmpList);
                tmpList.clear();
            }
            if (tmpList.size() > 0) {
                jokeService.insertList(tmpList);
            }
            taskVO.setStatus(STATUS_SUCCESS);
            taskService.updateInfo(taskVO);
        }

    }

}
