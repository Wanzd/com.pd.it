package com.pd.businessobject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pd.standard.itf.IIdentity;

import lombok.Data;

@Data
public class UserBO implements Serializable, IIdentity<Long> {
	@TableId(type = IdType.INPUT)
	private Long id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String fatherId;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String motherId;
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String mateId;
	private String sex;
	private String tel;
	private Integer sortId;

}
