package com.arjjs.ccm.modules.ccm.rest.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.*;
import com.arjjs.ccm.modules.ccm.house.service.*;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopBehind;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopBehindService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.DictService;
import com.arjjs.ccm.tool.PlmTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 人口接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-22
 */
@Controller
@RequestMapping(value = "${appPath}/rest/people")
public class CcmRestPeople extends BaseController {

	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmHouseRectificationService ccmHouseRectificationService;
	@Autowired
	private CcmHouseReleaseService ccmHouseReleaseService;
	@Autowired
	private CcmHousePsychogenyService ccmHousePsychogenyService;
	@Autowired
	private CcmHouseDrugsService ccmHouseDrugsService;
	@Autowired
	private CcmHouseAidsService ccmHouseAidsService;
	@Autowired
	private CcmHouseKymService ccmHouseKymService;
	@Autowired
	private CcmHousePetitionService ccmHousePetitionService;
	@Autowired
	private CcmHouseHeresyService ccmHouseHeresyService;
	@Autowired
	private CcmHouseDangerousService ccmHouseDangerousService;
	@Autowired
	private CcmPopBehindService ccmPopBehindService;
	@Autowired
	private DictService dictService;
	@Autowired
	private CcmPopTenantService ccmPopTenantService;
	@Autowired
	private CcmHarmNationalSecurityService ccmHarmNationalSecurityService;
	@Autowired
	private CcmHouseDeliberatelyIllegalService ccmHouseDeliberatelyIllegalService;
	@Autowired
	private CcmHouseDisputeService ccmHouseDisputeService;
	@Autowired
	private CcmSeriousCriminalOffenseService ccmSeriousCriminalOffenseService;


