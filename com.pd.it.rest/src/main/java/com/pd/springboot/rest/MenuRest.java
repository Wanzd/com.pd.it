package com.pd.springboot.rest;

import static com.pd.common.util.StaticTool.str;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.SysMenuBO;
import com.pd.businessobject.SysMenuFO;
import com.pd.springboot.service.MenuService;
import com.pd.standard.web.IStandardRest;

@RestController
@RequestMapping("menuRest")
public class MenuRest implements IStandardRest<SysMenuFO, SysMenuBO> {
	@Autowired
	private MenuService service;

	@RequestMapping("/root")
	public String root() throws BusinessException {
		return str(queryList(new SysMenuFO()));
	}

}
