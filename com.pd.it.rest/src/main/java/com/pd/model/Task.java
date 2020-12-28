package com.pd.model;

import com.pd.businessobject.BaseResourceBO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryListOperation;
import com.pd.standard.web.StandardService;

import lombok.Getter;
import lombok.Setter;

public class Task {
    @Setter
    @Getter
    public static class TaskBO extends BaseResourceBO {
        private String type;/* 任务类型 */
        private String subType;/* 任务子类型 */
        private String key;/* 任务主键 */
        private String eqParam;/* 入参 */
        private String resParam;/* 出参 */
        private int failCnt;/* 已经失败次数 */
        private int chanceCnt;/* 尝试机会 */
    }

    @Setter
    @Getter
    public static class TaskVO extends TaskBO {}

    @Setter
    @Getter
    public static class TaskFO extends TaskVO {}

    public static interface ITaskDao extends IBaseDao<TaskFO, TaskVO> {}

    public static interface ITaskService extends IQueryListOperation<TaskFO, TaskVO> {}

    public static class TaskService extends StandardService<TaskFO, TaskVO, ITaskDao> implements ITaskService {}
}