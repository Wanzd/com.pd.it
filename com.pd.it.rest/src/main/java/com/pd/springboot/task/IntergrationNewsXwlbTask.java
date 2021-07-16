package com.pd.springboot.task;

import static com.pd.it.common.util.StaticTool.ENCODE_UTF8;
import static com.pd.it.common.util.StaticTool.formatDate;
import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.str;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.pd.common.calobject.TimerCO;
import com.pd.common.util.WebUtil;
import com.pd.it.common.businessobject.MapVO;
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
public class IntergrationNewsXwlbTask implements ITask {
    private static final String XWLB_URL = "http://tv.cctv.com/lm/xwlb/day/%s.shtml";
    @Inject
    private TextareaService textareaService;
    @Inject
    private MailService mailService;
    private Calculator cal = new Calculator();

    @Override
    public Object process() throws BusinessException {
        TimerCO timer = new TimerCO(this.getClass().toString());
        String url = formatStr(XWLB_URL, formatDate(new Date(), "yyyyMMdd"));
        String htmlStr = WebUtil.get(url, ENCODE_UTF8);
        Document doc = Jsoup.parse(htmlStr);

        Elements divs = doc.getElementsByTag("li");
        List<MapVO> list = cal.parseList(divs);
        timer.end();
        return str(timer);
    }

    private class Calculator {
        private List<MapVO> parseList(Elements divs) {
            List<MapVO> list = new ArrayList<>();
            divs.forEach(each -> {
                MapVO mapVO = new MapVO();
                mapVO.put("url", each.getElementsByTag("a").first().attr("href"));
                mapVO.put("title", each.getElementsByClass("title").first().text());
                
                list.add(mapVO);
            });
            return list;
        }

    }
}
