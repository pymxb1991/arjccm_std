package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmMobileDeviceService;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityComponents;
import com.arjjs.ccm.modules.ccm.citycomponents.service.CcmCityComponentsService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventAmbi;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventKacc;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventAmbiService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventKaccService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseAids;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDangerous;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDrugs;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseHeresy;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseKym;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHousePetition;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHousePsychogeny;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseRectification;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseRelease;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseSchoolrim;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseAidsService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseDangerousService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseDrugsService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseHeresyService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseKymService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHousePetitionService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHousePsychogenyService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseRectificationService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseReleaseService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseSchoolrimService;
import com.arjjs.ccm.modules.ccm.line.entity.CcmLineProtect;
import com.arjjs.ccm.modules.ccm.line.service.CcmLineProtectService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgNpse;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSocialorg;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseService;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgSocialorgService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmTracingpointService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopBehind;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopBehindService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.tool.Tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 事件接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Controller
@RequestMapping(value = "${appPath}/rest/event")
public class CcmRestUploadLog extends BaseController {


	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	@Autowired
	private CcmHouseBuildmanageService ccmHouseBuildmanageService;
	@Autowired
	private CcmPopTenantService ccmPopTenantService;
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmOrgNpseService ccmOrgNpseService;
	@Autowired
	private CcmPopBehindService ccmPopBehindService;
	@Autowired
	private CcmHouseReleaseService ccmHouseReleaseService;
	@Autowired
	private CcmHouseRectificationService ccmHouseRectificationService;
	@Autowired
	private CcmHousePsychogenyService ccmHousePsychogenyService;
	@Autowired
	private CcmHouseDrugsService ccmHouseDrugsService;
	@Autowired
	private CcmHouseAidsService ccmHouseAidsService;
	@Autowired
	private CcmHousePetitionService ccmHousePetitionService;
	@Autowired
	private CcmHouseHeresyService ccmHouseHeresyService;
	@Autowired
	private CcmHouseDangerousService ccmHouseDangerousService;
	@Autowired
	private CcmHouseKymService ccmHouseKymService;
	@Autowired
	private CcmHouseSchoolrimService ccmHouseSchoolrimService;
	@Autowired
	private CcmCityComponentsService ccmCityComponentsService;
	@Autowired
	private CcmOrgSocialorgService ccmOrgSocialorgService;
	@Autowired
	private CcmLineProtectService ccmLineProtectService;
	@Autowired
	private CcmEventAmbiService ccmEventAmbiService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@Autowired
	private CcmEventKaccService ccmEventKaccService;
	@Autowired
	private CcmMobileDeviceService ccmMobileDeviceService;
	@Autowired
	private CcmTracingpointService ccmTracingpointService;
	
	
	
	/**
	 * 方法说明：定时上传本级的数据至上级平台（通过日志表同步数据）
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-12
	 */
	public void uploadLog() throws Exception {

		/**    1、从sysconfig中获取上级平台接口信息，进行接口登录     **/
		SysConfig sysConfig = sysConfigService.get("upper_system_config");
		JSONObject jsonObject = JSONObject.fromObject(sysConfig.getParamStr());
		String url = jsonObject.getString("url");
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		System.out.println(url);
		if (url == null || "".equals(url)) {
			return;
		}
		
		
		/**    2、查询待上传的数据日志     **/
		CcmUploadLog ccmUploadLog = new CcmUploadLog();
		ccmUploadLog.setUploadStatus("1");
		List<CcmUploadLog> ccmUploadLogList = ccmUploadLogService.findList(ccmUploadLog);
		if (ccmUploadLogList == null || ccmUploadLogList.size() == 0) {
			return;
		}
		
		
		/**    3、登录上级平台，获取userId     **/
		//登录上级平台
		String urlLogin = url + "/app/rest/login/login?loginName=" + username + "&password=" + password;
		System.out.println(urlLogin);
		String loginReturn = Tool.postRestReturn(urlLogin);
		//{"code":0,"result":{"id":"1","isNewRecord":false,"remarks":"最高管理员","createDate":"2013-05-27 08:00:00","updateDate":"2018-04-02 19:28:57","loginName":"admin","no":"0001","name":"系统管理员","email":"admin@163.com","phone":"78654","mobile":"8675","userType":"1","loginIp":"192.168.1.102","loginDate":"2018-05-12 15:44:42","loginFlag":"1","photo":"/arjccm/userfiles/1/images/photo/2018/03/guihuada.gif","oldLoginIp":"192.168.1.102","oldLoginDate":"2018-05-12 15:44:42","admin":true,"roleNames":""},"fileServerUrl":"http://192.168.11.205:8080","returnFlag":false}
		System.out.println(loginReturn);
		String userId = "";
		if (loginReturn != null && !"".equals(loginReturn)) {
			JSONObject jsonLogin = JSONObject.fromObject(loginReturn);
			int code = jsonLogin.getInt("code");
			if (code != CcmRestType.OK) {
				return;
			}
			String resultContent = jsonLogin.getString("result");
			JSONObject jsonLoginUser = JSONObject.fromObject(resultContent);
			userId = jsonLoginUser.getString("id");
		}
		
		/**    4、按上传数据日志的类型，对数据分别进行上传     **/
		for (int i = 0; i < ccmUploadLogList.size(); i++) {
			CcmUploadLog uploadLog = ccmUploadLogList.get(i);
			if ("ccm_house_buildmanage".equals(uploadLog.getTableName())) {//楼栋
				uploadBuilding(url, userId, uploadLog);
			} else if ("ccm_pop_tenant".equals(uploadLog.getTableName())) {//房屋
				uploadHouse(url, userId, uploadLog);
			} else if ("ccm_people".equals(uploadLog.getTableName())) {//人口
				uploadPeople(url, userId, uploadLog);
			} else if ("ccm_org_npse".equals(uploadLog.getTableName())) {//非公有制经济组织
				uploadNpse(url, userId, uploadLog);
			} else if ("ccm_pop_behind".equals(uploadLog.getTableName())) {//留守人员
				uploadBehind(url, userId, uploadLog);
			} else if ("ccm_house_release".equals(uploadLog.getTableName())) {//安置帮教人员
				uploadRelease(url, userId, uploadLog);
			} else if ("ccm_house_rectification".equals(uploadLog.getTableName())) {//社区矫正人员
				uploadRectification(url, userId, uploadLog);
			} else if ("ccm_house_psychogeny".equals(uploadLog.getTableName())) {//肇事肇祸等严重精神障碍患者
				uploadPsychogeny(url, userId, uploadLog);
			} else if ("ccm_house_drugs".equals(uploadLog.getTableName())) {//吸毒人员
				uploadDrugs(url, userId, uploadLog);
			} else if ("ccm_house_aids".equals(uploadLog.getTableName())) {//艾滋人员
				uploadAids(url, userId, uploadLog);
			} else if ("ccm_house_petition".equals(uploadLog.getTableName())) {//重点上访人员
				uploadPetition(url, userId, uploadLog);
			} else if ("ccm_house_heresy".equals(uploadLog.getTableName())) {//涉教人员
				uploadHeresy(url, userId, uploadLog);
			} else if ("ccm_house_dangerous".equals(uploadLog.getTableName())) {//危险品从业人员
				uploadDangerous(url, userId, uploadLog);
			} else if ("ccm_house_kym".equals(uploadLog.getTableName())) {//重点青少年人员
				uploadKym(url, userId, uploadLog);
			} else if ("ccm_house_schoolrim".equals(uploadLog.getTableName())) {//学校
				uploadSchool(url, userId, uploadLog);
			} else if ("ccm_city_components".equals(uploadLog.getTableName())) {//城市部件
				uploadCityComponents(url, userId, uploadLog);
			} else if ("ccm_org_socialorg".equals(uploadLog.getTableName())) {//社会组织
				uploadSocialorg(url, userId, uploadLog);
			} else if ("ccm_line_protect".equals(uploadLog.getTableName())) {//护路护线
				uploadProtect(url, userId, uploadLog);
			} else if ("ccm_event_ambi".equals(uploadLog.getTableName())) {//矛盾纠纷
				uploadAmbi(url, userId, uploadLog);
			} else if ("ccm_event_incident".equals(uploadLog.getTableName())) {//案事件、师生案事件、护路护线案事件、命案案事件等
				uploadEventIncident(url, userId, uploadLog);
			} else if ("ccm_event_kacc".equals(uploadLog.getTableName())) {//重点地区排查整治
				uploadEventKacc(url, userId, uploadLog);
			}
			
			
			
			
		}
		
  	 }

