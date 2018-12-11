package com.lczx.entity;

public class NetWorkCarLicense extends BaseEntity
{
    /**网络预约出租汽车驾驶员资格证号*/
    private String certificateNo;
    
    /**网络预约出租汽车驾驶员证发证机构*/
    private String networkCarIssueOrganization;
    
    /**资格证发证日期*/
    private String networkCarIssueDate;
    
    /**初次领取资格证日期*/
    private String getNetworkCarProofDate;
    
    /**资格证有效起始日期*/
    private String networkCarProofOn;
    
    /**资格证有效截止日期*/
    private String networkCarProofOff;
    
    /**报备日期*/
    private String registerDate;
    
    /**是否专职驾驶员*/
    private String fullTimeDriver;
    
    /**是否在驾驶员黑名单内*/
    private String inDriverBlacklist;
    
    /**服务类型*/
    private String commercialType;
    
    /**驾驶员合同（或协议）签署公司*/
    private String contractCompany;
    
    /**合同（或协议）有效期起*/
    private String contractOn;
    
    /**合同（或协议）有效期止*/
    private String contractOff;
    
    /**是否巡游出租汽车驾驶员*/
    private String taxiDriver;
}
