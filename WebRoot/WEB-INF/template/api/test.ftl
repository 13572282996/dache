[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8">
    <title>${message("admin.main.title")} - ${setting.siteName}</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${base}/resources/admin2/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${base}/resources/admin2/stylesheets/theme.css">
    <link rel="stylesheet" href="${base}/resources/admin2/lib/font-awesome/css/font-awesome.css">
    <script src="${base}/resources/admin2/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="${base}/resources/admin2/lib/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>

<link href="${base}/resources/admin2/custom/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$().ready(function() {
     $("#type").change(function(){
      var url =  $("#type").val();
      $("#inputForm").attr("action", url);
});
	
});	
</script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

 
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>


  <body class=""> 
    
    
    <div class="content">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml"></a> <span class="divider">/</span></li>
            <li class="active">API测试页</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
<form id="inputForm" action="#" class="form-inline" method="post">

    <table class="table" id="addEditTable">
    	       <tr>
					<th>
						<span class="requiredField">*</span>接口名称:
					</th>
					<td>
						<select id="type" name="type" class="ui_select01">
						    <option value="">请选择</option>
						    <option value="${base}/api/order/score.jhtml">订单评分{"orderId":"","score":""}String token</option>
						    <option value="${base}/api/cause/causeList.jhtml">订单取消原因列表{"causeType":"member"}</option>
						    <option value="${base}/api/bill/invoice.jhtml">申请发票{"billType":"company","classify":"electron","title":"","amount":"","mobile":"","email":"","dutySign":"","address":"","name":""} String token</option>
						    <option value="${base}/api/bill/invoiceList.jhtml">申请发票记录String token</option>
						    <option value="${base}/api/income/incomeList.jhtml">司机端收入列表String token</option>
						    <option value="${base}/api/driver/messageList.jhtml">司机端消息列表String token</option>
						    <option value="${base}/member/messageList.jhtml">乘客端消息列表String token</option>
						     <option value="${base}/api/article/article.jhtml">查文章{"id":""}</option>
						    <option value="${base}/api/driver/orderTest.jhtml">司机订单检查String token</option>
						    <option value="${base}/member/orderTest.jhtml">乘客订单检查String token</option>
						    <option value="${base}/api/pay/paySign.jhtml">支付{"orderId":""}String token</option>
							<option value="${base}/member/check_mobile.jhtml">检查手机号是否已存在</option>
							<option value="${base}/member/login.jhtml">用户登录{"mobile":"13111111111","passWord":"1111111"}</option>
							<option value="${base}/member/logout.jhtml">登录退出String token</option>
							<option value="${base}/member/register.jhtml">用户注册{"mobile":"13111111111","passWord":"1111111","code":"1234"}</option>
							<option value="${base}/member/fingMember.jhtml">根据手机号码查询用户信息{"mobile":"13111111111"} </option>
							<option value="${base}/member/updatePassWord.jhtml">修改密码{"mobile":"13111111111","passWord":"1111111"} </option>
							<option value="${base}/api/order/addOrder.jhtml">新增订单</option>
							<option value="${base}/api/order/cancelOrder.jhtml">取消订单{"id":"1"},String token </option>
							<option value="${base}/api/order/queryOrder.jhtml">订单列表String token</option>
							<option value="${base}/api/order/findOrder.jhtml">订单详情 {"id":"1"}  String token</option>
							<option value="${base}/api/sms/send.jhtml">发送短信{"mobile":"13111111111"}</option>
							<option value="${base}/api/track/aroundsearch.jhtml">根据坐标查3公里范围内车辆{"longitude":"20.11","latitude":"30.01"}</option>
							<option value="${base}/api/track/estAmount.jhtml">预估金额{"longitude":"104.0875895567","latitude":"30.6295625961","endLongitude":"104.1479462006","endLatitude":"30.6350371660"}</option>
							<option value="${base}/api/driver/activation.jhtml">司机激活{"mobile":"","code":""}</option>
							<option value="${base}/api/driver/updatePassword.jhtml">司机修改密码{"mobile":"","passWord":""}</option>
							<option value="${base}/api/driver/login.jhtml">司机登录{"mobile":"","passWord":""}</option>
							<option value="${base}/api/driver/receive.jhtml">司机听单String token</option>
							<option value="${base}/api/driver/receipt.jhtml">司机抢单{"orderId":""}String token</option>
							<option value="${base}/api/driver/complete.jhtml">订单完成{"orderId":""}String token</option>
							<option value="${base}/api/driver/cancell.jhtml">司机取消订单{"orderId":""}String token</option>
							<option value="${base}/api/driver/startTour.jhtml">乘客已上车{"orderId":""}String token</option>
							<option value="${base}/api/driver/findOrder.jhtml">司机查订单详情{"id":""}String token</option>
							<option value="${base}/api/driver/calculation.jhtml">司机端轮询{"longitude":"20.11","latitude":"30.01","orderId":""}String token</option>
							<option value="${base}/member/calculation.jhtml">乘客端轮询 {"orderId":""}　String token</option>
							<option value="${base}/api/order/reckon.jhtml">计算已完成订单金额 {"orderId":""}　String token</option>
							<option value="${base}/api/pay/paySign.jhtml">支付宝支付 {"orderId":""}　String token</option>
							<option value="${base}/api/driver/queryOrder.jhtml">司机端订单列表　String token</option>
							<option value="${base}/api/driver/driverInfo.jhtml">首页司机信息　String token</option>
							<option value="${base}/api/driver/logout.jhtml">司机端退出登录　String token</option>
							
					</select>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>加密参数字符串:
					</th>
					<td>
						<input type="text" name="param" value="" class="text"/>
					</td>
				</tr>
				<tr>
					<th>
						token:
					</th>
					<td>
						<input type="text" name="token" value="" class="text"/>
					</td>
				</tr>
				
				<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="submit" value="${message("admin.common.submit")}" class="btn"/>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="btn" onclick="location.href='list.jhtml'"/>
					</td>
				</tr>
    </table>

</form>
</div>

                    
		</div>
	</div>
</div>
    
    
  </body>
</html>


