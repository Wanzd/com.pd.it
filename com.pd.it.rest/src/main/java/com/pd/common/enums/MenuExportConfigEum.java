package com.pd.common.enums;

import com.pd.common.annotations.Export;
import com.pd.standard.itf.IExportConfigEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Export(titleName = "MenuInfo", sheetName = "MenuInfo")
public enum MenuExportConfigEum implements IExportConfigEnum {
    Pid("pid", "父节点", "String"),
    Id("id", "Id", "String"),
    Name("name", "名称", "String"),
    SortId("sortId", "排序号", "String"),
    Url("url", "链接", "String");
    private String field;
    private String label;
    private String type;
}
