package com.pd.springboot.rest;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.LookupFO;
import com.pd.common.util.StringFactory;
import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.exception.BusinessException;
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
public class PerspectiveRest extends BaseRest<MapVO, MapVO, ISysPerspectiveDao> {

    @Inject
    private ISysPerspectiveDao dao;

    @RequestMapping(value = "/queryCombo", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<ComboVO> queryCombo(@PathParam("") LookupFO fo) throws BusinessException {
        MapVO mapFO = new MapVO(StringFactory.from(fo));
        return dao.queryCombo(mapFO);
    }
}
