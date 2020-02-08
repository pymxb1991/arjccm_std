/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.importTool.FourHisListType;
import com.arjjs.ccm.tool.importTool.HelpcaseListType;
import com.arjjs.ccm.tool.importTool.HospReasonListType;
import com.arjjs.ccm.tool.importTool.ManagementListType;

/**
 * 肇事肇祸等严重精神障碍患者Entity
 * 
 * @author arj
 * @version 2018-01-05
 */
public class CcmHousePsychogeny extends DataEntity<CcmHousePsychogeny> {

	private static final long serialVersionUID = 1L;
	private String peopleId; // 实有人口id
	private String economic; // 家庭经济状况
	private Integer allowance; // 是否纳入低保
	private String guarIden; // 监护人公民身份证号码
	private String guarName; // 监护人姓名
	private String guarTel; // 监护人联系方式
	private Date dateonset; // 初次发病日期
	private Integer diagType; // 目前诊断类型
	private String atteType; // 关注程度
	private Integer acciHist; // 有无肇事肇祸史
	private Integer acciCount; // 肇事肇祸次数
	private Date acciLast; // 上次肇亊肇祸日期
	private String dangAsse; // 目前危险性评估等级
	private Integer treaCond; // 治疗情况
	private String hospital; // 治疗医院名称
	private String hospReason; // 实施医院治疗原因
	private String traiInst; // 接收康复训练机构名称
	private String management; // 参与管理人员
	private String helpcase; // 帮扶情况
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

	public CcmHousePsychogeny() {
		super();
	}

	public CcmHousePsychogeny(String id) {
		super(id);
	}
//	@ExcelField(title="实有人口（id）", align=2, sort=26)
//	@Length(min = 1, max = 64, message = "实有人口id长度必须介于 1 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	
	@ExcelField(title="家庭经济状况", align=2, sort=2 ,dictType="ccm_famy_codt")
	@Length(min = 0, max = 2, message = "家庭经济状况长度必须介于 0 和 2 之间")
	public String getEconomic() {
		return economic;
	}

	public void setEconomic(String economic) {
		this.economic = economic;
	}

	@ExcelField(title="是否纳入低保", align=2, sort=3 ,dictType="yes_no")
	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	@ExcelField(title="监护人公民身份证号码", align=2, sort=4)
	@Length(min = 0, max = 18, message = "监护人公民身份证号码长度必须介于 0 和 18 之间")
	public String getGuarIden() {
		return guarIden;
	}

	public void setGuarIden(String guarIden) {
		this.guarIden = guarIden;
	}

	@ExcelField(title="监护人姓名", align=2, sort=5)
	@Length(min = 0, max = 50, message = "监护人姓名长度必须介于 0 和 50 之间")
	public String getGuarName() {
		return guarName;
	}

	public void setGuarName(String guarName) {
		this.guarName = guarName;
	}

	@ExcelField(title="监护人联系方式", align=2, sort=6)
	@Length(min = 0, max = 50, message = "监护人联系方式长度必须介于 0 和 50 之间")
	public String getGuarTel() {
		return guarTel;
	}

	public void setGuarTel(String guarTel) {
		this.guarTel = guarTel;
	}

	@ExcelField(title="初次发病日期", align=2, sort=7)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateonset() {
		return dateonset;
	}

	public void setDateonset(Date dateonset) {
		this.dateonset = dateonset;
	}

	@ExcelField(title="目前诊断类型", align=2, sort=8,dictType="ccm_now_diag")
	public Integer getDiagType() {
		return diagType;
	}

	public void setDiagType(Integer diagType) {
		this.diagType = diagType;
	}

	@ExcelField(title="有无肇事肇祸史", align=2, sort=10,dictType="yes_no")
	public Integer getAcciHist() {
		return acciHist;
	}

	public void setAcciHist(Integer acciHist) {
		this.acciHist = acciHist;
	}

	@ExcelField(title="肇事肇祸次数", align=2, sort=11)
	public Integer getAcciCount() {
		return acciCount;
	}

