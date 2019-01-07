package tp.ms.base.rest.ots.staff.api;

import java.util.Map;

import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;

public interface StaffService {
	
	MyAdreamStaff getStaff(String userid, Map<String, Object> result);

	MyAdreamStaff queryByKey(String pkUser);
}
