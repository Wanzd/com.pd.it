package com.pd.wiki.module;

import com.pd.it.common.annotations.Module;
import com.pd.springboot.wiki.dao.IWikiDao;
import com.pd.springboot.wiki.service.WikiService;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;

@Module(daoClass = IWikiDao.class, foClass = WikiFO.class, name = "wiki", serviceClass = WikiService.class, voClass = WikiVO.class)
public class WikiModule {
}
