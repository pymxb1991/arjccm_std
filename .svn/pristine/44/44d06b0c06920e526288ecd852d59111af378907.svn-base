/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymembindService;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

import sun.misc.BASE64Decoder;

/**
 * 登录Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/archives/office")
public class MobileArchivesController extends BaseController {

	// 党员关系信息
	@Autowired
	private PbsPartymembindService pbsPartymembindService;
	// 党员信息
	@Autowired
	private PbsPartymemService pbsPartymemService;
	// 党员 -部分 关系
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
	// 部门 扩展信息
	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;

	// 党员list 信息
	@RequestMapping(value = "partymemlist")
	public String partymemlist(PbsOffice office, HttpServletRequest request, HttpServletResponse response, Model model) {
		PbsPartymem pbsPartymem = new PbsPartymem();
		pbsPartymem.setOfficeid(office.getId());
		pbsPartymem.setSName(office.getMemberName());
		List<PbsPartymem> list = pbsPartymemService.findListByOffice(pbsPartymem);
		model.addAttribute("partymemlist", list);
		model.addAttribute("officeid", office.getId());
		model.addAttribute("officeName", office.getName());
		model.addAttribute("memberName", office.getMemberName());
		
		return "Nav-home/archives/archivesUser";
	}

	// 单位list 信息
	@RequestMapping(value = "officelist")
	public String officelist(HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 当前的 用户 为党员
			PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
			pbsDepartmentbindDto.setSPartymemid(UserUtils.getPartymem().getId());
			List<PbsDepartmentbind> pbsDepartmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
			List<PbsOffice> listOffice = new ArrayList<>();
			//
			for (PbsDepartmentbind bind : pbsDepartmentbinds) {
				PbsOffice officeNew = new PbsOffice();
				officeNew.setType("2");
				officeNew.setId(bind.getSDepartmentid());
				listOffice.add(officeNew);
			}
			List<PbsOffice> list = pbsDepartmentetcService.getAllOfficeWithMem(listOffice);
			model.addAttribute("ofcList", list);// 向页面添加对象信息
		}
		return "Nav-home/archives/archivesOffice";
	}

	// 用户list 信息
	@RequestMapping(value = "pbspartymeminfo")
	public String userInfo(@RequestParam(required = false) String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 用户信息
		PbsPartymem pbsPartymemDto = new PbsPartymem();
		pbsPartymemDto.setId(id);
		PbsPartymem pbspartymem = pbsPartymemService.get(id);
		// 向页面 添加对象信息
		PbsPartymembind pbsPartymembindDto = new PbsPartymembind();
		pbsPartymembindDto.setSPrimarykey01(id);
		// 关系
		List<PbsPartymembind> list = pbsPartymembindService.findList(pbsPartymembindDto);
		model.addAttribute("pbspartymem", pbspartymem);
		model.addAttribute("binds", list);

		return "Nav-home/archives/archivesInfo";
	}

	// 通讯录信息
	@RequestMapping(value = "maillist")
	public String maillist(PbsOffice office, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 当前的 用户 为党员
			PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
			pbsDepartmentbindDto.setSPartymemid(UserUtils.getPartymem().getId());
			List<PbsDepartmentbind> pbsDepartmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
			List<PbsOffice> listOffice = new ArrayList<>();
			//
			for (PbsDepartmentbind bind : pbsDepartmentbinds) {
				PbsOffice officeNew = new PbsOffice();
				officeNew.setType("2");
				officeNew.setId(bind.getSDepartmentid());
				listOffice.add(officeNew);
			}
			List<PbsOffice> officelist = pbsDepartmentetcService.getAllOfficeWithMem(listOffice);
			List<String> ids = new ArrayList<>();
			for (PbsOffice officeDt : officelist) {
				ids.add(officeDt.getId());
			}
			List<PbsPartymem> pbsPartymems = pbsPartymemService.findListByOfficeList(ids);
			model.addAttribute("pbsPartymems", pbsPartymems);
		}

		return "Nav-home/maillist/maillist";
	}

	@ResponseBody
	@RequestMapping(value = "file")
	public String file(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) {
		saveFile(file, "E:/");
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping(value = "photo")
	public String photo(String base64str, String filename, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(base64str)){
			return FAIL;
		}
		String[] fileArray =base64str.split(",");
		String fileString = fileArray[1];
		
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(fileString);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = "d://"+filename;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return SUCCESS;
		} catch (Exception e) {
			return FAIL;
		}
	}

	private boolean saveFile(MultipartFile file, String path) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				File filepath = new File(path);
				if (!filepath.exists())
					filepath.mkdirs();
				// 文件保存路径
				String savePath = path + file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(savePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}