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
            left: 20%;
            width: 45%;
            height: 70%;
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

<div class="content"  style="left:-162px;width: 95%">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.admin.list")}</li> (${message("admin.page.total", page.total)})
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">

<!--添加-->
<div id="light" class="white_content">
			<div class="tk_header">
            	<p style="font-size: 18px">添加</p>
        	</div>
        	<div>
<form id="inputForm" action="save.jhtml" class="form-inline" method="post" enctype="multipart/form-data">
    <input type="hidden" name="articleCategoryId" value="1" />
    <table class="layui-table" id="addEditTable">
				<tr>
					<th>
						<span class="requiredField">*</span>${message("Admin.username")}:
					</th>
					<td>
						<input type="text" name="username" class="text" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>${message("Admin.password")}:
					</th>
					<td>
						<input type="password" id="password" name="password" class="text" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>${message("admin.admin.rePassword")}:
					</th>
					<td>
						<input type="password" name="rePassword" class="text" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>${message("Admin.email")}:
					</th>
					<td>
						<input type="text" name="email" class="text" maxlength="200" />
					</td>
				</tr>
				<tr class="roles">
					<th>
						<span class="requiredField">*</span>${message("Admin.roles")}:
					</th>
					<td>
						<span class="fieldSet">
							[#list roles as role]
								<label>
									<input type="checkbox" name="roleIds" value="${role.id}" />${role.name}
								</label>
							[/#list]
						</span>
					</td>
				</tr>
				<tr>
					<th>
						${message("admin.common.setting")}:
					</th>
					<td>
						<label>
							<input type="checkbox" name="isEnabled" value="true" checked="checked" />${message("Admin.isEnabled")}
							<input type="hidden" name="_isEnabled" value="false" />
						</label>
					</td>
				</tr>
				<tr>
					<th>
						${message("Admin.department")}:
					</th>
					<td>
						<input type="text" name="department" class="text" maxlength="200" />
					</td>
				</tr>
				<tr>
					<th>
						${message("Admin.name")}:
					</th>
					<td>
						<input type="text" name="name" class="text" maxlength="200" />
					</td>
				</tr>			
			
				<tr>
					<th>&nbsp;</th>
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
    <!--<input type="button" value="${message("admin.common.add")}" class="btn" onclick="location.href='add.jhtml'"/>
    -->
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
<!--指定按名称搜索	<select name="searchProperty" class="listselect">
		<option value="title" [#if page.searchProperty == "title"]  selected="selected" [/#if]>${message("Article.title")}</option>
	</select>-->
	<input type="hidden" name="searchProperty" value="username" />
	<input id="searchValue" name="searchValue" value="${page.searchValue}" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="layui-table" id="listTable">
     <tr>
							<th class="check"  style="text-align:center">
								<input type="checkbox" id="selectAll" />
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="username">${message("Admin.username")}</a>
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="email">${message("Admin.email")}</a>
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="name">${message("Admin.name")}</a>
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="department">${message("Admin.department")}</a>
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="loginDate">${message("Admin.loginDate")}</a>
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="loginIp">${message("Admin.loginIp")}</a>
							</th>
							<th  style="text-align:center">
								<span>${message("admin.admin.status")}</span>
							</th>
							<th  style="text-align:center">
								<a href="javascript:;" class="sort" name="createDate">${message("admin.common.createDate")}</a>
							</th>
							<th  style="text-align:center">
								<span>${message("admin.common.handle")}</span>
							</th>
						</tr>
						[#list page.content as admin]
							<tr>
								<td style="text-align:center">
									<input type="checkbox" name="ids" value="${admin.id}" />
								</td>
								<td style="text-align:center">
									${admin.username}
								</td>
								<td style="text-align:center">
									${admin.email}
								</td>
								<td>
									${admin.name}
								</td>
								<td style="text-align:center">
									${admin.department}
								</td>
								<td style="text-align:center">
									[#if admin.loginDate??]
										<span title="${admin.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.loginDate}</span>
									[#else]
										-
									[/#if]
								</td>
								<td style="text-align:center">
									${(admin.loginIp)!"-"}
								</td>
								<td style="text-align:center">
									[#if !admin.isEnabled]
										<span class="red">${message("admin.admin.disabled")}</span>
									[#elseif admin.isLocked]
										<span class="red"> ${message("admin.admin.locked")} </span>
									[#else]
										<span class="green">${message("admin.admin.normal")}</span>
									[/#if]
								</td>
								<td style="text-align:center">
									<span title="${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.createDate}</span>
								</td>
								<td style="text-align:center">
									<a href="edit.jhtml?id=${admin.id}" class="layui-btn layui-btn-xs">${message("admin.common.edit")}</a>
								</td>
							</tr>
						[/#list]

    </table>
</div>

[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
	[#include "/admin/include/pagination.ftl"]
[/@pagination]

</form>
<!-- 保留原始的分页页面
<div class="pagination">
    <ul>
        <li><a href="#">Prev</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</div>
-->

<!--原始提示框消息
<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Delete Confirmation</h3>
    </div>
    <div class="modal-body">
        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>Are you sure you want to delete the user?</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
    </div>
</div>
-->

 <!--                   
                   <footer>
                        <hr>

                        
                        <p class="pull-right">Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>

                        <p>&copy; 2012 <a href="#" target="_blank">Portnine</a></p>
                    </footer>
  -->

		</div>	<!--class="row-fluid"-->
	</div> <!--class="container-fluid"-->
</div><!--class="content"-->

 </body>
</html>