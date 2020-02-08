/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;

/**
 * 社会组织Entity
 * @author fuxinshuang
 * @version 2018-01-26
 */
public class CcmOrgSocialorg extends DataEntity<CcmOrgSocialorg> {
	
	private static final long serialVersionUID = 1L;
	private String creditCode;		// 统一社会信用代码
	private String regiNum;		// 登记证号
	private String orgName;		// 社会组织名称
	private String regiPlaceNum;		// 登记管理机关代码
	private String legalReprName;		// 法定代表人姓名
	private String place;		// 住所
	private Date apprDate;		// 批准日期
	private String orgType;		// 社会组织类型
	private Integer type;		// 状态
	private String prinCode;		// 负责人证件代码
	private String prinId;		// 负责人证件号码
	private String prinName;		// 负责人姓名
	private String prinTel;		// 负责人联系方式
	private String workAdd;		// 办公地址
	private String secuName;		// 治保负责人姓名
	private String secuTel;		// 治保负责人联系方式
	private String concernExtent;		// 关注程度
	private String estaOrgCond;		// 是否具备建立中共党组织条件
	private String estaOrg;		// 是否有中共党组织
	private Integer partyMem;		// 中共党员数量
	private String laborUnion;		// 是否有工会
	private Integer laborUnionNum;		// 工会会员数量
	private String youthLeagOrg;		// 是否有共青团组织
	private Integer youthLeagOrgNum;		// 共青团团员数量
	private String womenOrg;		// 是否有妇联组织
	private Integer womenNum;		// 妇女数量
	private String capiSour;		// 资金来源
	private String overBack;		// 是否有境外背景
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String icon;		// 图标
	private String images;		// 图片
	private Date beginApprDate;		// 开始 批准日期
	private Date endApprDate;		// 结束 批准日期
	private String concExteLable;	//app接口使用
	public CcmOrgSocialorg() {
		super();
	}

	public CcmOrgSocialorg(String id){
		super(id);
	}
	
	@ExcelField(title="统一社会信用代码", align=2, sort=1)
	@Length(min=0, max=18, message="统一社会信用代码长度必须介于 0 和 18 之间")
	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	
	@ExcelField(title="登记证号", align=2, sort=2)
	@Length(min=0, max=32, message="登记证号长度必须介于 0 和 32 之间")
	public String getRegiNum() {
		return regiNum;
	}

	public void setRegiNum(String regiNum) {
		this.regiNum = regiNum;
	}

