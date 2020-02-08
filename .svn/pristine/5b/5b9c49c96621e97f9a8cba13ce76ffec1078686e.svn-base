package com.arjjs.ccm.modules.flat.handle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 警情列表实体类
 */
@ApiModel
public class AlarmHandleDayInfo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "处警人ID")
    private String handleId;
    @ApiModelProperty(value = "处警状态")
    private String handleStatus;
    @ApiModelProperty(value = "警情ID")
    private String alarmId;
    @ApiModelProperty(value = "案发地")
    private String alarmPlace;
    @ApiModelProperty(value = "警情内容")
    private String alarmContent;
    @ApiModelProperty(value = "是否重大 0:不是 1:是")
    private String alarmIsImportant;
    @ApiModelProperty(value = "警情类别Code码")
    private String alarmGenreCode;
    @ApiModelProperty(value = "警情类别名称")
    private String alarmGenerName;
    @ApiModelProperty(value = "最大签收时间")
    private String maxDispatchTime;
    @ApiModelProperty(value = "最大到达时间")
    private String maxArriveTime;
    @ApiModelProperty(value = "出警安排")
    private String task;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报警时间")
    private Date   alarmTime;

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmPlace() {
        return alarmPlace;
    }

    public void setAlarmPlace(String alarmPlace) {
        this.alarmPlace = alarmPlace;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getAlarmIsImportant() {
        return alarmIsImportant;
    }

    public void setAlarmIsImportant(String alarmIsImportant) {
        this.alarmIsImportant = alarmIsImportant;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getAlarmGenreCode() {
        return alarmGenreCode;
    }

    public void setAlarmGenreCode(String alarmGenreCode) {
        this.alarmGenreCode = alarmGenreCode;
    }

    public String getAlarmGenerName() {
        return alarmGenerName;
    }

    public void setAlarmGenerName(String alarmGenerName) {
        this.alarmGenerName = alarmGenerName;
    }

    public String getMaxDispatchTime() {
        return maxDispatchTime;
    }

    public void setMaxDispatchTime(String maxDispatchTime) {
        this.maxDispatchTime = maxDispatchTime;
    }

    public String getMaxArriveTime() {
        return maxArriveTime;
    }

    public void setMaxArriveTime(String maxArriveTime) {
        this.maxArriveTime = maxArriveTime;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "AlarmHandleDayInfo{" +
                "handleId='" + handleId + '\'' +
                ", handleStatus='" + handleStatus + '\'' +
                ", alarmId='" + alarmId + '\'' +
                ", alarmPlace='" + alarmPlace + '\'' +
                ", alarmContent='" + alarmContent + '\'' +
                ", alarmIsImportant='" + alarmIsImportant + '\'' +
                ", alarmGenreCode='" + alarmGenreCode + '\'' +
                ", alarmGenerName='" + alarmGenerName + '\'' +
                ", maxDispatchTime='" + maxDispatchTime + '\'' +
                ", maxArriveTime='" + maxArriveTime + '\'' +
                ", task='" + task + '\'' +
                ", alarmTime=" + alarmTime +
                '}';
    }
}
