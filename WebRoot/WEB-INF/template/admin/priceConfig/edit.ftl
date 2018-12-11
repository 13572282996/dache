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
    
    
    <div class="content" style="left:-162px;width: 95%">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">添加司机</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
<form id="inputForm" action="update.jhtml" class="form-inline" method="post">
    <input type="hidden" name="id" value="${priceConfig.id}" />
    <input type="hidden" name="type" value="${priceConfig.type}" />
    <input type="hidden" name="carType" value="${priceConfig.carType}" />
    <table class="layui-table" id="addEditTable">
    	        <tr>
					<th>
						<span class="requiredField">*</span>类型:
					</th>
					<td>
					[#if priceConfig.type=='realTime']
					实时
					[#else]
					预约
					[/#if]	
					</td>
				</tr>
				<!--<tr>
					<th>
						<span class="requiredField">*</span>车辆类型:
					</th>
					<td>
						${priceConfig.carType}
					</td>
				</tr>-->
				 <tr>
					<th>
						<span class="requiredField">*</span>基础价格:
					</th>
					<td>
						<input type="text" id="basePrice" name="basePrice" class="text" maxlength="20" value="${priceConfig.basePrice}"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>公里数（含）:
					</th>
					<td>
						<input type="text" id="miles" name="miles" class="text" maxlength="20" value="${priceConfig.miles}"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>基础时长:
					</th>
					<td>
						<input type="text" name="baseTime" class="text" maxlength="200" value="${priceConfig.baseTime}"/>
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>里程价格:
					</th>
					<td>
						<input type="text" name="milPrice" class="text" maxlength="200" value="${priceConfig.milPrice}"/>
					</td>
				</tr>
				
				<tr>
					<th>
						时长价格:
					</th>
					<td>
						<input type="text" name="timePrice" class="text" maxlength="200" value="${priceConfig.timePrice}"/>
					</td>
				</tr>
				
				<tr>
					<th>
						回空里程:
					</th>
					<td>
						<input type="text" name="haulbackMil" class="text" maxlength="200" value="${priceConfig.haulbackMil}"/>
					</td>
				</tr>
				<tr>
					<th>
						回空里程附加费:
					</th>
					<td>
						<input type="text" name="haulbackPrice" class="text" maxlength="200" value="${priceConfig.haulbackPrice}"/>
					</td>
				</tr>
				<tr>
					<th>
						夜间里程附加费:
					</th>
					<td>
						<input type="text" name="nightPrice" class="text" maxlength="200" value="${priceConfig.nightPrice}"/>
					</td>
				</tr>
				<tr>
					<th>
						早高峰计价倍数:
					</th>
					<td>
						<input type="text" name="morningPeak" class="text" maxlength="200" value="${priceConfig.morningPeak}"/>
					</td>
				</tr>
				<tr>
					<th>
						晚高峰计价倍数:
					</th>
					<td>
						<input type="text" name="nightPeak" class="text" maxlength="200" value="${priceConfig.nightPeak}"/>
					</td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="submit" value="${message("admin.common.submit")}" class="layui-btn layui-btn-sm"/>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="layui-btn layui-btn-sm" onclick="location.href='list.jhtml'"/>
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


