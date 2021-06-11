package com.pd.it.common.businessobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    public static final String SUCCESS = "200";

    private String code;
    private String msg;
    private T data;

    public static <IN> ResultVO<IN> success(IN in) {
        ResultVO<IN> res = new ResultVO();
        res.setCode(SUCCESS);
        res.setData(in);
        return res;
    }
}
