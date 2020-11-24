package com.pd.standard.itf;

import org.apache.ibatis.annotations.Param;

public interface IDeleteOperation<FO> {

    int delete(@Param("fo") FO fo);

    int deleteById(@Param("fo") FO fo);

}
