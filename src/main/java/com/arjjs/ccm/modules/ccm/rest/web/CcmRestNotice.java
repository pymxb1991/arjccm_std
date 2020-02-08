package com.arjjs.ccm.modules.ccm.rest.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.message.entity.CcmMessage;
import com.arjjs.ccm.modules.ccm.message.service.CcmMessageService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkAdvise;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkNotice;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkAdviseService;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkNoticeService;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import com.arjjs.ccm.modules.oa.entity.OaNotifyRecord;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.RabbitMQTools;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li
 * @CreateTime: 2019-11-05 16:49
 * @Description: app公告接口
 */
@RestController
@RequestMapping("${appPath}/rest/notice")
public class CcmRestNotice {

    @Autowired
    private CcmWorkNoticeService ccmWorkNoticeService;
    @Autowired
    private OaNotifyService oaNotifyService;
    @Autowired
    private CcmMessageService ccmMessageService;
    @Autowired
    private CcmWorkAdviseService ccmWorkAdviseService;


    /**
     * 公告列表
     *
     * @param ccmWorkNotice
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryNotice")
    public CcmRestResult query(CcmWorkNotice ccmWorkNotice, HttpServletRequest request, HttpServletResponse response) {

        CcmRestResult result = new CcmRestResult();
        Page<CcmWorkNotice> page = ccmWorkNoticeService.findPage(new Page<>(request, response), ccmWorkNotice);

        List<CcmWorkNotice> list = page.getList();
        if (null != list || 0 != list.size()) {
            for (CcmWorkNotice workNotice : list) {
                workNotice.setPublisher(workNotice.getCreateBy().getName());
            }
        }

        result.setResult(list);
        result.setCode(CcmRestType.OK);
        return result;
    }

    /**
     * 公告详情
     *
     * @param ccmWorkNotice
     * @return
     */
    @RequestMapping(value = "getNotice")
    public CcmRestResult get(CcmWorkNotice ccmWorkNotice) {

        CcmRestResult result = new CcmRestResult();
        CcmWorkNotice ccmWorkNoticeDB = ccmWorkNoticeService.get(ccmWorkNotice.getId());
        result.setResult(ccmWorkNoticeDB);
        result.setCode(CcmRestType.OK);
        return result;
    }

    /**
     * 公告保存
     *
     * @param ccmWorkNotice
     * @return
     */
    @RequestMapping(value = "saveNotice")
    public CcmRestResult save(CcmWorkNotice ccmWorkNotice) {
        CcmRestResult result = new CcmRestResult();
        ccmWorkNoticeService.save(ccmWorkNotice);
        result.setCode(CcmRestType.OK);
        result.setResult("");
        return result;
    }


    /**
     * 我的通知列表
     */
    @RequestMapping(value = "selfList")
    public CcmRestResult selfList(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response) {

        CcmRestResult result = new CcmRestResult();

        oaNotify.setCurrentUser(UserUtils.get(oaNotify.getUserId()));
        oaNotify.setSelf(true);
        Page<OaNotify> page = oaNotifyService.find(new Page<>(request, response), oaNotify);
        result.setResult(page.getList());
        result.setCode(CcmRestType.OK);

        return result;
    }


    /**
     * 查看我的通知-只读
     */
    @RequestMapping(value = "viewRead")
    public CcmRestResult viewRead(OaNotify oaNotify) {
        CcmRestResult result = new CcmRestResult();

        if (StringUtils.isNotBlank(oaNotify.getId())) {
            OaNotify resoaNotify = oaNotifyService.get(oaNotify.getId());
            resoaNotify.setUserId(oaNotify.getUserId());
            oaNotifyService.updateReadFlag(resoaNotify);
            oaNotify = oaNotifyService.getRecordList(resoaNotify);
            for (OaNotifyRecord oaNotifyRecord : oaNotify.getOaNotifyRecordList()) {
                oaNotifyRecord.setDeptName(oaNotifyRecord.getUser().getOffice().getName());
            }
            if(StringUtils.isNotEmpty(oaNotify.getFiles())){
                if(oaNotify.getFiles().contains("|")){
                    oaNotify.setFiles( oaNotify.getFiles().replace("|",Global.getConfig("FILE_UPLOAD_URL")));
                } else {
                    oaNotify.setFiles(Global.getConfig("FILE_UPLOAD_URL") + oaNotify.getFiles());
                }
            }
            result.setResult(oaNotify);
            result.setCode(CcmRestType.OK);
            return result;
        }

        result.setCode(CcmRestType.OK);
        return result;
    }


