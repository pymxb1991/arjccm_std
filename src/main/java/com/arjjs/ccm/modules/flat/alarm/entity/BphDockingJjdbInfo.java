package com.arjjs.ccm.modules.flat.alarm.entity;


import java.io.Serializable;
import java.util.Date;

public class BphDockingJjdbInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 612581643615873677L;
	private String  jjdbh;         //接警单编号
	private String  jjygh;			//接警员工号
	private String  jjyxm;			//接警员姓名
	private String  bjrxm;			//报警人姓名
	private Long  bjrxb;			//报警人性别
	private String  lxdh;			//报警人联系电话
	private String  afdz;			//案发地址
	private String  xzb;				//X坐标
	private String  yzb;				//Y坐标
	private String  hbgd;			//海拔高度
	private String  bjnr;			//报警内容
	private String  jqlbdm;			//警情类别代码
	private String  jqlxdm;			//警情类型代码
	private String  jqxldm;			//警情细类代码
	private String  bjfsdm;			//报警方式代码
	private Date  bjsj;			//报警时间
	private String  jjlyh;			//接警录音号
	private String  jqjbdm;			//警情级别代码
	private String  jjlxdm;			//接警类型代码
	private String  cllxdm;			//接警单处理类型代码
	private String  jjdwdm;			//接警单位代码
	private Date  sjgxsj;			//数据更新时间.
	private Long  jjdzt;			//接警单状态
	private String  gxdwdm;			//管辖单位代码
	private String areaId;
	private String officeId;
	private String officeIds;
	public BphDockingJjdbInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BphDockingJjdbInfo(String jjdbh, String jjygh, String jjyxm, String bjrxm, Long bjrxb, String lxdh,
			String afdz, String xzb, String yzb, String hbgd, String bjnr, String jqlbdm, String jqlxdm, String jqxldm,
			String bjfsdm, Date bjsj, String jjlyh, String jqjbdm, String jjlxdm, String cllxdm, String jjdwdm,
			Date sjgxsj, Long jjdzt, String gxdwdm, String areaId, String officeId, String officeIds) {
		super();
		this.jjdbh = jjdbh;
		this.jjygh = jjygh;
		this.jjyxm = jjyxm;
		this.bjrxm = bjrxm;
		this.bjrxb = bjrxb;
		this.lxdh = lxdh;
		this.afdz = afdz;
		this.xzb = xzb;
		this.yzb = yzb;
		this.hbgd = hbgd;
		this.bjnr = bjnr;
		this.jqlbdm = jqlbdm;
		this.jqlxdm = jqlxdm;
		this.jqxldm = jqxldm;
		this.bjfsdm = bjfsdm;
		this.bjsj = bjsj;
		this.jjlyh = jjlyh;
		this.jqjbdm = jqjbdm;
		this.jjlxdm = jjlxdm;
		this.cllxdm = cllxdm;
		this.jjdwdm = jjdwdm;
		this.sjgxsj = sjgxsj;
		this.jjdzt = jjdzt;
		this.gxdwdm = gxdwdm;
		this.areaId = areaId;
		this.officeId = officeId;
		this.officeIds = officeIds;
	}
	
	public String getJjdbh() {
		return jjdbh;
	}
	public void setJjdbh(String jjdbh) {
		this.jjdbh = jjdbh;
	}
	public String getJjygh() {
		return jjygh;
	}
	public void setJjygh(String jjygh) {
		this.jjygh = jjygh;
	}
	public String getJjyxm() {
		return jjyxm;
	}
	public void setJjyxm(String jjyxm) {
		this.jjyxm = jjyxm;
	}
	public String getBjrxm() {
		return bjrxm;
	}
	public void setBjrxm(String bjrxm) {
		this.bjrxm = bjrxm;
	}
	public Long getBjrxb() {
		return bjrxb;
	}
	public void setBjrxb(Long bjrxb) {
		this.bjrxb = bjrxb;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getAfdz() {
		return afdz;
	}
	public void setAfdz(String afdz) {
		this.afdz = afdz;
	}
	public String getXzb() {
		return xzb;
	}
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}
	public String getYzb() {
		return yzb;
	}
	public void setYzb(String yzb) {
		this.yzb = yzb;
	}
	public String getHbgd() {
		return hbgd;
	}
	public void setHbgd(String hbgd) {
		this.hbgd = hbgd;
	}
	public String getBjnr() {
		return bjnr;
	}
	public void setBjnr(String bjnr) {
		this.bjnr = bjnr;
	}
	public String getJqlbdm() {
		return jqlbdm;
	}
	public void setJqlbdm(String jqlbdm) {
		this.jqlbdm = jqlbdm;
	}
	public String getJqlxdm() {
		return jqlxdm;
	}
	public void setJqlxdm(String jqlxdm) {
		this.jqlxdm = jqlxdm;
	}
	public String getJqxldm() {
		return jqxldm;
	}
	public void setJqxldm(String jqxldm) {
		this.jqxldm = jqxldm;
	}
	public String getBjfsdm() {
		return bjfsdm;
	}
	public void setBjfsdm(String bjfsdm) {
		this.bjfsdm = bjfsdm;
	}
	public Date getBjsj() {
		return bjsj;
	}
	public void setBjsj(Date bjsj) {
		this.bjsj = bjsj;
	}
	public String getJjlyh() {
		return jjlyh;
	}
	public void setJjlyh(String jjlyh) {
		this.jjlyh = jjlyh;
	}
	public String getJqjbdm() {
		return jqjbdm;
	}
	public void setJqjbdm(String jqjbdm) {
		this.jqjbdm = jqjbdm;
	}
	public String getJjlxdm() {
		return jjlxdm;
	}
	public void setJjlxdm(String jjlxdm) {
		this.jjlxdm = jjlxdm;
	}
	public String getCllxdm() {
		return cllxdm;
	}
	public void setCllxdm(String cllxdm) {
		this.cllxdm = cllxdm;
	}
	public String getJjdwdm() {
		return jjdwdm;
	}
	public void setJjdwdm(String jjdwdm) {
		this.jjdwdm = jjdwdm;
	}
	public Date getSjgxsj() {
		return sjgxsj;
	}
	public void setSjgxsj(Date sjgxsj) {
		this.sjgxsj = sjgxsj;
	}
	public Long getJjdzt() {
		return jjdzt;
	}
	public void setJjdzt(Long jjdzt) {
		this.jjdzt = jjdzt;
	}
	public String getGxdwdm() {
		return gxdwdm;
	}
	public void setGxdwdm(String gxdwdm) {
		this.gxdwdm = gxdwdm;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeIds() {
		return officeIds;
	}
	public void setOfficeIds(String officeIds) {
		this.officeIds = officeIds;
	}
	@Override
	public String toString() {
		return "BphDockingJjdbInfo [jjdbh=" + jjdbh + ", jjygh=" + jjygh + ", jjyxm=" + jjyxm + ", bjrxm=" + bjrxm
				+ ", bjrxb=" + bjrxb + ", lxdh=" + lxdh + ", afdz=" + afdz + ", xzb=" + xzb + ", yzb=" + yzb + ", hbgd="
				+ hbgd + ", bjnr=" + bjnr + ", jqlbdm=" + jqlbdm + ", jqlxdm=" + jqlxdm + ", jqxldm=" + jqxldm
				+ ", bjfsdm=" + bjfsdm + ", bjsj=" + bjsj + ", jjlyh=" + jjlyh + ", jqjbdm=" + jqjbdm + ", jjlxdm="
				+ jjlxdm + ", cllxdm=" + cllxdm + ", jjdwdm=" + jjdwdm + ", sjgxsj=" + sjgxsj + ", jjdzt=" + jjdzt
				+ ", gxdwdm=" + gxdwdm + ", areaId=" + areaId + ", officeId=" + officeId + ", officeIds=" + officeIds
				+ "]";
	}
	

}
