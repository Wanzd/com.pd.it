package com.pd.it.base.util;

import static com.pd.it.base.util.StaticTool.formatStr;

import com.pd.it.base.itf.Validable;

public class LenEqValid<T> extends Validable<T> {

	private int len;

	public LenEqValid(T target, int len) {
		super(target);
		this.len = len;
	}

	@Override
	public String valid() {
		String errorMsg = formatStr("lenEq:%s:%d failed¡£", target, len);
		return StaticTool.len(target) != len ? errorMsg : null;
	}

}
