package com.pd.standard.itf;

public interface IUpdateDao<FO, DTO> extends IInsertListOperation<DTO>, IUpdateListOperation<DTO>,
		IDeleteListOperation<DTO>, IDeleteOperation<FO>, IUpdateInfoOperation<DTO> {
}
