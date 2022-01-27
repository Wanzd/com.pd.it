package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.ZERO_STR;
import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.ne;
import static com.pd.it.common.util.StaticTool.notEmpty;
import static com.pd.standard.itf.TaskConst.STATUS_INIT;
import static com.pd.standard.itf.TaskConst.STATUS_SUCCESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.businessobject.LookupVO;
import com.pd.it.common.annotations.Log;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.ITask;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;
import com.pd.model.weather.vo.WeatherVO;
import com.pd.springboot.business.LookupBusiness;
import com.pd.springboot.service.TaskService;
import com.pd.springboot.service.WeatherService;
import com.pd.standard.itf.TaskConst;

import lombok.AllArgsConstructor;

/**
 * 每三分钟一次，扫描任务表，如果没有今天的新冠数据扫描任务，则新建一个任务
 * 
 * @author thinkpad
 *
 */
@Named
public class WeatherDailyTask implements ITask {
	private final static String TYPE = TaskConst.WEATHER_DAILY;

	@Inject
	private TaskService taskService;
	@Inject
	private WeatherService weatherService;

	@Inject
	private LookupBusiness lookupBusiness;

	private Calculator cal = new Calculator();

	@Log
	@Override
	public Object process() throws BusinessException {
		TaskVO todayTask = cal.getTodayTask();
		if (isEmpty(todayTask)) {
			cal.createTodayTask();
		}
		todayTask = cal.getTodayTask();
		if (notEmpty(todayTask)) {
			cal.executeTodayTask(todayTask);
		}
		return "success";
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
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			List<LookupVO> cityList = lookupBusiness.getCityList();
			List<WeatherVO> tmpList = new ArrayList<>();
			for (LookupVO cityVO : cityList) {
				executorService.submit(new WeatherCallable(cityVO.getName()));
			}
			if (tmpList.size() > 0) {
				weatherService.insertList(tmpList);
			}
			taskVO.setStatus(STATUS_SUCCESS);
			taskService.updateInfo(taskVO);
		}

	}

	@AllArgsConstructor
	class WeatherCallable implements Callable<Object> {
		private String cityName;

		@Override
		public Object call() throws Exception {
			try {
				List<WeatherVO> weatherList = weatherService.getListByCityName(cityName);
				if (isEmpty(weatherList)) {
					return null;
				}
				weatherService.insertList(weatherList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
}