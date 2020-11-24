package com.pd.businessobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PageVO {
	private int pageSize;
	private int curPage;
	private int mode;
	private int total;
	private int startIdx;
	private int endIdx;

	public int getStartIdx() {
		return pageSize * (curPage - 1) + 1;
	}

	public int getEndIdx() {
		return pageSize * curPage;
	}

}
