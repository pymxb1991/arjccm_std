package com.arjjs.ccm.modules.pbs.common;

import java.util.Date;
import java.util.List;

import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;
import com.arjjs.ccm.modules.pbs.apply.service.PbsApplyrecService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowsetstat;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowsetstatService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsRemindMsg;
import com.arjjs.ccm.modules.pbs.sys.service.PbsRemindMsgService;

public class FlowServiceUtil {
	// 状态设置 Service
	private static PbsFlowsetstatService flowsetstatService = SpringContextHolder.getBean(PbsFlowsetstatService.class);
	// 添加 组织-党员关系 Service
	private static PbsDepartmentbindService departmentbindService = SpringContextHolder
			.getBean(PbsDepartmentbindService.class);
	// 申请记录 Service
	private static PbsApplyrecService pbsApplyrecService = SpringContextHolder.getBean(PbsApplyrecService.class);
	// 通知信息
	private static PbsRemindMsgService pbsRemindMsgService = SpringContextHolder.getBean(PbsRemindMsgService.class);

	// 完成申请
	private static final String flowTypePASS = "100";
	// 驳回申请
	private static final String flowTypeREFUSE = "-99";
	// 添加组织
	private static final String FLOWJOINOFC = "JoinOfc";
	// 脱离组织
	private static final String FLOWDRAGOFC = "DragOfc";
	// 流程申请
	private static final String PBSAPPLYREC = "pbs_applyrec";

	/**
	 * 通过后的方法
	 * 
	 * @param Actiontype
	 * @param id
	 */
	public static void rountPass(String Actiontype, String id) {
		// 申请记录
		if ((FLOWJOINOFC).equals(Actiontype)) {
			JoinOfc(true, id);
		}
		if ((FLOWDRAGOFC).equals(Actiontype)) {
			DragOfc(true, id);
		}
	}

	/**
	 * 被驳回的方法
	 * 
	 * @param Actiontype
	 * @param id
	 */
	public static void rountRefuse(String Actiontype, String id) {
		// 申请记录
		if ((FLOWJOINOFC).equals(Actiontype)) {
			JoinOfc(false, id);
		}
		if ((FLOWDRAGOFC).equals(Actiontype)) {
			DragOfc(false, id);
		}
	}

	/**
	 * 添加通知
	 * 
	 * @param type
	 *            模块类型
	 * @param id
	 *            业务ID
	 * @param memid
	 *            党员用户id
	 */
	public static void Notice(String type, String Dateid, String memid) {
		PbsRemindMsg msg = new PbsRemindMsg();
		msg.setSFuncionid(type);
		msg.setSDataid(Dateid);
		msg.setsAcceptorid(new PbsPartymem(memid));
		// 默认系统发送
		msg.setsSenderid(new PbsPartymem("1"));
		msg.setSStat("0");
		pbsRemindMsgService.save(msg);
	}

	/**
	 * 读取通知
	 * 
	 * @param type
	 *            模块类型
	 * @param id
	 *            业务ID
	 * @param memid
	 *            党员用户id
	 */
	public static void ReadNotice(String type, String Dateid, String memid) {
		PbsRemindMsg msg = new PbsRemindMsg();
		msg.setSFuncionid(type);
		msg.setSDataid(Dateid);
		msg.setsAcceptorid(new PbsPartymem(memid));
		msg.setSStat("1");
		pbsRemindMsgService.save(msg);
	}

	// 加入组织 反馈
	public static void JoinOfc(boolean control, String id) {
		// 提交
		PbsFlowsetstat pbsFlowsetstat = new PbsFlowsetstat();
		pbsFlowsetstat.setKey(id);
		pbsFlowsetstat.setSCollection(PBSAPPLYREC);
		// 状态位
		pbsFlowsetstat.setSProperty("s_status");
		pbsFlowsetstat.setSSetval(control ? flowTypePASS : flowTypeREFUSE);
		flowsetstatService.updateOperation(pbsFlowsetstat);
		// 获取 当前 的 申请
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		// 申请
		PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
		pbsDepartmentbindDto.setSPartymemid(pbsApplyrec.getsBindmember().getId());
		pbsDepartmentbindDto.setSDepartmentid(pbsApplyrec.getsPartment().getId());
		// 如果通过了申请 则添加 相关组织
		List<PbsDepartmentbind> pbsDepartmentbinds = departmentbindService.findList(pbsDepartmentbindDto);
		// 当前没有
		if (control&&(pbsDepartmentbinds.size()==0)) {
			PbsDepartmentbind pbsDepartmentbind = new PbsDepartmentbind();
			pbsDepartmentbind.setSPartymemid(pbsApplyrec.getsBindmember().getId());
			pbsDepartmentbind.setSDepartmentid(pbsApplyrec.getsPartment().getId());
			pbsDepartmentbind.setDtPosttime(new Date());
			pbsDepartmentbind.setRemarks("提交组织转后进入" + DateUtils.formatDateTime(new Date()));
			departmentbindService.save(pbsDepartmentbind);
		}
	}

	// 脱离组织 反馈
	/**
	 * @param control
	 * @param id
	 */
	public static void DragOfc(boolean control, String id) {
		// 提交
		PbsFlowsetstat pbsFlowsetstat = new PbsFlowsetstat();
		pbsFlowsetstat.setKey(id);
		pbsFlowsetstat.setSCollection(PBSAPPLYREC);
		// 状态位
		pbsFlowsetstat.setSProperty("s_status");
		pbsFlowsetstat.setSSetval(control ? flowTypePASS : flowTypeREFUSE);
		flowsetstatService.updateOperation(pbsFlowsetstat);
		// 获取 当前 的 申请
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		// 如果通过了 则开始脱离组织的申请
		if (control) {
			PbsDepartmentbind pbsDepartmentbind = new PbsDepartmentbind();
			pbsDepartmentbind.setSPartymemid(pbsApplyrec.getsBindmember().getId());
			pbsDepartmentbind.setSDepartmentid(pbsApplyrec.getsPartment().getId());
			pbsDepartmentbind.setRemarks("提交组织脱离 时间：" + DateUtils.formatDateTime(new Date()));
			departmentbindService.deleteByRelationship(pbsDepartmentbind);
		}
	}

}
