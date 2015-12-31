package com.crm.core.base;

public class CrmException extends ExceptionBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1444000495613769573L;
	public static final String MSG_NOT_ENOUGH_AUTHORITY = "当前用户没有足够的权限";
	public static final String MSG_USER_NOT_SUPERVISOR= "当前用户没有经理角色";
	
	public CrmException(String exception){
		super(exception);
	}
	
}
