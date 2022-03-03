package com.pd.businessobject;

import java.util.List;

import lombok.Data;

@Data
public class StepVO {
    private List<StepBO> stepInfoList;

    public void addStep(StepBO stepBO) {
        if (null == stepInfoList) {
            return;
        }
        stepInfoList.add(stepBO);
    }
}
