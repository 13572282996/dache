package com.lczx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dc_code")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_code_sequence")
public class Code extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String code;
    
    private String mobile;
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
}
