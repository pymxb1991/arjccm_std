package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.modules.ccm.place.service.CcmBasePlaceService;
import com.arjjs.ccm.tool.CommUtilRest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgNpse;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 重点场所接口类
 *
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Controller
@RequestMapping(value = "${appPath}/rest/keyPlace")
public class CcmRestKeyPlace extends BaseController {

    @Autowired
    private CcmOrgNpseService ccmOrgNpseService;
    @Autowired
    private CcmBasePlaceService ccmBasePlaceService;


    /**
     * 查询重点场所详情
     *
     * @param userId
     * @param req
     * @param resp
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPlace", method = RequestMethod.GET)
    public CcmRestResult getPlace(String userId, HttpServletRequest req, HttpServletResponse resp, String id) {
        CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
        CcmBasePlace ccmBasePlace = ccmBasePlaceService.get(id);
        String file1 = ccmBasePlace.getPlacePicture();
        if (StringUtils.isNotEmpty(file1)) {
            String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
            ccmBasePlace.setPlacePicture(fileUrl + file1);
        }
        result.setCode(CcmRestType.OK);
        result.setResult(ccmBasePlace);
        return result;
    }

    /**
     * 查询重点场所列表
     *
     * @param userId
     * @param ccmBasePlace
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPlace", method = RequestMethod.GET)
    public CcmRestResult queryPlace(String userId, CcmBasePlace ccmBasePlace, HttpServletRequest req, HttpServletResponse resp) {
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
        ccmBasePlace.setCheckUser(sessionUser);
        Page<CcmBasePlace> page = ccmBasePlaceService.findPage(new Page<>(req, resp), ccmBasePlace);

        List<CcmBasePlace> list = page.getList();
        List<CcmBasePlace> resultList = Lists.newArrayList();
        list.forEach(ccmBasePlace1->{
            String file1 = ccmBasePlace1.getPlacePicture();
            if (StringUtils.isNotEmpty(file1)) {
                String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
                ccmBasePlace1.setPlacePicture(fileUrl + file1);
            }
            resultList.add(ccmBasePlace1);
        });

        result.setCode(CcmRestType.OK);
        result.setResult(page.getList());

        return result;
    }

    /**
     * 修改重点场所信息
     *
     * @param userId
     * @param ccmBasePlace
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPlace", method = RequestMethod.POST)
    public CcmRestResult modifyPlace(String userId, CcmBasePlace ccmBasePlace, HttpServletRequest req, HttpServletResponse resp) {

        CcmRestResult result = CommUtilRest.getResult(userId, req, resp, ccmBasePlace.getId());
        CcmBasePlace ccmBasePlaceDB = ccmBasePlaceService.get(ccmBasePlace.getId());
        if (StringUtils.isNotEmpty(ccmBasePlace.getPlaceName())) {
            ccmBasePlaceDB.setPlaceName(ccmBasePlace.getPlaceName());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getRelevanceOrg())) {
            ccmBasePlaceDB.setRelevanceOrg(ccmBasePlace.getRelevanceOrg());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getKeyPoint())) {
            ccmBasePlaceDB.setKeyPoint(ccmBasePlace.getKeyPoint());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getWorkerNumber())) {
            ccmBasePlaceDB.setWorkerNumber(ccmBasePlace.getWorkerNumber());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getPlaceUse())) {
            ccmBasePlaceDB.setPlaceUse(ccmBasePlace.getPlaceUse());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getPlaceArea())) {
            ccmBasePlaceDB.setPlaceArea(ccmBasePlace.getPlaceArea());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getLeaderName())) {
            ccmBasePlaceDB.setLeaderName(ccmBasePlace.getLeaderName());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getLeaderIdCard())) {
            ccmBasePlaceDB.setLeaderIdCard(ccmBasePlace.getLeaderIdCard());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getLeaderContact())) {
            ccmBasePlaceDB.setLeaderContact(ccmBasePlace.getLeaderContact());
        }
        if (null != ccmBasePlace.getCreateTime()) {
            ccmBasePlaceDB.setCreateTime(ccmBasePlace.getCreateTime());
        }
        if (null != ccmBasePlace.getArea()) {
            ccmBasePlaceDB.setAdministrativeDivision(ccmBasePlace.getArea().getId());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getAddress())) {
            ccmBasePlaceDB.setAddress(ccmBasePlace.getAddress());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getGoverningBodyName())) {
            ccmBasePlaceDB.setGoverningBodyName(ccmBasePlace.getGoverningBodyName());
        }
        if (StringUtils.isNotEmpty(ccmBasePlace.getRemarks())) {
            ccmBasePlaceDB.setRemarks(ccmBasePlace.getRemarks());
        }

        // 添加图片处理代码
        String images = ccmBasePlace.getPlacePicture();
        if (StringUtils.isNotEmpty(images)) {
            if(images.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
                ccmBasePlaceDB.setPlacePicture(images.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
            }else {
                ccmBasePlaceDB.setPlacePicture(images);
            }
        }

        // CommUtilRest.updateDataWithPart(ccmBasePlace, ccmBasePlaceDB);

        ccmBasePlaceDB.setCreateBy(new User(userId));

        ccmBasePlace.setUpdateBy(new User(userId));
        ccmBasePlaceService.save(ccmBasePlaceDB);
        result.setCode(CcmRestType.OK);
        result.setResult("成功");
        return result;

    }


    /**
     * @param id ID
     * @return
     * @author pengjianqiang
     * @version 2018-02-23
     * @see 获取单个重点场所信息
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

        CcmOrgNpse ccmOrgNpse = ccmOrgNpseService.get(id);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        if(StringUtils.isNotEmpty(ccmOrgNpse.getImages())){
            ccmOrgNpse.setImages(fileUrl + ccmOrgNpse.getImages());
        }
        result.setCode(CcmRestType.OK);
        result.setResult(ccmOrgNpse);

        return result;
    }

    /**
     * @param ccmOrgNpse(compImpoType 企业重点类型)
     * @param pageNo                  页码
     * @param pageSize                分页大小
     * @return
     * @author pengjianqiang
     * @version 2018-02-23
     * @see 查询重点场所信息
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CcmRestResult query(String userId, CcmOrgNpse ccmOrgNpse, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        ccmOrgNpse.setCheckUser(sessionUser);
        Page<CcmOrgNpse> page = ccmOrgNpseService.findPage(new Page<CcmOrgNpse>(req, resp), ccmOrgNpse);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        for(int f=0;f<page.getList().size();f++){
            if (StringUtils.isNotEmpty(page.getList().get(f).getImages())) {
                page.getList().get(f).setImages(fileUrl + page.getList().get(f).getImages());
            }
        }
        result.setCode(CcmRestType.OK);
        result.setResult(page.getList());

        return result;
    }

    /**
     * @param
     * @return
     * @author fuxinshuang
     * @version 2018-03-06
     * @see 保存重点场所信息
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CcmRestResult modify(String userId, CcmOrgNpse ccmOrgNpse, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        if (ccmOrgNpse.getId() != null && !"".equals(ccmOrgNpse.getId())) {
            CcmOrgNpse ccmOrgNpseDB = ccmOrgNpseService.get(ccmOrgNpse.getId());
            if (ccmOrgNpseDB == null) {//从数据库中没有取到对应数据
                result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
            }
        }
        if (ccmOrgNpse.getCreateBy() == null) {
            ccmOrgNpse.setCreateBy(new User(userId));
        }
        ccmOrgNpse.setUpdateBy(new User(userId));
        ccmOrgNpseService.save(ccmOrgNpse);
        result.setCode(CcmRestType.OK);
        result.setResult("成功");
        return result;

    }


}