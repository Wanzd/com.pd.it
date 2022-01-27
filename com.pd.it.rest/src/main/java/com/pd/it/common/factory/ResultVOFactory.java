package com.pd.it.common.factory;

import com.pd.it.common.businessobject.ResultVO;

public class ResultVOFactory {
	public static ResultVO error(String msg) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode("500");
		resultVO.setMsg(msg);
		return resultVO;
	}
}