	public void setAcciCount(Integer acciCount) {
		this.acciCount = acciCount;
	}

	@ExcelField(title="上次肇亊肇祸日期", align=2, sort=12)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcciLast() {
		return acciLast;
	}

	public void setAcciLast(Date acciLast) {
		this.acciLast = acciLast;
	}

	@ExcelField(title="目前危险性评估等级", align=2, sort=13,dictType="ccm_nors_asle")
	@Length(min = 0, max = 2, message = "目前危险性评估等级长度必须介于 0 和 2 之间")
	public String getDangAsse() {
		return dangAsse;
	}

	public void setDangAsse(String dangAsse) {
		this.dangAsse = dangAsse;
	}

	@ExcelField(title="治疗情况", align=2, sort=14,dictType="ccm_treat_cond")
	public Integer getTreaCond() {
		return treaCond;
	}

	public void setTreaCond(Integer treaCond) {
		this.treaCond = treaCond;
	}

	@ExcelField(title="治疗医院名称", align=2, sort=15)
	@Length(min = 0, max = 100, message = "治疗医院名称长度必须介于 0 和 100 之间")
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@ExcelField(title="实施医院治疗原因", align=32, sort=16, fieldType = HospReasonListType.class)
	@Length(min = 0, max = 32, message = "实施医院治疗原因长度必须介于 0 和 32 之间")
	public String getHospReason() {
		return hospReason;
	}

	public void setHospReason(String hospReason) {
		this.hospReason = hospReason;
	}

	public List<String> getHospReasonList() {
		List<String> list = Lists.newArrayList();
		if (hospReason != null){
			for (String s : StringUtils.split(hospReason, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	public void setHospReasonList(List<String> list) {
		hospReason = ","+StringUtils.join(list, ",")+",";
	}
	

	
	@ExcelField(title="接收康复训练机构名称", align=2, sort=17)
	@Length(min = 0, max = 100, message = "接收康复训练机构名称长度必须介于 0 和 100 之间")
	public String getTraiInst() {
		return traiInst;
	}

	public void setTraiInst(String traiInst) {
		this.traiInst = traiInst;
	}

	@ExcelField(title="参与管理人员", align=32, sort=18, fieldType = ManagementListType.class)
	@Length(min = 0, max = 32, message = "参与管理人员长度必须介于 0 和 32 之间")
	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}


	public List<String> getManagementList() {
		List<String> list = Lists.newArrayList();
		if (management != null){
			for (String s : StringUtils.split(management, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	public void setManagementList(List<String> list) {
		management = ","+StringUtils.join(list, ",")+",";
	}
	
	
	@ExcelField(title="帮扶情况", align=64, sort=19, fieldType = HelpcaseListType.class)
	@Length(min = 0, max = 64, message = "帮扶情况长度必须介于 0 和 64 之间")
	public String getHelpcase() {
		return helpcase;
	}

	public void setHelpcase(String helpcase) {
		this.helpcase = helpcase;
	}
	
	public List<String> getHelpcaseList() {
		List<String> list = Lists.newArrayList();
		if (helpcase != null){
			for (String s : StringUtils.split(helpcase, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	public void setHelpcaseList(List<String> list) {
		helpcase = ","+StringUtils.join(list, ",")+",";
	}
	
	
	@ExcelField(title="人口类型", align=2, sort=20,dictType="sys_ccm_people")
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

	@ExcelField(title="籍贯", align=2, sort=21)
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}

	@ExcelField(title="性别", align=2, sort=22,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ExcelField(title="公民身份号码", align=2, sort=23)
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@ExcelField(title="联系方式", align=2, sort=24)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@ExcelField(title="户籍门（楼）详址", align=2, sort=25)
	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}

	@ExcelField(title="现住门（楼）详址", align=2, sort=26)
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
	@ExcelField(title="关注程度", align=2, sort=9,dictType="ccm_conc_exte")
	@Length(min = 0, max = 2, message = "关注程度长度必须介于 0 和 2 之间")
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