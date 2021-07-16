package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.ENCODE_UTF8;
import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.formatStr;

import java.util.Date;

import javax.inject.Named;

import com.pd.common.util.WebUtil;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.ITask;

/**
 * 每三分钟一次，扫描任务表，如果没有今天的新冠数据扫描任务，则新建一个任务
 * 
 * @author thinkpad
 *
 */
@Named
public class XwlbLink implements ITask {
    private static final String XWLB_URL = "http://tv.cctv.com/lm/xwlb/day/%s.shtml";

    @Override
    public Object process() throws BusinessException {
        String url = formatStr(XWLB_URL, formatDate(new Date(), "yyyyMMdd"));
        return WebUtil.get(url, ENCODE_UTF8);
    }

}
