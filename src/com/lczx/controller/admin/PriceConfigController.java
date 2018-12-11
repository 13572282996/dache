package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.Driver;
import com.lczx.entity.Navigation;
import com.lczx.entity.PriceConfig;
import com.lczx.entity.Ad.Type;
import com.lczx.service.PriceConfigService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminPriceConfigController")
@RequestMapping("/admin/priceConfig")
public class PriceConfigController extends BaseController
{
    @Resource(name = "priceConfigServiceImpl")
    private PriceConfigService priceConfigService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/priceConfig/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(PriceConfig priceConfig,
            RedirectAttributes redirectAttributes)
    {
        priceConfigService.save(priceConfig);
        return "redirect:list.jhtml";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("priceConfig", priceConfigService.find(id));
        return "/admin/priceConfig/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(PriceConfig priceConfig, 
            RedirectAttributes redirectAttributes)
    {
        if (!isValid(priceConfig))
        {
            return ERROR_VIEW;
        }
       
        priceConfigService.update(priceConfig);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", priceConfigService.findPage(pageable));
        
        return "/admin/priceConfig/list";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        priceConfigService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
