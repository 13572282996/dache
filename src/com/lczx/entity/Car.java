package com.lczx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 车辆信息 
 * 
 **/
@Entity
@Table(name = "dc_car")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_car_sequence")
public class Car extends BaseEntity
{
    /**
     * 
     */
    private static final long serialVersionUID = 1770277818573293764L;
    
    /**公司名称*/
    private String company;
    
    /**注册地*/
    private String address;
    
    /**车辆号牌*/
    private String vehicleNo;
    
    /**车牌颜色*/
    private String plateColor;
    
    /**核定载客位*/
    private int seats;
    
    /**车辆厂牌*/
    private String brand;
    
    /**车辆型号*/
    private String model;
    
    /**车辆类型*/
    private String vehicleType;
    
    /**车辆所有人*/
    private String ownerName;
    
    /**车身颜色*/
    private String vehicleColor;
    
    /**发动机号*/
    private String engineId;
    
    /**车辆VIN码*/
    private String vIN;
    
    /**车辆注册日期*/
    private String certifyDateA;
    
    /**车辆燃料类型*/
    private String fuelType;
    
    /**发动机排量*/
    private String engineDisplace;
    
    /**车辆照片文件*/
    private String photoUrl;
    
    /**服务类型*/
    //private String CommercialType;
    
    /**运价类型编码*/
    // private String FareType;
    
    /**状态*/
    private boolean state;
    
    /**司机*/
    private Driver driver;
    
    /**司机手机号码*/
    private String driverPhone;
    
    public String getCompany()
    {
        return company;
    }
    
    public void setCompany(String company)
    {
        this.company = company;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getVehicleNo()
    {
        return vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }
    
    public String getPlateColor()
    {
        return plateColor;
    }
    
    public void setPlateColor(String plateColor)
    {
        this.plateColor = plateColor;
    }
    
    public int getSeats()
    {
        return seats;
    }
    
    public void setSeats(int seats)
    {
        this.seats = seats;
    }
    @JsonProperty
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    @JsonProperty
    public String getModel()
    {
        return model;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public String getVehicleType()
    {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }
    
    public String getOwnerName()
    {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }
    @JsonProperty
    public String getVehicleColor()
    {
        return vehicleColor;
    }
    
    public void setVehicleColor(String vehicleColor)
    {
        this.vehicleColor = vehicleColor;
    }
    
    public String getEngineId()
    {
        return engineId;
    }
    
    public void setEngineId(String engineId)
    {
        this.engineId = engineId;
    }
    
    public String getvIN()
    {
        return vIN;
    }
    
    public void setvIN(String vIN)
    {
        this.vIN = vIN;
    }
    
    public String getCertifyDateA()
    {
        return certifyDateA;
    }
    
    public void setCertifyDateA(String certifyDateA)
    {
        this.certifyDateA = certifyDateA;
    }
    
    public String getFuelType()
    {
        return fuelType;
    }
    
    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
    }
    
    public String getEngineDisplace()
    {
        return engineDisplace;
    }
    
    public void setEngineDisplace(String engineDisplace)
    {
        this.engineDisplace = engineDisplace;
    }
    
    public String getPhotoUrl()
    {
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }
    
    public boolean isState()
    {
        return state;
    }
    
    public void setState(boolean state)
    {
        this.state = state;
    }
    
    public Driver getDriver()
    {
        return driver;
    }
    
    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }
    
    public String getDriverPhone()
    {
        return driverPhone;
    }
    
    public void setDriverPhone(String driverPhone)
    {
        this.driverPhone = driverPhone;
    }
    
}
