package com.arjjs.ccm.modules.ccm.rest.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class CcmWorkNotice implements Serializable { //// NOTE:求助表 :ccm_work_notice

    private static final long serialVersionUID = 1L;

    private String id;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date datas;		// 时间
    private String title;		// 标题
    private String content;		// 内容
/*    private Date beginDatas;		// 开始 时间
    private Date endDatas;		// 结束 时间*/

    private String createBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String updateBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date   updateDate;

    private String remarks;

    private String delFlag;

    private String status;

    private String fileUrl ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatas() {
        return datas;
    }

    public void setDatas(Date datas) {
        this.datas = datas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
