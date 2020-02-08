package com.arjjs.ccm.modules.ccm.view.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.supcan.annotation.treelist.cols.SupCol;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.common.utils.excel.fieldtype.RoleListType;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserVCcmTeam extends DataEntity<com.arjjs.ccm.modules.sys.entity.User> {
    private static final long serialVersionUID = 1L;
    private Office company;
    private Office office;
    private String loginName;
    private String password;
    private String no;
    private String name;
    private String email;
    private String phone;
    private String mobile;
    private String userType;
    private String loginIp;
    private Date loginDate;
    private String loginFlag;
    private String photo;
    private String oldLoginName;
    private String newPassword;
    private String oldLoginIp;
    private Date oldLoginDate;
    private Role role;
    private List<Role> roleList = Lists.newArrayList();
    private String hasPermission;
    private String permissionKey;
//    private VCcmTeam vCcmTeam;

    private String teamType;		// 人员类型
    private String sex;		// 性别
    private String grade;		// 级别
    private String nation;		// 民族
    private String service;		// 职务
    private String politics;		// 政治面貌
    private Date birthday;		// 出生日期
    private String idenNum;		// 公民身份号码
    private String education;		// 学历
    private String fixTel;		// 其他联系方式

    public String getHasPermission() {
        return this.hasPermission;
    }

    public void setHasPermission(String hasPermission) {
        this.hasPermission = hasPermission;
    }

    public String getPermissionKey() {
        return this.permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public UserVCcmTeam() {
        this.loginFlag = "1";
    }

    public UserVCcmTeam(String id) {
        super(id);
    }

    public UserVCcmTeam(String id, String loginName) {
        super(id);
        this.loginName = loginName;
    }

    public UserVCcmTeam(Role role) {
        this.role = role;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginFlag() {
        return this.loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    @SupCol(
            isUnique = "true",
            isHide = "true"
    )
    @ExcelField(
            title = "ID",
            type = 1,
            align = 2,
            sort = 1
    )
    public String getId() {
        return this.id;
    }

    @JsonIgnore
    @NotNull(
            message = "机构名称不能为空"
    )
    @ExcelField(
            title = "机构名称",
            align = 2,
            sort = 5
    )
    public Office getCompany() {
        return this.company;
    }

    public void setCompany(Office company) {
        this.company = company;
    }

    @JsonIgnore
    @NotNull(
            message = "部门名称不能为空"
    )
    @ExcelField(
            title = "部门名称",
            align = 2,
            sort = 10
    )
    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Length(
            min = 1,
            max = 100,
            message = "登录名长度必须介于 1 和 100 之间"
    )
    @ExcelField(
            title = "登录名",
            align = 2,
            sort = 15
    )
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @JsonIgnore
    @Length(
            min = 1,
            max = 100,
            message = "密码长度必须介于 1 和 100 之间"
    )
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(
            min = 1,
            max = 100,
            message = "姓名长度必须介于 1 和 100 之间"
    )
    @ExcelField(
            title = "姓名",
            align = 2,
            sort = 20
    )
    public String getName() {
        return this.name;
    }

    @Length(
            min = 1,
            max = 100,
            message = "工号长度必须介于 1 和 100 之间"
    )
    @ExcelField(
            title = "工号",
            align = 2,
            sort = 25
    )
    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Email(
            message = "邮箱格式不正确"
    )
    @Length(
            min = 0,
            max = 200,
            message = "邮箱长度必须介于 1 和 200 之间"
    )
    @ExcelField(
            title = "邮箱",
            align = 1,
            sort = 30
    )
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(
            min = 0,
            max = 200,
            message = "电话长度必须介于 1 和 200 之间"
    )
    @ExcelField(
            title = "电话",
            align = 2,
            sort = 35
    )
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(
            min = 0,
            max = 200,
            message = "手机长度必须介于 1 和 200 之间"
    )
    @ExcelField(
            title = "手机",
            align = 2,
            sort = 40
    )
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ExcelField(
            title = "备注",
            align = 1,
            sort = 60
    )
    public String getRemarks() {
        return this.remarks;
    }

    @Length(
            min = 0,
            max = 100,
            message = "用户类型长度必须介于 1 和 100 之间"
    )
    @ExcelField(
            title = "用户类型",
            align = 2,
            sort = 45,
            dictType = "sys_user_type"
    )
    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ExcelField(
            title = "创建时间",
            align = 2,
            sort = 50
    )
    public Date getCreateDate() {
        return this.createDate;
    }

    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public Date getLoginDate() {
        return this.loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getOldLoginName() {
        return this.oldLoginName;
    }

    public void setOldLoginName(String oldLoginName) {
        this.oldLoginName = oldLoginName;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldLoginIp() {
        return this.oldLoginIp == null ? this.loginIp : this.oldLoginIp;
    }

    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public Date getOldLoginDate() {
        return this.oldLoginDate == null ? this.loginDate : this.oldLoginDate;
    }

    public void setOldLoginDate(Date oldLoginDate) {
        this.oldLoginDate = oldLoginDate;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonIgnore
    @ExcelField(
            title = "拥有角色",
            align = 1,
            sort = 55,
            fieldType = RoleListType.class
    )
    public List<Role> getRoleList() {
        return this.roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @JsonIgnore
    public List<String> getRoleIdList() {
        List<String> roleIdList = Lists.newArrayList();
        Iterator var3 = this.roleList.iterator();

        while(var3.hasNext()) {
            Role role = (Role)var3.next();
            roleIdList.add(role.getId());
        }

        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleList = Lists.newArrayList();
        Iterator var3 = roleIdList.iterator();

        while(var3.hasNext()) {
            String roleId = (String)var3.next();
            Role role = new Role();
            role.setId(roleId);
            this.roleList.add(role);
        }

    }

    public String getRoleNames() {
        return Collections3.extractToString(this.roleList, "name", ",");
    }

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(String id) {
        return id != null && "1".equals(id);
    }

    public String toString() {
        return this.id;
    }

//    public VCcmTeam getvCcmTeam() {
//        return vCcmTeam;
//    }
//
//    public void setvCcmTeam(VCcmTeam vCcmTeam) {
//        this.vCcmTeam = vCcmTeam;
//    }
    @ExcelField(
            title = "人员类型",
            align = 2,
            sort = 65,
            dictType = "ccm_org_team_type"
    )
    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }
    @ExcelField(
            title = "性别",
            align = 2,
            sort = 70,
            dictType = "sex"
    )
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ExcelField(
            title = "级别",
            align = 2,
            sort = 75,
            dictType = "ccm_sta_lev"
    )
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @ExcelField(
            title = "民族",
            align = 2,
            sort = 80,
            dictType = "sys_volk"
    )
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @ExcelField(
            title = "职务",
            align = 2,
            sort = 85,
            dictType = "ccm_sta_dut"
    )
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @ExcelField(
            title = "政治面貌",
            align = 2,
            sort = 90,
            dictType = "sys_ccm_poli_stat"
    )
    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ExcelField(
            title = "出生日期",
            align = 2,
            sort = 95
    )
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ExcelField(
            title = "公民身份证号码",
            align = 2,
            sort = 100
    )
    public String getIdenNum() {
        return idenNum;
    }

    public void setIdenNum(String idenNum) {
        this.idenNum = idenNum;
    }

    @ExcelField(
            title = "学历",
            align = 2,
            sort = 105,
            dictType = "sys_ccm_degree"
    )
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @ExcelField(
            title = "其他联系方式",
            align = 2,
            sort = 110
    )
    public String getFixTel() {
        return fixTel;
    }

    public void setFixTel(String fixTel) {
        this.fixTel = fixTel;
    }
}
