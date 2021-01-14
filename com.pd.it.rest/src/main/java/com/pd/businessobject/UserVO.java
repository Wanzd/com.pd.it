package com.pd.businessobject;

import static com.pd.it.common.util.StaticTool.apply;
import static com.pd.it.common.util.StaticTool.isEmpty;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pd.common.util.IntegerX;
import com.pd.standard.itf.SexEnum;

import lombok.Data;

@Data
@TableName("user_t")
@KeySequence(value = "user_s")
@JsonInclude(Include.NON_NULL)
public class UserVO extends UserBO {

    @TableField(exist = false)
    private UserVO father;
    @TableField(exist = false)
    private UserVO mother;
    @TableField(exist = false)
    private UserVO mate;
    @TableField(exist = false)
    private UserExtendBO extend;

    @TableField(exist = false)
    private Integer age;
    @TableField(exist = false)
    private String sexLabel;

    public Integer getAge() {
        return IntegerX.getAge(getBirthday());
    }

    public String getSexLabel() {
        String tmpSex = getSex();
        if (isEmpty(tmpSex)) {
            return null;
        }
        SexEnum sexEnum = SexEnum.valueOf(tmpSex);
        return apply(sexEnum, SexEnum::getValue);
    }
}
