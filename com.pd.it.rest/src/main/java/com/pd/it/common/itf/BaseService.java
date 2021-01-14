package com.pd.it.common.itf;

import javax.inject.Inject;

import lombok.Getter;

public class BaseService<FO, VO, Bridge> {

    @Inject
    @Getter
    protected Bridge dao;

}
