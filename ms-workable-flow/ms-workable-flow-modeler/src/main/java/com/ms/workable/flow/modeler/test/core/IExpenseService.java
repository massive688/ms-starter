package com.ms.workable.flow.modeler.test.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IExpenseService {

   /*
     * 新增一个报销单
     */
    void add(Expense expense);

   /*
     * 删除一个报销单
     */
    void delete(Integer expenseId);

   /*
     * 通过审批
     */
    void pass(String taskId);

   /*
     * 通过审批
     */
    void unPass(String taskId);

   /*
     * 获取审批列表
     */
    List<TaskVo> getProcessList();

   /*
     * 绘画当前流程图
     */
    void printProcessImage(Integer expenseId) throws IOException;

	List<Map<String, Object>> selectMaps(EntityWrapper<Expense> expenseEntityWrapper);

	void updateById(Expense expense);

	Object selectById(Integer expenseId);

}
