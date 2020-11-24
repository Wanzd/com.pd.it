package com.pd.standard.itf;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.MapVO;

public interface IBuilder<IN, OUT> {
	OUT build(IN in) throws BusinessException;

	default void init(MapVO rs) {
	};
}
