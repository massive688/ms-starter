package tp.ms.base.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.process.api.FlowProcessService;
import tp.ms.base.rest.resource.BasisResource;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("api/flow-process")
public class FlowProcessResource extends BasisResource<Object> {

	@Autowired
	FlowProcessService processService;
	
	@PostMapping("task-list")
	public Object toProcessTaskListExamined(@RequestBody Pager pager) throws ADBusinessException {
		return ResultSupport.ok(processService.obtainTask(pager));
	}
	
	
	@GetMapping("picture/{processId}")
	public void toProcessPictureExamined(@PathVariable("processId") String processId) throws ADBusinessException {
		processService.obtainProcessPicture(processId);
	}
	

	@GetMapping("information-list/{processId}")
	public Object toProcessInformationListExamined(@PathVariable("processId") String processId) throws ADBusinessException {
		return ResultSupport.ok(processService.obtainInformation(processId));
	}
}
