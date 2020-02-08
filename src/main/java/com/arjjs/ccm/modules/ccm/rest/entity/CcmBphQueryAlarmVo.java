package com.arjjs.ccm.modules.ccm.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "我的警情，警情列表入参实体")
public class CcmBphQueryAlarmVo extends CcmEntityUser implements Serializable {

    @ApiModelProperty(value = "日期条件：1:近一周，2：近一月，3近三月D")
    private String crite;
    @ApiModelProperty(value = "警情发生地")
    private String alarmSerach;
    @ApiModelProperty(value = "处警人ID")
    private String handlePoliceId;
    @ApiModelProperty(value = "报警时间1")
    private String alarmStartTime;
    @ApiModelProperty(value = "报警时间2")
    private String alarmEndTime;
    @ApiModelProperty(value = "ASC 降序 ；DESC升序(默认升序)")
    private String criteOrderBy;
    @ApiModelProperty(value = "部门ID")
    private String officeId;

    public String getCrite() {
        return crite;
    }

    public void setCrite(String crite) {
        this.crite = crite;
    }

    public String getAlarmSerach() {
        return alarmSerach;
    }

    public void setAlarmSerach(String alarmSerach) {
        this.alarmSerach = alarmSerach;
    }

    public String getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(String handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
    }

    public String getAlarmStartTime() {
        return alarmStartTime;
    }

    public void setAlarmStartTime(String alarmStartTime) {
        this.alarmStartTime = alarmStartTime;
    }

    public String getAlarmEndTime() {
        return alarmEndTime;
    }

    public void setAlarmEndTime(String alarmEndTime) {
        this.alarmEndTime = alarmEndTime;
    }

    public String getCriteOrderBy() {
        return criteOrderBy;
    }

    public void setCriteOrderBy(String criteOrderBy) {
        this.criteOrderBy = criteOrderBy;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }
}
