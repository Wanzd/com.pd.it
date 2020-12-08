package com.pd.it.common.itf;

import com.pd.it.common.itf.IQueryInfoOperation;

public interface IQueryDao<FO, DTO>
		extends IQueryInfoOperation<FO, DTO>, IQueryListOperation<FO, DTO>, IQueryPagedListOperation<FO, DTO> {
}
