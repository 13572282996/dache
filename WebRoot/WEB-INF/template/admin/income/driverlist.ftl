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
<script type="text/javascript" src="${base}/resources/admin2/custom/common.js"></script>
<script src="${base}/resources/admin2/custom/list.js" type="text/javascript"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
	

});	
</script>

</head>

<div class="content"  style="left:-162px;width: 95%">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">待结算列表</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
    <input type="button" value="${message("admin.common.refresh")}" class="layui-btn layui-btn-sm" id="refreshButton"/>
	
	
	
	<button id="searchValueButton" class="layui-btn layui-btn-sm" type="submit"><i class="icon-search"></i> 查询</button>
    <input type="hidden" name="searchProperty" value="dirverName" />
	<input id="searchValue" name="searchValue" value="" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="layui-table" id="listTable">
     <tr>
							<th class="check"  style="text-align:center">
								<input type="checkbox" id="selectAll" />
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">司机姓名</a>
							</th>
							
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="email">手机号码</a>
							</th>
							
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">身份证号码</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="loginDate">车牌号</a>
							</th>
							
							<th style="text-align:center">
								<span>未结算金额</span>
							</th>
							
							<th style="text-align:center">
								<span>${message("admin.common.handle")}</span>
							</th>
						</tr>
						[#list driverInComes as driverInCome]
							<tr>
								<td style="text-align:center">
									<input type="checkbox" name="ids" value="${driverInCome.id}" />
								</td>
								<td style="text-align:center">
									${driverInCome.name}
								</td>
								
								
								<td style="text-align:center">
									${driverInCome.mobile}
								</td>
								<td style="text-align:center">
									${driverInCome.idCard}
								</td>
								<td style="text-align:center">
									${driverInCome.vehicleNo}
								</td>
								
								<td style="text-align:center">
									${driverInCome.amount}
								</td>
								
								<td style="text-align:center">
									<a href="settlement.jhtml?id=${driverInCome.id}" class="layui-btn layui-btn-xs">结算</a>
								</td>
							</tr>
						[/#list]

    </table>
</div>


</form>


		</div>	
	</div> 
</div>

 </body>
</html>