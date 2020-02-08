package com.arjjs.ccm.modules.ccm.rest.entity;

public class CcmRestProposal {
	private String proposalId;
	private String proposalTitle;
	private String proposalContent;
	private String userId;
	private String userName;
	private String userPhoto;
	public String getProposalId() {
		return proposalId;
	}
	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}
	public String getProposalTitle() {
		return proposalTitle;
	}
	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}
	public String getProposalContent() {
		return proposalContent;
	}
	public void setProposalContent(String proposalContent) {
		this.proposalContent = proposalContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
}
