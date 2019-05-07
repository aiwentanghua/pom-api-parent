package generation;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pom.api.gxg.Util.Utils;
import pom.api.gxg.apiresult.ResultGenerator;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {


    public static void main(String[] args) {

        try{
            //Integer.valueOf()

        }catch (Exception e){

        }
        String a="1";

        //String.valueOf()
        if(!"1".equals(a)){

            System.out.println(111);
        }else {


            System.out.println(2123);
        }

    /*        //处理打印单子面单数据
        JSONObject print=new JSONObject();
        JSONObject task=new JSONObject();
        JSONArray documents=new JSONArray();
        JSONObject json=new JSONObject();
        JSONArray contents=new JSONArray();
        JSONObject jsondata=new JSONObject();

        print.put("cmd","print");
        print.put("requestID","requestID");
        print.put("version","1.0");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
        task.put("taskID","requestID"+sdf.format(date));
        task.put("preview",false);
        task.put("printer","");
        task.put("notifyMode","allInOne");
        task.put("previewType","pdf");

        json.put("documentID","documentID");

        jsondata.put("data","data");

        contents.add(jsondata);
        json.put("contents",contents);

        documents.add(json);
        task.put("documents",documents);



        print.put("task",task);

        System.out.println(print.toString());

        System.out.println(nDaysBetweenTwoDate("2017-7-11 00:00:00","2019-4-17 00:00:00"));
*/

    }

    public static int nDaysBetweenTwoDate(String firstString, String secondString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse(firstString);
            secondDate = df.parse(secondString);
        } catch (Exception e) {
            // 日期型字符串格式错误
            System.out.println("日期型字符串格式错误");
        }
        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
        return nDay;}

   /* public static void main(String[] args) {



        String datas="{\\\"data\\\":{\\\"cpCode\\\":\\\"YTO\\\",\\\"parent\\\":false,\\\"routingInfo\\\":{\\\"routeCode\\\":\\\"386-475-00 001\\\",\\\"sortation\\\":{\\\"name\\\":\\\"386玉环\\\"},\\\"startCenter\\\":{},\\\"origin\\\":{\\\"code\\\":\\\"571509\\\",\\\"name\\\":\\\"BW留下配送站\\\"},\\\"receiveBranch\\\":{\\\"code\\\":\\\"576025\\\"},\\\"consolidation\\\":{\\\"code\\\":\\\"576901\\\",\\\"name\\\":\\\"区域件\\\"},\\\"terminalCenter\\\":{}},\\\"extroInfo\\\":{\\\"O2O\\\":{\\\"watermark\\\":\\\"菜鸟裹裹\\\"}},\\\"shippingOption\\\":{\\\"code\\\":\\\"STANDARD_EXPRESS\\\",\\\"title\\\":\\\"标准快递\\\"},\\\"needEncrypt\\\":false,\\\"_dataFrom\\\":\\\"waybill\\\",\\\"sender\\\":{\\\"address\\\":{\\\"zipCode\\\":\\\"315100\\\",\\\"province\\\":\\\"浙江省\\\",\\\"city\\\":\\\"宁波市\\\",\\\"district\\\":\\\"鄞州区\\\",\\\"detail\\\":\\\"浙江省宁波市鄞州区中山东路2266号东部银泰城2楼265号gxg.jeans\\\"},\\\"phone\\\":\\\"0574-83893912\\\",\\\"name\\\":\\\"王琴\\\"},\\\"recipient\\\":{\\\"address\\\":{\\\"zipCode\\\":\\\"317600\\\",\\\"province\\\":\\\"浙江省\\\",\\\"city\\\":\\\"台州市\\\",\\\"district\\\":\\\"玉环市\\\",\\\"detail\\\":\\\"浙江省台州市玉环市   沙门镇滨港工业区百沃达铜业有限公司\\\"},\\\"phone\\\":\\\"13665781112\\\",\\\"name\\\":\\\"唐灵国\\\"},\\\"waybillCode\\\":\\\"990007415964638174\\\",\\\"ItemName\\\":\\\"连帽长款风衣\\\",\\\"packageInfo\\\":{\\\"items\\\":[{\\\"count\\\":1,\\\"name\\\":\\\"连帽长款风衣\\\"}]},\\\"tradeOrderCode\\\":\\\"211563732\\\"},\\\"signature\\\":\\\"MD:i53XxpQVljqtQjYIbBVxNw==\\\",\\\"templateURL\\\":\\\"http://cloudprint.cainiao.com/template/standard/241410\\\"}";
        String data=datas.replace("\\","");
        System.out.println(data);
        System.out.println("\\");

        JSONObject json = new JSONObject();

        json.put("A", "1");
        json.put("B", "2");
        json.put("C", "3");
        json.put("D", "4");
        System.out.println(json.toString());
        JSONObject json1 = new JSONObject();
        json1.put("E",json.toString());
        System.out.println(json1);
        System.out.println(json1.getString("E"));
        JSONObject json2=JSONObject.parseObject(json1.getString("E"));
        System.out.println(json2.toString());
    }*/


    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr ip: " + ip);
        }
        System.out.println("获取客户端ip: " + ip);
        return ip;
    }


    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("无二和解");
    }
}
