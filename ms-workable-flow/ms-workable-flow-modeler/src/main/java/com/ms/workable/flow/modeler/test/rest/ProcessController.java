package com.ms.workable.flow.modeler.test.rest;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.workable.flow.modeler.test.core.BaseController;
import com.ms.workable.flow.modeler.test.core.IExpenseService;

/**
 * 审批管理控制器
 *
 */
@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController {

    private String PREFIX = "/flowable/process/";

    @Autowired
    private IExpenseService expenseService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

   /*
     * 跳转到审批管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "process.html";
    }


   /*
     * 获取审批管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return expenseService.getProcessList();
    }

   /*
     * 审核通过
     */
    @RequestMapping(value = "/pass")
    @ResponseBody
    public Object pass(String taskId) {
        expenseService.pass(taskId);
        return super.SUCCESS_TIP;
    }

   /*
     * 审核不通过
     */
    @RequestMapping(value = "/unPass")
    @ResponseBody
    public Object unPass(String taskId) {
        expenseService.unPass(taskId);
//        runtimeService.
        return super.SUCCESS_TIP;
    }
}
