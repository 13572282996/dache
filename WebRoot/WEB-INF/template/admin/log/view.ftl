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
            <li class="active">${message("admin.log.view")}</li> 
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
                    
<div class="btn-toolbar" style="margin-top: 1px;">
    
	
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="table" id="listTable">
      <tr>
			<th>
				${message("Log.operation")}:
			</th>
			<td>
				${log.operation}
			</td>
		</tr>
		<tr>
			<th>
				${message("Log.operator")}:
			</th>
			<td>
				${log.operator}
			</td>
		</tr>
		<tr>
			<th>
				${message("Log.content")}:
			</th>
			<td>
				${log.content!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("Log.parameter")}:
			</th>
			<td>
				<textarea class="textarea" style="width: 98%; height: 300px;" readonly="readonly">${log.parameter?html}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				${message("Log.ip")}:
			</th>
			<td>
				${log.ip}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.common.createDate")}
			</th>
			<td>
				${log.createDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>
			<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="btn" onclick="location.href='list.jhtml'"/>
					</td>
				</tr>
    </table>
</div>


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