package tp.ms.base.rest.process.api;

import java.util.List;

import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformationExample;
import tp.ms.base.rest.resource.service.IChildService;

public interface ProcessInformationService extends IChildService<MsWorkableFlowProcessInformation, MsWorkableFlowProcessInformationExample>{

	List<MsWorkableFlowProcessInformation> queryByProcessId(String processId);

	List<MsWorkableFlowProcessInformation> queryHandledProcessOnOwn();

}
