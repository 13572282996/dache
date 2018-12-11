package com.lczx.util;

import java.math.BigDecimal;

import com.aisino.utils.ResultInfo;
import com.aisino.webs.FPServiceAPIPortTypeProxy;
import com.lczx.entity.Bill;

public class SaopUtil
{
    
    //地址
    //  private String urlString = "http://222.41.113.138:8123/FPService/services/FPServiceAPI";
    
    //方法
    //  private String soapActionString = "createUser";
    
    /**销货方识别号*/
    private static String NSRSBH = "150301206211289705";
    
    /**销货方名称*/
    private static String NSRMC = "西安测试公司";
    
    private static String XHF_DZ = "陕西省延安市宝塔区龙飞集团办公大3层";
    
    private static String XHF_YHZH = "中国工商银行股份有限公司延安双拥大道支行2609082909200054961";
    
    /**
     * 组装新增电子发票参数字符串 
     * 
     **/
    public static String addBillparam(Bill bill)
    {
        String soapStr = "<REQUEST_FPKJXX>\n" + "<FPKJXX_FPTXX>\n" + "<DDBH>"
                + bill.getNum()
                + "</DDBH> \n"
                + "<IS_APPLY>2</IS_APPLY> \n"
                + "<DSPTBM>16132807</DSPTBM>\n"
                + "<NSRSBH>"
                + NSRSBH
                + "</NSRSBH>\n"
                + "<NSRMC>"
                + NSRMC
                + "</NSRMC>\n"
                + "<NSRDZDAH/>\n"
                + "<SWJG_DM/>\n"
                + "<DKBZ>0</DKBZ>\n"
                + "<SGBZ>Y</SGBZ>\n"
                + "<PYDM>000001</PYDM>\n"
                + "<KPXM>网约车服务费</KPXM>\n"
                + "<BMB_BBH>1.0</BMB_BBH>\n"
                + "<XHF_NSRSBH>"
                + NSRSBH
                + "</XHF_NSRSBH>\n"
                + "<XHFMC>"
                + NSRMC
                + "</XHFMC>\n"
                + "<XHF_DZ>"
                + XHF_DZ
                + "</XHF_DZ>\n"
                + "<XHF_DH>15333333333</XHF_DH>\n"
                + "<XHF_YHZH>"
                + XHF_YHZH
                + "</XHF_YHZH>\n"
                + "<GHFMC>"
                + bill.getTitle()
                + "</GHFMC>\n"
                + "<GHF_NSRSBH>"
                + bill.getDutySign()
                + "</GHF_NSRSBH>\n"
                + "<GHF_SF>10</GHF_SF>\n"
                + "<GHF_DZ></GHF_DZ>\n"
                + "<GHF_GDDH/>\n"
                + "<GHF_SJ>"
                + bill.getMobile()
                + "</GHF_SJ>\n"
                + "<GHF_EMAIL>"
                + bill.getEmail()
                + "</GHF_EMAIL>\n"
                + "<GHFQYLX>03</GHFQYLX>\n"
                + "<GHF_YHZH/>\n"
                + "<HY_DM/>\n"
                + "<HY_MC/>\n"
                + "<KPY>开票员</KPY>\n"
                + "<SKY/>\n"
                + "<FHR/>\n"
                + "<KPRQ/>\n"
                + "<KPLX>1</KPLX>\n"
                + "<YFP_DM/>\n"
                + "<YFP_HM/>\n"
                + "<CZDM>10</CZDM>\n"
                + "<QD_BZ>0</QD_BZ>\n"
                + "<QDXMMC></QDXMMC>\n"
                + "<CHYY/>\n"
                + "<TSCHBZ>0</TSCHBZ>\n"
                + "<KPHJJE>"
                + bill.getAmount()
                + "</KPHJJE>\n"
                + "<HJBHSJE>"
                + bill.getAmount().subtract(bill.getAmount()
                        .multiply(new BigDecimal("0.06")))
                + "</HJBHSJE>\n"
                + "<HJSE>"
                + bill.getAmount().multiply(new BigDecimal("0.06"))
                + "</HJSE>\n"
                + "<BZ/>\n"
                + "<BYZD1/>\n"
                + "<BYZD2/>\n"
                + "<BYZD3/>\n"
                + "<BYZD4/>\n"
                + "<BYZD5/>\n"
                + "<FPKJXX_XMXX>\n"
                + "<XMMC>网约车服务费</XMMC>\n"
                + "<XMDW></XMDW>\n"
                + "<GGXH></GGXH>\n"
                + "<XMSL></XMSL>\n"
                + "<HSBZ>0</HSBZ>\n"
                + "<XMDJ></XMDJ>\n"
                + "<FPHXZ>0</FPHXZ>\n"
                + "<SPBM>1010101030000000000</SPBM>\n"
                + "<ZXBM></ZXBM> \n"
                + "<YHZCBS>0</YHZCBS> \n"
                + "<LSLBS></LSLBS> \n"
                + "<ZZSTSGL></ZZSTSGL>\n"
                + "<KCE></KCE> \n"
                + "<XMJE>"
                + bill.getAmount().subtract(bill.getAmount()
                        .multiply(new BigDecimal("0.06"))) + "</XMJE>\n"
                + "<SL>0.06</SL>\n" + "<SE>"
                + bill.getAmount().multiply(new BigDecimal("0.06")) + "</SE>\n"
                + "<BYZD1/>\n" + "<BYZD2/>\n" + "<BYZD3/>\n" + "<BYZD4/>\n"
                + "<BYZD5/>\n" + "</FPKJXX_XMXX>\n" + "</FPKJXX_FPTXX> \n"
                + "</REQUEST_FPKJXX>";
        System.out.println(soapStr);
        return soapStr;
    }
    
