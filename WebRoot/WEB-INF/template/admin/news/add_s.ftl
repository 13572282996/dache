<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.article.add")} ${setting.siteName}</title>
<meta name="author" content="${setting.siteName}" />
<meta name="copyright" content="${setting.siteName}" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
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
	$("#news").click(function(){
		$("#articleForm").show();
		$("#lineMobile").hide();
		$("#timeDate").hide();
		$("#address").hide();
	});
	$("#huodong").click(function(){
		$("#articleForm").hide();
		$("#lineMobile").show();
		$("#timeDate").show();
		$("#address").show();
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 新闻添加
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>区分分类:
				</th>
				<td>
					<input type="radio" name="articleClass" value="文章" id="news" checked="checked"/>文章
					<input type="radio" name="articleClass" value="活动" id="huodong" />活动
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.title")}:
				</th>
				<td>
					<input type="text" name="title" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.articleCategory")}:
				</th>
				<td>
					<select name="articleCategoryId" >
						<option value="">请选择</option>
						[#list articleCategorys as articleCategory]
							<option value="${articleCategory.id}">
								${articleCategory.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					作者(联系人):
				</th>
				<td>
					<input type="text" name="author" class="text" maxlength="200" />
				</td>
			</tr>
			<tr id="articleForm">
				<th>
					文章来源:
				</th>
				<td>
					<input type="text" name="articleForm" class="text" maxlength="200" />
				</td>
			</tr>
			<tr id="lineMobile" style="display: none;">
				<th>
					联系方式:
				</th>
				<td>
					<input type="text" name="lineMobile" class="text" maxlength="200" />
				</td>
			</tr>
			<tr id="timeDate" style="display: none;">
				<th>
					时间:
				</th>
				<td>
					<input type="text" name="timeDate" class="text" maxlength="200" onfocus="WdatePicker();"/>
				</td>
			</tr>
			<tr id="address" style="display: none;">
				<th>
					地点:
				</th>
				<td>
					<input type="text" name="address" class="text" maxlength="200" />
				</td>
			</tr>
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
		<!-- <tr>
				<th>
					${message("Article.tags")}:
				</th>
				<td>
					[#list tags as tag]
						<label>
							<input type="checkbox" name="tagIds" value="${tag.id}" />${tag.name}
						</label>
					[/#list]
				</td>
			</tr> -->
			<tr>
				<th>
					${message("Article.content")}:
				</th>
				<td>
					<textarea id="editor" name="content"  style="width:1000px;height:500px;"></textarea>
				</td>
			</tr>
			<tr>
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