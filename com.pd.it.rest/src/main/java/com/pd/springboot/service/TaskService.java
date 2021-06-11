package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.it.common.itf.BaseService;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;
import com.pd.springboot.dao.ITaskDao;

@Named
public class TaskService extends BaseService<TaskFO, TaskVO, ITaskDao>  {
}
