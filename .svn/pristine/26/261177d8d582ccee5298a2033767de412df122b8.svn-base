/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.house.dao.*;
import com.arjjs.ccm.modules.ccm.house.entity.*;
import com.arjjs.ccm.modules.ccm.house.service.*;
import com.arjjs.ccm.modules.ccm.org.service.SysAreaService;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPeopleDao;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPopBehindDao;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeopleExport;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeopleVo;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopBehind;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SysConfigInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实有人口Service
 * 
 * @author liang
 * @version 2018-01-04
 */
@Service
@Transactional(readOnly = true)
public class CcmPeopleService extends CrudService<CcmPeopleDao, CcmPeople> {

	@Autowired
	private CcmPeopleDao ccmPeopleDao;
	@Autowired
	private CcmPopBehindDao ccmPopBehindDao;
	@Autowired
	private CcmPopBehindService ccmPopBehindService;
	@Autowired
	private CcmHouseAidsDao ccmHouseAidsDao;
	@Autowired
	private CcmHouseAidsService ccmHouseAidsService;
	@Autowired
	private CcmHouseDangerousDao ccmHouseDangerousDao;
	@Autowired
	private CcmHouseDangerousService ccmHouseDangerousService;
	@Autowired
	private CcmHouseDrugsDao ccmHouseDrugsDao;
	@Autowired
	private CcmHouseDrugsService ccmHouseDrugsService;
	@Autowired
	private CcmHouseHeresyDao ccmHouseHeresyDao;
	@Autowired
	private CcmHouseHeresyService ccmHouseHeresyService;
	@Autowired
	private CcmHouseKymDao ccmHouseKymDao;
	@Autowired
	private CcmHouseKymService ccmHouseKymService;
	@Autowired
	private CcmHousePetitionDao ccmHousePetitionDao;
	@Autowired
	private CcmHousePetitionService ccmHousePetitionService;
	@Autowired
	private CcmHousePsychogenyDao ccmHousePsychogenyDao;
	@Autowired
	private CcmHousePsychogenyService ccmHousePsychogenyService;
	@Autowired
	private CcmHouseRectificationDao ccmHouseRectificationDao;
	@Autowired
	private CcmHouseRectificationService ccmHouseRectificationService;
	@Autowired
	private CcmHouseReleaseDao ccmHouseReleaseDao;
	@Autowired
	private CcmHouseReleaseService ccmHouseReleaseService;

	@Autowired
	private CcmHouseBuildmanageService ccmHouseBuildmanageService;

	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private CcmPopTenantService ccmPopTenantService;
	
	
	
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	
	// 人员信息弹框
	public CcmPeople getHousePopForm(CcmPeople ccmPeople) {
		ccmPeople = ccmPeopleDao.getHousePopForm(ccmPeople);
		return ccmPeople;
	}

	// getNation
	public String getNation(CcmPeople ccmPeople) {
		return ccmPeopleDao.getNation(ccmPeople);
	}

	// getRelation
	public String getRelation(CcmPeople ccmPeople) {
		return ccmPeopleDao.getRelation(ccmPeople);
	}

	// 楼栋-房屋-住户信息弹框
	public List<CcmPeople> getHousePopList(CcmPeople ccmPeople) {
		return ccmPeopleDao.getHousePopList(ccmPeople);
	}