	/**
	 * @see  获取单个人员信息
	 * @param id  ID
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId,HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (id == null || "".equals(id)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
		CcmPeople ccmPeople = ccmPeopleService.get(id);
        if(StringUtils.isNotEmpty(ccmPeople.getImages())){
            ccmPeople.setImages(fileUrl + ccmPeople.getImages());
        }
		result.setCode(CcmRestType.OK);
		result.setResult(ccmPeople);
		
		return result;
	}
	
	/**
	 * @see  查询人员信息
	 * @param name  小区（单位）名称
	 * @param buildname  楼栋名称 
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String roomid,String userId,CcmPeople ccmPeople, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》roomid : " + roomid + "  userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();

		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if(StringUtils.isNotEmpty(roomid)) {
			ccmPeople.setRoomId(new CcmPopTenant(roomid));
		}
		ccmPeople.setCheckUser(sessionUser);
		Page<CcmPeople> page = ccmPeopleService.findPage(new Page<CcmPeople>(req, resp), ccmPeople);
	
		//
		List<CcmPeople> list = page.getList();
		//
		CcmPeople ccmPeople2 = new CcmPeople();
		ccmPeople2.setCheckUser(sessionUser);
		String[] listLimite = new String[list.size()];
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				listLimite[i]=list.get(i).getId();
			}
			ccmPeople2.setListLimite(listLimite);
			List<CcmPeople> list2 = ccmPeopleService.findListLimite(ccmPeople2);//数组查询id
            for(int f=0;f<list2.size();f++){
                if (StringUtils.isNotEmpty(list2.get(f).getImages())) {
                    list2.get(f).setImages(fileUrl + list2.get(f).getImages());
                }
            }
			page.setList(list2);
			logger.info(""+list2);
		}
		result.setCode(CcmRestType.OK);
		if(page.getList()==null||page.getList().size()<=0) {
			result.setResult("");
		}else {
			result.setResult(page.getList());	
		}
		return result;
	}
	
	/**
	 * @see  查询户籍家庭人员信息
	 * @param account  户号
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/familyquery", method = RequestMethod.GET)
	public CcmRestResult familyquery(String userId,CcmPeople ccmPeople, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		Page<CcmPeople> page = ccmPeopleService.listAccount(new Page<CcmPeople>(req, resp), ccmPeople);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	
	
	/**
	 * @see  保存人员信息
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId,CcmPeople ccmPeople, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmPeople.getId()!= null && !"".equals(ccmPeople.getId())) {
			CcmPeople ccmPeopleDB = ccmPeopleService.get(ccmPeople.getId());
			if (ccmPeopleDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
			ccmPeople.setIsAids(ccmPeopleDB.getIsAids());
			ccmPeople.setIsBehind(ccmPeopleDB.getIsBehind());
			ccmPeople.setIsDangerous(ccmPeopleDB.getIsDangerous());
			ccmPeople.setIsDrugs(ccmPeopleDB.getIsDrugs());
			ccmPeople.setIsHeresy(ccmPeopleDB.getIsHeresy());
			ccmPeople.setIsKym(ccmPeopleDB.getIsKym());
			ccmPeople.setIsPsychogeny(ccmPeopleDB.getIsPsychogeny());
			ccmPeople.setIsRectification(ccmPeopleDB.getIsRectification());
			ccmPeople.setIsRelease(ccmPeopleDB.getIsRelease());
			ccmPeople.setIsVisit(ccmPeopleDB.getIsVisit());
		}
		if (ccmPeople.getRoomId().getId()!= null && !"".equals(ccmPeople.getRoomId().getId())) {
			CcmPopTenant ccmPopTenantDB = ccmPopTenantService.get(ccmPeople.getRoomId().getId());
			if (ccmPopTenantDB != null ) {//从数据库中没有取到对应数据 //根据房屋id 给  所在社区  所在网格   赋值
				CcmPeople ccmPeopleArea = ccmPopTenantService.findAreaByRoom(ccmPopTenantDB);
				ccmPeople.setAreaComId(new Area(ccmPeopleArea.getAreaComId().getId()));
				ccmPeople.setAreaGridId(new Area(ccmPeopleArea.getAreaGridId().getId()));
				ccmPeople.setBuildId(new CcmHouseBuildmanage(ccmPeopleArea.getBuildId().getId()));
			}
		}
		if (ccmPeople.getCreateBy()== null) {
			ccmPeople.setCreateBy(new User(userId));
		}
		ccmPeople.setUpdateBy(new User(userId));
		String file = ccmPeople.getImages();
		if(StringUtils.isNotEmpty(file)) {
			if(file.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
				ccmPeople.setImages(file.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
			}else {
				ccmPeople.setImages(file);
			}
		}
		ccmPeopleService.save(ccmPeople);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		
		return result;
		
	}
	


	/**
	 * @see  保存社区矫正人员信息
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/saveRectification", method = RequestMethod.POST)
	public CcmRestResult saveRectification(String userId,HttpServletRequest req,CcmHouseRectification ccmHouseRectification, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRectification : " + String.valueOf(ccmHouseRectification));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseRectification)) {//参数不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseRectification.getId() != null && !"".equals(ccmHouseRectification.getId())) {
			CcmHouseRectification ccmHouseRectificationDB = ccmHouseRectificationService.get(ccmHouseRectification.getId());
			if (ccmHouseRectificationDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseRectification.getCreateBy()== null) {
			ccmHouseRectification.setCreateBy(new User(userId));
		}
		ccmHouseRectification.setUpdateBy(new User(userId));
		ccmHouseRectificationService.save(ccmHouseRectification);
		// 更新 当前人 是 社区矫正人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseRectification.getPeopleId());
		ccmPop.setIsRectification(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存社区矫正人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseRectification);
		
		return result;
	}
	/**
	 * @see  查询社区矫正人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryRectification", method = RequestMethod.GET)
	public CcmRestResult queryRectification(String userId,CcmHouseRectification ccmHouseRectification, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRectification : " + String.valueOf(ccmHouseRectification));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseRectification.setCheckUser(sessionUser);
		Page<CcmHouseRectification> page = ccmHouseRectificationService.findPage(new Page<CcmHouseRectification>(req, resp), ccmHouseRectification);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取社区矫正人员信息
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/getRectification", method = RequestMethod.GET)
	public CcmRestResult getRectification(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		CcmHouseRectification entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseRectificationService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseRectificationService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseRectification();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	


	/**
	 * @see  保存安置帮教人员信息 saveRelease
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/saveRelease", method = RequestMethod.POST)
	public CcmRestResult saveRelease(String userId,CcmHouseRelease ccmHouseRelease,HttpServletRequest req, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRelease : " + String.valueOf(ccmHouseRelease));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseRelease)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseRelease.getId() != null && !"".equals(ccmHouseRelease.getId())) {
			CcmHouseRelease ccmHouseReleaseDB = ccmHouseReleaseService.get(ccmHouseRelease.getId());
			if (ccmHouseReleaseDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseRelease.getCreateBy()== null) {
			ccmHouseRelease.setCreateBy(new User(userId));
		}
		ccmHouseRelease.setUpdateBy(new User(userId));
		
		ccmHouseReleaseService.save(ccmHouseRelease);
		// 更新 当前人 是 安置帮教人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseRelease.getPeopleId());
		ccmPop.setIsRelease(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存安置帮教人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseRelease);
		
		return result;
	}
	/**
	 * @see  查询安置帮教人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryRelease", method = RequestMethod.GET)
	public CcmRestResult queryRelease(String userId,CcmHouseRelease ccmHouseRelease, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRelease : " + String.valueOf(ccmHouseRelease));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseRelease.setCheckUser(sessionUser);
		Page<CcmHouseRelease> page = ccmHouseReleaseService.findPage(new Page<CcmHouseRelease>(req, resp), ccmHouseRelease);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取安置帮教人员信息 getRelease
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/getRelease", method = RequestMethod.GET)
	public CcmRestResult getRelease(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		CcmHouseRelease entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseReleaseService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseReleaseService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseRelease();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	/**
	 * @see  保存肇事肇祸等严重精神障碍患者信息 savePsychogeny
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/savePsychogeny", method = RequestMethod.POST)
	public CcmRestResult savePsychogeny(String userId,HttpServletRequest req,CcmHousePsychogeny ccmHousePsychogeny, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePsychogeny : " + String.valueOf(ccmHousePsychogeny));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHousePsychogeny)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHousePsychogeny.getId() != null && !"".equals(ccmHousePsychogeny.getId())) {
			CcmHousePsychogeny ccmHousePsychogenyDB = ccmHousePsychogenyService.get(ccmHousePsychogeny.getId());
			if (ccmHousePsychogenyDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHousePsychogeny.getCreateBy()== null) {
			ccmHousePsychogeny.setCreateBy(new User(userId));
		}
		ccmHousePsychogeny.setUpdateBy(new User(userId));
		
		ccmHousePsychogenyService.save(ccmHousePsychogeny);
		// 更新 当前人 是 肇事肇祸等严重精神障碍患者
		CcmPeople ccmPop = ccmPeopleService.get(ccmHousePsychogeny.getPeopleId());
		ccmPop.setIsPsychogeny(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存肇事肇祸等严重精神障碍患者成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHousePsychogeny);
		
		return result;
	}
	/**
	 * @see  查询肇事肇祸等严重精神障碍患者信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryPsychogeny", method = RequestMethod.GET)
	public CcmRestResult queryPsychogeny(String userId,CcmHousePsychogeny ccmHousePsychogeny, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePsychogeny : " + String.valueOf(ccmHousePsychogeny));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHousePsychogeny.setCheckUser(sessionUser);
		Page<CcmHousePsychogeny> page = ccmHousePsychogenyService.findPage(new Page<CcmHousePsychogeny>(req, resp), ccmHousePsychogeny);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	} 

	/**
	 * @see  获取肇事肇祸等严重精神障碍患者信息 getPsychogeny
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/getPsychogeny", method = RequestMethod.GET)
	public CcmRestResult getPsychogeny(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHousePsychogeny entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHousePsychogenyService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHousePsychogenyService.getPeopleALL(peopleId);
		}
		else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHousePsychogeny();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}

	/**
	 * @see  保存吸毒人员信息 saveDrugs
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/saveDrugs", method = RequestMethod.POST)
	public CcmRestResult saveDrugs(String userId,HttpServletRequest req, CcmHouseDrugs ccmHouseDrugs, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDrugs : " + String.valueOf(ccmHouseDrugs));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseDrugs)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseDrugs.getId() != null && !"".equals(ccmHouseDrugs.getId())) {
			CcmHouseDrugs ccmHouseDrugsDB = ccmHouseDrugsService.get(ccmHouseDrugs.getId());
			if (ccmHouseDrugsDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseDrugs.getCreateBy()== null) {
			ccmHouseDrugs.setCreateBy(new User(userId));
		}
		ccmHouseDrugs.setUpdateBy(new User(userId));
		
		ccmHouseDrugsService.save(ccmHouseDrugs);
		// 更新 当前人 是 吸毒人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDrugs.getPeopleId());
		ccmPop.setIsDrugs(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存吸毒人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseDrugs);
		
		return result;
	}
	/**
	 * @see  查询吸毒人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryDrugs", method = RequestMethod.GET)
	public CcmRestResult queryDrugs(String userId,CcmHouseDrugs ccmHouseDrugs, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDrugs : " + String.valueOf(ccmHouseDrugs));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseDrugs.setCheckUser(sessionUser);
		Page<CcmHouseDrugs> page = ccmHouseDrugsService.findPage(new Page<CcmHouseDrugs>(req, resp), ccmHouseDrugs);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取吸毒人员信息 getDrugs
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/getDrugs", method = RequestMethod.GET)
	public CcmRestResult getDrugs(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHouseDrugs entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseDrugsService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseDrugsService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseDrugs();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}

	/**
	 * @see  保存艾滋人员信息 saveAids
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/saveAids", method = RequestMethod.POST)
	public CcmRestResult saveAids(String userId,HttpServletRequest req,CcmHouseAids ccmHouseAids, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseAids : " + String.valueOf(ccmHouseAids));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseAids)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseAids.getId() != null && !"".equals(ccmHouseAids.getId())) {
			CcmHouseAids ccmHouseAidsDB = ccmHouseAidsService.get(ccmHouseAids.getId());
			if (ccmHouseAidsDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseAids.getCreateBy()== null) {
			ccmHouseAids.setCreateBy(new User(userId));
		}
		ccmHouseAids.setUpdateBy(new User(userId));
		ccmHouseAidsService.save(ccmHouseAids);
		// 更新 当前人 是 艾滋人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseAids.getPeopleId());
/*		ccmPop.setIsAids(1);*/
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存艾滋人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseAids);
		
		return result;
	}
	/**
	 * @see  查询艾滋人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryAids", method = RequestMethod.GET)
	public CcmRestResult queryAids(String userId,CcmHouseAids ccmHouseAids, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseAids : " + String.valueOf(ccmHouseAids));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		ccmHouseAids.setCheckUser(sessionUser);
		Page<CcmHouseAids> page = ccmHouseAidsService.findPage(new Page<CcmHouseAids>(req, resp), ccmHouseAids);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取艾滋人员信息 getAids
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/getAids", method = RequestMethod.GET)
	public CcmRestResult getAids(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHouseAids entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseAidsService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseAidsService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseAids();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}

	/**
	 * @see  保存重点青少年人员信息 saveKym
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/saveKym", method = RequestMethod.POST)
	public CcmRestResult saveKym(String userId,HttpServletRequest req,CcmHouseKym ccmHouseKym, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseKym : " + String.valueOf(ccmHouseKym));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseKym)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseKym.getId() != null && !"".equals(ccmHouseKym.getId())) {
			CcmHouseKym ccmHouseKymDB = ccmHouseKymService.get(ccmHouseKym.getId());
			if (ccmHouseKymDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseKym.getCreateBy()== null) {
			ccmHouseKym.setCreateBy(new User(userId));
		}
		ccmHouseKym.setUpdateBy(new User(userId));
		ccmHouseKymService.save(ccmHouseKym);
		// 更新 当前人 是 重点青少年人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseKym.getPeopleId());
		ccmPop.setIsKym(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "重点青少年人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseKym);
		
		return result;
	}
	/**
	 * @see  查询重点青少年人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryKym", method = RequestMethod.GET)
	public CcmRestResult queryKym(String userId,CcmHouseKym ccmHouseKym, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseKym : " + String.valueOf(ccmHouseKym));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseKym.setCheckUser(sessionUser);
		Page<CcmHouseKym> page = ccmHouseKymService.findPage(new Page<CcmHouseKym>(req, resp), ccmHouseKym);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取重点青少年人员信息 getKym
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/getKym", method = RequestMethod.GET)
	public CcmRestResult getKym(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHouseKym entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseKymService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseKymService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseKym();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	/**
	 * @see  保存涉教人员信息 saveHeresy
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/saveHeresy", method = RequestMethod.POST)
	public CcmRestResult saveHeresy(String userId,HttpServletRequest req,CcmHouseHeresy ccmHouseHeresy, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseHeresy : " + String.valueOf(ccmHouseHeresy));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseHeresy)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseHeresy.getId() != null && !"".equals(ccmHouseHeresy.getId())) {
			CcmHouseHeresy ccmHouseHeresyDB = ccmHouseHeresyService.get(ccmHouseHeresy.getId());
			if (ccmHouseHeresyDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseHeresy.getCreateBy()== null) {
			ccmHouseHeresy.setCreateBy(new User(userId));
		}
		ccmHouseHeresy.setUpdateBy(new User(userId));
		ccmHouseHeresyService.save(ccmHouseHeresy);
		// 更新 当前人 是涉教人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseHeresy.getPeopleId());
		ccmPop.setIsHeresy(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存涉教人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseHeresy);
		
		return result;
	}
	/**
	 * @see  查询涉教人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryHeresy", method = RequestMethod.GET)
	public CcmRestResult queryHeresy(String userId,CcmHouseHeresy ccmHouseHeresy, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseHeresy : " + String.valueOf(ccmHouseHeresy));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseHeresy.setCheckUser(sessionUser);
		Page<CcmHouseHeresy> page = ccmHouseHeresyService.findPage(new Page<CcmHouseHeresy>(req, resp), ccmHouseHeresy);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取涉教人员信息 getHeresy
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-02-26
	 */
	@ResponseBody
	@RequestMapping(value="/getHeresy", method = RequestMethod.GET)
	public CcmRestResult getHeresy(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHouseHeresy entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseHeresyService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseHeresyService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseHeresy();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	/**
	 * @see  保存危险品从业人员信息 saveDangerous
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/saveDangerous", method = RequestMethod.POST)
	public CcmRestResult saveDangerous(String userId,HttpServletRequest req,CcmHouseDangerous ccmHouseDangerous, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDangerous : " + String.valueOf(ccmHouseDangerous));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseDangerous)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseDangerous.getId() != null && !"".equals(ccmHouseDangerous.getId())) {
			CcmHouseDangerous ccmHouseDangerousDB = ccmHouseDangerousService.get(ccmHouseDangerous.getId());
			if (ccmHouseDangerousDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHouseDangerous.getCreateBy()== null) {
			ccmHouseDangerous.setCreateBy(new User(userId));
		}
		ccmHouseDangerous.setUpdateBy(new User(userId));
		ccmHouseDangerousService.save(ccmHouseDangerous);
		// 更新 当前人 是危险品从业人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDangerous.getPeopleId());
		ccmPop.setIsDangerous(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存危险品从业人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseDangerous);
		
		return result;
	}
	/**
	 * @see  查询危险品从业人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryDangerous", method = RequestMethod.GET)
	public CcmRestResult queryDangerous(String userId,CcmHouseDangerous ccmHouseDangerous, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDangerous : " + String.valueOf(ccmHouseDangerous));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseDangerous.setCheckUser(sessionUser);
		Page<CcmHouseDangerous> page = ccmHouseDangerousService.findPage(new Page<CcmHouseDangerous>(req, resp), ccmHouseDangerous);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取危险品从业人员信息 getDangerous
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/getDangerous", method = RequestMethod.GET)
	public CcmRestResult getDangerous(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHouseDangerous entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseDangerousService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseDangerousService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseDangerous();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	/**
	 * @see  保存重点上访人员信息 savePetition
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/savePetition", method = RequestMethod.POST)
	public CcmRestResult savePetition(String userId,HttpServletRequest req,CcmHousePetition ccmHousePetition, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePetition : " + String.valueOf(ccmHousePetition));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHousePetition)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHousePetition.getId() != null && !"".equals(ccmHousePetition.getId())) {
			CcmHousePetition ccmHousePetitionDB = ccmHousePetitionService.get(ccmHousePetition.getId());
			if (ccmHousePetitionDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmHousePetition.getCreateBy()== null) {
			ccmHousePetition.setCreateBy(new User(userId));
		}
		ccmHousePetition.setUpdateBy(new User(userId));
		ccmHousePetitionService.save(ccmHousePetition);
		// 更新 当前人 是重点上访人员
		System.out.println(ccmHousePetition.getPeopleId());
		CcmPeople ccmPop = ccmPeopleService.get(ccmHousePetition.getPeopleId());
		ccmPop.setIsVisit(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存重点上访人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHousePetition);
		
		return result;
	}
	/**
	 * @see  查询重点上访人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryPetition", method = RequestMethod.GET)
	public CcmRestResult queryPetition(String userId,CcmHousePetition ccmHousePetition, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePetition : " + String.valueOf(ccmHousePetition));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHousePetition.setCheckUser(sessionUser);
		Page<CcmHousePetition> page = ccmHousePetitionService.findPage(new Page<CcmHousePetition>(req, resp), ccmHousePetition);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取重点上访人员信息 getPetition
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/getPetition", method = RequestMethod.GET)
	public CcmRestResult getPetition(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmHousePetition entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHousePetitionService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHousePetitionService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHousePetition();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	/**
	 * @see  保存留守人员信息 saveBehind
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/saveBehind", method = RequestMethod.POST)
	public CcmRestResult saveBehind(String userId,HttpServletRequest req,CcmPopBehind ccmPopBehind, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopBehind : " + String.valueOf(ccmPopBehind));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmPopBehind)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmPopBehind.getId() != null && !"".equals(ccmPopBehind.getId())) {
			CcmPopBehind ccmPopBehindDB = ccmPopBehindService.get(ccmPopBehind.getId());
			if (ccmPopBehindDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			} 
		}
		if (ccmPopBehind.getCreateBy()== null) {
			ccmPopBehind.setCreateBy(new User(userId));
		}
		ccmPopBehind.setUpdateBy(new User(userId));
		ccmPopBehindService.save(ccmPopBehind);
		// 更新 当前人 是留守人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmPopBehind.getPeopleId());
		ccmPop.setIsBehind(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存留守人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmPopBehind);
		
		return result;
	}
	/**
	 * @see  查询留守人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-09
	 */
	@ResponseBody
	@RequestMapping(value="/queryBehind", method = RequestMethod.GET)
	public CcmRestResult queryBehind(String userId,CcmPopBehind ccmPopBehind, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopBehind : " + String.valueOf(ccmPopBehind));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmPopBehind.setCheckUser(sessionUser);
		Page<CcmPopBehind> page = ccmPopBehindService.findPage(new Page<CcmPopBehind>(req, resp), ccmPopBehind);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  获取留守人员信息 getBehind
	 * @param 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/getBehind", method = RequestMethod.GET)
	public CcmRestResult getBehind(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		
		CcmPopBehind entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPopBehindService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmPopBehindService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmPopBehind();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		
		result.setCode(CcmRestType.OK);
		result.setResult(entity);
		
		return result;
	}
	/**
	 * @see  查询特殊关怀人员信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-24
	 */
	@ResponseBody
	@RequestMapping(value="/queryCare", method = RequestMethod.GET)
	public CcmRestResult queryCare(String userId,CcmPeople ccmPeople, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String specialCareTypeStr = "";
		if (ccmPeople.getSpecialCareType() != null && !"".equals(ccmPeople.getSpecialCareType())) {
			String[] specialCareTypes = ccmPeople.getSpecialCareType().split(",");
			for (int i = 0; i < specialCareTypes.length; i++) {// 去掉空字符串和null字符串
				if (specialCareTypes[i] != null && !"".equals(specialCareTypes[i])
						&& !"null".equals(specialCareTypes[i])) {
					specialCareTypeStr = specialCareTypeStr + specialCareTypes[i] + ",";
				}
			}
			ccmPeople.setSpecialCareTypes(specialCareTypeStr.split(","));
		}
		if ("".equals(specialCareTypeStr)) {// 传过来值中没有有效的内容，则查询全部
			Dict dict = new Dict();
			dict.setType("pop_special_care_type");
			List<Dict> dictList = dictService.findList(dict);
			String[] specialCareTypes = new String[dictList.size()];
			for (int i = 0; i < specialCareTypes.length; i++) {
				specialCareTypes[i] = dictList.get(i).getValue();
			}
			ccmPeople.setSpecialCareTypes(specialCareTypes);
		}
		ccmPeople.setCheckUser(sessionUser);
		Page<CcmPeople> page = ccmPeopleService.findPage(new Page<CcmPeople>(req, resp), ccmPeople);
		
		
		//
		
		List<CcmPeople> list = page.getList();
		//
		CcmPeople ccmPeople2 = new CcmPeople();
		String[] listLimite = new String[list.size()];
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				listLimite[i]=list.get(i).getId();
			}
			ccmPeople2.setListLimite(listLimite);
			List<CcmPeople> list2 = ccmPeopleService.findListLimite(ccmPeople2);//数组查询id
            String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
            for(int f=0;f<list2.size();f++){
                if (StringUtils.isNotEmpty(list2.get(f).getImages())) {
                    list2.get(f).setImages(fileUrl + list2.get(f).getImages());
                }
            }
			page.setList(list2);
		}
		
		
		
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	/**
	 * @see  查询老年人信息
	 * @param 
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/queryOlder", method = RequestMethod.GET)
	public CcmRestResult queryOlder(String userId,CcmPeople ccmPeople, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		long a = System.currentTimeMillis();
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 0-PlmTypes.OLD_AGE);
		ccmPeople.setBirthday(calendar.getTime());
		ccmPeople.setCheckUser(sessionUser);
		Page<CcmPeople> page = ccmPeopleService.findOlderPage(new Page<CcmPeople>(req, resp), ccmPeople);
		System.out.println("第一个使用了" + (System.currentTimeMillis() - a) + "ms");
	
		List<CcmPeople> list = page.getList();
		CcmPeople ccmPeople2 = new CcmPeople();
		String[] listLimite = new String[list.size()];
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				listLimite[i]=list.get(i).getId();
			}
			ccmPeople2.setListLimite(listLimite);
			ccmPeople2.setCheckUser(sessionUser);
			List<CcmPeople> list2 = ccmPeopleService.findListLimite_V2(ccmPeople2);//数组查询id
            String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
            for(int f=0;f<list2.size();f++){
                if (StringUtils.isNotEmpty(list2.get(f).getImages())) {
                    list2.get(f).setImages(fileUrl + list2.get(f).getImages());
                }
            }
			page.setList(list2);
		}
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		System.out.println("第二个使用了" + (System.currentTimeMillis() - a) + "ms");
		return result;
	}
	/**
	 * @see  查询党员信息
	 * @param 
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-22
	 */
	@ResponseBody
	@RequestMapping(value="/queryCommunist", method = RequestMethod.GET)
	public CcmRestResult queryCommunist(String userId,CcmPeople ccmPeople, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmPeople.setCheckUser(sessionUser);
		Page<CcmPeople> page = ccmPeopleService.findCommunistPage(new Page<CcmPeople>(req, resp), ccmPeople);
	
		//
		List<CcmPeople> list = page.getList();
		//
		CcmPeople ccmPeople2 = new CcmPeople();
		String[] listLimite = new String[list.size()];
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				listLimite[i]=list.get(i).getId();
			}
			ccmPeople2.setListLimite(listLimite);
			List<CcmPeople> list2 = ccmPeopleService.findListLimite(ccmPeople2);//数组查询id
            String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
            for(int f=0;f<list2.size();f++){
                if (StringUtils.isNotEmpty(list2.get(f).getImages())) {
                    list2.get(f).setImages(fileUrl + list2.get(f).getImages());
                }
            }
			page.setList(list2);
		}
		
		
		
		result.setCode(CcmRestType.OK);
		if(page.getList()==null||page.getList().size()<=0) {
			result.setResult("");
		}else {
			result.setResult(page.getList());	
		}
		return result;
	}
	
	/**********           以下是上下级平台数据同步用                  ***************/

	/**
	 * @see  保存人口信息（支持新增和编辑,数据同步用）
	 * @author pengjianqiang
	 * @version 2018-05-17
	 */
	@ResponseBody
	@RequestMapping(value="/savePeopleSyn", method = RequestMethod.POST)
	public CcmRestResult savePeopleSyn(String userId,CcmPeople ccmPeople,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmPeople.setCreateBy(user);
		ccmPeople.setUpdateBy(user);
		
		ccmPeopleService.save(ccmPeople);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除人口信息（数据同步用）
	 * @author pengjianqiang
	 * @version 2018-05-17
	 */
	@ResponseBody
	@RequestMapping(value="/deletePeopleSyn", method = RequestMethod.POST)
	public CcmRestResult deletePeopleSyn(String userId,CcmPeople ccmPeople,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPeople : " + String.valueOf(ccmPeople));
		CcmRestResult result = new CcmRestResult();
		ccmPeopleService.delete(ccmPeople);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	
	
	/**
	 * @see  保存留守人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-22
	 */
	@ResponseBody
	@RequestMapping(value="/saveBehindSyn", method = RequestMethod.POST)
	public CcmRestResult saveBehindSyn(String userId,CcmPopBehind ccmPopBehind,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopBehind : " + String.valueOf(ccmPopBehind));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmPopBehind.setCreateBy(user);
		ccmPopBehind.setUpdateBy(user);
		
		ccmPopBehindService.save(ccmPopBehind);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除留守人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-22
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBehindSyn", method = RequestMethod.POST)
	public CcmRestResult deleteBehindSyn(String userId,CcmPopBehind ccmPopBehind,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopBehind : " + String.valueOf(ccmPopBehind));
		CcmRestResult result = new CcmRestResult();
		ccmPopBehindService.delete(ccmPopBehind);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	
	
	/**
	 * @see  保存安置帮教人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/saveReleaseSyn", method = RequestMethod.POST)
	public CcmRestResult saveReleaseSyn(String userId,CcmHouseRelease ccmHouseRelease,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRelease : " + String.valueOf(ccmHouseRelease));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseRelease.setCreateBy(user);
		ccmHouseRelease.setUpdateBy(user);
		
		ccmHouseReleaseService.save(ccmHouseRelease);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除安置帮教人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/deleteReleaseSyn", method = RequestMethod.POST)
	public CcmRestResult deleteReleaseSyn(String userId,CcmHouseRelease ccmHouseRelease,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRelease : " + String.valueOf(ccmHouseRelease));
		CcmRestResult result = new CcmRestResult();
		ccmHouseReleaseService.delete(ccmHouseRelease);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存社区矫正人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/saveRectificationSyn", method = RequestMethod.POST)
	public CcmRestResult saveReleaseSyn(String userId,CcmHouseRectification ccmHouseRectification,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRectification : " + String.valueOf(ccmHouseRectification));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseRectification.setCreateBy(user);
		ccmHouseRectification.setUpdateBy(user);
		
		ccmHouseRectificationService.save(ccmHouseRectification);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除社区矫正人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/deleteRectificationSyn", method = RequestMethod.POST)
	public CcmRestResult deleteReleaseSyn(String userId,CcmHouseRectification ccmHouseRectification,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseRectification : " + String.valueOf(ccmHouseRectification));
		CcmRestResult result = new CcmRestResult();
		ccmHouseRectificationService.delete(ccmHouseRectification);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存肇事肇祸等严重精神障碍患者人口信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/savePsychogenySyn", method = RequestMethod.POST)
	public CcmRestResult savePsychogenySyn(String userId,CcmHousePsychogeny ccmHousePsychogeny,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePsychogeny : " + String.valueOf(ccmHousePsychogeny));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHousePsychogeny.setCreateBy(user);
		ccmHousePsychogeny.setUpdateBy(user);
		
		ccmHousePsychogenyService.save(ccmHousePsychogeny);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除肇事肇祸等严重精神障碍患者人口信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/deletePsychogenySyn", method = RequestMethod.POST)
	public CcmRestResult deletePsychogenySyn(String userId,CcmHousePsychogeny ccmHousePsychogeny,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePsychogeny : " + String.valueOf(ccmHousePsychogeny));
		CcmRestResult result = new CcmRestResult();
		ccmHousePsychogenyService.delete(ccmHousePsychogeny);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存吸毒人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/saveDrugsSyn", method = RequestMethod.POST)
	public CcmRestResult saveDrugsSyn(String userId,CcmHouseDrugs ccmHouseDrugs,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDrugs : " + String.valueOf(ccmHouseDrugs));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseDrugs.setCreateBy(user);
		ccmHouseDrugs.setUpdateBy(user);
		
		ccmHouseDrugsService.save(ccmHouseDrugs);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除吸毒人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-23
	 */
	@ResponseBody
	@RequestMapping(value="/deleteDrugsSyn", method = RequestMethod.POST)
	public CcmRestResult deleteDrugsSyn(String userId,CcmHouseDrugs ccmHouseDrugs,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDrugs : " + String.valueOf(ccmHouseDrugs));
		CcmRestResult result = new CcmRestResult();
		ccmHouseDrugsService.delete(ccmHouseDrugs);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存艾滋病患者信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/saveAidsSyn", method = RequestMethod.POST)
	public CcmRestResult saveAidsSyn(String userId,CcmHouseAids ccmHouseAids,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseAids : " + String.valueOf(ccmHouseAids));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseAids.setCreateBy(user);
		ccmHouseAids.setUpdateBy(user);
		
		ccmHouseAidsService.save(ccmHouseAids);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除艾滋病患者信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAidsSyn", method = RequestMethod.POST)
	public CcmRestResult deleteAidsSyn(String userId,CcmHouseAids ccmHouseAids,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseAids : " + String.valueOf(ccmHouseAids));
		CcmRestResult result = new CcmRestResult();
		ccmHouseAidsService.delete(ccmHouseAids);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存重点上访人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/savePetitionSyn", method = RequestMethod.POST)
	public CcmRestResult savePetitionSyn(String userId,CcmHousePetition ccmHousePetition,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePetition : " + String.valueOf(ccmHousePetition));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHousePetition.setCreateBy(user);
		ccmHousePetition.setUpdateBy(user);
		
		ccmHousePetitionService.save(ccmHousePetition);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除重点上访人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/deletePetitionSyn", method = RequestMethod.POST)
	public CcmRestResult deletePetitionSyn(String userId,CcmHousePetition ccmHousePetition,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHousePetition : " + String.valueOf(ccmHousePetition));
		CcmRestResult result = new CcmRestResult();
		ccmHousePetitionService.delete(ccmHousePetition);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存涉教人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/saveHeresySyn", method = RequestMethod.POST)
	public CcmRestResult saveHeresySyn(String userId,CcmHouseHeresy ccmHouseHeresy,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseHeresy : " + String.valueOf(ccmHouseHeresy));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseHeresy.setCreateBy(user);
		ccmHouseHeresy.setUpdateBy(user);
		
		ccmHouseHeresyService.save(ccmHouseHeresy);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除涉教人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/deleteHeresySyn", method = RequestMethod.POST)
	public CcmRestResult deleteHeresySyn(String userId,CcmHouseHeresy ccmHouseHeresy,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseHeresy : " + String.valueOf(ccmHouseHeresy));
		CcmRestResult result = new CcmRestResult();
		ccmHouseHeresyService.delete(ccmHouseHeresy);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存危险品从业人员信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/saveDangerousSyn", method = RequestMethod.POST)
	public CcmRestResult saveDangerousSyn(String userId,CcmHouseDangerous ccmHouseDangerous,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDangerous : " + String.valueOf(ccmHouseDangerous));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseDangerous.setCreateBy(user);
		ccmHouseDangerous.setUpdateBy(user);
		
		ccmHouseDangerousService.save(ccmHouseDangerous);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除危险品从业人员信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/deleteDangerousSyn", method = RequestMethod.POST)
	public CcmRestResult deleteDangerousSyn(String userId,CcmHouseDangerous ccmHouseDangerous,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDangerous : " + String.valueOf(ccmHouseDangerous));
		CcmRestResult result = new CcmRestResult();
		ccmHouseDangerousService.delete(ccmHouseDangerous);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存重点青少年信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/saveKymSyn", method = RequestMethod.POST)
	public CcmRestResult saveKymSyn(String userId,CcmHouseKym ccmHouseKym,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseKym : " + String.valueOf(ccmHouseKym));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmHouseKym.setCreateBy(user);
		ccmHouseKym.setUpdateBy(user);
		
		ccmHouseKymService.save(ccmHouseKym);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除重点青少年信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-28
	 */
	@ResponseBody
	@RequestMapping(value="/deleteKymSyn", method = RequestMethod.POST)
	public CcmRestResult deleteKymSyn(String userId,CcmHouseKym ccmHouseKym,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseKym : " + String.valueOf(ccmHouseKym));
		CcmRestResult result = new CcmRestResult();
		ccmHouseKymService.delete(ccmHouseKym);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	/**
	 * @see  保存危害国家安全活动嫌疑人员信息saveCcmHarmNationalSecurity
	 * @param
	 * @author ljd
	 * @version 2018-02-26
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/saveCcmHarmNationalSecurity", method = RequestMethod.POST)
	public CcmRestResult saveCcmHarmNationalSecurity(String userId,HttpServletRequest req, CcmHarmNationalSecurity ccmHarmNationalSecurity, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHarmNationalSecurity : " + String.valueOf(ccmHarmNationalSecurity));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHarmNationalSecurity)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHarmNationalSecurity.getId() != null && !"".equals(ccmHarmNationalSecurity.getId())) {
			CcmHarmNationalSecurity ccmHarmNationalSecurityDB = ccmHarmNationalSecurityService.get(ccmHarmNationalSecurity.getId());
			if (ccmHarmNationalSecurityDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		if (ccmHarmNationalSecurity.getCreateBy()== null) {
			ccmHarmNationalSecurity.setCreateBy(new User(userId));
		}
		ccmHarmNationalSecurity.setUpdateBy(new User(userId));

		ccmHarmNationalSecurityService.save(ccmHarmNationalSecurity);
		// 更新 当前人 是 吸毒人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHarmNationalSecurity.getPeopleId());
		ccmPop.setIsDrugs(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "危害国家安全活动嫌疑人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHarmNationalSecurity);

		return result;
	}

	/**
	 * @see  查询危害国家安全活动嫌疑人员
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/queryCcmHarmNationalSecurity", method = RequestMethod.GET)
	public CcmRestResult queryCcmHarmNationalSecurity(Integer pageNo,String userId,CcmHarmNationalSecurity ccmHarmNationalSecurity, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHarmNationalSecurity : " + String.valueOf(ccmHarmNationalSecurity));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHarmNationalSecurity.setCheckUser(sessionUser);
		Page<CcmHarmNationalSecurity> page = ccmHarmNationalSecurityService.findPage(new Page<CcmHarmNationalSecurity>(req, resp), ccmHarmNationalSecurity);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		page.setPageNo(pageNo);
		result.setResult(page.getList());

		return result;
	}
	/**
	 * @see  获取危害国家安全活动嫌疑人员详细信息 getDrugs
	 * @param
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/getCcmHarmNationalSecurity", method = RequestMethod.GET)
	public CcmRestResult getCcmHarmNationalSecurity(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
													@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		CcmHarmNationalSecurity entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHarmNationalSecurityService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHarmNationalSecurityService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHarmNationalSecurity();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		entity.setOffId(entity.getReportOffice().getId());
		entity.setOffName(entity.getReportOffice().getName());

        result.setCode(CcmRestType.OK);
		result.setResult(entity);
		return result;
	}

	/**
	 * @see  保存严重刑事犯罪活动嫌疑人员信息saveCcmSeriousCriminalOffense
	 * @param
	 * @author ljd
	 * @version 2018-02-26
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/saveCcmSeriousCriminalOffense", method = RequestMethod.POST)
	public CcmRestResult saveCcmSeriousCriminalOffense(String userId,HttpServletRequest req, CcmSeriousCriminalOffense ccmSeriousCriminalOffense, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmSeriousCriminalOffense : " + String.valueOf(ccmSeriousCriminalOffense));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmSeriousCriminalOffense)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmSeriousCriminalOffense.getId() != null && !"".equals(ccmSeriousCriminalOffense.getId())) {
			CcmSeriousCriminalOffense ccmSeriousCriminalOffenseDB = ccmSeriousCriminalOffenseService.get(ccmSeriousCriminalOffense.getId());
			if (ccmSeriousCriminalOffenseDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		if (ccmSeriousCriminalOffense.getCreateBy()== null) {
			ccmSeriousCriminalOffense.setCreateBy(new User(userId));
		}
		ccmSeriousCriminalOffense.setUpdateBy(new User(userId));

		ccmSeriousCriminalOffenseService.save(ccmSeriousCriminalOffense);
		// 更新 当前人 是 吸毒人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmSeriousCriminalOffense.getPeopleId());
		ccmPop.setIsDrugs(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存严重刑事犯罪活动嫌疑人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmSeriousCriminalOffense);

		return result;
	}

	/**
	 * @see  查询严重刑事犯罪活动嫌疑人员
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/queryCcmSeriousCriminalOffense", method = RequestMethod.GET)
	public CcmRestResult queryCcmSeriousCriminalOffense(Integer pageNo,String userId,CcmSeriousCriminalOffense ccmSeriousCriminalOffense, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmSeriousCriminalOffense : " + String.valueOf(ccmSeriousCriminalOffense));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmSeriousCriminalOffense.setCheckUser(sessionUser);
		Page<CcmSeriousCriminalOffense> page = ccmSeriousCriminalOffenseService.findPage(new Page<CcmSeriousCriminalOffense>(req, resp), ccmSeriousCriminalOffense);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		page.setPageNo(pageNo);
		result.setResult(page.getList());

		return result;
	}
	/**
	 * @see  获取严重刑事犯罪活动嫌疑人员详细信息
	 * @param
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/getCcmSeriousCriminalOffense", method = RequestMethod.GET)
	public CcmRestResult getCcmSeriousCriminalOffense(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
													  @RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		CcmSeriousCriminalOffense entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmSeriousCriminalOffenseService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmSeriousCriminalOffenseService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmSeriousCriminalOffense();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		result.setCode(CcmRestType.OK);
		result.setResult(entity);

		return result;
	}


	/**
	 * @see  保存闹事行凶报复嫌疑人员信息saveCcmHouseDispute
	 * @param
	 * @author ljd
	 * @version 2018-02-26
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/saveCcmHouseDispute", method = RequestMethod.POST)
	public CcmRestResult saveCcmHouseDispute(String userId,HttpServletRequest req, CcmHouseDispute ccmHouseDispute, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDispute : " + String.valueOf(ccmHouseDispute));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseDispute)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseDispute.getId() != null && !"".equals(ccmHouseDispute.getId())) {
			CcmHouseDispute ccmHouseDisputeDB = ccmHouseDisputeService.get(ccmHouseDispute.getId());
			if (ccmHouseDisputeDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		if (ccmHouseDispute.getCreateBy()== null) {
			ccmHouseDispute.setCreateBy(new User(userId));
		}
		ccmHouseDispute.setUpdateBy(new User(userId));

		ccmHouseDisputeService.save(ccmHouseDispute);
		// 更新 当前人 是 吸毒人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDispute.getPeopleId());
		ccmPop.setIsDrugs(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "保存闹事行凶报复嫌疑人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseDispute);

		return result;
	}

	/**
	 * @see  查询闹事行凶报复嫌疑人员
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/queryCcmHouseDispute", method = RequestMethod.GET)
	public CcmRestResult queryCcmHouseDispute(Integer pageNo,String userId,CcmHouseDispute ccmHouseDispute, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDispute : " + String.valueOf(ccmHouseDispute));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseDispute.setCheckUser(sessionUser);
		Page<CcmHouseDispute> page = ccmHouseDisputeService.findPage(new Page<CcmHouseDispute>(req, resp), ccmHouseDispute);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		page.setPageNo(pageNo);
		result.setResult(page.getList());

		return result;
	}
	/**
	 * @see  获取闹事行凶报复嫌疑人员详细信息
	 * @param
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/getCcmHouseDispute", method = RequestMethod.GET)
	public CcmRestResult getCcmHouseDispute(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
											@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		CcmHouseDispute entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseDisputeService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseDisputeService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseDispute();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		result.setCode(CcmRestType.OK);
		result.setResult(entity);

		return result;
	}

	/**
	 * @see  保存故意违法刑满释放不足5年人员信息saveCcmHouseDeliberatelyIllegal
	 * @param
	 * @author ljd
	 * @version 2018-02-26
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/saveCcmHouseDeliberatelyIllegal", method = RequestMethod.POST)
	public CcmRestResult saveCcmHouseDeliberatelyIllegal(String userId,HttpServletRequest req, CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, Model model, RedirectAttributes redirectAttributes) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDeliberatelyIllegal : " + String.valueOf(ccmHouseDeliberatelyIllegal));
		CcmRestResult result = new CcmRestResult();
		if (!beanValidator(model, ccmHouseDeliberatelyIllegal)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmHouseDeliberatelyIllegal.getId() != null && !"".equals(ccmHouseDeliberatelyIllegal.getId())) {
			CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegalDB = ccmHouseDeliberatelyIllegalService.get(ccmHouseDeliberatelyIllegal.getId());
			if (ccmHouseDeliberatelyIllegalDB == null) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		if (ccmHouseDeliberatelyIllegal.getCreateBy()== null) {
			ccmHouseDeliberatelyIllegal.setCreateBy(new User(userId));
		}
		ccmHouseDeliberatelyIllegal.setUpdateBy(new User(userId));

		ccmHouseDeliberatelyIllegalService.save(ccmHouseDeliberatelyIllegal);
		// 更新 当前人 是 吸毒人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDeliberatelyIllegal.getPeopleId());
		ccmPop.setIsDrugs(1);
		ccmPop.setUpdateBy(new User(userId));
		ccmPeopleService.save(ccmPop);
		addMessage(redirectAttributes, "故意违法刑满释放不足5年人员成功");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseDeliberatelyIllegal);

		return result;
	}

	/**
	 * @see  查询故意违法刑满释放不足5年人员列表
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/queryCcmHouseDeliberatelyIllegal", method = RequestMethod.GET)
	public CcmRestResult queryCcmHouseDeliberatelyIllegal(Integer pageNo,String userId,CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmHouseDeliberatelyIllegal : " + String.valueOf(ccmHouseDeliberatelyIllegal));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		ccmHouseDeliberatelyIllegal.setCheckUser(sessionUser);
		Page<CcmHouseDeliberatelyIllegal> page = ccmHouseDeliberatelyIllegalService.findPage(new Page<CcmHouseDeliberatelyIllegal>(req, resp), ccmHouseDeliberatelyIllegal);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
		result.setCode(CcmRestType.OK);
		page.setPageNo(pageNo);
		result.setResult(page.getList());

		return result;
	}
	/**
	 * @see  获取故意违法刑满释放不足5年人员详细信息
	 * @param
	 * @author LJD
	 * @version 2019-8-1
	 */
	@ResponseBody
	@RequestMapping(value="/getCcmHouseDeliberatelyIllegal", method = RequestMethod.GET)
	public CcmRestResult getCcmHouseDeliberatelyIllegal(String userId,HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "id", required = false) String id,
														@RequestParam(value = "peopleId", required = false) String peopleId) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  人员id : " + id + "  peopleId : " + peopleId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		CcmHouseDeliberatelyIllegal entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseDeliberatelyIllegalService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseDeliberatelyIllegalService.getPeopleALL(peopleId);
		}else{
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		if (entity == null) {
			entity = new CcmHouseDeliberatelyIllegal();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		result.setCode(CcmRestType.OK);
		result.setResult(entity);

		return result;
	}


	/**
	 * 根据sn号获取重点人员信息
	 * 
	 * @param snNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfoBySnNum", method = RequestMethod.GET)
	public CcmRestResult getInfo(@RequestParam String snNum) {
		CcmRestResult result = new CcmRestResult();
		CcmPeople ccmPeople = ccmPeopleService.getInfoBySnNum(snNum);
		if(ccmPeople != null) {	
			result.setCount(1);
			result.setCode(0);
			result.setResult(ccmPeople);
			result.setReturnFlag(true);
		} else {
			result.setCode(-1);
			result.setReturnFlag(false);
		}
		return result;
	}

	
	
}