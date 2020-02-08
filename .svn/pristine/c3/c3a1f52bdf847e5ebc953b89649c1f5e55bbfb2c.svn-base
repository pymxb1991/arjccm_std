package com.arjjs.ccm.modules.ccm.house.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.modules.ccm.house.dao.CcmHouseEmphasisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmExpireArea;
import com.arjjs.ccm.modules.ccm.house.entity.CcmExpireUser;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseEmphasis;
import com.arjjs.ccm.modules.ccm.house.entity.CcmIntervalPeople;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.modules.sys.dao.DictDao;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.tool.LayUIBean;
import com.arjjs.ccm.tool.Result;
import com.google.common.collect.Lists;

/**
 * 方法描述：重点人员service
 * @author liu
 * @version 2019-4-10
 */
@Service
@Transactional(readOnly = true)
public class CcmHouseEmphasisService {

	@Autowired
	private CcmHouseEmphasisDao ccmHouseEmphasisDao;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private DictDao dictDao;
	
	private static List<Dict> dictList = Collections.synchronizedList(Lists.newArrayList());
	
	public Result findTableName() {
		Result result = new Result();
		List<CcmHouseEmphasis> ccmHouseEmphasisList = Lists.newArrayList();
		String id = "emphasis_people_config";
		SysConfig sysConfig = sysConfigService.get(id);
		String param = sysConfig.getParamStr();
		List<String> paramStr = Arrays.asList(param.split(","));
		for (String str : paramStr) {
			for (Dict dict : dictList) {
				if(str.equals(dict.getValue())) {
					CcmHouseEmphasis houseEmphasis = new CcmHouseEmphasis();
					houseEmphasis.setLabel(dict.getLabel());
					houseEmphasis.setType(dict.getRemarks());
					ccmHouseEmphasisList.add(houseEmphasis);
				}
			}
		}
		if(ccmHouseEmphasisList != null && ccmHouseEmphasisList.size() > 0) {
			result.setContent(ccmHouseEmphasisList);
			result.setCount(ccmHouseEmphasisList.size());
			result.setRet(Result.ERROR_OK);
		}else {
			result.setRet(Result.ERROR_PARAM);
		}
		return result;
	}
	
	public LayUIBean findEmphasisDataByTableName(CcmPeople ccmPeople) {
		LayUIBean result = new LayUIBean();
		List<CcmPeople> peoList = ccmHouseEmphasisDao.findEmphasisDataByTableName(ccmPeople);
		if(peoList != null && peoList.size() > 0) {
			result.setCount(ccmHouseEmphasisDao.count(ccmPeople));
			result.setData(peoList);
			result.setCode(Result.ERROR_OK);
			result.setMsg("");
		}else {
			result.setCode(Result.ERROR_PARAM);
		}
		return result;
	}
	
	public LayUIBean findVisitRecord(CcmLogTail ccmLogTail) {
		LayUIBean result = new LayUIBean();
		List<CcmLogTail> logTailList = ccmHouseEmphasisDao.findVisitRecord(ccmLogTail);
		if(logTailList != null && logTailList.size() > 0) {
			result.setCount(logTailList.size());
			result.setData(logTailList);
			result.setCode(Result.ERROR_OK);
			result.setMsg("");
		}else {
			result.setCode(Result.ERROR_PARAM);
		}
		return result;
	}
	
	public List<CcmIntervalPeople> findExpirePeople(CcmPeople ccmPeople) {
		return ccmHouseEmphasisDao.findExpirePeople(ccmPeople);
	}
	
	public List<CcmExpireArea> findExpireUser() {
		return ccmHouseEmphasisDao.findExpireUser();
	}
	
	public List<CcmExpireUser> findUserByPeople(CcmPeople ccmPeople) {
		return ccmHouseEmphasisDao.findUserByPeople(ccmPeople);
	}
	
	
	public void dictList(){
		Dict dict = new Dict();
		dict.setType("emphasis_people_type");
		dictList = dictDao.findList(dict);
		System.out.println("--------------------"+dictList.size()+"------------------------");
	}
	@Transactional(readOnly = false)
	public void updateIntervalDate(String peopleId,String tableName) {
		CcmPeople ccmPeople = new CcmPeople();
		ccmPeople.setId(peopleId);
		ccmPeople.setTableName(tableName);
		ccmHouseEmphasisDao.updateIntervalDate(ccmPeople);
	}
}
