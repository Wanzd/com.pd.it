package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.exception.BusinessException;

public interface IExportOperation<FO> {

    void export(@Param("fo") FO in) throws BusinessException;

    default <T extends IExportConfigEnum> Class<T> getExportConfig() {
        return null;
    }
}
