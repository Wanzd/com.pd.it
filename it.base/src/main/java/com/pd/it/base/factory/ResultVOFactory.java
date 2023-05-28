package com.pd.it.base.factory;

import com.pd.it.base.vo.ResultVO;

public class ResultVOFactory {
	public static ResultVO error(String msg) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode("500");
		resultVO.setMsg(msg);
		return resultVO;
	}
}
