package com.crm.model;

import java.util.ArrayList;
import java.util.List;

import com.crm.common.utils.JsonUtil;

public class Privilege implements Comparable<Privilege> {
	private int id;
	private String name;
	private String icon;
	private boolean expanded;
	private List<Privilege> children;
	private Privilege parent;
	private int index;
	@SuppressWarnings("unused")
	private boolean leaf = true;
	private String config;
	private boolean done = false;
	
	public String getJson(){
		JsonUtil ju = new JsonUtil();
		ju.setFilter(new String[]{"parent","index"});
		String[][] field = {{"name","text"}};
		ju.setField(field);
		return ju.getJson(this);
	}
	
	public static Privilege getTreeJson(List<Privilege> list){
		Privilege p = new Privilege();
		p.setId(1);
		List<Privilege> children = new ArrayList<Privilege>();
		for(int i = 0;i<list.size(); i++){
			Privilege p2 = list.get(i);
			if(p2.getId() == 1){
				continue;
			}
			if(p2.getParent().getId() == 1){
				children.add(p2);
				getMyChildren(p2, list);
			}
		}
		p.setChildren(children);
//		JsonUtil ju = new JsonUtil();
//		ju.setFilter(new String[]{"parent","index"});
//		String[][] field = {{"name","text"}};
//		ju.setField(field);
		return p;
	}
	
	private static void getMyChildren(Privilege p, List<Privilege> list){
		boolean b = false;
		for(int i = 0;i<list.size();i++){
			Privilege p2 = list.get(i);
			if(p2.getId() == 1){
				continue;
			}
			if(p2.getParent().getId() == p.getId()){
				if(p.getChildren() == null){
					List<Privilege> set = new ArrayList<Privilege>();
					set.add(p2);
					p.setChildren(set);
				}else{
					for(int j = 0; j<p.getChildren().size(); j++){
						if(p.getChildren().get(j).getId() == p2.getId()){
							b = true;
							break;
						}
					}
					if(!b){
						p.getChildren().add(p2);
					}
				}
				getMyChildren(p2, list);
			}
		}
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public List<Privilege> getChildren() {
		return children;
	}
	public void setChildren(List<Privilege> children) {
		this.children = children;
		if(this.children.size()!=0){
			leaf = false;
		}else{
			leaf = true;
		}
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	
	public int compareTo(Privilege p) {
		if(this.index > p.getIndex()){
			return 1;
		}else if(this.index < p.getIndex()){
			return -1;
		}
		return 0;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
}

