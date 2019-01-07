package tp.ms.base.rest.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.ots.staff.api.StaffService;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;
import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultSupport;
import tp.ms.common.bean.support.context.MsEnvContextHolder;

@RestController
@RequestMapping("/api")
public class UserApiResource {

	@Autowired
	StaffService service;
	
	@GetMapping("/user-staff")
	public Result<MyAdreamStaff> getStaffUser(HttpServletRequest request) {
		MyAdreamStaff msf = (MyAdreamStaff) MsEnvContextHolder.getContext().user();		
		return ResultSupport.ok(msf);
	}
}
