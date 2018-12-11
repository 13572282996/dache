package com.lczx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.AdminDao;
import com.lczx.dao.Order2DriverDao;
import com.lczx.dao.OrderDao;
import com.lczx.dao.SnDao;
import com.lczx.entity.Driver;
import com.lczx.entity.Order;
import com.lczx.entity.Order.PaymentStatus;
import com.lczx.entity.Order2Driver;
import com.lczx.entity.Sn;
import com.lczx.entity.Order.OrderStatus;
import com.lczx.service.OrderService;

@Service("orderServiceImpl")
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements
        OrderService
{
    
    @Resource(name = "orderDaoImpl")
    private OrderDao orderDao;
    
    @Resource(name = "snDaoImpl")
    private SnDao snDao;
    
    @Resource(name = "order2driverDaoImpl")
    private Order2DriverDao order2DriverDao;
    
    @Resource(name = "orderDaoImpl")
    public void setBaseDao(OrderDao orderDao)
    {
        super.setBaseDao(orderDao);
    }
    
    @Override
    @Transactional
    public void save(Order entity)
    {
        entity.setNum(snDao.generate(Sn.Type.order));
        entity.setOrderStatus(OrderStatus.unconfirmed);
        entity.setPaymentStatus(PaymentStatus.unpaid);
        super.save(entity);
    }
    
    @Override
    @Transactional
    public Order receiptOrder(Driver driver, String orderId)
    {
        if (driver == null || orderId == null)
        {
            return null;
        }
        Order order = orderDao.find(Long.valueOf(orderId));
        if (order == null)
        {
            return null;
        }
        order.setDirverName(driver.getDriverName());
        order.setDirverMobile(driver.getDriverPhone());
        // order.setDirver(driver);
        order.setPlateNum(driver.getVehicleNo());
        order.setOrderStatus(Order.OrderStatus.unreceive);
        super.update(order);
        List<Order2Driver> list = order2DriverDao.queryOrder2DriversByOrderId(orderId);
        if (list == null || list.size() == 0)
        {
            return null;
            
        }
        //修改抢单状态
        for (int i = 0; i < list.size(); i++)
        {
            Order2Driver o2d = list.get(i);
            if (Long.valueOf(o2d.getDriverId()) == driver.getId())
            {
                o2d.setStatus(Order2Driver.Status.received);
            }
            else
            {
                o2d.setStatus(Order2Driver.Status.fail);
            }
            order2DriverDao.merge(o2d);
            
        }
        return order;
    }
    
    @Override
    public Order queryOrderbyNum(String orderNum)
    {
        
        return orderDao.queryOrderByNum(orderNum);
    }
    
    @Override
    public List<Order> queryOrderByDriver(String driver)
    {
        return null;
    }
    
    @Override
    public Long orderCountByToday(String driver)
    {
        return orderDao.orderCountByToday(driver);
    }
    
    @Override
    public Long orderCountByMonth(String driver)
    {
        return orderDao.orderCountByMonth(driver);
    }
    
    @Override
    public boolean cancelOrder(String orderId)
    {
        Order order = orderDao.find(Long.valueOf(orderId));
        if (order == null)
        {
            return false;
        }
        order.setOrderStatus(OrderStatus.cancelled);
        order.setCancellType(Order.CancellType.member);
        super.update(order);
        List<Order2Driver> list = order2DriverDao.queryOrder2DriversByOrderId(orderId);
        if (list == null || list.size() == 0)
        {
            return true;
            
        }
        //修改抢单状态
        for (int i = 0; i < list.size(); i++)
        {
            Order2Driver o2d = list.get(i);
            o2d.setStatus(Order2Driver.Status.cancelled);
            order2DriverDao.merge(o2d);
            
        }
        return true;
    }
    
    @Override
    public double driverScore(String driver)
    {
        return orderDao.driverScore(driver);
    }
    
}
