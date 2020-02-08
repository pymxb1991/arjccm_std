package com.arjjs.ccm.modules.ccm.rest.dao;

import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.rest.entity.AlarmHandleFile;

@MyBatisDao
public interface AlarmHandleFileDao {

	int insertAlarmHandleFile(AlarmHandleFile alarmHandleFile);

}
