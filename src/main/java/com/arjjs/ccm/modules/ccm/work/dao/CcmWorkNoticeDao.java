/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告DAO接口
 * @author liang
 * @version 2018-06-12
 */
@MyBatisDao
public interface CcmWorkNoticeDao extends CrudDao<CcmWorkNotice> {

    List<CcmWorkNotice> queryWorkNotice(@Param("id") String id);

    int selectWorkNoticeTotal();

    int selectWorkNoticeTodoCount();

    int updateSatausById(@Param("id") String id);
}