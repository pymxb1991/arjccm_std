/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.syslog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.syslog.entity.SysLog;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;

/**
 * 用户登录查询DAO接口
 *
 * @author liujindan
 * @version 2019-07-09
 */
@MyBatisDao
public interface SysLogDao extends CrudDao<SysLog> {

    List<Count> getLogByDays(@Param(value = "createBy") String userId, @Param(value = "beginDate") String beginDate,
                             @Param(value = "endDate") String endDate);

    List<Count> getDateForDept(String[] nameArr);

}