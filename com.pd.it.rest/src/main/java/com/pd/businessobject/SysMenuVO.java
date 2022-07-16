package com.pd.businessobject;

import static com.pd.it.common.util.StaticTool.compare;
import static com.pd.it.common.util.StaticTool.ne;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@TableName("sys_menu_t")
public class SysMenuVO extends SysMenuBO {

	public int equals(SysMenuVO vo) {
		if (ne(this.getPid(), vo.getPid())) {
			return compare(this.getPid(), vo.getPid());
		}
		if (ne(this.getId(), vo.getId())) {
			return compare(this.getId(), vo.getId());
		}
		return 0;
	}
}
