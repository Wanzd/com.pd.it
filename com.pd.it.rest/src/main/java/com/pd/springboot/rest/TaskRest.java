package com.pd.springboot.rest;

import static com.pd.it.common.util.StaticTool.getBean;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.itf.ITask;

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
        ITask task = getBean(taskName, ITask.class);
        if (task == null) {
            return "Not impl task:" + taskName;
        }
        return task.process();
    }
}
