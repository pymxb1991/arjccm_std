package com.arjjs.ccm.modules.ccm.sys.entity;

import java.util.List;

public class CcmMapDictAreaVo {

    private String dictValue;
    private String dictLabel;

    private List<CityComponents> cityList;

    public List<CityComponents> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityComponents> cityList) {
        this.cityList = cityList;
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
