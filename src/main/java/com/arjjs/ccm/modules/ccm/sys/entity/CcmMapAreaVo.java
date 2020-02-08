package com.arjjs.ccm.modules.ccm.sys.entity;

public class CcmMapAreaVo {

    private String dictValue;
    private String dictLabel;
    private String id;
    private String name;
    private String areaPoint;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaPoint() {
        return areaPoint;
    }

    public void setAreaPoint(String areaPoint) {
        this.areaPoint = areaPoint;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }
}
