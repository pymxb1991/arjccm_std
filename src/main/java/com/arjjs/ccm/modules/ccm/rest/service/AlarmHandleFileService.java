package com.arjjs.ccm.modules.ccm.rest.service;

import com.arjjs.ccm.modules.ccm.rest.dao.AlarmHandleFileDao;
import com.arjjs.ccm.modules.ccm.rest.entity.AlarmHandleFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处警文件
 */
@Service
@Transactional(readOnly = true)
public class AlarmHandleFileService {

	@Autowired
	private AlarmHandleFileDao alarmHandleFileDao;

	@Transactional(readOnly = false)
	public int insertAlarmHandleFile(AlarmHandleFile alarmHandleFile) {
		return alarmHandleFileDao.insertAlarmHandleFile(alarmHandleFile);
	}
}
