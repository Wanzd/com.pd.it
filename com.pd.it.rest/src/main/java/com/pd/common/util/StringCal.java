package com.pd.common.util;

import static com.pd.common.util.StaticTool.isNull;

public class StringCal {

    public static boolean isMail(String value) {
        if (isNull(value)) {
            return false;
        }
        if (!value.matches(".*@.*")) {
            return false;
        }
        return true;
    }
}
