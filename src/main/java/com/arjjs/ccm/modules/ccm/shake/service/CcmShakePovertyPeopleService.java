/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.shake.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.org.entity.SysArea;
import com.arjjs.ccm.modules.ccm.shake.dao.CcmShakePovertyPeopleDao;
import com.arjjs.ccm.modules.ccm.shake.entity.CcmShakePovertyPeople;
import com.arjjs.ccm.modules.ccm.sys.dao.SysDictsDao;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.*;

/**
 * 脱贫攻坚实体类Service
 * @author chenboyuan
 * @version 2019-06-21
 */
@Service
@Transactional(readOnly = true)
public class CcmShakePovertyPeopleService extends CrudService<CcmShakePovertyPeopleDao, CcmShakePovertyPeople> {

	@Autowired
	private CcmShakePovertyPeopleDao ccmShakePovertyPeopleDao;
	
	@Autowired
	private SysDictsDao sysDao;
	
	public CcmShakePovertyPeople get(String id) {
		return super.get(id);
	}
	
	public List<CcmShakePovertyPeople> findList(CcmShakePovertyPeople ccmShakePovertyPeople) {
		return super.findList(ccmShakePovertyPeople);
	}
	
	public Page<CcmShakePovertyPeople> findPage(Page<CcmShakePovertyPeople> page, CcmShakePovertyPeople ccmShakePovertyPeople) {
		return super.findPage(page, ccmShakePovertyPeople);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmShakePovertyPeople ccmShakePovertyPeople) {
		super.save(ccmShakePovertyPeople);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmShakePovertyPeople ccmShakePovertyPeople) {
		super.delete(ccmShakePovertyPeople);
	}
	
	/**
	 * 贫困属性查询业务处理
	 * @return
	 */
	@Transactional(readOnly = false)
	@SuppressWarnings("unchecked")
	public Map<String, Object> findGroupByTown(){
		//获取字典
		List<SysDicts> typelist = sysDao.findValueByType("ccm_shake_poverty_people_sp_alleviation");
		//序列化对象等待使用
		Map<String, List<Integer>> mapData = new HashMap<String, List<Integer>>();
		//循环typelist
		for(int i = 0; i < typelist.size(); i++) {
			SysDicts sysDicts = typelist.get(i);
			List<CcmShakePovertyPeople> list = ccmShakePovertyPeopleDao.findGroupByTown(sysDicts.getValue());
			for (CcmShakePovertyPeople ccmShakePovertyPeople : list) {
				String title = ccmShakePovertyPeople.getTownName();
				if(mapData.containsKey(title)) {
					mapData.get(title).add(ccmShakePovertyPeople.getSptownNum());
				}else {
					List<Integer> l = new ArrayList<Integer>();
					if(i > 0) {
						for (int j = 0; j < i; j++) {
							l.add(0);
						}
					}
					l.add(ccmShakePovertyPeople.getSptownNum());
					mapData.put(title, l);
				}
			}
		}
		List<Object> mlist = new ArrayList<Object>();
		Iterator<String> it = mapData.keySet().iterator();
	    while (it.hasNext()) {
	    	String key = it.next().toString();
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("title", key);
	    	map.put("num", mapData.get(key));
	        mlist.add(map);
	    }
		Map<String, Object> remap = new HashMap<String, Object>();
		remap.put("sysDicts", typelist);
		remap.put("result", mlist);
		return remap;
	}
	
