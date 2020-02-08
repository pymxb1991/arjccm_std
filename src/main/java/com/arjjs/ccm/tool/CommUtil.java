package com.arjjs.ccm.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.gis.Point;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityComponents;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.sys.utils.DictUtils;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CommUtil {

	public static final String COMP_IMPO_TYPE_NO = "00";		  //企业重点类型-无
	public static final String COMP_IMPO_TYPE_LOG = "01";		  //企业重点类型-物流安全
	public static final String COMP_IMPO_TYPE_SAFE = "02";		  //企业重点类型-安全生产重点
	public static final String COMP_IMPO_TYPE_FIRE = "03";		  //企业重点类型-消防重点
	public static final String COMP_IMPO_TYPE_SECURITY = "04";	  //企业重点类型-治安重点
	public static final String COMP_IMPO_TYPE_ELSE = "05";		  //企业重点类型-其他重点
	public static final int COMP_IMPO_TYPE_SDYQ = 1;		  //企业重点类型-水电油气
	public static final int COMP_IMPO_TYPE_SPECIAL = 1;		  //企业重点类型-场所特业
	public static final int COMP_IMPO_TYPE_DENSE = 1;		  //企业重点类型-人员密集
	/**
	 * @author Arj
	 * @see 用于 规范与复用某些具体方法
	 */

	// eList1

	public static void main(String[] args) {
//		int MAX = 30;
//		List<EchartType> eList1 = new ArrayList<>();
//		for (int type = 3; type < 6; type++) {
//			EchartType eNumber = new EchartType();
//			eNumber.setType("2016-" + type + "-" + (MAX - type));
//			eNumber.setValue(type + "");
//			eList1.add(eNumber);
//		}
//		List<EchartType> eList2 = new ArrayList<>();
//		for (int type = 4; type < 10; type++) {
//			EchartType eNumber = new EchartType();
//			eNumber.setType("2016-" + type + "-" + (MAX - type));
//			eNumber.setValue(type + "");
//			eList2.add(eNumber);
//		}
//		List<EchartType> eList3 = new ArrayList<>();
//		for (int type = 9; type < 18; type++) {
//			EchartType eNumber = new EchartType();
//			eNumber.setType("2016-" + (18 - type) + "-" + (MAX - type));
//			eNumber.setValue(type + "");
//			eList3.add(eNumber);
//		}
//
//		Collections.sort(eList3, new Comparator<EchartType>() {
//			@Override
//			public int compare(EchartType o1, EchartType o2) {
//				return compareE(o1, o2);
//			}
//		});
//		for (EchartType e : eList3) {
//			System.out.print(e.getType() + " " + e.getValue() + "|");
//		}

		System.out.println(DateUtils.getWeek());
	}
	public static boolean isJsonObject(String content) {
	    // 此处应该注意，不要使用StringUtils.isEmpty(),因为当content为"  "空格字符串时，JSONObject.parseObject可以解析成功，
	    // 实际上，这是没有什么意义的。所以content应该是非空白字符串且不为空，判断是否是JSON数组也是相同的情况。
	    if(StringUtils.isBlank(content))
	        return false;
	    try {
	        JSONObject jsonStr = JSONObject.parseObject(content);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	/**
	 * @see 生成 title List
	 * @param result
	 * @param B
	 * @return
	 */
	public static List<String> getTitleList(List<String> result, List<EchartType> B) {
		// 添加 B List
		List<String> Ls = new ArrayList<>();
		// 生成 B title List
		for (EchartType b : B) {
			Ls.add(b.getType());
		}
		// 清除已含有数据
		result.removeAll(Ls);
		// 添加 未含有数据
		result.addAll(Ls);
		// 去除重复的数据
		HashSet<String> h = new HashSet<String>(result);
		result.clear();
		result.addAll(h);
		return result;
	}

	public static int compareE(String arg0, String arg1) {
		// 当前 是否是同一字符串
		if (arg0.equals(arg1)) {
			return 0;
		}
		String[] m1 = arg0.split("-");
		String[] m2 = arg1.split("-");
		// 当前年份是否 相同
		if (m1[0].equals(m2[0])) {
			return Integer.parseInt(m1[1]) - Integer.parseInt(m2[1]) > 0 ? 1 : -1;
			// 当前年份 不同
		} else {
			return Integer.parseInt(m1[0]) - Integer.parseInt(m2[0]) > 0 ? 1 : -1;
		}
	}

	public static int compareE(EchartType Echar0, EchartType Echar1) {
		String arg0 = Echar0.getType();
		String arg1 = Echar1.getType();
		// 当前 是否是同一字符串
		if (arg0.equals(arg1)) {
			return 0;
		}
		String[] m1 = arg0.split("-");
		String[] m2 = arg1.split("-");
		// 当前 为 年-月
		if (m1.length == 2) {
			// 当前年份是否 相同
			if (m1[0].equals(m2[0])) {
				return Integer.parseInt(m1[1]) - Integer.parseInt(m2[1]) > 0 ? 1 : -1;
				// 当前年份 不同
			} else {
				return Integer.parseInt(m1[0]) - Integer.parseInt(m2[0]) > 0 ? 1 : -1;
			}
			// 当前 为 年-月-日
		} else if (m1.length == 3) {
			// 当前年份 不同
			if (m1[0].equals(m2[0])) {
				if (m1[1].equals(m2[1])) {
					// 日期 比较
					return Integer.parseInt(m1[2]) - Integer.parseInt(m2[2]) > 0 ? 1 : -1;
				} else {
					// 月份 比较
					return Integer.parseInt(m1[1]) - Integer.parseInt(m2[1]) > 0 ? 1 : -1;
				}
			} else {
				// 年份比较
				return Integer.parseInt(m1[0]) - Integer.parseInt(m2[0]) > 0 ? 1 : -1;
			}
		}
		return 0;
	}

	/**
	 * @param list1  标准标题List
	 * @param list2  实际数据List
	 * @return 封装数据
	 */
	public static List<EchartType> getSimple(List<String> M, List<EchartType> list2) {
		List<EchartType> list1 = new ArrayList<>();
		for (String a : M) {
			EchartType echar = new EchartType();
			echar.setType(a);
			list1.add(echar);
		}
		// 合并两个集合
		for (EchartType EN : list1) {
			for (EchartType EO : list2) {
				if (EO.getType().equals(EN.getType())) {
					EN.setValue(EO.getValue());
				}
			}
		}
		return list1;
	}

	/**
	 * @see 返回跳转的页面
	 * @param arg0
	 * @return
	 */
	public static String GetPageForLog(String arg0) {
		if (StringUtils.isEmpty(arg0)) {
			return "";
		}
		switch (arg0) {
		// 校园周边重点人员
		case "ccm_house_schoolrim":
			return "/house/ccmHouseSchoolrim/popList";
		// 安置帮教人员
		case "ccm_house_release":
			return "/house/ccmHouseRelease";
		// 肇事肇祸等严重精神障碍患者
		case "ccm_house_psychogeny":
			return "/house/ccmHousePsychogeny";
		// 社区矫正人员
		case "ccm_house_rectification":
			return "/house/ccmHouseRectification";
		// 吸毒人员
		case "ccm_house_drugs":
			return "/house/ccmHouseDrugs";
		//故意犯法释放人员
		case "ccm_house_deliberatelyIllegal":
			return "/house/ccmHouseDeliberatelyIllegal";
		//危害国家安全
		case "ccm_harm_national_security":
			return "/house/ccmHarmNationalSecurity";				
		// 艾滋病患者
		case "ccm_house_aids":
			return "/house/ccmHouseAids";
		// 楼栋
		case "ccm_house_buildmanage":
			return "/house/ccmHouseBuildmanage";
		// 房屋
		case "ccm_pop_tenant":
			return "/pop/ccmPopTenant";
		// 实有人口管理
		case "ccm_people":
			return "/pop/ccmPeople";
		// 留守人口管理
		case "ccm_pop_behind":
			return "/pop/ccmPopBehind";

		// 重点青少年管理
		case "ccm_house_kym":
			return "/house/ccmHouseKym";

		// 非公有制经济组织
		case "ccm_org_npse":
			return "/org/ccmOrgNpse";

		// 社会组织管理
		case "ccm_org_socialorg":
			return "/org/ccmOrgSocialorg";

		// 消防
		case "ccm_security_asset":
			return "/security/ccmSecurityAsset";

		// 安防
		case "ccm_security_secur":
			return "/security/ccmSecuritySecur";

		// 学校
		case "ccm_house_school":
			return "/house/ccmHouseSchoolrim/list";

		// 矛盾纠纷排查化解
		case "ccm_event_ambi":
			return "/event/ccmEventAmbi";

		// 安全生产重点
		case "ccm_org_npsesafe":
			return "/org/ccmOrgNpsesafe";

		// 消防安全重点
		case "ccm_org_npseFire":
			return "/org/ccmOrgNpseFire";

		// 治安重点
		case "ccm_org_npseSecurity":
			return "/org/ccmOrgNpseSecurity";

		// 其他重点
		case "ccm_org_npseElse":
			return "/org/ccmOrgNpseElse";

		// 物流安全重点
		case "ccm_org_npseLog":
			return "/org/ccmOrgNpseLog";

		// 重点上访人员
		case "ccm_house_petition":
			return "/house/ccmHousePetition";

		// 涉教人员
		case "ccm_house_heresy":
			return "/house/ccmHouseHeresy";

		// 危险品从业人员
		case "ccm_house_dangerous":
			return "/house/ccmHouseDangerous";

		// 工作日志
		case "ccm_sys_workreport":
			return "/sys/ccmWorkReport/self";

		// 风险事件管理
		case "risk_incident":
			return "/report/riskIncident";
		
		// 场所特业理
		case "ccm_org_npseSpecial":
			return "/org/ccmOrgNpseSpecial";
		// 水电油气管理
		case "ccm_org_npseSdyq":
			return "/org/ccmOrgNpseSdyq";
		// 人员密集场所管理
		case "ccm_org_npseDense":
			return "/org/ccmOrgNpseDense";
		// 宗教场所管理
		case "ccm_org_site":
			return "/org/ccmOrgSite";
		// 场所管理
		case "ccm_base_place":
			return "/place/ccmBasePlace/emphasisForm";	
			
		default:
			return "";
		}
	}

	/**
	 * @see 返回跳转的页面
	 * @param arg0
	 * @return
	 */
	public static String GetPageFormForLog(String arg0) {
		if (StringUtils.isEmpty(arg0)) {
			return "";
		}
		switch (arg0) {
		// 校园周边重点人员
		case "ccm_house_schoolrim":
			return "/pop/ccmPeople/formPop";
		// 安置帮教人员
		case "ccm_house_release":
			return "/house/ccmHouseRelease/form";
		// 肇事肇祸等严重精神障碍患者
		case "ccm_house_psychogeny":
			return "/house/ccmHousePsychogeny/form";
		// 社区矫正人员
		case "ccm_house_rectification":
			return "/house/ccmHouseRectification/form";
		// 吸毒人员
		case "ccm_house_drugs":
			return "/house/ccmHouseDrugs/form";
		//故意犯法释放人员
		case "ccm_house_deliberatelyIllegal":
			return "/house/ccmHouseDeliberatelyIllegal/form";
		//危害国家安全
		case "ccm_harm_national_security":
			return "/house/ccmHarmNationalSecurity/form";				
		// 艾滋病患者
		case "ccm_house_aids":
			return "/house/ccmHouseAids/form";
		// 楼栋
		case "ccm_house_buildmanage":
			return "/house/ccmHouseBuildmanage/form";
		// 房屋
		case "ccm_pop_tenant":
			return "/pop/ccmPopTenant/form";
		// 实有人口管理
		case "ccm_people":
			return "/pop/ccmPeople/form";

		// 留守人口管理
		case "ccm_pop_behind":
			return "/pop/ccmPopBehind/form";

		// 重点青少年管理
		case "ccm_house_kym":
			return "/house/ccmHouseKym/form";

		// 非公有制经济组织
		case "ccm_org_npse":
			return "/org/ccmOrgNpse/form";

		// 社会组织管理
		case "ccm_org_socialorg":
			return "/org/ccmOrgSocialorg/form";

		// 消防
		case "ccm_security_asset":
			return "/security/ccmSecurityAsset/form";

		// 安防
		case "ccm_security_secur":
			return "/security/ccmSecuritySecur/form";

		// 学校
		case "ccm_house_school":
			return "/house/ccmHouseSchoolrim/form";

		// 矛盾纠纷排查化解
		case "ccm_event_ambi":
			return "/event/ccmEventAmbi/form";

		// 安全生产重点
		case "ccm_org_npsesafe":
			return "/org/ccmOrgNpsesafe/form";

		// 消防安全重点
		case "ccm_org_npseFire":
			return "/org/ccmOrgNpseFire/form";

		// 治安重点
		case "ccm_org_npseSecurity":
			return "/org/ccmOrgNpseSecurity/form";

		// 其他重点
		case "ccm_org_npseElse":
			return "/org/ccmOrgNpseElse/form";

		// 重点上访人员
		case "ccm_house_petition":
			return "/house/ccmHousePetition/form";

		// 涉教人员
		case "ccm_house_heresy":
			return "/house/ccmHouseHeresy/form";
		// 危险品从业人员
		case "ccm_house_dangerous":
			return "/house/ccmHouseDangerous/form";
		// 工作日志
		case "ccm_sys_workreport":
			return "/sys/ccmWorkReport/view";
		// 物流安全重点
		case "ccm_org_npseLog":
			return "/org/ccmOrgNpseLog/form";
		// 风险事件管理
		case "risk_incident":
			return "/report/riskIncident/form";
		
		// 场所特业理
		case "ccm_org_npseSpecial":
			return "/org/ccmOrgNpseSpecial/form";
		// 水电油气管理
		case "ccm_org_npseSdyq":
			return "/org/ccmOrgNpseSdyq/form";
		// 人员密集场所管理
		case "ccm_org_npseDense":
			return "/org/ccmOrgNpseDense/form";
		// 宗教场所管理
		case "ccm_org_site":
			return "/org/ccmOrgSite/form";
		// 场所管理
		case "ccm_base_place":
			return "/place/ccmBasePlace/emphasisForm";
			
			
		default:
			return "";
		}
	}

	/**
	 * @see 返回 表名称
	 * @param arg0
	 *            表名
	 * @return
	 */
	public static String GetStringForLog(String arg0) {
		if (StringUtils.isEmpty(arg0)) {
			return "";
		}
		switch (arg0) {
		// 校园周边重点人员
		case "ccm_house_schoolrim":
			return "校园周边重点人员";
		// 安置帮教人员
		case "ccm_house_release":
			return "安置帮教人员";
		// 肇事肇祸等严重精神障碍患者
		case "ccm_house_psychogeny":
			return "肇事肇祸等严重精神障碍患者";
		// 社区矫正人员
		case "ccm_house_rectification":
			return "社区矫正人员";
		// 吸毒人员
		case "ccm_house_drugs":
			return "吸毒人员";
		//故意犯法释放人员
		case "ccm_house_deliberatelyIllegal":
			return "故意违法刑释不足5年";
		//危害国家安全
		case "ccm_harm_national_security":
			return "危害国家安全活动嫌疑";
		// 重点青少年管理
		case "ccm_house_kym":
			return "重点青少年";
		// 楼栋
		case "ccm_house_buildmanage":
			return "楼栋";
		// 房屋
		case "ccm_pop_tenant":
			return "房屋";
		// 实有人口管理
		case "ccm_people":
			return "实有人口";
		// 留守人口管理
		case "ccm_pop_behind":
			return "留守人口";
		// 艾滋病患者
		case "ccm_house_aids":
			return "艾滋病患者";
		// 非公有制经济组织
		case "ccm_org_npse":
			return "非公有制经济组织";
		// 社会组织管理
		case "ccm_org_socialorg":
			return "社会组织";
		// 消防
		case "ccm_security_asset":
			return "消防";
		// 安防
		case "ccm_security_secur":
			return "安防";
		// 学校
		case "ccm_house_school":
			return "学校";
		// 矛盾纠纷排查化解
		case "ccm_event_ambi":
			return "矛盾纠纷排查化解";
		// 安全生产重点
		case "ccm_org_npsesafe":
			return "安全生产重点";
		// 消防安全重点
		case "ccm_org_npseFire":
			return "消防安全重点";
		// 治安重点
		case "ccm_org_npseSecurity":
			return "治安重点";
		// 其他重点
		case "ccm_org_npseElse":
			return "其他重点";
		// 物流安全重点
		case "ccm_org_npseLog":
			return "物流安全重点";
		// 重点上访人员
		case "ccm_house_petition":
			return "重点上访人员";
		// 涉教人员
		case "ccm_house_heresy":
			return "涉教人员";
		// 危险品从业人员
		case "ccm_house_dangerous":
			return "危险品从业人员";
		// 工作日志
		case "ccm_sys_workreport":
			return "工作日志";
		// 风险事件管理
		case "risk_incident":
			return "风险事件";
		
		// 场所特业理
		case "ccm_org_npseSpecial":
			return "场所特业";
		// 水电油气管理
		case "ccm_org_npseSdyq":
			return "水电油气";
		// 人员密集场所管理
		case "ccm_org_npseDense":
			return "人员密集场所";
		// 宗教场所管理
		case "ccm_org_site":
			return "宗教场所";
		// 场所管理
		case "ccm_base_place":
			return "场所";
		// 严重刑事犯罪活动嫌疑
		case "ccm_serious_criminal_offense":
			return "严重刑事犯罪活动嫌疑";
		// 闹事行凶报复嫌疑
		case "ccm_house_dispute":
			return "闹事行凶报复嫌疑";
		default:
			return "";
		}
	}

	public static Map<String, SearchTab> GetSearchTabForIncident(List<EchartType> list) {
		// 返回结果
		Map<String, SearchTab> ResultMap = new HashMap<String, SearchTab>();
		for (int i = 0; i < list.size(); i++) {
			EchartType et = list.get(i);
			// 查看该map中是否含有该list
			if (StringUtils.isEmpty(et.getType())) {
				continue;
			}
			if (ResultMap.containsKey(et.getType())) {
				SearchTab searchTabDto = ResultMap.get(et.getType());
				switch (et.getTypeO()) {
				case "重特大":
					searchTabDto.setValue1(et.getValue());
					break;
				case "重大":
					searchTabDto.setValue2(et.getValue());
					break;
				case "较大":
					searchTabDto.setValue3(et.getValue());
					break;
				case "一般":
					searchTabDto.setValue4(et.getValue());
					break;
				}
			} else {
				SearchTab searchTabDto = new SearchTab();
				switch (et.getTypeO()) {
				case "重特大":
					searchTabDto.setValue1(et.getValue());
					break;
				case "重大":
					searchTabDto.setValue2(et.getValue());
					break;
				case "较大":
					searchTabDto.setValue3(et.getValue());
					break;
				case "一般":
					searchTabDto.setValue4(et.getValue());
					break;
				}
				ResultMap.put(et.getType(), searchTabDto);
			}
		}
		return ResultMap;
	}

	/**
	 * 1:安全生产重点场所,2:消防生产重点场所 , 3:治安重点场所,4:物流安全重点场所,5:其他重点场所。<br>
	 * 6:学校信息 学校是另一张表，所以不在该内容中。
	 * 
	 * @param type
	 *            接收的类型参数结果
	 * @return 该场所的需要的类型结果
	 */
	public static String ReturnCompImpoType(String type) {
		// 获取返回的字符串
		String returnCom = "";
		// 判断当前是否位数字
		if (!StringUtils.isNumeric(type)) {
			// 如果不是数字 返回为空 默认为情况不会出现该情况
			return "";
		}
		int comType = Integer.parseInt(type);

		// boolean type6= false;
		boolean type1 = (comType % 2) == 1 ? true : false;
		boolean type2 = (comType % 4 / 2) == 1 ? true : false;
		boolean type3 = (comType % 8 / 4) == 1 ? true : false;
		boolean type4 = (comType % 16 / 8) == 1 ? true : false;
		boolean type5 = (comType % 32 / 16) == 1 ? true : false;
		boolean type6 = (comType % 64 / 32) == 1 ? true : false;

		if (type1) {
			// 1:安全生产重点场所
			returnCom += "01";
		}
		if (type2) {
			// 2:消防生产重点场所
			returnCom += StringUtils.isEmpty(returnCom) ? "02" : ",02";
		}
		if (type3) {
			// 3:治安重点场所
			returnCom += StringUtils.isEmpty(returnCom) ? "03" : ",03";
		}
		if (type4) {
			// 4:物流安全重点场所
			returnCom += StringUtils.isEmpty(returnCom) ? "04" : ",04";
		}
		if (type5) {
			// 5:其他重点场所
			returnCom += StringUtils.isEmpty(returnCom) ? "05" : ",05";
		}
		if (type6) {
			// 6:学校信息 学校是另一张表，所以不在该内容中
			returnCom += StringUtils.isEmpty(returnCom) ? "06" : ",06";
		}

		return returnCom;
	}

	/**
	 * 1:公安,2:医院 , 3:消防,4:图书馆,5:体育场,6:博物馆,7:供水,8:供电。10：警务室，11：社区(村)工作站
	 * @param type 接收的类型参数结果
	 * @return 该场所的需要的类型结果
	 */
	public static String ReturnCommonalityType(String type) {
		// 获取返回的字符串
		String returnCom = "";
		// 判断当前是否位数字
		if (!StringUtils.isNumeric(type)) {
			// 如果不是数字 返回为空 默认为情况不会出现该情况
			return "";
		}
		// int commonalityType = Integer.parseInt(type);

		//
		boolean type1 = false;
		boolean type2 = false;
		boolean type3 = false;
		boolean type4 = false;
		boolean type5 = false;
		boolean type6 = false;
		boolean type7 = false;
		boolean type8 = false;
//		boolean type10 = false;
//		boolean type11 = false;
		int[] intArray = new int[type.length()];
		for (int i = 0; i < type.length(); i++) {
			// 遍历type将每一位数字添加如intArray
			Character ch = type.charAt(i);
			intArray[i] = Integer.parseInt(ch.toString());
		}
		for (int i = 0; i < intArray.length; i++) {
			// 遍历打印int[],察看运行结果.
			if (intArray[i] == 1) {
				type1 = true;
			}
			if (intArray[i] == 2) {
				type2 = true;
			}
			if (intArray[i] == 3) {
				type3 = true;
			}
			if (intArray[i] == 4) {
				type4 = true;
			}
			if (intArray[i] == 5) {
				type5 = true;
			}
			if (intArray[i] == 6) {
				type6 = true;
			}
			if (intArray[i] == 7) {
				type7 = true;
			}
			if (intArray[i] == 8) {
				type8 = true;
			}
//			if (intArray[i] == 10) {
//				type10 = true;
//			}
//			if (intArray[i] == 11) {
//				type11 = true;
//			}
			// System.err.print(intArray[i] + " ");
		}

		if (type1) {
			// 1:公安
			returnCom += "01";
		}
		if (type2) {
			// 2:医院
			returnCom += StringUtils.isEmpty(returnCom) ? "02" : ",02";
		}
		if (type3) {
			// 3:消防
			returnCom += StringUtils.isEmpty(returnCom) ? "03" : ",03";
		}
		if (type4) {
			// 4:图书馆
			returnCom += StringUtils.isEmpty(returnCom) ? "04" : ",04";
		}
		if (type5) {
			// 5:体育馆
			returnCom += StringUtils.isEmpty(returnCom) ? "05" : ",05";
		}
		if (type6) {
			// 6:博物馆
			returnCom += StringUtils.isEmpty(returnCom) ? "06" : ",06";
		}
		if (type7) {
			// 7:供水
			returnCom += StringUtils.isEmpty(returnCom) ? "07" : ",07";
		}
		if (type8) {
			// 8:供电
			returnCom += StringUtils.isEmpty(returnCom) ? "08" : ",08";
		}
//		if (type10) {
//			// 10：警务室，
//			returnCom += StringUtils.isEmpty(returnCom) ? "10" : ",10";
//		}
//
//		if (type11) {
//			//11：社区(村)工作站
//			returnCom += StringUtils.isEmpty(returnCom) ? "11" : ",11";
//		}
		return returnCom;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public static CcmPeople ReturnPeoType(String type) {
		String SqlSelect = "";
		CcmPeople ccmPeopleDto = new CcmPeople();
		// 判断当前是否位数字
		if (!StringUtils.isNumeric(type)) {
			// 如果不是数字 返回为空 默认为情况不会出现该情况
			return new CcmPeople();
		}
		int comType = Integer.parseInt(type);
		if (comType >= 4194304 || comType < 1) {
			return new CcmPeople();
		}
		boolean type1 = (comType % 2) == 1 ? true : false;
		boolean type2 = (comType % 4 / 2) == 1 ? true : false;
		boolean type3 = (comType % 8 / 4) == 1 ? true : false;
		boolean type4 = (comType % 16 / 8) == 1 ? true : false;
		boolean type5 = (comType % 32 / 16) == 1 ? true : false;
		boolean type6 = (comType % 64 / 32) == 1 ? true : false;
		boolean type7 = (comType % 128 / 64) == 1 ? true : false;
		boolean type8 = (comType % 256 / 128) == 1 ? true : false;
		boolean type9 = (comType % 512 / 256) == 1 ? true : false;
		boolean type10 = (comType % 1024 / 512) == 1 ? true : false;
		boolean type11 = (comType % 2048 / 1024) == 1 ? true : false;
		boolean type12 = (comType % 4096 / 2048) == 1 ? true : false;
		boolean type13 = (comType % 8192 / 4096) == 1 ? true : false;
		boolean type14 = (comType % 16384 / 8192) == 1 ? true : false;
		boolean type15 = (comType % 32768 / 16384) == 1 ? true : false;
		boolean type16 = (comType % 65536 / 32768) == 1 ? true : false;
		boolean type17 = (comType % 131072 / 65536) == 1 ? true : false;
		boolean type18 = (comType % 262144 / 131072) == 1 ? true : false;
		boolean type19 = (comType % 524288 / 262144) == 1 ? true : false;
		boolean type20 = (comType % 1048576 / 524288) == 1 ? true : false;
		boolean type21 = (comType % 2097152 / 1048576) == 1 ? true : false;
		boolean type22 = (comType % 4194304 / 2097152) == 1 ? true : false;


		
		// 1.艾滋病患者
		if (type1) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_aids = 1 " : "or is_aids = 1 ";
		}
		// 2.肇事肇祸等严重精神障碍患者
		if (type2) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_psychogeny = 1 " : "or is_psychogeny = 1 ";
		}
		// 3.重点青少年
		if (type3) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_kym = 1 " : "or is_kym = 1 ";
		}
		// 4.吸毒人员
		if (type4) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_drugs = 1 " : "or is_drugs = 1 ";
		}
		// 5.安置帮教人员
		if (type5) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_release = 1 " : "or is_release = 1 ";
		}
		// 6.社区矫正人员
		if (type6) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_rectification = 1 " : "or is_rectification = 1 ";
		}
		// 7.留守人员
		if (type7) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_behind = 1 " : "or is_behind = 1 ";
		}
		// 8.重点上访人员
		if (type8) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_visit = 1 " : "or is_visit = 1 ";
		}
		// 9.是否涉教人员
		if (type9) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_heresy = 1 " : "or is_heresy = 1 ";
		}
		// 10.是否危险品工作人员
		if (type10) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_dangerous = 1 " : "or is_dangerous = 1 ";
		}
		// 11.是否老年人
		if (type11) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.birthday < DATE_SUB(curdate(), INTERVAL 65 YEAR) " : "or a.birthday < DATE_SUB(curdate(), INTERVAL 65 YEAR) ";
		}
		// 12.低保人员
		if (type12) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','a','%') " : "or a.special_care_type LIKE concat('%','a','%') ";
		}
		// 13.残疾人员
		if (type13) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','b','%') " : "or a.special_care_type LIKE concat('%','b','%') ";
		}
		// 14.退伍人员
		if (type14) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','c','%') " : "or a.special_care_type LIKE concat('%','c','%') ";
		}
		// 15.优抚对象
		if (type15) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','d','%') " : "or a.special_care_type LIKE concat('%','d','%') ";
		}
		// 16.现役军人
		if (type16) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','e','%') " : "or a.special_care_type LIKE concat('%','e','%') ";
		}
		// 17.烈士家属
		if (type17) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','f','%') " : "or a.special_care_type LIKE concat('%','f','%') ";
		}
		// 18.失业人员
		if (type18) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','g','%') " : "or a.special_care_type LIKE concat('%','g','%') ";
		}
		// 19.孤儿
		if (type19) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','h','%') " : "or a.special_care_type LIKE concat('%','h','%') ";
		}
		// 20.救助对象
		if (type20) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','i','%') " : "or a.special_care_type LIKE concat('%','i','%') ";
		}
		// 21.计生重点
		if (type21) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','j','%') " : "or a.special_care_type LIKE concat('%','j','%') ";
		}
		// 22.失独
		if (type22) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','k','%') " : "or a.special_care_type LIKE concat('%','k','%') ";
		}
		
		ccmPeopleDto.setMore1(SqlSelect);
		return ccmPeopleDto;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static CcmPeople ReturnPeoTypeMore(String type) {
		String SqlSelect = "";
		CcmPeople ccmPeopleDto = new CcmPeople();
		
		boolean type1 = false;
		boolean type2 = false;
		boolean type3 = false;
		boolean type4 = false;
		boolean type5 = false;
		boolean type6 = false;
		boolean type7 = false;
		boolean type8 = false;
		boolean type9 = false;
		boolean type10 = false;
		boolean type11 = false;
		boolean type12 = false;
		boolean type13 = false;
		boolean type14 = false;
		boolean type15 = false;
		boolean type16 = false;
		boolean type17 = false;
		boolean type18 = false;
		boolean type19 = false;
		boolean type20 = false;
		boolean type21 = false;
		boolean type22 = false;

		if(type==null || "".equals(type)){
			type = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22";
		}
		
		String[] f = type.split(",");
		for(int i=0;i<f.length;i++){
			if("1".equals(f[i])){ type1 = true; }
			if("2".equals(f[i])){ type2 = true; }
			if("3".equals(f[i])){ type3 = true; }
			if("4".equals(f[i])){ type4 = true; }
			if("5".equals(f[i])){ type5 = true; }
			if("6".equals(f[i])){ type6 = true; }
			if("7".equals(f[i])){ type7 = true; }
			if("8".equals(f[i])){ type8 = true; }
			if("9".equals(f[i])){ type9 = true; }
			if("10".equals(f[i])){ type10 = true; }
			if("11".equals(f[i])){ type11 = true; }
			if("12".equals(f[i])){ type12 = true; }
			if("13".equals(f[i])){ type13 = true; }
			if("14".equals(f[i])){ type14 = true; }
			if("15".equals(f[i])){ type15 = true; }
			if("16".equals(f[i])){ type16 = true; }
			if("17".equals(f[i])){ type17 = true; }
			if("18".equals(f[i])){ type18 = true; }
			if("19".equals(f[i])){ type19 = true; }
			if("20".equals(f[i])){ type20 = true; }
			if("21".equals(f[i])){ type21 = true; }
			if("22".equals(f[i])){ type22 = true; }
		}
		
		
		// 1.艾滋病患者
		if (type1) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_aids = 1 " : "or is_aids = 1 ";
		}
		// 2.肇事肇祸等严重精神障碍患者
		if (type2) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_psychogeny = 1 " : "or is_psychogeny = 1 ";
		}
		// 3.重点青少年
		if (type3) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_kym = 1 " : "or is_kym = 1 ";
		}
		// 4.吸毒人员
		if (type4) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_drugs = 1 " : "or is_drugs = 1 ";
		}
		// 5.安置帮教人员
		if (type5) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_release = 1 " : "or is_release = 1 ";
		}
		// 6.社区矫正人员
		if (type6) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_rectification = 1 " : "or is_rectification = 1 ";
		}
		// 7.留守人员
		if (type7) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_behind = 1 " : "or is_behind = 1 ";
		}
		// 8.重点上访人员
		if (type8) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_visit = 1 " : "or is_visit = 1 ";
		}
		// 9.是否涉教人员
		if (type9) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_heresy = 1 " : "or is_heresy = 1 ";
		}
		// 10.是否危险品工作人员
		if (type10) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "is_dangerous = 1 " : "or is_dangerous = 1 ";
		}
		// 11.是否老年人
		if (type11) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.birthday < DATE_SUB(curdate(), INTERVAL 65 YEAR) " : "or a.birthday < DATE_SUB(curdate(), INTERVAL 65 YEAR) ";
		}
		// 12.低保人员
		if (type12) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','a','%') " : "or a.special_care_type LIKE concat('%','a','%') ";
		}
		// 13.残疾人员
		if (type13) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','b','%') " : "or a.special_care_type LIKE concat('%','b','%') ";
		}
		// 14.退伍人员
		if (type14) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','c','%') " : "or a.special_care_type LIKE concat('%','c','%') ";
		}
		// 15.优抚对象
		if (type15) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','d','%') " : "or a.special_care_type LIKE concat('%','d','%') ";
		}
		// 16.现役军人
		if (type16) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','e','%') " : "or a.special_care_type LIKE concat('%','e','%') ";
		}
		// 17.烈士家属
		if (type17) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','f','%') " : "or a.special_care_type LIKE concat('%','f','%') ";
		}
		// 18.失业人员
		if (type18) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','g','%') " : "or a.special_care_type LIKE concat('%','g','%') ";
		}
		// 19.孤儿
		if (type19) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','h','%') " : "or a.special_care_type LIKE concat('%','h','%') ";
		}
		// 20.救助对象
		if (type20) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','i','%') " : "or a.special_care_type LIKE concat('%','i','%') ";
		}
		// 21.计生重点
		if (type21) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','j','%') " : "or a.special_care_type LIKE concat('%','j','%') ";
		}
		// 22.失独
		if (type22) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.special_care_type LIKE concat('%','k','%') " : "or a.special_care_type LIKE concat('%','k','%') ";
		}
		
		ccmPeopleDto.setMore1(SqlSelect);
		return ccmPeopleDto;
	}
	
	/**
	 * 
	 * @param typeEvery
	 * @return
	 */
	public static SearchTabMore ReturnPeoTypeMoreEvery(String type) {
		SearchTabMore searchTabMore = new SearchTabMore();
		if(type==null || "".equals(type)){
			type = "1,2,3,4,5,6,7,8,9,10";
		}
		
		String[] f = type.split(",");
		for(int i=0;i<f.length;i++){
			if("1".equals(f[i])){ searchTabMore.setValue1("1"); }		// 1.艾滋病患者
			if("2".equals(f[i])){ searchTabMore.setValue2("1"); }		// 2.肇事肇祸等严重精神障碍患者
			if("3".equals(f[i])){ searchTabMore.setValue3("1"); }		// 3.重点青少年
			if("4".equals(f[i])){ searchTabMore.setValue4("1"); }		// 4.吸毒人员
			if("5".equals(f[i])){ searchTabMore.setValue5("1"); }		// 5.安置帮教人员
			if("6".equals(f[i])){ searchTabMore.setValue6("1"); }		// 6.社区矫正人员
			if("7".equals(f[i])){ searchTabMore.setValue7("1"); }		// 7.留守人员
			if("8".equals(f[i])){ searchTabMore.setValue8("1"); }		// 8.重点上访人员
			if("9".equals(f[i])){ searchTabMore.setValue9("1"); }		// 9.是否涉教人员
			if("10".equals(f[i])){ searchTabMore.setValue10("1"); }		// 10.是否危险品工作人员
		}
		
		return searchTabMore;
	}
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static CcmPeople ReturnPeoTypeUNION(String type) {
		String SqlSelect = "";
		CcmPeople ccmPeopleDto = new CcmPeople();
		// 判断当前是否位数字
		if (!StringUtils.isNumeric(type)) {
			// 如果不是数字 返回为空 默认为情况不会出现该情况
			return new CcmPeople();
		}
		int comType = Integer.parseInt(type);
		if (comType >= 1024 || comType < 1) {
			return new CcmPeople();
		}
		boolean type1 = (comType % 2) == 1 ? true : false;
		boolean type2 = (comType % 4 / 2) == 1 ? true : false;
		boolean type3 = (comType % 8 / 4) == 1 ? true : false;
		boolean type4 = (comType % 16 / 8) == 1 ? true : false;
		boolean type5 = (comType % 32 / 16) == 1 ? true : false;
		boolean type6 = (comType % 64 / 32) == 1 ? true : false;
		boolean type7 = (comType % 128 / 64) == 1 ? true : false;
		boolean type8 = (comType % 256 / 128) == 1 ? true : false;
		boolean type9 = (comType % 512 / 256) == 1 ? true : false;
		boolean type10 = (comType % 1024 / 512) == 1 ? true : false;

		String s1 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_aids =1 GROUP BY build_id  ";
		String u1 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_aids =1 GROUP BY build_id  ";
		String s2 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_psychogeny =1 GROUP BY build_id  ";
		String u2 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_psychogeny =1 GROUP BY build_id  ";
		String s3 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_kym =1 GROUP BY build_id  ";
		String u3 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_kym =1 GROUP BY build_id  ";
		String s4 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_drugs =1 GROUP BY build_id  ";
		String u4 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_drugs =1 GROUP BY build_id  ";
		String s5 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_release =1 GROUP BY build_id  ";
		String u5 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_release =1 GROUP BY build_id  ";
		String s6 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_rectification =1 GROUP BY build_id  ";
		String u6 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_rectification =1 GROUP BY build_id  ";
		String s7 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_behind =1 GROUP BY build_id  ";
		String u7 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_behind =1 GROUP BY build_id  ";
		String s8 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_visit =1 GROUP BY build_id  ";
		String u8 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_visit =1 GROUP BY build_id  ";
		String s9 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_heresy =1 GROUP BY build_id  ";
		String u9 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_heresy =1 GROUP BY build_id  ";
		String s10 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_dangerous =1 GROUP BY build_id  ";
		String u10 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_dangerous =1 GROUP BY build_id  ";
		// 1.艾滋病患者
		if (type1) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s1 : u1;
		}
		// 2.肇事肇祸等严重精神障碍患者
		if (type2) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s2 : u2;
		}
		// 3.重点青少年
		if (type3) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s3 : u3;
		}
		// 4.吸毒人员
		if (type4) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s4 : u4;
		}
		// 5.安置帮教人员
		if (type5) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s5 : u5;
		}
		// 6.社区矫正人员
		if (type6) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s6 : u6;
		}
		// 7.留守人员
		if (type7) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s7 : u7;
		}
		// 8.重点上访人员
		if (type8) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s8 : u8;
		}
		// 9.是否涉教人员
		if (type9) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s9 : u9;
		}
		// 10.是否危险品工作人员
		if (type10) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? s10 : u10;
		}
		ccmPeopleDto.setMore1(SqlSelect);
		return ccmPeopleDto;
	}
	
	/**
	 * 
	 * @param flag
	 * @return
	 */
	public static CcmPeople ReturnPeoTypeFlag(String flag) {
		String SqlSelect = "";
		CcmPeople ccmPeopleDto = new CcmPeople();
		
		String s1 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_aids =1 GROUP BY build_id  ";
		String u1 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_aids =1 GROUP BY build_id  ";
		String s2 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_psychogeny =1 GROUP BY build_id  ";
		String u2 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_psychogeny =1 GROUP BY build_id  ";
		String s3 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_kym =1 GROUP BY build_id  ";
		String u3 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_kym =1 GROUP BY build_id  ";
		String s4 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_drugs =1 GROUP BY build_id  ";
		String u4 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_drugs =1 GROUP BY build_id  ";
		String s5 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_release =1 GROUP BY build_id  ";
		String u5 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_release =1 GROUP BY build_id  ";
		String s6 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_rectification =1 GROUP BY build_id  ";
		String u6 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_rectification =1 GROUP BY build_id  ";
		String s7 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_behind =1 GROUP BY build_id  ";
		String u7 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_behind =1 GROUP BY build_id  ";
		String s8 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_visit =1 GROUP BY build_id  ";
		String u8 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_visit =1 GROUP BY build_id  ";
		String s9 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_heresy =1 GROUP BY build_id  ";
		String u9 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_heresy =1 GROUP BY build_id  ";
		String s10 = "SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_dangerous =1 GROUP BY build_id  ";
		String u10 = "UNION SELECT build_id from ccm_people where del_flag = 0 AND build_id is not null AND build_id != '' and is_dangerous =1 GROUP BY build_id  ";
		
		
		String[] f = flag.split(",");
		for(int i=0;i<f.length;i++){
			if("1".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s1 : u1;
			}
			if("2".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s2 : u2;
			}
			if("3".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s3 : u3;
			}
			if("4".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s4 : u4;
			}
			if("5".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s5 : u5;
			}
			if("6".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s6 : u6;
			}
			if("7".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s7 : u7;
			}
			if("8".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s8 : u8;
			}
			if("9".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s9 : u9;
			}
			if("10".equals(f[i])){
				SqlSelect += StringUtils.isEmpty(SqlSelect) ? s10 : u10;
			}
		}
		ccmPeopleDto.setMore1(SqlSelect);
		return ccmPeopleDto;
	}
	
	

	@SuppressWarnings("unchecked")
	public static Map<String, Object> GetRoomPeoMap(List<CcmPeople> PeopleList) {
		Map<String, Object> ResultMap = new HashMap<String, Object>();
		// 循环 人口List
		for (CcmPeople ccmPeople : PeopleList) {
			if (null == ccmPeople.getRoomId()) {
				continue;
			}
			// 当前是否含有人口的list
			if (!ResultMap.containsKey(ccmPeople.getRoomId().getId())) {
				// 当前的list
				List<CcmPeople> peoples = new ArrayList<>();
				peoples.add(ccmPeople);
				// 民族 与户主关系 进行字典替换 sys_volk
				ccmPeople.setNation(DictUtils.getDictLabel(ccmPeople.getNation(), "sys_volk", ""));
				ccmPeople.setAccountrelation(
						DictUtils.getDictLabel(ccmPeople.getAccountrelation(), "sys_ccm_fami_ties", ""));
				// 当前Map
				ResultMap.put(ccmPeople.getRoomId().getId(), peoples);
			} else {
				// 强行转换
				List<CcmPeople> list = (List<CcmPeople>) ResultMap.get(ccmPeople.getRoomId().getId());
				list.add(ccmPeople);
				ccmPeople.setNation(DictUtils.getDictLabel(ccmPeople.getNation(), "sys_volk", ""));
				ccmPeople.setAccountrelation(
						DictUtils.getDictLabel(ccmPeople.getAccountrelation(), "sys_ccm_fami_ties", ""));
				// 更新list后，再次更新Map
				ResultMap.put(ccmPeople.getRoomId() + "", list);
			}
		}

		return ResultMap;
	}

	/**
	 * @see 返回重点人员类型
	 * @param peopleR
	 * @return
	 */
	public static String ReturnSpecialString(CcmPeople peopleR) {
		if (null == peopleR) {
			return "";
		}
		String specailP = "";

		// getIsAids
		if (StringUtils.isNumeric(peopleR.getIsAids() + "") && (peopleR.getIsAids() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "艾滋病患者" : ",艾滋病患者";
		}
		// getIsBehind
		if (StringUtils.isNumeric(peopleR.getIsBehind() + "") && (peopleR.getIsBehind() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "留守人员" : ",留守人员";
		}
		// getIsRelease
		if (StringUtils.isNumeric(peopleR.getIsRelease() + "") && (peopleR.getIsRelease() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "安置帮教人员" : ",安置帮教人员";
		}
		// getIsRectification
		if (StringUtils.isNumeric(peopleR.getIsRectification() + "") && (peopleR.getIsRectification() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "社区矫正人员" : ",社区矫正人员";
		}
		// getIsPsychogeny
		if (StringUtils.isNumeric(peopleR.getIsPsychogeny() + "") && (peopleR.getIsPsychogeny() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "肇事肇祸等严重精神障碍患者" : ",肇事肇祸等严重精神障碍患者";
		}
		// getIsKym
		if (StringUtils.isNumeric(peopleR.getIsKym() + "") && (peopleR.getIsKym() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "重点青少年" : ",重点青少年";
		}
		// getIsDrugs
		if (StringUtils.isNumeric(peopleR.getIsDrugs() + "") && (peopleR.getIsDrugs() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "吸毒人员" : ",吸毒人员";
		}
		// getIsVisit
		if (StringUtils.isNumeric(peopleR.getIsVisit() + "") && (peopleR.getIsVisit() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "重点上访人员" : ",重点上访人员";
		}
		// getIsHeresy
		if (StringUtils.isNumeric(peopleR.getIsHeresy() + "") && (peopleR.getIsHeresy() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "涉教人员" : ",涉教人员";
		}
		// getIsDangerous
		if (StringUtils.isNumeric(peopleR.getIsDangerous() + "") && (peopleR.getIsDangerous() == 1)) {
			specailP += (StringUtils.isEmpty(specailP)) ? "危险品从业人员" : ",危险品从业人员";
		}
		
		//老年人
		Date birthday = peopleR.getBirthday();
		birthday.setYear(birthday.getYear()+65);
		Date now = new Date();
		if(birthday.getTime()<now.getTime()){
			specailP += (StringUtils.isEmpty(specailP)) ? "老年人" : ",老年人";
		}
		
		//特殊关怀
		String specialCareType = peopleR.getSpecialCareType();
		if(specialCareType!=null && !"".equals(specialCareType)){
			String[] f = specialCareType.split(",");
			for(int i=0;i<f.length;i++){
				// getIsAids
				if ("a".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "低保人员" : ",低保人员"; }
				if ("b".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "残疾人员" : ",残疾人员"; }
				if ("c".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "退伍军人" : ",退伍军人"; }
				if ("d".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "优抚对象" : ",优抚对象"; }
				if ("e".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "现役军人" : ",现役军人"; }
				if ("f".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "烈士家属" : ",烈士家属"; }
				if ("g".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "失业人员" : ",失业人员"; }
				if ("h".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "孤儿" : ",孤儿"; }
				if ("i".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "救助对象" : ",救助对象"; }
				//if ("j".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "计生重点" : ",计生重点"; }
				if ("k".equals(f[i])) { specailP += (StringUtils.isEmpty(specailP)) ? "失独" : ",失独"; }
			}
		}
		
		
		
		return specailP;
	}

	/**
	 * 返回一周的长度
	 * 
	 * @return
	 */
	public static List<EchartType> dateToWeek() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long fTime = new Date().getTime();
		List<EchartType> list = new ArrayList<>();
		for (int a = 1; a <= 7; a++) {
			Date fdate = new Date();
			fdate.setTime(fTime - ((a - 1) * 24 * 3600000)); // 一周从周一开始算，则使用此方式
			EchartType echartType = new EchartType();
			echartType.setType(sdf.format(fdate));
			list.add(a - 1, echartType);
		}
		// 反转
		Collections.reverse(list);
		return list;
	}

	/**
	 * 方法名称：将传入字符串的中文转换编码
	 * 
	 * @param str
	 *            encode
	 * @return String
	 * @throws Exception
	 *             作者和时间：pengjianqiang 2018-02-24
	 */
	public static String encodeURL(String str, String encode) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		StringBuilder noAsciiPart = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c > 255) {
				noAsciiPart.append(c);
			} else {
				if (noAsciiPart.length() != 0) {
					sb.append(URLEncoder.encode(noAsciiPart.toString(), encode));
					noAsciiPart.delete(0, noAsciiPart.length());
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * @ 返回 楼栋建筑物 信息 通过 人查询
	 * @param type ：当前1 ： 只查询 含重点人员 2：不含人员 不在这里面 添加查询相关的方法 3 ： 查询 相关的 出租和 重点人员
	 *  楼栋查询方法
	 * @param table2 ：查询的出租房表名
	 * @return
	 */
	public static CcmPeople ReturnMapBuildType(String type, String table2) {

		// 返回查询重点人员 相关
		CcmPeople ccmPeopleDto = ReturnPeoType("127");
		if ("1".equals(type)) {
			return ccmPeopleDto;
		} else {
			// 默认当前的 查询CcmPeople 方法 应为 含有 出租房与 重点人员 查询对象信息
			String more2 = "";
			more2 += " or  " + table2 + ".house_prup= \"02\"";
			ccmPeopleDto.setMore2(more2);
			return ccmPeopleDto;
		}
	}

	/**
	 * @see 城市部件
	 * @param type
	 * @return 城市部件查询SQL
	 */
	public static CcmCityComponents ReturnCityComponentsType(String type) {
		String SqlSelect = "";
		CcmCityComponents ccmCityComponents = new CcmCityComponents();
		// 判断当前是否位数字
		if (StringUtils.isEmpty(type)) {
			// 如果不是数字 返回为空 默认为情况不会出现该情况
			return ccmCityComponents;
		}

		// 1.井盖
		if (type.contains("a")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =01" : " or a.type =01";
		}
		// 2.交通信号灯
		if (type.contains("b")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =02" : " or a.type =02";
		}
		// 3.交通标志牌
		if (type.contains("c")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =03" : " or a.type =03";
		}
		// 4.停车场
		if (type.contains("d")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =04" : " or a.type =04";
		}
		// 5.健身设施
		if (type.contains("e")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =05" : " or a.type =05";
		}
		// 6.公交站亭
		if (type.contains("f")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =06" : " or a.type =06";
		}
		// 7.垃圾箱
		if (type.contains("g")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =07" : " or a.type =07";
		}
		// 8.存车支架
		if (type.contains("h")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =08" : " or a.type =08";
		}
		// 9.报刊亭
		if (type.contains("i")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =09" : " or a.type =09";
		}
		// 10.景观灯
		if (type.contains("j")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =10" : " or a.type =10";
		}
		// 11.电力设施
		if (type.contains("k")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =11" : " or a.type =11";
		}
		// 12.监控电子眼
		if (type.contains("l")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =12" : " or a.type =12";
		}
		// 13.行道树
		if (type.contains("m")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =13" : " or a.type =13";
		}
		// 14.路灯
		if (type.contains("n")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =14" : " or a.type =14";
		}
		// 15.邮筒
		if (type.contains("o")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =15" : " or a.type =15";
		}
		// 16.消防栓
		if (type.contains("p")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =16" : " or a.type =16";
		}
		// 17.消防水源
		if (type.contains("q")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =17" : " or a.type =17";
		}
		// 18.急避难场所
		if (type.contains("r")) {
			SqlSelect += StringUtils.isEmpty(SqlSelect) ? "a.type =18" : " or a.type =18";
		}
		ccmCityComponents.setMore1(SqlSelect);
		return ccmCityComponents;
	}
	/** 
	* 获得指定日期的前一天 
	* @param specifiedDay 
	* @return 
	* @throws Exception 
	*/ 
	public static String getSpecifiedDayBefore(String specifiedDay) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}

	/**
	 * 弹出层
	 * 
	 * @param out
	 * @param num
	 * @param msg
	 */
	public static void openWinExpDiv(PrintWriter out, String msg) {
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.alertInfo('" + msg
				+ "');</script>");
//		out.println("<script language='javascript'>parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
		out.println("<script language='javascript'>if (!window.location.hash){parent.document.getElementById('mainFrame').contentWindow.location.href = parent.document.getElementById('mainFrame').contentWindow.location.href;} else {parent.document.getElementById('mainFrame').contentWindow.location.reload(true);}</script>");

	}
	/**
	 * 弹出层并执行方法
	 * 
	 * @param out
	 * @param function方法名
	 * @param type方法名参
	 * @param msg
	 */
	public static void openWinExpDivFunction(PrintWriter out, String msg,String function,String type) {
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.alertInfo('" + msg+ "');</script>");
		out.println("<script language='javascript'>parent."+function+"("+type+");</script>");
	}
	
	/**
	 * 弹出层验证失败
	 * 
	 * @param out
	 * 
	 * @param msg
	 */
	public static void openWinExpDivValidator(PrintWriter out, String msg) {
		
		out.println("<script language='javascript'>parent.alertInfo('" + msg+ "');</script>");
		
	}
	/**
	 * zhanghao
	 * @param 求两经纬度之间距离
	 * @return
	 */
	private static final double EARTH_RADIUS=6378.173;
	private static double rad(double d){
		return d*Math.PI/180.0;	
	}
	public static double getDistance(double lat1,double lng1,double lat2,double lng2) {
		double radLat1 =rad(lat1);
		double radLat2=rad(lat2);
		double a=radLat1-radLat2;
		double b=rad(lng1)-rad(lng2);
		double s=2*Math.asin(Math.sqrt(Math.pow(Math.sin(a/2), 2)
				+Math.cos(radLat1)*Math.cos(radLat2)
				*Math.pow(Math.sin(b/2), 2)));
		s=s*EARTH_RADIUS;
		s=Math.round(s*100000d)/100000d;
		s=s*1000;
		return s;
	}
	/**
	 * zhanghao
	 * @param String to point 
	 * @return
	 */
	public static Point toPoint(String areaPoint) {
		String[] p1=areaPoint.split(",");
		double x= 0;
		double y= 0;
		if(!p1[0].isEmpty()){
			x = Double.parseDouble(p1[0]);
		}
		if(!p1[1].isEmpty()){
			y = Double.parseDouble(p1[1]);
		}
		Point sPoint= new Point(x, y);
		return sPoint;
		
	}
	
	/**
     * 根据JSONArray String获取到List
     * @param <T>
     * @param <T>
     * @param jArrayStr
     * @return
     */
    public static <T> List<T> getListByArray(Class<T> class1,String jArrayStr) {
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jArrayStr);
        if (jsonArray==null || jsonArray.isEmpty()) {
            return list;//nerver return null
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            T t = JSONObject.toJavaObject(jsonObject, class1);
            list.add(t);
        }
        return list;
    }

	/**
	 * @see 返回 民族键值
	 * @param nationName 民族名称
	 * @return  民族值
	 */
	public static String getNationValue(String nationName) {
		if (StringUtils.isEmpty(nationName)) {
			return "";
		}
		switch (nationName) {
		case "汉族": return "01";
		case "蒙古族": return "02";
		case "回族": return "03";
		case "藏族": return "04";
		case "维吾尔族": return "05";
		case "苗族": return "06";
		case "彝族": return "07";
		case "壮族": return "08";
		case "布依族": return "09";
		case "朝鲜族": return "10";
		case "满族": return "11";
		case "侗族": return "12";
		case "瑶族": return "13";
		case "白族": return "14";
		case "土家族": return "15";
		case "哈尼族": return "16";
		case "哈萨克族": return "17";
		case "傣族": return "18";
		case "黎族": return "19";
		case "傈僳族": return "20";
		case "佤族": return "21";
		case "畲族": return "22";
		case "高山族": return "23";
		case "拉祜族": return "24";
		case "水族": return "25";
		case "东乡族": return "26";
		case "纳西族": return "27";
		case "景颇族": return "28";
		case "柯尔克孜族": return "29";
		case "土族": return "30";
		case "达斡尔族": return "31";
		case "仫佬族": return "32";
		case "羌族": return "33";
		case "布朗族": return "34";
		case "撒拉族": return "35";
		case "毛难族": return "36";
		case "仡佬族": return "37";
		case "锡伯族": return "38";
		case "阿昌族": return "39";
		case "普米族": return "40";
		case "塔吉克族": return "41";
		case "怒族": return "42";
		case "乌孜别克族": return "43";
		case "俄罗斯族": return "44";
		case "鄂温克族": return "45";
		case "保安族": return "47";
		case "裕固族": return "48";
		case "京族": return "49";
		case "塔塔尔族": return "50";
		case "独龙族": return "51";
		case "鄂伦春族": return "52";
		case "赫哲族": return "53";
		case "门巴族": return "54";
		case "珞巴族": return "55";
		case "基诺族": return "56";
		case "德昂族": return "57";
		case "其他": return "58";
		default:
			return "58";
		}
	}

	/**
	 * @see 返回 宗教信仰键值
	 * @param beliefName 宗教信仰名称
	 * @return  宗教信仰值
	 */
	public static String getBeliefValue(String beliefName) {
		if (StringUtils.isEmpty(beliefName)) {
			return "";
		}
		switch (beliefName) {
		case "无宗教信仰": return "1";
		case "佛教": return "2";
		case "喇嘛教": return "3";
		case "道教": return "4";
		case "天主教": return "5";
		case "基督教": return "6";
		case "东正教": return "7";
		case "伊斯兰教": return "8";
		default:
			return "9";
		}
	}
	
	/**
	 * 判断字符串是否是数字组成
	 * @param str
	 * @return 都是数字组成：true
	 */
	public static boolean isNumeric(String str)	{
		if (str == null) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
		    	return false;
		   	}
		}
		return true;
	}
    
	public static Date getBirthDay(String ident) throws ParseException {
		String birth = "";
		if (ident.length() == 15) {
			birth = "19" + ident.substring(6, 12);
		} else if (ident.length() == 18) {
			birth = ident.substring(6, 14) ;
		} else {
			birth = ident.substring(6, 14) ;
		}
		String birthday = birth.substring(0, 4)+"-"+birth.substring(4, 6)+"-"+birth.substring(6, 8);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date birthDate = sdf.parse(birthday);
		return birthDate;
	}
	
	public static Date parse(String strDate) throws ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        return sdf.parse(strDate);  
    }
	
	public static int getAge(Date birthDay) throws Exception {  
		int yearBirth = Integer.parseInt(String.format("%tY", birthDay));
		int monBirth = Integer.parseInt(String .format("%tm", birthDay));
		int dayBirth = Integer.parseInt(String .format("%td", birthDay));  
		Date nowDate = new Date();
		int yearNow = Integer.parseInt(String.format("%tY", nowDate));
		int monNow = Integer.parseInt(String .format("%tm", nowDate));
		int dayNow = Integer.parseInt(String .format("%td", nowDate));
		LocalDate date1 = LocalDate.of(yearNow, monNow, dayNow);
	    LocalDate date2 = LocalDate.of(yearBirth, monBirth, dayBirth);
	    int age = date2.until(date1).getYears();
        return age;  
    }
	
	public static int getAgeByBirth(Date birthDay) throws ParseException {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);  //当前年份
		int monthNow = cal.get(Calendar.MONTH);  //当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		age = yearNow - yearBirth;   //计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
			} else {
				age--;//当前月份在生日之前，年龄减一
			}
		}
		return age;
	}
}
