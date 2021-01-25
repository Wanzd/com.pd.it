package com.pd.it.common.itf;

public interface IBaseDao<FO, VO> extends IQueryInfoOperation<FO, VO>, IQueryListOperation<FO, VO>,
        IQueryPagedListOperation<FO, VO>, IInsertInfoOperation<VO>, IInsertListOperation<VO>, IUpdateListOperation<VO>,
        IDeleteListOperation<VO>, IDeleteOperation<FO>, IUpdateInfoOperation<VO> {}
