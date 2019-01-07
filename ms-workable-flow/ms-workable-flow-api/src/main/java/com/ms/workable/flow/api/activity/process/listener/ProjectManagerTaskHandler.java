//package com.ms.workable.flow.api.activity.process.listener;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import org.flowable.engine.delegate.TaskListener;
//import org.flowable.task.service.delegate.DelegateTask;
//
//import com.ms.workable.flow.api.activity.process.exception.SubmitStartProcessException;
//import com.ms.workable.flow.api.process.ProcessesVariableKey;
//
//import tp.ms.base.rest.orgs.api.OrgsService;
//import tp.ms.base.rest.orgs.entity.MyAdreamOrgelements;
//import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
//import tp.ms.common.bean.exception.ADBusinessException;
//import tp.ms.common.bean.support.context.SpringContextHolder;
//
//public class ProjectManagerTaskHandler implements TaskListener {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 9037184954609876259L;
//
//	@Override
//	public void notify(DelegateTask delegateTask) {
//		
//		Object submitData = delegateTask.getVariable(ProcessesVariableKey.AUDITED_FORM_DATA_OBJECT);
//		if(submitData instanceof MajorAuditBaseVO) {
//			try {
//				Method poject = submitData.getClass().getDeclaredMethod("getPoject");
//				Object value = poject.invoke(submitData);
//				
//				OrgsService orgservice = SpringContextHolder.getBean(OrgsService.class);
//				MyAdreamOrgelements element = orgservice.queryByKey((String) value);
////				element
//				
//				
//			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ADBusinessException e) {
//			}
//			
//		}else {
//			throw new SubmitStartProcessException("提交的数据没有实现 必要的接口，请检查");
//		}
//		
//		
//	}
//
//}
