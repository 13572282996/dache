package com.lczx.entity;

import java.math.BigDecimal;

public class DriverInCome extends BaseEntity
{
    /**
     * 
     */
    private static final long serialVersionUID = 2310839061663185060L;
    
    /**姓名*/
    private String name;
    
    /**手机号*/
    private String mobile;
    
    /**未结算金额*/
    private BigDecimal amount;
    
    /**身份证号*/
    private String idCard;
    
    /**车牌号*/
    private String vehicleNo;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public BigDecimal getAmount()
    {
        return amount;
    }
    
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
    
    public String getIdCard()
    {
        return idCard;
    }
    
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }
    
    public String getVehicleNo()
    {
        return vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }
    
}
