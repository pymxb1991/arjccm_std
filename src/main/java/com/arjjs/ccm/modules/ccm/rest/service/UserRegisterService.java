package com.arjjs.ccm.modules.ccm.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.modules.ccm.rest.dao.UserRegisterDao;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 用户注册Service
 * 
 * @author zjb
 * @version 2018-9-11
 */
@Service
@Transactional(readOnly = true)
public class UserRegisterService {
	
	@Autowired
	private UserRegisterDao userRegisterDao;
	
	//插入新用户==》注册用户
	@Transactional(readOnly = false)
	public void insertUser(User user) {
		userRegisterDao.insertUser(user);
	}
}
