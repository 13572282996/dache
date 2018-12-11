package com.lczx.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "dc_priceConfig")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_priceConfig_sequence")
public class PriceConfig extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**基础价格*/
    private BigDecimal basePrice;
    
    /**公里数（含）*/
    private BigDecimal miles;
    
    /**基础时长*/
    private BigDecimal baseTime;
    
    /**里程价格*/
    private BigDecimal milPrice;
    
    /**时长价格*/
    private BigDecimal timePrice;
    
    /**回空里程*/
    private BigDecimal haulbackMil;
    
    /**回空里程附加费*/
    private BigDecimal haulbackPrice;
    
    /**夜间里程附加费*/
    private BigDecimal nightPrice;
    
    /**早高峰计价倍数*/
    private BigDecimal morningPeak;
    
    /**晚高峰计价倍数*/
    private BigDecimal nightPeak;
    
    public enum Type
    {
        /**实时*/
        realTime,
        /**预约*/
        appointment
    }
    
    public enum CarType
    {
        /**舒适型*/
        comfort,
        /**豪华型*/
        luxury,
        /**商务型*/
        business
    }
    
    private Type type;
    
    private CarType carType;

    /**
     * @return the basePrice
     */
    public BigDecimal getBasePrice()
    {
        return basePrice;
    }

    /**
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(BigDecimal basePrice)
    {
        this.basePrice = basePrice;
    }

    /**
     * @return the miles
     */
    public BigDecimal getMiles()
    {
        return miles;
    }

    /**
     * @param miles the miles to set
     */
    public void setMiles(BigDecimal miles)
    {
        this.miles = miles;
    }

    /**
     * @return the baseTime
     */
    public BigDecimal getBaseTime()
    {
        return baseTime;
    }

    /**
     * @param baseTime the baseTime to set
     */
    public void setBaseTime(BigDecimal baseTime)
    {
        this.baseTime = baseTime;
    }

    /**
     * @return the milPrice
     */
    public BigDecimal getMilPrice()
    {
        return milPrice;
    }

    /**
     * @param milPrice the milPrice to set
     */
    public void setMilPrice(BigDecimal milPrice)
    {
        this.milPrice = milPrice;
    }

    /**
     * @return the timePrice
     */
    public BigDecimal getTimePrice()
    {
        return timePrice;
    }

    /**
     * @param timePrice the timePrice to set
     */
    public void setTimePrice(BigDecimal timePrice)
    {
        this.timePrice = timePrice;
    }

    /**
     * @return the haulbackMil
     */
    public BigDecimal getHaulbackMil()
    {
        return haulbackMil;
    }

    /**
     * @param haulbackMil the haulbackMil to set
     */
    public void setHaulbackMil(BigDecimal haulbackMil)
    {
        this.haulbackMil = haulbackMil;
    }

    /**
     * @return the haulbackPrice
     */
    public BigDecimal getHaulbackPrice()
    {
        return haulbackPrice;
    }

    /**
     * @param haulbackPrice the haulbackPrice to set
     */
    public void setHaulbackPrice(BigDecimal haulbackPrice)
    {
        this.haulbackPrice = haulbackPrice;
    }

    /**
     * @return the nightPrice
     */
    public BigDecimal getNightPrice()
    {
        return nightPrice;
    }

    /**
     * @param nightPrice the nightPrice to set
     */
    public void setNightPrice(BigDecimal nightPrice)
    {
        this.nightPrice = nightPrice;
    }

    /**
     * @return the morningPeak
     */
    public BigDecimal getMorningPeak()
    {
        return morningPeak;
    }

    /**
     * @param morningPeak the morningPeak to set
     */
    public void setMorningPeak(BigDecimal morningPeak)
    {
        this.morningPeak = morningPeak;
    }

    /**
     * @return the nightPeak
     */
    public BigDecimal getNightPeak()
    {
        return nightPeak;
    }

    /**
     * @param nightPeak the nightPeak to set
     */
    public void setNightPeak(BigDecimal nightPeak)
    {
        this.nightPeak = nightPeak;
    }

    /**
     * @return the type
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * @return the carType
     */
    public CarType getCarType()
    {
        return carType;
    }

    /**
     * @param carType the carType to set
     */
    public void setCarType(CarType carType)
    {
        this.carType = carType;
    }
    
    
    
}
