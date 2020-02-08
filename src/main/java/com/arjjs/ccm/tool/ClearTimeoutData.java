package com.arjjs.ccm.tool;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.modules.flat.realtimeSituation.entity.PeopleData;
import com.arjjs.ccm.modules.flat.rest.service.FlatRestService;

public class ClearTimeoutData {
	public void runAllMethodData() {
		clearUserPointTimeoutData();
	}

	public void clearUserPointTimeoutData() {
		for (Map.Entry<String, PeopleData> entry : FlatRestService.peoPleMap.entrySet()) {
			String timeOccurs = entry.getValue().getTimeOccurs();
			if(StringUtils.isNotBlank(timeOccurs)) {
				Date timeOccurDate = (Date) DateUtils.parseDate(timeOccurs);
				Date now = new Date();
				if ((now.getTime() - timeOccurDate.getTime()) > (1000 * 60 * 1)) {// 超时超过1分钟
					FlatRestService.peoPleMap.remove(entry.getKey());
				}
			}
		}
	}
}
