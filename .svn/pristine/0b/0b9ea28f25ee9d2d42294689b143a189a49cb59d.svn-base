package com.arjjs.ccm.modules.ccm.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
@ApiModel(value = "更新处警信息实体类")
public class CcmBphUpdateAlarmVo extends CcmEntityUser{

    @NotBlank(message = "处警ID不能为空")
    @ApiModelProperty(value = "处警ID(任务ID)")
    private String id;
    @NotBlank(message = "警情ID不能为空")
    @ApiModelProperty(value = "警情ID")
    private String alarmId;
    @ApiModelProperty(value = "反馈信息")
    private String handleResult;
    @ApiModelProperty(value = "案件类别")
    private String alarmGenerCode;
    @ApiModelProperty(value = "案件类型")
    private String alarmTypeCode;
    @ApiModelProperty(value = "案件细类")
    private String alarmSmallTypeCode;
    @NotBlank(message = "处置状态不能为空")
    @ApiModelProperty(value = "处置状态 : 0:未处理、1:已签收、2:已到达、3:已反馈")
    private String status;
    @ApiModelProperty(value = "接单时经度")
    private String receiveX;
    @ApiModelProperty(value = "接单时纬度")
    private String receiveY;
    @ApiModelProperty(value = "到达现场时经度")
    private String arriveX;
    @ApiModelProperty(value = "到达现场时纬度")
    private String arriveY;
    @ApiModelProperty(value = "处警完成时经度")
    private String finishX;
    @ApiModelProperty(value = "处警完成时纬度")
    private String finishY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getAlarmGenerCode() {
        return alarmGenerCode;
    }

    public void setAlarmGenerCode(String alarmGenerCode) {
        this.alarmGenerCode = alarmGenerCode;
    }

    public String getAlarmTypeCode() {
        return alarmTypeCode;
    }

    public void setAlarmTypeCode(String alarmTypeCode) {
        this.alarmTypeCode = alarmTypeCode;
    }

    public String getAlarmSmallTypeCode() {
        return alarmSmallTypeCode;
    }

    public void setAlarmSmallTypeCode(String alarmSmallTypeCode) {
        this.alarmSmallTypeCode = alarmSmallTypeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiveX() {
        return receiveX;
    }

    public void setReceiveX(String receiveX) {
        this.receiveX = receiveX;
    }

    public String getReceiveY() {
        return receiveY;
    }

    public void setReceiveY(String receiveY) {
        this.receiveY = receiveY;
    }

    public String getArriveX() {
        return arriveX;
    }

    public void setArriveX(String arriveX) {
        this.arriveX = arriveX;
    }

    public String getArriveY() {
        return arriveY;
    }

    public void setArriveY(String arriveY) {
        this.arriveY = arriveY;
    }

    public String getFinishX() {
        return finishX;
    }

    public void setFinishX(String finishX) {
        this.finishX = finishX;
    }

    public String getFinishY() {
        return finishY;
    }

    public void setFinishY(String finishY) {
        this.finishY = finishY;
    }
}
