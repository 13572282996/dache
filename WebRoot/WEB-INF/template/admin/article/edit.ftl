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
</script>

   <!-- Demo page code -->

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

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
    
    <div class="content" style="left:-162px;width: 95%">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.article.edit")}</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
<form id="inputForm" action="update.jhtml" class="form-inline" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${article.id}" />
<input type="hidden" name="articleCategoryId" value="1" />
    <table class="layui-table" id="addEditTable">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.title")}:
				</th>
				<td>
					<input type="text" name="title" class="text" value="${article.title}" maxlength="200" />
				</td>
			</tr>
			<!--<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.articleCategory")}:
				</th>
				<td>
					<select name="articleCategoryId" class="ui_select01">
						[#list articleCategoryTree as articleCategory]
							<option value="${articleCategory.id}"[#if articleCategory == article.articleCategory] selected="selected"[/#if]>
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
					<input type="text" name="author" class="text" value="${article.author}" maxlength="200" />
				</td>
			</tr>
			<!--<tr id="articleForm">
				<th>
					文章来源:
				</th>
				<td>
					<input type="text" name="articleForm" class="text" value="${article.articleForm}" maxlength="200" />
				</td>
			</tr>-->
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isPublication" value="true"[#if article.isPublication] checked="checked"[/#if] />${message("Article.isPublication")}
						<input type="hidden" name="_isPublication" value="false" />
					</label>
					<label>
						<input type="checkbox" name="isTop" value="true"[#if article.isTop] checked="checked"[/#if] />${message("Article.isTop")}
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
					
					<input type="text" name="info" value="${article.info}" class="text" style="width:500px;" maxlength="200"/>
					
				</td>
			</tr> -->
			<tr>
				<th>
					${message("Article.content")}:
				</th>
				<td>
					<textarea id="editor" name="content"  style="width:1000px;height:500px;">${article.content?html}</textarea>
				</td>
			</tr>
			<!--<tr>
				<th>
					${message("Article.seoTitle")}:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" value="${article.seoTitle}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Article.seoKeywords")}:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" value="${article.seoKeywords}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Article.seoDescription")}:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" value="${article.seoDescription}" maxlength="200" />
				</td>
			</tr>-->
				<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="submit" value="${message("admin.common.submit")}" class="layui-btn layui-btn-sm"/>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="layui-btn layui-btn-sm" onclick="location.href='list.jhtml'"/>
					</td>
				</tr>
    </table>

</form>
</div>

                    
		</div>	<!--class="row-fluid"-->
	</div> <!--class="container-fluid"-->
</div><!--class="content"-->
    
    
  </body>
</html>