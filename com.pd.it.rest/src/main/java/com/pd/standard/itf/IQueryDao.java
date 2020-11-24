package com.pd.standard.itf;

public interface IQueryDao<FO, DTO>
		extends IQueryInfoOperation<FO, DTO>, IQueryListOperation<FO, DTO>, IQueryPagedListOperation<FO, DTO> {
}
