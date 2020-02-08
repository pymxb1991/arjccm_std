
package com.arjjs.ccm.modules.ccm.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class CcmHouseBuildentranceVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "/单元id")
    private String id;//单元id

    @ApiModelProperty(value = "/单元编号【在楼宇/小区中的编号】")
    private String entranceNum;//单元编号【在楼宇/小区中的编号】

    @ApiModelProperty(value = "单元名称")
    private String entranceName;//单元名称

    @ApiModelProperty(value = "楼层数量")
    private Integer floorNum;//楼层数量

    @ApiModelProperty(value = "地下楼层数")
    private Integer underfloorNum;//地下楼层数

    @ApiModelProperty(value = "房屋数量")
    private Integer houseNum;//房屋数量

    @ApiModelProperty(value = "/居民数量")
    private Integer residentNum;//居民数量

    @ApiModelProperty(value = "单元照片的url")
    private String imageurl;//单元照片的url.

    protected String remarks;
    protected Date createDate;
    protected Date updateDate;
    protected String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntranceNum() {
        return entranceNum;
    }
    public void setEntranceNum(String entranceNum) {
        this.entranceNum = entranceNum;
    }
    public String getEntranceName() {
        return entranceName;
    }
    public void setEntranceName(String entranceName) {
        this.entranceName = entranceName;
    }
    public Integer getFloorNum() {
        return floorNum;
    }
    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }
    public Integer getUnderfloorNum() {
        return underfloorNum;
    }
    public void setUnderfloorNum(Integer underfloorNum) {
        this.underfloorNum = underfloorNum;
    }
    public Integer getHouseNum() {
        return houseNum;
    }
    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }
    public Integer getResidentNum() {
        return residentNum;
    }
    public void setResidentNum(Integer residentNum) {
        this.residentNum = residentNum;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}