package com.pd.springboot.business;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.util.DbTool;
import com.pd.model.datasource.vo.CopyTableVO;
import com.pd.model.datasource.vo.DataSourceVO;
import com.pd.springboot.service.DataSourceService;

@Named
public class DataSourceCopyTableBusiness {

    @Inject
    DataSourceService dataSourceService;

    public ResultVO<String> copyTable(CopyTableVO vo) throws BusinessException {
        DataSourceVO fromDataSource = dataSourceService.queryInfoById(vo.getFromDataSourceId());
        DataSourceVO toDataSource = dataSourceService.queryInfoById(vo.getToDataSourceId());
        String createTableSql = DbTool.getCreateTableSql(fromDataSource, vo.getFromTable());
        DbTool.execute(toDataSource, createTableSql);
        return ResultVO.success(null);
    }

}
