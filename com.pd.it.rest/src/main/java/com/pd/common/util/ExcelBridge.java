package com.pd.common.util;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IExportOperation;

public class ExcelBridge {

    public static <FO> void export(Object field, FO fo) throws BusinessException {
        if (field instanceof IExportOperation) {
            IExportOperation op = (IExportOperation) field;
            op.export(fo);
        }
    }
}
