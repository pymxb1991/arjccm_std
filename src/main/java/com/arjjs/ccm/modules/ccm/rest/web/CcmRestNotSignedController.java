package com.arjjs.ccm.modules.ccm.rest.web;

import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.rest.service.AlarmNotifyService;
import com.arjjs.ccm.modules.flat.handle.service.BphAlarmHandleService;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 查询未签收信息
 *
 * @Author: Li
 * @CreateTime: 2019-12-10 15:21
 * @Description: ${Description}
 */
@RestController
@RequestMapping(value = "${appPath}/rest/noticeService")
@Api(description = "未签收信息数量-app接口类")
@Slf4j
public class CcmRestNotSignedController extends BaseController {
    @Autowired
    private BphAlarmHandleService handleService;
    @Autowired
    AlarmNotifyService alarmNotifyService;

    /**
     * 处理未签收信息查询
     */
    @ApiOperation(value = "未签收信息处理（查询未处理数量 根据Id 查询当日未签收事件总数)")
    @RequestMapping(value = "/queryNewsList", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "登陆人ID", paramType = "query", required = true)})
    public CcmRestResult queryRestEventCount( String userId, HttpServletRequest req, HttpServletResponse resp) {
        logger.info("当前正在执行的类名为》》》" + Thread.currentThread().getStackTrace()[1].getClassName());
        logger.info("当前正在执行的方法名为》》》" + Thread.currentThread().getStackTrace()[1].getMethodName());
        CcmRestResult result = new CcmRestResult();
//        User sessionUser = (User) req.getSession().getAttribute("user");
//        if (sessionUser == null) {
//            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
//            return result;
//        }
//        String sessionUserId = sessionUser.getId();
//        if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
//            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
//            return result;
//        }
        String status[]={"0"};
        List allStatusList = new ArrayList(Arrays.asList(status));
        //接警处未查询信息
        int alarmNum = handleService.queryAlarmCount(userId, allStatusList);
        //警情通知文查询信息
        int alarmNotifyNum  = alarmNotifyService.selectAlarmNotifyTodoCount(userId);
        //事件管理未查询信息
        int eventNum = handleService.queryEventCount(userId);
        //我的消息未查询信息
        int messageNum = handleService.queryNewsCount(userId);

        Map<String, Object> resultInfo = new HashMap<>();
        resultInfo.put("messageNum", messageNum);
        resultInfo.put("eventNum", eventNum);
        resultInfo.put("alarmNum", alarmNum);
        resultInfo.put("alarmNotifyNum", alarmNotifyNum);

        result.setCode(CcmRestType.OK);
        result.setResult(resultInfo);
        return result;

    }
}

