package com.lczx.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 司机收入 
 * 
 **/
@Entity
@Table(name = "dc_income")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_income_sequence")
public class InCome extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -7031715231947679682L;
    
    /**订单ID*/
    private Long orderId;
    
    /**订单金额*/
    private BigDecimal orderAmount;
    
    /**收入金额*/
    private BigDecimal amount;
    
    /**抽成比例*/
    private BigDecimal feeProportion;
    
    /**是否结算*/
    private boolean settlement;
    
    /**司机id*/
    private Driver driver;
    
    @JsonProperty
    public Long getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }
    @JsonProperty
    public BigDecimal getOrderAmount()
    {
        return orderAmount;
    }
    
    public void setOrderAmount(BigDecimal orderAmount)
    {
        this.orderAmount = orderAmount;
    }
    @JsonProperty
    public BigDecimal getAmount()
    {
        return amount;
    }
    
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
    @JsonProperty
    public BigDecimal getFeeProportion()
    {
        return feeProportion;
    }
    
    public void setFeeProportion(BigDecimal feeProportion)
    {
        this.feeProportion = feeProportion;
    }
    @JsonProperty
    public boolean isSettlement()
    {
        return settlement;
    }
    
    public void setSettlement(boolean settlement)
    {
        this.settlement = settlement;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Driver getDriver()
    {
        return driver;
    }
    
    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }
    
}
