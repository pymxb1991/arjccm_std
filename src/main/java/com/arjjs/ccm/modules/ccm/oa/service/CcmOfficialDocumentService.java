/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.oa.entity.CcmOfficialDocument;
import com.arjjs.ccm.modules.oa.entity.TestAudit;
import com.google.common.collect.Maps;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.utils.ActUtils;
import com.arjjs.ccm.modules.ccm.oa.dao.CcmOfficialDocumentDao;

/**
 * 公文Service
 * @author pengjianqiang
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly = true)
public class CcmOfficialDocumentService extends CrudService<CcmOfficialDocumentDao, CcmOfficialDocument> {

	@Autowired
	private ActTaskService actTaskService;
	
	@Autowired
	private CcmOfficialDocumentDao ccmOfficialDocumentDao;

	public CcmOfficialDocument getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	
	public CcmOfficialDocument get(String id) {
		return super.get(id);
	}
	
	public List<CcmOfficialDocument> findList(CcmOfficialDocument ccmOfficialDocument) {
		return super.findList(ccmOfficialDocument);
	}
	
	public Page<CcmOfficialDocument> findPage(Page<CcmOfficialDocument> page, CcmOfficialDocument ccmOfficialDocument) {
		return super.findPage(page, ccmOfficialDocument);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOfficialDocument ccmOfficialDocument) {
//		super.save(ccmOfficialDocument);
		

		// 申请发起
		if (StringUtils.isBlank(ccmOfficialDocument.getId())){
			ccmOfficialDocument.preInsert();
			dao.insert(ccmOfficialDocument);
			
			// 启动流程
			String procInsId = actTaskService.startProcess(ActUtils.PD_OFFICIAL_DOCUMENT[0], ActUtils.PD_OFFICIAL_DOCUMENT[1], ccmOfficialDocument.getId(), ccmOfficialDocument.getSubject(), ccmOfficialDocument.getCurrentUser().getLoginName());
			ccmOfficialDocument.setProcInsId(procInsId);
			ccmOfficialDocumentDao.updateProcInsId(ccmOfficialDocument);
		}
		
		// 重新编辑申请		
		else{
			if(ccmOfficialDocument.getCurrentUser() == null){
				ccmOfficialDocument.preUpdate();
			}
			ccmOfficialDocument.setProcInsId(ccmOfficialDocument.getAct().getProcInsId());
			dao.update(ccmOfficialDocument);

			ccmOfficialDocument.getAct().setComment(("yes".equals(ccmOfficialDocument.getAct().getFlag())?"[重申] ":"[销毁] ")+ccmOfficialDocument.getAct().getComment());
			
			// 完成流程任务
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(ccmOfficialDocument.getAct().getFlag())? "1" : "0");
			actTaskService.complete(ccmOfficialDocument.getAct().getTaskId(), ccmOfficialDocument.getAct().getProcInsId(), ccmOfficialDocument.getAct().getComment(), ccmOfficialDocument.getContent(), vars);
		}
		
	}
	

	/**
	 * 审批保存
	 * @param ccmOfficialDocument
	 */
	@Transactional(readOnly = false)
	public void auditSave(CcmOfficialDocument ccmOfficialDocument) {
		
		// 设置意见
		ccmOfficialDocument.getAct().setComment(("yes".equals(ccmOfficialDocument.getAct().getFlag())?"[同意] ":"[驳回] ") + ccmOfficialDocument.getAct().getComment());
		
		ccmOfficialDocument.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = ccmOfficialDocument.getAct().getTaskDefKey();

		// 审核环节
		if ("app1".equals(taskDefKey)){
			
		}
		else if ("app2".equals(taskDefKey)){
			
		}
		
		// 未知环节，直接返回
		else{
			return;
		}
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(ccmOfficialDocument.getAct().getFlag())? "1" : "0");
		actTaskService.complete(ccmOfficialDocument.getAct().getTaskId(), ccmOfficialDocument.getAct().getProcInsId(), ccmOfficialDocument.getAct().getComment(), vars);

	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOfficialDocument ccmOfficialDocument) {
		super.delete(ccmOfficialDocument);
	}
	
}