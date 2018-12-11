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
	var $clearButton = $("#clearButton");
	var $resultRow = $("#listTable tr:gt(0)");
	
	[@flash_message /]
	
	$clearButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.clearConfirm")}",
			onOk: function() {
				$.ajax({
					url: "clear.jhtml",
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							$resultRow.remove();
						}
						$.message(message);
					}
				});
			}
		});
		return false;
	});


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
            <li class="active">${message("admin.log.list")}</li> (${message("admin.page.total", page.total)})
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">




            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
    <input type="button" value="${message("admin.common.clear")}" class="layui-btn layui-btn-sm" id="clearButton"/>
    <input type="button" value="${message("admin.common.delete")}" class="layui-btn layui-btn-sm disabled" id="deleteButton" /> 
	<input type="button" value="${message("admin.common.refresh")}" class="layui-btn layui-btn-sm" id="refreshButton"/>
	
	<div class="btn-group" id="groupDiv" style="left:10px">
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
	<input type="hidden" name="searchProperty" value="operator" />
	<input id="searchValue" name="searchValue" value="${page.searchValue}" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="table" id="listTable">
     <tr>
				<th class="check"  style="text-align:center">
					<input type="checkbox" id="selectAll" />
				</th>
				<th  style="text-align:center">
					<a href="javascript:;" class="sort" name="operation">${message("Log.operation")}</a>
				</th>
				<th  style="text-align:center">
					<a href="javascript:;" class="sort" name="operator">${message("Log.operator")}</a>
				</th>
				<th  style="text-align:center">
					<a href="javascript:;" class="sort" name="ip">${message("Log.ip")}</a>
				</th>
				<th  style="text-align:center">
					<a href="javascript:;" class="sort" name="content">${message("Log.content")}</a>
				</th>
				<th  style="text-align:center">
					<a href="javascript:;" class="sort" name="createDate">${message("admin.common.createDate")}</a>
				</th>
				<th  style="text-align:center">
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as log]
				<tr>
					<td style="text-align:center">
						<input type="checkbox" name="ids" value="${log.id}" />
					</td>
					<td style="text-align:center">
						${log.operation}
					</td>
					<td style="text-align:center">
						${log.operator}
					</td>
					<td style="text-align:center">
						${log.ip}
					</td>
					<td style="text-align:center">
						[#if log.content??]
							<span title="${log.content}">${abbreviate(log.content, 50, "...")}</span>
						[/#if]
					</td>
					<td style="text-align:center">
						<span title="${log.createDate?string("yyyy-MM-dd HH:mm:ss")}">${log.createDate}</span>
					</td>
					<td style="text-align:center">
						<a href="view.jhtml?id=${log.id}" class="layui-btn layui-btn-xs">[${message("admin.common.view")}]</a>
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