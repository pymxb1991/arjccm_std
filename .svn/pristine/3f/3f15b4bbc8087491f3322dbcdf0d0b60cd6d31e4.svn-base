package com.arjjs.ccm.modules.ccm.sys.service;

import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmDeviceService;
import com.arjjs.ccm.modules.ccm.citycomponents.service.CcmCityComponentsService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmAreaPoint;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeopleVo;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class CcmMapService {



    // 人员管理
    @Autowired
    private CcmPeopleService ccmPeopleService;

    // 楼栋管理
    @Autowired
    private CcmHouseBuildmanageService ccmHouseBuildmanageService;

    // 租房
    @Autowired
    private CcmPopTenantService ccmPopTenantService;

    // 城市部件
    @Autowired
    private CcmCityComponentsService ccmCityComponentsService;

    // 重点场所
    @Autowired
    private CcmOrgNpseService ccmOrgNpseService;

    // 事件管理
    @Autowired
    private CcmEventIncidentService ccmEventIncidentService;

    // 设备管理
    @Autowired
    private CcmDeviceService ccmDeviceService;
    /**
     * @see  地图信息点位图
     * @param type   类型:
     *                  1、人口；2：楼栋 ；3、出租屋（只查出租的）；
     *                  4、城市部件（按部件类型区分）；5、企业（按风险等级区分）；
     *                  6、重点人员（按重点人员类型区分）；7、事件（按事件分级区分）; 8、视频；
     * @param areaId 区域ID
     * @param name  类型对应名称
     * @return
     */

    public List<CcmAreaPoint> queryAreaPoint(String type, String areaId, String name) {
        List<CcmAreaPoint> resultPointList = new ArrayList<>();
        CcmAreaPointVo areaPointVo = new CcmAreaPointVo();
        areaPointVo.setAreaId(areaId);
        areaPointVo.setName(name);
        //人口
        if ("1".equals(type)){
            List<CcmPeopleVo> ccmPeopleList = ccmPeopleService.selectByAreaGIdAndName(areaPointVo);
            for (CcmPeopleVo people:ccmPeopleList) {
                CcmHouseBuildmanage buildmanage = ccmHouseBuildmanageService.get(people.getBuildId());
                if (buildmanage!=null){
                    resultPointList.add(areaPointSet(people.getId(), buildmanage.getAreaPoint()));
                }
            }
        }
        //楼栋
        if ("2".equals(type)){
            List<CcmHouseBuildmanage> buildmanageList = ccmHouseBuildmanageService.selectByAreaGIdAndName(areaPointVo);
            for (CcmHouseBuildmanage buildmanage:buildmanageList) {
                resultPointList.add(areaPointSet(buildmanage.getId(), buildmanage.getAreaPoint()));
            }
        }
        //page1.getList().forEach(item->item.setPlacePicture(Global.getConfig("FILE_UPLOAD_URL")+item.getPlacePicture()));
        //3、出租屋（只查出租的）
        if ("3".equals(type)){
            List<CcmHouseBuildmanage> buildmanageList = ccmHouseBuildmanageService.selectByAreaId(areaId);

            buildmanageList.forEach(buildmanage->{
                CcmAreaPointVo areaPointBuildVo = new CcmAreaPointVo();
                areaPointBuildVo.setAreaId(buildmanage.getId());
                areaPointBuildVo.setName(name);
                List<String> popTenantList = ccmPopTenantService.selectByBuildIdAndName(areaPointBuildVo);
                popTenantList.forEach(popTenant-> {
                    if ("b3076e9ea5e74b9aa207926589a89583".equals(popTenant))
                        System.out.println("...........");
                    resultPointList.add(areaPointSet(popTenant, buildmanage.getAreaPoint())); });
            });
        }
        //4、城市部件（按部件类型区分）
        if ("4".equals(type)){
            resultPointList.addAll(ccmCityComponentsService.selectByAreaGIdAndName(areaPointVo));
        }
        //5、企业（按风险等级区分）；
        if ("5".equals(type)){
            resultPointList.addAll(ccmOrgNpseService.selectByAreaGIdAndName(areaPointVo));
        }
        // 6、重点人员（按重点人员类型区分）
        if ("6".equals(type)){
            List<CcmPeopleVo> ccmPeopleList = ccmPeopleService.selectByAreaGIdAndNameImport(areaPointVo);
            for (CcmPeopleVo people:ccmPeopleList) {
                CcmHouseBuildmanage buildmanage = ccmHouseBuildmanageService.get(people.getBuildId());
                if (buildmanage!=null){
                    String pointType = null;
                    if(null!=people.getIsBehind()&&people.getIsBehind()==1){
                        pointType = "1";
                    }
                    if(null!=people.getIsRelease()&&people.getIsRelease()==1){
                        pointType = "2";
                    }
                    if(null!=people.getIsRectification()&&people.getIsRectification()==1){
                        pointType = "3";
                    }
                    if(null!=people.getIsAids()&&people.getIsAids()==1){
                        pointType = "4";
                    }
                    if(null!=people.getIsPsychogeny()&&people.getIsPsychogeny()==1){
                        pointType = "5";
                    }
                    if(null!=people.getIsKym()&&people.getIsKym()==1){
                        pointType = "6";
                    }
                    if(null!=people.getIsDrugs()&&people.getIsDrugs()==1){
                        pointType = "7";
                    }
                    if(null!=people.getIsVisit()&&people.getIsVisit()==1){
                        pointType = "8";
                    }
                    if(null!=people.getIsDangerous()&&people.getIsDangerous()==1){
                        pointType = "9";
                    }
                    if(null!=people.getIsHeresy()&&people.getIsHeresy()==1){
                        pointType = "10";
                    }
                    if(null!=people.getIsDeliberatelyIllegal()&&people.getIsDeliberatelyIllegal()==1){
                        pointType = "11";
                    }
                    if(null!=people.getIsCriminalOffense()&&people.getIsCriminalOffense()==1){
                        pointType = "12";
                    }
                    if(null!=people.getIsEscape()&&people.getIsEscape()==1){
                        pointType = "13";
                    }
                    if(null!=people.getIsHarmNational()&&people.getIsHarmNational()==1){
                        pointType = "14";
                    }
                    resultPointList.add(areaPointSet(people.getId(), buildmanage.getAreaPoint(),pointType));
                }
            }
        }
        //7、事件（按事件分级区分）
        if ("7".equals(type)){
            resultPointList.addAll(ccmEventIncidentService.selectByAreaGIdAndName(areaPointVo));
        }
        //8、视频
        if ("8".equals(type)){
            resultPointList.addAll(ccmDeviceService.selectByAreaGIdAndName(areaPointVo));
        }
        return resultPointList;
    }
    //设置返回区域点
    public  CcmAreaPoint areaPointSet(String id,String areaPoint){
        CcmAreaPoint resultPoint = new CcmAreaPoint();
        resultPoint.setAreaPoint(areaPoint);
        resultPoint.setId(id);
        return resultPoint;
    }
    //设置返回区域点
    public  CcmAreaPoint areaPointSet(String id,String areaPoint,String type){
        CcmAreaPoint resultPoint = new CcmAreaPoint();
        resultPoint.setAreaPoint(areaPoint);
        resultPoint.setId(id);
        resultPoint.setType(type);
        return resultPoint;
    }
}
