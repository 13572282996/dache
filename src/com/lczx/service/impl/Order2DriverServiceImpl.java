package com.lczx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.lczx.dao.DriverDao;
import com.lczx.dao.Order2DriverDao;
import com.lczx.entity.Order;
import com.lczx.entity.Order2Driver;
import com.lczx.service.Order2DriverService;
import com.lczx.util.TruckUtil;

@Service("order2driverServiceImpl")
public class Order2DriverServiceImpl extends
        BaseServiceImpl<Order2Driver, Long> implements Order2DriverService
{
    @Resource(name = "order2driverDaoImpl")
    private Order2DriverDao order2DriverDao;
    
    @Resource(name = "driverDaoImpl")
    private DriverDao driverDao;
    
    @Resource(name = "order2driverDaoImpl")
    public void setBaseDao(Order2DriverDao order2DriverDao)
    {
        super.setBaseDao(order2DriverDao);
    }
    
    @Override
    public List<Order2Driver> queryOrder2DriversByMobile(String driverPhone)
    {
        if (driverPhone == null)
        {
            return null;
        }
        return order2DriverDao.queryOrder2DriversByMobile(driverPhone);
    }
    
    //{"status":0,"message":"鎴愬姛","size":1,"total":1,"entities":[{"entity_name":"琚佹枃娉�","create_time":"2018-06-10 04:40:25","modify_time":"2018-06-10 05:04:14","latest_location":{"loc_time":1528578221,"longitude":20.11,"latitude":30.01,"coord_type":"wgs84","speed":170.793,"direction":220,"distance":0}}]}
    @Override
    public void addOrder2Driver(Order order) throws Exception
    {
        if (order == null || order.getLatitude() == null
                || order.getLongitude() == null)
        {
            throw new Exception();
        }
        String str = TruckUtil.aroundsearch(order.getLatitude(),
                order.getLongitude());
        
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        if (jsonObject1.getInt("status") == 0
                && jsonObject1.getInt("size") != 0)
        {
            JSONArray jsonArray = jsonObject1.getJSONArray("entities");
            for (int i = 0; i < jsonArray.size(); i++)
            {
                String driverPhone = jsonArray.getJSONObject(i)
                        .getString("entity_name");
                Order2Driver o2d = new Order2Driver();
                o2d.setOrderId(String.valueOf(order.getId()));
                o2d.setMemberName(order.getMemberName());
                o2d.setMobile(order.getMemberMobile());
                
                try
                {
                    o2d.setDriverId(String.valueOf((driverDao.findDriverByMobile(driverPhone).getId())));
                }
                catch (Exception e)
                {
                    continue;
                }
                o2d.setDriverPhone(driverPhone);
                o2d.setStatus(Order2Driver.Status.unreceive);
                o2d.setStartPoint(order.getStartPoint());
                o2d.setEndPoint(order.getEndPoint());
                super.save(o2d);
            }
        }
    }
}
