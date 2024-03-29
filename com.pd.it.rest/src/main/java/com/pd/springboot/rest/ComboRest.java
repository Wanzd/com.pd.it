package com.pd.springboot.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.common.util.StringFactory;
import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.dao.ILookupDao;
import com.pd.springboot.dao.IPersonDao;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("/comboRest")
public class ComboRest extends BaseRest<LookupFO, LookupVO, ILookupDao> {
	@Autowired
	private ILookupDao lookupDao;
	@Autowired
	private IPersonDao personDao;

	@RequestMapping(value = "/LOOKUP:{lookupType}", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ComboVO> queryLookup(@PathParam("") LookupFO fo) throws BusinessException {
		MapVO mapFO = new MapVO(StringFactory.from(fo));
		return lookupDao.queryCombo(mapFO);
	}

	@RequestMapping(value = "/USER:{userName}", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ComboVO> queryUser(@PathParam("") LookupFO fo) throws BusinessException {
		MapVO mapFO = new MapVO(StringFactory.from(fo));
		return personDao.queryCombo(mapFO);
	}
}
