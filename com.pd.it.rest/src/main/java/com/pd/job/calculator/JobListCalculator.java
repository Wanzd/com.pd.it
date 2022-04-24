package com.pd.job.calculator;

import com.pd.common.util.MapVOX;
import com.pd.common.util.StringFactory;
import com.pd.it.common.businessobject.MapVO;
import com.pd.model.job.vo.JobVO;
import com.pd.standard.itf.ICalculator;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.pd.it.common.util.StaticTool.*;

@Named
public class JobListCalculator implements ICalculator<String, List<JobVO>> {
    @Override
    public List<JobVO> cal(String in) {
        String str = StringFactory.between(in, "\"engine_jds\":", ",\"jobid_count\"");
        if (isEmpty(str)) {
            return null;
        }
        List<MapVO> mapList = strToList(str, MapVO.class);
        if (isEmpty(mapList)) {
            return null;
        }
        List<JobVO> rsList = new ArrayList<>();
        mapList.forEach(vo -> {
            MapVO bridgeVO = MapVOX.bridge(vo,
                    "company_name:company,jobid:id,workarea_text:location,job_name:jobName,providesalary_text:salary,issuedate:creationDate,job_href:url");
            JobVO jobVO = toObj(bridgeVO, JobVO.class);
            String salary = jobVO.getSalary();
            jobVO.setSalaryFrom(calSalaryFrom(salary));
            jobVO.setSalaryTo(calSalaryTo(salary));

            rsList.add(jobVO);
        });
        return rsList;
    }

    private BigDecimal calSalaryFrom(String in) {
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

    private BigDecimal calSalaryTo(String in) {
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
