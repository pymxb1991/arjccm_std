package com.arjjs.ccm.modules.ccm.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "文件上传参数实体")
public class CcmBphFileUploadVo extends CcmEntityUser{

    @ApiModelProperty(value = "处警ID（任务ID）")
    private  String alarmHandleId;
    @ApiModelProperty(value = "文件类型：0图片；3音频；4视频")
    private  String type;

    public String getAlarmHandleId() {
        return alarmHandleId;
    }

    public void setAlarmHandleId(String alarmHandleId) {
        this.alarmHandleId = alarmHandleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
