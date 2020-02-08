/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.face.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.iot.face.entity.CcmFaceControl;

/**
 * 人脸布控实体类DAO接口
 * @author lgh
 * @version 2019-06-05
 */
@MyBatisDao
public interface CcmFaceControlDao extends CrudDao<CcmFaceControl> {

    public void deleteByIdent(CcmFaceControl ccmFaceControl);
}