package pom.api.gxg.Util;


import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * by mofeng
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj
                    : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str
                    : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "  \"order_sn\" : null,\n" +
                "  \"type\" : [ \"1\", \"1\", \"1\", \"1\" ],\n" +
                "  \"paymount\" : [ 5830 ],\n" +
                "  \"pricelist\" : [ 518, 518, 1899, 498 ],\n" +
                "  \"createtime\" : \"20190116 11:03:45\",\n" +
                "  \"storecode\" : \"9999991\",\n" +
                "  \"qty\" : [ 1, 1, 2, 2 ],\n" +
                "  \"productno\" : [ \"A230102900713\", \"A230102900714\", \"5482603684754\", \"0215100180500\" ],\n" +
                "  \"priceactual\" : [ 518, 518, 1899, 498 ],\n" +
                "  \"cashtime\" : \"20190116 11:10:40\",\n" +
                "  \"receiver_MOBILE\" : \"\",\n" +
                "  \"c_PROVINCE_ID__NAME\" : \"\",\n" +
                "  \"receiver_PHONE\" : \"\",\n" +
                "  \"receiver_ZIP\" : \"\",\n" +
                "  \"receiver_ADDRESS\" : \"\",\n" +
                "  \"receiver_NAME\" : \"\",\n" +
                "  \"c_CITY_ID__NAME\" : \"\",\n" +
                "  \"c_DISTRICT_ID__NAME\" : \"\"\n" +
                "}";
        /*
         * User user = new User(); user.setId(2);
         * user.setAccount("geely@happymmall.com"); user.setCreateTime(new Date());
         * String userJsonPretty = JsonUtil.obj2StringPretty(user);
         * log.info("userJson:{}",userJsonPretty);
         *
         *
         * User user2 = JsonUtil.string2Obj(userJsonPretty, User.class);
         * System.out.println(user2);
         */

    }

}