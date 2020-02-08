package com.arjjs.ccm.modules.ccm.event.entity.preview;

/**
 * 查重返回对象
 * 
 * @author UserLi
 *
 */
public class CcmEventIncidentSimilarty {

	private CcmEventIncidentPreview eventA;
	private CcmEventIncidentPreview eventB;
	private String titleSim;
	private String ContentSim;

	public CcmEventIncidentSimilarty() {
		super();
	}

	public CcmEventIncidentSimilarty(CcmEventIncidentPreview eventA, CcmEventIncidentPreview eventB, String titleSim,
			String contentSim) {
		super();
		this.eventA = eventA;
		this.eventB = eventB;
		this.titleSim = titleSim;
		ContentSim = contentSim;
	}

	public CcmEventIncidentPreview getEventA() {
		return eventA;
	}

	public void setEventA(CcmEventIncidentPreview eventA) {
		this.eventA = eventA;
	}

	public CcmEventIncidentPreview getEventB() {
		return eventB;
	}

	public void setEventB(CcmEventIncidentPreview eventB) {
		this.eventB = eventB;
	}

	public String getTitleSim() {
		return titleSim;
	}

	public void setTitleSim(String titleSim) {
		this.titleSim = titleSim;
	}

	public String getContentSim() {
		return ContentSim;
	}

	public void setContentSim(String contentSim) {
		ContentSim = contentSim;
	}

}
