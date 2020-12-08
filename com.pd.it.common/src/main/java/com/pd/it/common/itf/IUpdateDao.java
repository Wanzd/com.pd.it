package com.pd.it.common.itf;

public interface IUpdateDao<FO, DTO> extends IInsertListOperation<DTO>, IUpdateListOperation<DTO>,
		IDeleteListOperation<DTO>, IDeleteOperation<FO>, IUpdateInfoOperation<DTO> {
}
