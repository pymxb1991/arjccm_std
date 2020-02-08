/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.log.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.log.dao.CcmLogTailDao;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 跟踪信息Service
 *
 * @author arj
 * @version 2018-01-23
 */
@Service
@Transactional(readOnly = true)
public class CcmLogTailService extends CrudService<CcmLogTailDao, CcmLogTail> {

    @Autowired
    private CcmLogTailDao ccmLogTailDao;


    public CcmLogTail get(String id) {
        return super.get(id);
    }

    public List<CcmLogTail> findList(CcmLogTail ccmLogTail) {
        return super.findList(ccmLogTail);
    }

    public Page<CcmLogTail> findPage(Page<CcmLogTail> page, CcmLogTail ccmLogTail) {
        return super.findPage(page, ccmLogTail);
    }

    @Transactional(readOnly = false)
    public void save(CcmLogTail ccmLogTail) {
        super.save(ccmLogTail);
    }

    @Transactional(readOnly = false)
    public void delete(CcmLogTail ccmLogTail) {
        super.delete(ccmLogTail);
    }

    @Transactional(readOnly = false)
    public int insertLogTail(CcmLogTail ccmLogTail, String ids) {
        String[] logTailIds = ids.split(",");
        List<String> idList = Arrays.asList(logTailIds);
        List<CcmLogTail> list = Lists.newArrayList();
        for (String strId : idList) {
            CcmLogTail ccmLogTailCP = new CcmLogTail();
            ccmLogTailCP.setId(IdGen.uuid());
            ccmLogTailCP.setRelevanceId(strId);
            ccmLogTailCP.setRelevanceTable(ccmLogTail.getRelevanceTable());
            ccmLogTailCP.setTailPerson(ccmLogTail.getTailPerson());
            ccmLogTailCP.setMore1(ccmLogTail.getMore1());
            ccmLogTailCP.setTailContent(ccmLogTail.getTailContent());
            ccmLogTailCP.setTailCase(ccmLogTail.getTailCase());
            ccmLogTailCP.setTailTime(new Date());
            if (null == ccmLogTail.getUpdateBy()) {
                ccmLogTailCP.setUpdateBy(UserUtils.getUser());
            } else {
                ccmLogTailCP.setUpdateBy(ccmLogTail.getUpdateBy());
            }
            if (null == ccmLogTail.getCreateBy()) {
                ccmLogTailCP.setCreateBy(UserUtils.getUser());
            } else {
                ccmLogTailCP.setCreateBy(ccmLogTail.getCreateBy());
            }

            ccmLogTailCP.setCreateDate(new Date());
            ccmLogTailCP.setUpdateDate(new Date());
            ccmLogTailCP.setDelFlag("0");
            list.add(ccmLogTailCP);
        }
        return dao.insertLogTail(list);
    }

    public List<CcmLogTail> findListByObject(CcmLogTail ccmLogTail) {
        return ccmLogTailDao.findListByObject(ccmLogTail);
    }



    //事件签收日志
    @Transactional(readOnly = false)
    public void signingLogTail(CcmEventCasedeal ccmEventCasedeal,User user){
        //将信息插入日志
        CcmLogTail ccmLogTail = new CcmLogTail();
        //为了兼容app上传，传入user
        ccmLogTail.setCreateBy(user);
        ccmLogTail.setUpdateBy(user);
//		ccmLogTail.setType("");
//		ccmLogTail.setTailPerson(ccmLogTail.getTailPerson());
        ccmLogTail.setTailTime(new Date());
        ccmLogTail.setTailContent(user.getName()+"将事件："+ccmEventCasedeal.getCaseName()+"签收");
        ccmLogTail.setTailCase("任务签收");
        ccmLogTail.setRelevanceTable("ccm_event_incident");
        ccmLogTail.setRelevanceId(ccmEventCasedeal.getObjId());
        save(ccmLogTail);
    }

    //事件拒签日志
    @Transactional(readOnly = false)
    public void rejectLogTail(CcmEventCasedeal ccmEventCasedeal,User user){
        CcmLogTail ccmLogTail = new CcmLogTail();
        //为了兼容app上传，传入user
        ccmLogTail.setCreateBy(user);
        ccmLogTail.setUpdateBy(user);
//		ccmLogTail.setType("");
//		ccmLogTail.setTailPerson(ccmLogTail.getTailPerson());
        ccmLogTail.setTailTime(new Date());
        ccmLogTail.setTailContent(user.getName()+"将事件："+ccmEventCasedeal.getCaseName()+"拒签");
        ccmLogTail.setTailCase("任务拒签");
        ccmLogTail.setRelevanceTable("ccm_event_incident");
        ccmLogTail.setRelevanceId(ccmEventCasedeal.getObjId());
        save(ccmLogTail);
    }

    //事件上报日志
    @Transactional(readOnly = false)
    public void addEventLogTail(CcmEventIncident ccmEventIncident, User user){
        CcmLogTail ccmLogTail = new CcmLogTail();
        //为了兼容app上传，传入user
        ccmLogTail.setCreateBy(user);
        ccmLogTail.setUpdateBy(user);
        ccmLogTail.setTailTime(new Date());
        ccmLogTail.setTailContent(user.getName()+"将事件："+ccmEventIncident.getCaseName()+"上报");
        ccmLogTail.setTailCase("事件上报");
        ccmLogTail.setRelevanceTable("ccm_event_incident");
        ccmLogTail.setRelevanceId(ccmEventIncident.getId());
        save(ccmLogTail);
    }

    //事件反馈日志
    @Transactional(readOnly = false)
    public void eventFeedBackLogTail(CcmEventCasedeal ccmEventCasedeal, User user){
        CcmLogTail ccmLogTail = new CcmLogTail();
        //为了兼容app上传，传入user
        ccmLogTail.setCreateBy(user);
        ccmLogTail.setUpdateBy(user);
//		ccmLogTail.setType("");
//		ccmLogTail.setTailPerson(ccmLogTail.getTailPerson());
        ccmLogTail.setTailTime(new Date());
        ccmLogTail.setTailContent(user.getName()+"将事件："+ccmEventCasedeal.getCaseName()+"反馈");
        ccmLogTail.setTailCase("任务反馈");
        ccmLogTail.setRelevanceTable("ccm_event_incident");
        ccmLogTail.setRelevanceId(ccmEventCasedeal.getObjId());
        save(ccmLogTail);
    }
}