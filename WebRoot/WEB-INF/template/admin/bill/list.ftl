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
   
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">发票申请列表</li> (${message("admin.page.total", page.total)})
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
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

	<input type="hidden" name="searchProperty" value="num" />
	<input id="searchValue" name="searchValue" value="${page.searchValue}" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;overflow:scroll; width:96.3%;">
    <table class="layui-table" id="listTable" style="width:2000px;white-space: nowrap">
     <tr>
							<th class="check" style="text-align:center">
								<input type="checkbox" id="selectAll" />
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">申请单号</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">发票类型</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">发票抬头</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="email">开票金额</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="name">申请人</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="department">手机号码</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="loginDate">邮箱</a>
							</th>
							
							<th style="text-align:center">
								<span>状态</span>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="createDate">创建时间</a>
							</th>
							<th style="text-align:center">
								<span>${message("admin.common.handle")}</span>
							</th>
						</tr>
						[#list page.content as bill]
							<tr>
								<td style="text-align:center">
									<input type="checkbox" name="ids" value="${bill.id}" />
								</td>
								<td style="text-align:center">
									${bill.num}
								</td>
								<td style="text-align:center">
								[#if bill.billType == 'company']
									企业
								[#else]
								         个人
								[/#if]		
								</td>
								<td style="text-align:center">
									${bill.title}
								</td>
								<td style="text-align:center">
									${bill.amount}
								</td>
								<td style="text-align:center">
									${bill.member.name}
								</td>
								<td style="text-align:center">
									${bill.mobile}
								</td>
								
								<td style="text-align:center">
									${bill.email}
								</td>
								<td style="text-align:center">
								[#if bill.billStatus == 'uninvoice']
								待开票
								[#elseif bill.billStatus == 'invoiced']
								已开票
								[#else]
								开票失败
								[/#if]	
								</td>
								<td style="text-align:center">
									<span title="${bill.createDate?string("yyyy-MM-dd HH:mm:ss")}">${bill.createDate}</span>
								</td>
								
								
								<td style="text-align:center">
									<a href="#" class="layui-btn layui-btn-xs">查看</a>
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