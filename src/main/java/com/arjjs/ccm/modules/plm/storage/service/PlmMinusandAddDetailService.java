/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.storage.dao.PlmMinusandAddDetailDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmMinusandAddDetail;
import com.arjjs.ccm.modules.plm.storage.util.QREncodeUtils;
import com.google.zxing.common.BitMatrix;

/**
 * 出库、入库进出明细Service
 * @author dongqikai
 * @version 2018-06-30
 */
@Service
@Transactional(readOnly = true)
public class PlmMinusandAddDetailService extends CrudService<PlmMinusandAddDetailDao, PlmMinusandAddDetail> {
	
	@Autowired
	private PlmMinusandAddDetailDao plmMinusandAddDetailDao;

	public PlmMinusandAddDetail get(String id) {
		return super.get(id);
	}
	
	public List<PlmMinusandAddDetail> findList(PlmMinusandAddDetail plmMinusandAddDetail) {
		return super.findList(plmMinusandAddDetail);
	}
	
	public Page<PlmMinusandAddDetail> findPage(Page<PlmMinusandAddDetail> page, PlmMinusandAddDetail plmMinusandAddDetail) {
		return super.findPage(page, plmMinusandAddDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmMinusandAddDetail plmMinusandAddDetail) {
		super.save(plmMinusandAddDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmMinusandAddDetail plmMinusandAddDetail) {
		super.delete(plmMinusandAddDetail);
	}
	
	/**
	 * 修改耗材数量
	 * @param plmMinusandAddDetail
	 */
	@Transactional(readOnly = false)
	public void updateNum(PlmMinusandAddDetail plmMinusandAddDetail) {
		PlmMinusandAddDetail detail = super.get(plmMinusandAddDetail.getId());
		detail.setErialNumber(plmMinusandAddDetail.getErialNumber());
		plmMinusandAddDetailDao.update(detail);
	}
	
	/**
	 * 修改入库状态
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateFlag(String id) {
		return plmMinusandAddDetailDao.updateFlag(id);
	}
	
	/**
	 * 生成base64编码的二维码
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public String qrCodeWithBase64(String code) throws IOException {
		BitMatrix bm = QREncodeUtils.createBitMatrixWithQR(code, 70);
		return QREncodeUtils.writeToBase64(bm, "jpg");
	}
	
	
	/**
	 * 借用超期装备 
	 * @return
	 */
	public List<Map<String, Object>> findDeadLineCount(){
		
		return plmMinusandAddDetailDao.findDeadLineCount();
	};
	
	
	/**
	 * 检查是否关联物资
	 * @param pId
	 * @return
	 */
	public boolean checkIsHasDetail(String pId) {
		boolean isHasDetail = false;
		int counts = plmMinusandAddDetailDao.countDetails(pId);
		if (counts > 0) {
			isHasDetail = true;
		}
		return isHasDetail;
	}
	public List<PlmMinusandAddDetail> findIndexList( PlmMinusandAddDetail plmMinusandAddDetail){
		return dao.findIndexList(plmMinusandAddDetail);
	}
	public List<PlmMinusandAddDetail> findGiveBack(PlmMinusandAddDetail plmMinusandAddDetail){
		return dao.findGiveBack(plmMinusandAddDetail);
	}
	@Transactional(readOnly = false)
	public int updateId(String id) {
		return plmMinusandAddDetailDao.updateId(id);
	}
	
	/**
	 * 修改出库单下的出库物资的使用有效期
	 * @param id 出库单id
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateDeadlineDate(String id, String deadlineDate) {
		return dao.updateDeadlineDate(id, deadlineDate);
	}
}