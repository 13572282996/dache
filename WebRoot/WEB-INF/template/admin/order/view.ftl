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

  <link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/layui/css/layui.css" media="all"> 
  <link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/style/admin.css" media="all"> 
  <link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/style/login.css" media="all"> 
  <script src="/resources/src/layuiadmin/layui/layui.js" type="text/javascript"></script>
  <script src="/resources/dist/layuiadmin/layui/layui.js" type="text/javascript"></script>

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

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "check_username.jhtml",
					cache: false
				}
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			roleIds: "required"
		},
		messages: {
			username: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.validate.exist")}"
			},
			password: {
				pattern: "${message("admin.validate.illegal")}"
			}
		}
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


  <body class=""  style="left:-162px;width: 95%"> 
    
    
    <div class="content">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">乘客详情</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
	<ul class="nav nav-tabs">
	      <li class="active"><a href="#tab1" data-toggle="tab">订单信息</a></li>
	 </ul>
　　<div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="tab1">
    <table class="layui-table" id="addEditTable">
    	        <tr>
					<th>
						<span class="requiredField">*</span>订单类型:
					</th>
					<td>
						[#if order.orderType=='realTime']
								实时
								[#else]
								预约
								[/#if]	
						
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>订单编号:
					</th>
					<td>
						${order.num}
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>司机名称:
					</th>
					<td>
						${order.dirverName}	
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>司机手机号码:
					</th>
					<td>
					${order.dirverMobile}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>乘客名称:
					</th>
					<td>
					${order.memberName}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>乘客手机号码:
					</th>
					<td>
					${order.memberMobile}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>起点:
					</th>
					<td>
					${order.startPoint}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>终点:
					</th>
					<td>
					${order.endPoint}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>行驶里程:
					</th>
					<td>
					${order.mileage}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>预估金额:
					</th>
					<td>
					${order.estAmount}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>实际金额:
					</th>
					<td>
					${order.amount}
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>订单状态:
					</th>
					<td>
					[#if order.orderStatus=='unconfirmed']
								待司机接单
								[#elseif order.orderStatus=='confirmed']
								行程中
								[#elseif order.orderStatus=='completed']
								已完成
								[#else]
								已取消
								[/#if]	
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>支付状态:
					</th>
					<td>
					[#if order.paymentStatus=='paid']
								已支付
								[#else]
								未支付
								[/#if]	
					</td>
				</tr>
				<tr>
					<th>
						创建时间:
					</th>
					<td>
						<span title="${order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${order.createDate}</span>
					</td>
				</tr>
				
				
				<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="layui-btn layui-btn-sm" onclick="location.href='list.jhtml'"/>
					</td>
				</tr>
    </table>

	</div>
	 <div class="tab-pane fade" id="tab2">
	 

	 </div>
	</div>

</div>

                    
		</div>	
	</div> 
</div>
    
    
  </body>
</html>


