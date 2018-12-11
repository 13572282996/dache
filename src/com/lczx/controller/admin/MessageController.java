package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.service.MessageService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminMessageController")
@RequestMapping("/admin/message")
public class MessageController extends BaseController
{
    @Resource(name = "messageServiceImpl")
    private MessageService messageService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/message/add";
    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", messageService.findPage(pageable));
        return "/admin/message/list";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(com.lczx.entity.Message message, RedirectAttributes redirectAttributes)
    {
        if (!isValid(message))
        {
            return ERROR_VIEW;
        }
       // message.setSender("系统管理员");
        messageService.save(message);
        return "redirect:list.jhtml";
    }
    
    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, ModelMap model)
    {
        model.addAttribute("message", messageService.find(id));
        return "/admin/message/view";
    }
    
    /**
   	 * 删除
   	 */
   	@RequestMapping(value = "/delete", method = RequestMethod.POST)
   	public @ResponseBody 
   	Message delete(Long[] ids) {
   		if (ids.length >= messageService.count()) {
   		}
   		messageService.delete(ids);
   		return  Message.success("操作成功","操作成功");
   	}
   	
}
