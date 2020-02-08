package com.arjjs.ccm.modules.pbs.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/office/pbsArchivesPC/")
public class PbsArchivesPCController extends BaseController {
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;
	@Autowired
	private PbsPartymemService pbsPartymemService;
	
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
		return "pbs/sys/pbsMaillist";
	}
	
}