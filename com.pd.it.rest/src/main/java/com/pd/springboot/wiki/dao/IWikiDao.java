package com.pd.springboot.wiki.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.itf.IBaseDao;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IWikiDao extends IBaseDao<WikiFO, WikiVO> {
    WikiVO queryWikiInfo(@Param("fo")WikiFO fo);
}