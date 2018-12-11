package com.lczx.controller.appInterface;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.Driver;
import com.lczx.service.DriverService;
import com.lczx.service.InComeService;

@Controller("faceInComeController")
@RequestMapping("/api/income")
public class InComeController extends BaseController
{
    @Resource(name = "inComeServiceImpl")
    private InComeService inComeService;
    
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    @RequestMapping(value = "/incomeList", method = RequestMethod.POST)
    public @ResponseBody
    Object incomeList(String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Driver driver = null;
        BigDecimal driverInComeSum = new BigDecimal("0");
        BigDecimal settlement = new BigDecimal("0");
        BigDecimal unSettlement = new BigDecimal("0");
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "incomeList");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "incomeList");
                result.put("errormsg", "登录过期，请重新登录");
                return result;
            }
            //总收入
            driverInComeSum = inComeService.driverInComeSum(driver.getId(),null);
            //已结算
            settlement = inComeService.inComeSum(driver.getId(), true);
            //未结算
            unSettlement = inComeService.inComeSum(driver.getId(), false);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "incomeList");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "incomeList");
        result.put("data", driver.getInComes());
        result.put("driverInComeSum", driverInComeSum);
        result.put("settlement", settlement);
        result.put("unSettlement", unSettlement);
        return result;
    }
}
