/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.payment.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.modules.pbs.payment.entity.PbsPayment;
import com.arjjs.ccm.modules.pbs.payment.service.PbsPaymentService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 党员缴费信息管理Controller
 * @author qixuesong
 * @version 2018-09-05
 */
@Controller
@RequestMapping(value = "${adminPath}/payment/pbsPayment")
public class PbsPaymentController extends BaseController {

	@Autowired
	private PbsPaymentService pbsPaymentService;
	@Autowired
	private PbsPartymemService pbsPartymemService;
	
	@ModelAttribute
	public PbsPayment get(@RequestParam(required=false) String id) {
		PbsPayment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsPaymentService.get(id);
		}
		if (entity == null){
			entity = new PbsPayment();
		}
		return entity;
	}
	
	@RequiresPermissions("payment:pbsPayment:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsPayment pbsPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsPayment> page = pbsPaymentService.findPage(new Page<PbsPayment>(request, response), pbsPayment); 
		model.addAttribute("page", page);
		return "pbs/payment/pbsPaymentList";
	}

	@RequiresPermissions("payment:pbsPayment:view")
	@RequestMapping(value = "form")
	public String form(PbsPayment pbsPayment, Model model) {
		model.addAttribute("pbsPayment", pbsPayment);
		return "pbs/payment/pbsPaymentForm";
	}

	@RequiresPermissions("payment:pbsPayment:edit")
	@RequestMapping(value = "save")
	public String save(PbsPayment pbsPayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsPayment)){
			return form(pbsPayment, model);
		}
		PbsPartymem pbsPartymem = pbsPayment.getPbspartymem();
		pbsPayment.setSPartymemid(pbsPartymem.getId());
		pbsPaymentService.save(pbsPayment);
		addMessage(redirectAttributes, "保存党员缴费信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/payment/pbsPayment/?repage";
	}
	
	@RequiresPermissions("payment:pbsPayment:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsPayment pbsPayment, RedirectAttributes redirectAttributes) {
		pbsPaymentService.delete(pbsPayment);
		addMessage(redirectAttributes, "删除党员缴费信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/payment/pbsPayment/?repage";
	}
	
	/**
	 * 下载导入党员缴费数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(PbsPayment pbsPayment, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "党员缴费数据导入模板.xlsx";
    		List<PbsPayment> list = pbsPaymentService.findList(pbsPayment);
    		new ExportExcel("党员缴费数据", PbsPayment.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/payment/pbsPayment/list?repage";
    }
    /**
	 * 导出党员缴费数据
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PbsPayment pbsPayment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "党员缴费数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PbsPayment> page = pbsPaymentService.findPage(new Page<PbsPayment>(request, response, -1), pbsPayment);
    		new ExportExcel("党员缴费数据", PbsPayment.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出党员缴费数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/payment/pbsPayment/list?repage";
    }

	/**
	 * 导入党员缴费数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/payment/pbsPayment/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PbsPayment> list = ei.getDataList(PbsPayment.class);
			for (PbsPayment pbsPayment : list){
				try{
					//根据名字查询出第一个党员id
					List<PbsPartymem> pbsPartymemList = pbsPartymemService.getListByName(pbsPayment.getPbsmemName());
					PbsPayment pbsPaymentDto = pbsPaymentService.get(pbsPayment.getId());
					User user = UserUtils.getUser();
					Date date = new Date();
					if(pbsPaymentDto == null || null == pbsPaymentDto.getId()) {
						pbsPayment.setsPartymemid(pbsPartymemList.get(0).getId());
						pbsPayment.setCreateBy(user);
						pbsPayment.setCreateDate(date);
						pbsPayment.setUpdateBy(user);
						pbsPayment.setUpdateDate(date);
						pbsPaymentService.insert(pbsPayment);
					} else {
						pbsPayment.setsPartymemid(pbsPartymemList.get(0).getId());
						pbsPayment.setUpdateBy(user);
						pbsPayment.setUpdateDate(date);
						pbsPaymentService.update(pbsPayment);
					}
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>党员名 "+pbsPayment.getPbsmemName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>党员名  "+pbsPayment.getPbsmemName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条缴费记录，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条缴费记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入党员缴费数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/payment/pbsPayment/list?repage";
    }
}