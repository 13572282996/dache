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
            <li class="active">财务列表</li> 
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="listForm" action="list.jhtml" method="get">
<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="layui-table" id="listTable">
     <tr>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username"></a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">已结算合计</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">未结算合计</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="email">抽成合计</a>
							</th>
							
						</tr>
							<tr>
								<td style="text-align:center">
									历史收入
								</td>
								<td style="text-align:center">${settlementSum}
								</td>
								<td style="text-align:center">${unSettlementSum}
								</td>
								<td>
								&nbsp;
								</td>
								
							</tr>
							<tr>
								<td style="text-align:center">
									本周收入
								</td>
								<td style="text-align:center">${settlementWeek}</td>
								<td style="text-align:center">${unSettlementWeek}
								</td>
								<td>
								&nbsp;
								</td>
								
							</tr>
							<tr>
								<td style="text-align:center">
									本月收入
								</td>
								<td style="text-align:center">${settlementMonth}
								</td>
								<td style="text-align:center">${unSettlementMonth}
								</td>
								<td>
								&nbsp;
								</td>
							</tr>
							<tr>
								<td style="text-align:center">
									上月收入
								</td>
								<td style="text-align:center">${settlementLastMonth}
								</td>
								<td style="text-align:center">${unSettlementLastMonth}
								</td>
								<td>
								&nbsp;
								</td>
							</tr>
							<tr>
								<td style="text-align:center">
									本季收入
								</td>
								<td style="text-align:center">${settlementLastSeason}
								</td>
								<td style="text-align:center">${unSettlementLastSeason}
								</td>
								<td>
								&nbsp;
								</td>
							</tr>

    </table>
</div>

</form>
		</div>	
	</div> 
</div>

 </body>
</html>