/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.allot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.allot.dao.PlmAllotDetailDao;
import com.arjjs.ccm.modules.plm.allot.entity.PlmAllotDetail;
import com.arjjs.ccm.modules.plm.storage.service.PlmMinusandAddDetailService;

/**
 * 调拨详细Service
 * @author dongqikai
 * @version 2018-08-21
 */
@Service
@Transactional(readOnly = true)
public class PlmAllotDetailService extends CrudService<PlmAllotDetailDao, PlmAllotDetail> {

	@Autowired
	private PlmMinusandAddDetailService plmMinusandAddDetailService;
	
	public PlmAllotDetail get(String id) {
		return super.get(id);
	}
	
	public List<PlmAllotDetail> findList(PlmAllotDetail plmAllotDetail) {
		List<PlmAllotDetail> details = new ArrayList<>();
		List<PlmAllotDetail> detailList = super.findList(plmAllotDetail);
		if (detailList != null && detailList.size() > 0) {
			for (PlmAllotDetail detail : detailList) {
				int num = detail.getErialNumber();
				if (StringUtils.isNotBlank(detail.getPrice())) {
					double price = Double.parseDouble(detail.getPrice());
					double sum = num * price;
					detail.setSum(sum+"");
				} else {
					detail.setPrice("0");
					detail.setSum("0");
				}
				try {
					detail.setQrCode(plmMinusandAddDetailService.qrCodeWithBase64(detail.getCode()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				details.add(detail);
			}
		}
		return details;
	}
	
	public Page<PlmAllotDetail> findPage(Page<PlmAllotDetail> page, PlmAllotDetail plmAllotDetail) {
		return super.findPage(page, plmAllotDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmAllotDetail plmAllotDetail) {
		super.save(plmAllotDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmAllotDetail plmAllotDetail) {
		super.delete(plmAllotDetail);
	}
	
}