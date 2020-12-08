package com.pd.it.common.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.MapVO;

public interface IQueryComboOperation {
    List<ComboVO> queryCombo(@Param("fo") MapVO fo);
}
