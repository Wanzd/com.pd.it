package com.pd.springboot.business;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.WashingMachineBO;
import com.pd.springboot.mongo.WashingMachineDao;

@Named
public class WashingMachineBusiness {

    @Autowired
    private WashingMachineDao dao;

    public void init() throws BusinessException {
        dao.truncate();
    }

    public void process() throws BusinessException {

        // fo.put("type", "sari");
        // List<MapVO> list = baseService.queryList(fo);
        // if (list.size() == 0) {
        // String httpStr =
        // WebUtil.post("https://www.zhihu.com/special/19681091/trends", null);
        // MapVO vo = new MapVO();
        // vo.put("type", "sari");
        // vo.put("creationDate", new Date());
        // vo.put("parseBean", "html");
        // vo.put("value", httpStr);
        // List<MapVO> insertList = ListX.as(vo);
        // baseService.insertList(insertList);
        // list = baseService.queryList(fo);
        // }
        // init(list);
        // dao.delete(fo);// 初始化今日数据
        //
        // List<MapVO> insertList = new SariInsertListBuilder().build(list);
        // ListUtils.partition(insertList, 500).forEach(subList -> {
        // try {
        // dao.updateList(subList);
        // } catch (Exception e) {
        // System.out.println(subList);
        // }
        // });// 分页插入数据库
        //
        // dao.initChinaData();
        
        WashingMachineBO bo = new WashingMachineBO();
        bo.setId("111");
        bo.setSource("taobao");
        dao.insert(bo);
    }

}
