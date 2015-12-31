package com.crm.model;

/**
 * 附件
 * @author wukh
 * @2015-2-27
 */
public class Attachment extends BaseModel {

	private int id;		//附件ID
	private User user;			//上传者
	private String path;			//路径
	private String name;			//文件名
	private String description;		//描述
	private int type;				//业务类型. 1-合同  2-款项
	private String url;			//图片链接
	private int ownerId;				//业务归属记录的ID 
	
	@Override
	public String getJson() {
		StringBuilder json = new StringBuilder();
		json.append("{id:"+id);
		json.append(",name:'"+name+"'");
		json.append(",description:'"+description+"'");
		json.append(",url:'"+url+"'");
		json.append("}");
		return json.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	

}
