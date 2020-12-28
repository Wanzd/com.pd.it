package com.pd.it.common.itf;

import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.exception.BusinessException;

public interface IBuilder<IN, OUT> {
	OUT build(IN in) throws BusinessException;

	default void init(MapVO rs) {
	};
}
