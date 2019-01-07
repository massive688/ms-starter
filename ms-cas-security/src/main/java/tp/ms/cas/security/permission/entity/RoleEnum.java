package tp.ms.cas.security.permission.entity;
public enum RoleEnum {
	//顺序不能动
	SUPER,			//0
	ADMIN,			//1
	DEV,			//2
	USER,			//3
	OUTSIDE_USER,	//4
	ANONYMOUS;		//5

	public static RoleEnum valueOf(int i) {
		for(RoleEnum role : RoleEnum.values()) {
			if(role.ordinal() == i) {
				return role;
			}
		}
		return ANONYMOUS;
	}

}
