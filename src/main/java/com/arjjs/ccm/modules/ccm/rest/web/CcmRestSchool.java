package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.config.Global;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseSchoolrim;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseSchoolrimService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.sys.service.AreaSPService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.CommUtilRest;
import com.arjjs.ccm.tool.Tree;
import com.fay.tree.service.IFayTreeNode;
import com.fay.tree.util.FayTreeUtil;
import com.google.common.collect.Maps;

/**
 * 学校接口类
 *
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Controller
@RequestMapping(value = "${appPath}/rest/school")
public class CcmRestSchool extends BaseController {

    @Autowired
    private CcmHouseSchoolrimService ccmHouseSchoolrimService;
    @Autowired
    private AreaSPService areaSPService;
    @Autowired
    private CcmPeopleService ccmPeopleService;

    /**
     * @param id ID
     * @return
     * @author pengjianqiang
     * @version 2018-02-23
     * @see 获取单个学校信息
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
        CcmHouseSchoolrim ccmHouseSchoolrim = ccmHouseSchoolrimService.get(id);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        if(StringUtils.isNotEmpty(ccmHouseSchoolrim.getImages())){
            ccmHouseSchoolrim.setImages(fileUrl + ccmHouseSchoolrim.getImages());
        }
        result.setCode(CcmRestType.OK);
        result.setResult(ccmHouseSchoolrim);

        return result;
    }

    /**
     * @param CcmHouseSchoolrim
     * @param pageNo            页码
     * @param pageSize          分页大小
     * @return
     * @author pengjianqiang
     * @version 2018-02-23
     * @see 查询学校信息
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CcmRestResult query(String userId, CcmHouseSchoolrim ccmHouseSchoolrim, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        ccmHouseSchoolrim.setCheckUser(sessionUser);
        Page<CcmHouseSchoolrim> page = ccmHouseSchoolrimService.findPage(new Page<CcmHouseSchoolrim>(req, resp), ccmHouseSchoolrim);
        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
        if(page.getList().size()>0){
            for (int i = 0; i < page.getList().size(); i++) {
                page.getList().get(i).setImages(fileUrl + page.getList().get(i).getImages());
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
     * @version 2018-03-05
     * @see 修改学校信息
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CcmRestResult modify(String userId, CcmHouseSchoolrim ccmHouseSchoolrim, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        if (ccmHouseSchoolrim.getId() != null && !"".equals(ccmHouseSchoolrim.getId())) {
            CcmHouseSchoolrim ccmHouseSchoolrimDB = ccmHouseSchoolrimService.get(ccmHouseSchoolrim.getId());
            if (ccmHouseSchoolrimDB == null) {//从数据库中没有取到对应数据
                result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
                return result;
            }
            ccmHouseSchoolrim.setAreaPoint(ccmHouseSchoolrimDB.getAreaPoint());
            ccmHouseSchoolrim.setAreaMap(ccmHouseSchoolrimDB.getAreaMap());
        } else {
            ccmHouseSchoolrim.setAreaPoint("");
            ccmHouseSchoolrim.setAreaMap("");
            ccmHouseSchoolrim.setImage("");
        }
        String file = ccmHouseSchoolrim.getImages();
        if(StringUtils.isNotEmpty(file)) {
            if(file.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
                ccmHouseSchoolrim.setImages(file.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
            }else {
                ccmHouseSchoolrim.setImages(file);
            }
        }

        if (ccmHouseSchoolrim.getCreateBy() == null) {
            ccmHouseSchoolrim.setCreateBy(new User(userId));
        }
        ccmHouseSchoolrim.setUpdateBy(new User(userId));
        ccmHouseSchoolrimService.save(ccmHouseSchoolrim);
        result.setCode(CcmRestType.OK);
        result.setResult("成功");
        return result;

    }

    /**
     * @param type 类型
     * @return
     * @author fuxinshuang
     * @version 2018-03-20
     * @see 获取区域列表信息并生成树形
     */
    @ResponseBody
    @RequestMapping(value = "schoolTree")
    public Object schoolTree(@RequestParam(required = false) String extId, HttpServletResponse response) {
        CcmRestResult result = new CcmRestResult();
        List<IFayTreeNode> listTree = new ArrayList<IFayTreeNode>();
        List<CcmHouseSchoolrim> list = ccmHouseSchoolrimService.findListSP(new CcmHouseSchoolrim());
        for (int i = 0; i < list.size(); i++) {
            CcmHouseSchoolrim e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()) && e.getMore2().indexOf("," + extId + ",") == -1)) {
                Tree tree = new Tree(e.getId(), e.getMore1(), e.getSchoolName(), "", "", true);
                listTree.add(tree);
            }
        }
        Object data = FayTreeUtil.getTreeInJsonObject(listTree);
        result.setCode(CcmRestType.OK);
        result.setResult(data);
        return result;

    }

    //校园周边重点人员列表
    @ResponseBody
    @RequestMapping(value = "schoolrimPop")
    public CcmRestResult schoolrimPop(String userId, CcmHouseSchoolrim ccmHouseSchoolrim, HttpServletRequest req, HttpServletResponse resp) {
        CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
        // 如果当前的 flag 为返回
        if (result.isReturnFlag()) {
            return result;
        }
        CcmHouseSchoolrim ccmHouseSchoolrimDB = ccmHouseSchoolrimService.get(ccmHouseSchoolrim.getId());
        CcmPeople ccmPeople = new CcmPeople();
        Area area = new Area();
        if (ccmHouseSchoolrimDB != null && ccmHouseSchoolrimDB.getArea() != null) {
            area.setId(ccmHouseSchoolrimDB.getArea().getId());
            ccmPeople.setAreaComId(area);
        }
        Page<CcmPeople> page = ccmPeopleService.findPagePop(new Page<CcmPeople>(req, resp), ccmPeople);

        List<CcmPeople> list = page.getList();
        //
        CcmPeople ccmPeople2 = new CcmPeople();
        String[] listLimite = new String[list.size()];
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                listLimite[i] = list.get(i).getId();
            }
            ccmPeople2.setListLimite(listLimite);
            List<CcmPeople> list2 = ccmPeopleService.findListLimite(ccmPeople2);//数组查询id
            page.setList(list2);
        }
        result.setCode(CcmRestType.OK);
        result.setResult(page.getList());
        return result;
    }


    /**
     * @param
     * @return
     * @author pengjianqiang
     * @version 2018-05-12
     * @see 保存学校信息（支持新增和编辑,数据同步用）
     */
    @ResponseBody
    @RequestMapping(value = "/saveSyn", method = RequestMethod.POST)
    public CcmRestResult saveSyn(String userId, CcmHouseSchoolrim ccmHouseSchoolrim, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CcmRestResult result = new CcmRestResult();
        if (userId == null || "".equals(userId)) {
            userId = "1";
        }
        User user = new User(userId);
        ccmHouseSchoolrim.setCreateBy(user);
        ccmHouseSchoolrim.setUpdateBy(user);

        ccmHouseSchoolrimService.save(ccmHouseSchoolrim);
        result.setCode(CcmRestType.OK);
        result.setResult("成功");
        return result;

    }

    /**
     * @param
     * @return
     * @author pengjianqiang
     * @version 2018-05-17
     * @see 删除学校信息（数据同步用）
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSyn", method = RequestMethod.POST)
    public CcmRestResult deleteSyn(String userId, CcmHouseSchoolrim ccmHouseSchoolrim, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CcmRestResult result = new CcmRestResult();
        ccmHouseSchoolrimService.delete(ccmHouseSchoolrim);
        result.setCode(CcmRestType.OK);
        result.setResult("成功");
        return result;

    }


    //校园周边重点人员列表 + 学校信息
    @ResponseBody
    @RequestMapping(value = "schoolrimPopTest")
    public CcmRestResult schoolrimPop1(String userId, CcmHouseSchoolrim ccmHouseSchoolrim, HttpServletRequest req, HttpServletResponse resp) {
        CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);

        Map map = Maps.newHashMap();
        if (StringUtils.isNotEmpty(ccmHouseSchoolrim.getId())) {
            map.put("schoolInfo", ccmHouseSchoolrimService.get(ccmHouseSchoolrim.getId()));
        } else {
            map.put("schoolInfo", "");
        }

        if (ccmHouseSchoolrim.getArea() == null || StringUtils.isEmpty(ccmHouseSchoolrim.getArea().getId())) {
            List<CcmHouseSchoolrim> school = ccmHouseSchoolrimService.getAreaBySchool();
            String[] schoolArea = new String[school.size()];
            for (int i = 0; i < school.size(); i++) {
                if (school.get(i).getArea() != null && school.get(i).getArea().getId() != null) {
                    schoolArea[i] = school.get(i).getArea().getId();
                }
            }
            CcmPeople parampop = new CcmPeople();
            parampop.setListLimite(schoolArea);
            Page<CcmPeople> page = ccmPeopleService.findAllPopByArea(new Page<>(req, resp), parampop);
            //数组查询id
            List<CcmPeople> list = page.getList();
            CcmPeople ccmPeople2 = new CcmPeople();
            String[] listLimite = new String[list.size()];
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    listLimite[i] = list.get(i).getId();
                }
                ccmPeople2.setListLimite(listLimite);
                List<CcmPeople> list2 = ccmPeopleService.findListLimite_V2(ccmPeople2);//数组查询id
                page.setList(list2);
            }
            result.setCode(CcmRestType.OK);
            map.put("popInfo", page);
            result.setResult(map);
            return result;
        } else {
            CcmPeople ccmPeople = new CcmPeople();
            Area area = new Area();
            if (ccmHouseSchoolrim.getArea() != null && ccmHouseSchoolrim.getArea().getId() != null) {
                area.setId(ccmHouseSchoolrim.getArea().getId());
                ccmPeople.setAreaComId(area);
            }
            Page<CcmPeople> page = ccmPeopleService.findPagePop(new Page<>(req, resp), ccmPeople);
            //数组查询id
            List<CcmPeople> list = page.getList();
            CcmPeople ccmPeople2 = new CcmPeople();
            String[] listLimite = new String[list.size()];
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    listLimite[i] = list.get(i).getId();
                }
                ccmPeople2.setListLimite(listLimite);
                List<CcmPeople> list2 = ccmPeopleService.findListLimite_V2(ccmPeople2);//数组查询id
                page.setList(list2);
            }

            result.setCode(CcmRestType.OK);
            map.put("popInfo", page);
            result.setResult(map);
            ;
            return result;

        }

    }
}