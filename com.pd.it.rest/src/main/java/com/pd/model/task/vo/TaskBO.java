package com.pd.model.task.vo;

import com.pd.businessobject.BaseResourceBO;

import lombok.Data;

@Data
public class TaskBO extends BaseResourceBO {
    private String type;/* 任务类型 */
    private String subType;/* 任务子类型 */
    private String taskKey;/* 任务主键 */
    private String reqParam;/* 入参 */
    private String resParam;/* 出参 */
    private int failCnt;/* 已经失败次数 */
    private int chanceCnt;/* 尝试机会 */
}