package com.pd.businessobject;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user_t")
public class UserExtendBO implements Serializable {
	@TableId(type = IdType.INPUT)
	private String id;
	private String fatherId;
}
