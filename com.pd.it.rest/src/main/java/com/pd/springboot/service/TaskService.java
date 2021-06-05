package com.pd.springboot.service;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.model.Task.ITaskService;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;
import com.pd.springboot.dao.ITaskDao;
import com.pd.standard.web.StandardService;

@Named
public class TaskService extends StandardService<TaskFO, TaskVO, ITaskDao> implements ITaskService {

    @Autowired
    private ITaskDao dao;
}
