package com.arjjs.ccm.tool;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.service.entity.CcmCommunityWork;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.DictUtils;

public class CommUtilRest {

    /**
     * @author Arj
     * @see 用于 规范与复用某些具体方法
     */


    /**
     * 更新实体类部分字段使用，一般用作于修改接口
     *
     * @param objOld
     * @param objNew
     */
    public static void updateDataWithPart(Object objOld, Object objNew) {
        if (objOld == null || objNew == null) {
            return;
        }
        Class newClazz = objNew.getClass();
        Class oldClazz = objOld.getClass();
        Field[] fields = newClazz.getDeclaredFields();
        List<Field> oldAllFields = new ArrayList<>();
        oldAllFields.addAll(Arrays.asList(oldClazz.getDeclaredFields()));
        oldAllFields.addAll(Arrays.asList(oldClazz.getSuperclass().getDeclaredFields()));
        Map<String, Field> oldFieldMap = new HashMap<>(oldAllFields.size());
        for (Field f : oldAllFields) {
            oldFieldMap.put(f.getName(), f);
        }
        try {
            for (Field field : fields) {
                Method newGetMethod = newClazz.getDeclaredMethod(getterMethod(field));
                if (org.springframework.util.StringUtils.isEmpty(newGetMethod.invoke(objNew))) {
                    continue;
                }
                if (!oldFieldMap.containsKey(field.getName())) {
                    continue;
                }
                Method oldSetMethod = oldClazz.getMethod(setterMethod(field), field.getType());
                oldSetMethod.invoke(objOld, newGetMethod.invoke(objNew));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static String getterMethod(Field field) {
        String firstLetter = field.getName().substring(0, 1).toUpperCase();
        return "get" + firstLetter + field.getName().substring(1);
    }

    private static String setterMethod(Field field) {
        String firstLetter = field.getName().substring(0, 1).toUpperCase();
        return "set" + firstLetter + field.getName().substring(1);
    }


    /**
     * @param userId
     * @param req
     * @param resp
     * @param id
     * @return
     * @see
     */
    public static CcmRestResult getResult(String userId, HttpServletRequest req, HttpServletResponse resp, String id) {
        //
        CcmRestResult result = new CcmRestResult();
        // 默认为 有问题 直接返回
        result.setReturnFlag(true);
        // 获取 User
        User sessionUser = (User) req.getSession().getAttribute("user");
        // 当前的 sessionUser 为空
        if (null == sessionUser) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        // 当前的 UserId 为空 或者 sessionUser.getId() 与 传入 userId 一致
        if (StringUtils.isBlank(userId) || (!userId.equals(sessionUser.getId()))) {
            result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
            return result;
        }
        // 当前id为空
        if (StringUtils.isBlank(id)) {// 参数id不对
            result.setCode(CcmRestType.ERROR_PARAM);
            return result;
        }

        // 无问题 直接返回
        result.setReturnFlag(false);
        return result;
    }

    public static CcmRestResult queryResult(String userId, HttpServletRequest req, HttpServletResponse resp) {
        //
        CcmRestResult result = new CcmRestResult();
        // 默认为 有问题 直接返回
        result.setReturnFlag(true);
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
        // 无问题 直接返回
        result.setReturnFlag(false);
        return result;
    }

    public static String getParam(HttpServletRequest request, String type) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return request.getParameter(type);
    }
}
