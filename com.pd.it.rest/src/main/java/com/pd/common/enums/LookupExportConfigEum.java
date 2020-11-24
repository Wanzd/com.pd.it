package com.pd.common.enums;

import com.pd.common.annotations.Export;
import com.pd.standard.itf.IExportConfigEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Export(titleName = "Lookup信息", sheetName = "LookupInfo")
public enum LookupExportConfigEum implements IExportConfigEnum {
    LookupType("lookupType", "Lookup类型", "String"),
    LookupCode("lookupCode", "Lookup编码", "String"),
    LookupText("lookupText", "LookupText", "String");
    private String field;
    private String label;
    private String type;
}
