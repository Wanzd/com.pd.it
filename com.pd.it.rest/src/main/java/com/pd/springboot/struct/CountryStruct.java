package com.pd.springboot.struct;

import javax.inject.Named;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.util.DbTool;
import com.pd.it.common.util.Tools;
import com.pd.springboot.adaptor.IRedisAdaptor;
import com.pd.springboot.dao.ITestDao;
import com.pd.springboot.service.MailService;
import com.pd.springboot.service.MenuService;

import lombok.Getter;
import lombok.Setter;

public class CountryStruct {
    @Getter
    @Setter
    public static class CountryBO {
        private String code;
        private String nameEn;
        private String nameCn;
    }

    public static class CountryVO extends CountryBO {}

    public static class CountryFO extends CountryVO {}

    @Mapper
    public static interface ICountryDao extends IBaseDao<CountryFO, CountryVO> {}

    @Named
    public static class CountryService extends BaseService<CountryFO, CountryVO, ICountryDao> {}

    @RestController
    @RequestMapping("countryRest")
    public static class CountryRest {
        @Autowired
        ITestDao dao;
        @Autowired
        private IRedisAdaptor redisAdaptor;
        @Autowired
        MailService mailService;
        @Autowired
        MenuService menuService;

        @RequestMapping("/test1")
        public String root() throws BusinessException {
            CountryService countryTool = Tools.getCountryTool();
            Object queryInfo2 = DbTool.queryInfo(countryTool, null);
            return "null";
        }

    }
}