	@ExcelField(title="社会组织名称", align=2, sort=3)
	@Length(min=0, max=100, message="社会组织名称长度必须介于 0 和 100 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@ExcelField(title="登记管理机关代码", align=2, sort=4)
	@Length(min=0, max=6, message="登记管理机关代码长度必须介于 0 和 6 之间")
	public String getRegiPlaceNum() {
		return regiPlaceNum;
	}

	public void setRegiPlaceNum(String regiPlaceNum) {
		this.regiPlaceNum = regiPlaceNum;
	}

	@ExcelField(title="法定代表人姓名", align=2, sort=5)
	@Length(min=0, max=80, message="法定代表人姓名长度必须介于 0 和 80 之间")
	public String getLegalReprName() {
		return legalReprName;
	}

	public void setLegalReprName(String legalReprName) {
		this.legalReprName = legalReprName;
	}

	@ExcelField(title="住所", align=2, sort=6)
	@Length(min=0, max=200, message="住所长度必须介于 0 和 200 之间")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@ExcelField(title="批准日期", align=2, sort=7)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApprDate() {
		return apprDate;
	}

	public void setApprDate(Date apprDate) {
		this.apprDate = apprDate;
	}

	@ExcelField(title="社会组织类型", align=2, sort=8,dictType="ccm_sosr_type")
	@Length(min=0, max=2, message="社会组织类型长度必须介于 0 和 2 之间")
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@ExcelField(title="状态", align=2, sort=9)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@ExcelField(title="负责人证件代码", align=2, sort=10,dictType="sys_ccm_org_papers")
	@Length(min=0, max=3, message="负责人证件代码长度必须介于 0 和 3 之间")
	public String getPrinCode() {
		return prinCode;
	}

	public void setPrinCode(String prinCode) {
		this.prinCode = prinCode;
	}

	@ExcelField(title="负责人证件号码", align=2, sort=11)
	@Length(min=0, max=50, message="负责人证件号码长度必须介于 0 和 50 之间")
	public String getPrinId() {
		return prinId;
	}

	public void setPrinId(String prinId) {
		this.prinId = prinId;
	}

	@ExcelField(title="负责人姓名", align=2, sort=12)
	@Length(min=0, max=80, message="负责人姓名长度必须介于 0 和 80 之间")
	public String getPrinName() {
		return prinName;
	}

	public void setPrinName(String prinName) {
		this.prinName = prinName;
	}

	@ExcelField(title="负责人联系方式", align=2, sort=13)
	@Length(min=0, max=50, message="负责人联系方式长度必须介于 0 和 50 之间")
	public String getPrinTel() {
		return prinTel;
	}

	public void setPrinTel(String prinTel) {
		this.prinTel = prinTel;
	}

	@ExcelField(title="办公地址", align=2, sort=14)
	@Length(min=0, max=200, message="办公地址长度必须介于 0 和 200 之间")
	public String getWorkAdd() {
		return workAdd;
	}

	public void setWorkAdd(String workAdd) {
		this.workAdd = workAdd;
	}

	@ExcelField(title="治保负责人姓名", align=2, sort=15)
	@Length(min=0, max=50, message="治保负责人姓名长度必须介于 0 和 50 之间")
	public String getSecuName() {
		return secuName;
	}

	public void setSecuName(String secuName) {
		this.secuName = secuName;
	}

	@ExcelField(title="治保负责人联系方式", align=2, sort=16)
	@Length(min=0, max=50, message="治保负责人联系方式长度必须介于 0 和 50 之间")
	public String getSecuTel() {
		return secuTel;
	}

	public void setSecuTel(String secuTel) {
		this.secuTel = secuTel;
	}

	@ExcelField(title="关注程度", align=2, sort=17, dictType="ccm_conc_exte")
	@Length(min=0, max=2, message="关注程度长度必须介于 0 和 2 之间")
	public String getConcernExtent() {
		return concernExtent;
	}

	public void setConcernExtent(String concernExtent) {
		this.concernExtent = concernExtent;
	}

	@ExcelField(title="是否具备建立中共党组织条件", align=2, sort=18, dictType="yes_no")
	@Length(min=0, max=1, message="是否具备建立中共党组织条件长度必须介于 0 和 1 之间")
	public String getEstaOrgCond() {
		return estaOrgCond;
	}

	public void setEstaOrgCond(String estaOrgCond) {
		this.estaOrgCond = estaOrgCond;
	}

	@ExcelField(title="是否有中共党组织", align=2, sort=19, dictType="yes_no")
	@Length(min=0, max=1, message="是否有中共党组织长度必须介于 0 和 1 之间")
	public String getEstaOrg() {
		return estaOrg;
	}

	public void setEstaOrg(String estaOrg) {
		this.estaOrg = estaOrg;
	}

	@ExcelField(title="中共党员数量", align=2, sort=20)
	public Integer getPartyMem() {
		return partyMem;
	}

	public void setPartyMem(Integer partyMem) {
		this.partyMem = partyMem;
	}

	@ExcelField(title="是否有工会", align=2, sort=21, dictType="yes_no")
	@Length(min=0, max=1, message="是否有工会长度必须介于 0 和 1 之间")
	public String getLaborUnion() {
		return laborUnion;
	}

	public void setLaborUnion(String laborUnion) {
		this.laborUnion = laborUnion;
	}

	@ExcelField(title="工会会员数量", align=2, sort=22)
	public Integer getLaborUnionNum() {
		return laborUnionNum;
	}

	public void setLaborUnionNum(Integer laborUnionNum) {
		this.laborUnionNum = laborUnionNum;
	}

	@ExcelField(title="是否有共青团组织", align=2, sort=23, dictType="yes_no")
	@Length(min=0, max=1, message="是否有共青团组织长度必须介于 0 和 1 之间")
	public String getYouthLeagOrg() {
		return youthLeagOrg;
	}

	public void setYouthLeagOrg(String youthLeagOrg) {
		this.youthLeagOrg = youthLeagOrg;
	}

	@ExcelField(title="共青团团员数量", align=2, sort=24)
	public Integer getYouthLeagOrgNum() {
		return youthLeagOrgNum;
	}

	public void setYouthLeagOrgNum(Integer youthLeagOrgNum) {
		this.youthLeagOrgNum = youthLeagOrgNum;
	}

	@ExcelField(title="是否有妇联组织", align=2, sort=25, dictType="yes_no")
	@Length(min=0, max=1, message="是否有妇联组织长度必须介于 0 和 1 之间")
	public String getWomenOrg() {
		return womenOrg;
	}

	public void setWomenOrg(String womenOrg) {
		this.womenOrg = womenOrg;
	}

	@ExcelField(title="妇女数量", align=2, sort=26)
	public Integer getWomenNum() {
		return womenNum;
	}

	public void setWomenNum(Integer womenNum) {
		this.womenNum = womenNum;
	}

	@ExcelField(title="资金来源", align=2, sort=27)
	@Length(min=0, max=100, message="资金来源长度必须介于 0 和 100 之间")
	public String getCapiSour() {
		return capiSour;
	}

	public void setCapiSour(String capiSour) {
		this.capiSour = capiSour;
	}

	@ExcelField(title="是否有境外背景", align=2, sort=28, dictType="yes_no")
	@Length(min=0, max=1, message="是否有境外背景长度必须介于 0 和 1 之间")
	public String getOverBack() {
		return overBack;
	}

	public void setOverBack(String overBack) {
		this.overBack = overBack;
	}

	
	@Length(min=0, max=2000, message="区域图长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}

	@ExcelField(title="中心点", align=2, sort=29)
	@Length(min=0, max=40, message="中心点长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public Date getBeginApprDate() {
		return beginApprDate;
	}

	public void setBeginApprDate(Date beginApprDate) {
		this.beginApprDate = beginApprDate;
	}
	
	public Date getEndApprDate() {
		return endApprDate;
	}

	public void setEndApprDate(Date endApprDate) {
		this.endApprDate = endApprDate;
	}

	public String getConcExteLable() {
		return concExteLable;
	}

	public void setConcExteLable(String concExteLable) {
		this.concExteLable = concExteLable;
	}
		
}