/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 安置帮教人员Entity
 * 
 * @author arj
 * @version 2018-01-04
 */
public class CcmHouseRelease extends DataEntity<CcmHouseRelease> {

	private static final long serialVersionUID = 1L;
	private String peopleId; // 实有人口（id）
	private String recidivism; // 是否累犯
	private String origCha; // 原罪名
	private String atteType; // 关注程度
	private String sentence; // 原判刑期
	private String servinGplace; // 服刑场所
	private Date releDate; // 释放日期
	private String risk; // 危险性评估类型
	private Date joinDate; // 衔接日期
	private String joinCond; // 衔接情况
	private Date placeDate; // 安置日期
	private String placement; // 安置情况
	private String notPlace; // 未安置原因
	private String helpCase; // 帮教情况
	private Integer reoffend; // 是否重新犯罪
	private String reofCharge; // 重新犯罪罪名
	private Date beginReleDate; // 开始 释放日期
	private Date endReleDate; // 结束 释放日期
	private String atteTypeLable;	//app接口使用

	// 实有人口
	private String type; // 人口类型
	private String name; // 姓名
	private String censu; // 籍贯
	private String sex; // 性别
	private String ident; // 公民身份号码
	private String telephone; // 联系方式

	private String domiciledetail; // 户籍门（楼）详址
	private String residencedetail; // 现住门（楼）详址
	private String images; // 图片
	private Date birthday;	//出生日期
	private String comName; 	//app接口使用，所属社区

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
	public CcmHouseRelease() {
		super();
	}

	public CcmHouseRelease(String id) {
		super(id);
	}

//	@ExcelField(title="实有人口（id）", align=2, sort=23)
//	@Length(min = 1, max = 64, message = "实有人口（id）长度必须介于 1 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	
	@ExcelField(title="是否累犯", align=2, sort=2,dictType="yes_no")
	@Length(min = 0, max = 1, message = "是否累犯长度必须介于 0 和 1 之间")
	public String getRecidivism() {
		return recidivism;
	}

	public void setRecidivism(String recidivism) {
		this.recidivism = recidivism;
	}

	@ExcelField(title="原罪名", align=2, sort=3,dictType="ccm_chag_type")
	@Length(min = 0, max = 2, message = "原罪名长度必须介于 0 和 2 之间")
	public String getOrigCha() {
		return origCha;
	}

	public void setOrigCha(String origCha) {
		this.origCha = origCha;
	}

	@ExcelField(title="原判刑期", align=2, sort=4)
	@Length(min = 0, max = 50, message = "原判刑期长度必须介于 0 和 50 之间")
	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	@ExcelField(title="服刑场所", align=2, sort=5)
	@Length(min = 0, max = 100, message = "服刑场所长度必须介于 0 和 100 之间")
	public String getServinGplace() {
		return servinGplace;
	}

	public void setServinGplace(String servinGplace) {
		this.servinGplace = servinGplace;
	}

	@ExcelField(title="释放日期", align=2, sort=6)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleDate() {
		return releDate;
	}

	public void setReleDate(Date releDate) {
		this.releDate = releDate;
	}

	@ExcelField(title="危险性评估类型", align=2, sort=7,dictType="ccm_nors_asle")
	@Length(min = 0, max = 2, message = "危险性评估类型长度必须介于 0 和 2 之间")
	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	@ExcelField(title="衔接日期", align=2, sort=8)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@ExcelField(title="衔接情况", align=2, sort=9,dictType="ccm_gose_case")
	@Length(min = 0, max = 2, message = "衔接情况长度必须介于 0 和 2 之间")
	public String getJoinCond() {
		return joinCond;
	}

	public void setJoinCond(String joinCond) {
		this.joinCond = joinCond;
	}

	@ExcelField(title="安置日期", align=2, sort=10)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlaceDate() {
		return placeDate;
	}

	public void setPlaceDate(Date placeDate) {
		this.placeDate = placeDate;
	}

	@ExcelField(title="安置情况", align=2, sort=11,dictType="ccm_plam_case")
	@Length(min = 0, max = 2, message = "安置情况长度必须介于 0 和 2 之间")
	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	@ExcelField(title="未安置原因", align=2, sort=12)
	@Length(min = 0, max = 1024, message = "未安置原因长度必须介于 0 和 1024 之间")
	public String getNotPlace() {
		return notPlace;
	}

	public void setNotPlace(String notPlace) {
		this.notPlace = notPlace;
	}

	@ExcelField(title="帮教情况", align=2, sort=13)
	@Length(min = 0, max = 1024, message = "帮教情况长度必须介于 0 和 1024 之间")
	public String getHelpCase() {
		return helpCase;
	}

	public void setHelpCase(String helpCase) {
		this.helpCase = helpCase;
	}

	@ExcelField(title="是否重新犯罪", align=2, sort=14,dictType="yes_no")
	public Integer getReoffend() {
		return reoffend;
	}

	public void setReoffend(Integer reoffend) {
		this.reoffend = reoffend;
	}

	@ExcelField(title="重新犯罪罪名", align=2, sort=15)
	@Length(min = 0, max = 100, message = "重新犯罪罪名长度必须介于 0 和 100 之间")
	public String getReofCharge() {
		return reofCharge;
	}

	public void setReofCharge(String reofCharge) {
		this.reofCharge = reofCharge;
	}

	
	public Date getBeginReleDate() {
		return beginReleDate;
	}

	public void setBeginReleDate(Date beginReleDate) {
		this.beginReleDate = beginReleDate;
	}

	
	public Date getEndReleDate() {
		return endReleDate;
	}

	public void setEndReleDate(Date endReleDate) {
		this.endReleDate = endReleDate;
	}

	@ExcelField(title="人口类型", align=2, sort=16,dictType="sys_ccm_people")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@ExcelField(title="姓名", align=1, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title="籍贯", align=2, sort=17)
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}

	@ExcelField(title="性别", align=2, sort=18,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ExcelField(title="公民身份号码", align=2, sort=19)
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@ExcelField(title="联系方式", align=2, sort=20)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@ExcelField(title="户籍门（楼）详址", align=2, sort=21)
	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}

	@ExcelField(title="现住门（楼）详址", align=2, sort=22)
	public String getResidencedetail() {
		return residencedetail;
	}

	public void setResidencedetail(String residencedetail) {
		this.residencedetail = residencedetail;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	@ExcelField(title="关注程度", align=2, sort=23,dictType="ccm_conc_exte")
	@Length(min = 0, max = 2, message = "关注类型长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}
	public String getAtteTypeLable() {
		return atteTypeLable;
	}
	public void setAtteTypeLable(String atteTypeLable) {
		this.atteTypeLable = atteTypeLable;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}