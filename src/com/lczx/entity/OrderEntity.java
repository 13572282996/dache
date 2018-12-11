/*



 */
package com.lczx.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 项目名称：
 * 功能模块名称：Entity - 排序基类
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@MappedSuperclass
public abstract class OrderEntity extends BaseEntity implements
        Comparable<OrderEntity>
{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -6224201293840087415L;

    /** "排序"属性名称 */
    public static final String ORDER_PROPERTY_NAME = "order";
    
    /** 排序 */
    private Integer order;
    
    /**
     * 获取排序
     * 
     * @return 排序
     */
    @JsonProperty
    //	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
    @Field(store = Store.YES)
    @Min(0)
    @Column(name = "orders")
    public Integer getOrder()
    {
        return order;
    }
    
    /**
     * 设置排序
     * 
     * @param order
     *            排序
     */
    public void setOrder(Integer order)
    {
        this.order = order;
    }
    
    /**
     * 实现compareTo方法
     * 
     * @param orderEntity
     *            排序对象
     * @return 比较结果
     */
    public int compareTo(OrderEntity orderEntity)
    {
        return new CompareToBuilder().append(getOrder(), orderEntity.getOrder())
                .append(getId(), orderEntity.getId())
                .toComparison();
    }
    
}