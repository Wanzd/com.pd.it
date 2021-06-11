package com.pd.springboot.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.model.datasource.vo.CopyTableVO;
import com.pd.springboot.business.DataSourceCopyTableBusiness;

@RestController
@RequestMapping("/dataSourceRest")
public class DataSourceRest {
    @Inject
    DataSourceCopyTableBusiness dataSourceCopyTableBusiness;

    @RequestMapping("/copyTable")
    public ResultVO<String> copyTable(@RequestBody(required = false) CopyTableVO vo) throws BusinessException {
        ResultVO<String> result = dataSourceCopyTableBusiness.copyTable(vo);
        return result;
    }

}
