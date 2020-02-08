/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.web;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.message.entity.CcmMessage;
import com.arjjs.ccm.modules.ccm.message.service.CcmMessageService;
import com.arjjs.ccm.modules.ccm.rest.web.CcmRestEvent;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkTiming;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkTimingService;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import com.arjjs.ccm.modules.oa.entity.OaNotifyRecord;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.tool.RabbitMQTools;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知通告Controller
 * @author admin001
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaNotify")
public class OaNotifyController extends BaseController {

	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private CcmWorkTimingService ccmWorkTimingService;
	@Autowired
	private CcmMessageService ccmMessageService;
	
	
	
	@ModelAttribute
	public OaNotify get(@RequestParam(required=false) String id) {
		OaNotify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaNotifyService.get(id);
		}
		if (entity == null){
			entity = new OaNotify();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyList";
	}

	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = "form")
	public String form(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotify = oaNotifyService.getRecordList(oaNotify);
		}
		model.addAttribute("oaNotify", oaNotify);
		return "modules/oa/oaNotifyForm";
	}

	@RequiresPermissions("oa:oaNotify:edit")
	@RequestMapping(value = "save")
	public String save(OaNotify oaNotify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaNotify)){
			return form(oaNotify, model);
		}
		// 如果是修改，则状态为已发布，则不能再进行操作
		if (StringUtils.isNotBlank(oaNotify.getId())){
			OaNotify e = oaNotifyService.get(oaNotify.getId());
			if ("1".equals(e.getStatus())){
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/oa/oaNotify/form?id="+oaNotify.getId();
			}
		}
		oaNotifyService.save(oaNotify);
		if(oaNotify.getStatus().equals("1")){//消息发布
			List<CcmMessage> list = new ArrayList<CcmMessage>();
			List<OaNotifyRecord> oaNotifyRecordList = oaNotify.getOaNotifyRecordList();
			for (OaNotifyRecord oaNotifyRecord : oaNotifyRecordList) {
				CcmMessage ccmMessage = new CcmMessage();
				ccmMessage.preInsert();
				ccmMessage.setType("03");//通知消息

				Date createDate = oaNotify.getUpdateDate();
				String str = "MM-dd HH:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(str);
				ccmMessage.setContent(sdf.format(createDate)+"："+"通知："+oaNotify.getTitle());
				ccmMessage.setReadFlag("0");//未读
				ccmMessage.setObjId(oaNotify.getId());
				ccmMessage.setUserId(oaNotifyRecord.getUser().getId());
				list.add(ccmMessage);

				Map resmap = Maps.newHashMap();
				resmap.put("type","oaNotifyType");
				resmap.put("id",oaNotify.getId());
				resmap.put("name","通知："+oaNotify.getTitle());
				RabbitMQTools.sendMessage(oaNotifyRecord.getUser().getId(), JSONObject.fromObject(resmap).toString());

			}
			//批量添加
			ccmMessageService.insertEventAll(list);
			//发送mq
			CcmRestEvent.sendMessageToMq(list);
		}
		addMessage(redirectAttributes, "保存通知'" + oaNotify.getTitle() + "'成功");
		return "redirect:" + adminPath + "/oa/oaNotify/?repage";
	}
	
	//定时提醒
	@RequestMapping(value = "saveOaNotify")
	public void saveOaNotify() {
		List<CcmWorkTiming> list = new ArrayList<CcmWorkTiming>();
		list = ccmWorkTimingService.findTiming();
		for(CcmWorkTiming l:list){
			OaNotify oaNotify = new OaNotify();
			oaNotify.setContent(l.getDetails());
			oaNotify.setStatus("1");
			oaNotify.setTitle("定时提醒");
			oaNotify.setType("5");
			oaNotify.setReadFlag("0");
			oaNotify.setIsNewRecord(false);
			oaNotify.setCreateBy(l.getUser());
			oaNotify.setCurrentUser(l.getUser());
			oaNotify.setUpdateBy(l.getUser());
			oaNotify.setDelFlag("0");
			
			List<OaNotifyRecord> oaNotifyRecordList = Lists.newArrayList();
			OaNotifyRecord entity = new OaNotifyRecord();
			entity.setId(IdGen.uuid());
			entity.setOaNotify(oaNotify);
			entity.setUser(l.getUser());
			entity.setReadFlag("0");
			entity.setIsNewRecord(false);
			entity.setCreateBy(l.getUser());
			entity.setCurrentUser(l.getUser());
			entity.setUpdateBy(l.getUser());
			entity.setDelFlag("0");
			oaNotifyRecordList.add(entity);
			oaNotify.setOaNotifyRecordList(oaNotifyRecordList);

			oaNotifyService.save(oaNotify);
		}
		
	}
	
	
	
	@RequiresPermissions("oa:oaNotify:edit")
	@RequestMapping(value = "delete")
	public String delete(OaNotify oaNotify, RedirectAttributes redirectAttributes) {
		oaNotifyService.delete(oaNotify);
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:" + adminPath + "/oa/oaNotify/?repage";
	}
	
	/**
	 * 我的通知列表
	 */
	@RequestMapping(value = "self")
	public String selfList(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaNotify.setSelf(true);
		Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify); 
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyListRead";
	}

	/**
	 * 我的通知列表-数据
	 */
	@RequiresPermissions("oa:oaNotify:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<OaNotify> listData(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaNotify.setSelf(true);
		Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
		return page;
	}
	
	/**
	 * 查看我的通知
	 */
	@RequestMapping(value = "view")
	public String view(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotifyService.updateReadFlag(oaNotify);
			oaNotify = oaNotifyService.getRecordList(oaNotify);
			model.addAttribute("oaNotify", oaNotify);
			return "modules/oa/oaNotifyForm";
		}
		return "redirect:" + adminPath + "/oa/oaNotify/self?repage";
	}

	/**
	 * 查看我的通知-只读
	 */
	@RequestMapping(value = "viewRead")
	public String viewRead(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotifyService.updateReadFlag(oaNotify);
			oaNotify = oaNotifyService.getRecordList(oaNotify);
			model.addAttribute("oaNotify", oaNotify);
			return "modules/oa/oaNotifyFormRead";
		}
		return "redirect:" + adminPath + "/oa/oaNotify/self?repage";
	}
	
	
	/**
	 * 查看我的通知-数据
	 */
	@RequestMapping(value = "viewData")
	@ResponseBody
	public OaNotify viewData(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotifyService.updateReadFlag(oaNotify);
			return oaNotify;
		}
		return null;
	}
	
	/**
	 * 查看我的通知-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public OaNotify viewRecordData(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotify = oaNotifyService.getRecordList(oaNotify);
			return oaNotify;
		}
		return null;
	}
	
	/**
	 * 获取我的通知数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(OaNotify oaNotify, Model model) {
		oaNotify.setSelf(true);
		oaNotify.setReadFlag("0");
		return String.valueOf(oaNotifyService.findCount(oaNotify));
	}
	
	//门户公告通知统计
	@RequestMapping(value = "findNotice")
	public String findNotice(HttpServletRequest request, HttpServletResponse response, Model model, OaNotify oaNotify) {
		List<OaNotify> oaNotifyList = oaNotifyService.findNotice(oaNotify);
		List<OaNotify> list = oaNotifyService.count();
		model.addAttribute("list", list);
		model.addAttribute("oaNotifyList", oaNotifyList);
		return "flat/home/bphNoticeList";
	}
}