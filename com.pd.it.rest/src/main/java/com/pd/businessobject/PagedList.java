package com.pd.businessobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pd.it.common.businessobject.PageVO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class PagedList<T> {
    private PageVO page;
    private List<T> list;
}