  	/**
  	 * 方法说明：上传楼栋数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-12
  	 * @其他：
  	 */
	public void uploadBuilding(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/building/deleteSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseBuildmanage entity = ccmHouseBuildmanageService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();
			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getName()!=null)objSbf.append("&name=").append(URLEncoder.encode(entity.getName(),"utf-8"));
			if(entity.getBuildname()!=null)objSbf.append("&buildname=").append(URLEncoder.encode(entity.getBuildname(),"utf-8"));
			if(entity.getArea()!=null&&entity.getArea().getId()!=null)objSbf.append("&area.id=").append(entity.getArea().getId());
			if(entity.getFloorArea()!=null)objSbf.append("&floorArea=").append(entity.getFloorArea());
			if(entity.getPilesNum()!=null)objSbf.append("&pilesNum=").append(entity.getPilesNum());
			if(entity.getElemNum()!=null)objSbf.append("&elemNum=").append(entity.getElemNum());
			if(entity.getBuildNum()!=null)objSbf.append("&buildNum=").append(entity.getBuildNum());
			if(entity.getBuildPeo()!=null)objSbf.append("&buildPeo=").append(entity.getBuildPeo());
			if(entity.getBuildPname()!=null)objSbf.append("&buildPname=").append(URLEncoder.encode(entity.getBuildPname(),"utf-8"));
			if(entity.getSex()!=null)objSbf.append("&sex=").append(URLEncoder.encode(entity.getSex(),"utf-8"));
			if(entity.getNation()!=null)objSbf.append("&nation=").append(URLEncoder.encode(entity.getNation(),"utf-8"));
			if(entity.getContent()!=null)objSbf.append("&content=").append(URLEncoder.encode(entity.getContent(),"utf-8"));
			if (entity.getBirthday() != null && !"".equals(entity.getBirthday())) {
				String birthday = DateFormatUtils.format(entity.getBirthday(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&birthday=").append(URLEncoder.encode(birthday, "utf-8"));
			}
			if(entity.getEducation()!=null)objSbf.append("&education=").append(URLEncoder.encode(entity.getEducation(),"utf-8"));
			if(entity.getTel()!=null)objSbf.append("&tel=").append(URLEncoder.encode(entity.getTel(),"utf-8"));
			if(entity.getPhone()!=null)objSbf.append("&phone=").append(URLEncoder.encode(entity.getPhone(),"utf-8"));
			if(entity.getResidence()!=null)objSbf.append("&residence=").append(URLEncoder.encode(entity.getResidence(),"utf-8"));
			if(entity.getResidencedetail()!=null)objSbf.append("&residencedetail=").append(URLEncoder.encode(entity.getResidencedetail(),"utf-8"));
			if(entity.getAreaMap()!=null)objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null)objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getImage()!=null)objSbf.append("&image=").append(URLEncoder.encode(entity.getImage(),"utf-8"));
			if(entity.getImages()!=null){
				String urlFiles = entity.getImages();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));

			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/building/saveSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
		
	}

  	/**
  	 * 方法说明：上传房屋数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-17
  	 * @其他：
  	 */
	public void uploadHouse(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/house/deleteSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmPopTenant entity = ccmPopTenantService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getHouseBuild()!=null)objSbf.append("&houseBuild=").append(URLEncoder.encode(entity.getHouseBuild(),"utf-8"));
			if(entity.getHousePlace()!=null)objSbf.append("&housePlace=").append(URLEncoder.encode(entity.getHousePlace(),"utf-8"));
			if(entity.getHousePrup()!=null)objSbf.append("&housePrup=").append(URLEncoder.encode(entity.getHousePrup(),"utf-8"));
			if(entity.getBuildingId()!=null&&entity.getBuildingId().getId()!=null)objSbf.append("&buildingId.id=").append(entity.getBuildingId().getId());
			if(entity.getBuildDoorNum()!=null)objSbf.append("&buildDoorNum=").append(URLEncoder.encode(entity.getBuildDoorNum(),"utf-8"));
			if(entity.getFloorNum()!=null)objSbf.append("&floorNum=").append(URLEncoder.encode(entity.getFloorNum(),"utf-8"));
			if(entity.getDoorNum()!=null)objSbf.append("&doorNum=").append(URLEncoder.encode(entity.getDoorNum(),"utf-8"));
			if(entity.getHouseArea()!=null)objSbf.append("&houseArea=").append(entity.getHouseArea());
			if(entity.getHouseType()!=null)objSbf.append("&houseType=").append(URLEncoder.encode(entity.getHouseType(),"utf-8"));
			if(entity.getIdenCode()!=null)objSbf.append("&idenCode=").append(URLEncoder.encode(entity.getIdenCode(),"utf-8"));
			if(entity.getIdenNum()!=null)objSbf.append("&idenNum=").append(URLEncoder.encode(entity.getIdenNum(),"utf-8"));
			if(entity.getHouseName()!=null)objSbf.append("&houseName=").append(URLEncoder.encode(entity.getHouseName(),"utf-8"));
			if(entity.getHouseTl()!=null)objSbf.append("&houseTl=").append(URLEncoder.encode(entity.getHouseTl(),"utf-8"));
			if(entity.getHouseCur()!=null)objSbf.append("&houseCur=").append(URLEncoder.encode(entity.getHouseCur(),"utf-8"));
			if(entity.getRentPur()!=null)objSbf.append("&rentPur=").append(URLEncoder.encode(entity.getRentPur(),"utf-8"));
			if(entity.getHazard()!=null)objSbf.append("&hazard=").append(URLEncoder.encode(entity.getHazard(),"utf-8"));
			if(entity.getTenantId()!=null)objSbf.append("&tenantId=").append(URLEncoder.encode(entity.getTenantId(),"utf-8"));
			if(entity.getTenantName()!=null)objSbf.append("&tenantName=").append(URLEncoder.encode(entity.getTenantName(),"utf-8"));
			if(entity.getTenantTl()!=null)objSbf.append("&tenantTl=").append(URLEncoder.encode(entity.getTenantTl(),"utf-8"));
			if(entity.getArea()!=null&&entity.getArea().getId()!=null)objSbf.append("&area.id=").append(entity.getArea().getId());
			if(entity.getAreaMap()!=null)objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null)objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getImage()!=null)objSbf.append("&image=").append(URLEncoder.encode(entity.getImage(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/house/saveSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
		
	}

  	/**
  	 * 方法说明：上传人口数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-17
  	 * @其他：
  	 */
	public void uploadPeople(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deletePeopleSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmPeople entity = ccmPeopleService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();
			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getType()!=null)objSbf.append("&type=").append(URLEncoder.encode(entity.getType(),"utf-8"));
			if(entity.getName()!=null)objSbf.append("&name=").append(URLEncoder.encode(entity.getName(),"utf-8"));
			if(entity.getUsedname()!=null)objSbf.append("&usedname=").append(URLEncoder.encode(entity.getUsedname(),"utf-8"));
			if(entity.getSex()!=null)objSbf.append("&sex=").append(URLEncoder.encode(entity.getSex(),"utf-8"));
			if (entity.getBirthday() != null && !"".equals(entity.getBirthday())) {
				String birthday = DateFormatUtils.format(entity.getBirthday(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&birthday=").append(URLEncoder.encode(birthday, "utf-8"));
			}
			if(entity.getNation()!=null)objSbf.append("&nation=").append(URLEncoder.encode(entity.getNation(),"utf-8"));
			if(entity.getCensu()!=null)objSbf.append("&censu=").append(URLEncoder.encode(entity.getCensu(),"utf-8"));
			if(entity.getMarriage()!=null)objSbf.append("&marriage=").append(URLEncoder.encode(entity.getMarriage(),"utf-8"));
			if(entity.getPolitics()!=null)objSbf.append("&politics=").append(URLEncoder.encode(entity.getPolitics(),"utf-8"));
			if(entity.getEducation()!=null)objSbf.append("&education=").append(URLEncoder.encode(entity.getEducation(),"utf-8"));
			if(entity.getBelief()!=null)objSbf.append("&belief=").append(URLEncoder.encode(entity.getBelief(),"utf-8"));
			if(entity.getProfType()!=null)objSbf.append("&profType=").append(URLEncoder.encode(entity.getProfType(),"utf-8"));
			if(entity.getProfession()!=null)objSbf.append("&profession=").append(URLEncoder.encode(entity.getProfession(),"utf-8"));
			if(entity.getServPlace()!=null)objSbf.append("&servPlace=").append(URLEncoder.encode(entity.getServPlace(),"utf-8"));
			if(entity.getDomicile()!=null)objSbf.append("&domicile=").append(URLEncoder.encode(entity.getDomicile(),"utf-8"));
			if(entity.getDomiciledetail()!=null)objSbf.append("&domiciledetail=").append(URLEncoder.encode(entity.getDomiciledetail(),"utf-8"));
			if(entity.getResidence()!=null)objSbf.append("&residence=").append(URLEncoder.encode(entity.getResidence(),"utf-8"));
			if(entity.getResidencedetail()!=null)objSbf.append("&residencedetail=").append(URLEncoder.encode(entity.getResidencedetail(),"utf-8"));
			if(entity.getAreaComId()!=null&&entity.getAreaComId().getId()!=null)objSbf.append("&areaComId.id=").append(entity.getAreaComId().getId());
			if(entity.getAreaGridId()!=null&&entity.getAreaGridId().getId()!=null)objSbf.append("&areaGridId.id=").append(entity.getAreaGridId().getId());
			if(entity.getRoomId()!=null&&entity.getRoomId().getId()!=null)objSbf.append("&roomId.id=").append(entity.getRoomId().getId());
			if(entity.getIsBehind()!=null)objSbf.append("&isBehind=").append(entity.getIsBehind());
			if(entity.getIsRelease()!=null)objSbf.append("&isRelease=").append(entity.getIsRelease());
			if(entity.getIsRectification()!=null)objSbf.append("&isRectification=").append(entity.getIsRectification());
			if(entity.getIsAids()!=null)objSbf.append("&isAids=").append(entity.getIsAids());
			if(entity.getIsPsychogeny()!=null)objSbf.append("&isPsychogeny=").append(entity.getIsPsychogeny());
			if(entity.getIsKym()!=null)objSbf.append("&isKym=").append(entity.getIsKym());
			if(entity.getIsDrugs()!=null)objSbf.append("&isDrugs=").append(entity.getIsDrugs());
			if(entity.getIsVisit()!=null)objSbf.append("&isVisit=").append(entity.getIsVisit());
			if(entity.getIsDangerous()!=null)objSbf.append("&isDangerous=").append(entity.getIsDangerous());
			if(entity.getIsHeresy()!=null)objSbf.append("&isHeresy=").append(entity.getIsHeresy());
			if(entity.getIsMore1()!=null)objSbf.append("&isMore1=").append(entity.getIsMore1());
			if(entity.getIsMore2()!=null)objSbf.append("&isMore2=").append(entity.getIsMore2());
			if(entity.getTypeSpec()!=null)objSbf.append("&typeSpec=").append(entity.getTypeSpec());
			if(entity.getImages()!=null){
				String urlFiles = entity.getImages();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getIdent()!=null)objSbf.append("&ident=").append(URLEncoder.encode(entity.getIdent(),"utf-8"));
			if(entity.getTelephone()!=null)objSbf.append("&telephone=").append(URLEncoder.encode(entity.getTelephone(),"utf-8"));
			if(entity.getUniformlogo()!=null)objSbf.append("&uniformlogo=").append(URLEncoder.encode(entity.getUniformlogo(),"utf-8"));
			if(entity.getAccount()!=null)objSbf.append("&account=").append(URLEncoder.encode(entity.getAccount(),"utf-8"));
			if(entity.getAccountidentity()!=null)objSbf.append("&accountidentity=").append(URLEncoder.encode(entity.getAccountidentity(),"utf-8"));
			if(entity.getAccountname()!=null)objSbf.append("&accountname=").append(URLEncoder.encode(entity.getAccountname(),"utf-8"));
			if(entity.getAccountrelation()!=null)objSbf.append("&accountrelation=").append(URLEncoder.encode(entity.getAccountrelation(),"utf-8"));
			if(entity.getAccounttelephone()!=null)objSbf.append("&accounttelephone=").append(URLEncoder.encode(entity.getAccounttelephone(),"utf-8"));
			if(entity.getPersonType()!=null)objSbf.append("&personType=").append(URLEncoder.encode(entity.getPersonType(),"utf-8"));
			if (entity.getPersonTime() != null && !"".equals(entity.getPersonTime())) {
				String personTime = DateFormatUtils.format(entity.getPersonTime(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&personTime=").append(URLEncoder.encode(personTime, "utf-8"));
			}
			if(entity.getSpecialCareType()!=null)objSbf.append("&specialCareType=").append(URLEncoder.encode(entity.getSpecialCareType(),"utf-8"));
			if(entity.getPersonReason()!=null)objSbf.append("&personReason=").append(URLEncoder.encode(entity.getPersonReason(),"utf-8"));
			if(entity.getFlowRea()!=null)objSbf.append("&flowRea=").append(URLEncoder.encode(entity.getFlowRea(),"utf-8"));
			if(entity.getAccrType()!=null)objSbf.append("&accrType=").append(URLEncoder.encode(entity.getAccrType(),"utf-8"));
			if(entity.getCertNum()!=null)objSbf.append("&certNum=").append(URLEncoder.encode(entity.getCertNum(),"utf-8"));
			if (entity.getRecoDate() != null && !"".equals(entity.getRecoDate())) {
				String recoDate = DateFormatUtils.format(entity.getRecoDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&recoDate=").append(URLEncoder.encode(recoDate, "utf-8"));
			}
			if (entity.getDueDate() != null && !"".equals(entity.getDueDate())) {
				String dueDate = DateFormatUtils.format(entity.getDueDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&dueDate=").append(URLEncoder.encode(dueDate, "utf-8"));
			}
			if(entity.getDomiType()!=null)objSbf.append("&domiType=").append(URLEncoder.encode(entity.getDomiType(),"utf-8"));
			if(entity.getFocuPers()!=null)objSbf.append("&focuPers=").append(entity.getFocuPers());
			if (entity.getTime() != null && !"".equals(entity.getTime())) {
				String time = DateFormatUtils.format(entity.getTime(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&time=").append(URLEncoder.encode(time, "utf-8"));
			}
			if(entity.getCause()!=null)objSbf.append("&cause=").append(URLEncoder.encode(entity.getCause(),"utf-8"));
			if(entity.getExplainelse()!=null)objSbf.append("&explainelse=").append(URLEncoder.encode(entity.getExplainelse(),"utf-8"));
			if(entity.getEsurname()!=null)objSbf.append("&esurname=").append(URLEncoder.encode(entity.getEsurname(),"utf-8"));
			if(entity.getEname()!=null)objSbf.append("&ename=").append(URLEncoder.encode(entity.getEname(),"utf-8"));
			if(entity.getNationality()!=null)objSbf.append("&nationality=").append(URLEncoder.encode(entity.getNationality(),"utf-8"));
			if(entity.getIdenCode()!=null)objSbf.append("&idenCode=").append(URLEncoder.encode(entity.getIdenCode(),"utf-8"));
			if(entity.getIdenNum()!=null)objSbf.append("&idenNum=").append(URLEncoder.encode(entity.getIdenNum(),"utf-8"));
			if (entity.getIdenDate() != null && !"".equals(entity.getIdenDate())) {
				String idenDate = DateFormatUtils.format(entity.getIdenDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&idenDate=").append(URLEncoder.encode(idenDate, "utf-8"));
			}
			if(entity.getPurpose()!=null)objSbf.append("&purpose=").append(URLEncoder.encode(entity.getPurpose(),"utf-8"));
			if (entity.getArriDate() != null && !"".equals(entity.getArriDate())) {
				String arriDate = DateFormatUtils.format(entity.getArriDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&arriDate=").append(URLEncoder.encode(arriDate, "utf-8"));
			}
			if (entity.getDepartDate() != null && !"".equals(entity.getDepartDate())) {
				String departDate = DateFormatUtils.format(entity.getDepartDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&departDate=").append(URLEncoder.encode(departDate, "utf-8"));
			}
			if(entity.getMore1()!=null)objSbf.append("&more1=").append(URLEncoder.encode(entity.getMore1(),"utf-8"));
			if(entity.getMore2()!=null)objSbf.append("&more2=").append(URLEncoder.encode(entity.getMore2(),"utf-8"));
			if(entity.getMore3()!=null)objSbf.append("&more3=").append(URLEncoder.encode(entity.getMore3(),"utf-8"));
			if(entity.getMore4()!=null)objSbf.append("&more4=").append(URLEncoder.encode(entity.getMore4(),"utf-8"));
			if(entity.getMore5()!=null)objSbf.append("&more5=").append(URLEncoder.encode(entity.getMore5(),"utf-8"));
			if(entity.getIsPermanent()!=null)objSbf.append("&isPermanent=").append(URLEncoder.encode(entity.getIsPermanent(),"utf-8"));
			if(entity.getUnsettleReason()!=null)objSbf.append("&unsettleReason=").append(URLEncoder.encode(entity.getUnsettleReason(),"utf-8"));
			if (entity.getUnsettleDate() != null && !"".equals(entity.getUnsettleDate())) {
				String unsettleDate = DateFormatUtils.format(entity.getUnsettleDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&unsettleDate=").append(URLEncoder.encode(unsettleDate, "utf-8"));
			}
			if(entity.getUnsettleCardType()!=null)objSbf.append("&unsettleCardType=").append(URLEncoder.encode(entity.getUnsettleCardType(),"utf-8"));
			if(entity.getUnsettleCardNumber()!=null)objSbf.append("&unsettleCardNumber=").append(URLEncoder.encode(entity.getUnsettleCardNumber(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/savePeopleSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	/**
  	 * 方法说明：上传非公有制经济组织
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-21
  	 * @其他：
  	 */
	public void uploadNpse(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/orgNpse/deleteSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmOrgNpse entity = ccmOrgNpseService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();
			
			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getCompId()!=null)objSbf.append("&compId=").append(URLEncoder.encode(entity.getCompId(),"utf-8"));
			if(entity.getCompName()!=null)objSbf.append("&compName=").append(URLEncoder.encode(entity.getCompName(),"utf-8"));
			if(entity.getCompType()!=null)objSbf.append("&compType=").append(URLEncoder.encode(entity.getCompType(),"utf-8"));
			if(entity.getCompAdd()!=null)objSbf.append("&compAdd=").append(URLEncoder.encode(entity.getCompAdd(),"utf-8"));
			if(entity.getCompTl()!=null)objSbf.append("&compTl=").append(URLEncoder.encode(entity.getCompTl(),"utf-8"));
			if(entity.getCompanyNum()!=null)objSbf.append("&companyNum=").append(entity.getCompanyNum());
			if(entity.getLegalReprCode()!=null)objSbf.append("&legalReprCode=").append(URLEncoder.encode(entity.getLegalReprCode(),"utf-8"));
			if(entity.getLegalReprId()!=null)objSbf.append("&legalReprId=").append(URLEncoder.encode(entity.getLegalReprId(),"utf-8"));
			if(entity.getLegalReprName()!=null)objSbf.append("&legalReprName=").append(URLEncoder.encode(entity.getLegalReprName(),"utf-8"));
			if(entity.getLegalReprTl()!=null)objSbf.append("&legalReprTl=").append(URLEncoder.encode(entity.getLegalReprTl(),"utf-8"));
			if(entity.getSecuName()!=null)objSbf.append("&secuName=").append(URLEncoder.encode(entity.getSecuName(),"utf-8"));
			if(entity.getSecuPhone()!=null)objSbf.append("&secuPhone=").append(URLEncoder.encode(entity.getSecuPhone(),"utf-8"));
			if(entity.getEntePrinName()!=null)objSbf.append("&entePrinName=").append(URLEncoder.encode(entity.getEntePrinName(),"utf-8"));
			if(entity.getDangComp()!=null)objSbf.append("&dangComp=").append(entity.getDangComp());
			if(entity.getEntePrincipalTl()!=null)objSbf.append("&entePrincipalTl=").append(URLEncoder.encode(entity.getEntePrincipalTl(),"utf-8"));
			if(entity.getSafeHazaType()!=null)objSbf.append("&safeHazaType=").append(URLEncoder.encode(entity.getSafeHazaType(),"utf-8"));
			if(entity.getConcExte()!=null)objSbf.append("&concExte=").append(URLEncoder.encode(entity.getConcExte(),"utf-8"));
			if(entity.getRiskRank()!=null)objSbf.append("&riskRank=").append(URLEncoder.encode(entity.getRiskRank(),"utf-8"));
			if(entity.getRegiType()!=null)objSbf.append("&regiType=").append(URLEncoder.encode(entity.getRegiType(),"utf-8"));
			if(entity.getEstaOrgaCond()!=null)objSbf.append("&estaOrgaCond=").append(entity.getEstaOrgaCond());
			if(entity.getHoldCase()!=null)objSbf.append("&holdCase=").append(URLEncoder.encode(entity.getHoldCase(),"utf-8"));
			if(entity.getCompImpoType()!=null)objSbf.append("&compImpoType=").append(URLEncoder.encode(entity.getCompImpoType(),"utf-8"));
			if(entity.getEstaOrga()!=null)objSbf.append("&estaOrga=").append(entity.getEstaOrga());
			if(entity.getPartyMem()!=null)objSbf.append("&partyMem=").append(entity.getPartyMem());
			if(entity.getLaborUnion()!=null)objSbf.append("&laborUnion=").append(entity.getLaborUnion());
			if(entity.getLaborUnionNum()!=null)objSbf.append("&laborUnionNum=").append(entity.getLaborUnionNum());
			if(entity.getYouthLeagOrga()!=null)objSbf.append("&youthLeagOrga=").append(entity.getYouthLeagOrga());
			if(entity.getYouthLeagOrgaNum()!=null)objSbf.append("&youthLeagOrgaNum=").append(entity.getYouthLeagOrgaNum());
			if(entity.getWomenOrg()!=null)objSbf.append("&womenOrg=").append(entity.getWomenOrg());
			if(entity.getWomenNum()!=null)objSbf.append("&womenNum=").append(entity.getWomenNum());
			if(entity.getArea()!=null&&entity.getArea().getId()!=null)objSbf.append("&area.id=").append(entity.getArea().getId());
			if(entity.getRegisteredFund()!=null)objSbf.append("&registeredFund=").append(URLEncoder.encode(entity.getRegisteredFund(),"utf-8"));
			if(entity.getManageScope()!=null)objSbf.append("&manageScope=").append(URLEncoder.encode(entity.getManageScope(),"utf-8"));
			if(entity.getEnteType()!=null)objSbf.append("&enteType=").append(URLEncoder.encode(entity.getEnteType(),"utf-8"));
			if(entity.getServBrand()!=null)objSbf.append("&servBrand=").append(URLEncoder.encode(entity.getServBrand(),"utf-8"));
			if(entity.getIndustry()!=null)objSbf.append("&industry=").append(URLEncoder.encode(entity.getIndustry(),"utf-8"));
			if(entity.getCompArea()!=null)objSbf.append("&compArea=").append(entity.getCompArea());
			if(entity.getDangerCase()!=null)objSbf.append("&dangerCase=").append(URLEncoder.encode(entity.getDangerCase(),"utf-8"));
			if(entity.getReformCase()!=null)objSbf.append("&reformCase=").append(URLEncoder.encode(entity.getReformCase(),"utf-8"));
			if(entity.getImages()!=null){
				String urlFiles = entity.getImages();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getSurvCameNum()!=null)objSbf.append("&survCameNum=").append(entity.getSurvCameNum());
			if(entity.getXRayNum()!=null)objSbf.append("&xRayNum=").append(entity.getXRayNum());
			if(entity.getChecPack()!=null)objSbf.append("&checPack=").append(entity.getChecPack());
			if(entity.getRealName()!=null)objSbf.append("&realName=").append(entity.getRealName());
			if(entity.getXRayChec()!=null)objSbf.append("&xRayChec=").append(entity.getXRayChec());
			if(entity.getAreaMap()!=null)objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null)objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getIcon()!=null)objSbf.append("&icon=").append(URLEncoder.encode(entity.getIcon(),"utf-8"));
			if(entity.getMore1()!=null)objSbf.append("&more1=").append(URLEncoder.encode(entity.getMore1(),"utf-8"));
			if(entity.getMore2()!=null)objSbf.append("&more2=").append(URLEncoder.encode(entity.getMore2(),"utf-8"));
			if(entity.getMore3()!=null)objSbf.append("&more3=").append(URLEncoder.encode(entity.getMore3(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));

			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/orgNpse/saveSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
		
	}
	
	/**
  	 * 方法说明：上传留守人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-22
  	 * @其他：
  	 */
	public void uploadBehind(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteBehindSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmPopBehind entity = ccmPopBehindService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getHealth()!=null)objSbf.append("&health=").append(URLEncoder.encode(entity.getHealth(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getAnnualincome()!=null)objSbf.append("&annualincome=").append(URLEncoder.encode(entity.getAnnualincome(),"utf-8"));
			if(entity.getStaytype()!=null)objSbf.append("&staytype=").append(URLEncoder.encode(entity.getStaytype(),"utf-8"));
			if(entity.getCrucialcondition()!=null)objSbf.append("&crucialcondition=").append(URLEncoder.encode(entity.getCrucialcondition(),"utf-8"));
			if(entity.getCrucialname()!=null)objSbf.append("&crucialname=").append(URLEncoder.encode(entity.getCrucialname(),"utf-8"));
			if(entity.getCrucialhealth()!=null)objSbf.append("&crucialhealth=").append(URLEncoder.encode(entity.getCrucialhealth(),"utf-8"));
			if(entity.getCrucialrelation()!=null)objSbf.append("&crucialrelation=").append(URLEncoder.encode(entity.getCrucialrelation(),"utf-8"));
			if(entity.getCrucialtelephone()!=null)objSbf.append("&crucialtelephone=").append(URLEncoder.encode(entity.getCrucialtelephone(),"utf-8"));
			if(entity.getCrucialwork()!=null)objSbf.append("&crucialwork=").append(URLEncoder.encode(entity.getCrucialwork(),"utf-8"));
			if(entity.getCrucialmoney()!=null)objSbf.append("&crucialmoney=").append(URLEncoder.encode(entity.getCrucialmoney(),"utf-8"));
			if(entity.getDifficult()!=null)objSbf.append("&difficult=").append(URLEncoder.encode(entity.getDifficult(),"utf-8"));
			if(entity.getHelpcase()!=null)objSbf.append("&helpcase=").append(URLEncoder.encode(entity.getHelpcase(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveBehindSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	
	
	/**
  	 * 方法说明：上传安置帮教人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-23
  	 * @其他：
  	 */
	public void uploadRelease(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteReleaseSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseRelease entity = ccmHouseReleaseService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getRecidivism()!=null)objSbf.append("&recidivism=").append(URLEncoder.encode(entity.getRecidivism(),"utf-8"));
			if(entity.getOrigCha()!=null)objSbf.append("&origCha=").append(URLEncoder.encode(entity.getOrigCha(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getSentence()!=null)objSbf.append("&sentence=").append(URLEncoder.encode(entity.getSentence(),"utf-8"));
			if(entity.getServinGplace()!=null)objSbf.append("&servinGplace=").append(URLEncoder.encode(entity.getServinGplace(),"utf-8"));
			if (entity.getReleDate() != null && !"".equals(entity.getReleDate())) {
				String releDate = DateFormatUtils.format(entity.getReleDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&releDate=").append(URLEncoder.encode(releDate, "utf-8"));
			}
			if(entity.getRisk()!=null&&entity.getRisk()!="")objSbf.append("&risk=").append(URLEncoder.encode(entity.getRisk(),"utf-8"));
			if (entity.getJoinDate() != null && !"".equals(entity.getJoinDate())) {
				String joinDate = DateFormatUtils.format(entity.getJoinDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&joinDate=").append(URLEncoder.encode(joinDate, "utf-8"));
			}
			if(entity.getJoinCond()!=null&&entity.getJoinCond()!="")objSbf.append("&joinCond=").append(URLEncoder.encode(entity.getJoinCond(),"utf-8"));
			if (entity.getPlaceDate() != null && !"".equals(entity.getPlaceDate())) {
				String placeDate = DateFormatUtils.format(entity.getPlaceDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&placeDate=").append(URLEncoder.encode(placeDate, "utf-8"));
			}
			if(entity.getPlacement()!=null)objSbf.append("&placement=").append(URLEncoder.encode(entity.getPlacement(),"utf-8"));
			if(entity.getNotPlace()!=null)objSbf.append("&notPlace=").append(URLEncoder.encode(entity.getNotPlace(),"utf-8"));
			if(entity.getHelpCase()!=null)objSbf.append("&helpCase=").append(URLEncoder.encode(entity.getHelpCase(),"utf-8"));
			if(entity.getReoffend()!=null)objSbf.append("&reoffend=").append(entity.getReoffend());
			if(entity.getReofCharge()!=null)objSbf.append("&reofCharge=").append(URLEncoder.encode(entity.getReofCharge(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveReleaseSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	
	
	/**
  	 * 方法说明：上传社区矫正人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-23
  	 * @其他：
  	 */
	public void uploadRectification(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteRectificationSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseRectification entity = ccmHouseRectificationService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getRectNum()!=null)objSbf.append("&rectNum=").append(URLEncoder.encode(entity.getRectNum(),"utf-8"));
			if(entity.getRectPlace()!=null)objSbf.append("&rectPlace=").append(URLEncoder.encode(entity.getRectPlace(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getRectType()!=null)objSbf.append("&rectType=").append(URLEncoder.encode(entity.getRectType(),"utf-8"));
			if(entity.getCaseType()!=null)objSbf.append("&caseType=").append(URLEncoder.encode(entity.getCaseType(),"utf-8"));
			if(entity.getCharge()!=null)objSbf.append("&charge=").append(URLEncoder.encode(entity.getCharge(),"utf-8"));
			if(entity.getOrigCharge()!=null)objSbf.append("&origCharge=").append(URLEncoder.encode(entity.getOrigCharge(),"utf-8"));
			if (entity.getOrigBegin() != null && !"".equals(entity.getOrigBegin())) {
				String origBegin = DateFormatUtils.format(entity.getOrigBegin(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&origBegin=").append(URLEncoder.encode(origBegin, "utf-8"));
			}
			if (entity.getOrigEnd() != null && !"".equals(entity.getOrigEnd())) {
				String origEnd = DateFormatUtils.format(entity.getOrigEnd(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&origEnd=").append(URLEncoder.encode(origEnd, "utf-8"));
			}
			if (entity.getRectBegin() != null && !"".equals(entity.getRectBegin())) {
				String rectBegin = DateFormatUtils.format(entity.getRectBegin(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&rectBegin=").append(URLEncoder.encode(rectBegin, "utf-8"));
			}
			if (entity.getRectEnd() != null && !"".equals(entity.getRectEnd())) {
				String rectEnd = DateFormatUtils.format(entity.getRectEnd(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&rectEnd=").append(URLEncoder.encode(rectEnd, "utf-8"));
			}
			if(entity.getReceiveMode()!=null)objSbf.append("&receiveMode=").append(URLEncoder.encode(entity.getReceiveMode(),"utf-8"));
			if(entity.getFourHis()!=null)objSbf.append("&fourHis=").append(URLEncoder.encode(entity.getFourHis(),"utf-8"));
			if(entity.getRecidivist()!=null)objSbf.append("&recidivist=").append(entity.getRecidivist());
			if(entity.getThrHold()!=null)objSbf.append("&thrHold=").append(URLEncoder.encode(entity.getThrHold(),"utf-8"));
			if(entity.getCorrecthas()!=null)objSbf.append("&correcthas=").append(entity.getCorrecthas());
			if(entity.getCorrected()!=null)objSbf.append("&corrected=").append(URLEncoder.encode(entity.getCorrected(),"utf-8"));
			if(entity.getCorrectlift()!=null)objSbf.append("&correctlift=").append(URLEncoder.encode(entity.getCorrectlift(),"utf-8"));
			if(entity.getDetached()!=null)objSbf.append("&detached=").append(entity.getDetached());
			if(entity.getDetaReason()!=null)objSbf.append("&detaReason=").append(URLEncoder.encode(entity.getDetaReason(),"utf-8"));
			if(entity.getDetaSupe()!=null)objSbf.append("&detaSupe=").append(URLEncoder.encode(entity.getDetaSupe(),"utf-8"));
			if(entity.getDetaCorr()!=null)objSbf.append("&detaCorr=").append(URLEncoder.encode(entity.getDetaCorr(),"utf-8"));
			if(entity.getLackContr()!=null)objSbf.append("&lackContr=").append(entity.getLackContr());
			if(entity.getLackContrRe()!=null)objSbf.append("&lackContrRe=").append(URLEncoder.encode(entity.getLackContrRe(),"utf-8"));
			if(entity.getLackContrCase()!=null)objSbf.append("&lackContrCase=").append(URLEncoder.encode(entity.getLackContrCase(),"utf-8"));
			if(entity.getLackContrCaseCorr()!=null)objSbf.append("&lackContrCaseCorr=").append(URLEncoder.encode(entity.getLackContrCaseCorr(),"utf-8"));
			if(entity.getRewandpun()!=null)objSbf.append("&rewandpun=").append(URLEncoder.encode(entity.getRewandpun(),"utf-8"));
			if(entity.getPenaChan()!=null)objSbf.append("&penaChan=").append(URLEncoder.encode(entity.getPenaChan(),"utf-8"));
			if(entity.getReoffend()!=null)objSbf.append("&reoffend=").append(entity.getReoffend());
			if(entity.getReofCharge()!=null)objSbf.append("&reofCharge=").append(URLEncoder.encode(entity.getReofCharge(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveRectificationSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	

	/**
  	 * 方法说明：上传肇事肇祸等严重精神障碍患者数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-23
  	 * @其他：
  	 */
	public void uploadPsychogeny(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deletePsychogenySyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHousePsychogeny entity = ccmHousePsychogenyService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getEconomic()!=null)objSbf.append("&economic=").append(URLEncoder.encode(entity.getEconomic(),"utf-8"));
			if(entity.getAllowance()!=null)objSbf.append("&allowance=").append(entity.getAllowance());
			if(entity.getGuarIden()!=null)objSbf.append("&guarIden=").append(URLEncoder.encode(entity.getGuarIden(),"utf-8"));
			if(entity.getGuarName()!=null)objSbf.append("&guarName=").append(URLEncoder.encode(entity.getGuarName(),"utf-8"));
			if(entity.getGuarTel()!=null)objSbf.append("&guarTel=").append(URLEncoder.encode(entity.getGuarTel(),"utf-8"));
			if (entity.getDateonset() != null && !"".equals(entity.getDateonset())) {
				String dateonset = DateFormatUtils.format(entity.getDateonset(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&dateonset=").append(URLEncoder.encode(dateonset, "utf-8"));
			}
			if(entity.getDiagType()!=null)objSbf.append("&diagType=").append(entity.getDiagType());
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getAcciHist()!=null)objSbf.append("&acciHist=").append(entity.getAcciHist());
			if(entity.getAcciCount()!=null)objSbf.append("&acciCount=").append(entity.getAcciCount());
			if (entity.getAcciLast() != null && !"".equals(entity.getAcciLast())) {
				String acciLast = DateFormatUtils.format(entity.getAcciLast(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&acciLast=").append(URLEncoder.encode(acciLast, "utf-8"));
			}
			if(entity.getDangAsse()!=null)objSbf.append("&dangAsse=").append(entity.getDangAsse());
			if(entity.getTreaCond()!=null)objSbf.append("&treaCond=").append(entity.getTreaCond());
			if(entity.getHospital()!=null)objSbf.append("&hospital=").append(URLEncoder.encode(entity.getHospital(),"utf-8"));
			if(entity.getHospReason()!=null)objSbf.append("&hospReason=").append(URLEncoder.encode(entity.getHospReason(),"utf-8"));
			if(entity.getTraiInst()!=null)objSbf.append("&traiInst=").append(URLEncoder.encode(entity.getTraiInst(),"utf-8"));
			if(entity.getManagement()!=null)objSbf.append("&management=").append(URLEncoder.encode(entity.getManagement(),"utf-8"));
			if(entity.getHelpcase()!=null)objSbf.append("&helpcase=").append(URLEncoder.encode(entity.getHelpcase(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/savePsychogenySyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	

	/**
  	 * 方法说明：上传吸毒人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-23
  	 * @其他：
  	 */
	public void uploadDrugs(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteDrugsSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseDrugs entity = ccmHouseDrugsService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if (entity.getFirstFound() != null && !"".equals(entity.getFirstFound())) {
				String firstFound = DateFormatUtils.format(entity.getFirstFound(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&firstFound=").append(URLEncoder.encode(firstFound, "utf-8"));
			}
			if(entity.getContSit()!=null)objSbf.append("&contSit=").append(URLEncoder.encode(entity.getContSit(),"utf-8"));
			if(entity.getContName()!=null)objSbf.append("&contName=").append(URLEncoder.encode(entity.getContName(),"utf-8"));
			if(entity.getContTl()!=null)objSbf.append("&contTl=").append(URLEncoder.encode(entity.getContTl(),"utf-8"));
			if(entity.getHelpCase()!=null)objSbf.append("&helpCase=").append(URLEncoder.encode(entity.getHelpCase(),"utf-8"));
			if(entity.getHelpName()!=null)objSbf.append("&helpName=").append(URLEncoder.encode(entity.getHelpName(),"utf-8"));
			if(entity.getHelpTl()!=null)objSbf.append("&helpTl=").append(URLEncoder.encode(entity.getHelpTl(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getCrimPast()!=null)objSbf.append("&crimPast=").append(entity.getCrimPast());
			if(entity.getCrimStat()!=null)objSbf.append("&crimStat=").append(URLEncoder.encode(entity.getCrimStat(),"utf-8"));
			if(entity.getDrugCaus()!=null)objSbf.append("&drugCaus=").append(URLEncoder.encode(entity.getDrugCaus(),"utf-8"));
			if(entity.getConsDrug()!=null)objSbf.append("&consDrug=").append(URLEncoder.encode(entity.getConsDrug(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveDrugsSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

	

	/**
  	 * 方法说明：上传艾滋病患者数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-28
  	 * @其他：
  	 */
	public void uploadAids(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteAidsSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseAids entity = ccmHouseAidsService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getInfeRoute()!=null)objSbf.append("&infeRoute=").append(URLEncoder.encode(entity.getInfeRoute(),"utf-8"));
			if(entity.getCrimPast()!=null)objSbf.append("&crimPast=").append(entity.getCrimPast());
			if(entity.getCrimStat()!=null)objSbf.append("&crimStat=").append(URLEncoder.encode(entity.getCrimStat(),"utf-8"));
			if(entity.getCateCase()!=null)objSbf.append("&cateCase=").append(URLEncoder.encode(entity.getCateCase(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getHelpCase()!=null)objSbf.append("&helpCase=").append(URLEncoder.encode(entity.getHelpCase(),"utf-8"));
			if(entity.getHelpName()!=null)objSbf.append("&helpName=").append(URLEncoder.encode(entity.getHelpName(),"utf-8"));
			if(entity.getHelpTel()!=null)objSbf.append("&helpTel=").append(URLEncoder.encode(entity.getHelpTel(),"utf-8"));
			if(entity.getTreaCase()!=null)objSbf.append("&treaCase=").append(URLEncoder.encode(entity.getTreaCase(),"utf-8"));
			if(entity.getInstitutions()!=null)objSbf.append("&institutions=").append(URLEncoder.encode(entity.getInstitutions(),"utf-8"));
			if(entity.getAttention()!=null)objSbf.append("&attention=").append(URLEncoder.encode(entity.getAttention(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveAidsSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	


	

	/**
  	 * 方法说明：上传重点上访人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-28
  	 * @其他：
  	 */
	public void uploadPetition(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deletePetitionSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHousePetition entity = ccmHousePetitionService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getWorkUnit()!=null)objSbf.append("&workUnit=").append(URLEncoder.encode(entity.getWorkUnit(),"utf-8"));
			if(entity.getWorkPhone()!=null)objSbf.append("&workPhone=").append(URLEncoder.encode(entity.getWorkPhone(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getPetitionType()!=null)objSbf.append("&petitionType=").append(URLEncoder.encode(entity.getPetitionType(),"utf-8"));
			if(entity.getPeopleNumber()!=null)objSbf.append("&peopleNumber=").append(URLEncoder.encode(entity.getPeopleNumber(),"utf-8"));
			if(entity.getPetitionFor()!=null)objSbf.append("&petitionFor=").append(URLEncoder.encode(entity.getPetitionFor(),"utf-8"));
			if (entity.getFirstTime() != null && !"".equals(entity.getFirstTime())) {
				String firstTime = DateFormatUtils.format(entity.getFirstTime(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&firstTime=").append(URLEncoder.encode(firstTime, "utf-8"));
			}
			if(entity.getPetitionNum()!=null)objSbf.append("&petitionNum=").append(entity.getPetitionNum());
			if(entity.getPetitionPlace()!=null)objSbf.append("&petitionPlace=").append(URLEncoder.encode(entity.getPetitionPlace(),"utf-8"));
			if(entity.getReflectProblem()!=null)objSbf.append("&reflectProblem=").append(URLEncoder.encode(entity.getReflectProblem(),"utf-8"));
			if(entity.getTroubleBehavior()!=null)objSbf.append("&troubleBehavior=").append(URLEncoder.encode(entity.getTroubleBehavior(),"utf-8"));
			if(entity.getPetitionResult()!=null)objSbf.append("&petitionResult=").append(URLEncoder.encode(entity.getPetitionResult(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/savePetitionSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

	

	/**
  	 * 方法说明：上传涉教人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-28
  	 * @其他：
  	 */
	public void uploadHeresy(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteHeresySyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseHeresy entity = ccmHouseHeresyService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getHeresyName()!=null)objSbf.append("&heresyName=").append(URLEncoder.encode(entity.getHeresyName(),"utf-8"));
			if (entity.getFirstTime() != null && !"".equals(entity.getFirstTime())) {
				String firstTime = DateFormatUtils.format(entity.getFirstTime(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&firstTime=").append(URLEncoder.encode(firstTime, "utf-8"));
			}
			if (entity.getEndTime() != null && !"".equals(entity.getEndTime())) {
				String endTime = DateFormatUtils.format(entity.getEndTime(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&endTime=").append(URLEncoder.encode(endTime, "utf-8"));
			}
			if(entity.getLevel()!=null)objSbf.append("&level=").append(URLEncoder.encode(entity.getLevel(),"utf-8"));
			if(entity.getHowToKnow()!=null)objSbf.append("&howToKnow=").append(URLEncoder.encode(entity.getHowToKnow(),"utf-8"));
			if(entity.getIntroducer()!=null)objSbf.append("&introducer=").append(URLEncoder.encode(entity.getIntroducer(),"utf-8"));
			if(entity.getDutyOfficer()!=null)objSbf.append("&dutyOfficer=").append(URLEncoder.encode(entity.getDutyOfficer(),"utf-8"));
			if(entity.getOfficerTel()!=null)objSbf.append("&officerTel=").append(URLEncoder.encode(entity.getOfficerTel(),"utf-8"));
			if(entity.getIsStudy()!=null)objSbf.append("&isStudy=").append(entity.getIsStudy());
			if(entity.getIsChange()!=null)objSbf.append("&isChange=").append(entity.getIsChange());
			if(entity.getLiveStatus()!=null)objSbf.append("&liveStatus=").append(URLEncoder.encode(entity.getLiveStatus(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveHeresySyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	

	/**
  	 * 方法说明：上传危险品从业人员数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-23
  	 * @其他：
  	 */
	public void uploadDangerous(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteDangerousSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseDangerous entity = ccmHouseDangerousService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getWorkUnit()!=null)objSbf.append("&workUnit=").append(URLEncoder.encode(entity.getWorkUnit(),"utf-8"));
			if(entity.getWorkPhone()!=null)objSbf.append("&workPhone=").append(URLEncoder.encode(entity.getWorkPhone(),"utf-8"));
			if(entity.getWorkPlace()!=null)objSbf.append("&workPlace=").append(URLEncoder.encode(entity.getWorkPlace(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getGoodsType()!=null)objSbf.append("&goodsType=").append(URLEncoder.encode(entity.getGoodsType(),"utf-8"));
			if(entity.getWorkType()!=null)objSbf.append("&workType=").append(URLEncoder.encode(entity.getWorkType(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveDangerousSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	

	/**
  	 * 方法说明：上传重点青少年数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-23
  	 * @其他：
  	 */
	public void uploadKym(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/people/deleteKymSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseKym entity = ccmHouseKymService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getPeopleId()!=null)objSbf.append("&peopleId=").append(URLEncoder.encode(entity.getPeopleId(),"utf-8"));
			if(entity.getAtteType()!=null)objSbf.append("&atteType=").append(URLEncoder.encode(entity.getAtteType(),"utf-8"));
			if(entity.getManType()!=null)objSbf.append("&manType=").append(URLEncoder.encode(entity.getManType(),"utf-8"));
			if(entity.getFamiStat()!=null)objSbf.append("&famiStat=").append(URLEncoder.encode(entity.getFamiStat(),"utf-8"));
			if(entity.getGuarPerId()!=null)objSbf.append("&guarPerId=").append(URLEncoder.encode(entity.getGuarPerId(),"utf-8"));
			if(entity.getGuarName()!=null)objSbf.append("&guarName=").append(URLEncoder.encode(entity.getGuarName(),"utf-8"));
			if(entity.getGuarRela()!=null)objSbf.append("&guarRela=").append(URLEncoder.encode(entity.getGuarRela(),"utf-8"));
			if(entity.getGuarTl()!=null)objSbf.append("&guarTl=").append(URLEncoder.encode(entity.getGuarTl(),"utf-8"));
			if(entity.getGuarAdd()!=null)objSbf.append("&guarAdd=").append(URLEncoder.encode(entity.getGuarAdd(),"utf-8"));
			if(entity.getDelinquency()!=null)objSbf.append("&delinquency=").append(entity.getDelinquency());
			if(entity.getDeliCase()!=null)objSbf.append("&deliCase=").append(URLEncoder.encode(entity.getDeliCase(),"utf-8"));
			if(entity.getAssistName()!=null)objSbf.append("&assistName=").append(URLEncoder.encode(entity.getAssistName(),"utf-8"));
			if(entity.getAssistTl()!=null)objSbf.append("&assistTl=").append(URLEncoder.encode(entity.getAssistTl(),"utf-8"));
			if(entity.getAssistMethod()!=null)objSbf.append("&assistMethod=").append(URLEncoder.encode(entity.getAssistMethod(),"utf-8"));
			if(entity.getAssistCase()!=null)objSbf.append("&assistCase=").append(URLEncoder.encode(entity.getAssistCase(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/people/saveKymSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	

	/**
  	 * 方法说明：上传学校数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-23
  	 * @其他：
  	 */
	public void uploadSchool(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/school/deleteSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmHouseSchoolrim entity = ccmHouseSchoolrimService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null) objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			
			if(entity.getSchoolName()!=null) objSbf.append("&schoolName=").append(URLEncoder.encode(entity.getSchoolName(),"utf-8"));
			if(entity.getSchoolAdd()!=null) objSbf.append("&schoolAdd=").append(URLEncoder.encode(entity.getSchoolAdd(),"utf-8"));
			if(entity.getSchoolType()!=null) objSbf.append("&schoolType=").append(URLEncoder.encode(entity.getSchoolType(),"utf-8"));
			if(entity.getSchoolEducDepa()!=null) objSbf.append("&schoolEducDepa=").append(URLEncoder.encode(entity.getSchoolEducDepa(),"utf-8"));
			if(entity.getSchoolNum()!=null) objSbf.append("&schoolNum=").append(entity.getSchoolNum());
			if(entity.getHeadName()!=null) objSbf.append("&headName=").append(URLEncoder.encode(entity.getHeadName(),"utf-8"));
			if(entity.getHeadTl()!=null) objSbf.append("&headTl=").append(URLEncoder.encode(entity.getHeadTl(),"utf-8"));
			if(entity.getSecuBranName()!=null) objSbf.append("&secuBranName=").append(URLEncoder.encode(entity.getSecuBranName(),"utf-8"));
			if(entity.getSecuBranTl()!=null) objSbf.append("&secuBranTl=").append(URLEncoder.encode(entity.getSecuBranTl(),"utf-8"));
			if(entity.getSecuGuardName()!=null) objSbf.append("&secuGuardName=").append(URLEncoder.encode(entity.getSecuGuardName(),"utf-8"));
			if(entity.getSecuGuardTl()!=null) objSbf.append("&secuGuardTl=").append(URLEncoder.encode(entity.getSecuGuardTl(),"utf-8"));
			if(entity.getSecuName()!=null) objSbf.append("&secuName=").append(URLEncoder.encode(entity.getSecuName(),"utf-8"));
			if(entity.getSecuTl()!=null) objSbf.append("&secuTl=").append(URLEncoder.encode(entity.getSecuTl(),"utf-8"));
			if(entity.getSecuGuardNum()!=null) objSbf.append("&secuGuardNum=").append(entity.getSecuGuardNum());
			if(entity.getArea()!=null && entity.getArea().getId()!=null) objSbf.append("&area.id=").append(URLEncoder.encode(entity.getArea().getId(),"utf-8"));
			if(entity.getAreaMap()!=null) objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null) objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getImage()!=null) objSbf.append("&image=").append(URLEncoder.encode(entity.getImage(),"utf-8"));
			if(entity.getImages()!=null){
				String urlFiles = entity.getImages();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getRemarks()!=null) objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null) objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/school/saveSyn?userId=" + userId + objSbf.toString();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

	/**
  	 * 方法说明：上传城市部件数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-23
  	 * @其他：
  	 */
	public void uploadCityComponents(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/cityComponents/deleteSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmCityComponents entity = ccmCityComponentsService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null) objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			
			if(entity.getType()!=null) objSbf.append("&type=").append(URLEncoder.encode(entity.getType(),"utf-8"));
			if(entity.getName()!=null) objSbf.append("&name=").append(URLEncoder.encode(entity.getName(),"utf-8"));
			if(entity.getCode()!=null) objSbf.append("&code=").append(URLEncoder.encode(entity.getCode(),"utf-8"));
			if(entity.getCompetentDepartmentCode()!=null) objSbf.append("&competentDepartmentCode=").append(URLEncoder.encode(entity.getCompetentDepartmentCode(),"utf-8"));
			if(entity.getCompetentDepartmentName()!=null) objSbf.append("&competentDepartmentName=").append(URLEncoder.encode(entity.getCompetentDepartmentName(),"utf-8"));
			if(entity.getOwnershipDepartmentCode()!=null) objSbf.append("&ownershipDepartmentCode=").append(URLEncoder.encode(entity.getOwnershipDepartmentCode(),"utf-8"));
			if(entity.getOwnershipDepartmentName()!=null) objSbf.append("&ownershipDepartmentName=").append(URLEncoder.encode(entity.getOwnershipDepartmentName(),"utf-8"));
			if(entity.getMaintainDepartmentCode()!=null) objSbf.append("&maintainDepartmentCode=").append(URLEncoder.encode(entity.getMaintainDepartmentCode(),"utf-8"));
			if(entity.getMaintainDepartmentName()!=null) objSbf.append("&maintainDepartmentName=").append(URLEncoder.encode(entity.getMaintainDepartmentName(),"utf-8"));
			if(entity.getMaintainDepartmentTel()!=null) objSbf.append("&maintainDepartmentTel=").append(URLEncoder.encode(entity.getMaintainDepartmentTel(),"utf-8"));
			if(entity.getAddress()!=null) objSbf.append("&address=").append(URLEncoder.encode(entity.getAddress(),"utf-8"));
			if(entity.getSpatialForm()!=null) objSbf.append("&spatialForm=").append(URLEncoder.encode(entity.getSpatialForm(),"utf-8"));
			if(entity.getStatus()!=null) objSbf.append("&status=").append(URLEncoder.encode(entity.getStatus(),"utf-8"));
			if(entity.getImagePath()!=null){
				String urlFiles = entity.getImagePath();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&imagePath=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&imagePath=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getArea()!=null && entity.getArea().getId()!=null) objSbf.append("&area.id=").append(URLEncoder.encode(entity.getArea().getId(),"utf-8"));
			if(entity.getAreaMap()!=null) objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null) objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getRemarks()!=null) objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null) objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/cityComponents/saveSyn?userId=" + userId + objSbf.toString();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

	/**
  	 * 方法说明：上传社会组织数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-29
  	 * @其他：
  	 */
	public void uploadSocialorg(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/socialOrg/deleteSocialorgSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmOrgSocialorg entity = ccmOrgSocialorgService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null) objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getCreditCode()!=null)objSbf.append("&creditCode=").append(URLEncoder.encode(entity.getCreditCode(),"utf-8"));
			if(entity.getRegiNum()!=null)objSbf.append("&regiNum=").append(URLEncoder.encode(entity.getRegiNum(),"utf-8"));
			if(entity.getOrgName()!=null)objSbf.append("&orgName=").append(URLEncoder.encode(entity.getOrgName(),"utf-8"));
			if(entity.getRegiPlaceNum()!=null)objSbf.append("&regiPlaceNum=").append(URLEncoder.encode(entity.getRegiPlaceNum(),"utf-8"));
			if(entity.getLegalReprName()!=null)objSbf.append("&legalReprName=").append(URLEncoder.encode(entity.getLegalReprName(),"utf-8"));
			if(entity.getPlace()!=null)objSbf.append("&place=").append(URLEncoder.encode(entity.getPlace(),"utf-8"));
			if (entity.getApprDate() != null && !"".equals(entity.getApprDate())) {
				String apprDate = DateFormatUtils.format(entity.getApprDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&apprDate=").append(URLEncoder.encode(apprDate, "utf-8"));
			}
			if(entity.getOrgType()!=null)objSbf.append("&orgType=").append(URLEncoder.encode(entity.getOrgType(),"utf-8"));
			if(entity.getType()!=null)objSbf.append("&type=").append(entity.getType());
			if(entity.getPrinCode()!=null)objSbf.append("&prinCode=").append(URLEncoder.encode(entity.getPrinCode(),"utf-8"));
			if(entity.getPrinId()!=null)objSbf.append("&prinId=").append(URLEncoder.encode(entity.getPrinId(),"utf-8"));
			if(entity.getPrinName()!=null)objSbf.append("&prinName=").append(URLEncoder.encode(entity.getPrinName(),"utf-8"));
			if(entity.getPrinTel()!=null)objSbf.append("&prinTel=").append(URLEncoder.encode(entity.getPrinTel(),"utf-8"));
			if(entity.getWorkAdd()!=null)objSbf.append("&workAdd=").append(URLEncoder.encode(entity.getWorkAdd(),"utf-8"));
			if(entity.getSecuName()!=null)objSbf.append("&secuName=").append(URLEncoder.encode(entity.getSecuName(),"utf-8"));
			if(entity.getSecuTel()!=null)objSbf.append("&secuTel=").append(URLEncoder.encode(entity.getSecuTel(),"utf-8"));
			if(entity.getConcernExtent()!=null)objSbf.append("&concernExtent=").append(URLEncoder.encode(entity.getConcernExtent(),"utf-8"));
			if(entity.getEstaOrgCond()!=null)objSbf.append("&estaOrgCond=").append(URLEncoder.encode(entity.getEstaOrgCond(),"utf-8"));
			if(entity.getEstaOrg()!=null)objSbf.append("&estaOrg=").append(URLEncoder.encode(entity.getEstaOrg(),"utf-8"));
			if(entity.getPartyMem()!=null)objSbf.append("&partyMem=").append(entity.getPartyMem());
			if(entity.getLaborUnion()!=null)objSbf.append("&laborUnion=").append(URLEncoder.encode(entity.getLaborUnion(),"utf-8"));
			if(entity.getLaborUnionNum()!=null)objSbf.append("&laborUnionNum=").append(entity.getLaborUnionNum());
			if(entity.getYouthLeagOrg()!=null)objSbf.append("&youthLeagOrg=").append(URLEncoder.encode(entity.getYouthLeagOrg(),"utf-8"));
			if(entity.getYouthLeagOrgNum()!=null)objSbf.append("&youthLeagOrgNum=").append(entity.getYouthLeagOrgNum());
			if(entity.getWomenOrg()!=null)objSbf.append("&womenOrg=").append(URLEncoder.encode(entity.getWomenOrg(),"utf-8"));
			if(entity.getWomenNum()!=null)objSbf.append("&womenNum=").append(entity.getWomenNum());
			if(entity.getCapiSour()!=null)objSbf.append("&capiSour=").append(URLEncoder.encode(entity.getCapiSour(),"utf-8"));
			if(entity.getOverBack()!=null)objSbf.append("&overBack=").append(URLEncoder.encode(entity.getOverBack(),"utf-8"));
			if(entity.getAreaMap()!=null)objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null)objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getIcon()!=null)objSbf.append("&icon=").append(URLEncoder.encode(entity.getIcon(),"utf-8"));
			if(entity.getImages()!=null){
				String urlFiles = entity.getImages();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&images=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getRemarks()!=null) objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null) objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/socialOrg/saveSocialorgSyn?userId=" + userId + objSbf.toString();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

	/**
  	 * 方法说明：上传护路护线数据
  	 *
  	* @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-29
  	 * @其他：
  	 */
	public void uploadProtect(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/line/deleteLineProtectSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmLineProtect entity = ccmLineProtectService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null) objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getName()!=null)objSbf.append("&name=").append(URLEncoder.encode(entity.getName(),"utf-8"));
			if(entity.getLineType()!=null)objSbf.append("&lineType=").append(URLEncoder.encode(entity.getLineType(),"utf-8"));
			if(entity.getCompName()!=null)objSbf.append("&compName=").append(URLEncoder.encode(entity.getCompName(),"utf-8"));
			if(entity.getCompAdd()!=null)objSbf.append("&compAdd=").append(URLEncoder.encode(entity.getCompAdd(),"utf-8"));
			if(entity.getCompTel()!=null)objSbf.append("&compTel=").append(URLEncoder.encode(entity.getCompTel(),"utf-8"));
			if(entity.getCompPrinName()!=null)objSbf.append("&compPrinName=").append(URLEncoder.encode(entity.getCompPrinName(),"utf-8"));
			if(entity.getCompPrinTel()!=null)objSbf.append("&compPrinTel=").append(URLEncoder.encode(entity.getCompPrinTel(),"utf-8"));
			if(entity.getGoveName()!=null)objSbf.append("&goveName=").append(URLEncoder.encode(entity.getGoveName(),"utf-8"));
			if(entity.getGoveAdd()!=null)objSbf.append("&goveAdd=").append(URLEncoder.encode(entity.getGoveAdd(),"utf-8"));
			if(entity.getGoveTel()!=null)objSbf.append("&goveTel=").append(URLEncoder.encode(entity.getGoveTel(),"utf-8"));
			if(entity.getSecuName()!=null)objSbf.append("&secuName=").append(URLEncoder.encode(entity.getSecuName(),"utf-8"));
			if(entity.getSecuTel()!=null)objSbf.append("&secuTel=").append(URLEncoder.encode(entity.getSecuTel(),"utf-8"));
			if(entity.getDangCase()!=null)objSbf.append("&dangCase=").append(URLEncoder.encode(entity.getDangCase(),"utf-8"));
			if(entity.getDangGrade()!=null)objSbf.append("&dangGrade=").append(URLEncoder.encode(entity.getDangGrade(),"utf-8"));
			if(entity.getLine()!=null)objSbf.append("&line=").append(URLEncoder.encode(entity.getLine(),"utf-8"));
			if(entity.getRemarks()!=null) objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null) objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/line/saveLineProtectSyn?userId=" + userId + objSbf.toString();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

	/**
  	 * 方法说明：上传矛盾纠纷数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-29
  	 * @其他：
  	 */
	public void uploadAmbi(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/event/deleteAmbiSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmEventAmbi entity = ccmEventAmbiService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();

			if(entity.getId()!=null) objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getName()!=null)objSbf.append("&name=").append(URLEncoder.encode(entity.getName(),"utf-8"));
			if (entity.getSendDate() != null && !"".equals(entity.getSendDate())) {
				String sendDate = DateFormatUtils.format(entity.getSendDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&sendDate=").append(URLEncoder.encode(sendDate, "utf-8"));
			}
			if(entity.getSendAdd()!=null)objSbf.append("&sendAdd=").append(URLEncoder.encode(entity.getSendAdd(),"utf-8"));
			if(entity.getEventScale()!=null)objSbf.append("&eventScale=").append(URLEncoder.encode(entity.getEventScale(),"utf-8"));
			if(entity.getEventType()!=null)objSbf.append("&eventType=").append(URLEncoder.encode(entity.getEventType(),"utf-8"));
			if(entity.getInvoNum()!=null)objSbf.append("&invoNum=").append(URLEncoder.encode(entity.getInvoNum(),"utf-8"));
			if(entity.getEventSket()!=null)objSbf.append("&eventSket=").append(URLEncoder.encode(entity.getEventSket(),"utf-8"));
			if(entity.getInvolveCom()!=null)objSbf.append("&involveCom=").append(URLEncoder.encode(entity.getInvolveCom(),"utf-8"));
			if(entity.getPartCode()!=null)objSbf.append("&partCode=").append(URLEncoder.encode(entity.getPartCode(),"utf-8"));
			if(entity.getPartNum()!=null)objSbf.append("&partNum=").append(URLEncoder.encode(entity.getPartNum(),"utf-8"));
			if(entity.getPartName()!=null)objSbf.append("&partName=").append(URLEncoder.encode(entity.getPartName(),"utf-8"));
			if(entity.getPartSex()!=null)objSbf.append("&partSex=").append(URLEncoder.encode(entity.getPartSex(),"utf-8"));
			if(entity.getPartNat()!=null)objSbf.append("&partNat=").append(URLEncoder.encode(entity.getPartNat(),"utf-8"));
			if(entity.getPartEduBg()!=null)objSbf.append("&partEduBg=").append(URLEncoder.encode(entity.getPartEduBg(),"utf-8"));
			if(entity.getPartType()!=null)objSbf.append("&partType=").append(URLEncoder.encode(entity.getPartType(),"utf-8"));
			if(entity.getPartAdd()!=null)objSbf.append("&partAdd=").append(URLEncoder.encode(entity.getPartAdd(),"utf-8"));
			if (entity.getSolve() != null && !"".equals(entity.getSolve())) {
				String solve = DateFormatUtils.format(entity.getSolve(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&solve=").append(URLEncoder.encode(solve, "utf-8"));
			}
			if(entity.getSolveType()!=null)objSbf.append("&solveType=").append(URLEncoder.encode(entity.getSolveType(),"utf-8"));
			if(entity.getSolveComp()!=null)objSbf.append("&solveComp=").append(URLEncoder.encode(entity.getSolveComp(),"utf-8"));
			if(entity.getSolveName()!=null)objSbf.append("&solveName=").append(URLEncoder.encode(entity.getSolveName(),"utf-8"));
			if(entity.getSolveTl()!=null)objSbf.append("&solveTl=").append(URLEncoder.encode(entity.getSolveTl(),"utf-8"));
			if(entity.getSolveSucc()!=null)objSbf.append("&solveSucc=").append(URLEncoder.encode(entity.getSolveSucc(),"utf-8"));
			if(entity.getSolveCase()!=null)objSbf.append("&solveCase=").append(URLEncoder.encode(entity.getSolveCase(),"utf-8"));
			if (entity.getEvaluateDate() != null && !"".equals(entity.getEvaluateDate())) {
				String evaluateDate = DateFormatUtils.format(entity.getEvaluateDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&evaluateDate=").append(URLEncoder.encode(evaluateDate, "utf-8"));
			}
			if(entity.getEvaluateAdv()!=null)objSbf.append("&evaluateAdv=").append(URLEncoder.encode(entity.getEvaluateAdv(),"utf-8"));
			if(entity.getAreaMap()!=null)objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null)objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getIcon()!=null)objSbf.append("&icon=").append(URLEncoder.encode(entity.getIcon(),"utf-8"));
			if(entity.getStatus()!=null)objSbf.append("&status=").append(URLEncoder.encode(entity.getStatus(),"utf-8"));
			if(entity.getFile()!=null)objSbf.append("&file=").append(URLEncoder.encode(entity.getFile(),"utf-8"));
			if(entity.getArea()!=null&&entity.getArea().getId()!=null)objSbf.append("&area.id=").append(entity.getArea().getId());
			if(entity.getRemarks()!=null) objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null) objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/event/saveAmbiSyn?userId=" + userId + objSbf.toString();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

  	/**
  	 * 方法说明：上传案事件数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-29
  	 * @其他：
  	 */
	public void uploadEventIncident(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/event/deleteSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmEventIncident entity = ccmEventIncidentService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();
			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));

			if(entity.getCaseName()!=null) objSbf.append("&caseName=").append(URLEncoder.encode(entity.getCaseName(),"utf-8"));
			if (entity.getHappenDate() != null && !"".equals(entity.getHappenDate())) {
				String happenDate = DateFormatUtils.format(entity.getHappenDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&happenDate=").append(URLEncoder.encode(happenDate, "utf-8"));
			}
			if(entity.getArea()!=null&&entity.getArea().getId()!=null)objSbf.append("&area.id=").append(entity.getArea().getId());
			if(entity.getHappenPlace()!=null) objSbf.append("&happenPlace=").append(URLEncoder.encode(entity.getHappenPlace(),"utf-8"));
			if(entity.getAreaMap()!=null) objSbf.append("&areaMap=").append(URLEncoder.encode(entity.getAreaMap(),"utf-8"));
			if(entity.getAreaPoint()!=null) objSbf.append("&areaPoint=").append(URLEncoder.encode(entity.getAreaPoint(),"utf-8"));
			if(entity.getImage()!=null) objSbf.append("&image=").append(URLEncoder.encode(entity.getImage(),"utf-8"));
			if(entity.getEventKind()!=null) objSbf.append("&eventKind=").append(URLEncoder.encode(entity.getEventKind(),"utf-8"));
			if(entity.getOtherId()!=null) objSbf.append("&otherId=").append(URLEncoder.encode(entity.getOtherId(),"utf-8"));
			if(entity.getEventScale()!=null) objSbf.append("&eventScale=").append(URLEncoder.encode(entity.getEventScale(),"utf-8"));
			if(entity.getEventType()!=null) objSbf.append("&eventType=").append(URLEncoder.encode(entity.getEventType(),"utf-8"));
			if(entity.getNumber()!=null) objSbf.append("&number=").append(URLEncoder.encode(entity.getNumber(),"utf-8"));
			if(entity.getProperty()!=null) objSbf.append("&property=").append(URLEncoder.encode(entity.getProperty(),"utf-8"));
			if(entity.getCaseCondition()!=null) objSbf.append("&caseCondition=").append(URLEncoder.encode(entity.getCaseCondition(),"utf-8"));
			if(entity.getCulPapercode()!=null) objSbf.append("&culPapercode=").append(URLEncoder.encode(entity.getCulPapercode(),"utf-8"));
			if(entity.getCulPaperid()!=null) objSbf.append("&culPaperid=").append(URLEncoder.encode(entity.getCulPaperid(),"utf-8"));
			if(entity.getCulName()!=null) objSbf.append("&culName=").append(URLEncoder.encode(entity.getCulName(),"utf-8"));
			if(entity.getTypeSolve()!=null) objSbf.append("&typeSolve=").append(entity.getTypeSolve());
			if(entity.getNumCrime()!=null) objSbf.append("&numCrime=").append(entity.getNumCrime());
			if(entity.getNumFlee()!=null) objSbf.append("&numFlee=").append(entity.getNumFlee());
			if(entity.getNumArrest()!=null) objSbf.append("&numArrest=").append(entity.getNumArrest());
			if (entity.getCaseOverDay() != null && !"".equals(entity.getCaseOverDay())) {
				String caseOverDay = DateFormatUtils.format(entity.getCaseOverDay(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&caseOverDay=").append(URLEncoder.encode(caseOverDay, "utf-8"));
			}
			if(entity.getCaseSolve()!=null) objSbf.append("&caseSolve=").append(URLEncoder.encode(entity.getCaseSolve(),"utf-8"));
			if(entity.getFile1()!=null){ 
				String urlFiles = entity.getFile1();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&file1=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&file1=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getFile2()!=null){
				String urlFiles = entity.getFile2();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&file2=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&file2=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getFile3()!=null){
				String urlFiles = entity.getFile3();
				if(urlFiles.indexOf("http")==-1){
					if(urlFiles.indexOf("|")==0){
						urlFiles = urlFiles.substring(1, urlFiles.length());
					}
					String str = Global.getConfig("FILE_UPLOAD_URL");
					urlFiles = str + urlFiles;
					urlFiles = urlFiles.replace("|", "|"+str);
					objSbf.append("&file3=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}else{
					objSbf.append("&file3=").append(URLEncoder.encode(urlFiles,"utf-8"));
				}
			}
			if(entity.getStatus()!=null) objSbf.append("&status=").append(URLEncoder.encode(entity.getStatus(),"utf-8"));
			
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/event/saveSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}

  	/**
  	 * 方法说明：上传重点地区排查整治数据
  	 *
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-5-29
  	 * @其他：
  	 */
	public void uploadEventKacc(String url, String userId, CcmUploadLog uploadLog) throws IOException {
		boolean isSuccess = false;
		if ("3".equals(uploadLog.getOperateType())) {//操作类型：删除
			String urlRest = url + "/app/rest/event/deleteKaccSyn?userId=" + userId + "&id=" + uploadLog.getDataId();
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		} else if ("1".equals(uploadLog.getOperateType()) || "2".equals(uploadLog.getOperateType())) {//操作类型：新增或编辑
			CcmEventKacc entity = ccmEventKaccService.get(uploadLog.getDataId());
			StringBuffer objSbf = new StringBuffer();
			if(entity.getId()!=null)objSbf.append("&id=").append(URLEncoder.encode(entity.getId(),"utf-8"));
			if(entity.getSecuPlace()!=null)objSbf.append("&secuPlace=").append(URLEncoder.encode(entity.getSecuPlace(),"utf-8"));
			if(entity.getArea()!=null&&entity.getArea().getId()!=null)objSbf.append("&area.id=").append(entity.getArea().getId());
			if(entity.getSecuProb()!=null)objSbf.append("&secuProb=").append(URLEncoder.encode(entity.getSecuProb(),"utf-8"));
			if(entity.getDistType()!=null)objSbf.append("&distType=").append(URLEncoder.encode(entity.getDistType(),"utf-8"));
			if(entity.getDistScope()!=null)objSbf.append("&distScope=").append(URLEncoder.encode(entity.getDistScope(),"utf-8"));
			if(entity.getCompLead()!=null)objSbf.append("&compLead=").append(URLEncoder.encode(entity.getCompLead(),"utf-8"));
			if(entity.getCompPart()!=null)objSbf.append("&compPart=").append(URLEncoder.encode(entity.getCompPart(),"utf-8"));
			if(entity.getCompPrinName()!=null)objSbf.append("&compPrinName=").append(URLEncoder.encode(entity.getCompPrinName(),"utf-8"));
			if(entity.getCompPrinPhone()!=null)objSbf.append("&compPrinPhone=").append(URLEncoder.encode(entity.getCompPrinPhone(),"utf-8"));
			if (entity.getAbarDate() != null && !"".equals(entity.getAbarDate())) {
				String abarDate = DateFormatUtils.format(entity.getAbarDate(), "yyyy-MM-dd HH:mm:ss");
				objSbf.append("&abarDate=").append(URLEncoder.encode(abarDate, "utf-8"));
			}
			if(entity.getAbarSolvNum()!=null)objSbf.append("&abarSolvNum=").append(entity.getAbarSolvNum());
			if(entity.getAbarInveNum()!=null)objSbf.append("&abarInveNum=").append(entity.getAbarInveNum());
			if(entity.getAbarCase()!=null)objSbf.append("&abarCase=").append(URLEncoder.encode(entity.getAbarCase(),"utf-8"));
			if(entity.getResuAsse()!=null)objSbf.append("&resuAsse=").append(URLEncoder.encode(entity.getResuAsse(),"utf-8"));
			if(entity.getRemarks()!=null)objSbf.append("&remarks=").append(URLEncoder.encode(entity.getRemarks(),"utf-8"));
			if(entity.getDelFlag()!=null)objSbf.append("&delFlag=").append(URLEncoder.encode(entity.getDelFlag(),"utf-8"));
			
			
			if ("1".equals(uploadLog.getOperateType())) {//操作类型：新增
				objSbf.append("&isNewRecord=true");
			}
			
			String urlRest = url + "/app/rest/event/saveKaccSyn?userId=" + userId + objSbf.toString();
			System.out.println(urlRest);
			String restReturn = Tool.postRestReturn(urlRest);
			if (restReturn != null && !"".equals(restReturn)) {
				JSONObject jsonLogin = JSONObject.fromObject(restReturn);
				int code = jsonLogin.getInt("code");
				if (code == CcmRestType.OK) {
					isSuccess = true;
				}
			}
		}
		//更新数据的上传状态
		if (isSuccess) {
			uploadLog.setUploadStatus("2");//2已上传
		} else {
			uploadLog.setUploadStatus("3");//3上传失败
		}
		ccmUploadLogService.save(uploadLog);
	}
	
	
	

	public static void main(String args[]) throws Exception {
		CcmRestUploadLog ccmRestUploadLog = new CcmRestUploadLog();
		ccmRestUploadLog.uploadLog();
		
	}
	
	
	/**
	 * 方法说明：定时上传本级app的数据至上级平台（通过本表字段同步数据）
	 * @return 
	 * @author liang
	 * @version 2018-09-09
	 */
	public void uploadLogApp() throws Exception {

		/**    1、从sysconfig中获取上级平台接口信息，进行接口登录     **/
		SysConfig sysConfig = sysConfigService.get("upper_system_config");
		JSONObject jsonObject = JSONObject.fromObject(sysConfig.getParamStr());
		String url = jsonObject.getString("url");
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		System.out.println(url);
		if (url == null || "".equals(url)) {
			return;
		}
		
		
		/**    2、查询待上传的数据     **/
		//移动端
		List<CcmMobileDevice> ccmMobileDeviceList = ccmMobileDeviceService.findListApp(new CcmMobileDevice());
		//点位
		CcmTracingpoint ccmTracingpoint = new CcmTracingpoint();
		ccmTracingpoint.setUploadStatus("1");
		List<CcmTracingpoint> ccmTracingpointList = ccmTracingpointService.findList(ccmTracingpoint);
		if (ccmMobileDeviceList.size()==0 && ccmTracingpointList.size()==0) {
			return;
		}
		
		
		/**    3、登录上级平台，获取userId     **/
		//登录上级平台
		String urlLogin = url + "/app/rest/login/login?loginName=" + username + "&password=" + password;
		System.out.println(urlLogin);
		String loginReturn = Tool.postRestReturn(urlLogin);
		//{"code":0,"result":{"id":"1","isNewRecord":false,"remarks":"最高管理员","createDate":"2013-05-27 08:00:00","updateDate":"2018-04-02 19:28:57","loginName":"admin","no":"0001","name":"系统管理员","email":"admin@163.com","phone":"78654","mobile":"8675","userType":"1","loginIp":"192.168.1.102","loginDate":"2018-05-12 15:44:42","loginFlag":"1","photo":"/arjccm/userfiles/1/images/photo/2018/03/guihuada.gif","oldLoginIp":"192.168.1.102","oldLoginDate":"2018-05-12 15:44:42","admin":true,"roleNames":""},"fileServerUrl":"http://192.168.11.205:8080","returnFlag":false}
		System.out.println(loginReturn);
		String userId = "";
		if (loginReturn != null && !"".equals(loginReturn)) {
			JSONObject jsonLogin = JSONObject.fromObject(loginReturn);
			int code = jsonLogin.getInt("code");
			if (code != CcmRestType.OK) {
				return;
			}
			String resultContent = jsonLogin.getString("result");
			JSONObject jsonLoginUser = JSONObject.fromObject(resultContent);
			userId = jsonLoginUser.getString("id");
		}
		
		/**    4、按上传数据日志的类型，对数据分别进行上传     **/
		//移动端
		if(ccmMobileDeviceList.size()>0) {
			uploadCcmMobileDevice(url, userId, ccmMobileDeviceList);//app移动设备管理
		}
		//点位
		if(ccmTracingpointList.size()>0) {
			uploadTracingpoint(url, userId, ccmTracingpointList);//点位管理
		}
		
		
  	 }

  	/**
  	 * 方法说明：点位管理数据
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-09-09
  	 * @其他：
  	 */
	public void uploadTracingpoint (String url, String userId, List<CcmTracingpoint> ccmTracingpointList) throws IOException {
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"currentUser",
				"dbName","global","isNewRecord","page","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String JsonString = JSONArray.fromObject(ccmTracingpointList,config).toString(); //Json
		JsonString = URLEncoder.encode(JsonString,"utf-8");
		//JsonString = "123";
		String urlRest = url + "/app/rest/patrol/ccmRestTracingpoint/saveSyn?JsonString="+JsonString;
		System.out.println(urlRest);
		String restReturn = Tool.postRestReturn(urlRest);
		System.out.println(restReturn);
		for (CcmTracingpoint ccmTracingpoint : ccmTracingpointList) {
			ccmTracingpoint.setUploadStatus("2");
			ccmTracingpointService.save(ccmTracingpoint);
		}
		
	}
	
	/**
  	 * 方法说明： app移动设备管理数据
  	 * @param Url
  	 * @param userId
  	 * @param uploadLog
  	 * @return 
  	 * @作者及日期：liangwanmin    2018-09-09
  	 * @其他：
  	 */
	public void uploadCcmMobileDevice(String url, String userId, List<CcmMobileDevice> ccmMobileDeviceList) throws IOException {
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"currentUser",
				"beginCreateDate","endCreateDate",
				"dbName","global","isNewRecord","page","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String JsonString = JSONArray.fromObject(ccmMobileDeviceList,config).toString(); //Json
		JsonString = URLEncoder.encode(JsonString,"utf-8");
		//JsonString = "123";
		String urlRest = url + "/app/rest/ccmMobileDevice/saveSyn?JsonString="+JsonString;
		System.out.println(urlRest);
		String restReturn = Tool.postRestReturn(urlRest);
		System.out.println(restReturn);
		
	}
	
	
	
	
	

}