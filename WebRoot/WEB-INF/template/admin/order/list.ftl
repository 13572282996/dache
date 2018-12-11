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

<div class="content" style="left:-162px;width: 95%">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">订单列表</li> (${message("admin.page.total", page.total)})
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
   <!-- <input type="button" value="${message("admin.common.add")}" class="btn" onclick="location.href='add.jhtml'"/>-->
    <input type="button" value="${message("admin.common.delete")}" class="layui-btn layui-btn-sm disabled" id="deleteButton" /> 
	<input type="button" value="${message("admin.common.refresh")}" class="layui-btn layui-btn-sm" id="refreshButton"/>
	
	<div class="btn-group" id="groupDiv" style="left:10px;">
		<a class='layui-btn layui-btn-sm dropdown-toggle' data-toggle='dropdown' href='javascript:;'>每页显示<span class='caret'></span></a>
		<ul class='dropdown-menu' id="pageSizeOption">
			<li [#if page.pageSize == 10] class="active"[/#if]>
				<a href="javascript:;" val="10">10</a>
			</li>
			<li [#if page.pageSize == 20] class="active"[/#if]>
				<a href="javascript:;" val="20">20</a>
			</li>
			<li [#if page.pageSize == 50] class="active"[/#if]>
				<a href="javascript:;" val="50">50</a>
			</li>
			<li [#if page.pageSize == 100] class="active"[/#if]>
				<a href="javascript:;" val="100">100</a>
			</li>
		</ul>
	</div>
	
	<button id="searchValueButton" class="layui-btn layui-btn-sm" type="submit"><i class="icon-search"></i> 查询</button>
<!--指定按名称搜索	<select name="searchProperty" class="listselect">
		<option value="title" [#if page.searchProperty == "title"]  selected="selected" [/#if]>${message("Article.title")}</option>
	</select>-->
	<input type="hidden" name="searchProperty" value="dirverName" />
	<input id="searchValue" name="searchValue" value="${page.searchValue}" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px; overflow:scroll; width:96.3%;">
    <table class="layui-table" id="listTable" style="width:3500px;white-space: nowrap">
     					<tr>
							<th class="check"  style="text-align:center">
								<input type="checkbox" id="selectAll" />
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">起点</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">终点</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">订单编号</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="email">订单类型</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="name">司机名称</a>
							</th>
							
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="loginDate">车牌号</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">司机手机号码</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">乘客名称</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">乘客手机号码</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">预估金额</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">实际金额</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">行驶里程</a>
							</th>
							<th style="text-align:center">
								<span>订单状态</span>
							</th>
							<th style="text-align:center">
								<span>支付状态</span>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="createDate">创建时间</a>
							</th>
							<th style="text-align:center">
								<span>${message("admin.common.handle")}</span>
							</th>
						</tr>
						[#list page.content as order]
							<tr>
								<td style="text-align:center">
									<input type="checkbox" name="ids" value="${order.id}" />
								</td>
								<td style="text-align:center">
									${order.startPoint}
								</td>
								<td style="text-align:center">
									${order.endPoint}
								</td>
								<td style="text-align:center">
									${order.num}
								</td>
								<td style="text-align:center">
								[#if order.orderType=='realTime']
								实时
								[#else]
								预约
								[/#if]	
									
								</td>
								<td style="text-align:center">
									${order.dirverName}
								</td>
								<td style="text-align:center">
									${order.plateNum}
								</td>
								<td style="text-align:center">
									${order.dirverMobile}
								</td>
								<td style="text-align:center">
									${order.memberName}
								</td>
								
								<td style="text-align:center">
									${order.memberMobile}
								</td>
								<td style="text-align:center">
									${order.estAmount}
								</td>
								<td style="text-align:center">
									${order.amount}
								</td>
								<td style="text-align:center">
									${order.mileage}
								</td>
								<td style="text-align:center">
								[#if order.orderStatus=='unconfirmed']
								待司机接单
								[#elseif order.orderStatus=='confirmed']
								行程中
								[#elseif order.orderStatus=='completed']
								已完成
								[#else]
								已取消
								[/#if]	
								<td style="text-align:center">
								[#if order.paymentStatus=='paid']
								已支付
								[#else]
								未支付
								[/#if]	
								</td>
								<td style="text-align:center">
									<span title="${order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${order.createDate}</span>
								</td>
								
								
								<td style="text-align:center">
									<a href="view.jhtml?id=${order.id}" class="layui-btn layui-btn-xs">查看</a>
								</td>
							</tr>
						[/#list]

    </table>
</div>

[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
	[#include "/admin/include/pagination.ftl"]
[/@pagination]

</form>


		</div>	
	</div> 
</div>

 </body>
</html>