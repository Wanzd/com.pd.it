package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.ProposalVO;
import com.pd.standard.itf.IQueryComboOperation;

@Mapper
public interface IProposalDao extends BaseMapper<ProposalVO>, IQueryComboOperation {
}