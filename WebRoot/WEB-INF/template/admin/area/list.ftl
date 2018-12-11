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

	var $delete = $("#listTable a.delete");
	
	[@flash_message /]
	
	// 删除
	$delete.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {id: $this.attr("val")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$this.parent().html("&nbsp;");
						}
					}
				});
			}
		});
		return false;
	});

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
            top: 10%;
            left: 30%;
            width: 40%;
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

<div class="content"  style="left:-162px;width: 95%">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.area.list")}</li> 
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">

<!--添加-->            
 <div id="light" class="white_content">
			<div class="tk_header">
            	<p style="font-size: 18px">添加</p>
        	</div>
        	<div>
				<form id="inputForm" action="save.jhtml" class="form-inline" method="post">
				    <table class="layui-table" id="addEditTable" style="position:relative;left:-30px;top:2px">
[#if parent??]
	<input type="hidden" name="parentId" value="${parent.id}" />
[/#if]
    	<tr>
				<th>
					${message("admin.area.parent")}:
				</th>
				<td>
					[#if parent??]${parent.name}[#else]${message("admin.area.root")}[/#if]
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Area.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" />
				</td>
			</tr>
								<tr>
									<th>
									</th>
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
	
 <!--   <input type="button" value="${message("admin.common.add")}" class="layui-btn layui-btn-sm" onclick="location.href='add.jhtml[#if parent??]?parentId=${parent.id}[/#if]'"/>
-->
							[#if parent??]
								[#if parent.parent??]
									<input type="button" value="${message("admin.area.parent")}" class="ui_input_btn01" onclick="location.href='list.jhtml?parentId=${parent.parent.id}'"/>
								[#else]
									<input type="button" value="${message("admin.area.parent")}" class="ui_input_btn01" onclick="location.href='list.jhtml'"/>
								[/#if]
							[/#if]
</div>


<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="table" id="listTable">
      <tr>
							<th colspan="5" class="green" style="text-align: center;">
								[#if parent??]${message("admin.area.parent")} - ${parent.name}[#else]${message("admin.area.root")}[/#if]
							</th>
						</tr>
						[#list areas?chunk(5, "") as row]
							<tr>
								[#list row as area]
									[#if area?has_content]
										<td>
											<a href="list.jhtml?parentId=${area.id}" title="${message("admin.common.view")}">${area.name}</a>
											<a href="edit.jhtml?id=${area.id}">[${message("admin.common.edit")}]</a>
											<a href="javascript:;" class="delete" val="${area.id}">[${message("admin.common.delete")}]</a>
										</td>
									[#else]
										<td>
											&nbsp;
										</td>
									[/#if]
								[/#list]
							</tr>
						[/#list]
						[#if !areas?has_content]
							<tr>
								<td colspan="5" style="text-align: center; color: red;">
									${message("admin.area.emptyChildren")} <a href="add.jhtml[#if parent??]?parentId=${parent.id}[/#if]" style="color: gray">${message("admin.common.add")}</a>
								</td>
							</tr>
						[/#if]
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