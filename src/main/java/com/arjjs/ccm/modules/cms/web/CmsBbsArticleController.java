/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.cms.entity.CmsBbsArticle;
import com.arjjs.ccm.modules.cms.service.CmsBbsArticleService;
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
import java.util.Date;

/**
 * 网上论坛帖子Controller
 * @author maoxb
 * @version 2019-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/cmsBbsArticle")
public class CmsBbsArticleController extends BaseController {

	@Autowired
	private CmsBbsArticleService cmsBbsArticleService;
	
	@ModelAttribute
	public CmsBbsArticle get(@RequestParam(required=false) String id) {
		CmsBbsArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsBbsArticleService.get(id);
		}
		if (entity == null){
			entity = new CmsBbsArticle();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsBbsArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsBbsArticle cmsBbsArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsBbsArticle> page = cmsBbsArticleService.findPage(new Page<CmsBbsArticle>(request, response), cmsBbsArticle); 
		model.addAttribute("page", page);
		return "modules/cms/cmsBbsArticleList";
	}

	@RequiresPermissions("cms:cmsBbsArticle:view")
	@RequestMapping(value = "form")
	public String form(CmsBbsArticle cmsBbsArticle, Model model) {
		model.addAttribute("cmsBbsArticle", cmsBbsArticle);
		return "modules/cms/cmsBbsArticleForm";
	}

	@RequiresPermissions("cms:cmsBbsArticle:edit")
	@RequestMapping(value = "save")
	public String save(CmsBbsArticle cmsBbsArticle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsBbsArticle)){
			return form(cmsBbsArticle, model);
		}
		cmsBbsArticle.setReviewFlag("1");
		cmsBbsArticle.setReviewDate(new Date());
		cmsBbsArticleService.save(cmsBbsArticle);
		addMessage(redirectAttributes, "审核完毕！");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBbsArticle/?repage";
	}
	
	@RequiresPermissions("cms:cmsBbsArticle:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsBbsArticle cmsBbsArticle, RedirectAttributes redirectAttributes) {
		cmsBbsArticleService.delete(cmsBbsArticle);
		addMessage(redirectAttributes, "删除网上论坛帖子成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBbsArticle/?repage";
	}

	@RequiresPermissions("cms:cmsBbsArticle:edit")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(CmsBbsArticle cmsBbsArticle) {
		cmsBbsArticle.setReviewFlag("1");
		cmsBbsArticle.setReviewDate(new Date());
		cmsBbsArticleService.save(cmsBbsArticle);
		return "200";
	}
//	@RequestMapping(value = "queryArticleCommentInfo")
//	@ResponseBody
//	public List<BbsArticleInfo> queryArticleCommentInfo(String id) {
//
//		List<BbsArticleInfo> bbsArticleInfos = cmsBbsArticleService.queryArticleCommentInfo(id);
//		return bbsArticleInfos;
//	}
}