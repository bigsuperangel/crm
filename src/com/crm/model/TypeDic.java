package com.crm.model;

public class TypeDic extends BaseModel {
	private int id;
	private int type; //1:服务   2：地区
	private String name;
	
	public String combobox(){
		return "{id:"+id+",name:'"+name+"'}";
	}
	public String getJson() {
		return "{id:"+id+",name:'"+name+"',state:"+type+"}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
