package com.lczx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dc_o2d")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_o2d_sequence")
public class Order2Driver extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7582795486817291792L;
    
    /**
     * 
     *状态 
     **/
    public enum Status
    {
        /**待抢单*/
        unreceive,
        /**已抢单*/
        received,
        /**抢单失败*/
        fail,
        /**已取消*/
        cancelled
        
    }
    
    /**订单id*/
    private String orderId;
    
    /**用户手机号码*/
    private String mobile;
    
    /**用户名称*/
    private String memberName;
    
    /**司机id*/
    private String driverId;
    
    /**司机手机号码*/
    private String driverPhone;
    
    /**状态*/
    private Status status;
    
    /**起点*/
    private String startPoint;
    
    /**终点*/
    private String endPoint;

    @JsonProperty
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }
    @JsonProperty
    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    @JsonProperty
    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }
    @JsonProperty
    public String getDriverId()
    {
        return driverId;
    }

    public void setDriverId(String driverId)
    {
        this.driverId = driverId;
    }
    @JsonProperty
    public String getDriverPhone()
    {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone)
    {
        this.driverPhone = driverPhone;
    }
    @JsonProperty
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
    
    @JsonProperty
    public String getStartPoint()
    {
        return startPoint;
    }

    public void setStartPoint(String startPoint)
    {
        this.startPoint = startPoint;
    }
    @JsonProperty
    public String getEndPoint()
    {
        return endPoint;
    }

    public void setEndPoint(String endPoint)
    {
        this.endPoint = endPoint;
    }
    
    
    
    
}
