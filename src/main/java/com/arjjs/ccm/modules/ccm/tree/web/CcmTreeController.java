/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tree.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.ccm.tree.service.CcmTreeService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 树Controller
 * 
 * @author liang
 * @version 2018-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/tree/ccmTree")
public class CcmTreeController extends BaseController {

	@Autowired
	private CcmTreeService ccmTreeService;

	@ModelAttribute
	public CcmTree get(@RequestParam(required = false) String id) {
		CcmTree entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmTreeService.get(id);
		}
		if (entity == null) {
			entity = new CcmTree();
		}
		return entity;
	}

	@RequiresPermissions("tree:ccmTree:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmTree ccmTree, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CcmTree> list = ccmTreeService.findList(ccmTree);
		model.addAttribute("list", list);
		return "ccm/tree/ccmTreeList";
	}

	@RequiresPermissions("tree:ccmTree:view")
	@RequestMapping(value = "form")
	public String form(CcmTree ccmTree, Model model) {
		if (ccmTree.getParent() != null && StringUtils.isNotBlank(ccmTree.getParent().getId())) {
			ccmTree.setParent(ccmTreeService.get(ccmTree.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(ccmTree.getId())) {
				CcmTree ccmTreeChild = new CcmTree();
				ccmTreeChild.setParent(new CcmTree(ccmTree.getParent().getId()));
				List<CcmTree> list = ccmTreeService.findList(ccmTree);
				if (list.size() > 0) {
					ccmTree.setSort(list.get(list.size() - 1).getSort());
					if (ccmTree.getSort() != null) {
						ccmTree.setSort(ccmTree.getSort() + 30);
					}
				}
			}
		}
		if (ccmTree.getSort() == null) {
			ccmTree.setSort(30);
		}
		model.addAttribute("ccmTree", ccmTree);
		return "ccm/tree/ccmTreeForm";
	}

	@RequiresPermissions("tree:ccmTree:edit")
	@RequestMapping(value = "save")
	public String save(CcmTree ccmTree, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmTree)) {
			return form(ccmTree, model);
		}
		ccmTreeService.save(ccmTree);
		addMessage(redirectAttributes, "保存树成功");
		return "redirect:" + Global.getAdminPath() + "/tree/ccmTree/?repage";
	}

	@RequiresPermissions("tree:ccmTree:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmTree ccmTree, RedirectAttributes redirectAttributes) {
		ccmTreeService.delete(ccmTree);
		addMessage(redirectAttributes, "删除树成功");
		return "redirect:" + Global.getAdminPath() + "/tree/ccmTree/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmTree> list = ccmTreeService.findList(new CcmTree());
		for (int i = 0; i < list.size(); i++) {
			CcmTree e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

	

	/***
	 * 社区网格楼栋房屋
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeDataArea")
	public List<Map<String, Object>> treeDataArea(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) String areaid, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmTree> list = ccmTreeService.findListArea(new CcmTree(), type);
		int t = 0;
		int ty = 0;
		//System.out.println(areaid+"321");
		//街道
		if(type.equals("5")){
			for (int i = 0; i < list.size(); i++) {
				CcmTree e1 = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(e1.getId()) ))
						&& (type.equals("5"))) {
					t = Integer.parseInt(e1.getType());
					ty = Integer.parseInt(type);
					if(t <= ty){
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", e1.getId());
						map.put("pId", e1.getParent().getId());
						map.put("name", e1.getName());
						mapList.add(map);
					}
				}
			}
		}
		//社区
		if(type.equals("6")){
			for (int i = 0; i < list.size(); i++) {
				CcmTree e1 = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(e1.getId()) ))
						&& (type.equals("6"))) {
					t = Integer.parseInt(e1.getType());
					ty = Integer.parseInt(type);
					if(t <= ty){
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", e1.getId());
						map.put("pId", e1.getParent().getId());
						map.put("name", e1.getName());
						mapList.add(map);
					}
				}
			}
		}
		//网格
		if(type.equals("7")){
			for (int i = 0; i < list.size(); i++) {
				CcmTree e2 = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(e2.getId()) ))
						&& (type.equals("7"))) {
					t = Integer.parseInt(e2.getType());
					ty = Integer.parseInt(type);
					if(t <= ty){
						//社区选择网格，空全显，有id选网格
						if(!"".equals(areaid)){
							Map<String, Object> map = Maps.newHashMap();
							map.put("id", e2.getId());
							map.put("pId", e2.getParent().getId());
							map.put("name", e2.getName());
							if(areaid.equals(e2.getParent().getId())){
								mapList.add(map);
							}
						}else{
							Map<String, Object> map = Maps.newHashMap();
							map.put("id", e2.getId());
							map.put("pId", e2.getParent().getId());
							map.put("name", e2.getName());
							mapList.add(map);
						}
						
					}
				}
			}
		}
		//房屋
		if(type.equals("9")){
			for (int i = 0; i < list.size(); i++) {
				CcmTree e3 = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(e3.getId()) ))
						&& (type.equals("9"))) {
					t = Integer.parseInt(e3.getType());
					ty = Integer.parseInt(type);
					if(t <= ty){
						//网格选择房屋，空全显，有id选网格
						if(!"".equals(areaid)){
							if(areaid.equals(e3.getParent().getId())){
								Map<String, Object> map = Maps.newHashMap();
								map.put("id", e3.getId());
								map.put("pId", e3.getParent().getId());
								map.put("name", e3.getName());
								mapList.add(map);//选楼栋
								for (int j = 0; j < list.size(); j++) {
									CcmTree h = list.get(j);
									if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(h.getId()) ))
											&& (type.equals("9"))) {
										if(t <= ty){
											if(e3.getId().equals(h.getParent().getId())){
												Map<String, Object> map2 = Maps.newHashMap();
												map2.put("id", h.getId());
												map2.put("pId", h.getParent().getId());
												map2.put("name", h.getName());
												mapList.add(map2);//选楼栋下的房屋
											}
										}
									}
								}
								
							}
							
						}else{
							Map<String, Object> map = Maps.newHashMap();
							map.put("id", e3.getId());
							map.put("pId", e3.getParent().getId());
							map.put("name", e3.getName());
							mapList.add(map);
						}
					}
				}
			}
		}
		return mapList;
	}
	/***
	 * 地图标注树状图
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeDataNew")
	public List<Map<String, Object>> treeDataNew(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Area parent= UserUtils.getUser().getOffice().getArea();
		long t1 = System.currentTimeMillis();
		List<CcmTree> list = ccmTreeService.findListTree(new CcmTree(), type ,parent);
		long t2 = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t2 - t1);

		System.out.println("查询所有数据耗时: " + c.get(Calendar.MINUTE) + "分 "
				+ c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND)
				+ " 毫秒");
		for (int i = 0; i < list.size(); i++) {
			CcmTree e = list.get(i);
			// if (StringUtils.isBlank(extId) || (extId!=null &&
			// !extId.equals(e.getId()) &&
			// e.getParentIds().indexOf(","+extId+",")==-1)){
			// 如果当前的 类别是所需则进行添加
			if (filterType(e.getType(), type) || StringUtils.isBlank(type)) {
				// 当前的是否为被取消的内容
				if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()))) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", e.getId());
					map.put("pId", e.getParentId());
					map.put("name", e.getName());
					map.put("point", StringUtils.isEmpty(e.getAreaPoint()) ? false : true);
					// 当前城市部件  点线面
					if ("citycomponents".equals(e.getType())) {
						if ("01".equals(e.getMore1())) {
							map.put("pointType", "0");
						}
						if ("02".equals(e.getMore1())) {
							map.put("pointType", "2");
						}
						if ("03".equals(e.getMore1())) {
							map.put("pointType", "1");
						}
						
					} else if("broadcast".equals(e.getType())){
						map.put("pointType", "0");
					}else {
						map.put("pointType",
								"camera".equals(e.getType()) ? "0" : "1");
					}

					map.put("areaMap", e.getAreaMap());
					map.put("areaPoint", e.getAreaPoint());
					map.put("type", e.getType());
					map.put("icon", "");
					map.put("color", e.getColor());//区域设置的颜色和透明度
					mapList.add(map);
				}
			}
		}
		long t3 = System.currentTimeMillis();
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(t3 - t1);

		System.out.println("数据返回封装 总耗时: " + c2.get(Calendar.MINUTE) + "分 "
				+ c2.get(Calendar.SECOND) + "秒 " + c2.get(Calendar.MILLISECOND)
				+ " 毫秒");
		return mapList;
	}
	/***
	 * 地图标注树状图app
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeDataNewApp")
	public List<Map<String, Object>> treeDataNewApp(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmTree> list = ccmTreeService.findListTreeApp(new CcmTree());
		Map<String, Object> map2 = Maps.newHashMap();
		map2.put("id", "10000");
		map2.put("pId", "0");
		map2.put("name", "app设备列表");
		map2.put("point", false);
		// 当前城市部件  点线面
		map2.put("pointType", "1");
		map2.put("areaMap", "");
		map2.put("areaPoint", "");
		map2.put("type", "appEfence");
		map2.put("icon", "");
		mapList.add(map2);
		
		for (int i = 0; i < list.size(); i++) {
			CcmTree e = list.get(i);
			// 当前的是否为被取消的内容
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName()+"("+e.getMore1()+")");
				map.put("point", StringUtils.isEmpty(e.getAreaMap()) ? false : true);
				// 当前城市部件  点线面
				map.put("pointType", "1");
				map.put("areaMap", e.getAreaMap());
				map.put("areaPoint", e.getAreaPoint());
				map.put("type", e.getType());
				map.put("icon", "");
				mapList.add(map);
			}
			
		}
		return mapList;
	}

	/**
	 * @see 查看当前的接口是否可以使用
	 * @param a 当前的for循环内容是否可以使用
	 * @param type 当前的需求的类别
	 * @return
	 */
	public boolean filterType(String a, String type) {
		if(StringUtils.isEmpty(a)){
			return false;
		}
		// 默认 区域是可以通过的
		if ((a.startsWith("area")) || (type.equals(a))) {
			return true;
		}
		return false;
	}

}