    /**
     * 组装查询电子发票参数字符串 
     * 
     **/
    public static String queryBillparam(String billNum)
    {
        String soapStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<REQUEST_FPCXXX>" + "<FPKJXX_FPTXX>" + "<DDBH>" + billNum
                + "</DDBH>" + "<INVOICE_TYPE>1</INVOICE_TYPE>"
                + "</FPKJXX_FPTXX>" + "</REQUEST_FPCXXX>";
        return soapStr;
    }
    
    public static void main(String[] args) throws Exception
    {
        //        // translate("sea");
        //        String soap = "<REQUEST_FPKJXX>\n"
        //                + "<FPKJXX_FPTXX>\n"
        //                + "<DDBH>201707311234158</DDBH> \n"
        //                + "<IS_APPLY>2</IS_APPLY> \n"
        //                + "<DSPTBM>16132807</DSPTBM>\n"
        //                + "<NSRSBH>150301206211289705</NSRSBH>\n"
        //                + "<NSRMC>西安测试公司</NSRMC>\n"
        //                + "<NSRDZDAH/>\n"
        //                + "<SWJG_DM/>\n"
        //                + "<DKBZ>0</DKBZ>\n"
        //                + "<SGBZ>Y</SGBZ>\n"
        //                + //新加字段默认Y
        //                "<PYDM>000001</PYDM>\n"
        //                + "<KPXM>电子发票</KPXM>\n"
        //                + "<BMB_BBH>1.0</BMB_BBH>\n"
        //                + //新加字段默认1.0
        //                "<XHF_NSRSBH>150301206211289705</XHF_NSRSBH>\n"
        //                + "<XHFMC>150301206211289705</XHFMC>\n"
        //                + "<XHF_DZ>企业</XHF_DZ>\n"
        //                + "<XHF_DH>15333333333</XHF_DH>\n"
        //                + "<XHF_YHZH></XHF_YHZH>\n"
        //                + "<GHFMC></GHFMC>\n"
        //                + "<GHF_NSRSBH/>\n"
        //                + "<GHF_SF>10</GHF_SF>\n"
        //                + "<GHF_DZ></GHF_DZ>\n"
        //                + "<GHF_GDDH/>\n"
        //                + "<GHF_SJ>15129202543</GHF_SJ>\n"
        //                + "<GHF_EMAIL>22097002@qq.com</GHF_EMAIL>\n"
        //                + "<GHFQYLX>03</GHFQYLX>\n"
        //                + "<GHF_YHZH/>\n"
        //                + "<HY_DM/>\n"
        //                + "<HY_MC/>\n"
        //                + "<KPY>开票员</KPY>\n"
        //                + "<SKY/>\n"
        //                + "<FHR/>\n"
        //                + "<KPRQ/>\n"
        //                + "<KPLX>1</KPLX>\n"
        //                + "<YFP_DM/>\n"
        //                + "<YFP_HM/>\n"
        //                + "<CZDM>10</CZDM>\n"
        //                + "<QD_BZ>0</QD_BZ>\n"
        //                + //新家字段清单标志 0 默认
        //                "<QDXMMC></QDXMMC>\n"
        //                + //新家字段清单发票项目名称
        //                "<CHYY/>\n"
        //                + "<TSCHBZ>0</TSCHBZ>\n"
        //                + "<KPHJJE>110.00</KPHJJE>\n"
        //                + "<HJBHSJE>100.00</HJBHSJE>\n"
        //                + "<HJSE>10.00</HJSE>\n"
        //                + "<BZ/>\n"
        //                + "<BYZD1/>\n"
        //                + "<BYZD2/>\n"
        //                + "<BYZD3/>\n"
        //                + "<BYZD4/>\n"
        //                + "<BYZD5/>\n"
        //                + "<FPKJXX_XMXX>\n"
        //                + "<XMMC>2014中秋大礼举举包雅致黑</XMMC>\n"
        //                + "<XMDW></XMDW>\n"
        //                + "<GGXH></GGXH>\n"
        //                + "<XMSL>1</XMSL>\n"
        //                + "<HSBZ>1</HSBZ>\n"
        //                + "<XMDJ>100</XMDJ>\n"
        //                + "<FPHXZ>0</FPHXZ>\n"
        //                + //新家字段发票行性质
        //                "<SPBM>1010101030000000000</SPBM>\n"
        //                + //新家字段商品编码
        //                "<ZXBM></ZXBM> \n"
        //                + //新家字段自行编码
        //                "<YHZCBS>0</YHZCBS> \n"
        //                + //新家字段优惠政策标识
        //                "<LSLBS></LSLBS> \n"
        //                + //新家字段零税率标识
        //                "<ZZSTSGL></ZZSTSGL>\n"
        //                + //新家字段增值税特殊管理
        //                "<KCE></KCE> \n"
        //                + //新家字段扣除额
        //                "<XMJE>100</XMJE>\n" + "<SL>0.10</SL>\n" + "<SE></SE>\n"
        //                + "<BYZD1/>\n" + "<BYZD2/>\n" + "<BYZD3/>\n" + "<BYZD4/>\n"
        //                + "<BYZD5/>\n" + "</FPKJXX_XMXX>\n" + "</FPKJXX_FPTXX> \n"
        //                + "</REQUEST_FPKJXX>";
        //        
        String soap1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<REQUEST_FPCXXX>" + "<FPKJXX_FPTXX>"
                + "<DDBH>1234567897</DDBH>" + "<INVOICE_TYPE>1</INVOICE_TYPE>"
                + "</FPKJXX_FPTXX>" + "</REQUEST_FPCXXX>";
        
        FPServiceAPIPortTypeProxy fpServiceAPIPortTypeProxy = new FPServiceAPIPortTypeProxy();
        ResultInfo rInfo = fpServiceAPIPortTypeProxy.createUser(soap1);
        
    }
}
