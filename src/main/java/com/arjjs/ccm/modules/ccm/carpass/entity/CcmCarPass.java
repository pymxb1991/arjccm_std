package com.arjjs.ccm.modules.ccm.carpass.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.bayonet.entity.CcmCarBayonet;
import com.arjjs.ccm.modules.ccm.lane.entity.CcmLane;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 过车查询Entity
 * @author liuyongjian
 * @version 2019-07-24
 */
public class CcmCarPass extends DataEntity<CcmCarPass> {
	
	private static final long serialVersionUID = 1L;
	private String plate;		// 车牌
	private String number;		// 车牌号码
	private String bayonet;		// 所属卡口
	private String lane;		// 所属车道
	private String brand;		// 车辆品牌
	private String type;		// 车辆类型
	private Date passtime;		// 过车时间
	private String direction;		// 方向
	private String lanenumber;		// 车道序号
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private String condition;		// 行驶状态
	private String speed;		// 车辆速度
	private String platetype;		// 车牌类型
	private String platecolor;		// 车牌颜色
	private String cartype;		// 车辆类型
	private String carcolor;		// 车辆颜色
	private String illegaltype;		// 违法类型
	private String sunlouver;		// 遮阳板
	private String subbrand;		// 车辆子品牌
	private String outincity;		// 出入城
	private String issafety;		// 系安全带
	private String pendant;		// 挂件
	private String iscall;		// 是否打电话
	private String yellowcar;		// 是否黄标车
	private String dangerous;		// 是否危险品
	private String chronology;		// 年代款
	private String headdirection;		// 车头方向
	private String carphotos;		// 车辆照片
	private Date beginPasstime;		// 开始 过车时间
	private Date endPasstime;		// 结束 过车时间
	private CcmCarBayonet ccmCarBayonet;	//路口信息
	private CcmLane ccmLane;	//车道信息
	
	public CcmCarPass() {
		super();
	}

	public CcmCarPass(String id){
		super(id);
	}

	@Length(min=0, max=255, message="车牌长度必须介于 0 和 255 之间")
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	@Length(min=0, max=11, message="车牌号码长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=255, message="所属卡口长度必须介于 0 和 255 之间")
	public String getBayonet() {
		return bayonet;
	}

	public void setBayonet(String bayonet) {
		this.bayonet = bayonet;
	}
	
	@Length(min=0, max=255, message="所属车道长度必须介于 0 和 255 之间")
	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}
	
	@Length(min=0, max=255, message="车辆品牌长度必须介于 0 和 255 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=255, message="车辆类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPasstime() {
		return passtime;
	}

	public void setPasstime(Date passtime) {
		this.passtime = passtime;
	}
	
	@Length(min=0, max=255, message="方向长度必须介于 0 和 255 之间")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	@Length(min=0, max=255, message="车道序号长度必须介于 0 和 255 之间")
	public String getLanenumber() {
		return lanenumber;
	}

	public void setLanenumber(String lanenumber) {
		this.lanenumber = lanenumber;
	}
	
	@Length(min=0, max=255, message="经度长度必须介于 0 和 255 之间")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=255, message="纬度长度必须介于 0 和 255 之间")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Length(min=0, max=255, message="行驶状态长度必须介于 0 和 255 之间")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	@Length(min=0, max=255, message="车辆速度长度必须介于 0 和 255 之间")
	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	@Length(min=0, max=255, message="车牌类型长度必须介于 0 和 255 之间")
	public String getPlatetype() {
		return platetype;
	}

	public void setPlatetype(String platetype) {
		this.platetype = platetype;
	}
	
	@Length(min=0, max=255, message="车牌颜色长度必须介于 0 和 255 之间")
	public String getPlatecolor() {
		return platecolor;
	}

	public void setPlatecolor(String platecolor) {
		this.platecolor = platecolor;
	}
	
