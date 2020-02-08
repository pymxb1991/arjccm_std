package com.arjjs.ccm.modules.ccm.house.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmExpireArea;
import com.arjjs.ccm.modules.ccm.house.entity.CcmExpireUser;
import com.arjjs.ccm.modules.ccm.house.entity.CcmIntervalPeople;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.sys.entity.Dict;

@MyBatisDao
public interface CcmHouseEmphasisDao {
	List<Dict> findTableNameByDescription(Dict dict);
	List<CcmPeople> findEmphasisDataByTableName(CcmPeople ccmPeople);
	int count(CcmPeople ccmPeople);
	List<CcmLogTail> findVisitRecord(CcmLogTail ccmLogTail);
	List<CcmIntervalPeople> findExpirePeople(CcmPeople ccmPeople);
	List<CcmExpireArea> findExpireUser();
	List<CcmExpireUser> findUserByPeople(CcmPeople ccmPeople);
	void updateIntervalDate(CcmPeople ccmPeople);
}
