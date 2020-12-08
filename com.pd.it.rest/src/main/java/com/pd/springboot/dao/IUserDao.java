package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.UserFO;
import com.pd.businessobject.UserVO;
import com.pd.it.common.itf.IQueryComboOperation;
import com.pd.it.common.itf.IQueryInfoOperation;
import com.pd.it.common.itf.IQueryPagedListOperation;

@Mapper
public interface IUserDao extends BaseMapper<UserVO>, IQueryInfoOperation<UserFO, UserVO>,
        IQueryPagedListOperation<UserFO, UserVO>, IQueryComboOperation {

}