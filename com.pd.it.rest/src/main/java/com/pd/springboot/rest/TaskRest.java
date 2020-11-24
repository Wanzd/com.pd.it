package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.springboot.SpringUtil;
import com.pd.standard.itf.ITask;
import com.pd.standard.itf.TaskEnum;

/**
 * 集成系统
 * 
 * @author thinkpad
 *
 */
@RestController
@RequestMapping("/taskRest")
public class TaskRest {
	@RequestMapping(value = "/{taskName}")
	@ResponseBody
	public Object intergration(@PathVariable String taskName) {
		// if (TaskEnum.valueOf(taskName) == null) {
		// return "Not api task:" + taskName;
		// }
		ITask task = SpringUtil.getBean(taskName, ITask.class);
		if (task == null) {
			return "Not impl task:" + taskName;
		}
		return task.process();
	}
}
