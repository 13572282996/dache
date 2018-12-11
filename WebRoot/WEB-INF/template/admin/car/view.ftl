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


  <body class=""> 
    
    
    <div class="content"  style="left:-162px;width: 95%">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">车辆详情</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
	<ul class="nav nav-tabs">
	      <li class="active"><a href="#tab1" data-toggle="tab">基本信息</a></li>
	      
	 </ul>
　　<div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="tab1">
    <table class="table" id="addEditTable">
    	       <tr>
					<th>
						<span class="requiredField">*</span>车牌号:
					</th>
					<td>
						${car.vehicleNo}
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>所司公司:
					</th>
					<td>
						${car.company}
					</td>
				</tr>
				 <tr>
					<th>
						<span class="requiredField">*</span>注册地:
					</th>
					<td>
						${car.address}
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>品牌/型号:
					</th>
					<td>
						${car.brand}/${car.model}
					</td>
				</tr>
				
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>车辆颜色:
					</th>
					<td>
						${car.vehicleColor}
					</td>
				</tr>
				
				<tr>
					<th>
						座位数:
					</th>
					<td>
						${car.seats}
					</td>
				</tr>
				
				<tr>
					<th>
						司机姓名:
					</th>
					<td>
						${car.ownerName}
					</td>
				</tr>
				
				<tr>
					<th>
						状态:
					</th>
					<td>
						<label>
							[#if car.state]
								启用
								[#else]
								未启用
						[/#if]	
						</label>
					</td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="btn" onclick="location.href='list.jhtml'"/>
					</td>
				</tr>
    </table>

	</div>
	 
	</div>

</div>

                    
		</div>	
	</div> 
</div>
    
    
  </body>
</html>


