package com.pd.springboot.rest;

import static com.pd.it.common.util.StaticTool.str;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.SysMenuBO;
import com.pd.businessobject.SysMenuFO;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.service.MenuService;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("menuRest")
public class MenuRest extends BaseRest<SysMenuFO, SysMenuBO, MenuService> {

	@RequestMapping("/root")
	public String root() throws BusinessException {
		return str(bridge.queryList(new SysMenuFO()));
	}

}
