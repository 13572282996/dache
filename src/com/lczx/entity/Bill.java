package com.lczx.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dc_bill")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_bill_sequence")
public class Bill extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -8061071701988160313L;
    
    private String num;
    
    public enum BillType
    {
        /**企业*/
        company,
        /**个人*/
        individual
    }
    
    public enum BillStatus
    {
        /**待开票*/
        uninvoice,
        /**已开票*/
        invoiced,
        /**开票失败*/
        fail
    }
    
    public enum Classify
    {
        /**纸质*/
        paper,
        /**电子*/
        electron
    }
    
    private BillType billType;
    
    private BillStatus billStatus;
    
    private Classify classify;
    
    /**发票抬头*/
    private String title;
    
    /**开票金额*/
    private BigDecimal amount;
    
    /**开票人手机号*/
    private String mobile;
    
    /**开票人邮箱*/
    private String email;
    
    /**税号*/
    private String dutySign;
    
    /**收件地址*/
    private String address;
    
    /**收件人*/
    private String name;
    
    /**申请开票人*/
    private Member member;
    
    private Set<Order> orders = new HashSet<Order>();
    
    
    /**
     * @return the num
     */
    public String getNum()
    {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(String num)
    {
        this.num = num;
    }

    /**
     * @return the billType
     */
    @JsonProperty
    public BillType getBillType()
    {
        return billType;
    }
    
    /**
     * @param billType the billType to set
     */
    public void setBillType(BillType billType)
    {
        this.billType = billType;
    }
    
    /**
     * @return the billStatus
     */
    @JsonProperty
    public BillStatus getBillStatus()
    {
        return billStatus;
    }
    
    /**
     * @param billStatus the billStatus to set
     */
    public void setBillStatus(BillStatus billStatus)
    {
        this.billStatus = billStatus;
    }
    
    /**
     * @return the classify
     */
    @JsonProperty
    public Classify getClassify()
    {
        return classify;
    }
    
    /**
     * @param classify the classify to set
     */
    public void setClassify(Classify classify)
    {
        this.classify = classify;
    }
    
    /**
     * @return the title
     */
    @JsonProperty
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * @return the amount
     */
    @JsonProperty
    public BigDecimal getAmount()
    {
        return amount;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
    
    /**
     * @return the mobile
     */
    @JsonProperty
    public String getMobile()
    {
        return mobile;
    }
    
    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    /**
     * @return the email
     */
    @JsonProperty
    public String getEmail()
    {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * @return the dutySign
     */
    @JsonProperty
    public String getDutySign()
    {
        return dutySign;
    }
    
    /**
     * @param dutySign the dutySign to set
     */
    public void setDutySign(String dutySign)
    {
        this.dutySign = dutySign;
    }
    
    /**
     * @return the address
     */
    @JsonProperty
    public String getAddress()
    {
        return address;
    }
    
    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    /**
     * @return the name
     */
    @JsonProperty
    public String getName()
    {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return the member
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Member getMember()
    {
        return member;
    }
    
    /**
     * @param member the member to set
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return the orders
     */
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    public Set<Order> getOrders()
    {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }
    
    
}
