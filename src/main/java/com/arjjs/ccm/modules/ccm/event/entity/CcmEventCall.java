package com.arjjs.ccm.modules.ccm.event.entity;

import com.arjjs.ccm.common.persistence.DataEntity;

import java.util.List;

public class CcmEventCall extends DataEntity<CcmEventCall> {

    private static final long serialVersionUID = 1L;

    private List<CcmEventIncident> incidentList;

    private List<CcmEventCasedeal> casedealList;

    public List<CcmEventIncident> getIncidentList() {
        return incidentList;
    }

    public void setIncidentList(List<CcmEventIncident> incidentList) {
        this.incidentList = incidentList;
    }

    public List<CcmEventCasedeal> getCasedealList() {
        return casedealList;
    }

    public void setCasedealList(List<CcmEventCasedeal> casedealList) {
        this.casedealList = casedealList;
    }
}
