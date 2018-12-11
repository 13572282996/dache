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
            <li class="active">${message("admin.navigation.list")}</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="listForm" action="list.jhtml" method="get">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
    <input type="button" value="${message("admin.common.add")}" class="btn" onclick="location.href='add.jhtml'"/>
    <input type="button" value="${message("admin.common.delete")}" class="btn disabled" id="deleteButton" /> 
	<input type="button" value="${message("admin.common.refresh")}" class="btn" id="refreshButton"/>
	
	
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="table" id="listTable">
      <tr>
							<th class="check">
								<input type="checkbox" id="selectAll" />
							</th>
							<th>
								<span>${message("Navigation.name")}</span>
							</th>
							<th>
								<span>${message("Navigation.position")}</span>
							</th>
							<th>
								<span>${message("Navigation.isBlankTarget")}</span>
							</th>
							<th>
								<span>${message("admin.common.order")}</span>
							</th>
							<th>
								<span>${message("admin.common.handle")}</span>
							</th>
						</tr>
						[#list topNavigations as navigation]
							<tr>
								<td>
									<input type="checkbox" name="ids" value="${navigation.id}" />
								</td>
								<td>
									${navigation.name}
								</td>
								<td>
									${message("Navigation.Position." + navigation.position)}
								</td>
								<td>
									${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
								</td>
								<td>
									${navigation.order}
								</td>
								<td>
									<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
								</td>
							</tr>
							[#if navigation.children?has_content]
									[#list navigation.children as navigation]
										<tr>
											<td>
												<input type="checkbox" name="ids" value="${navigation.id}" />
											</td>
											<td>
												[#if navigation.grade != 0]
													[#list 1..navigation.grade as i]
														&nbsp;&nbsp;&nbsp;&nbsp;
													[/#list]
												[/#if]${navigation.name}
											</td>
											<td>
												${message("Navigation.Position." + navigation.position)}
											</td>
											<td>
												${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
											</td>
											<td>
												${navigation.order}
											</td>
											<td>
												<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
											</td>
										</tr>
										[#if navigation.children?has_content]
											[#list navigation.children as navigation]
												<tr>
													<td>
														<input type="checkbox" name="ids" value="${navigation.id}" />
													</td>
													<td>
														[#if navigation.grade != 0]
															[#list 1..navigation.grade as i]
																&nbsp;&nbsp;&nbsp;&nbsp;
															[/#list]
														[/#if]${navigation.name}
													</td>
													<td>
														${message("Navigation.Position." + navigation.position)}
													</td>
													<td>
														${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
													</td>
													<td>
														${navigation.order}
													</td>
													<td>
														<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
													</td>
												</tr>
											[/#list]
										[/#if]
									[/#list]
								[/#if]
						[/#list]
						[#if topNavigations?has_content]
							<tr>
								<td colspan="7">&nbsp;</td>
							</tr>
						[/#if]
						[#list middleNavigations as navigation]
							<tr>
								<td>
									<input type="checkbox" name="ids" value="${navigation.id}" />
								</td>
								<td>
									${navigation.name}
								</td>
								<td>
									${message("Navigation.Position." + navigation.position)}
								</td>
								<td>
									${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
								</td>
								<td>
									${navigation.order}
								</td>
								<td>
									<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
								</td>
							</tr>
							[#if navigation.children?has_content]
									[#list navigation.children as navigation]
										<tr>
											<td>
												<input type="checkbox" name="ids" value="${navigation.id}" />
											</td>
											<td>
												[#if navigation.grade != 0]
													[#list 1..navigation.grade as i]
														&nbsp;&nbsp;&nbsp;&nbsp;
													[/#list]
												[/#if]${navigation.name}
											</td>
											<td>
												${message("Navigation.Position." + navigation.position)}
											</td>
											<td>
												${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
											</td>
											<td>
												${navigation.order}
											</td>
											<td>
												<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
											</td>
										</tr>
										[#if navigation.children?has_content]
											[#list navigation.children as navigation]
												<tr>
													<td>
														<input type="checkbox" name="ids" value="${navigation.id}" />
													</td>
													<td>
														[#if navigation.grade != 0]
															[#list 1..navigation.grade as i]
																&nbsp;&nbsp;&nbsp;&nbsp;
															[/#list]
														[/#if]${navigation.name}
													</td>
													<td>
														${message("Navigation.Position." + navigation.position)}
													</td>
													<td>
														${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
													</td>
													<td>
														${navigation.order}
													</td>
													<td>
														<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
													</td>
												</tr>
											[/#list]
										[/#if]
									[/#list]
							[/#if]
						[/#list]
						[#if middleNavigations?has_content]
							<tr>
								<td colspan="7">&nbsp;</td>
							</tr>
						[/#if]
						[#list bottomNavigations as navigation]
							<tr>
								<td>
									<input type="checkbox" name="ids" value="${navigation.id}" />
								</td>
								<td>
									${navigation.name}
								</td>
								<td>
									${message("Navigation.Position." + navigation.position)}
								</td>
								<td>
									${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
								</td>
								<td>
									${navigation.order}
								</td>
								<td>
									<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
								</td>
							</tr>
							[#if navigation.children?has_content]
									[#list navigation.children as navigation]
										<tr>
											<td>
												<input type="checkbox" name="ids" value="${navigation.id}" />
											</td>
											<td>
												[#if navigation.grade != 0]
													[#list 1..navigation.grade as i]
														&nbsp;&nbsp;&nbsp;&nbsp;
													[/#list]
												[/#if]${navigation.name}
											</td>
											<td>
												${message("Navigation.Position." + navigation.position)}
											</td>
											<td>
												${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
											</td>
											<td>
												${navigation.order}
											</td>
											<td>
												<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
											</td>
										</tr>
										[#if navigation.children?has_content]
											[#list navigation.children as navigation]
												<tr>
													<td>
														<input type="checkbox" name="ids" value="${navigation.id}" />
													</td>
													<td>
														[#if navigation.grade != 0]
															[#list 1..navigation.grade as i]
																&nbsp;&nbsp;&nbsp;&nbsp;
															[/#list]
														[/#if]${navigation.name}
													</td>
													<td>
														${message("Navigation.Position." + navigation.position)}
													</td>
													<td>
														${message(navigation.isBlankTarget?string('admin.common.true', 'admin.common.false'))}
													</td>
													<td>
														${navigation.order}
													</td>
													<td>
														<a href="edit.jhtml?id=${navigation.id}">[${message("admin.common.edit")}]</a>
													</td>
												</tr>
											[/#list]
										[/#if]
									[/#list]
							[/#if]
						[/#list]
<!-- 保留一个原始的表格
        <tr>
          <td>6</td>
          <td>Chris</td>
          <td>Albert</td>
          <td>cab79</td>
          <td>
              <a href="user.html"><i class="icon-pencil"></i></a>
              <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
 -->
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