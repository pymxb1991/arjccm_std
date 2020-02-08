/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 艾滋病患者Entity
 * 
 * @author arj
 * @version 2018-01-05
 */
public class CcmHouseAids extends DataEntity<CcmHouseAids> {

	private static final long serialVersionUID = 1L;
	private String peopleId; // 实有人口ID
	private String infeRoute; // 感染途径
	private Integer crimPast; // 是否有违法犯罪史
	private String crimStat; // 违法犯罪情况
	private String cateCase; // 案事件类别
	private String atteType; // 关注程度
	private String helpCase; // 帮扶情况
	private String helpName; // 帮扶人姓名
	private String helpTel; // 帮扶人联系方式
	private String treaCase; // 收治情况
	private String institutions; // 收治机构名称
	private String attention; // 关注类型
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

	
	public CcmHouseAids() {
		super();
	}

	public CcmHouseAids(String id) {
		super(id);
	}
	
//	@ExcelField(title="实有人口（id）", align=2, sort=18)
//	@Length(min = 1, max = 64, message = "实有人口ID长度必须介于 1 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	
	@ExcelField(title="感染途径", align=2, sort=2,dictType="ccm_infe_rout")
	@Length(min = 0, max = 2, message = "感染途径长度必须介于 0 和 2 之间")
	public String getInfeRoute() {
		return infeRoute;
	}

	public void setInfeRoute(String infeRoute) {
		this.infeRoute = infeRoute;
	}

	@ExcelField(title="是否有违法犯罪史", align=2, sort=3,dictType="yes_no")
	public Integer getCrimPast() {
		return crimPast;
	}

	public void setCrimPast(Integer crimPast) {
		this.crimPast = crimPast;
	}

	@ExcelField(title="违法犯罪情况", align=2, sort=4)
	@Length(min = 0, max = 1024, message = "违法犯罪情况长度必须介于 0 和 1024 之间")
	public String getCrimStat() {
		return crimStat;
	}

	public void setCrimStat(String crimStat) {
		this.crimStat = crimStat;
	}

	@ExcelField(title="案事件类别", align=2, sort=5)
	@Length(min = 0, max = 6, message = "案事件类别长度必须介于 0 和 6 之间")
	public String getCateCase() {
		return cateCase;
	}

	public void setCateCase(String cateCase) {
		this.cateCase = cateCase;
	}

	@ExcelField(title="关注程度", align=2, sort=6,dictType="ccm_conc_exte")
	@Length(min = 0, max = 2, message = "关注程度长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}

	@ExcelField(title="帮扶情况", align=2, sort=7)
	@Length(min = 0, max = 1024, message = "帮扶情况长度必须介于 0 和 1024 之间")
	public String getHelpCase() {
		return helpCase;
	}

	public void setHelpCase(String helpCase) {
		this.helpCase = helpCase;
	}

	@ExcelField(title="帮扶人姓名", align=2, sort=8)
	@Length(min = 0, max = 50, message = "帮扶人姓名长度必须介于 0 和 50 之间")
	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}

	@ExcelField(title="帮扶人联系方式", align=2, sort=9)
	@Length(min = 0, max = 50, message = "帮扶人联系方式长度必须介于 0 和 50 之间")
	public String getHelpTel() {
		return helpTel;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	@ExcelField(title="收治情况", align=2, sort=10,dictType="ccm_tred_case")
	@Length(min = 0, max = 2, message = "收治情况长度必须介于 0 和 2 之间")
	public String getTreaCase() {
		return treaCase;
	}

	public void setTreaCase(String treaCase) {
		this.treaCase = treaCase;
	}

	@ExcelField(title="收治机构名称", align=2, sort=11)
	@Length(min = 0, max = 100, message = "收治机构名称长度必须介于 0 和 100 之间")
	public String getInstitutions() {
		return institutions;
	}

	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}
	
	@ExcelField(title="关注类型", align=2, sort=19,dictType="ccm_focu_type")
	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}
	
	@ExcelField(title="人口类型", align=2, sort=12,dictType="sys_ccm_people")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ExcelField(title="姓名", align=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="籍贯", align=2, sort=13)
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}

	@ExcelField(title="性别", align=2, sort=14,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ExcelField(title="公民身份号码", align=2, sort=15)
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@ExcelField(title="联系方式", align=2, sort=16)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@ExcelField(title="户籍门（楼）详址", align=2, sort=17)
	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}

	@ExcelField(title="现住门（楼）详址", align=2, sort=18)
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