    /**
     * 通告管理列表
     *
     * @param oaNotify
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryAnnouncement")
    public CcmRestResult queryList(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response) {
        CcmRestResult result = new CcmRestResult();
        Page<OaNotify> page = oaNotifyService.find(new Page<>(request, response), oaNotify);
        result.setResult(page.getList());
        result.setCode(CcmRestType.OK);
        return result;
    }

    /**
     * 通告管理详情
     *
     * @param oaNotify
     * @return
     */
    @RequestMapping(value = "getAnnouncement")
    public CcmRestResult get(OaNotify oaNotify) {
        CcmRestResult result = new CcmRestResult();
        if (StringUtils.isNotBlank(oaNotify.getId())) {
            oaNotify = oaNotifyService.get(oaNotify.getId());
            oaNotify = oaNotifyService.getRecordList(oaNotify);
            for (OaNotifyRecord oaNotifyRecord : oaNotify.getOaNotifyRecordList()) {
                oaNotifyRecord.setDeptName(oaNotifyRecord.getUser().getOffice().getName());
            }
        }
        if (StringUtils.isNotEmpty(oaNotify.getFiles())) {
            String images = oaNotify.getFiles();
            String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
            oaNotify.setFiles(fileUrl + images);
        }
        result.setResult(oaNotify);
        result.setCode(CcmRestType.OK);
        return result;
    }

    /**
     * 通告管理保存
     *
     * @param oaNotify
     * @return
     */
    @RequestMapping(value = "saveAnnouncement")
    public CcmRestResult save(OaNotify oaNotify, String userId) {

        CcmRestResult result = new CcmRestResult();


        if (StringUtils.isEmpty(oaNotify.getId())) {
            oaNotify.setCreateBy(new User(userId));
        }

        // 如果是修改，且状态为已发布，则不能再进行操作2019
        if (StringUtils.isNotBlank(oaNotify.getId())) {
            OaNotify e = oaNotifyService.get(oaNotify.getId());
            if ("1".equals(e.getStatus())) {
                result.setResult("当前通告已发布，不能再修改！");
                result.setCode(CcmRestType.OK);
                return result;
            }
        }

        // 添加图片处理代码
        if (StringUtils.isNotEmpty(oaNotify.getFiles())) {
            String images = oaNotify.getFiles();
            String fileUrl = Global.getConfig("FILE_UPLOAD_URL");   // 文件上传存储路径
            images = images.substring(fileUrl.length());
            oaNotify.setFiles(images);
        }

        oaNotify.setUpdateBy(new User(userId));
        oaNotifyService.save(oaNotify);

        // 消息发布
        if (oaNotify.getStatus().equals("1")) {
            List<CcmMessage> list = new ArrayList<CcmMessage>();
            List<OaNotifyRecord> oaNotifyRecordList = oaNotify.getOaNotifyRecordList();
            for (OaNotifyRecord oaNotifyRecord : oaNotifyRecordList) {
                CcmMessage ccmMessage = new CcmMessage();
                ccmMessage.preInsert();
                ccmMessage.setType("03");//通知消息

                Date createDate = oaNotify.getUpdateDate();
                String str = "MM-dd HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(str);
                ccmMessage.setContent(sdf.format(createDate) + "：" + "通知：" + oaNotify.getTitle());
                ccmMessage.setReadFlag("0");//未读
                ccmMessage.setObjId(oaNotify.getId());
                ccmMessage.setUserId(oaNotifyRecord.getUser().getId());
                list.add(ccmMessage);

                Map resmap = Maps.newHashMap();
                resmap.put("type","oaNotifyType");
                resmap.put("id",oaNotify.getId());
                resmap.put("name","通知："+oaNotify.getTitle());
                RabbitMQTools.sendMessage(oaNotifyRecord.getUser().getId(), JSONObject.fromObject(resmap).toString());

            }
            //批量添加
            ccmMessageService.insertEventAll(list);
            //发送mq
            CcmRestEvent.sendMessageToMq(list);
        }

        result.setResult("");
        result.setCode(CcmRestType.OK);
        return result;
    }


    /**
     * 查询意见建议列表
     *
     * @param ccmWorkAdvise
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryAdvise")
    public CcmRestResult list(CcmWorkAdvise ccmWorkAdvise, HttpServletRequest request, HttpServletResponse response) {
        CcmRestResult result = new CcmRestResult();
        Page<CcmWorkAdvise> page = ccmWorkAdviseService.findPage(new Page<>(request, response), ccmWorkAdvise);
        List<CcmWorkAdvise> list = page.getList();
        if (null != list || 0 != list.size()) {
            for (CcmWorkAdvise workAdvise : list) {
                workAdvise.setPname(workAdvise.getCreateBy().getName());
            }
        }
        result.setResult(list);
        result.setCode(CcmRestType.OK);
        return result;
    }

    /**
     * 查询意见建议详情
     *
     * @param ccmWorkAdvise
     * @return
     */
    @RequestMapping(value = "getAdvise")
    public CcmRestResult form(CcmWorkAdvise ccmWorkAdvise) {
        CcmRestResult result = new CcmRestResult();

        CcmWorkAdvise ccmWorkAdviseDB = ccmWorkAdviseService.get(ccmWorkAdvise.getId());
        result.setResult(ccmWorkAdviseDB);
        result.setCode(CcmRestType.OK);

        return result;
    }

    /**
     * 保存意见建议
     *
     * @param ccmWorkAdvise
     * @return
     */
    @RequestMapping(value = "saveAdvise")
    public CcmRestResult save(CcmWorkAdvise ccmWorkAdvise) {

        CcmRestResult result = new CcmRestResult();
        ccmWorkAdviseService.save(ccmWorkAdvise);
        result.setResult("");
        result.setCode(CcmRestType.OK);

        return result;
    }

}
