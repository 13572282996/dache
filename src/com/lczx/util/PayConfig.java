package com.lczx.util;

public class PayConfig
{
    //编码集  
    public static final String CHARSET = "UTF-8";  
      
      
    /**支付宝接口url**/  
    public static final String ALI_APIURL = "https://openapi.alipay.com/gateway.do";  
      
    /**支付宝appid*/  
    public static final String ALI_APPID = "2018042802604341";  
      
    /**支付宝APP_PRIVATE_KEY**/  
    public static final String ALI_APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCYMjdj5wKOa6/ZWJH/2Evpcy36J2Kt3V9P9jRmBg2w8qdsNR5jm9bFrFLxfOrslbduvdI6c3kLZZTjoxqDZmqszIHj/f+qCQJPW+EHWq2atp5L/V8/BGLZpPa3LUtCCLjNSJAmUQBWdhWAI+KlLwUkNpIt9pMIc+f7y/tx0h3DJAfhWUrIujPBhiBZ1RSMPKSUyqcVhaKo7DUmZI1tUaWGguwkS2SZ86ReSYk0yavGSfKYhPHns1gthf2aKRNLJEquB3DC9K3Atqf0shzML0YlH9JY0bhE8nan2/MODqwyZTYYKULqRP37zbcw1ybPaIz/iX0X2N9/5Ui4WbncKL4PAgMBAAECggEASlwem/z+Tr9mh3ayvqHbVRAlYpdjqzcBnnvYVhCXF+E207OQSXKWmLX9QGecEEOF/+FWmaBZf6v99sp15cVgUVUKEW7EPztvmX52j7VKJNEcvlvo8U0fTt8+e3jx0C2avRG6/D2PQK8pChZ+Yd57UJopCbPzWqnmqiubipzyVai9Jb9SXUkWrJvIUg6oNc/cDNZm1jraetb2gXJHE7QdSXvSbaArQolKJJZYzW6BK+v+6SdZMZC5cIkEbNE4iQKjAf91C1zwY+wv62QJCuJECChe0KMPjPcFiheA1e6Eu0BfuXzEs+wkgtK/qKJUgW+QJzaAW86AonsRMPPZTkazYQKBgQDrOVTDJ5BHxuOaRjIPTnj5EoFB3VDu4ax/iL61JEEt7fY6c7OpFxGRY8OCQlvfj3AahDi+2oH4ESVeyTzNMwTHiFLOT+ghi6iolw4IedaWyGCvJ8tv13SyM914wusFsAwUzonuFt42J/lqYj7kH8qA2ppNlHgBugpQMONdQki2SwKBgQClo4ln5ohkHUgTq2PfsWdL+qPONHNgneMd1dzIR5FWtSmvR50Q6ILdPLffdBTXKfxT7b5hm2y00NpcUKNRC6hSmLfO6Ts1X+c9L7/+lXpeXpknRuJFnP42CYY1nvbLDFVExUDQGmweJlf5T+fyEPbHfjOHwnO7IXLQhaGUpSHMzQKBgG1JcMFPM3C1zm+SJ5YG/puyZk2ktwKV5WBHt1tP4dymD92ufg2UwzvKnz54+FiROyOGxRqfSP1pcfRtCQvJpXHTO63RYGedjJnvvLxhZzsdFBatJY7sCrL09/Qwk6NL3QEZyOnOxCVjQzWTOechgiOF7WUYEv2yYzJI1IoyzrerAoGAVrFSUHQjPKrqt4jDvCeA1g53v4xKOu5IGax+YsC6e/RTve6wcVIQdDS8VBLQeZD7BP5w32tWqQiK0ThPUjLUxW4y/fOqTpREVgzw4xFfO5ctd5YtBZxWnPe9K8Foj/uDgXvwITtDDUCqWM2Oq25fiygtVkfbBL1L94GJgWAwYZ0CgYA8C5zlUva+HNWhxpoZkDjNv43AzHUuD0NyKSBXwfyVjcrXlWRV5IdKOULxYKpzbWBW8llfMCPLVS1a4+YPMpPQBQuJ++NeFikhcXxTuXresEbSyj0eLhAyM6CoNiXaHmmplyuRP8Gvey30v/sP3mbVkaNkBco8xyxsxYodJoZRnw==";  
  
    /**FORMAT - JSON**/  
    public static final String ALI_FORMAT = "json";  
      
    /**支付宝公钥ALIPAY_PUBLIC_KEY*/  
    public static final String ALI_ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx3p3CwGrh+F55Z+Kgf5DnE+8B7N2rFc1YtQhzUEDOD1RaB2ShJLgc8ybMjzWhrGWOk1uD/89riGzSOizIu+Jii1wBz3rV3MjMzPuq33G9Pe9bY2so3ObHjrP9R2YxS5pZzZ/f9Cn3/J/a7wAuHnQPqhg9x8b7UZoQNqEhZQ29Ot1tJ6V0/piCcNJsuQKYqhskmp1600EJz1Dr4efa62jSzv5SN8JrkL76hhS/EtB/FZautCKgXsRjHsGGq1gouKOXe+kZB1gdBnZXnM7aqyY4O2yVYCNOW43ijvq38UF+/8hT45pPuI4iNpuLFp+uQCvqDV3RNzwAJvx6ma+9zcUgQIDAQAB";  
  
    /**签名方式SIGN_TYPE*/  
    public static final String ALI_SIGN_TYPE = "RSA";  
      
    /**支付宝订单超时时间**/  
    public static final String TIMEOUTEXPRESS = "30m";  
      
    /**支付宝异步通知地址**/  
    public static final String ALI_NOTIFYURL = "http://demo.lczxtech.com/api/pay/notify.jhtml";  
      
    /**提交方式**/  
    public static final String POST = "POST";  
    public static final String GET = "GET";  
}
