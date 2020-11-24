package com.pd.springboot.service;

import static com.pd.common.util.StaticTool.strToList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.MapVO;
import com.pd.common.util.DoubleX;
import com.pd.common.util.MapVOX;
import com.pd.common.util.StringFactory;
import com.pd.common.util.WebUtil;
import com.pd.springboot.dao.IAppJobDao;
import com.pd.standard.itf.IBuilder;

@Named
public class Job51Service extends ServiceAdapter<MapVO, MapVO, IAppJobDao> {
    @Autowired
    private IAppJobDao dao;

    public void init(MapVO fo) {
        dao.delete(fo);
    }

    public void process(MapVO fo) {
        String keyword = "java";
        for (int i = 1, total = 50; i <= total; i++) {
            String url = "https://search.51job.com/list/180200,000000,0000,00,9,99," + keyword + ",2," + i
                    + ".html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            String httpStr = WebUtil.post(url, null, "GB2312");

            try {
                dao.insertList(new Strategy20200802().build(httpStr));
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }

    }

}

class SalaryFromBuilder implements IBuilder<String, String> {
    @Override
    public String build(String in) {
        try {
            String salaryStr = in;
            int base = 0;
            if (salaryStr.endsWith("万/月")) {
                base = 10000;
            } else if (salaryStr.endsWith("千/月")) {
                base = 1000;
            } else if (salaryStr.endsWith("万/年")) {
                base = 833;
            }
            String from = salaryStr.substring(0, salaryStr.indexOf("-"));
            return DoubleX.str(from) * base + "";
        } catch (Exception e) {
            return "0";
        }
    }

}

class SalaryToBuilder implements IBuilder<String, String> {
    @Override
    public String build(String in) {
        try {
            String salaryStr = in;
            int base = 0;
            if (salaryStr.endsWith("万/月")) {
                base = 10000;
            } else if (salaryStr.endsWith("千/月")) {
                base = 1000;
            } else if (salaryStr.endsWith("万/年")) {
                base = 833;
            }
            String to = salaryStr.substring(salaryStr.indexOf("-") + 1, salaryStr.indexOf("/") - 1);
            return DoubleX.str(to) * base + "";
        } catch (Exception e) {
            return "0";
        }
    }
}

class VoBuilder implements IBuilder<Element, MapVO> {
    @Override
    public MapVO build(Element in) {
        MapVO rsVO = new MapVO();
        rsVO.put("id", in.getElementsByClass("t1").first().getElementsByClass("checkbox").first().attr("value"));
        rsVO.put("location", in.getElementsByClass("t3").first().text());
        rsVO.put("company", in.getElementsByClass("t2").first().getElementsByTag("a").first().attr("title"));
        rsVO.put("jobName", in.getElementsByClass("t1").first().getElementsByTag("span").first().getElementsByTag("a")
                .first().attr("title"));
        String salary = in.getElementsByClass("t4").first().text();
        rsVO.put("salary", in.getElementsByClass("t4").first().text());
        rsVO.put("salaryFrom", new SalaryFromBuilder().build(salary));
        rsVO.put("salaryTo", new SalaryToBuilder().build(salary));
        rsVO.put("creationDate", in.getElementsByClass("t5").first().text());
        rsVO.put("url", in.getElementsByClass("t1").first().getElementsByTag("span").first().getElementsByTag("a")
                .first().attr("href"));
        return rsVO;
    }

}

class StrategyOld implements IBuilder<String, List<MapVO>> {

    @Override
    public List<MapVO> build(String in) throws BusinessException {
        Document doc = Jsoup.parse(in);
        Element resultList = doc.getElementById("resultList");
        Elements divs = resultList.getElementsByClass("el");
        divs.remove(0);
        List<MapVO> list = divs.stream().map(vo -> new VoBuilder().build(vo)).collect(Collectors.toList());
        return list;
    }

}

class Strategy20200802 implements IBuilder<String, List<MapVO>> {

    @Override
    public List<MapVO> build(String in) throws BusinessException {
        String str = StringFactory.between(in, "\"engine_search_result\":", ",\"jobid_count\"");
        System.out.println(str);
        List<MapVO> mapList = strToList(str, MapVO.class);
        List<MapVO> rsList = new ArrayList<>();
        mapList.forEach(vo -> {

            MapVO bridgeVO = MapVOX.bridge(vo,
                    "company_name:company,jobid:id,workarea_text:location,job_name:jobName,providesalary_text:salary,issuedate:creationDate,job_href:url");
            String salary = bridgeVO.str("salary");
            bridgeVO.put("salaryFrom", new SalaryFromBuilder().build(salary));
            bridgeVO.put("salaryTo", new SalaryToBuilder().build(salary));

            rsList.add(bridgeVO);
        });
        return rsList;
    }

}