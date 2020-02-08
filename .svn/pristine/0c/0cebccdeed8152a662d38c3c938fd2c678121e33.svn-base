package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogImpPopSign;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogImpPopSignService;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReport;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.CcmWorkReportService;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.tool.CommUtilRest;
import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.log.dao.CcmLogTailDao;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 跟踪日志接口类
 *
 * @author pengjianqiang
 * @version 2018-02-22
 */
@Controller
@RequestMapping(value = "${appPath}/rest/logTail")
public class CcmRestLogTail extends BaseController {

    @Autowired
    private CcmLogTailService ccmLogTailService;

    @Autowired
    private CcmLogTailDao ccmLogTailDao;

    @Autowired
    private CcmLogImpPopSignService ccmLogImpPopSignService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private CcmWorkReportService ccmWorkReportService;

    /**
     * @param id ID
     * @return
     * @author pengjianqiang
     * @version 2018-02-22
     * @see 获取单个信息
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public CcmRestResult get(String userId, HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
        CcmRestResult result = new CcmRestResult();
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (sessionUser == null) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        String sessionUserId = sessionUser.getId();
        if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        if (id == null || "".equals(id)) {//参数id不对
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }

        CcmLogTail ccmLogTail = ccmLogTailService.get(id);

        result.setCode(CcmRestType.OK);
        result.setResult(ccmLogTail);

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/msgRecord", method = RequestMethod.GET)
    public CcmRestResult query(CcmWorkReport ccmWorkReport, String userId, HttpServletRequest req, HttpServletResponse resp) {

        // 获取结果
        CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);

        Map map = Maps.newHashMap();

        CcmLogTail ccmLogTailDto = new CcmLogTail();
        if (StringUtils.isEmpty(ccmWorkReport.getId())) {
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }
        ccmWorkReport = ccmWorkReportService.getRecordList(ccmWorkReport);
        map.put("ccmWorkReport", ccmWorkReport);
        ccmLogTailDto.setRelevanceId(ccmWorkReport.getId());
        ccmLogTailDto.setRelevanceTable("ccm_sys_workreport");
        List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
        if (null != ccmLogTailList || 0 != ccmLogTailList.size()) {
            map.put("ccmLogTailList", ccmLogTailList);
        } else {
            map.put("ccmLogTailList", "");
        }

        result.setCode(CcmRestType.OK);
        result.setResult(map);

        return result;
    }


    /**
     * @param relevanceId    跟踪对象
     * @param relevanceTable 跟踪对象所在表
     * @param pageNo         页码
     * @param pageSize       分页大小
     * @return
     * @author pengjianqiang
     * @version 2018-02-22
     * @see 查询日志信息
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CcmRestResult query(Page<CcmLogTail> page, String userId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (page.getPageSize() == -1) {
            page.setPageSize(30);
        }
        CcmRestResult result = new CcmRestResult();
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (sessionUser == null) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        String sessionUserId = sessionUser.getId();
        if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        String relevanceId = req.getParameter("relevanceId");
        String relevanceTable = req.getParameter("relevanceTable");
        String tailCase = req.getParameter("tailCase");

        CcmLogTail ccmLogTail = new CcmLogTail();
        ccmLogTail.setRelevanceId(relevanceId);
        ccmLogTail.setRelevanceTable(relevanceTable);
        ccmLogTail.setTailCase(tailCase);
        ccmLogTail.setPage(page);
        List<CcmLogTail> logList = ccmLogTailService.findListByObject(ccmLogTail);
        if(logList.size()>0){
            String file = Global.getConfig("FILE_UPLOAD_URL");
            for(CcmLogTail resccmLogTail : logList){
                if(StringUtils.isNotEmpty(resccmLogTail.getFile())){
                    resccmLogTail.setFile(file + resccmLogTail.getFile());
                }
            }
        }
        page.setList(logList);
        result.setCode(CcmRestType.OK);
        result.setResult(page.getList());

        return result;
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     *//*
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}*/


