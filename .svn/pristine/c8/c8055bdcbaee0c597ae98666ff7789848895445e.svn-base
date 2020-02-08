package com.arjjs.ccm.tool;

import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 实体类工具包
 *
 * @author: li jiupeng
 * @create: 2019-07-03 11:32
 */
public class EntityTools {

    /**
     * 是否为空
     *
     * @return boolean
     */
    public static boolean isEmpty(Object object){

        Class<?> clas=object.getClass();
        Method [] methods=clas.getMethods();
        for (int i = 0; i <methods.length; i++) {
            String name=methods[i].getName();
            if(name.indexOf("get")!=-1 ) {
                try {
                    String getName=name.substring(3,name.length());
                    String variable=getName.substring(0,1).toLowerCase()+getName.substring(1,getName.length());
                    if(clas.getDeclaredField(variable)!=null){
                            System.out.print(variable+"-"+name+"-");
                            Object obj=methods[i].invoke(object);
                           if(obj!=null && obj!="" && !obj.toString().equals("0") && !obj.toString().equals("true") && !obj.toString().equals("null")){
                               return false;
                           }
                    }
                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

        return true;
    }



}
