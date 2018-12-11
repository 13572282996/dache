package com.lczx.controller.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.DriverInCome;
import com.lczx.service.DriverService;
import com.lczx.service.InComeService;

@Controller("adminInComeController")
@RequestMapping("/admin/income")
public class InComeController extends BaseController
{
    @Resource(name = "inComeServiceImpl")
    private InComeService inComeService;
    
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    /**
     * 查询收入结算信息 
     * driverId　司机ＩＤ
     * settlement　是否结算
     *  month：本月
     *  week：本周
     *  lastMonth：上月
     *  season：本季
     **/
    @RequestMapping(value = "/incomeInfo", method = RequestMethod.GET)
    public String incomeInfo(ModelMap model)
    {
        //已结算总额
        BigDecimal settlementSum = new BigDecimal("0");
        //未结算总额
        BigDecimal unSettlementSum = new BigDecimal("0");
        //本月已结算
        BigDecimal settlementMonth = new BigDecimal("0");
        //本月未结算
        BigDecimal unSettlementMonth = new BigDecimal("0");
        
        //本周已结算
        BigDecimal settlementWeek = new BigDecimal("0");
        //本周未结算
        BigDecimal unSettlementWeek = new BigDecimal("0");
        
        //上月已结算
        BigDecimal settlementLastMonth = new BigDecimal("0");
        //上月未结算
        BigDecimal unSettlementLastMonth = new BigDecimal("0");
        //本季已结算
        BigDecimal settlementLastSeason = new BigDecimal("0");
        //本季未结算
        BigDecimal unSettlementLastSeason = new BigDecimal("0");
        settlementSum = inComeService.incomeSum(null, true, null);
        unSettlementSum = inComeService.incomeSum(null, false, null);
        settlementMonth = inComeService.incomeSum(null, true, "month");
        unSettlementMonth = inComeService.incomeSum(null, false, "month");
        settlementWeek = inComeService.incomeSum(null, true, "week");
        unSettlementWeek = inComeService.incomeSum(null, false, "week");
        settlementLastMonth = inComeService.incomeSum(null, true, "lastMonth");
        unSettlementLastMonth = inComeService.incomeSum(null,
                false,
                "lastMonth");
        settlementLastSeason = inComeService.incomeSum(null, true, "season");
        unSettlementLastSeason = inComeService.incomeSum(null, false, "season");
        model.put("settlementSum", settlementSum);
        model.put("unSettlementSum", unSettlementSum);
        model.put("settlementMonth", settlementMonth);
        model.put("unSettlementMonth", unSettlementMonth);
        model.put("settlementWeek", settlementWeek);
        model.put("unSettlementWeek", unSettlementWeek);
        model.put("settlementLastMonth", settlementLastMonth);
        model.put("unSettlementLastMonth", unSettlementLastMonth);
        model.put("settlementLastSeason", settlementLastSeason);
        model.put("unSettlementLastSeason", unSettlementLastSeason);
        return "/admin/income/info";
    }
    
    /**
     *待结算统计列表 
     * 
     **/
    @RequestMapping(value = "/driverIcomeList", method = RequestMethod.GET)
    public String driverIcomeList(ModelMap model)
    {
        List<DriverInCome> list = inComeService.driverInCome(false);
        model.put("driverInComes", list);
        return "/admin/income/driverlist";
    }
    
    /**
     *已结算统计列表 
     * 
     **/
    @RequestMapping(value = "/driverInComeed", method = RequestMethod.GET)
    public String driverInComeed(ModelMap model)
    {
        List<DriverInCome> list = inComeService.driverInCome(true);
        model.put("driverInComeed", list);
        return "/admin/income/inComeed";
    }
    
    
    
    @RequestMapping(value = "/settlement", method = RequestMethod.GET)
    public String settlement(Long id, ModelMap model,
            RedirectAttributes redirectAttributes)
    {
        inComeService.settlementing(id);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:driverIcomeList.jhtml";
    }
    
}
