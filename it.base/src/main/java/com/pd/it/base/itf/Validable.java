package com.pd.it.base.itf;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Validable<T> implements IValidable<T> {
	protected T target;
}
