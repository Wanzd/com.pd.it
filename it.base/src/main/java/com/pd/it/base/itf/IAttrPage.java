package com.pd.it.base.itf;

import com.pd.it.base.vo.PageVO;

public interface IAttrPage {
	default PageVO getPage() {
		return null;
	}

	default void setPage(PageVO page) {
	}
}
