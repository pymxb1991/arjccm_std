/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmail;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmailRead;
import com.arjjs.ccm.modules.plm.email.service.PlmWorkEmailService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 工作日志Controller
 * 
 * @author arj
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/email/plmWorkEmail")
public class PlmWorkEmailController extends BaseController {

	@Autowired
	private PlmWorkEmailService plmWorkEmailService;

	@ModelAttribute
	public PlmWorkEmail get(@RequestParam(required = false) String id) {
		PlmWorkEmail entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmWorkEmailService.get(id);
			entity = plmWorkEmailService.getRecordList(entity);
		}
		if (entity == null) {
			entity = new PlmWorkEmail();
		}
		return entity;
	}
	/**
	 * 已发送/草稿箱
	 * @param plmWorkEmail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("email:plmWorkEmail:view")
	@RequestMapping(value = { "list", "" })
	public String list(PlmWorkEmail plmWorkEmail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		plmWorkEmail.setCreateBy(UserUtils.getUser());
		Page<PlmWorkEmail> page = plmWorkEmailService.findPage(new Page<PlmWorkEmail>(request, response),
				plmWorkEmail);
		model.addAttribute("page", page);
		model.addAttribute("view", false);
		model.addAttribute("status", plmWorkEmail.getStatus());
		return "plm/email/plmWorkEmailList";
	}
	/**
	 * 写信
	 * @param plmWorkEmail
	 * @param model
	 * @return
	 */
	@RequiresPermissions("email:plmWorkEmail:view")
	@RequestMapping(value = "form")
	public String form(PlmWorkEmail plmWorkEmail, Model model,Boolean reedit) {
		plmWorkEmail.setIsC(plmWorkEmail.getPlmWorkEmailCReadList().size()>0 ? true : false);
		plmWorkEmail.setIsM(plmWorkEmail.getPlmWorkEmailMReadList().size()>0 ? true : false );
		//若为重新编辑
		if(reedit!=null && reedit){
			plmWorkEmail.setId("");	//修改为一条新纪录
			plmWorkEmail.setStatus("1");	//状态改为未提交
		}
		model.addAttribute("plmWorkEmail", plmWorkEmail);
		return "plm/email/plmWorkEmailAddForm";
	}
	
	
	/**
	 * 回复
	 * @param plmWorkEmail
	 * @param model
	 * @return
	 */
	@RequiresPermissions("email:plmWorkEmail:view")
	@RequestMapping(value = "reply")
	public String reply(PlmWorkEmail plmWorkEmail, Model model,Boolean all,Boolean repeat) {
		PlmWorkEmail plmWorkEmailReply = new PlmWorkEmail();
		//1、如果不是转发，添加回复人
		if(repeat==null){
			PlmWorkEmailRead r = new PlmWorkEmailRead();
			r.setUser(plmWorkEmail.getCreateBy());
			plmWorkEmailReply.getPlmWorkEmailSReadList().add(r);
		}
		//2、添加回复内容模板
		StringBuffer contentReply = new StringBuffer();
		//留白
		contentReply.append("<p>&nbsp;</p><p>&nbsp;</p>");	
		//分割线
		contentReply.append("<p>------------------&nbsp;原始邮件&nbsp;------------------</p>");	
		//盛放上一封邮件基础信息div
		contentReply.append("<div style=\"line-height: 1.5; color: rgb(0, 0, 0); font-family: Verdana; "
				+ "white-space: normal; font-size: 12px; background: rgb(239, 239, 239); padding: 8px;\">");	
		//发件人信息
		contentReply.append("<div style=\"line-height: 1.5;\"><b>发件人:</b>&nbsp;"+plmWorkEmail.getCreateBy().getName()+"</div>");	
		//发送时间
		contentReply.append("<div style=\"line-height: 1.5;\"><b>发送时间:</b>&nbsp;"+DateUtils.formatDateTime(plmWorkEmail.getUpdateDate())+"</div>");
		//收件人信息
		contentReply.append("<div style=\"line-height: 1.5;\"><b>收件人:</b>&nbsp;"+plmWorkEmail.getPlmWorkEmailSReadNames()+"</div>");	
		//抄送人信息
		if(plmWorkEmail.getPlmWorkEmailCReadList().size()>0){
			contentReply.append("<div style=\"line-height: 1.5;\"><b>抄送人:</b>&nbsp;"+plmWorkEmail.getPlmWorkEmailCReadNames()+"</div>");	
		}
		//主题
		contentReply.append("<div style=\"line-height: 1.5;\"><b>主题:</b>&nbsp;"+plmWorkEmail.getTitle()+"</div></div>");
		//原邮件内容
		contentReply.append(plmWorkEmail.getContent());
		plmWorkEmailReply.setContent(contentReply.toString());
		
		//3、添加主题
		StringBuffer titleReply = new StringBuffer();
		if(repeat!=null && repeat){
			titleReply.append("【转发】");
		}else if(all!=null && all){
			titleReply.append("【回复全部】");
		}else{
			titleReply.append("【回复】");
		}
		titleReply.append(plmWorkEmail.getTitle());
		plmWorkEmailReply.setTitle(titleReply.toString());
		
		//4、如果回复全部
		if(all!=null && all){
			//添加抄送人,排除自己
			for (PlmWorkEmailRead plmWorkEmailRead : plmWorkEmail.getPlmWorkEmailCReadList()) {
				if(!plmWorkEmailRead.getUser().getId().equals(UserUtils.getUser().getId())){
					plmWorkEmailReply.getPlmWorkEmailCReadList().add(plmWorkEmailRead);
				}
			}
			//添加发送人，排除自己和发件人
			for (PlmWorkEmailRead plmWorkEmailRead : plmWorkEmail.getPlmWorkEmailSReadList()) {
				if(!plmWorkEmailRead.getUser().getId().equals(UserUtils.getUser().getId()) 
						&& !plmWorkEmailRead.getUser().getId().equals(plmWorkEmail.getCreateBy().getId())){
					plmWorkEmailReply.getPlmWorkEmailSReadList().add(plmWorkEmailRead);
				}
			}
		}
		
		plmWorkEmailReply.setIsC(plmWorkEmailReply.getPlmWorkEmailCReadList().size()>0 ? true : false);
		plmWorkEmailReply.setIsM(plmWorkEmailReply.getPlmWorkEmailMReadList().size()>0 ? true : false );
		model.addAttribute("plmWorkEmail", plmWorkEmailReply);
		return "plm/email/plmWorkEmailAddForm";
	}
	
	/**
	 * 保存到草稿箱/发送
	 * @param plmWorkEmail
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("email:plmWorkEmail:edit")
	@RequestMapping(value = "save")
	public String save(PlmWorkEmail plmWorkEmail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmWorkEmail)) {
			return form(plmWorkEmail, model, null);
		}
		plmWorkEmailService.save(plmWorkEmail);
		if("1".equals(plmWorkEmail.getStatus())){
			addMessage(redirectAttributes, "保存草稿箱成功");
			return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/succeed?caogao=true&id="+plmWorkEmail.getId();
		}else{
			addMessage(redirectAttributes, "发送邮件成功");
			return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/succeed?id="+plmWorkEmail.getId();
		}
		
	}
	/**
	 * 邮件草稿/发送成功
	 * @param plmWorkEmail
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("email:plmWorkEmail:edit")
	@RequestMapping(value = "succeed")
	public String succeed(PlmWorkEmail plmWorkEmail, Model model,Boolean caogao) {
		model.addAttribute("plmWorkEmail", plmWorkEmail);
		if(caogao!=null && caogao){
			return "plm/email/plmWorkEmailcaogao";
		}
		return "plm/email/plmWorkEmailSucceed";
	}

	/**
	 * 自己所写邮件修改星标
	 * @param plmWorkEmail
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ={"star"})
	public PlmWorkEmail star(PlmWorkEmail plmWorkEmail) {
		plmWorkEmailService.star(plmWorkEmail);
		return plmWorkEmail;
	}	
	
	/**
	 * 自己所写邮件删除
	 * @param plmWorkEmail
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("email:plmWorkEmail:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmWorkEmail plmWorkEmail, RedirectAttributes redirectAttributes,Boolean view) {
		plmWorkEmailService.delete(plmWorkEmail);
		addMessage(redirectAttributes, "删除邮件成功");
		if(view!=null && view){
			return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/self?readStatus=1&view=true";
		}
		return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/?repage&status="+plmWorkEmail.getStatus();
	}
	/**
	 * 自己所写邮件彻底删除
	 * @param plmWorkEmail
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete2")
	public String delete2(PlmWorkEmail plmWorkEmail, RedirectAttributes redirectAttributes) {
		plmWorkEmail.setDelFlag("2");
		plmWorkEmailService.delete2(plmWorkEmail);
		addMessage(redirectAttributes, "彻底删除成功");
		return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/self?readStatus=2";
	}
	/**
	 * 自己所写已删除邮件恢复
	 * @param plmWorkEmail
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "recover")
	public String recover(PlmWorkEmail plmWorkEmail, RedirectAttributes redirectAttributes) {
		plmWorkEmail.setDelFlag("0");
		plmWorkEmailService.delete2(plmWorkEmail);
		addMessage(redirectAttributes, "邮件还原成功");
		return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/self?readStatus=2";
	}	
	/**
	 * 收件箱/已删除/星标邮件
	 */
	@RequestMapping(value = "self")
	public String selfList(PlmWorkEmail plmWorkEmail, HttpServletRequest request, HttpServletResponse response,Boolean view,
			Model model) {
		model.addAttribute("view", view);
		Page<PlmWorkEmail> page = null;
		//是未删除邮件
		if("1".equals(plmWorkEmail.getReadStatus())){
			plmWorkEmail.setSelf(true);
			//是星标邮件
			if(view!=null && view){
				plmWorkEmail.setIsStar("1");
				page = plmWorkEmailService.findStarPage(new Page<PlmWorkEmail>(request, response),plmWorkEmail);
			}else{
				page = plmWorkEmailService.findPage(new Page<PlmWorkEmail>(request, response),plmWorkEmail);
				//查询收件箱总数、未读数量
				PlmWorkEmail plmWorkEmailNew = new PlmWorkEmail();
				plmWorkEmailNew.setCurrentUser(UserUtils.getUser());
				List<Integer> countlist = plmWorkEmailService.findReceiveCount(plmWorkEmailNew);
				model.addAttribute("unReaadNum", countlist.get(1));
				model.addAttribute("totalNum", countlist.get(0));
			}
		}
		if("2".equals(plmWorkEmail.getReadStatus())){
			page = plmWorkEmailService.findDefPage(new Page<PlmWorkEmail>(request, response),plmWorkEmail);
		}
		model.addAttribute("readStatus", plmWorkEmail.getReadStatus());
		model.addAttribute("page", page);
		return "plm/email/plmWorkEmailReadList";
	}

	/**
	 * 收件箱邮件修改星标
	 */
	@ResponseBody
	@RequestMapping(value ={"self/star"})
	public PlmWorkEmailRead selfStar(PlmWorkEmailRead plmWorkEmailRead) {
		plmWorkEmailRead.setUser(UserUtils.getUser());
		plmWorkEmailService.selfStar(plmWorkEmailRead);
		return plmWorkEmailRead;
	}		
	/**
	 * 收件箱邮件删除
	 */
	@RequestMapping(value = "self/delete")
	public String selfDelete(PlmWorkEmailRead plmWorkEmailRead,RedirectAttributes redirectAttributes,Boolean fromForm,Boolean view) {
		plmWorkEmailRead.setUser(UserUtils.getUser());
		plmWorkEmailRead.setStatus("2");
		plmWorkEmailService.selfDelete(plmWorkEmailRead);
		addMessage(redirectAttributes, "删除邮件成功");
		if(fromForm!=null && fromForm){
			return "plm/email/plmWorkEmailDelete";
		}
		return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/self?readStatus=1&view="+view.toString();
	}
	/**
	 * 收件箱邮件彻底删除
	 */
	@RequestMapping(value = "self/delete2")
	public String selfDelete2(PlmWorkEmailRead plmWorkEmailRead,RedirectAttributes redirectAttributes,Boolean fromForm) {
		plmWorkEmailRead.setUser(UserUtils.getUser());
		plmWorkEmailRead.setStatus("3");
		plmWorkEmailService.selfDelete(plmWorkEmailRead);
		addMessage(redirectAttributes, "彻底删除邮件成功");
		if(fromForm!=null && fromForm){
			return "plm/email/plmWorkEmailDelete2";
		}
		return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/self?readStatus=2";
	}
	/**
	 * 收件箱已删除邮件恢复
	 */
	@RequestMapping(value = "self/recover")
	public String recover(PlmWorkEmailRead plmWorkEmailRead,RedirectAttributes redirectAttributes,Boolean fromForm) {
		plmWorkEmailRead.setUser(UserUtils.getUser());
		plmWorkEmailRead.setStatus("1");
		plmWorkEmailService.selfDelete(plmWorkEmailRead);
		addMessage(redirectAttributes, "邮件还原成功");
		if(fromForm!=null && fromForm){
			return "plm/email/plmWorkEmailDelete2";
		}
		return "redirect:" + Global.getAdminPath() + "/email/plmWorkEmail/self?readStatus=2";
	}

    /**
	 * 查看除草稿箱以外的邮件详情
	 */
	@RequestMapping(value = "receive")
	public String receive(PlmWorkEmail plmWorkEmail, Model model,Boolean isDel) {
		plmWorkEmailService.updateReadFlag(plmWorkEmail);
		//判断是否是收件箱邮件
		boolean isSent = true;
		if (StringUtils.isNotBlank(plmWorkEmail.getReadFlag())) {
			isSent = false;
		}
		model.addAttribute("isDel", isDel);
		model.addAttribute("isSent", isSent);
		model.addAttribute("plmWorkEmail", plmWorkEmail);
		return "/plm/email/plmWorkEmailReceive";
	}
    /**
	 * 查看阅读状态
	 */	
	@RequestMapping(value = "readStatusList")
	public String readStatusList(PlmWorkEmail plmWorkEmail, Model model) {
		model.addAttribute("plmWorkEmail", plmWorkEmail);
		return "/plm/email/plmWorkEmailReadStatusList";
	}
	/*	*//**
	 * 我的通知列表-数据
	 *//*
	@RequiresPermissions("oa:plmWorkEmail:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<PlmWorkEmail> listData(PlmWorkEmail plmWorkEmail, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		plmWorkEmail.setSelf(true);
		Page<PlmWorkEmail> page = plmWorkEmailService.findPage(new Page<PlmWorkEmail>(request, response),
				plmWorkEmail);
		return page;
	}*/
	
	/**
	 * 查看我的通知-数据
	 *//*
	@RequestMapping(value = "viewData")
	@ResponseBody
	public PlmWorkEmail viewData(PlmWorkEmail plmWorkEmail, Model model) {
		if (StringUtils.isNotBlank(plmWorkEmail.getId())) {
			plmWorkEmailService.updateReadFlag(plmWorkEmail);
			return plmWorkEmail;
		}
		return null;
	}

	*//**
	 * 查看我的通知-发送记录
	 *//*
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public PlmWorkEmail viewRecordData(PlmWorkEmail plmWorkEmail, Model model) {
		if (StringUtils.isNotBlank(plmWorkEmail.getId())) {
			plmWorkEmail = plmWorkEmailService.getRecordList(plmWorkEmail);
			return plmWorkEmail;
		}
		return null;
	}

	*//**
	 * 获取我的通知数目
	 *//*
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(PlmWorkEmail plmWorkEmail, Model model) {
		plmWorkEmail.setSelf(true);
		plmWorkEmail.setReadFlag("0");
		return String.valueOf(plmWorkEmailService.findCount(plmWorkEmail));
	}*/
}