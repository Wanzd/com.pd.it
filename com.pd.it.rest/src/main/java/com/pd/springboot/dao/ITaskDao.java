package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.itf.IBaseDao;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;

@Mapper
public interface ITaskDao extends IBaseDao<TaskFO, TaskVO> {}