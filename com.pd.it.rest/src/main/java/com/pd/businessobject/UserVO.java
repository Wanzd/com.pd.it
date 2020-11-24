package com.pd.businessobject;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pd.common.util.EnumX;
import com.pd.common.util.IntegerX;
import com.pd.standard.itf.SexEnum;

import lombok.Data;

@Data
@TableName("user_t")
@KeySequence(value = "user_s")
@JsonInclude(Include.NON_NULL)
public class UserVO extends UserBO {

	@TableField(exist = false)
	private UserVO father;
	@TableField(exist = false)
	private UserVO mother;
	@TableField(exist = false)
	private UserVO mate;
	@TableField(exist = false)
	private UserExtendBO extend;

	@TableField(exist = false)
	private Integer age;
	@TableField(exist = false)
	private String sexLabel;

	public Integer getAge() {
		return IntegerX.getAge(getBirthday());
	}

	public String getSexLabel() {
		String tmpSex = getSex();
		if (StringUtils.isEmpty(tmpSex)) {
			return null;
		}
		SexEnum sexEnum = EnumX.getEnum(SexEnum.class, "code", tmpSex);
		if (sexEnum == null) {
			return null;
		}
		return sexEnum.getValue();
	}
}