    /**
     * @param
     * @return
     * @author pengjianqiang
     * @version 2018-02-22
     * @see 增加跟踪信息
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CcmRestResult add(String userId, String tailTime, String id, CcmLogTail ccmLogTail, HttpServletRequest req) {
        CcmRestResult result = new CcmRestResult();
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (sessionUser == null) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        String sessionUserId = sessionUser.getId();
        if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        if (tailTime == null || "".equals(tailTime)) {
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }
        if (ccmLogTail.getRelevanceId() == null || "".equals(ccmLogTail.getRelevanceId())) {//参数id不对
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }
        if (ccmLogTail.getRelevanceTable() == null || "".equals(ccmLogTail.getRelevanceTable())) {//参数id不对
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }

        if (ccmLogTail.getId() != null && !"".equals(ccmLogTail.getId())) {

            CcmLogTail ccmLogTailDB = ccmLogTailService.get(ccmLogTail.getId());
            if (ccmLogTailDB == null) {
                result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
                return result;
            }


            ccmLogTailDB.setType(ccmLogTail.getType());
            ccmLogTailDB.setTailPerson(ccmLogTail.getTailPerson());
            ccmLogTailDB.setTailTime(ccmLogTail.getTailTime());
            ccmLogTailDB.setTailContent(ccmLogTail.getTailContent());
            ccmLogTailDB.setTailCase(ccmLogTail.getTailCase());
            ccmLogTailDB.setMore1(ccmLogTail.getMore1());
            ccmLogTailDB.setFile(ccmLogTail.getFile());
            ccmLogTailDB.setRemarks(ccmLogTail.getRemarks());

            ccmLogTailDB.setUpdateBy(new User(userId));
            ccmLogTailService.save(ccmLogTailDB);

        } else {

            ccmLogTail.setCreateBy(new User(userId));
            ccmLogTail.setUpdateBy(new User(userId));
            ccmLogTailService.save(ccmLogTail);
        }


        result.setCode(CcmRestType.OK);
        result.setResult(ccmLogTail);
        return result;

    }


    /**
     * @param
     * @return
     * @author pengjianqiang
     * @version 2019-03-05
     * @see 增加重点人员登记记录信息
     */
    @ResponseBody
    @RequestMapping(value = "/addSign", method = RequestMethod.POST)
    public CcmRestResult addSign(String userId, String tailTime, CcmLogImpPopSign ccmLogImpPopSign, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CcmRestResult result = new CcmRestResult();
        if (ccmLogImpPopSign.getPeopleId() == null || "".equals(ccmLogImpPopSign.getPeopleId())) {//参数PeopleId不对
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }
        if (ccmLogImpPopSign.getContent() == null || "".equals(ccmLogImpPopSign.getContent())) {//参数content不对
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }
        if (tailTime == null || "".equals(tailTime)) {
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }
        if (userId == null || "".equals(userId)) {//userid为空时，默认赋值1，暂不做权限限制
            userId = "1";
        }
        ccmLogImpPopSign.setRelevanceTable("ccm_people");
        SysConfig signEffectiveConfig = sysConfigService.get("sign_effective_config");
        Date effectiveDate = signEffectiveConfig.getParamDate();
        Date signDate = ccmLogImpPopSign.getTailTime();
/*		Calendar effectiveCalendar = Calendar.getInstance();
		effectiveCalendar.setTime(effectiveDate);
		effectiveCalendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		Calendar signCalendar = Calendar.getInstance();
		signCalendar.setTime(signDate);*/
/*		boolean compareResult = signDate
		if (compareResult) {
			ccmLogImpPopSign.setErrorStatus("1");
		} else {
			ccmLogImpPopSign.setErrorStatus("0");
		}*/
        ccmLogImpPopSign.setCreateBy(new User(userId));
        ccmLogImpPopSign.setUpdateBy(new User(userId));
        ccmLogImpPopSignService.save(ccmLogImpPopSign);

        result.setCode(CcmRestType.OK);
        result.setResult(ccmLogImpPopSign);

        return result;

    }


}