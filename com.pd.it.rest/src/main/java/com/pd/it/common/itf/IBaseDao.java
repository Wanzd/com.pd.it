package com.pd.it.common.itf;

import com.pd.it.common.itf.IDeleteListOperation;
import com.pd.it.common.itf.IDeleteOperation;
import com.pd.it.common.itf.IInsertInfoOperation;
import com.pd.it.common.itf.IInsertListOperation;
import com.pd.it.common.itf.IQueryInfoOperation;
import com.pd.it.common.itf.IQueryListOperation;
import com.pd.it.common.itf.IQueryPagedListOperation;
import com.pd.it.common.itf.IUpdateInfoOperation;
import com.pd.it.common.itf.IUpdateListOperation;

public interface IBaseDao<FO, VO> extends IQueryInfoOperation<FO, VO>, IQueryListOperation<FO, VO>,
        IQueryPagedListOperation<FO, VO>, IInsertInfoOperation<VO>, IInsertListOperation<VO>, IUpdateListOperation<VO>,
        IDeleteListOperation<VO>, IDeleteOperation<FO>, IUpdateInfoOperation<VO> {}
