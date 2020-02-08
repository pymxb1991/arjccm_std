/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.web.front;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.cms.entity.*;
import com.arjjs.ccm.modules.cms.service.CcmFontUserService;
import com.arjjs.ccm.modules.cms.service.CmsBbsArticleService;
import com.arjjs.ccm.modules.cms.service.CmsBbsCommentService;
import com.arjjs.ccm.modules.cms.utils.CmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * 网上论坛Controller
 * @author admin001
 * @version 2013-3-15
 */
@Controller
@RequestMapping(value = "${frontPath}/bbsArticle")
public class FrontBBSArticleController extends BaseController{
	
	@Autowired
	private CmsBbsArticleService cmsBbsArticleService;
	
	@Autowired
	private CmsBbsCommentService cmsBbsCommentService;

	@Autowired
	private CcmFontUserService ccmFontUserService;

	/**
	 * 获取帖子列表 
	 */
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String getBBsArticle(@RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="30") Integer pageSize, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		
		Page<CmsBbsArticle> page = new Page<CmsBbsArticle>(pageNo, pageSize);
		CmsBbsArticle article = new CmsBbsArticle();
		article.setReviewFlag("1");//0:未审核 1：审核
		page = cmsBbsArticleService.findPage(page, article);
		model.addAttribute("page", page);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontBBSArticle";
	}
	/**
	 * 获取帖子详情，
	 * @param cmsBbsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(CmsBbsArticle cmsBbsArticle, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		BbsArticleInfo bbsArticleInfos = cmsBbsArticleService.queryArticleCommentInfo(cmsBbsArticle.getId());

		//设置阅读量
		CmsBbsArticle poArticle = cmsBbsArticleService.get(cmsBbsArticle.getId());
		int viewNum = 0;
		if(StringUtils.isNotBlank(poArticle.getViewNum())){
			viewNum = Integer.parseInt(poArticle.getViewNum()) +1;
		}else{
			viewNum += 1;
		}
		poArticle.setViewNum(viewNum + "");
		cmsBbsArticleService.save(poArticle);

		model.addAttribute("cmsBbsArticle", bbsArticleInfos);


		return "modules/cms/front/themes/"+site.getTheme()+"/frontBBSComment";
	}
	/**
	 * 回帖
	 * @param cmsBbsComment
	 */
	@RequestMapping(value = "saveComment" ,method=RequestMethod.POST)
	@ResponseBody
	public String saveComment(CmsBbsComment cmsBbsComment) {
		cmsBbsComment.setComTime(new Date());
		CmsBbsArticle poArticle = cmsBbsArticleService.get(cmsBbsComment.getArticleId());
		CcmFontUser ccmFontUser = ccmFontUserService.get(cmsBbsComment.getFontUserId());
		if (ccmFontUser!=null && poArticle!=null){

			int commentNum = 0;
			if(StringUtils.isNotBlank(poArticle.getCommentNum())){
				commentNum = Integer.parseInt(poArticle.getCommentNum()) +1;
			}else{
				commentNum += 1;
			}
			poArticle.setCommentNum(commentNum + "");
			poArticle.setEndCommentName(ccmFontUser.getName());
			poArticle.setUpdateDate(new Date());
			cmsBbsArticleService.save(poArticle);
		}
		cmsBbsCommentService.save(cmsBbsComment);
		return "200";
	}
	/**
	 * 网上论坛-保存评论信息
	 *
	 */
	@RequestMapping(value = "sendArticle", method=RequestMethod.POST)
	public String sendArticle(CmsBbsArticle cmsBbsArticle,Model model, RedirectAttributes redirectAttributes) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		cmsBbsArticle.setReviewFlag("0");//0:未审核 1：审核
		cmsBbsArticle.setDelFlag(cmsBbsArticle.DEL_FLAG_NORMAL);
		cmsBbsArticleService.save(cmsBbsArticle);
		addMessage(redirectAttributes, "添加评论成功");
		return "modules/cms/front/themes/"+site.getTheme()+"/frontBBSArticle";
	}
	
}
