package com.pd.it.base.itf;

public interface IValidable<T> {
	String valid();

	default String valid(String validScene) {
		return null;
	}
}
