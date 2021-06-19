package com.pd.it.common.itf;

import com.pd.it.common.exception.BusinessException;

public interface ITask {
	Object process() throws BusinessException;
}
