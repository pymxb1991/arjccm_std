package com.arjjs.ccm.tool;

/**
 * 返回JSON 工具类
 *
 * @author: li jiupeng
 * @create: 2019-07-12 14:49
 */
public class JsonResult {

    private static final Integer OK=200;
    private static final Integer ERROR=500;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public static JsonResult ok(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        jsonResult.setStatus(JsonResult.OK);
        return jsonResult;

    }
    public static JsonResult ok(Object data,String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        jsonResult.setMsg(msg);
        jsonResult.setStatus(JsonResult.OK);
        return jsonResult;
    }
    public static JsonResult error(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        jsonResult.setStatus(JsonResult.ERROR);
        return jsonResult;

    }
    public static JsonResult error(Object data,String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        jsonResult.setMsg(msg);
        jsonResult.setStatus(JsonResult.ERROR);
        return jsonResult;
    }
    public static JsonResult error(String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMsg(msg);
        jsonResult.setStatus(JsonResult.ERROR);
        return jsonResult;
    }




    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
