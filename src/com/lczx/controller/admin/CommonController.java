/*



 */
package com.lczx.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.lczx.util.VerifiCode;


/**
 * 项目名称：
 * 功能模块名称：Controller - 共用
 * 功能描述：
 * @author yideng
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware
{
    
    @Value("${system.name}")
    private String systemName;
    
    @Value("${system.version}")
    private String systemVersion;
    
    @Value("${system.description}")
    private String systemDescription;
    
    @Value("${system.show_powered}")
    private Boolean systemShowPowered;
    
//    @Resource(name = "areaServiceImpl")
//    private AreaService areaService;
//    
//    @Resource(name = "captchaServiceImpl")
//    private CaptchaService captchaService;
//    
//    @Resource(name = "memberServiceImpl")
//    private MemberService memberService;
//    
//    @Resource(name = "messageServiceImpl")
//    private MessageService messageService;
//    
//    @Resource(name = "resourceServiceImpl")
//    private ResourceService resourceService;
    
    /** servletContext */
    private ServletContext servletContext;
    
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }
    
    /**
     * 主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap model)
    {
    	System.out.println("登录重定向到此方法");
        return "/admin/common/main";
    }
    
    /**
     * 首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        model.addAttribute("systemName", systemName);
        model.addAttribute("systemVersion", systemVersion);
        model.addAttribute("systemDescription", systemDescription);
        model.addAttribute("systemShowPowered", systemShowPowered);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("javaHome", System.getProperty("java.home"));
        model.addAttribute("osName", System.getProperty("os.name"));
        model.addAttribute("osArch", System.getProperty("os.arch"));
        model.addAttribute("serverInfo", servletContext.getServerInfo());
        model.addAttribute("servletVersion", servletContext.getMajorVersion()
                + "." + servletContext.getMinorVersion());
//        model.addAttribute("memberCount", memberService.count());
//        model.addAttribute("unreadMessageCount",
//                messageService.count(null, false));
        return "/admin/common/index";
    }
    
//    /**
//     * 地区
//     */
//    @RequestMapping(value = "/area", method = RequestMethod.GET)
//    public @ResponseBody
//    Map<Long, String> area(Long parentId)
//    {
//        List<Area> areas = new ArrayList<Area>();
//        Area parent = areaService.find(parentId);
//        if (parent != null)
//        {
//            areas = new ArrayList<Area>(parent.getChildren());
//        }
//        else
//        {
//            areas = areaService.findRoots();
//        }
//        Map<Long, String> options = new HashMap<Long, String>();
//        for (Area area : areas)
//        {
//            options.put(area.getId(), area.getName());
//        }
//        return options;
//    }
    
    
    @RequestMapping(value = "/verificode", method = RequestMethod.GET)
    public void verificode(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        /*
        1.生成验证码
        2.把验证码上的文本存在session中
        3.把验证码图片发送给客户端
        */
       VerifiCode v=new VerifiCode();     //用我们的验证码类，生成验证码类对象
       BufferedImage image=v.getImage();  //获取验证码
       request.getSession().setAttribute("vcode", v.getText()); //将验证码的文本存在session中
       v.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }
    
   
    
}