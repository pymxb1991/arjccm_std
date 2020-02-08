/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgComPop;
import com.arjjs.ccm.modules.ccm.org.entity.SysArea;
import com.arjjs.ccm.modules.ccm.report.dao.CcmPeopleAmountDao;
import com.arjjs.ccm.modules.ccm.sys.dao.SysConfigDao;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgComPopDao;
import com.arjjs.ccm.modules.ccm.org.dao.SysAreaDao;

/**
 * 公共机构人员Service
 * @author liang
 * @version 2018-05-11
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgComPopService extends CrudService<CcmOrgComPopDao, CcmOrgComPop> {

	@Autowired
	private CcmOrgComPopDao ccmOrgComPopDao;
	
	@Autowired
	private SysConfigDao sysConfigDao;
	
	@Autowired
	private SysAreaDao sysAreaDao;
	
	
	public CcmOrgComPop get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgComPop> findList(CcmOrgComPop ccmOrgComPop) {
		return super.findList(ccmOrgComPop);
	}
	
	public Page<CcmOrgComPop> findPage(Page<CcmOrgComPop> page, CcmOrgComPop ccmOrgComPop) {
		return super.findPage(page, ccmOrgComPop);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgComPop ccmOrgComPop) {
		super.save(ccmOrgComPop);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgComPop ccmOrgComPop) {
		super.delete(ccmOrgComPop);
	}

	//社区民警/辅警
	public int findPop() {
		return ccmOrgComPopDao.findPop();
	}
	
	//工作力量
	public Map<String, Object> getnumOfWorkPower() {
		SysConfig system_level = sysConfigDao.get("system_level");
		//系统应用级别：1街道；2区县；3市
		String paramStr = system_level.getParamStr();
		if("1".equals(paramStr)) {
			List<SearchTab> list = ccmOrgComPopDao.getnumOfWorkPower();
			List<SearchTab> list2 = ccmOrgComPopDao.getnumOfWorkPowerForOne();
			int num=0;
			int temp;
			for(int i=0;i<list.size();i++) {
				temp = Integer.parseInt(list.get(i).getValue1());
				for(int j=0;j<list2.size();j++) {
					if(list.get(i).getType().equals(list2.get(j).getType())) {
						temp = temp+Integer.parseInt(list2.get(j).getValue1());
						list.get(i).setValue1(""+temp);
					}
				}
				num = num + temp;
			}
			String[] dataX = new String[list.size()];
			int[] dataY = new int[list.size()];
			int[] bgNum = new int[list.size()];
			for(int i=0;i<list.size();i++) {
				dataX[i] = list.get(i).getValue();
				dataY[i] = Integer.parseInt(list.get(i).getValue1());
				bgNum[i] = num;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataX",dataX);
			map.put("dataY",dataY);
			map.put("bgNum",bgNum);
			return map;
		}else if("2".equals(paramStr)){
			SysArea area = new SysArea();
			area.setType("5");
			List<SysArea> list = sysAreaDao.findList(area);
			String[] dataX = new String[list.size()];
			int[] dataY = new int[list.size()];
			int[] bgNum = new int[list.size()];
			int bigger = 0;
			SysArea sysarea;
			for(int i=0;i<list.size();i++) {
				sysarea = new SysArea();
				sysarea = list.get(i);
				SearchTab getNum = ccmOrgComPopDao.getnumOfWorkPowerForTwo(sysarea);
				dataX[i] = sysarea.getName();
				dataY[i] = Integer.parseInt(getNum.getValue1());
				bigger = bigger+Integer.parseInt(getNum.getValue1());
			}
			for(int j=0;j<bgNum.length;j++) {
				if(bigger!=0) {
					bgNum[j] = bigger;
				}else {
					bgNum[j] = 500;
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataX",dataX);
			map.put("dataY",dataY);
			map.put("bgNum",bgNum);
			return map;
		}else {
			return null;
		}
		
	}
	
}