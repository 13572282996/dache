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
    <style>
        .black_overlay{
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index:1001;
            -moz-opacity: 0.4;
            opacity:.40;
            filter: alpha(opacity=44);
        }
        .white_content {
            display: none;
            position: absolute;
            top: 11%;
            left: 33%;
            width: 45%;
            height: 40%;
            /*padding: 20px;*/
            border: 3px solid gray;
            background-color: white;
            z-index:1002;
            overflow: auto;
        }
        .tk_header{
            position: relative;
            width: 100%;
            height: 40px;
            background-color: gainsboro;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>

<div class="content" style="left:-162px;width: 95%">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">取消订单原因列表</li> (${message("admin.page.total", page.total)})
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
 
<div id="light" class="white_content">
			<div class="tk_header">
            	<p style="font-size: 18px">添加</p>
        	</div>
        	<div>
				<form id="inputForm" action="save.jhtml" class="form-inline" method="post">
				    <table class="layui-table" id="addEditTable" style="position:relative;left:-30px;top:2px">
    	        <tr>
					<th>
						<span class="requiredField">*</span>取消原因:
					</th>
					<td>
						<input type="text" name="cause" class="text"  />
					</td>
				</tr>
				
				 <tr>
					<th>
						<span class="requiredField">*</span>客户端:
					</th>
					<td>
						<input type="radio" name="causeType" class="radio" value="member" checked />乘客端
						<input type="radio" name="causeType" class="radio" value="driver"  />司机端
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>是否显示:
					</th>
					<td>
						<input type="radio" name="causeStatus" class="radio" value="true" checked />显示
						<input type="radio" name="causeStatus" class="radio" value="false"  />不显示
					</td>
				</tr>							
			<tr>
				<th></th>
					<td>
						&nbsp;<input type="submit" value="确定" class="layui-btn layui-btn-sm"/>
						&nbsp;<input type="button" value="取消"   class="layui-btn layui-btn-sm" href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
					</td>
				</tr>
				    </table>
				</form>
			</div>
	</div> 
	<div id="fade" class="black_overlay"></div> 

 
 
 
            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
    <input type="button" value="${message("admin.common.add")}" class="layui-btn layui-btn-sm" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'" />
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

	<input type="hidden" name="searchProperty" value="causeType" />
	<input id="searchValue" name="searchValue" value="${page.searchValue}" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="layui-table" id="listTable">
     <tr>
							<th class="check"  style="text-align:center">
								<input type="checkbox" id="selectAll" />
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">客户端</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">原因</a>
							</th>
							<th style="text-align:center">
								<a href="javascript:;" class="sort" name="username">是否显示</a>
							</th>
							<!--<th>
								<span>${message("admin.common.handle")}</span>
							</th>-->
						</tr>
						[#list page.content as cause]
							<tr>
								<td style="text-align:center">
									<input type="checkbox" name="ids" value="${cause.id}" />
								</td>
								<td style="text-align:center">
									[#if cause.causeType=='member']
									乘客端
									[#else]
									司机端
									[/#if]
								</td>
								<td style="text-align:center">
								${cause.cause}
								</td>
								<td style="text-align:center">
									[#if cause.causeStatus]
									显示
									[#else]
									不显示
									[/#if]
								</td>
						
								<!--<td>
									<a href="#" class="layui-btn layui-btn-xs">查看</a>
								</td>-->
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