package com.pd.springboot.rest;

import static com.pd.it.base.util.StaticTool.attr;
import static com.pd.it.base.util.StaticTool.toStr;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.base.itf.ICrack;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.util.SpringUtil;

@RestController
@RequestMapping("gameRest")
public class GameRest {

	@RequestMapping("/crack")
	public String crack(@RequestBody Object obj) throws BusinessException {
		ICrack bean = SpringUtil.getBean(attr(obj, "game", String.class), ICrack.class);
		return toStr(bean.crack(attr(obj, "param", String.class)));

	}

}
