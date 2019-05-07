package pom.api.gxg.interceptor;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.pac.sdk.cp.dataobject.request.MODUAN_ORDER_CREATE.request;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

import pom.api.gxg.apiresult.Result;
import pom.api.gxg.apiresult.ResultCode;
import pom.api.gxg.exception.ServiceException;
import pom.api.gxg.exception.UserPwdException;

/**
 * @author fz
 * @version 0.1 (2018年8月16日 下午3:01:55)
 * @since 0.1
 * @see
 */

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
	
	@Autowired
	private Environment env;


	private String apiver="1.0";

    private static HashMap<String, String> keySecret = null;



	static {
        keySecret = new HashMap<String, String>();

        keySecret.put("qimen", "s83jfsuh3dfik3hj");


    }

	/**
     * 消息内容转换配置
     * 配置fastJson返回json转换
     * @param converters
     */
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, //保留空的字段
                SerializerFeature.WriteNullStringAsEmpty, //String null -> ""
                SerializerFeature.WriteNullNumberAsZero, //Number null -> 0
                SerializerFeature.DisableCircularReferenceDetect); // 禁止循环引用
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }
	
	/**
	 * 统一异常处理
	 */
	@Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof ServiceException) { //业务失败的异常
                    result.setP_code(ResultCode.SERVICE_FAIL).setP_message(e.getMessage());
                    logger.info(e.getMessage());
                }else if (e instanceof NoHandlerFoundException) {
                    result.setP_code(ResultCode.NOT_FOUND).setP_message("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setP_code(ResultCode.FAIL).setP_message(e.getMessage());
                } else if(e instanceof UserPwdException){//用户密码异常
                	result.setP_code(ResultCode.PWD_ERROR).setP_message(e.getMessage()).setP_param(((UserPwdException) e).getData());
                    logger.info(e.getMessage());
                }else {
                	result.setP_code(ResultCode.INTERNAL_SERVER_ERROR).setP_message("服务器内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                //如果是界面转发报错，就直接跳转错误页面
                ModelAndView mav = new ModelAndView();
                responseResult(response, result);
                return mav;
            }
        });
    }
	
	private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
            response.getWriter().flush();
			response.getWriter().close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
	
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


    	 // 接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        if ("dev".equals(env.getActiveProfiles()[0])) { //开发环境忽略签名认证-此处暂未启用,SSO后启用
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                    //request.setCharacterEncoding("UTF-8");
                    /*InputStream in = request.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in, "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        buffer.append(line);
                    }

                    java.net.URLDecoder.decode(buffer.toString(),"utf-8");*/
                    // 验证签名
                    boolean pass =true; //validateSign(request);
                    if (pass) {
                        return true;
                    } else {
                        logger.warn("接口认证失败，请求接口：{}，请求ip：{}，请求参数：{}",
                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

                        Result result = new Result();
                        result.setP_code(ResultCode.UNAUTHORIZED).setP_message("接口认证失败");
                        responseResult(response, result);
                        return false;
                    }
                }
            });
        }
    }


    public boolean Sign(HttpServletRequest request){


        String key = request.getParameter("key");
        String requestTime = request.getParameter("requestTime");
        String data = request.getParameter("data");
        String sign = request.getParameter("sign");
        String version = request.getParameter("version");
        String func=request.getParameter("func");
        if (null == key || key.isEmpty() || null == keySecret.get(key)
                || null == requestTime || requestTime.isEmpty()
                || null == version || (0 != version.compareTo(apiver))
                || null == data || data.isEmpty()
                || null == sign || sign.isEmpty())
        {
            return false;
        }

        String content = "key=" + key + "&requestTime=" + requestTime +
                "&secret=" + keySecret.get(key) + "&version=" + apiver + "&func=" + func +
                "&data=" + data;
        String checkSign = DigestUtils.md5Hex(content);

        return StringUtils.equals(sign,checkSign); //比较

    }


    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    public boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getParameter("sign"); //获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign"); //排除sign参数
        Collections.sort(keys); //排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&"); //拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1); //去除最后一个'&'

        String secret = "Potato";//密钥，自己修改
        String sign = DigestUtils.md5Hex(linkString + secret); //混合密钥md5
        return StringUtils.equals(sign, requestSign); //比较
    }
    
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
    
}
