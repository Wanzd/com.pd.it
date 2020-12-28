package com.pd.it.common.itf;

import com.pd.it.common.util.Reflects;

public interface IAttrName {
    default String getName() {
        return Reflects.field(this, String.class, "name");
    }
}
