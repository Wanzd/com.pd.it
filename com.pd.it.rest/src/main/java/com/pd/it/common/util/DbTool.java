package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.isNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.MybatisPlusService;
import com.pd.model.datasource.vo.DataSourceVO;

public class DbTool {

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
        if (bean instanceof BaseService) {
            return new BaseServiceDbAdapter((BaseService) bean);
        }
        if (bean instanceof MybatisPlusService) {
            return new BaseServiceDbAdapter((BaseService) bean);
        }
        return null;
    }
}
