package com.pd.standard.itf;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum {
	Male("1", "男"), Female("0", "女");
	private String code;
	private String value;

}
