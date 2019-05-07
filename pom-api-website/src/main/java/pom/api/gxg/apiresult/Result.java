package pom.api.gxg.apiresult;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * 统一API响应结果封装
 *
 * @author fz
 * @version 0.1 (2018年8月15日 上午11:15:49)
 * @see
 * @since 0.1
 */

public class Result {

    private int p_code;
    private String p_message;
    private Object p_param;
    private int p_qty;

    public int getP_qty() {
        return p_qty;
    }

    public Result setP_qty(int p_qty) {
        this.p_qty = p_qty;
        return this;
    }

    public int getP_code() {
        return p_code;
    }

    public Result setP_code(ResultCode p_code) {
        this.p_code = p_code.p_code;
        return this;
    }

    public String getP_message() {
        return p_message;
    }

    public Result setP_message(String p_message) {
        this.p_message = p_message;
        return this;
    }

    public Object getP_param() {
        return p_param;
    }

    public Result setP_param(Object p_param) {
        this.p_param = p_param;
        return this;
    }

    @Override
    public String toString()
    {

        /*try {
            return new String(JSON.toJSONString(this).getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return JSON.toJSONString(this);
    }
}
