package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

public interface IDeleteOperation<FO> {

    int deleteInfo(@Param("fo") FO fo);

    int deleteById(@Param("id") String id);

}
