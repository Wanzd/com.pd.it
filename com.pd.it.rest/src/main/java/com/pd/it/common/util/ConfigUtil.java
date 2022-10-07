package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.isNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboDao;
import com.pd.it.common.itf.PlusService;
import com.pd.model.datasource.vo.DataSourceVO;

public class ConfigUtil {

	public static <FO, VO> VO queryInfo(Object bean, FO fo) throws BusinessException {
		IDbAdapter<FO, VO> dbAdapter = getDbAdapter(bean);
		if (isNull(dbAdapter)) {
			return null;
		}
		return dbAdapter.queryInfo(fo);
	}

	public static <FO, VO> List<VO> queryList(Object bean, FO fo) throws BusinessException {
		IDbAdapter<FO, VO> dbAdapter = getDbAdapter(bean);
		if (isNull(dbAdapter)) {
			return null;
		}
		return dbAdapter.queryList(fo);
	}

	public static <FO, VO> List<VO> queryPagedList(Object bean, FO fo, PageVO page) throws BusinessException {
		IDbAdapter<FO, VO> dbAdapter = getDbAdapter(bean);
		if (isNull(dbAdapter)) {
			return null;
		}
		return dbAdapter.queryPagedList(fo, page);
	}

	public static void execute(DataSourceVO dsVO, String sql) {
		try {
			Class.forName(dsVO.getDriver());
			Connection conn = DriverManager.getConnection(dsVO.getUrl(), dsVO.getUsername(), dsVO.getPassword());
			Statement stmt = conn.createStatement();
			boolean rs = stmt.execute(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static <FO, VO> IDbAdapter<FO, VO> getDbAdapter(Object bean) {
		if (bean instanceof PlusService) {
			return new BaseServiceDbAdapter((BaseService) bean);
		}
		if (bean instanceof BaseService) {
			return new BaseServiceDbAdapter((BaseService) bean);
		}
		if (bean instanceof IBaseDao) {
			return new BaseDaoDbAdapter((IBaseDao) bean);
		}
		return null;
	}

	public static List<ComboVO> queryCombo(Object bean, MapVO fo) throws BusinessException {
		if (bean instanceof IQueryComboDao) {
			IQueryComboDao queryComboDao = (IQueryComboDao) bean;
			return queryComboDao.queryCombo(fo);
		}
		throw new BusinessException("Not impl IQueryComboDao");
	}

	public static <FO, VO> void insertList(IBaseDao<FO, VO> dao, List<VO> list) {
		if (dao == null) {
			return;
		}
		dao.insertList(list);
	}
}
