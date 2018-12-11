package com.lczx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lczx.util.Setting;

/**
 * 乘客实体类 
 * 
 **/
@Entity
@Table(name = "dc_member")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_member_sequence")
public class Member extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4705742306314594526L;
    
    /**姓名*/
    private String name;
    
    /**手机号*/
    private String mobilePhone;
    
    /**姓别*/
    private String sex;
    
    /**年龄*/
    private int age;
    
    /**头像*/
    private String head;
    
    /**身份证*/
    private String iDcard;
    
    /**状态*/
    private String status;
    
    /**认证状态*/
    private String authStatus;
    
    /**登录密码*/
    private String passWord;
    
    /**发票*/
    private Set<Bill> bills = new HashSet<Bill>();
    
    private String token;
    
    @JsonProperty
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Column(nullable = false)
    public String getMobilePhone()
    {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }
    
    public String getSex()
    {
        return sex;
    }
    
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getHead()
    {
        return head;
    }
    
    public void setHead(String head)
    {
        this.head = head;
    }
    
    public String getiDcard()
    {
        return iDcard;
    }
    
    public void setiDcard(String iDcard)
    {
        this.iDcard = iDcard;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getAuthStatus()
    {
        return authStatus;
    }
    
    public void setAuthStatus(String authStatus)
    {
        this.authStatus = authStatus;
    }
    
    @Column(nullable = false)
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
    
    /**
     * @return the bills
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public Set<Bill> getBills()
    {
        return bills;
    }
    
    /**
     * @param bills the bills to set
     */
    public void setBills(Set<Bill> bills)
    {
        this.bills = bills;
    }
    
}
