package com.pd.springboot.business;

import static com.pd.it.common.util.StaticTool.assertNull;
import static com.pd.it.common.util.StaticTool.queryInfo;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.common.util.MapVOX;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IBuilder;
import com.pd.model.datasource.vo.CopyTableVO;
import com.pd.model.datasource.vo.DataSourceVO;
import com.pd.springboot.dao.ISysObjDao;
import com.pd.springboot.dao.IViewDao;
import com.pd.springboot.service.DataSourceService;
import com.pd.springboot.service.SysDataSourceService;

@Named
public class DataSourceBusiness {
    private Map<String, IBuilder<SysDataSourceBO, Object>> builderMap = initBuilderMap();
    @Inject
    private SysDataSourceService service;
    @Inject
    private IViewDao viewDao;
    @Inject
    private ISysObjDao sysObjDao;

    @Inject
    DataSourceService dataSourceService;

    public Object query(SysDataSourceFO fo) throws BusinessException {
        SysDataSourceBO vo = queryInfo(service, fo);
        assertNull(vo, "SysDataSourceService.queryInfo return null!");

        IBuilder<SysDataSourceBO, Object> opBuilder = builderMap.get(vo.getType());
        assertNull(opBuilder, "vo.getType() not support!");
        return opBuilder.build(vo);
    }

    /**
     * 初始化执行器地图
     * 
     * @return
     */
    private Map<String, IBuilder<SysDataSourceBO, Object>> initBuilderMap() {
        Map<String, IBuilder<SysDataSourceBO, Object>> opMap = new HashMap<>();
        opMap.put("view", new DataSourceViewBuilder());
        opMap.put("obj", new DataSourceObjBuilder());
        opMap.put("treeGrid", new DataSourceTreeGridBuilder());
        return opMap;
    }

    public class DataSourceViewBuilder implements IBuilder<SysDataSourceBO, Object> {
        @Override
        public Object build(SysDataSourceBO in) throws BusinessException {
            MapVO fo = new MapVO();
            fo.put("viewName", in.getDetail());
            return viewDao.queryList(fo);
        }
    }

    public class DataSourceObjBuilder implements IBuilder<SysDataSourceBO, Object> {

        @Override
        public Object build(SysDataSourceBO in) throws BusinessException {
            MapVO fo = new MapVO();
            fo.put("objId", in.getDetail());
            String jsonData = sysObjDao.queryJson(fo);
            return new MapVO(jsonData);
        }

    }

    public class DataSourceTreeGridBuilder implements IBuilder<SysDataSourceBO, Object> {

        @Override
        public Object build(SysDataSourceBO in) throws BusinessException {
            MapVO fo = new MapVO();
            fo.put("viewName", in.getDetail());
            // return viewDao.queryList(fo);
            return MapVOX.sortTreeList(viewDao.queryList(fo));
        }

    }

}
