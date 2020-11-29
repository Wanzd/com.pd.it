package com.pd.businessobject;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SysMenuFO extends SysMenuVO {
	
	@NotNull
	private String keyTest;
}
