package com.pd.job.service;

import com.pd.common.util.WebUtil;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.BaseService;
import com.pd.job.calculator.JobListCalculator;
import com.pd.model.job.vo.JobFO;
import com.pd.model.job.vo.JobVO;
import com.pd.springboot.dao.IAppJobDao;

import javax.inject.Named;
import java.util.List;

import static com.pd.it.common.util.StaticTool.*;

@Named
public class Job51Service extends BaseService<JobFO, JobVO, IAppJobDao> {
    private static final String URL = "https://search.51job.com/list/180200,000000,0000,00,9,99,%s,2,%d.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";

    private JobListCalculator jobListCalculator = new JobListCalculator();

    public void init(MapVO fo) {
        // dao.deleteInfo(fo);
    }

    public void process(MapVO fo) {
        String keyword = "java";
        for (int i = 1, total = 200; i <= total; i++) {
            String url = formatStr(URL, keyword, i);
            String httpStr = WebUtil.post(url, null, "gbk");
            List<JobVO> list = cal(httpStr, jobListCalculator);
            if (isEmpty(list)) {
                break;
            }
            dao.insertList(list);
        }

    }

}
