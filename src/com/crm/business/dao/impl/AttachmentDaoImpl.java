package com.crm.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.crm.business.dao.IAttachmentDao;
import com.crm.common.PageModel;
import com.crm.core.base.BaseDao;
import com.crm.model.Attachment;

@Repository("attachmentDao")
public class AttachmentDaoImpl extends BaseDao<Attachment, Integer> implements IAttachmentDao {
	
}
