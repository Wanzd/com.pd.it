package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.ProposalFO;
import com.pd.businessobject.ProposalVO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboOperation;

@Mapper
public interface IProposalDao extends IBaseDao<ProposalFO, ProposalVO>, IQueryComboOperation {}