package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.CancelCauseConfig;
import com.lczx.entity.Car;
import com.lczx.entity.Driver;
import com.lczx.service.CancelCauseConfigService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminCancelCauseConfigController")
@RequestMapping("/admin/cause")
public class CancelCauseConfigController extends BaseController
{
    @Resource(name = "cancelCauseServiceImpl")
    private CancelCauseConfigService cancelCauseConfigService;
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", cancelCauseConfigService.findPage(pageable));
        
        return "/admin/cause/list";
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/cause/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(CancelCauseConfig cause,
            RedirectAttributes redirectAttributes)
    {
        cancelCauseConfigService.save(cause);
        return "redirect:list.jhtml";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        if (ids.length >= cancelCauseConfigService.count()) {
            return Message.error("admin.common.deleteAllNotAllowed");
        }
        cancelCauseConfigService.delete(ids);
        return SUCCESS_MESSAGE;
    }
    
}
