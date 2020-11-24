package com.pd.businessobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName("app_job_t")
public class AppJobBO implements Serializable {
	@TableId(type = IdType.INPUT)
	private String id;
	private String location;
	private String company;
	private String jobName;
	private String salary;
	private BigDecimal salaryFrom;
	private BigDecimal salaryTo;

	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date creationDate;
	private String url;
	private Integer deleted;
	@TableLogic
	private Integer deleteFlag;
}
