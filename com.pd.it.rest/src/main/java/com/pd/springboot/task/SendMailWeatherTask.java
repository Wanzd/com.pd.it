package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.str;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.businessobject.MailVO;
import com.pd.businessobject.TextareaFO;
import com.pd.businessobject.TextareaVO;
import com.pd.common.calobject.TimerCO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.ITask;
import com.pd.springboot.service.MailService;
import com.pd.springboot.service.TextareaService;

/**
 * 每三分钟一次，扫描任务表，如果没有今天的新冠数据扫描任务，则新建一个任务
 * 
 * @author thinkpad
 *
 */
@Named
public class SendMailWeatherTask implements ITask {
    @Inject
    private TextareaService textareaService;
    @Inject
    private MailService mailService;

    @Override
    public Object process() throws BusinessException {
        TextareaFO textareaFO = new TextareaFO();
        textareaFO.setType("freeMarker");
        textareaFO.setKey("testMail");
        TextareaVO textareaVO = textareaService.queryInfo(textareaFO);
        TimerCO timer = new TimerCO(this.getClass().toString());
        MailVO mailVO = new MailVO();
        mailVO.setMailSender("pd_test@163.com");
        mailVO.setMailTo(Arrays.asList("panda_zdwan@hotmail.com").toArray(new String[0]));
        mailVO.setSubject(textareaVO.getTitle());
        mailVO.setMailContent(textareaVO.getValue());
        mailService.sendMail(mailVO);
        timer.end();
        return str(timer);
    }

}
