/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.cms.entity.BbsArticleInfo;
import com.arjjs.ccm.modules.cms.entity.CmsBbsArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网上论坛帖子DAO接口
 * @author maoxb
 * @version 2019-08-01
 */
@MyBatisDao
public interface CmsBbsArticleDao extends CrudDao<CmsBbsArticle> {

    List<BbsArticleInfo> queryArticleCommentInfo(@Param("articleId") String articleId);
}