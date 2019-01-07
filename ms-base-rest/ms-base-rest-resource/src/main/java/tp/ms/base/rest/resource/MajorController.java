package tp.ms.base.rest.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.vo.AuditInfoVo;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.http.OperationValue;

@Slf4j
public abstract class MajorController<T extends MajorBaseVO> extends BaseController<T> {

	@PostMapping("examined-submit")
	public Object toExaminedSubmit(@RequestBody T data, @RequestBody OperationValue operation) {
		log.info(data.toString());
		return operation;
	}
	
	@PostMapping("examined")
	public Object toExamined(@RequestBody T data, @RequestBody AuditInfoVo auditInfo) {
		log.info(data.toString());
		return auditInfo;
	}
}
