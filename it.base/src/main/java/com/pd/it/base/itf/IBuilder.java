package com.pd.it.base.itf;

import com.pd.it.base.exception.BusinessException;
import com.pd.it.base.vo.MapVO;

public interface IBuilder<IN, OUT> {
	OUT build(IN in) throws BusinessException;

	default void init(MapVO rs) {
	};
}
