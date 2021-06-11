package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.it.common.itf.BaseService;
import com.pd.model.datasource.vo.DataSourceFO;
import com.pd.model.datasource.vo.DataSourceVO;
import com.pd.springboot.dao.IDataSourceDao;

@Named
public class DataSourceService extends BaseService<DataSourceFO, DataSourceVO, IDataSourceDao> {
}
