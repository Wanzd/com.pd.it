package com.pd.model;

import com.pd.businessobject.BaseResourceBO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IInsertInfoOperation;
import com.pd.standard.web.StandardService;

import lombok.Getter;
import lombok.Setter;

public interface Log {
    @Setter
    @Getter
    public static class LogBO extends BaseResourceBO {
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
    public static class LogVO extends LogBO {}

    @Setter
    @Getter
    public static class LogFO extends LogVO {}

    public static interface ILogDao extends IBaseDao<LogFO, LogVO> {}

    public static interface ILogService extends IInsertInfoOperation<LogVO> {}

    public static class LogService extends StandardService<LogFO, LogVO, ILogDao> implements ILogService {}
}