package com.lczx.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller("loginController")
@RequestMapping("/admin")
public class LoginController 
{
	//跳转登录页面
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public String login(Model model){  
        return "admin/login/login";
    }
	
	
	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login(String username, String password, 
    		RedirectAttributes redirectAttributes, Model model,HttpServletRequest request){ 
	    
	    String session_vcode=(String) request.getSession().getAttribute("vcode");    //从session中获取真正的验证码
        String form_vcode=request.getParameter("vcode"); //获取用户输入的验证码
        if(!(session_vcode.equalsIgnoreCase(form_vcode))) //进行判断
        {
            model.addAttribute("message", "验证码错误");   //如果错误就将错误信息发送给客户端
            return "admin/login/login"; 
        }
		
        try 
        {  
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
        	//执行这段代码会自动调用AuthRealm类。AuthRealm类又会调用CustomCredentialsMatcher。都正常执行才会return登录成功页面
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));  
            return "redirect:/admin/common/main.jhtml";
        }
        catch (LockedAccountException e){
        	model.addAttribute("message","此账号已被锁定");
        	return "admin/login/login"; 
        }
        catch (IncorrectCredentialsException e) {
        	model.addAttribute("message","密码错误，若连续5次密码错误账号将被锁定");
        	return "admin/login/login"; 
		}
        catch (UnknownAccountException e) {
            model.addAttribute("message","账号不存在，请重新输入正确的账号");
            return "admin/login/login"; 
        }
        catch (AuthenticationException e) { 
            model.addAttribute("message","帐号认证失败");
            return "admin/login/login";  
        }
        
    }
	
	//退出
	@RequestMapping(value="/logout",method=RequestMethod.GET)    
    public String logout(RedirectAttributes redirectAttributes ){   
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
        return "admin/login/login";  
    }   
      
    @RequestMapping("/403")  
    public String unauthorizedRole(){  
        return "admin/login/403";  
    }  
	

}
