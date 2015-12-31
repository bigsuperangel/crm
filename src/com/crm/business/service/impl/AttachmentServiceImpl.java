package com.crm.business.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.crm.business.dao.IAttachmentDao;
import com.crm.business.service.IAttachmentService;
import com.crm.common.utils.DateUtil;
import com.crm.core.base.BaseService;
import com.crm.core.base.CrmException;
import com.crm.model.Attachment;
import com.crm.model.User;

/**
 * 附件服务类
 * 
 * @author wukh
 * @2015-3-2
 */
@Service("attachmentService")
public class AttachmentServiceImpl extends BaseService implements
		IAttachmentService {

	@Autowired
	@Qualifier("attachmentDao")
	private IAttachmentDao attachmentDao;

	@Override
	public void addAttachment(HttpServletRequest request, int ownerId,
			int type, String name, String des, User user) {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 由CommonsMultipartFile继承而来,拥有上面的方法.
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String suffix = file.getOriginalFilename();
					int index = suffix.indexOf(".");
					if (index != -1) {
						suffix = suffix.substring(index);
					}

					String folderPath = "upload/";
					if (type == 1) {
						folderPath = folderPath + "am/"
								+ DateUtil.getFormatedCurrentDate2() + "/";
					} else {
						folderPath = folderPath + "pm/"
								+ DateUtil.getFormatedCurrentDate2() + "/";
					}

					String localPath = request.getSession().getServletContext()
							.getRealPath("/");

					File folder = new File(localPath + folderPath);
					if (!folder.exists()) {
						folder.mkdirs();
					}

					String filePath = folderPath
							+ generateFileName(type, suffix);
					String localFilePath = localPath + filePath;

					File localFile = new File(localFilePath);

					try {

						file.transferTo(localFile);

						Attachment attachment = new Attachment();
						attachment.setDescription(des);
						attachment.setName(name);
						attachment.setPath(localFilePath);
						attachment.setOwnerId(ownerId);
						attachment.setUser(user);
						attachment.setUrl("/" + filePath);
						attachment.setType(type);

						if (type == 1) {
							// 合同的附件只能存在一份
							List<Attachment> attachmentList = queryAttachmentList(
									ownerId, type);
							if (attachmentList != null
									&& attachmentList.size() > 0) {
								// 已有附件, 替换
								Attachment oldAttachment = attachmentList
										.get(0);
								// attachment.setId(oldAttachment.getId());
								attachmentDao.delete(oldAttachment);

								File oldFile = new File(oldAttachment.getPath());
								oldFile.delete();
							}
						}

						attachmentDao.add(attachment);
					} catch (Exception e) {
						e.printStackTrace();
						if (localFile.exists()) {
							localFile.delete();
						}
					}
				}

			}
		}
	}

	@Override
	public void deleteAttachments(int[] ids, User user) throws CrmException {
		if (!user.isUserFinance()) {
			throw new CrmException(CrmException.MSG_NOT_ENOUGH_AUTHORITY);
		}
		for (int id : ids) {
			Attachment attachment = attachmentDao.getObject(id);
			try {
				File localFile = new File(attachment.getPath());
				if (localFile.exists()) {
					localFile.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			attachmentDao.delete(id);
		}
	}

	@Override
	public List<Attachment> queryAttachmentList(int ownerId, int type) {
		String hql = "select a from Attachment a where a.ownerId = " + ownerId
				+ " and a.type = " + type;

		return attachmentDao.getList(hql);
	}

	private synchronized String generateFileName(int type, String suffix) {
		long time = System.currentTimeMillis();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return type + "_" + time + suffix;
	}
}
