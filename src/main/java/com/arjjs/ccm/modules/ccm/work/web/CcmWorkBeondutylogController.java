/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkBeondutylog;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkBeondutylogExport;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkBeondutylogService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 值班日志表Controller
 * @author liang
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/work/ccmWorkBeondutylog")
public class CcmWorkBeondutylogController extends BaseController {

	@Autowired
	private CcmWorkBeondutylogService ccmWorkBeondutylogService;
	
	@ModelAttribute
	public CcmWorkBeondutylog get(@RequestParam(required=false) String id) {
		CcmWorkBeondutylog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkBeondutylogService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkBeondutylog();
		}
		return entity;
	}
	
	@RequiresPermissions("work:ccmWorkBeondutylog:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkBeondutylog ccmWorkBeondutylog, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ccmWorkBeondutylog.getBeginDatas() == null && ccmWorkBeondutylog.getEndDatas() == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -7);
			ccmWorkBeondutylog.setBeginDatas(c.getTime());
			ccmWorkBeondutylog.setEndDatas(new Date());
		}
		if(ccmWorkBeondutylog.getOffice()==null) {
			ccmWorkBeondutylog.setOffice(UserUtils.getUser().getOffice());
		}
		Page<CcmWorkBeondutylog> page = ccmWorkBeondutylogService.findPage(new Page<CcmWorkBeondutylog>(request, response), ccmWorkBeondutylog); 
		model.addAttribute("page", page);
		return "ccm/work/ccmWorkBeondutylogList";
	}
	/**
	 *
	 * 导出
	 */
	@RequiresPermissions("work:ccmWorkBeondutylog:view")
	@RequestMapping(value = { "exportWorkBeondutylog" })
	public void exportWorkBeondutylog(CcmWorkBeondutylog ccmWorkBeondutylog, HttpServletRequest request, HttpServletResponse response,
								   Model model) {
		try {
			String fileName = "值班日志"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmWorkBeondutylogExport> list = ccmWorkBeondutylogService.exportList(ccmWorkBeondutylog);
			new ExportExcel("值班日志", CcmWorkBeondutylogExport.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			System.out.println("导出事件处理数据失败！失败信息：" + e.getMessage());
		}
	}
	@RequiresPermissions("work:ccmWorkBeondutylog:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkBeondutylog ccmWorkBeondutylog, Model model) {
		model.addAttribute("ccmWorkBeondutylog", ccmWorkBeondutylog);
		return "ccm/work/ccmWorkBeondutylogForm";
	}

	@RequiresPermissions("work:ccmWorkBeondutylog:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkBeondutylog ccmWorkBeondutylog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkBeondutylog)){
			return form(ccmWorkBeondutylog, model);
		}
		ccmWorkBeondutylogService.save(ccmWorkBeondutylog);
		addMessage(redirectAttributes, "保存值班日志表成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkBeondutylog/?repage";
	}
	
	@RequiresPermissions("work:ccmWorkBeondutylog:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkBeondutylog ccmWorkBeondutylog, RedirectAttributes redirectAttributes) {
		ccmWorkBeondutylogService.delete(ccmWorkBeondutylog);
		addMessage(redirectAttributes, "删除值班日志表成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkBeondutylog/?repage";
	}

}