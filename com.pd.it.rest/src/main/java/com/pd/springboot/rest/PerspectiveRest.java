package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.MapVO;
import com.pd.springboot.dao.ISysPerspectiveDao;
import com.pd.standard.web.IQueryComboRest;
import com.pd.standard.web.IStandardRest;

/**
 * AI系统
 * 
 * @author thinkpad
 *
 */
@RestController
@RequestMapping("perspectiveRest")
public class PerspectiveRest implements IStandardRest<MapVO, MapVO>, IQueryComboRest<MapVO, MapVO> {
	@Autowired
	private ISysPerspectiveDao dao;
}
