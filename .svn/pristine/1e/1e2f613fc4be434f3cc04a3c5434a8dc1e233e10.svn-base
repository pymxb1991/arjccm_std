/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 系统信息配置Entity
 * 
 * @author liang
 * @version 2018-05-10
 */
public class SysConfig extends DataEntity<SysConfig> {

	private static final long serialVersionUID = 1L;
	private String paramStr; // 信息
	private int paramInt; // 信息
	private Date paramDate; // 信息
	private String tableName; // 所在表
	private String objId; // 对象id
	private Date beginParamDate; // 开始 信息
	private Date endParamDate; // 结束 信息

	private String url; // url
	private String username; // username
	private String password; // password

	private SysMapConfig sysMapConfig; // 地图配置信息实体

	public static final String MAP_CONFIG_ID = "map_info_config";// 地图配置ID

	public static final String SPECIAL_PERSONNEL_EVENT_ID = "special_personnel_event";//特殊人员添加触发事件0关闭，1开启

	private String green; // green
	private String yellowMin; // yellowMin
	private String yellowMax; // yellowMax
	private String orangeMin; // orangeMin
	private String orangeMax; // orangeMax
	private String red; // red


	private String quXian;
	private String jieDaoMin;
	private String jieDaoMax;
	private String sheQuMin;
	private String sheQuMax;
	private String wangGe;

    public String getQuXian() {
        return quXian;
    }

    public void setQuXian(String quXian) {
        this.quXian = quXian;
    }

    public String getJieDaoMin() {
        return jieDaoMin;
    }

    public void setJieDaoMin(String jieDaoMin) {
        this.jieDaoMin = jieDaoMin;
    }

    public String getJieDaoMax() {
        return jieDaoMax;
    }

    public void setJieDaoMax(String jieDaoMax) {
        this.jieDaoMax = jieDaoMax;
    }

    public String getSheQuMin() {
        return sheQuMin;
    }

    public void setSheQuMin(String sheQuMin) {
        this.sheQuMin = sheQuMin;
    }

    public String getSheQuMax() {
        return sheQuMax;
    }

    public void setSheQuMax(String sheQuMax) {
        this.sheQuMax = sheQuMax;
    }

    public String getWangGe() {
        return wangGe;
    }

    public void setWangGe(String wangGe) {
        this.wangGe = wangGe;
    }

    private String maxDispatchTime; // 最大派发时间
	private String maxAcceptTime; // 最大签收时间
	private String maxArriveTime; // 最大到达时间
	
	private String flag; //是否自动标记
	private String handleModel; //警情处警单位设置      handleModel = 1   片区片警制   handleModel = 0   抢单模式 
	
	private String alarmFlowFlag; //警情分流设置标志位  1：自动 0手动
	private String alarmHandleFlag;//警情处警单位设置标志们 1：自动 0手动

	public List<String> getParamStrList() {
		List<String> list = Lists.newArrayList();
		if (paramStr != null) {
			for (String s : StringUtils.split(paramStr, ",")) {
				list.add(s);
			}
		}
		return list;
	}

	public void setParamStrList(List<String> paramStrList) {
		paramStr = "," + StringUtils.join(paramStrList, ",") + ",";
	}
	private String apiUrl;
	private String appKey;
	private String appSecet;
	private String svrIp;
	private String svrPort;
	
	private String tiandyIp;
	private String tiandyPort;
	private String tiandyUserName;
	private String tiandyPassWord;
	private String tiandyUserId; 
	
	/*private String ccmHouseAids;	//艾滋病患者
	private String ccmHouseDangerous;//危险品从业者	
	private String ccmHouseDrugs;	//吸毒人员
	private String ccmHouseHeresy;	//涉教人员	
	private String ccmHouseKym;		//重点青少年
	private String ccmHousePetition;//重点上访	
	private String ccmHousePsychogeny;//肇事肇祸等严重精神障碍患者		
	private String ccmHouseRectification;//社区矫正	
	private String ccmHouseRelease;	//安置帮教

	
	public String getCcmHouseAids() {
		return ccmHouseAids;
	}

	public void setCcmHouseAids(String ccmHouseAids) {
		this.ccmHouseAids = ccmHouseAids;
	}

	public String getCcmHouseDangerous() {
		return ccmHouseDangerous;
	}

	public void setCcmHouseDangerous(String ccmHouseDangerous) {
		this.ccmHouseDangerous = ccmHouseDangerous;
	}

	public String getCcmHouseDrugs() {
		return ccmHouseDrugs;
	}

	public void setCcmHouseDrugs(String ccmHouseDrugs) {
		this.ccmHouseDrugs = ccmHouseDrugs;
	}

	public String getCcmHouseHeresy() {
		return ccmHouseHeresy;
	}

	public void setCcmHouseHeresy(String ccmHouseHeresy) {
		this.ccmHouseHeresy = ccmHouseHeresy;
	}

	public String getCcmHouseKym() {
		return ccmHouseKym;
	}

	public void setCcmHouseKym(String ccmHouseKym) {
		this.ccmHouseKym = ccmHouseKym;
	}

	public String getCcmHousePetition() {
		return ccmHousePetition;
	}

	public void setCcmHousePetition(String ccmHousePetition) {
		this.ccmHousePetition = ccmHousePetition;
	}

	public String getCcmHousePsychogeny() {
		return ccmHousePsychogeny;
	}

	public void setCcmHousePsychogeny(String ccmHousePsychogeny) {
		this.ccmHousePsychogeny = ccmHousePsychogeny;
	}

	public String getCcmHouseRectification() {
		return ccmHouseRectification;
	}

	public void setCcmHouseRectification(String ccmHouseRectification) {
		this.ccmHouseRectification = ccmHouseRectification;
	}

	public String getCcmHouseRelease() {
		return ccmHouseRelease;
	}

	public void setCcmHouseRelease(String ccmHouseRelease) {
		this.ccmHouseRelease = ccmHouseRelease;
	}*/
	public String getTiandyIp() {
		return tiandyIp;
	}
	
