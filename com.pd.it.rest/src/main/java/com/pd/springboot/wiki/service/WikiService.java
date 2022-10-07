package com.pd.springboot.wiki.service;

import javax.inject.Named;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.wiki.dao.IWikiDao;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;

import java.util.List;

@Named
public class WikiService extends BaseService<WikiFO, WikiVO, IWikiDao> {
}
