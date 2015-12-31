package com.crm.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable {

	private static final long serialVersionUID = 4633017667852408631L;

	private String id;
	private String text;
	private String iconCls;
	private Boolean leaf;
	private Boolean checked;
	private Boolean expanded;
	private List<TreeNode> children;

	public TreeNode() {

	}

	public TreeNode(String id, String text, String iconCls, String leafStr, Boolean checked, Boolean expanded) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.leaf = "Y".equals(leafStr)?true:false;
		this.checked = checked;
		this.expanded = expanded;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public void addChild(TreeNode treeNode) {
		if (this.children == null)
			children = new ArrayList<TreeNode>();
		children.add(treeNode);
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	
}