	@Length(min=0, max=255, message="车辆类型长度必须介于 0 和 255 之间")
	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	
	@Length(min=0, max=255, message="车辆颜色长度必须介于 0 和 255 之间")
	public String getCarcolor() {
		return carcolor;
	}

	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}
	
	@Length(min=0, max=255, message="违法类型长度必须介于 0 和 255 之间")
	public String getIllegaltype() {
		return illegaltype;
	}

	public void setIllegaltype(String illegaltype) {
		this.illegaltype = illegaltype;
	}
	
	@Length(min=0, max=255, message="遮阳板长度必须介于 0 和 255 之间")
	public String getSunlouver() {
		return sunlouver;
	}

	public void setSunlouver(String sunlouver) {
		this.sunlouver = sunlouver;
	}
	
	@Length(min=0, max=255, message="车辆子品牌长度必须介于 0 和 255 之间")
	public String getSubbrand() {
		return subbrand;
	}

	public void setSubbrand(String subbrand) {
		this.subbrand = subbrand;
	}
	
	@Length(min=0, max=255, message="出入城长度必须介于 0 和 255 之间")
	public String getOutincity() {
		return outincity;
	}

	public void setOutincity(String outincity) {
		this.outincity = outincity;
	}
	
	@Length(min=0, max=255, message="系安全带长度必须介于 0 和 255 之间")
	public String getIssafety() {
		return issafety;
	}

	public void setIssafety(String issafety) {
		this.issafety = issafety;
	}
	
	@Length(min=0, max=255, message="挂件长度必须介于 0 和 255 之间")
	public String getPendant() {
		return pendant;
	}

	public void setPendant(String pendant) {
		this.pendant = pendant;
	}
	
	@Length(min=0, max=255, message="是否打电话长度必须介于 0 和 255 之间")
	public String getIscall() {
		return iscall;
	}

	public void setIscall(String iscall) {
		this.iscall = iscall;
	}
	
	@Length(min=0, max=255, message="是否黄标车长度必须介于 0 和 255 之间")
	public String getYellowcar() {
		return yellowcar;
	}

	public void setYellowcar(String yellowcar) {
		this.yellowcar = yellowcar;
	}
	
	@Length(min=0, max=255, message="是否危险品长度必须介于 0 和 255 之间")
	public String getDangerous() {
		return dangerous;
	}

	public void setDangerous(String dangerous) {
		this.dangerous = dangerous;
	}
	
	@Length(min=0, max=255, message="年代款长度必须介于 0 和 255 之间")
	public String getChronology() {
		return chronology;
	}

	public void setChronology(String chronology) {
		this.chronology = chronology;
	}
	
	@Length(min=0, max=255, message="车头方向长度必须介于 0 和 255 之间")
	public String getHeaddirection() {
		return headdirection;
	}

	public void setHeaddirection(String headdirection) {
		this.headdirection = headdirection;
	}
	
	@Length(min=0, max=255, message="车辆照片长度必须介于 0 和 255 之间")
	public String getCarphotos() {
		return carphotos;
	}

	public void setCarphotos(String carphotos) {
		this.carphotos = carphotos;
	}
	
	public Date getBeginPasstime() {
		return beginPasstime;
	}

	public void setBeginPasstime(Date beginPasstime) {
		this.beginPasstime = beginPasstime;
	}
	
	public Date getEndPasstime() {
		return endPasstime;
	}

	public void setEndPasstime(Date endPasstime) {
		this.endPasstime = endPasstime;
	}

	public CcmCarBayonet getCcmCarBayonet() {
		return ccmCarBayonet;
	}

	public void setCcmCarBayonet(CcmCarBayonet ccmCarBayonet) {
		this.ccmCarBayonet = ccmCarBayonet;
	}

	public CcmLane getCcmLane() {
		return ccmLane;
	}

	public void setCcmLane(CcmLane ccmLane) {
		this.ccmLane = ccmLane;
	}
}