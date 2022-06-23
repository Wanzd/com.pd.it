package com.pd.businessobject;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SysMenuFO extends SysMenuVO {
	
	@NotNull
	private String keyTest;

	@Override
	public String getPid(){
		String spid=super.getPid();
		return spid==null?"":spid;
	}
}