	/**
	 * 各镇贫困人口年龄查询
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<CcmShakePovertyPeople> findByBirNum(){
		List<CcmShakePovertyPeople> list = ccmShakePovertyPeopleDao.findByBirNum();
		return list;
	}
	
	
    public List<SysArea> findTown(){
        List<SysArea> list = ccmShakePovertyPeopleDao.findTown();
        return list;
    }
	
	/**
	 * 各镇贫困人口劳动技能查询
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> skilllist(){
		List<CcmShakePovertyPeople> list = ccmShakePovertyPeopleDao.skilllist();
		List<SysDicts> typelist = sysDao.findValueByType("ccm_shake_poverty_people_sp_labor_skill");
		SysDicts sysDicts = new SysDicts();
		sysDicts.setLabel("未填写劳动技能");
		sysDicts.setValue("99");
		typelist.add(sysDicts);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		String [] nameArr = new String[typelist.size()];
		int [] valueArr = new int[typelist.size()];
		for (int i = 0; i < typelist.size(); i++) {
			int labelNum = 0;
			for (int j = 0; j < list.size(); j++) {
				String spLaborSkill = list.get(j).getSpLaborSkill();
				if(StringUtils.isEmpty(spLaborSkill)) {
					spLaborSkill = "99";
				}
				if(spLaborSkill.equals(typelist.get(i).getValue())) {
					labelNum = list.get(j).getSpSum();
					break;
				}
			}
			nameArr[i] = typelist.get(i).getLabel();
			valueArr[i] = labelNum;
		}
		map.put("name", nameArr);
		map.put("value", valueArr);
		result.put("sysDict", typelist);
		result.put("result", map);
		return result;
	}
	
	/**
	 * 各镇贫困人口健康状况查询
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> Healthlist(){
		List<CcmShakePovertyPeople> list = ccmShakePovertyPeopleDao.Healthlist();
		List<SysDicts> typelist = sysDao.findValueByType("ccm_shake_poverty_people_sp_health");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		SysDicts sysDicts = new SysDicts();
		sysDicts.setLabel("未填写健康情况");
		sysDicts.setValue("99");
		typelist.add(sysDicts);
		String [] nameArr = new String[typelist.size()];
		int [] valueArr = new int[typelist.size()];
		for(int j=0;j<typelist.size();j++) {
			int labelNum = 0;
			for(int i=0;i<list.size();i++) {
				String spHealth = list.get(i).getSpHealth();
				if(StringUtils.isEmpty(spHealth)) {
					spHealth = "99";
				}
				if(spHealth.equals(typelist.get(j).getValue())) {
					labelNum = list.get(i).getSpSum();
					break;
				}
			}
			nameArr[j] = typelist.get(j).getLabel();
			valueArr[j] = labelNum;
		}
		map.put("name",nameArr);
		map.put("num", valueArr);

		result.put("sysDict", typelist);
		result.put("result", map);
		return result;
	}
	
	/**
	 * 综合查询
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> SelectAll(){
		Map<String, Object> map = new HashMap<>();
		List<CcmShakePovertyPeople> selectAll = ccmShakePovertyPeopleDao.selectAll();
		//农业人口
		System.out.println(selectAll.size());
		map.put("AGPOP", selectAll.get(0).getSpSum());
		List<CcmShakePovertyPeople> selectAreaid = ccmShakePovertyPeopleDao.selectAreaid();
		//涉农村居
		map.put("Rural", selectAreaid.size());
		List<CcmShakePovertyPeople> selectAreaCountId = ccmShakePovertyPeopleDao.selectAreaCountId();
		//贫困村
		map.put("Poor", selectAreaCountId.size());
		List<CcmShakePovertyPeople> selectJDLK = ccmShakePovertyPeopleDao.selectJDLK();
		int[] jdlk = new int[2];
		jdlk = this.common(selectJDLK);
		//建档立卡
		map.put("Document", jdlk);
		List<CcmShakePovertyPeople> selectTP = ccmShakePovertyPeopleDao.selectTP();
		int[] TP = new int[2];
		TP = this.common(selectTP);
		//累计脱贫
		map.put("Cumulative", TP);
		List<CcmShakePovertyPeople> selectWTP = ccmShakePovertyPeopleDao.selectWTP();
		int[] WTP = new int[2];
		WTP = this.common(selectWTP);
		//未脱贫
		map.put("Notout", WTP);

		//本月脱贫人数
		List<CcmShakePovertyPeople> selectTMD = ccmShakePovertyPeopleDao.selectTMD();

		map.put("OnLineRate",selectTMD.get(0).getSpSum());

		// 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();   
         // 设置精确到小数点后2位   
        numberFormat.setMaximumFractionDigits(2);   
        String result = numberFormat.format((float)WTP[1]/(float)jdlk[1]*100);
      //贫困发生率
        map.put("Incidence", result);
		
		return map;
		
	}
	
	private int[] common(List<CcmShakePovertyPeople> list) {
		int[] num = new int[2];
		num[0] = list.size();
		for(int i =0 ;i<list.size();i++) {
			num[1] = num[1] + list.get(i).getSpSum();
		}
		return num;
	}
	public List<CcmShakePovertyPeople> findPoorList(CcmShakePovertyPeople ccmShakePovertyPeople) {
		return ccmShakePovertyPeopleDao.findPoorList(ccmShakePovertyPeople);
	}
}