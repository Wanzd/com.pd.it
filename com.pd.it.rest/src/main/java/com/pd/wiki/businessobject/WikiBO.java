package com.pd.wiki.businessobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pd.it.common.itf.IIdentity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@TableName("wiki_t")
public class WikiBO implements IIdentity<String> {
	@TableId(type = IdType.INPUT)
	private String id;
	private String groupId;
	private String name;
	private String content;
}
