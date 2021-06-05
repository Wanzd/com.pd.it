package com.pd.model;

import com.pd.it.common.itf.IInsertInfoOperation;
import com.pd.it.common.itf.IQueryInfoOperation;
import com.pd.it.common.itf.IQueryListOperation;
import com.pd.model.task.vo.TaskFO;
import com.pd.model.task.vo.TaskVO;

public class Task {

    public static interface ITaskService extends IQueryListOperation<TaskFO, TaskVO>,
            IQueryInfoOperation<TaskFO, TaskVO>, IInsertInfoOperation<TaskVO> {}

}