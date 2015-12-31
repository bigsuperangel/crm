package com.crm.common.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QueryCondition implements Serializable {

	private static final long serialVersionUID = 6321626739264483431L;

	private Integer limit = 20; //默认
	private Integer page;
	private Integer total;
	private Integer start = 0; //默认
	private Map<String,String> params = new HashMap<String,String>();
	
	public boolean containParam(String key){
		return params.containsKey(key);
	}
	
	public String getParam(String key){
		return params.get(key);
	}
	
	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

}
