package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.Encodes;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.utils.ActUtils;
import com.arjjs.ccm.modules.ccm.oa.entity.CcmOfficialDocument;
import com.arjjs.ccm.modules.ccm.oa.service.CcmOfficialDocumentService;
import com.arjjs.ccm.modules.ccm.rest.entity.ActForApp;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.CommUtilRest;
import com.google.common.collect.Lists;
import com.google.zxing.Result;

import groovyjarjarantlr.ActionTransInfo;

/**
 * 社区服务
 * 
 * @author arj
 * @version 2018-03-12
 */
@Controller
@RequestMapping(value = "${appPath}/rest/oa/ccmOfficialDocument")
public class CcmRestOfficialDocument extends BaseController {

	@Autowired
	private CcmOfficialDocumentService ccmOfficialDocumentService;
	@Autowired
	private ActTaskService actTaskService;

	/**
	 * @see 获取单个公文申请
	 * @param id
	 *            公文申请ID
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-21
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId,ActForApp actForApp, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
		// 获取results
		CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 当前 是否为空
		CcmOfficialDocument ccmOfficialDocument = ccmOfficialDocumentService.get(id);
		//ccmOfficialDocument.setActForApp(actForApp);
		ccmOfficialDocument.getAct().setProcInsId(ccmOfficialDocument.getProcInsId());
		List<ActForApp> histoicFlowListForApp = new ArrayList<>();
		List<Act> histoicFlowList = actTaskService.histoicFlowList(ccmOfficialDocument.getProcInsId(), null,null);
		for (Act act : histoicFlowList) {
			ActForApp actp = new ActForApp();
			actp.setActivityName(act.getHistIns().getActivityName());
			actp.setAssigneeName(act.getAssigneeName());
			actp.setStartTime(act.getHistIns().getStartTime());
			actp.setEndTime(act.getHistIns().getEndTime());
			actp.setComment(act.getComment());
			actp.setDurationTime(act.getDurationTime());
			
			histoicFlowListForApp.add(actp);
		}
		ccmOfficialDocument.setHistoicFlowListForApp(histoicFlowListForApp);
		result.setCode(CcmRestType.OK);
		result.setResult(ccmOfficialDocument);
		return result;
	}

	/**
	 * @see 查询我的公文申请
	 * @param pageNo
	 *            页码
	 * @param pageSiz
	 *            分页大小
	 * @return
	 * @author arj
	 * @version 2018-03-12
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmOfficialDocument ccmOfficialDocument) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		ccmOfficialDocument.setCurrentUser(new User(userId));
		Page<CcmOfficialDocument> page = ccmOfficialDocumentService.findPage(new Page<CcmOfficialDocument>(req, resp),
				(null == ccmOfficialDocument) ? new CcmOfficialDocument() : ccmOfficialDocument);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * @see 修改公文申请
	 * @param ccmOfficialDocument
	 *            公文申请
	 * @author arj
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CcmRestResult modify(String flag,String userId, CcmOfficialDocument ccmOfficialDocument, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		if (StringUtils.isNotBlank(flag)){
			ccmOfficialDocument.getAct().setFlag(flag);
		}
		
		// 如果创建者为空
		User user = (User) req.getSession().getAttribute("user");
		if(StringUtils.isNotBlank(ccmOfficialDocument.getId())){
			CcmOfficialDocument ccmOfficialDocumentDB = ccmOfficialDocumentService.get(ccmOfficialDocument.getId());
			ccmOfficialDocument.setCreateBy(ccmOfficialDocumentDB.getCreateBy());
			ccmOfficialDocument.setCreateDate(ccmOfficialDocumentDB.getCreateDate());
		}else{
			ccmOfficialDocument.setCreateBy(user);
			ccmOfficialDocument.setCreateDate(new Date());
		}
		ccmOfficialDocument.setUpdateBy(user);
		ccmOfficialDocument.setUpdateDate(new Date());
		ccmOfficialDocument.setCurrentUser(user);
		ccmOfficialDocumentService.save(ccmOfficialDocument);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}
	/**
	 * 获取待办列表
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "todo", method = RequestMethod.GET)
	public CcmRestResult todoList(Page<Act> page,String userId,Act act,  HttpServletRequest req,HttpServletResponse resp) throws Exception {
		if(page.getPageSize() == -1){
			page.setPageSize(30);
		}
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		if (result.isReturnFlag()) {
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		List<Act> list = actTaskService.todoList(act,sessionUser.getLoginName());
		if (StringUtils.isNotBlank(act.getTitle())) {
			List<Act> resultList = Lists.newArrayList();
			for(Act actInfo : list) {
				if (actInfo.getVars().getMap().get("title").toString().contains(act.getTitle())) {
					resultList.add(actInfo);
				}
			}
			act.setPage(page);
			page.setList(resultList);
			result.setCode(CcmRestType.OK);
			page.setCount(resultList.size());
			result.setResult(page.getList());
			return result;
		}
		act.setPage(page);
		page.setList(list);
		result.setCode(CcmRestType.OK);
		page.setCount(list.size());
		result.setResult(page.getList());
		return result;
	}
	
	/**
	 * 流程类型字典
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "type", method = RequestMethod.GET)
	public CcmRestResult getActType() {
		CcmRestResult result = new CcmRestResult();
		List<Dict> dicts = DictUtils.getDictList("act_type");
		result.setCode(CcmRestType.OK);
		result.setResult(dicts);
		return result;
	}
	
	/**
	 * 获取已办任务
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "historic")
	public CcmRestResult historicList(Page<Act> page,String userId,Act act, String title,  HttpServletRequest req,HttpServletResponse resp) throws Exception {
		if(page.getPageSize() == -1){
			page.setPageSize(30);
		}
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		if (result.isReturnFlag()) {
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		page = actTaskService.historicList(page, act,sessionUser.getLoginName());
		List<Act>  actList = page.getList();
		Iterator<Act> iter = actList.iterator();
		while(iter.hasNext()){
			Act act2 = iter.next();
			act2.setTaskName(act2.getHistTask().getName());
			//获取流程实例id
			String procInsId=act2.getHistTask().getProcessInstanceId();
			// 获取公文对象
			CcmOfficialDocument ccmOfficialDocument = ccmOfficialDocumentService.getByProcInsId(procInsId);
			if(ccmOfficialDocument != null){
				act2.setBusinessId(ccmOfficialDocument.getId());
			}else{
				 iter.remove();
				 long count = page.getCount();
				 count--;
				 page.setCount(count);
			}
		}
		if (StringUtils.isNotBlank(act.getTitle())) {
			List<Act> resultList = Lists.newArrayList();
			for(Act actInfo : page.getList()) {
				if (actInfo.getVars().getMap().get("title").toString().contains(act.getTitle())) {
					resultList.add(actInfo);
				}
			}
			page.setList(resultList);
			result.setCode(CcmRestType.OK);
			result.setResult(page.getList());
			return result;
		}
		
		/*for (Act act2 : actList) {
			act2.setTaskName(act2.getHistTask().getName());
			//获取流程实例id
			String procInsId=act2.getHistTask().getProcessInstanceId();
			// 获取公文对象
			CcmOfficialDocument ccmOfficialDocument = ccmOfficialDocumentService.getByProcInsId(procInsId);
			if(ccmOfficialDocument != null){
				act2.setBusinessId(ccmOfficialDocument.getId());
			}else{
				actList.remove(act2);
			}			
		}*/
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		return result;
	}
	/**
	 * 获取流程表单
	 * @param taskId	任务ID
	 * @param taskName	任务名称
	 * @param taskDefKey 任务环节标识
	 * @param procInsId 流程实例ID
	 * @param procDefId 流程定义ID
	 */
	@RequestMapping(value = "form")
	public String form(String userId,Act act, HttpServletRequest req,HttpServletResponse resp){
		
		
		// 获取流程XML上的表单KEY
//		String formKey = actTaskService.getFormKey(act.getProcDefId(), act.getTaskDefKey());

		// 获取流程实例对象
		if (act.getProcInsId() != null){
			act.setProcIns(actTaskService.getProcIns(act.getProcInsId()));
		}
		
		StringBuilder formUrl = new StringBuilder();
		
		String formServerUrl = Global.getConfig("activiti.form.server.url");
		if (StringUtils.isBlank(formServerUrl)){
			formUrl.append(Global.getConfig("appPath"));
		}else{
			formUrl.append(formServerUrl);
		}
		
 		formUrl.append("/rest/oa/ccmOfficialDocument/get?");
		formUrl.append("taskId=").append(act.getTaskId() != null ? act.getTaskId() : "");
		formUrl.append("&taskName=").append(act.getTaskName() != null ? Encodes.urlEncode(act.getTaskName()) : "");
		formUrl.append("&taskDefKey=").append(act.getTaskDefKey() != null ? act.getTaskDefKey() : "");
		formUrl.append("&procInsId=").append(act.getProcInsId() != null ? act.getProcInsId() : "");
		formUrl.append("&procDefId=").append(act.getProcDefId() != null ? act.getProcDefId() : "");
		formUrl.append("&status=").append(act.getStatus() != null ? act.getStatus() : "");
		formUrl.append("&id=").append(act.getBusinessId() != null ? act.getBusinessId() : "");
		formUrl.append("&userId=").append(userId);
		
		return "redirect:" + formUrl.toString();
	}
	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveAudit")
	public CcmRestResult saveAudit(String flag,String userId,CcmOfficialDocument ccmOfficialDocument,HttpServletRequest req,HttpServletResponse resp) {
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		
		if (StringUtils.isBlank(flag)){
			result.setCode(CcmRestType.ERROR_NO_EXAMINATION_REPORT);
			return result;
		}
		ccmOfficialDocument.getAct().setFlag(flag);
		ccmOfficialDocumentService.auditSave(ccmOfficialDocument);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}

}