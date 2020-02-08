/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.action.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.flat.action.dao.BphActionInfoDao;
import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;
import com.arjjs.ccm.modules.flat.actionuser.entity.BphActionUser;
import com.arjjs.ccm.modules.flat.actionuser.service.BphActionUserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 执行动作配置Service
 * 
 * @author liu
 * @version 2018-11-14
 */
@Service
@Transactional(readOnly = true)
public class BphActionInfoService extends CrudService<BphActionInfoDao, BphActionInfo> {

	@Autowired
	private BphActionUserService bphActionUserService;

	public BphActionInfo get(String id) {
		return super.get(id);
	}
	
	public List<BphActionInfo> planActionTree(BphActionInfo bphActionInfo) {
		return dao.planActionTree(bphActionInfo);
	}

	public List<BphActionInfo> findList(BphActionInfo bphActionInfo) {
		return super.findList(bphActionInfo);
	}

	public Page<BphActionInfo> findPage(Page<BphActionInfo> page, BphActionInfo bphActionInfo) {
		return super.findPage(page, bphActionInfo);
	}

	public List<BphActionInfo> findAllListBphActionInfo(BphActionInfo bphActionInfo){
		return dao.findAllListBphActionInfo(bphActionInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BphActionInfo bphActionInfo) {
		super.save(bphActionInfo);
	}

	@Transactional(readOnly = false)
	public void saveActionInfo(BphActionInfo bphActionInfo) {
		dao.insert(bphActionInfo);
	}

	@Transactional(readOnly = false)
	public void delete(BphActionInfo bphActionInfo) {
		super.delete(bphActionInfo);
	}

	@Transactional(readOnly = false)
	public void updateActionInfo(BphActionInfo bphActionInfo) {
		dao.update(bphActionInfo);
	}

	@Transactional(readOnly = false)
	public void saveData(String param) throws IOException {
		param = java.net.URLDecoder.decode(param, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(param);
		BphActionInfo bphActionInfo = new BphActionInfo();
		String id = "";
		if (null != jsonObject) {
			if (jsonObject.containsKey("id")) {
				id = jsonObject.getString("id");
				if (StringUtils.isBlank(id)) {
					// UserUtils.getUser().getId();
					String uuid = UUID.randomUUID().toString();
					bphActionInfo.setId(uuid);
				}else {
					bphActionInfo.setId(id);
				}
			}
			if (jsonObject.containsKey("content")) {
				String content = jsonObject.getString("content");
				bphActionInfo.setContent(content);
			}
			if (jsonObject.containsKey("name")) {
				String name = jsonObject.getString("name");
				bphActionInfo.setName(name);
			}
			if (jsonObject.containsKey("remarks")) {
				String remarks = jsonObject.getString("remarks");
				bphActionInfo.setRemarks(remarks);
			}
			if (jsonObject.containsKey("title")) {
				String title = jsonObject.getString("title");
				bphActionInfo.setTitle(title);
			}
			if (jsonObject.containsKey("type")) {
				String type = jsonObject.getString("type");
				bphActionInfo.setType(type);
			}
			if (jsonObject.containsKey("users")) {
				JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("users"));
				BphActionUser bphActionUser = new BphActionUser();
				if (StringUtils.isBlank(id)) {
					bphActionUser.setActionId(bphActionInfo.getId());
				} else {
					bphActionUser.setActionId(id);
				}
				bphActionUserService.deleteBphActionUser(bphActionUser);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonUsers = JSONObject.fromObject(jsonArray.get(i));
					BphActionUser bphActionUser1 = new BphActionUser();
					bphActionUser1.setActionId(bphActionUser.getActionId());
					if (jsonUsers.containsKey("userId")) {
						String datasUser = jsonUsers.getString("userId").substring(2);
						bphActionUser1.setUserId(datasUser);
					}
					// 保存默认推送的用户
					bphActionUserService.saveBphActionUser(bphActionUser1);
				}
			}
			if (StringUtils.isBlank(id)) {
				saveActionInfo(bphActionInfo);
			} else {
				updateActionInfo(bphActionInfo);
			}
		}
	}

}