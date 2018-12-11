/**
  * 文  件   名:  Driver.java
  * 功能描述：<描述>
  *	修改时间:  2018年6月6日
  * 修   改  人:Administrator
  * 版  本   号： 1.0 2018年6月6日
  * Copyright: Copyright (c) LCZX Co.,Ltd.
  * Company: 成都联创众享科技有限公司 www.lczxtech.com
  */
package com.lczx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lczx.util.SettingUtils;

/**
 * 功能描述：<描述>
 * 详细描述：<功能详细描述>
 * 修改时间:  2018年6月6日
 * @author  Administrator
 * @version  [版本号, 2018年6月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "dc_driver")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_driver_sequence")
public class Driver extends BaseEntity
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5770390080910879955L;
    
    public enum Gender
    {
        
        /** 男 */
        male,
        
        /** 女 */
        female
    }
    
    public enum MaritalStatus
    {
        /**未婚*/
        unmarried,
        /**已婚*/
        married,
        /**离异*/
        divorce
    }
    
    /**司机名称*/
    private String driverName;
    
    /**手机号码*/
    private String driverPhone;
    
    /**所属公司*/
    private String companyName;
    
    private Admin admin;
    
    /**注册区域*/
    private String address;
    
    /**性别*/
    private Gender driverGender;
    
    /**出生日期*/
    private Date driverBirthday;
    
    /**身份证号码*/
    private String idCard;
    
    /**车牌号*/
    private String vehicleNo;
    
    /**国籍*/
    private String driverNationality;
    
    /**民族*/
    private String driverNation;
    
    /**婚姻状况*/
    private MaritalStatus driverMaritalStatus;
    
    /**外语能力*/
    private String driverLanguageLevel;
    
    /**学历*/
    private String driverEducation;
    
    /**户口登记机关名称*/
    private String driverCensus;
    
    /**户口住址或长住地址*/
    private String driverAddress;
    
    /**驾驶员通信地址*/
    private String driverContactAddress;
    
    /**紧急情况联系人*/
    private String emergencyContact;
    
    /**紧急情况联系人电话*/
    private String emergencyContactPhone;
    
    /**紧急情况联系人通信地址*/
    private String emergencyContactAddress;
    
    /**状态*/
    private boolean state;
    
    /**
     * 驾驶证信息 
     * 
     **/
    private DriverLicense dirverLicense;
    
    /**
     * 网约车资格证信息 
     * 
     **/
    private NetWorkCarLicense netWorkCarLicense;
    
    /**车辆信息*/
    private Car car;
    
    /**登录密码*/
    private String passWord;
    
    /**token*/
    private String token;
    
    /**司机星级*/
    private double score = 5;
    
    private Set<InCome> inComes = new HashSet<InCome>();
    
    private Set<Order> orders = new HashSet<Order>();
    
    @JsonProperty
    public String getDriverName()
    {
        return driverName;
    }
    
    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
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
    public String getCompanyName()
    {
        return companyName;
    }
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    public Admin getAdmin()
    {
        return admin;
    }
    
    public void setAdmin(Admin admin)
    {
        this.admin = admin;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public Gender getDriverGender()
    {
        return driverGender;
    }
    
    public void setDriverGender(Gender driverGender)
    {
        this.driverGender = driverGender;
    }
    
    public Date getDriverBirthday()
    {
        return driverBirthday;
    }
    
    public void setDriverBirthday(Date driverBirthday)
    {
        this.driverBirthday = driverBirthday;
    }
    
    public String getDriverNationality()
    {
        return driverNationality;
    }
    
    public void setDriverNationality(String driverNationality)
    {
        this.driverNationality = driverNationality;
    }
    
    public String getDriverNation()
    {
        return driverNation;
    }
    
    public void setDriverNation(String driverNation)
    {
        this.driverNation = driverNation;
    }
    
    public MaritalStatus getDriverMaritalStatus()
    {
        return driverMaritalStatus;
    }
    
    public void setDriverMaritalStatus(MaritalStatus driverMaritalStatus)
    {
        this.driverMaritalStatus = driverMaritalStatus;
    }
    
    public String getDriverLanguageLevel()
    {
        return driverLanguageLevel;
    }
    
    public void setDriverLanguageLevel(String driverLanguageLevel)
    {
        this.driverLanguageLevel = driverLanguageLevel;
    }
    
    public String getDriverEducation()
    {
        return driverEducation;
    }
    
    public void setDriverEducation(String driverEducation)
    {
        this.driverEducation = driverEducation;
    }
    
    public String getDriverCensus()
    {
        return driverCensus;
    }
    
    public void setDriverCensus(String driverCensus)
    {
        this.driverCensus = driverCensus;
    }
    
    public String getDriverAddress()
    {
        return driverAddress;
    }
    
    public void setDriverAddress(String driverAddress)
    {
        this.driverAddress = driverAddress;
    }
    
    public String getDriverContactAddress()
    {
        return driverContactAddress;
    }
    
    public void setDriverContactAddress(String driverContactAddress)
    {
        this.driverContactAddress = driverContactAddress;
    }
    
    public String getEmergencyContact()
    {
        return emergencyContact;
    }
    
    public void setEmergencyContact(String emergencyContact)
    {
        this.emergencyContact = emergencyContact;
    }
    
    public String getEmergencyContactPhone()
    {
        return emergencyContactPhone;
    }
    
    public void setEmergencyContactPhone(String emergencyContactPhone)
    {
        this.emergencyContactPhone = emergencyContactPhone;
    }
    
    public String getEmergencyContactAddress()
    {
        return emergencyContactAddress;
    }
    
    public void setEmergencyContactAddress(String emergencyContactAddress)
    {
        this.emergencyContactAddress = emergencyContactAddress;
    }
    
    public boolean isState()
    {
        return state;
    }
    
    public void setState(boolean state)
    {
        this.state = state;
    }
    
    public DriverLicense getDirverLicense()
    {
        return dirverLicense;
    }
    
    public void setDirverLicense(DriverLicense dirverLicense)
    {
        this.dirverLicense = dirverLicense;
    }
    
    public NetWorkCarLicense getNetWorkCarLicense()
    {
        return netWorkCarLicense;
    }
    
    public void setNetWorkCarLicense(NetWorkCarLicense netWorkCarLicense)
    {
        this.netWorkCarLicense = netWorkCarLicense;
    }
    
    public Car getCar()
    {
        return car;
    }
    
    public void setCar(Car car)
    {
        this.car = car;
    }
    
    public String getPassWord()
    {
        return passWord;
    }
    
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
    
    public String getToken()
    {
        return token;
    }
    
    public void setToken(String token)
    {
        this.token = token;
    }
    
    @JsonProperty
    public String getIdCard()
    {
        return idCard;
    }
    
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }
    
    @JsonProperty
    public String getVehicleNo()
    {
        return vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }
    
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    public Set<InCome> getInComes()
    {
        return inComes;
    }
    
    public void setInComes(Set<InCome> inComes)
    {
        this.inComes = inComes;
    }
    
    @OneToMany(mappedBy = "dirvers", fetch = FetchType.LAZY)
    public Set<Order> getOrders()
    {
        return orders;
    }
    
    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }
    
    @JsonProperty
    public double getScore()
    {
        return score;
    }
    
    public void setScore(double score)
    {
        this.score = score;
    }
    
}
