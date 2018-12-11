<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.adPosition.add")} ${setting.siteName}</title>
<meta name="Author" content="${setting.siteName}" />
<meta name="Copyright" content="${setting.siteName}" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	//编辑时显示
	$("#referenceClass").val($("#addReferenceClass").val());
	// 表单验证
	$inputForm.validate({
		
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 文档管理编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${reference.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.articleCategory")}:
				</th>
				<td>
					<select name="articleCategoryId" >
						[#list articleCategoryTree as articleCategory]
							<option value="${articleCategory.id}"[#if articleCategory == reference.articleCategory] selected="selected"[/#if]>
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
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>文件名:
				</th>
				<td>
					<input type="text" name="fileName" class="text" value="${reference.fileName}" />
				</td>
			</tr><tr>
				<th>
					<span class="requiredField">*</span>文档封面:
				</th>
				<td>
					<input type="file" name="referenceClass0" class="text"  />
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>上传文档:
				</th>
				<td>
					<input type="file" name="filePath0" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>