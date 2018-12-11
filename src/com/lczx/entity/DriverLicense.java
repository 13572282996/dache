package com.lczx.entity;

public class DriverLicense extends BaseEntity
{
    /**照片*/
    private String photoId;
    
    /**机动车驾驶证号*/
    private String licenseId;
    
    /**机动车驾驶证扫描件文件编号*/
    private String licensePhotoId;
    
    /**准驾车型*/
    private String driverType;
    
    /**初次领取驾驶证日期*/
    private String getDriverLicenseDate;
    
    /**驾驶证有效期限起*/
    private String griverLicenseOn;
    
    /**驾驶证有效期限止*/
    private String griverLicenseOff;
}
