package com.pd.springboot.rest;

import java.util.List;

import com.pd.springboot.wiki.service.WikiPlusService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.util.DbTool;
import com.pd.springboot.wiki.service.WikiService;
import com.pd.standard.web.BaseRest;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;

@RestController
@RequestMapping("wikiRest")
public class WikiRest extends BaseRest<WikiFO, WikiVO, WikiPlusService> {
}
