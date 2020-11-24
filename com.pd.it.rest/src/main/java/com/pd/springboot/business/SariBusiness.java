package com.pd.springboot.business;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pd.base.exception.BusinessException;
import com.pd.builderobject.SariInsertListBuilder;
import com.pd.businessobject.MapVO;
import com.pd.common.util.ListFactory;
import com.pd.common.util.WebUtil;
import com.pd.springboot.dao.IAppSariDao;
import com.pd.springboot.service.SariBaseService;

@Named
public class SariBusiness {
    @Autowired
    private SariBaseService baseService;
    @Autowired
    private IAppSariDao dao;

    public void process(MapVO fo) throws BusinessException {
        fo.put("type", "sari");
        List<MapVO> list = baseService.queryList(fo);
        if (list.size() == 0) {
            String httpStr = WebUtil.post("https://www.zhihu.com/special/19681091/trends", null);
            MapVO vo = new MapVO();
            vo.put("type", "sari");
            vo.put("creationDate", new Date());
            vo.put("parseBean", "html");
            vo.put("value", httpStr);
            List<MapVO> insertList = ListFactory.asList(vo);
            baseService.insertList(insertList);
            list = baseService.queryList(fo);
        }
        init(list);
        dao.delete(fo);// 初始化今日数据

        List<MapVO> insertList = new SariInsertListBuilder().build(list);
        ListUtils.partition(insertList, 500).forEach(subList -> {
            try {
                dao.updateList(subList);
            } catch (Exception e) {
                System.out.println(subList);
            }
        });// 分页插入数据库

        dao.initChinaData();
    }

    private void init(List<MapVO> list) {
        for (MapVO eachVO : list) {
            eachVO.put("value", eachVO.str("value").replaceAll("\r\n", ";").replaceAll("人、", "例、")
                    .replaceAll("确诊", " 确诊 ").replaceAll("州", "州 ").replaceAll("省", " "));
        }
    }

    public void init(MapVO fo) throws BusinessException {
        fo.put("type", "sari");
        baseService.delete(fo);
    }

}
