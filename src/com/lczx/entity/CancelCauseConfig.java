package com.lczx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 取消原因 
 * 
 **/

@Entity
@Table(name = "dc_cancelCause")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_cancelCause_sequence")
public class CancelCauseConfig extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 3909160515553253520L;
    
    private String cause;
    
    /**类型*/
    public enum CauseType
    {
        /**乘客端*/
        member,
        /**司机端*/
        driver
    }
    
    private CauseType causeType;
    
    /**状态*/
    private boolean causeStatus;
    
    /**
     * @return the cause
     */
    @JsonProperty
    public String getCause()
    {
        return cause;
    }
    
    /**
     * @param cause the cause to set
     */
    public void setCause(String cause)
    {
        this.cause = cause;
    }
    
    /**
     * @return the causeType
     */
    public CauseType getCauseType()
    {
        return causeType;
    }
    
    /**
     * @param causeType the causeType to set
     */
    public void setCauseType(CauseType causeType)
    {
        this.causeType = causeType;
    }
    
    /**
     * @return the causeStatus
     */
    public boolean isCauseStatus()
    {
        return causeStatus;
    }
    
    /**
     * @param causeStatus the causeStatus to set
     */
    public void setCauseStatus(boolean causeStatus)
    {
        this.causeStatus = causeStatus;
    }
    
}
