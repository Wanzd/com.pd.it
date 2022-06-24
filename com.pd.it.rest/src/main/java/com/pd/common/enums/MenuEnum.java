package com.pd.common.enums;

import com.pd.businessobject.SysMenuVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuEnum {
	devPerspective("","devPerspective","开发视角",0,null),
	devTool("devPerspective","devTool","开发辅助工具",1,null);
	private String pid;
	private String id;
	private String name;
	private Integer sordId;
	private String url;
}
