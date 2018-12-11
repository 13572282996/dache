package com.lczx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 消息 
 * 
 **/
@Entity
@Table(name = "dc_message")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "dc_message_sequence")
public class Message extends BaseEntity
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -8451224220896123249L;
    
    /** 标题 */
    private String title;
    
    /** 内容 */
    private String content;
    
    /**
     * 消息状态 
     * 
     **/
    public enum Status
    {
        /**未发布*/
        unPut,
        /**已发布*/
        put
    }
    
    private Status status;
    
    /**
     * 消息类型 
     **/
    public enum MessageType
    {
        /**乘客*/
        member,
        /**司机*/
        driver
    }
    
    private MessageType messageType;
    
    /**发件人*/
    private String sender;
    
    /**乘客*/
    private Member member;
    
    /**司机*/
    private Driver driver;
    
    @JsonProperty
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    @JsonProperty
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    @JsonProperty
    public Status getStatus()
    {
        return status;
    }
    
    public void setStatus(Status status)
    {
        this.status = status;
    }
    @JsonProperty
    public MessageType getMessageType()
    {
        return messageType;
    }
    
    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }
    @JsonProperty
    public String getSender()
    {
        return sender;
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
    }
    
    public Member getMember()
    {
        return member;
    }
    
    public void setMember(Member member)
    {
        this.member = member;
    }
    
    public Driver getDriver()
    {
        return driver;
    }
    
    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }
    
}
