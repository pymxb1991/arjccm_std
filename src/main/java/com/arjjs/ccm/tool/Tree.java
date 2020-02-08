package com.arjjs.ccm.tool;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fay.tree.domain.Org;
import com.fay.tree.service.IFayTreeNode;
import com.fay.tree.util.FayTreeUtil;

/**
 * 树形json
 * 
 * @author pengjianqiang
 * @version 2018-03-02
 */
public class Tree implements IFayTreeNode {
	private String uuid;
	private String parentId;
	private String name;
	private String code;
	private String type;
	private boolean spread;

	public Tree(String uuid, String parentId, String name, String code, String type, boolean spread) {
		this.uuid = uuid;
		this.parentId = parentId;
		this.name = name;
		this.code = code;
		this.type = type;
		this.spread = spread;
	}

	@Override
	public String getNodeId() {
		return this.uuid;
	}

	@Override
	public String getNodeParentId() {
		return this.parentId;
	}

	@Override
	public String getNodeName() {
		return this.name;
	}

	@Override
	public String getNodeType() {
		return this.type;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}
	
	
	
	
	
	public static void main(String[] args) {
		Object data = FayTreeUtil.getTreeInJsonObject(genFayOrgList());
		System.out.println(JSONObject.toJSONString(data));
	}

	public static List<IFayTreeNode> genFayOrgList() {
		List<IFayTreeNode> list = new ArrayList<IFayTreeNode>();

		Org org = new Org("2", "1", "北京市", "110000", "2", true);
		list.add(org);
		org = new Org("3", "2", "市辖区", "110100", "3", true);
		list.add(org);
		org = new Org("4", "3", "东城区", "110101", "4", false);
		list.add(org);
		org = new Org("5", "3", "东城区", "110102", "4", false);
		list.add(org);
		org = new Org("6", "3", "东城区", "110105", "4", false);
		list.add(org);
		org = new Org("7", "3", "东城区", "110106", "4", false);
		list.add(org);
		org = new Org("8", "3", "东城区", "110107", "4", false);
		list.add(org);
		org = new Org("9", "3", "东城区", "110108", "4", false);
		list.add(org);
		org = new Org("10", "3", "东城区", "110109", "4", false);
		list.add(org);
		org = new Org("11", "3", "东城区", "110111", "4", false);
		list.add(org);
		org = new Org("12", "3", "东城区", "110112", "4", false);
		list.add(org);
		org = new Org("13", "3", "东城区", "110113", "4", false);
		list.add(org);
		org = new Org("14", "3", "东城区", "110114", "4", false);
		list.add(org);
		org = new Org("15", "3", "东城区", "110115", "4", false);
		list.add(org);
		org = new Org("16", "3", "东城区", "110116", "4", false);
		list.add(org);
		org = new Org("17", "3", "东城区", "110117", "4", false);
		list.add(org);
		org = new Org("18", "2", "县", "110200", "3", false);
		list.add(org);
		org = new Org("19", "18", "密云县", "110228", "4", false);
		list.add(org);
		org = new Org("20", "18", "延庆县", "110229", "4", false);
		list.add(org);
		return list;
	}
	
	
}
