/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.cms.dao.CmsBbsArticleDao;
import com.arjjs.ccm.modules.cms.entity.BbsArticleInfo;
import com.arjjs.ccm.modules.cms.entity.CmsBbsArticle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 网上论坛帖子Service
 * @author maoxb
 * @version 2019-08-01
 */
@Service
@Transactional(readOnly = true)
public class CmsBbsArticleService extends CrudService<CmsBbsArticleDao, CmsBbsArticle> {

	public CmsBbsArticle get(String id) {
		return super.get(id);
	}
	
	public List<CmsBbsArticle> findList(CmsBbsArticle cmsBbsArticle) {
		return super.findList(cmsBbsArticle);
	}
	
	public Page<CmsBbsArticle> findPage(Page<CmsBbsArticle> page, CmsBbsArticle cmsBbsArticle) {

		Page<CmsBbsArticle> resultPage = super.findPage(page, cmsBbsArticle);
		List<CmsBbsArticle> list = resultPage.getList();
		List<CmsBbsArticle> list2 = new ArrayList<>();
		for (CmsBbsArticle bbsArticleInfo:list ) {
			if (StringUtils.isBlank(bbsArticleInfo.getCommentNum())){
				bbsArticleInfo.setCommentNum("0");
			}	if (StringUtils.isBlank(bbsArticleInfo.getHotMun())){
				bbsArticleInfo.setHotMun("0");
			}	if (StringUtils.isBlank(bbsArticleInfo.getLikeNum())){
				bbsArticleInfo.setLikeNum("0");
			}	if (StringUtils.isBlank(bbsArticleInfo.getViewNum())){
				bbsArticleInfo.setViewNum("0");
			}
			list2.add(bbsArticleInfo);
		}
		resultPage.setList(list2);
		return super.findPage(resultPage, cmsBbsArticle);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsBbsArticle cmsBbsArticle) {
		super.save(cmsBbsArticle);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsBbsArticle cmsBbsArticle) {
		super.delete(cmsBbsArticle);
	}


	public BbsArticleInfo queryArticleCommentInfo(String articleId){

		//设置返回值
		List<BbsArticleInfo> bbsArticleInfos = dao.queryArticleCommentInfo(articleId);
		BbsArticleInfo bbsArticleInfo = bbsArticleInfos.get(0);
		bbsArticleInfo.setCreateDate(bbsArticleInfo.getCreateDate().substring(0,bbsArticleInfo.getCreateDate().lastIndexOf(".")));

		if (StringUtils.isBlank(bbsArticleInfo.getCommentNum())){
			bbsArticleInfo.setCommentNum("0");
		}	if (StringUtils.isBlank(bbsArticleInfo.getHotMun())){
			bbsArticleInfo.setHotMun("0");
		}	if (StringUtils.isBlank(bbsArticleInfo.getLikeNum())){
			bbsArticleInfo.setLikeNum("0");
		}	if (StringUtils.isBlank(bbsArticleInfo.getViewNum())){
			bbsArticleInfo.setViewNum("0");
		}
		return  bbsArticleInfo;
	}
}