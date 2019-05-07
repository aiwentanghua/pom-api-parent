package pom.api.gxg.Util;

import org.springframework.beans.factory.annotation.Value;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class E3requestUtil {




    //E3调用格式
    public static HashMap<String,String> getE3dataRequest(String apiname,String data,String key,String secret,String e3ver){

        SimpleDateFormat dfTemp = new SimpleDateFormat("yyyyMMddHHmmss");
        String curTime = dfTemp.format(new Date());

        String signString="key="+key+"&requestTime="+curTime+
                "&secret="+secret+"&version="+e3ver+
                "&serviceType="+apiname+"&data="+data;

        String sign = secritMD5(signString,"UTF-8");

        HashMap<String,String> hmParams=new HashMap<String, String>();

        hmParams.put("key", key);
        hmParams.put("version",e3ver);
        hmParams.put("requestTime",curTime);
        hmParams.put("serviceType",apiname);
        hmParams.put("format","json");
        hmParams.put("sign",sign);
        hmParams.put("data",data);
        return hmParams;
        ///Users/tang/Desktop/pom-api-parent/pom-api-website/src/main/java/pom/api/gxg/apiresult
    }




    //MD5加密
    public static String secritMD5(String orgString,String codename){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == codename || codename.isEmpty())
                md.update(orgString.getBytes("UTF-8"));
            else
                md.update(orgString.getBytes(codename));
            byte b[] = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
