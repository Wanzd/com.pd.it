package com.pd.springboot.rest;

import static com.pd.it.common.util.StaticTool.str;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.MailVO;
import com.pd.common.util.LookupTool;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.adaptor.IRedisAdaptor;
import com.pd.springboot.dao.ITestDao;
import com.pd.springboot.service.MailService;
import com.pd.springboot.service.MenuService;

@RestController
@RequestMapping("testRest")
public class TestRest {
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
        LookupFO fo = new LookupFO();
        fo.setType("country");
        fo.setCode("CN");
        return str(LookupTool.getInfo(fo));
    }

    @RequestMapping("/queryRedis")
    public String queryRedis() throws BusinessException {
        return redisAdaptor.query("user.1");
    }

    @RequestMapping("/testMail")
    public String testMail() throws BusinessException {
        MailVO mailVO = new MailVO();
        mailVO.setMailSender("pd_test@163.com");
        mailVO.setMailTo(Arrays.asList("panda_zdwan@hotmail.com").toArray(new String[0]));
        mailVO.setSubject("testSubject");
        mailVO.setMailContent("testContent");
        mailService.sendMail(mailVO);
        return "200";
    }
}
