package com.pd.it.common.itf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;

import lombok.Getter;

public class BaseService<FO, VO, Dao extends IBaseDao<FO, VO>> {

	@Autowired
	@Getter
	protected Dao dao;

	public VO queryInfo(FO in) throws BusinessException {
		return dao.queryInfo(in);
	}

	public VO queryInfoById(String id) throws BusinessException {
		return dao.queryInfoById(id);
	}

	public VO queryDetailInfo(FO fo) throws BusinessException {
		return dao.queryDetailInfo(fo);
	}

	public String queryJson(FO fo) throws BusinessException {
		return dao.queryJson(fo);
	}

	public List<VO> queryList(FO fo) throws BusinessException {
		return dao.queryList(fo);
	}

	public List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException {
		return dao.queryPagedList(fo, page);
	}

	public int queryCount(FO fo) throws BusinessException {
		return dao.queryCount(fo);
	}

	public int insertInfo(VO vo) throws BusinessException {
		return dao.insertInfo(vo);
	}

	public int insertList(List<VO> list) {
		return dao.insertList(list);
	}

	public int updateList(List<VO> list) {
		return dao.updateList(list);
	}

	public int deleteList(List<VO> list) {
		return dao.deleteList(list);
	}

	public int deleteInfo(FO fo) {
		return dao.deleteInfo(fo);
	}

	public int deleteById(String id) {
		return dao.deleteById(id);
	}

	public int updateInfo(VO vo) throws BusinessException {
		return dao.updateInfo(vo);
	}

}
