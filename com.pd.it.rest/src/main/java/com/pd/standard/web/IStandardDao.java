package com.pd.standard.web;

import com.pd.standard.itf.IQueryDao;
import com.pd.standard.itf.IUpdateDao;

public interface IStandardDao<FO, VO> extends IQueryDao<FO, VO>, IUpdateDao<FO, VO> {
}
