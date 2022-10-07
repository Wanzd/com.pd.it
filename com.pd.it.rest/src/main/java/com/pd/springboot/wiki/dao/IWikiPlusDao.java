package com.pd.springboot.wiki.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.itf.IBaseDao;
import com.pd.wiki.businessobject.WikiBO;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IWikiPlusDao extends BaseMapper<WikiVO> {
}