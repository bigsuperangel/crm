package com.crm.common.tools;

public class StringTool {
	/**
	 * 判断是否为空
	 * @param str
	 * @return　true:非空　false:空
	 */
	public static boolean checkEmpty(String str){
		return (str!=null && !"".equals(str.trim())) ? true :false;
	}
	
	public static void main(String[] args){
		System.out.println(checkEmpty(""));
	}
}
