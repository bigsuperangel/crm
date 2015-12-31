package com.crm.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.crm.common.utils.JsonUtil;

public class Dept extends BaseModel {
	private int id;
	private String name;
	private Dept parent;
	private String code;
	private String cdate;
	private int isValid;
	private List<Dept> children;
	private boolean expanded;
	private boolean leaf = true;

	public String getComboboxJson() {
		String json = "{id:" + id + ",name:'" + name + "'";
		if (parent != null) {
			json += ",parentName:'" + parent.getName() + "',parentId:"
					+ parent.getId();
		}
		json += "}";
		return json;
	}

	public static String getJson(List<Dept> list) {
		Dept d = new Dept();
		d.setId(1);
		d.setName("所有部门");
		d.setExpanded(true);
		getChildren(d, list);

		JsonUtil ju = new JsonUtil();
		ju.setFilter(new String[] { "parent" });
		ju.setField(new String[][] { { "name", "text" } });
		return ju.getJson(d);
	}

	public static String getComboboxJson(List<Dept> list) {
		Dept d = new Dept();
		d.setId(1);
		d.setName("所有部门");
		d.setExpanded(true);
		getChildren(d, list);

		JsonUtil ju = new JsonUtil();
		ju.setFilter(new String[] { "parent" });
		ju.setField(new String[][] { { "name", "text" } });
		return ju.getJson(d.getChildren());
	}

	public static void getChildren(Dept dept, List<Dept> list) {
		for (int i = 0; i < list.size(); i++) {
			Dept d = (Dept) list.get(i);
			if (d.getParent().getId() == dept.getId()) {
				if (dept.getChildren() == null) {
					List<Dept> children = new ArrayList<Dept>();
					children.add(d);
					dept.setChildren(children);
					getChildren(d, list);
				} else {
					dept.getChildren().add(d);
					getChildren(d, list);
				}
			}
		}
	}

	public String getJson() {
		return "{id:" + id + ",text:'" + name + "',expanded: true,leaf:" + leaf
				+ "}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getParent() {
		return this.parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	public List<Dept> getChildren() {
		return children;
	}

	public void setChildren(List<Dept> children) {
		this.children = children;
		if (this.children.size() > 0) {
			leaf = false;
		}
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 是否是对方的子区域
	 * 
	 * @param dept
	 * @return
	 */
	public boolean isChildOf(Dept dept) {
		if (dept == null || StringUtils.isEmpty(dept.getCode())) {
			return false;
		}
		return code.startsWith(dept.getCode());
	}
	
	/**
	 * 获取大区的编码
	 * @return
	 */
	public String getBigAreaCode(){
		if(code.length() <= 3){
			return code;
		}else{
			return code.substring(0,3);
		}
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