	public void setTiandyIp(String tiandyIp) {
		this.tiandyIp = tiandyIp;
	}
	
	public String getTiandyPort() {
		return tiandyPort;
	}
	
	public void setTiandyPort(String tiandyPort) {
		this.tiandyPort = tiandyPort;
	}
	
	public String getTiandyUserName() {
		return tiandyUserName;
	}
	
	public void setTiandyUserName(String tiandyUserName) {
		this.tiandyUserName = tiandyUserName;
	}
	
	public String getTiandyPassWord() {
		return tiandyPassWord;
	}
	
	public void setTiandyPassWord(String tiandyPassWord) {
		this.tiandyPassWord = tiandyPassWord;
	}
	
	public String getTiandyUserId() {
		return tiandyUserId;
	}
	
	public void setTiandyUserId(String tiandyUserId) {
		this.tiandyUserId = tiandyUserId;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecet() {
		return appSecet;
	}

	public void setAppSecet(String appSecet) {
		this.appSecet = appSecet;
	}

	public String getSvrIp() {
		return svrIp;
	}

	public void setSvrIp(String svrIp) {
		this.svrIp = svrIp;
	}

	public String getSvrPort() {
		return svrPort;
	}

	public void setSvrPort(String svrPort) {
		this.svrPort = svrPort;
	}

	public String getMaxDispatchTime() {
		return maxDispatchTime;
	}

	public void setMaxDispatchTime(String maxDispatchTime) {
		this.maxDispatchTime = maxDispatchTime;
	}

	public String getMaxAcceptTime() {
		return maxAcceptTime;
	}

	public void setMaxAcceptTime(String maxAcceptTime) {
		this.maxAcceptTime = maxAcceptTime;
	}

	public String getMaxArriveTime() {
		return maxArriveTime;
	}

	public void setMaxArriveTime(String maxArriveTime) {
		this.maxArriveTime = maxArriveTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SysConfig() {
		super();
	}

	public SysConfig(String id) {
		super(id);
	}

	public String getGreen() {
		return green;
	}

	public void setGreen(String green) {
		this.green = green;
	}

	public String getYellowMin() {
		return yellowMin;
	}

	public void setYellowMin(String yellowMin) {
		this.yellowMin = yellowMin;
	}

	public String getYellowMax() {
		return yellowMax;
	}

	public void setYellowMax(String yellowMax) {
		this.yellowMax = yellowMax;
	}

	public String getOrangeMin() {
		return orangeMin;
	}

	public void setOrangeMin(String orangeMin) {
		this.orangeMin = orangeMin;
	}

	public String getOrangeMax() {
		return orangeMax;
	}

	public void setOrangeMax(String orangeMax) {
		this.orangeMax = orangeMax;
	}

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	@Length(min = 0, max = 1024, message = "信息长度必须介于 0 和 1024 之间")
	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public int getParamInt() {
		return paramInt;
	}

	public void setParamInt(int paramInt) {
		this.paramInt = paramInt;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getParamDate() {
		return paramDate;
	}

	public void setParamDate(Date paramDate) {
		this.paramDate = paramDate;
	}

	@Length(min = 0, max = 64, message = "所在表长度必须介于 0 和 64 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Length(min = 0, max = 64, message = "对象id长度必须介于 0 和 64 之间")
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getBeginParamDate() {
		return beginParamDate;
	}

	public void setBeginParamDate(Date beginParamDate) {
		this.beginParamDate = beginParamDate;
	}

	public Date getEndParamDate() {
		return endParamDate;
	}

	public void setEndParamDate(Date endParamDate) {
		this.endParamDate = endParamDate;
	}

	public SysMapConfig getSysMapConfig() {
		return sysMapConfig;
	}

	public void setSysMapConfig(SysMapConfig sysMapConfig) {
		this.sysMapConfig = sysMapConfig;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHandleModel() {
		return handleModel;
	}

	public void setHandleModel(String handleModel) {
		this.handleModel = handleModel;
	}

	public String getAlarmFlowFlag() {
		return alarmFlowFlag;
	}

	public void setAlarmFlowFlag(String alarmFlowFlag) {
		this.alarmFlowFlag = alarmFlowFlag;
	}

	public String getAlarmHandleFlag() {
		return alarmHandleFlag;
	}

	public void setAlarmHandleFlag(String alarmHandleFlag) {
		this.alarmHandleFlag = alarmHandleFlag;
	}
	
}