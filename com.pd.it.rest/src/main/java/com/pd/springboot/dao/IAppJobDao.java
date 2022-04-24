package com.pd.springboot.dao;

import com.pd.it.common.itf.IBaseDao;
import com.pd.model.job.vo.JobFO;
import com.pd.model.job.vo.JobVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAppJobDao extends IBaseDao<JobFO, JobVO> {}