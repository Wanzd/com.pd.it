package com.pd.springboot.wiki.service;

import com.pd.it.common.itf.PlusService;
import com.pd.springboot.wiki.dao.IWikiPlusDao;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;

import javax.inject.Named;

@Named
public class WikiPlusService extends PlusService<WikiFO, WikiVO, IWikiPlusDao> {
}
