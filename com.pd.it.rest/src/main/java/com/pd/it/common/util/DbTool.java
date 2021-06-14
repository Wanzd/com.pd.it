package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.toObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IQueryInfoOperation;
import com.pd.model.datasource.vo.DataSourceVO;

public class DbTool {
    public static <FO, VO> VO queryInfo(Object field, FO fo) throws BusinessException {
        if (field instanceof IQueryInfoOperation) {
            IQueryInfoOperation op = (IQueryInfoOperation) field;
            return (VO) op.queryInfo(fo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return (VO) op.selectById(toObj(fo, HashMap.class));
        }
        return null;
    }

    public static String getCreateTableSql(DataSourceVO dsVO, String fromTable) {
        try {
            Class.forName(dsVO.getDriver());
            Connection conn = DriverManager.getConnection(dsVO.getUrl(), dsVO.getUsername(), dsVO.getPassword());
            Statement stmt = conn.createStatement();
            String sql = formatStr("show create table %s", fromTable);
            ResultSet executeQuery = stmt.executeQuery(sql);
            executeQuery.next();
            String createTableSql = executeQuery.getString(2);
            String rsSql=createTableSql.replaceAll("COLLATE=utf8mb4_0900_ai_ci","").replaceAll("COLLATE utf8mb4_0900_ai_ci", "");//utf8mb4_0900_ai_ci
            conn.close();
            return rsSql;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

}
