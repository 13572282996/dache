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
<script type="text/javascript" src="${base}/resources/admin2/custom/common.js"></script>
<script src="${base}/resources/admin2/custom/list.js" type="text/javascript"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
	

});	
</script>

</head>

<div class="content">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.template.list")}</li> 
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
<!--    <input type="button" value="${message("admin.common.add")}" class="btn" onclick="location.href='add.jhtml'"/>
    <input type="button" value="${message("admin.common.delete")}" class="btn disabled" id="deleteButton" /> -->
	<input type="button" value="${message("admin.common.refresh")}" class="btn" id="refreshButton"/>
	
	<div class="btn-group" id="groupDiv">
		<a class='btn dropdown-toggle' data-toggle='dropdown' href='javascript:;'>类型<span class='caret'></span></a>
		<ul class='dropdown-menu' id="typeSelect">
			[#list types as list]
				[#if list == type]
				<li class="active">
					<a href="${base}/admin/template/list.jhtml?type=${type}" val="${type}">${message("Template.Type." + type)}</a>
				</li>
				[#else]
				<li class="">
					<a href="${base}/admin/template/list.jhtml?type=${list}" val="${list}">${message("Template.Type." + list)}</a>
				</li>
				[/#if]
			[/#list]
			
		</ul>
	</div>
	
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="table" id="listTable">
      <tr>
			<tr>
							<th>
								<span>${message("Template.name")}</span>
							</th>
							<th>
								<span>${message("Template.type")}</span>
							</th>
							<th>
								<span>${message("Template.templatePath")}</span>
							</th>
							<th>
								<span>${message("Template.description")}</span>
							</th>
							<th>
								<span>${message("admin.common.handle")}</span>
							</th>
						</tr>
						[#list templates as template]
							<tr>
								<td>
									${template.name}
								</td>
								<td>
									${message("Template.Type." + template.type)}
								</td>
								<td>
									${template.templatePath}
								</td>
								<td>
									[#if template.description??]
										<span title="${template.description}">${abbreviate(template.description, 50, "...")}</span>
									[/#if]
								</td>
								<td>
									<a href="edit.jhtml?id=${template.id}">[${message("admin.common.edit")}]</a>
								</td>
							</tr>
						[/#list]
    </table>
</div>


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