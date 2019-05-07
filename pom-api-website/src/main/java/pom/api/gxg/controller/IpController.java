package pom.api.gxg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pom.api.gxg.Util.IpUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IpController {


    @RequestMapping(value = "/getIp", method = RequestMethod.POST)
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }
}
