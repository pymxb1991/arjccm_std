package com.arjjs.ccm.modules.ccm.ccmsys.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmLogService;
import com.arjjs.ccm.tool.ExportExcel;

/**
 * 日志管理Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log") 
public class CcmLogController extends BaseController  {
	
	@Autowired
	private CcmLogService ccmLogService;

	/**
	 * 导出所选日志
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = "export")
	public String exportFile(String ids, CcmLog CcmLog, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String [] strArr={"操作菜单","操作用户","提交方式","创造时间"};
	try {
			String fileName = "日志数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmLog> logs = new ArrayList<CcmLog>(); 
			String[] idarr= null;
			if(ids.contains(",")) {
				idarr = ids.split(",");
				for (int i = 0; i < idarr.length; i++) {
					CcmLog ccmLog2 = ccmLogService.getById(idarr[i]);
					logs.add(ccmLog2);
				}
			} else {
				CcmLog ccmLog2 = ccmLogService.getById(ids);
				logs.add(ccmLog2);
			}
			new ExportExcel("日志数据", CcmLog.class,strArr).setDataList(logs).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日志数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/log/?repage";
	}
	
}
