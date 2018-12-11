package com.lczx.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dc_order")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_order_sequence")
public class Order extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**起点*/
    private String startPoint;
    
    /**终点*/
    private String endPoint;
    
    /**订单号*/
    private String num;
    
    /**乘客手机号*/
    private String memberMobile;
    
    /**乘客名称*/
    private String memberName;
    
    /**里程*/
    private double mileage;
    
    /**订单金额*/
    private BigDecimal amount;
    
    /**预估金额*/
    private BigDecimal estAmount;
    
    /**小费*/
    private BigDecimal tip;
    
    /**订单状态*/
    private OrderStatus orderStatus;
    
    /**支付状态*/
    private PaymentStatus paymentStatus;
    
    private OrderType orderType;
    
    /**预约时间*/
    private Date appointment;
    
    /**司机*/
    private Driver dirvers;
    
    /**车牌号*/
    private String plateNum;
    
    /**司机姓名*/
    private String dirverName;
    
    /**司机手机号码*/
    private String dirverMobile;
    
    public enum OrderType
    {
        /**实时*/
        realTime,
        /**预约*/
        appointment
    }
    
    /**
     * 订单状态
     */
    public enum OrderStatus
    {
        
        /** 待派单 */
        unconfirmed,
        /**待接驾*/
        unreceive,
        /** 进程中*/
        confirmed,
        
        /** 已完成 */
        completed,
        
        /** 已取消 */
        cancelled
    }
    
    /**
     * 支付状态
     */
    public enum PaymentStatus
    {
        
        /** 未支付 */
        unpaid,
        
        /** 已支付 */
        paid
        
    }
    
    /**
     * 结算状态 
     **/
    public enum SettlementStatus
    {
        /**未结算*/
        no,
        /**已结算*/
        yes
    }
    
    /**订单取消类型*/
    public enum CancellType
    {
        /**乘客*/
        member,
        /**司机*/
        dirver
        
    }
    
    /**
     * 结算状态 
     **/
    private SettlementStatus settlementStatus;
    
    /**订单取消类型*/
    private CancellType cancellType;
    
    private Member member;
    
    /**起点经度*/
    private String longitude;
    
    /**起点纬度*/
    private String latitude;
    
    /**终点经度*/
    private String endLongitude;
    
    /**终点纬度*/
    private String endLatitude;
    
    /**订单完成时间*/
    private Date completeDate;
    
    /**乘客上车时间*/
    private Date startDate;
    
    /**发票*/
    private Bill bill;
    
    /**取消原因*/
    private String cancelCause;
    
    /**订单评分*/
    private double score;
    
    @Column(nullable = false)
    @JsonProperty
    public String getStartPoint()
    {
        return startPoint;
    }
    
    public void setStartPoint(String startPoint)
    {
        this.startPoint = startPoint;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getEndPoint()
    {
        return endPoint;
    }
    
    public void setEndPoint(String endPoint)
    {
        this.endPoint = endPoint;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getNum()
    {
        return num;
    }
    
    public void setNum(String num)
    {
        this.num = num;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getMemberMobile()
    {
        return memberMobile;
    }
    
    public void setMemberMobile(String memberMobile)
    {
        this.memberMobile = memberMobile;
    }
    
    @Column(nullable = false)
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
    public double getMileage()
    {
        return mileage;
    }
    
    public void setMileage(double mileage)
    {
        this.mileage = mileage;
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
    public BigDecimal getEstAmount()
    {
        return estAmount;
    }
    
    public void setEstAmount(BigDecimal estAmount)
    {
        this.estAmount = estAmount;
    }
    
    @JsonProperty
    public BigDecimal getTip()
    {
        return tip;
    }
    
    public void setTip(BigDecimal tip)
    {
        this.tip = tip;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public OrderStatus getOrderStatus()
    {
        return orderStatus;
    }
    
    public void setOrderStatus(OrderStatus orderStatus)
    {
        this.orderStatus = orderStatus;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }
    
    @JsonProperty
    public Date getAppointment()
    {
        return appointment;
    }
    
    public void setAppointment(Date appointment)
    {
        this.appointment = appointment;
    }
    
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    public Driver getDirvers()
    {
        return dirvers;
    }
    
    public void setDirvers(Driver dirvers)
    {
        this.dirvers = dirvers;
    }
    
    @JsonProperty
    public String getPlateNum()
    {
        return plateNum;
    }
    
    public void setPlateNum(String plateNum)
    {
        this.plateNum = plateNum;
    }
    
    @JsonProperty
    public String getDirverName()
    {
        return dirverName;
    }
    
    public void setDirverName(String dirverName)
    {
        this.dirverName = dirverName;
    }
    
    @JsonProperty
    public String getDirverMobile()
    {
        return dirverMobile;
    }
    
    public void setDirverMobile(String dirverMobile)
    {
        this.dirverMobile = dirverMobile;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    @JsonProperty
    public Member getMember()
    {
        return member;
    }
    
    public void setMember(Member member)
    {
        this.member = member;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getLongitude()
    {
        return longitude;
    }
    
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getLatitude()
    {
        return latitude;
    }
    
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getEndLongitude()
    {
        return endLongitude;
    }
    
    public void setEndLongitude(String endLongitude)
    {
        this.endLongitude = endLongitude;
    }
    
    @Column(nullable = false)
    @JsonProperty
    public String getEndLatitude()
    {
        return endLatitude;
    }
    
    public void setEndLatitude(String endLatitude)
    {
        this.endLatitude = endLatitude;
    }
    
    @JsonProperty
    public OrderType getOrderType()
    {
        return orderType;
    }
    
    public void setOrderType(OrderType orderType)
    {
        this.orderType = orderType;
    }
    
    public CancellType getCancellType()
    {
        return cancellType;
    }
    
    public void setCancellType(CancellType cancellType)
    {
        this.cancellType = cancellType;
    }
    
    /**
     * @return the settlementStatus
     */
    public SettlementStatus getSettlementStatus()
    {
        return settlementStatus;
    }
    
    /**
     * @param settlementStatus the settlementStatus to set
     */
    public void setSettlementStatus(SettlementStatus settlementStatus)
    {
        this.settlementStatus = settlementStatus;
    }
    
    @JsonProperty
    public Date getCompleteDate()
    {
        return completeDate;
    }
    
    public void setCompleteDate(Date completeDate)
    {
        this.completeDate = completeDate;
    }
    
    @JsonProperty
    public Date getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }
    
    /**
     * @return the bill
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Bill getBill()
    {
        return bill;
    }
    
    /**
     * @param bill the bill to set
     */
    public void setBill(Bill bill)
    {
        this.bill = bill;
    }
    
    public String getCancelCause()
    {
        return cancelCause;
    }
    
    public void setCancelCause(String cancelCause)
    {
        this.cancelCause = cancelCause;
    }
    
    public double getScore()
    {
        return score;
    }
    
    public void setScore(double score)
    {
        this.score = score;
    }
    
}