	// 校园周边重点人员列表
	public Page<CcmPeople> findPagePop(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findPagePop(ccmPeople));
		return page;
	}

	// 户籍户号调用
	public Page<CcmPeople> listAccount(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.listAccount(ccmPeople));
		return page;
	}

	//
	public CcmPeople get(String id) {
		return super.get(id);
	}

	public List<CcmPeople> findList(CcmPeople ccmPeople) {
		return super.findList(ccmPeople);
	}

	public Page<CcmPeople> findPage(Page<CcmPeople> page, CcmPeople ccmPeople) {
		return super.findPage(page, ccmPeople);
	}
	/**
	 * @see 党员
	 * @param user
	 * @return
	 */
	public Page<CcmPeople> findCommunistPage(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findCommunistList(ccmPeople));
		return page;
	}
	
	public List<EchartType> selectPopNumAllByPolitics() {
		return ccmPeopleDao.selectPopNumAllByPolitics();
	}
	
	
	public List<EchartType> selectPopNumAllByRectification() {
		return ccmPeopleDao.selectPopNumAllByRectification();
	}
	/**
	 * @see 老年人
	 * @param user
	 * @return
	 */
	public Page<CcmPeople> findOlderPage(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findOlderPage(ccmPeople));
		return page;
	}
	public List<CcmPeople> findOlderList(CcmPeople ccmPeople) {
		return ccmPeopleDao.findOlderPage(ccmPeople);
	}
	@Transactional(readOnly = false)
	public void save(CcmPeople ccmPeople) {
		boolean isNew = false;
		if (ccmPeople.getId() == null || "".equals(ccmPeople.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmPeople.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		
		//人口数据为新增的时候，给户籍状态一个默认值
		if(StringUtils.isEmpty(ccmPeople.getPersonType()) && isNew) {
			ccmPeople.setPersonType("01");
		}
		
		super.save(ccmPeople);
		
		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增人口数据：" + ccmPeople.getName());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑人口数据：" + ccmPeople.getName());
			}
			uploadLog.setTableName("ccm_people");
			uploadLog.setDataId(ccmPeople.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	@Transactional(readOnly = false)
	public void delete(CcmPeople ccmPeople) {
		ccmPeople = ccmPeopleDao.get(ccmPeople.getId());
		super.delete(ccmPeople);
		//1
		if(ccmPeople.getIsBehind()!=null && ccmPeople.getIsBehind()==1){
			CcmPopBehind ccmPopBehind = new CcmPopBehind();
			ccmPopBehind.setPeopleId(ccmPeople.getId());
			List<CcmPopBehind> list1 = ccmPopBehindService.findList(ccmPopBehind);
			for(CcmPopBehind l1:list1){
				ccmPopBehindDao.delete(l1);
			}
		}
		//2
		if(ccmPeople.getIsAids()!=null && ccmPeople.getIsAids()==1){
			CcmHouseAids ccmHouseAids = new CcmHouseAids();
			ccmHouseAids.setPeopleId(ccmPeople.getId());
			List<CcmHouseAids> list2 = ccmHouseAidsService.findList(ccmHouseAids);
			for(CcmHouseAids l2:list2){
				ccmHouseAidsDao.delete(l2);
			}
		}
		//3
		if(ccmPeople.getIsDangerous()!=null && ccmPeople.getIsDangerous()==1){
			CcmHouseDangerous ccmHouseDangerous = new CcmHouseDangerous();
			ccmHouseDangerous.setPeopleId(ccmPeople.getId());
			List<CcmHouseDangerous> list3 = ccmHouseDangerousService.findList(ccmHouseDangerous);
			for(CcmHouseDangerous l3:list3){
				ccmHouseDangerousDao.delete(l3);
			}
		}
		//4
		if(ccmPeople.getIsDrugs()!=null && ccmPeople.getIsDrugs()==1){
			CcmHouseDrugs ccmHouseDrugs = new CcmHouseDrugs();
			ccmHouseDrugs.setPeopleId(ccmPeople.getId());
			List<CcmHouseDrugs> list4 = ccmHouseDrugsService.findList(ccmHouseDrugs);
			for(CcmHouseDrugs l4:list4){
				ccmHouseDrugsDao.delete(l4);
			}
		}
		//5
		if(ccmPeople.getIsHeresy()!=null && ccmPeople.getIsHeresy()==1){
			CcmHouseHeresy ccmHouseHeresy = new CcmHouseHeresy();
			ccmHouseHeresy.setPeopleId(ccmPeople.getId());
			List<CcmHouseHeresy> list5 = ccmHouseHeresyService.findList(ccmHouseHeresy);
			for(CcmHouseHeresy l5:list5){
				ccmHouseHeresyDao.delete(l5);
			}
		}
		//6
		if(ccmPeople.getIsKym()!=null && ccmPeople.getIsKym()==1){
			CcmHouseKym ccmHouseKym = new CcmHouseKym();
			ccmHouseKym.setPeopleId(ccmPeople.getId());
			List<CcmHouseKym> list6 = ccmHouseKymService.findList(ccmHouseKym);
			for(CcmHouseKym l6:list6){
				ccmHouseKymDao.delete(l6);
			}
		}
		//7
		if(ccmPeople.getIsVisit()!=null && ccmPeople.getIsVisit()==1){
			CcmHousePetition ccmHousePetition = new CcmHousePetition();
			ccmHousePetition.setPeopleId(ccmPeople.getId());
			List<CcmHousePetition> list7 = ccmHousePetitionService.findList(ccmHousePetition);
			for(CcmHousePetition l7:list7){
				ccmHousePetitionDao.delete(l7);
			}
		}
		//8
		if(ccmPeople.getIsPsychogeny()!=null && ccmPeople.getIsPsychogeny()==1){
			CcmHousePsychogeny ccmHousePsychogeny = new CcmHousePsychogeny();
			ccmHousePsychogeny.setPeopleId(ccmPeople.getId());
			List<CcmHousePsychogeny> list8 = ccmHousePsychogenyService.findList(ccmHousePsychogeny);
			for(CcmHousePsychogeny l8:list8){
				ccmHousePsychogenyDao.delete(l8);
			}
		}
		//9
		if(ccmPeople.getIsRectification()!=null && ccmPeople.getIsRectification()==1){
			CcmHouseRectification ccmHouseRectification = new CcmHouseRectification();
			ccmHouseRectification.setPeopleId(ccmPeople.getId());
			List<CcmHouseRectification> list9 = ccmHouseRectificationService.findList(ccmHouseRectification);
			for(CcmHouseRectification l9:list9){
				ccmHouseRectificationDao.delete(l9);
			}
		}
		//10
		if(ccmPeople.getIsRelease()!=null && ccmPeople.getIsRelease()==1){
			CcmHouseRelease ccmHouseRelease = new CcmHouseRelease();
			ccmHouseRelease.setPeopleId(ccmPeople.getId());
			List<CcmHouseRelease> list10 = ccmHouseReleaseService.findList(ccmHouseRelease);
			for(CcmHouseRelease l10:list10){
				ccmHouseReleaseDao.delete(l10);
			}
		}
		
		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除人口数据：" + ccmPeople.getName());
			uploadLog.setTableName("ccm_people");
			uploadLog.setDataId(ccmPeople.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	/**
	 * @see 返回 以整月 下的 list求和数
	 * @param user
	 * @return
	 */
	public List<EchartType> getListByMon(User user, CcmPeople ccmPeople) {
		return ccmPeopleDao.getListByMon(user, ccmPeople);
	}

	/**
	 * @see 返回 以整日 下的 list求和数
	 * @param user
	 * @return
	 */
	public List<EchartType> getListByDay(User user, CcmPeople ccmPeople) {
		return ccmPeopleDao.getListByDay(user, ccmPeople);
	}

	// 查询指定的重点人员
	public List<CcmPeople> findSpeList(CcmPeople ccmPeople) {
		return ccmPeopleDao.findSpeList(ccmPeople);
	}

	// 查询指定的 人员身份证是否可以存入数据库
	public boolean checkPeopleIden(CcmPeople ccmPeople) {
		return ccmPeopleDao.checkPeopleIden(ccmPeople) == 0 ? false : true;
	}

	// 查询相关户籍家庭人口
	public List<CcmPeople> listAccount(CcmPeople ccmPeople){
		return ccmPeopleDao.listAccount(ccmPeople);
	}

	//导出实有人口数据---老年人
	public List<CcmPeople> findOlder(CcmPeople ccmPeople) {
		return ccmPeopleDao.findOlderPage(ccmPeople);
	}

	//导出实有人口数据---党员
	public List<CcmPeople> findCommunist(CcmPeople ccmPeople) {
		return ccmPeopleDao.findCommunistList(ccmPeople);
	}

	//特殊关怀人员数量
	public int findListNum(CcmPeople ccmPeople) {
		return ccmPeopleDao.findListNum(ccmPeople);
	}

	//数组查询id
	public List<CcmPeople> findListLimite(CcmPeople ccmPeople2) {
//		return ccmPeopleDao.findListLimite(ccmPeople2);
		return ccmPeopleDao.findListLimite_V2(ccmPeople2);
	}

	//特殊关怀
	public Page<CcmPeople> findCarePage(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findCareList(ccmPeople));
		return page;
	}
	
	public Page<CcmPeople> findPopRoomPage(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.housePopListAdd(ccmPeople));
		return page;
	}
	
	public List<CcmPeople> findCareList(CcmPeople ccmPeople) {
		return ccmPeopleDao.findCareList(ccmPeople);
	}
	
	//导出特殊关怀查询
	public List<CcmPeopleExport> findAllCareList(CcmPeople ccmPeople2) {
		return ccmPeopleDao.findAllCareList(ccmPeople2);
	}

	// 特殊关怀First
	public Page<CcmPeople> findCareFirst(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findCareFirst(ccmPeople));
		return page;
	}

	//常住人口
	public Page<CcmPeople> findPermanentPage(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findPermanentList(ccmPeople));
		return page;
	}
	
	/**
	 * 区域内人员统计
	 */
	public int countSelect(List<String> coordianteList){
		return ccmPeopleDao.countSelect(coordianteList);
	}
	/**
	 * zhanghao
	 * @param 事件周边嫌疑人
	 * @return
	 */
	public List<CcmPeople> locatingSuspects(List<String> coordianteList) {
		return ccmPeopleDao.locatingSuspects(coordianteList);
	}
	
	//数组查询id  V2 版本
	public List<CcmPeople> findListLimite_V2(CcmPeople ccmPeople2) {
		return ccmPeopleDao.findListLimite_V2(ccmPeople2);
	}

	public List<CcmPeople> getByIdent(String string){

		return ccmPeopleDao.getByIdent(string);

	}
	
	@Transactional(readOnly = false)
	public void updatePeople(CcmPeople ccmPeople) {
		ccmPeopleDao.updatePeople(ccmPeople);
	}

	public int findCount() {
		int count = ccmPeopleDao.findCount();
		return count;
	}

	public List<CcmPeople> queryPeopleInfo(Page page) {
		List<CcmPeople> list = page.getList();
		List<CcmPeople> ccmPeoplelist = new ArrayList<CcmPeople>();
		for (CcmPeople people:list) {
			CcmPeople peoplePojo = get(people);
//			//区域
//			if (peoplePojo.getAreaComId() != null && StringUtils.isNotBlank(peoplePojo.getAreaComId().getId())){
//				SysArea sysAreaCom = sysAreaService.get(peoplePojo.getAreaComId().getId());
//			}
//			//网格
//			if (peoplePojo.getAreaGridId() != null && StringUtils.isNotBlank(peoplePojo.getAreaGridId().getId())){
//
//				SysArea sysAreaGrid = sysAreaService.get(peoplePojo.getAreaGridId().getId());
//			}
//			//楼幢
//			if (peoplePojo.getBuildId() != null && StringUtils.isNotBlank(peoplePojo.getBuildId().getId() ) ){
//				CcmHouseBuildmanage ccmHouseBuildmanage = ccmHouseBuildmanageService.get(peoplePojo.getBuildId().getId());
//			}
//			//房屋
//			if (peoplePojo.getRoomId() != null && StringUtils.isNotBlank(peoplePojo.getRoomId().getId())){
//				CcmPopTenant ccmPopTenant = ccmPopTenantService.get( peoplePojo.getRoomId().getId());
//			}
			ccmPeoplelist.add(peoplePojo);
		}
		return ccmPeoplelist;
	}

	/**
	 * 根据sn号查询重点人员信息
	 *
	 * @param snNum
	 * @return
	 */
	public CcmPeople getInfoBySnNum(String snNum) {
		return ccmPeopleDao.getInfoBySnNum(snNum);
	}

	public List<CcmPeople> findListBuildBox(CcmPeople ccmPeople) {
		return ccmPeopleDao.findListBuildBox(ccmPeople);
	}

	
	public CcmPeople peopleSexCount(CcmPeople ccmPeople) {
		return ccmPeopleDao.peopleSexCount(ccmPeople);
	}

	public Map<String, Object> peopleBirthdayCount(List<SysDicts> list) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,(c.get(Calendar.YEAR)-c.get(Calendar.YEAR)%10));
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date date1 = c.getTime();
		c.set(Calendar.YEAR,(c.get(Calendar.YEAR)-10));
		Date date2 = c.getTime();
		c.set(Calendar.YEAR,(c.get(Calendar.YEAR)-10));
		Date date3 = c.getTime();
		c.set(Calendar.YEAR,(c.get(Calendar.YEAR)-10));
		Date date4 = c.getTime();
		CcmPeople people = new CcmPeople();
		String[] year = new String[5];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		people.setBeginBirthday(date1);
		year[4] = formatter.format(date1)+"-now";
		List<CcmPeople> list1 = ccmPeopleDao.peopleBirthdayCount(people);
		people.setBeginBirthday(date2);
		people.setEndBirthday(date1);
		year[3] = formatter.format(date2)+"-"+formatter.format(date1);
		List<CcmPeople> list2 = ccmPeopleDao.peopleBirthdayCount(people);
		people.setBeginBirthday(date3);
		people.setEndBirthday(date2);
		year[2] = formatter.format(date3)+"-"+formatter.format(date2);
		List<CcmPeople> list3 = ccmPeopleDao.peopleBirthdayCount(people);
		people.setBeginBirthday(date4);
		people.setEndBirthday(date3);
		year[1] = formatter.format(date4)+"-"+formatter.format(date3);
		List<CcmPeople> list4 = ccmPeopleDao.peopleBirthdayCount(people);
		people.setEndBirthday(date4);
		year[0] = "before-"+formatter.format(date4);
		List<CcmPeople> list5 = ccmPeopleDao.peopleBirthdayCount(people);
		List<Map<String, Object>> listData = new ArrayList<Map<String,Object>>();
		String[] dicts = new String[list.size()];
		int[] num;
		String value = null;
		int i = 0;
		for (SysDicts dict : list) {
			value = dict.getValue();
			dicts[i] = dict.getLabel();
			num = new int[5];
			for (CcmPeople p : list1) {
				if(value.equals(p.getSex())) {
					num[4] = p.getResultNum();
				}
			}
			for (CcmPeople p : list2) {
				if(value.equals(p.getSex())) {
					num[3] = p.getResultNum();
				}
			}
			for (CcmPeople p : list3) {
				if(value.equals(p.getSex())) {
					num[2] = p.getResultNum();
				}
			}
			for (CcmPeople p : list4) {
				if(value.equals(p.getSex())) {
					num[1] = p.getResultNum();
				}
			}
			for (CcmPeople p : list5) {
				if(value.equals(p.getSex())) {
					num[0] = p.getResultNum();
				}
			}
			Map<String, Object> temp = new HashMap<>();
			temp.put("name", dict.getLabel());
			temp.put("type", "bar");
			temp.put("data", num);
			listData.add(temp);
			i++;
		}
		Map<String, Object> result = new HashMap<>();
		result.put("yAxis", year);
		result.put("legend", dicts);
		result.put("value", listData);
		return result;
	}

	public Map<String, Object> peopleNationCount(List<SysDicts> list) {
		List<CcmPeople> getcount = ccmPeopleDao.peopleNationCount();
		List<Map<String, Object>> listData = new ArrayList<Map<String,Object>>();
		Map<String, Object> temp;
		for (CcmPeople p : getcount) {
			for (SysDicts dict : list) {
				if(dict.getValue().equals(p.getNation())) {
					temp = new HashMap<String, Object>();
					temp.put("name", dict.getLabel());
					temp.put("value", p.getResultNum());
					listData.add(temp);
				}
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value", listData);
		return result;
	}

	public Map<String, Object> peopleAgeCount() {
		List<CcmPeople> agecount = ccmPeopleDao.peopleAgeCount();
		String[] agetitle = new String[5];
		int[] num = new int[5];
		agetitle[0] = "<15";
		agetitle[1] = "15-25";
		agetitle[2] = "25-35";
		agetitle[3] = "35-45";
		agetitle[4] = "45<";
		for (CcmPeople p : agecount) {
			if(p.getPeopleAge()<15) {
				num[0] = num[0]+p.getPeopleAge();
			}else if(p.getPeopleAge()>=15 && p.getPeopleAge()<25) {
				num[1] = num[1]+p.getPeopleAge();
			}else if(p.getPeopleAge()>=25 && p.getPeopleAge()<35) {
				num[2] = num[2]+p.getPeopleAge();
			}else if(p.getPeopleAge()>=35 && p.getPeopleAge()<45) {
				num[3] = num[3]+p.getPeopleAge();
			}else {
				num[4] = num[4]+p.getPeopleAge();
			}
		}
		List<Map<String, Object>> listData = new ArrayList<Map<String,Object>>();
		Map<String, Object> temp;
		int j = 0;
		for (int i : num) {
			temp = new HashMap<String, Object>();
			temp.put("name", agetitle[j]);
			temp.put("value", i);
			listData.add(temp);
			j++;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", agetitle);
		result.put("value", listData);
		return result;
	}

	public List<EchartType> peopleRegionCount() {
		 return ccmPeopleDao.peopleRegionCount();
	}

    public User judgeUserAreaPermission(String userId,String areaId,List parentIds) {
		return dao.judgeUserAreaPermission(userId,areaId,parentIds);
    }
    
    // 校园周边重点人员列表
 	public Page<CcmPeople> findAllPopByArea(Page<CcmPeople> page, CcmPeople ccmPeople) {
 		ccmPeople.setPage(page);
 		page.setList(ccmPeopleDao.findAllPopByArea(ccmPeople));
 		return page;
 	}
	public List<CcmPeopleVo> selectByAreaGIdAndName(CcmAreaPointVo areaPointVo){
		return dao.selectByAreaGIdAndName(areaPointVo);
	}

	public List<CcmPeopleVo> selectByAreaGIdAndNameImport(CcmAreaPointVo areaPointVo){
		return dao.selectByAreaGIdAndNameImport(areaPointVo);
	}

	public List<CcmPeople> findByRoomId(String roomId){
		return dao.findByRoomId(roomId);
	}

	public List<CcmPeople> findSuspect(CcmPeople ccmPeople) {
		return ccmPeopleDao.findSuspect(ccmPeople);
	}
	
	public CcmPeople getSuspect(CcmPeople ccmPeople) {
		return ccmPeopleDao.getSuspect(ccmPeople);
	}

	public Page<CcmPeople> findListexists(Page<CcmPeople> page, CcmPeople ccmPeople) {
		ccmPeople.setPage(page);
		page.setList(ccmPeopleDao.findListexists(ccmPeople));
		return page;
	}
	//人口导出数据查询
	public List<CcmPeople> getExportList(CcmPeople ccmPeople) {
		return ccmPeopleDao.getExportList(ccmPeople);
	}

	//重点人员top5
	public List<EchartType> getKeypeopleTop(){
		return ccmPeopleDao.getKeypeopleTop();
	}
}