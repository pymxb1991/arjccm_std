/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.relief.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Area;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 备勤任务实体类Entity
 *
 * @author lgh
 * @version 2019-07-25
 */
public class CcmReliefTask extends DataEntity<CcmReliefTask> {

    private static final long serialVersionUID = 1L;
    private String taskName;        // 任务名称
    private String reliefLevel;        // 备勤等级
    private String reliefType;        // 备勤类别
    private Date startTime;        // 开始时间
    private String massAddress;        // 集结地点
    private Date endTime;        // 结束时间
    private String reliefDept;        // 备勤参与部门
    private String reliefDeptName;        // 备勤参与部门名称
    private String reliefNumber;        // 每个单位人数
    private String reviewStatus;        // 审核状态
    private String roadLine;        // 备勤路线
    private String auditingStatus; //审核状态
    private Area area;        // 备勤辖区

    public CcmReliefTask() {
        super();
    }

    public CcmReliefTask(String id) {
        super(id);
    }

    public String getReliefDeptName() {
        return reliefDeptName;
    }

    public void setReliefDeptName(String reliefDeptName) {
        this.reliefDeptName = reliefDeptName;
    }

    @Length(min = 1, max = 64, message = "任务名称长度必须介于 1 和 64 之间")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Length(min = 1, max = 2, message = "备勤等级长度必须介于 1 和 2 之间")
    public String getReliefLevel() {
        return reliefLevel;
    }

    public void setReliefLevel(String reliefLevel) {
        this.reliefLevel = reliefLevel;
    }

    @Length(min = 1, max = 2, message = "备勤类别长度必须介于 1 和 2 之间")
    public String getReliefType() {
        return reliefType;
    }

    public void setReliefType(String reliefType) {
        this.reliefType = reliefType;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Length(min = 0, max = 64, message = "集结地点长度必须介于 0 和 64 之间")
    public String getMassAddress() {
        return massAddress;
    }

    public void setMassAddress(String massAddress) {
        this.massAddress = massAddress;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Length(min = 0, max = 255, message = "备勤参与部门长度必须介于 0 和 255 之间")
    public String getReliefDept() {
        return reliefDept;
    }

    public void setReliefDept(String reliefDept) {
        this.reliefDept = reliefDept;
    }

    @Length(min = 0, max = 255, message = "每个单位人数长度必须介于 0 和 255 之间")
    public String getReliefNumber() {
        return reliefNumber;
    }

    public void setReliefNumber(String reliefNumber) {
        this.reliefNumber = reliefNumber;
    }

    @Length(min = 0, max = 3, message = "审核状态长度必须介于 0 和 3 之间")
    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Length(min = 0, max = 255, message = "备勤路线长度必须介于 0 和 255 之间")
    public String getRoadLine() {
        return roadLine;
    }

    public void setRoadLine(String roadLine) {
        this.roadLine = roadLine;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Length(min = 0, max = 3, message = "审核状态长度必须介于 0 和 3 之间")
    public String getAuditingStatus() {
        return auditingStatus;
    }

    public void setAuditingStatus(String auditingStatus) {
        this.auditingStatus = auditingStatus;
    }

    @Override
    public String toString() {
        return "CcmReliefTask{" +
                "taskName='" + taskName + '\'' +
                ", reliefLevel='" + reliefLevel + '\'' +
                ", reliefType='" + reliefType + '\'' +
                ", startTime=" + startTime +
                ", massAddress='" + massAddress + '\'' +
                ", endTime=" + endTime +
                ", reliefDept='" + reliefDept + '\'' +
                ", reliefNumber='" + reliefNumber + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", roadLine='" + roadLine + '\'' +
                ", auditingStatus='" + auditingStatus + '\'' +
                ", area=" + area +
                '}';
    }
}