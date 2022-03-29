package com.pd.springboot.service;

import static com.pd.it.common.util.StaticTool.ZERO;
import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.mul;
import static com.pd.it.common.util.StaticTool.strToList;
import static com.pd.it.common.util.StaticTool.toDecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pd.common.util.MapVOX;
import com.pd.common.util.StringFactory;
import com.pd.common.util.WebUtil;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IBuilder;
import com.pd.springboot.dao.IAppJobDao;

@Named
public class Job51Service extends BaseService<MapVO, MapVO, IAppJobDao> {
    private static final String URL = "https://search.51job.com/list/180200,000000,0000,00,9,99,%s,2,%d.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";

    Calculator cal = new Calculator();

    public void init(MapVO fo) {
        // dao.deleteInfo(fo);
    }

    public void process(MapVO fo) {
        String keyword = "java";
        List<MapVO> tmpList = new ArrayList<>();
        for (int i = 1, total = 50; i <= total; i++) {
            String url = formatStr(URL, keyword, i);
            String httpStr = WebUtil.post(url, null, "gbk");
            try {
                List<MapVO> list = cal.buildStrategy20200802(httpStr);
                if (isEmpty(list)) {
                    break;
                }
                dao.insertList(list);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }

    }

    class Calculator {
        private List<MapVO> buildStrategy20200802(String in) throws BusinessException {
            String str = StringFactory.between(in, "\"engine_jds\":", ",\"jobid_count\"");
            if (isEmpty(str)) {
                return null;
            }
            List<MapVO> mapList = strToList(str, MapVO.class);
            List<MapVO> rsList = new ArrayList<>();
            if (isEmpty(mapList)) {
                return null;
            }
            mapList.forEach(vo -> {

                MapVO bridgeVO = MapVOX.bridge(vo,
                        "company_name:company,jobid:id,workarea_text:location,job_name:jobName,providesalary_text:salary,issuedate:creationDate,job_href:url");
                String salary = bridgeVO.str("salary");
                bridgeVO.put("salaryFrom", cal.buildSalaryFrom(salary));
                bridgeVO.put("salaryTo", cal.buildSalaryTo(salary));

                rsList.add(bridgeVO);
            });
            return rsList;
        }

        private BigDecimal buildSalaryFrom(String in) {
            try {
                String salaryStr = in;
                int base = 1;
                if (salaryStr.endsWith("万/月")) {
                    base = 10000;
                } else if (salaryStr.endsWith("千/月")) {
                    base = 1000;
                } else if (salaryStr.endsWith("万/年")) {
                    base = 833;
                }
                String from = salaryStr.substring(0, salaryStr.indexOf("-"));
                return mul(toDecimal(from), base);
            } catch (Exception e) {
                return ZERO;
            }
        }

        private BigDecimal buildSalaryTo(String in) {
            try {
                String salaryStr = in;
                int base = 1;
                if (salaryStr.endsWith("万/月")) {
                    base = 10000;
                } else if (salaryStr.endsWith("千/月")) {
                    base = 1000;
                } else if (salaryStr.endsWith("万/年")) {
                    base = 833;
                }
                String to = salaryStr.substring(salaryStr.indexOf("-") + 1, salaryStr.indexOf("/") - 1);
                return mul(toDecimal(to), base);
            } catch (Exception e) {
                return ZERO;
            }
        }
    }

}
