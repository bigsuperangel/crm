package com.crm.model;

import org.springframework.util.StringUtils;

import com.crm.common.utils.JsonUtil;

/**
 * 合作实体类
 * 
 * @author wukh
 * @2015-1-28
 */
public class Cooperation extends BaseModel {

	public static final int TYPE_ACROSS_AREA = 0;
	public static final int TYPE_ACROSS_BIG_AREA = 1;

	public static final int STATUS_NEW = 0;
	public static final int STATUS_SUBMIT_AUDIT = 1;
	public static final int STATUS_TARGET_AUDIT = 2;
	public static final int STATUS_SUCCESS = 9;
	public static final int STATUS_FAIL = -1;

	private int id;
	private int cooperatorType; // 合作类型: 0-跨区域合作 1-跨大区合作
	private Customer customer; // 合作的客户
	private User submitor; // 发起合作的员工
	private User receiver; // 接收合作的员工
	private int status; // 合作状态 新增0, 大区经理审批1, 对方大区经理审批2(跨区合作才会有), 合作成功9, 合作失败-1
	private Dept submitDept; // 发起区域
	private Dept targetDept; // 目标区域
	private String cdate; // 创建时间
	private String opTime; // 操作时间
	private int isValid; // 是否可用. 0不可用, 1可用

	@Override
	public String getJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id:" + id + ",cooperatorType:" + cooperatorType
				+ ",customerId:" + customer.getId() + ",customerName:'"
				+ customer.getCustomerName() + "',submitorId:"
				+ submitor.getId() + ",submitor:'" + submitor.getRealName()
				+ "',status:" + status + ",cdate:'" + cdate);
		if (!StringUtils.isEmpty(opTime)) {
			sb.append("',opTime:'" + opTime + "'");
		}
		if (submitDept != null) {
			sb.append(",submitDeptId:" + submitDept.getId() + ",submitDept:'"
					+ submitDept.getName() + "'");
		}
		if (targetDept != null) {
			sb.append(",targetDeptId:" + targetDept.getId() + ",targetDept:'"
					+ targetDept.getName() + "'");
		}
		if (receiver != null) {
			sb.append(",receiverId:" + receiver.getId() + ",receiver:'"
					+ receiver.getRealName() + "'");
		}
		sb.append("}");
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCooperatorType() {
		return cooperatorType;
	}

	public void setCooperatorType(int cooperatorType) {
		this.cooperatorType = cooperatorType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getSubmitor() {
		return submitor;
	}

	public void setSubmitor(User submitor) {
		this.submitor = submitor;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Dept getSubmitDept() {
		return submitDept;
	}

	public void setSubmitDept(Dept submitDept) {
		this.submitDept = submitDept;
	}

	public Dept getTargetDept() {
		return targetDept;
	}

	public void setTargetDept(Dept targetDept) {
		this.targetDept = targetDept;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
