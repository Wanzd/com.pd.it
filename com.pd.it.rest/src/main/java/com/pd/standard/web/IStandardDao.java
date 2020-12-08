package com.pd.standard.web;

import com.pd.it.common.itf.IQueryDao;
import com.pd.it.common.itf.IUpdateDao;

public interface IStandardDao<FO, VO> extends IQueryDao<FO, VO>, IUpdateDao<FO, VO> {
}
