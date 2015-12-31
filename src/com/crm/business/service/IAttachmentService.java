package com.crm.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.crm.core.base.CrmException;
import com.crm.model.Attachment;
import com.crm.model.User;

public interface IAttachmentService {

	/**
	 * 上传新增一个附件
	 * 
	 * @param agreementId
	 * @param name
	 * @param des
	 * @param path
	 * @param user
	 */
	public void addAttachment(HttpServletRequest request, int ownerId,
			int type, String name, String des,User user);

	/**
	 * 批量删除附件
	 * 
	 * @param ids
	 * @throws CrmException 
	 */
	public void deleteAttachments(int[] ids, User user) throws CrmException;

	/**
	 * 查询合同的附件列表
	 * 
	 * @param agreementId
	 * @return
	 */
	public List<Attachment> queryAttachmentList(int ownerId, int type);
}
