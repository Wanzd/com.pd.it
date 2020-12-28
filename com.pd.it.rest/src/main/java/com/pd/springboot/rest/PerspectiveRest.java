package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.businessobject.MapVO;
import com.pd.springboot.dao.ISysPerspectiveDao;
import com.pd.standard.web.BaseRest;

/**
 * AI系统
 * 
 * @author thinkpad
 *
 */
@RestController
@RequestMapping("perspectiveRest")
public class PerspectiveRest extends BaseRest<MapVO, MapVO, ISysPerspectiveDao> {}
