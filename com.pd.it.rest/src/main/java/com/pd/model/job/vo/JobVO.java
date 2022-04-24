package com.pd.model.job.vo;

import lombok.Data;

import static com.pd.it.common.util.StaticTool.asList;
import static com.pd.it.common.util.StringTool.joinStr;

@Data
public class JobVO extends JobBO {
    private String title;
    public String getTitle(){
        return joinStr(asList(getLocation(),getCompany(),getJobName(),getSalary()),"<p/>");
    }
}