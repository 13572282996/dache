package com.lczx.job;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aisino.utils.ResultInfo;
import com.aisino.webs.FPServiceAPIPortTypeProxy;
import com.lczx.entity.Bill;
import com.lczx.entity.Bill.BillStatus;
import com.lczx.entity.Bill.Classify;
import com.lczx.entity.Driver;
import com.lczx.service.BillService;
import com.lczx.service.DriverService;
import com.lczx.service.OrderService;
import com.lczx.util.Filter;
import com.lczx.util.SaopUtil;

@Component("jobUtil")
public class JobUtil
{
    @Resource(name = "billServiceImpl")
    private BillService billService;
    
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    /**
     * 订时短信
     * */
    // @Scheduled(cron = "0/10 * * * * ?")
    public void sendSMS()
    {
        System.out.println("订时任务");
    }
    
    /**
     * 电子发票开据 
     * 
     **/
    //@Scheduled(cron = "0/10 * * * * ?")
    //@Scheduled(cron = "0 0 0/1 * * ?")
    public void bill() throws RemoteException
    {
        FPServiceAPIPortTypeProxy fpServiceAPIPortTypeProxy = new FPServiceAPIPortTypeProxy();
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(Filter.eq("classify", Classify.electron));
        filters.add(Filter.eq("billStatus", BillStatus.uninvoice));
        List<Bill> list = billService.findList(null, filters, null);
        if (list != null && list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                ResultInfo rInfo = fpServiceAPIPortTypeProxy.createUser(SaopUtil.addBillparam(list.get(i)));
                System.out.println(rInfo.getMessage());
                System.out.println(rInfo.getCode());
                if ("0000".equals(rInfo.getCode()))
                {
                    list.get(i).setBillStatus(BillStatus.invoiced);
                    billService.update(list.get(i));
                }
                else
                {
                    list.get(i).setBillStatus(BillStatus.fail);
                    billService.update(list.get(i));
                }
            }
        }
        
    }
    
    /**
     * 计算司机评分数据 
     * 
     **/
    @Scheduled(cron = "0 0 0/4 * * ?")
    public void score()
    {
        List<Driver> drivers = driverService.findAll();
        if (drivers != null && drivers.size() != 0)
        {
            for (int i = 0; i < drivers.size(); i++)
            {
                double driverScore = orderService.driverScore(drivers.get(i)
                        .getDriverPhone());
                if (drivers.get(i).getScore() != driverScore)
                {
                    drivers.get(i).setScore(driverScore);
                    driverService.update(drivers.get(i));
                }
            }
        }
        else
        {
            return;
        }
    }
    
}
