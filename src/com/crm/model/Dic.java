package com.crm.model;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.crm.common.utils.JsonUtil;

/**
 * 标签字典
 * 
 * @author wukh
 * @2015-2-4
 */
public class Dic extends BaseModel {

	private int id;
	private String name; // 标签名
	private String value; // 标签值
	private String type; // 类型
	private String description; // 描述
	private int sort; // 排序号(升序)
	private User createBy; // 创建者
	private String cdate; // 创建时间
	private User updateBy; // 更新者
	private String udate; // 更新时间
	private String remarks; // 备注
	private int isValid; // 是否可用 0删除 1正常

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public String getUdate() {
		return udate;
	}

	public void setUdate(String udate) {
		this.udate = udate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{id:" + id + ",name:'" + name + "',value:'" + value + "',type:'"+type+"'");
		json.append(",description:'" + description + "',sort:" + sort
				+ ",remarks:'" + remarks + "',isVliad:'" + isValid + "'");
		if (!StringUtils.isEmpty(cdate)) {
			json.append(",cdate:'" + cdate + "'");
		}
		if (!StringUtils.isEmpty(udate)) {
			json.append(",udate:'" + udate + "'");
		}
		if (createBy != null) {
			json.append(",createById:" + createBy.getId() + ",createBy:'"
					+ createBy.getRealName() + "'");
		}
		if (updateBy != null) {
			json.append(",updateById:" + updateBy.getId() + ",updateBy:'"
					+ updateBy.getRealName() + "'");
		}
		json.append("}");
		return json.toString();
	}

	public String combobox() {
		return "{id:" + id + ",name:'" + name + "',value:'" + value + "'}";
	}

}
