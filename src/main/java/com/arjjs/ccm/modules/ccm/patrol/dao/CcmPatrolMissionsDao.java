/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 巡逻任务DAO接口
 * @author lijiupeng
 * @version 2019-07-05
 */
@MyBatisDao
public interface CcmPatrolMissionsDao extends CrudDao<CcmPatrolMissions> {

    /**
     * 按开始时间和结束时间查找list
     * @param startDate
     * @param endDate
     * @return
     */
    List<CcmPatrolMissions> findListByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 按着id 修改状态
     * @param val 状态值
     * @param id 任务id
     * @return
     */
    int updateStatus(String val,String id);
}