package com.arjjs.ccm.modules.ccm.index.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.index.service.IndexChartService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping(value = "${adminPath}/index/chart")
public class IndexChartController extends BaseController {

	public static String IP;

	@Autowired
	private IndexChartService indexChartService;

	@RequestMapping("solveEvent")
	public Object oneYearSolveEvent() {
		return indexChartService.oneYearSolveEvent();
	}

	/**
	 * 描述：矛盾纠纷数量查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "byStatus")
	public Object findCountByStatus() {
		return indexChartService.indexChartService();
	}

	/**
	 * 描述：综治机构数量查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "findOfficeCount")
	public Object findOfficeCount() {
		return indexChartService.findOfficeCount();
	}

	/**
	 * 描述：综治机构队伍数量查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "findTeamCount")
	public Object findTeamCount() {
		return indexChartService.findTeamCount();
	}

	/**
	 * 描述：首页统计两新组织数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountByCompType")
	public Object findCountByCompType() {
		return indexChartService.findCountByCompType();
	}

	/**
	 * 描述：首页统计护线护路案件数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountLineProtect")
	public Object findCountLineProtect() {
		return indexChartService.findCountLineProtect();
	}

	/**
	 * 描述：首页统计护线护路数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountLine")
	public Object findCountLine() {
		return indexChartService.findCountLine();
	}

	/**
	 * 描述：首页校园数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountSchool")
	public Object findCountSchool() {
		return indexChartService.findCountSchool();
	}

	/**
	 * 描述：首页校园师生安全数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountSchoolPeople")
	public Object findCountSchoolPeople() {
		return indexChartService.findCountSchoolPeople();
	}

	/**
	 * 描述：首页重点人员数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountPeopleForType")
	public Object findCountPeopleForType() {
		return indexChartService.findCountPeopleForType();
	}

	/**
	 * 描述：首页实有人口数量统计（户籍信息 。流动信息。 境外信息）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findCountByType")
	public Object findCountByType() {
		return indexChartService.findCountByType();
	}

	/**
	 * 描述：首页实有人口数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountByUniformlogo")
	public Object findCountByUniformlogo() {
		return indexChartService.findCountByUniformlogo();
	}

	/**
	 * 描述：首页实有房屋数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountByHouseType")
	public Object findCountByHouseType() {
		return indexChartService.findCountByHouseType();
	}

	/**
	 * 描述：首页地址库数量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCountAddressLibrary")
	public Object findCountAddressLibrary() {
		return indexChartService.findCountAddressLibrary();
	}

	/**
	 * 描述：首页出租房屋数量统计
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findCountByRentPur")
	public Object findCountByRentPur() {
		return indexChartService.findCountByRentPur();
	}
	/*public Object findCountByHousePrup() {
		return indexChartService.findCountByHousePrup();
	}*/


	/**
	 * 查询建筑物数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findCountByconstruction")
	public Object findCountByconstruction() {
		return indexChartService.findCountByconstruction();
	}

	/**
	 * 查询街路巷数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "streetRoadLane")
	public Object streetRoadLane() {
		return indexChartService.streetRoadLane();
	}

	/**
	 * 查询重点场所
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findKeyPlace")
	public Object findKeyPlace() {
		return indexChartService.findKeyPlace();
	}

	/**
	 * 获取ip
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "saveIP")
//	public Object getIP(String ip) {
//		String src=IndexChartController.class.getResource("/").getPath()+"arjccm.properties";
//		Map<String, Object> map=new HashMap<>();
//		map.put("FILE_UPLOAD_URL",ip);
//		System.out.println(writeData(map,src));
//		return null;
//	}

	/**
	 * 修改并写properties
	 * @param map
	 * @param fileURL
	 * @return
	 */
	public boolean writeData(Map<String, Object> map, String fileURL) {
		Properties prop = new Properties();
		InputStream fis = null;
		OutputStream fos = null;
		try {
			//获得文件
			File file = new File(fileURL);
			//查看文件是否存在
			if (!file.exists()){
				return false;
			}
			fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();// 一定要在修改值之前关闭fis
			fos = new FileOutputStream(file);
			Iterator<Map.Entry<String, Object>> valueSet = map.entrySet().iterator();
			//便利map的值
			while (valueSet.hasNext()) {
				Map.Entry<String, Object> entry = valueSet.next();
				String key = entry.getKey();
				String value= entry.getValue().toString();
				prop.setProperty(key, value);
				prop.store(fos, "Update '" + key + "' value");
			}
			fos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
