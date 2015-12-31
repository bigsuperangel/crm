package com.crm.model;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 客户信息实体
 * 
 * @author wukh
 * @2015-1-19
 */
public class Customer extends BaseModel {
	private int id; // 客户ID
	private String customerName; // 客户名
	private String companyAddress; // 公司地址
	private String brand; // 品牌名称
	private int operateBrand; // 代运营品牌数
	private int status; // 1－本地客户 2－合作客户 3-公海客户
	private User handler; // 所属业务员ID
	private String cdate; // 创建时间
	private int isCooperate; // 是否合作过 0:未合作1:已合作
	private String auditTime; // 审核通过时间, 计算90天释放期限
	private int isSign; // 是否已经签约 0未签约1已签约. 已签约客户不计算期限
	private Dept dept; // 部门(客户归属区域)
	private int audit; // 审核状态.0未审核 1已审核 －1驳回
	private String saleMode; // 销售模式 1直营、2分销、3直营和分销
	private String companyType; // 公司类 1型品牌商、2代运营商
	private String storePlatform; // 开店平台
	// 1淘宝系、2拍拍系、3京东商城、4当当、5卓越、6１号店、7马可波罗、8慧聪、9其他
	private String brandAwareness; // 品牌知名度 1一线、2二线、3三线
	private String companyAwareness; // 公司知名度 1高、2中、3低
	private String customerSource; // 客户来源 1电话营销、2会议营销、3主动联系、4客户推荐、5淘拍档、6其他
	private int isValid; // 删除标识 0删除 1正常
	private User creater; // 创建人
	private String freeTime; // 客户释放时间

	private List<Person> list;

	public String combobox() {
		return "{id:" + id + ",customerName:'" + customerName + "'}";
	}

	public String getJson() {
		StringBuilder json = new StringBuilder();

		json.append("{id:" + id);
		json.append(",customerName:'" + customerName + "'");
		if (!StringUtils.isEmpty(companyAddress)) {
			json.append(",companyAddress:'" + companyAddress + "'");
		}
		json.append(",operateBrand:" + operateBrand);

		if (!StringUtils.isEmpty(brand)) {
			json.append(",brand:'" + brand + "'");
		}
		json.append(",status:" + status);

		if (!StringUtils.isEmpty(cdate)) {
			json.append(",cdate:'" + cdate + "'");
		}
		if (!StringUtils.isEmpty(auditTime)) {
			json.append(",auditTime:'" + auditTime + "'");
		}
		if (!StringUtils.isEmpty(saleMode)) {
			json.append(",saleMode:'" + saleMode + "'");
		}
		if (!StringUtils.isEmpty(companyType)) {
			json.append(",companyType:'" + companyType + "'");
		}
		if (!StringUtils.isEmpty(storePlatform)) {
			json.append(",storePlatform:'" + storePlatform + "'");
		}
		if (!StringUtils.isEmpty(brandAwareness)) {
			json.append(",brandAwareness:'" + brandAwareness + "'");
		}
		if (!StringUtils.isEmpty(companyAwareness)) {
			json.append(",companyAwareness:'" + companyAwareness + "'");
		}
		if (!StringUtils.isEmpty(customerSource)) {
			json.append(",customerSource:'" + customerSource + "'");
		}
		if (!StringUtils.isEmpty(freeTime)) {
			json.append(",freeTime:'" + freeTime + "'");
		}
		json.append(",isCooperate:" + isCooperate);
		json.append(",isSign:" + isSign);
		json.append(",audit:" + audit);
		json.append(",isValid:" + isValid);
		if (handler != null) {
			json.append(",handler:" + handler.getId());
			json.append(",handlerName:'" + handler.getRealName() + "'");
		}
		if (dept != null) {
			json.append(",dept:" + dept.getId());
			json.append(",deptName:'" + dept.getName() + "'");
		}
		if (creater != null) {
			json.append(",creater:" + creater.getId());
			json.append(",createrName:'" + creater.getRealName() + "'");
		}
		json.append("}");

		return json.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public int getOperateBrand() {
		return operateBrand;
	}

	public void setOperateBrand(int operateBrand) {
		this.operateBrand = operateBrand;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getHandler() {
		return handler;
	}

	public void setHandler(User handler) {
		this.handler = handler;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getIsCooperate() {
		return isCooperate;
	}

	public void setIsCooperate(int isCooperate) {
		this.isCooperate = isCooperate;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

	public String getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(String saleMode) {
		this.saleMode = saleMode;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getBrandAwareness() {
		return brandAwareness;
	}

	public void setBrandAwareness(String brandAwareness) {
		this.brandAwareness = brandAwareness;
	}

	public String getCompanyAwareness() {
		return companyAwareness;
	}

	public void setCompanyAwareness(String companyAwareness) {
		this.companyAwareness = companyAwareness;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public List<Person> getList() {
		return list;
	}

	public void setList(List<Person> list) {
		this.list = list;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStorePlatform() {
		return storePlatform;
	}

	public void setStorePlatform(String storePlatform) {
		this.storePlatform = storePlatform;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(String freeTime) {
		this.freeTime = freeTime;
	}

}
