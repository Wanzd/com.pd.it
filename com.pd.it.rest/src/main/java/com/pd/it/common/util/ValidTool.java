package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.eq;
import static com.pd.it.common.util.StringTool.Y_FLAG;

public class ValidTool {
    public static boolean isY(String str) {
        return eq(Y_FLAG, str);
    }
}
