/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.report.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.report.entity.CcmPeopleStat;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.tool.SearchTabMore;
import com.arjjs.ccm.modules.ccm.report.dao.CcmPeopleStatDao;

/**
 * 人口新增统计Service
 * 
 * @author arj
 * @version 2018-01-20
 */
@Service
@Transactional(readOnly = true)
public class CcmPeopleStatService extends CrudService<CcmPeopleStatDao, CcmPeopleStat> {

	@Autowired
	private CcmPeopleStatDao ccmPeopleStatDao;

	
	//实有人口总数、新增
	public EchartType getOnLineRate() {
		return ccmPeopleStatDao.getOnLineRate();
	}
	//本月人口信息getnumPopFollowPop
	public SearchTabMore getnumPopFollowPop() {
		return ccmPeopleStatDao.getnumPopFollowPop();
	}
	
	
	
	
	//
	public CcmPeopleStat get(String id) {
		return super.get(id);
	}

	public List<CcmPeopleStat> findList(CcmPeopleStat ccmPeopleStat) {
		return super.findList(ccmPeopleStat);
	}

	public Page<CcmPeopleStat> findPage(Page<CcmPeopleStat> page, CcmPeopleStat ccmPeopleStat) {
		return super.findPage(page, ccmPeopleStat);
	}

	@Transactional(readOnly = false)
	public void save(CcmPeopleStat ccmPeopleStat) {
		super.save(ccmPeopleStat);
	}

	@Transactional(readOnly = false)
	public void delete(CcmPeopleStat ccmPeopleStat) {
		super.delete(ccmPeopleStat);
	}

	public List<EchartType> findListBySum(String column) {
		return ccmPeopleStatDao.findListBySum(column);
	}

	public List<EchartType> findListByMon(String column) {
		return ccmPeopleStatDao.findListByMon(column);
	}
	
	//首页社区弹框：本月新增人口
	public CcmPeopleStat findMonthAreaStat(CcmPeopleStat ccmPeopleStat) {
		return ccmPeopleStatDao.findMonthAreaStat(ccmPeopleStat);
	}
	
	//流入流出分析
	public List<SearchTab> findFloatOutInArea(CcmPeopleStat ccmPeopleStat) {
		return ccmPeopleStatDao.findFloatOutInArea(ccmPeopleStat);
	}
	
	//户籍人口迁入迁出情况
	public List<SearchTab> getPopInOut(CcmPeopleStat ccmPeopleStat) {
		return ccmPeopleStatDao.getPopInOut(ccmPeopleStat);
	}

	//查询流入流出人口数据 12个月
	public List<EchartType> getcountperson(int num) {
		return ccmPeopleStatDao.getcountperson(num);
	}

	//获取所有类型新增人口数量
	public List<EchartType> findPeopleNewSum() {
		return ccmPeopleStatDao.findPeopleNewSum();
	}
	
	//特殊人群分析(曲梁)
	public Map<String, Object> getSpecialPopDataQL() {
		SearchTabMore obj = ccmPeopleStatDao.getSpecialPopDataQL();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String[] name = new String[10];
		Map<String, Object> map = null;
		int num = 0;
		for(int i=0;i<name.length;i++) {
			map = new HashMap<String, Object>();
			if(num==0) {
				name[i] = "留守人员";
				map.put("name", "留守人员");
				map.put("value", obj.getValue1());
			}else if(num==1) {
				name[i] = "社区矫正人员";
				map.put("name", "社区矫正人员");
				map.put("value", obj.getValue2());
			}else if(num==2) {
				name[i] = "吸毒人员";
				map.put("name", "吸毒人员");
				map.put("value", obj.getValue4());
			}else if(num==3) {
				name[i] = "重点青少年";
				map.put("name", "重点青少年");
				map.put("value", obj.getValue21());
			}else if(num==4) {
				name[i] = "安置帮教人员";
				map.put("name", "安置帮教人员");
				map.put("value", obj.getValue5());
			}else if(num==5) {
				name[i] = "艾滋病危险人员";
				map.put("name", "艾滋病危险人员");
				map.put("value", obj.getValue6());
			}else if(num==6) {
				name[i] = "重点上访人员";
				map.put("name", "重点上访人员");
				map.put("value", obj.getValue11());
			}else if(num==7) {
				name[i] = "涉教人员";
				map.put("name", "涉教人员");
				map.put("value", obj.getValue12());
			}else if(num==8) {
				name[i] = "危险品从业人员";
				map.put("name", "危险品从业人员");
				map.put("value", obj.getValue13());
			}else if(num==9) {
				name[i] = "肇事肇祸等严重精神障碍患者";
				map.put("name", "肇事肇祸等严重精神障碍患者");
				map.put("value", obj.getValue3());
			}else {
				break;
			}
			list.add(map);
			num = num+1;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", name);
		result.put("list", list);
		return result;
	}
}