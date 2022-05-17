package com.pd.businessobject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pd.it.common.itf.IIdentity;

import lombok.Data;

@Data
public class PersonBO implements Serializable, IIdentity<Long> {
	@TableId(type = IdType.INPUT)
	private Long id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;
	private String sex;
	private String desc;
}
