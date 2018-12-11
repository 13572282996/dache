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

import com.lczx.entity.Car;
import com.lczx.entity.Driver;
import com.lczx.service.CarService;
import com.lczx.service.DriverService;
import com.lczx.util.Filter;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminCarController")
@RequestMapping("/admin/car")
public class CarController
{
    @Resource(name = "carServiceImpl")
    private CarService carService;
    
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/car/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Car car, RedirectAttributes redirectAttributes)
    {
        Driver driver = driverService.findDriverByMobile(car.getDriverPhone());
        if(driver != null) {
        car.setOwnerName(driver.getDriverName());
        car.setDriverPhone(driver.getDriverPhone());
        }
        carService.save(car);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", carService.findPage(pageable));
        
        return "/admin/car/list";
    }
    /**
     * 详情 
     **/
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        
        model.addAttribute("car", carService.find(id));
        return "/admin/car/view";
    }
    
    /**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody 
	Message delete(Long[] ids) {
		if (ids.length >= carService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		carService.delete(ids);
		return Message.success("操作成功","操作成功");
	}
}
