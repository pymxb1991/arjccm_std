/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.files.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.files.dao.PlmPublicFilesDao;
import com.arjjs.ccm.modules.plm.files.entity.PlmPublicFiles;

/**
 * 附件Service
 * @author dongqikai
 * @version 2018-06-22
 */
@Service
@Transactional(readOnly = true)
public class PlmPublicFilesService extends CrudService<PlmPublicFilesDao, PlmPublicFiles> {

	public PlmPublicFiles get(String id) {
		return super.get(id);
	}
	
	public List<PlmPublicFiles> findList(PlmPublicFiles plmPublicFiles) {
		return super.findList(plmPublicFiles);
	}
	
	public List<PlmPublicFiles> findListByFid(String id) {
		PlmPublicFiles plmPublicFiles = new PlmPublicFiles();
		plmPublicFiles.setFid(id);
		return this.findList(plmPublicFiles);
	}
	
	public Page<PlmPublicFiles> findPage(Page<PlmPublicFiles> page, PlmPublicFiles plmPublicFiles) {
		return super.findPage(page, plmPublicFiles);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmPublicFiles plmPublicFiles) {
		super.save(plmPublicFiles);
	}
	
	@Transactional(readOnly = false)
	public void saveAccessoryInfo(String id, String filePaths, String tableName) {
		if (StringUtils.isBlank(id)) {
			return;
		}
		this.deleteByFid(id);
		if (StringUtils.isBlank(filePaths)) {
			return;
		}
		try {
			filePaths = filePaths.substring(1, filePaths.length());
			String[] fPaths = filePaths.split("\\|");
			for (int i = 0; i < fPaths.length; i++) {
				PlmPublicFiles plmPublicFiles = new PlmPublicFiles();
				String filePath = fPaths[i];
				filePath = URLDecoder.decode(filePath, "utf-8");
				String[] strs = filePath.split("/");
				String fileName = strs[strs.length-1];
				String fileType = fileName.split("\\.",2)[1];
				String cnName = fileName.split("\\.",2)[0];
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				String objPath = request.getSession().getServletContext().getRealPath("/");
				objPath = objPath.substring(0, objPath.lastIndexOf("arjplm")-1);
				System.out.println(objPath + filePath);
				File file = new File(objPath + filePath);
				long fileSize = 0;
				if (file.exists() && file.isFile()) {
					fileSize = file.length();
				}
				plmPublicFiles.setFid(id);
				plmPublicFiles.setSeqidx(String.valueOf(i));
				plmPublicFiles.setTablename(tableName);
				plmPublicFiles.setCnname(cnName);
				plmPublicFiles.setFilename(fileName);
				plmPublicFiles.setFiletype(fileType);
				plmPublicFiles.setSize(String.valueOf(fileSize));
				this.save(plmPublicFiles);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmPublicFiles plmPublicFiles) {
		super.delete(plmPublicFiles);
	}
	
	@Transactional(readOnly = false)
	public void deleteByFid(String id) {
		PlmPublicFiles publicFiles = new PlmPublicFiles();
		publicFiles.setFid(id);
		List<PlmPublicFiles> files = this.findList(publicFiles);
		if (files !=null && files.size() > 0) {
			for (PlmPublicFiles plmPublicFiles : files) {
				this.delete(plmPublicFiles);
			}
		}
	}
}