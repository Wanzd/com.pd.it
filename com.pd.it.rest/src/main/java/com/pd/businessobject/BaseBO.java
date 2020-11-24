package com.pd.businessobject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseBO implements Serializable {
    private String deleteFlag;
    @TableField(exist = false)
    private Double sortId;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date creationDate;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastUpdateDate;
    private String lastUpdatedBy;
}
