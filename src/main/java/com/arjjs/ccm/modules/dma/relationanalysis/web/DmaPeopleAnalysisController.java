/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.dma.relationanalysis.web;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopPop;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopVehile;
import com.arjjs.ccm.modules.ccm.cpp.service.CppPopPopService;
import com.arjjs.ccm.modules.ccm.cpp.service.CppPopVehileService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.tool.EchartForce;
import com.arjjs.ccm.tool.EchartForceLinks;
import com.arjjs.ccm.tool.EchartForceNodes;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 人员分析Controller
 * 
 * @author lhf
 * @version 2019-09-4
 */
@Controller
@RequestMapping(value = "${adminPath}/relationAnalysis/dmaPeopleAnalysis")
public class DmaPeopleAnalysisController extends BaseController {

	@Autowired
	private CcmPeopleService ccmPeopleService;

	@Autowired
	private CppPopPopService cppPopPopService;

	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;

	@Autowired
	private CppPopVehileService cppPopVehileService;

	@RequiresPermissions("relationAnalysis:dmaPeopleAnalysis:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPeople ccmPeople,HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPeople> page = ccmPeopleService.findPage(new Page<CcmPeople>(request, response), ccmPeople);

		List<CcmPeople> list = page.getList();
		//
		CcmPeople ccmPeople2 = new CcmPeople();
		String[] listLimite = new String[list.size()];
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				listLimite[i] = list.get(i).getId();
			}
			ccmPeople2.setListLimite(listLimite);
			List<CcmPeople> list2 = ccmPeopleService.findListLimite_V2(ccmPeople2);// 数组查询id
			page.setList(list2);
		}
		model.addAttribute("page", page);
		return "dma/relationanalysis/peopleAnalysisList";
	}


	@RequiresPermissions("relationAnalysis:dmaPeopleAnalysis:view")
	@RequestMapping(value = "peopleAnalays")
	public String peopleAnalays(CcmPeople ccmPeople,HttpServletRequest request, HttpServletResponse response,
					   Model model) {
//		//人际关系,category=3
//		CppPopPop cppPopPop = new CppPopPop();
//		cppPopPop.setIdCard1(ccmPeople.getIdent());
//		EchartForce listForForce = cppPopPopService.findListForForce(cppPopPop);
		model.addAttribute("ccmPeople",ccmPeople);
		return "dma/relationanalysis/peopleAnalysisForm";
	}

	@ResponseBody
	@RequiresPermissions("relationAnalysis:dmaPeopleAnalysis:view")
	@RequestMapping(value = "getPeopleRelationData")
	public Map<String, Object> getPeopleRelationData(CcmPeople ccmPeople,HttpServletRequest request, HttpServletResponse response,
								Model model) {

		List<EchartForceNodes> echartForceNodes = new ArrayList<EchartForceNodes>();
		List<EchartForceLinks> echartForceLinks = new ArrayList<EchartForceLinks>();

		//本人信息
		EchartForceNodes ownForceNodes = new EchartForceNodes();
		ownForceNodes.setName(ccmPeople.getIdent());
		ownForceNodes.setLabel(ccmPeople.getName());
		ownForceNodes.setValue(25);
		echartForceNodes.add(ownForceNodes);

		//事件信息,category=1
		CcmEventIncident ccmEventIncident = new CcmEventIncident();
		ccmEventIncident.setCulPaperid(ccmPeople.getIdent());
		EchartForce eventForForce = ccmEventIncidentService.getEventForForce(ccmEventIncident);
		if(eventForForce!=null){
			echartForceNodes.addAll(eventForForce.getNodes());
			echartForceLinks.addAll(eventForForce.getLinks());
		}

		//现住址,category=2
		EchartForceNodes residencedetailNodes = new EchartForceNodes();
		String residencedetailUUID = UUID.randomUUID().toString();
		residencedetailNodes.setName(residencedetailUUID);
		residencedetailNodes.setLabel(ccmPeople.getResidencedetail());
		residencedetailNodes.setValue(15);
		residencedetailNodes.setCategory(2);

		echartForceNodes.add(residencedetailNodes);
		EchartForceLinks residencedetailLinks = new EchartForceLinks();
		residencedetailLinks.setTarget(ccmPeople.getIdent());
		residencedetailLinks.setSource(residencedetailUUID);
		echartForceLinks.add(residencedetailLinks);


		//人际关系,category=3
		CppPopPop cppPopPop = new CppPopPop();
		cppPopPop.setIdCard1(ccmPeople.getIdent());
		EchartForce peopleForForce = cppPopPopService.findListForForce(cppPopPop);
		if(peopleForForce!=null) {
			echartForceNodes.addAll(peopleForForce.getNodes());
			echartForceLinks.addAll(peopleForForce.getLinks());
		}

		//车辆信息，category=4
		CppPopVehile cppPopVehile = new CppPopVehile();
		cppPopVehile.setIdCard(ccmPeople.getIdent());
		EchartForce vehileForForce = cppPopVehileService.getVehileForForce(cppPopVehile);
		if(vehileForForce!=null) {
			echartForceNodes.addAll(vehileForForce.getNodes());
			echartForceLinks.addAll(vehileForForce.getLinks());
		}

		Map<String, Object> data = new HashMap<>();
		data.put("nodes",echartForceNodes);
		data.put("links",echartForceLinks);
		return  data;
	}
}