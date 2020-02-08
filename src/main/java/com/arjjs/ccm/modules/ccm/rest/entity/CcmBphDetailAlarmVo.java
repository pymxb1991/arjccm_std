package com.arjjs.ccm.modules.ccm.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "处警详情入参")
public class CcmBphDetailAlarmVo extends CcmEntityUser{

    @ApiModelProperty(value = "处警人ID")
    private String handlePoliceId;

    @ApiModelProperty(value = "警情ID")
    private String alarmId;

    @ApiModelProperty(value = "处警ID（任务ID）")
    private String handleId;

    public String getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(String handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }
}
