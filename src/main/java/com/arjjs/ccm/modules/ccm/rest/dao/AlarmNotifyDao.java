package com.arjjs.ccm.modules.ccm.rest.dao;

import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.rest.entity.AlarmNotify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface AlarmNotifyDao {

	public List<AlarmNotify> queryAlarmNotifyList(@Param("handlePoliceId") String handlePoliceId);

	int selectAlarmNotifyTotal(@Param("userId") String userId);

	int selectAlarmNotifyTodoCount(@Param("userId") String userId);

	int updateSatausByIdAndUserId(@Param("id") String id, @Param("userId") String userId);
}
