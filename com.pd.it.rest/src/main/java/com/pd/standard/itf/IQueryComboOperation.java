package com.pd.standard.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pd.businessobject.MapVO;
import com.pd.it.base.vo.ComboVO;

public interface IQueryComboOperation {
	List<ComboVO> queryCombo(@Param("fo") MapVO fo);
}
