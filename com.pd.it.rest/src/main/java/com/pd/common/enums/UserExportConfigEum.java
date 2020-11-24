package com.pd.common.enums;

import com.pd.common.annotations.Export;
import com.pd.standard.itf.IExportConfigEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Export(titleName = "人员信息", sheetName = "UserInfo")
public enum UserExportConfigEum implements IExportConfigEnum {
    Name("name", "姓名", "String"),
    Sex("sexLabel", "性别", "String"),
    Telephone("tel", "电话号码", "String");
    private String field;
    private String label;
    private String type;
}
