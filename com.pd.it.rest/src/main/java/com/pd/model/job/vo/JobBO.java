package com.pd.model.job.vo;

import java.math.BigDecimal;

import com.pd.businessobject.BaseResourceBO;

import lombok.Data;

@Data
public class JobBO extends BaseResourceBO {
    private String id;
	private String company;
	private String location;
	private String jobName;
	private String salary;
    private BigDecimal salaryFrom;
    private BigDecimal salaryTo;
	private String url;
}