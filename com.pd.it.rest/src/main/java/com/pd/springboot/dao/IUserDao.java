package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.UserFO;
import com.pd.businessobject.UserVO;
import com.pd.standard.itf.IQueryComboOperation;
import com.pd.standard.itf.IQueryInfoOperation;
import com.pd.standard.itf.IQueryPagedListOperation;

@Mapper
public interface IUserDao extends BaseMapper<UserVO>, IQueryInfoOperation<UserFO, UserVO>,
		IQueryPagedListOperation<UserFO, UserVO>, IQueryComboOperation {

}