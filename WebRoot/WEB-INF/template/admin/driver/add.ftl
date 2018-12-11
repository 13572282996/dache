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
    
    
    <div class="content">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">添加司机</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
<form id="inputForm" action="save.jhtml" class="form-inline" method="post">

    <table class="table" id="addEditTable">
    	        <tr>
					<th>
						<span class="requiredField">*</span>司机名称:
					</th>
					<td>
						<input type="text" name="driverName" class="text" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>生日:
					</th>
					<td>
						<input type="text" name="driverBirthday" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />
						
					</td>
				</tr>
				 <tr>
					<th>
						<span class="requiredField">*</span>性别:
					</th>
					<td>
						<input type="radio" name="driverGender" class="radio" value="male" checked />男
						<input type="radio" name="driverGender" class="radio" value="female"  />女
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>手机号码:
					</th>
					<td>
						<input type="text" id="password" name="driverPhone" class="text" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>所属公司:
					</th>
					<td>
						<input type="text" name="companyName" class="text" maxlength="200" />
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>身份证号码:
					</th>
					<td>
						<input type="text" name="idCard" class="text" maxlength="200" />
					</td>
				</tr>
				
				<tr>
					<th>
						车牌号:
					</th>
					<td>
						<input type="text" name="vehicleNo" class="text" maxlength="200" />
					</td>
				</tr>
				
				<tr>
					<th>
						状态:
					</th>
					<td>
						<label>
							<input type="checkbox" name="state" value="true" checked="checked" />${message("Admin.isEnabled")}
							<input type="hidden" name="state" value="false" />
						</label>
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


