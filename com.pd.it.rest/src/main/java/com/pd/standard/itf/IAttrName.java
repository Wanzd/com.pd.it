package com.pd.standard.itf;

import com.pd.common.util.Reflects;

public interface IAttrName {
	default String getName() {
		return Reflects.field(this, String.class, "name");
	}
}
