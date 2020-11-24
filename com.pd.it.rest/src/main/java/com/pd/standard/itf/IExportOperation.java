package com.pd.standard.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.base.exception.BusinessException;

public interface IExportOperation<FO> {

    void export(@Param("fo") FO in) throws BusinessException;

    default <T extends IExportConfigEnum> Class<T> getExportConfig() {
        return null;
    }
}
