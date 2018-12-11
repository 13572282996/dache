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

<script src="${base}/resources/admin2/custom/list.js" type="text/javascript"></script>

<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.js"></script>

<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.validate.js"></script>

<script type="text/javascript" src="${base}/resources/admin2/custom/editor/kindeditor.js"></script>

<script type="text/javascript" src="${base}/resources/admin2/custom/common.js"></script>

<script type="text/javascript" src="${base}/resources/admin2/custom/input.js"></script>

<script type="text/javascript" src="${base}/resources/admin2/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			articleCategoryId: "required"
		
		}
	});
});	
	var $EditForm = $("#EditForm");
	
	[@flash_message /]
	
	// 表单验证
	$EditForm.validate({
		rules: {
			title: "required",
			articleCategoryId: "required"
		
		}
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
     <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
     <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  
    
</head>

<div class="content"  style="left:-162px;width: 95%">
<!--        
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->      
        <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.article.list")}</li> (${message("admin.page.total", page.total)})
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
					<span class="requiredField">*</span>${message("Article.title")}:
				</th>
				<td>
					<input type="text" name="title" class="text" maxlength="200" />
				</td>
			</tr>
			<!--<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.articleCategory")}:
				</th>
				<td>
					<select name="articleCategoryId" class="ui_select01">
						[#list articleCategoryTree as articleCategory]
							<option value="${articleCategory.id}">
								[#if articleCategory.grade != 0]
									[#list 1..articleCategory.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${articleCategory.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>-->
			<tr>
				<th>
					作者:
				</th>
				<td>
					<input type="text" name="author" class="text" maxlength="200" />
				</td>
			</tr>
			<!--<tr id="articleForm">
				<th>
					文章来源:
				</th>
				<td>
					<input type="text" name="articleForm" class="text" maxlength="200" />
				</td>
			</tr>-->
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isPublication" value="true" checked="checked" />${message("Article.isPublication")}
						<input type="hidden" name="_isPublication" value="false" />
					</label>
					<label>
						<input type="checkbox" name="isTop" value="true" />${message("Article.isTop")}
						<input type="hidden" name="_isTop" value="false" />
					</label>
				</td>
			</tr>
			<!--<tr>
				<th>
					展示图片:
				</th>
				<td>
					
					<input type="file" name="pictureFile" value="" class="text" maxlength="200"/>
					
				</td>
			</tr> 
			<tr>
				<th>
					文章简介:
				</th>
				<td>
					
					<input type="text" name="info" value="" class="text" style="width:500px;" maxlength="200"/>
					
				</td>
			</tr> -->
			<tr>
				<th>
					${message("Article.content")}:
				</th>
				<td>
					<textarea id="editor" name="content"  style="width:98%;height:70%;"></textarea>
				</td>
			</tr>
			<!--<tr>
				<th>
					${message("Article.seoTitle")}:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Article.seoKeywords")}:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Article.seoDescription")}:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200" />
				</td>
			</tr>-->
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

	
<!--编辑-->
<div id="light_edit" class="white_content">
			<div class="tk_header">
            	<p style="font-size: 18px">编辑</p>
        	</div>
        	<div>
        	
				<form id="EditForm" action="update.jhtml" class="form-inline" method="post" enctype="multipart/form-data">
				    
				    <table class="layui-table" id="addEditTable1">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.title")}:
				</th>
				<td>
					<input type="text" name="title" value="" class="text" maxlength="200" />
				</td>
			</tr>
			<!--<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.articleCategory")}:
				</th>
				<td>
					<select name="articleCategoryId" class="ui_select01">
						[#list articleCategoryTree as articleCategory]
							<option value="${articleCategory.id}">
								[#if articleCategory.grade != 0]
									[#list 1..articleCategory.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${articleCategory.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>-->
			<tr>
				<th>
					作者:
				</th>
				<td>
					<input type="text" name="author" class="text" maxlength="200" />
				</td>
			</tr>
			<!--<tr id="articleForm">
				<th>
					文章来源:
				</th>
				<td>
					<input type="text" name="articleForm" class="text" maxlength="200" />
				</td>
			</tr>-->
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isPublication" value="true" checked="checked" />${message("Article.isPublication")}
						<input type="hidden" name="_isPublication" value="false" />
					</label>
					<label>
						<input type="checkbox" name="isTop" value="true" />${message("Article.isTop")}
						<input type="hidden" name="_isTop" value="false" />
					</label>
				</td>
			</tr>
			<!--<tr>
				<th>
					展示图片:
				</th>
				<td>
					
					<input type="file" name="pictureFile" value="" class="text" maxlength="200"/>
					
				</td>
			</tr> 
			<tr>
				<th>
					文章简介:
				</th>
				<td>
					
					<input type="text" name="info" value="" class="text" style="width:500px;" maxlength="200"/>
					
				</td>
			</tr> -->
			<tr>
				<th>
					${message("Article.content")}:
				</th>
				<td>
					<textarea id="editor" name="content"  style="width:98%;height:70%;"></textarea>
				</td>
			</tr>
			<!--<tr>
				<th>
					${message("Article.seoTitle")}:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Article.seoKeywords")}:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Article.seoDescription")}:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200" />
				</td>
			</tr>-->
				<tr>
					<th>&nbsp;</th>
					<td>
					  &nbsp;<input type="submit" value="确定" class="layui-btn layui-btn-sm"/>
					  &nbsp;<input type="button" value="取消"   class="layui-btn layui-btn-sm" href = "javascript:void(0)" onclick = "document.getElementById('light_edit').style.display='none';document.getElementById('fade').style.display='none'">
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
<!--指定按名称搜索	<select name="searchProperty" class="listselect">
		<option value="title" [#if page.searchProperty == "title"]  selected="selected" [/#if]>${message("Article.title")}</option>
	</select>-->
	<input type="hidden" name="searchProperty" value="title" />
	<input id="searchValue" name="searchValue" value="${page.searchValue}" class="input-xlarge" placeholder="Search Help..." type="text">
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="layui-table" id="listTable" >
      <tr>
			<th width="30"  style="text-align:center">
				<input type="checkbox" id="selectAll" />
			</th>
			<th style="text-align:center">
				${message("Article.title")}
			</th>
			<!--<th>
				${message("Article.articleCategory")}
			</th>-->
			<th style="text-align:center">
				${message("Article.isPublication")}
			</th>
			<th style="text-align:center">
				${message("admin.common.createDate")}
			</th>
			<th style="text-align:center">
				${message("admin.common.handle")}
			</th>
		</tr>
		[#list page.content as article]
			<tr>
				<td style="text-align:center">
					<input type="checkbox" name="ids" value="${article.id}" />
				</td>
				<td style="text-align:center">
					<span title="${article.title}">${abbreviate(article.title, 50, "...")}</span>
				</td>
				<!--<td>
					${article.articleCategory.name}
				</td>-->
				<td style="text-align:center;">
					<span class="${article.isPublication?string("true", "false")}Icon">${article.isPublication?string("是","否")}&nbsp;</span>
				</td>
				<td style="text-align:center">
					<span title="${article.createDate?string("yyyy-MM-dd HH:mm:ss")}">${article.createDate}</span>
				</td>
				<td style="text-align:center">
					<a class="layui-btn layui-btn-xs" onclick = "document.getElementById('light_edit').style.display='block';document.getElementById('fade').style.display='block'">${message("admin.common.edit")}</a>
					
					<!--编辑弹框<a class="layui-btn layui-btn-xs" href="edit.jhtml?id=${article.id}">${message("admin.common.edit")}</a>
					-->
				</td>
			</tr>
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

<script type="text/javascript">
        $(function(){
            //用jQuery获取table中td值
            $("#listTable td").click(function(){
                alert("table td值："+$(this).text());
            });
            
            //jQuery获取table中点击位置所在行的id
            $("#listTable td").click(function() {
                //td的id 
                alert($(this).attr("id"));
                //tr的id  
                alert($(this).parent().attr("id"));
            });
        });
        </script>



 </body>
</html>