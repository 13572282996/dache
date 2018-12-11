package com.lczx.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.Admin;
import com.lczx.entity.Driver;
import com.lczx.entity.Member;
import com.lczx.service.DriverService;
import com.lczx.service.OrderService;
import com.lczx.util.Filter;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminDriverController")
@RequestMapping("/admin/driver")
public class DriverController extends BaseController
{
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/driver/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Driver driver, RedirectAttributes redirectAttributes)
    {
        driverService.save(driver);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", driverService.findPage(pageable));
        
        return "/admin/driver/list";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String edit(Long id, Integer pageNumber, ModelMap model)
    {
        
        if (pageNumber == null)
        {
            pageNumber = 1;
        }
        Driver driver = driverService.find(id);
        Pageable pageable = new Pageable(pageNumber, 10);
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(Filter.eq("dirverMobile", driver.getDriverPhone()));
        pageable.setFilters(filters);
        model.addAttribute("driver", driver);
        model.addAttribute("page", orderService.findPage(pageable));
        return "/admin/driver/view";
    }
    
    /**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody 
	Message delete(Long[] ids) {
		if (ids.length >= driverService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		driverService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}
