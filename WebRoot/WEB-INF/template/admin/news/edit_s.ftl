<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.article.edit")} ${setting.siteName}</title>
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
	$("#articleClass").val($("#articleClassId").val());
	var value =	$('input:radio[name="articleClass"]:checked').val();
	if(value=="文章"){
		$("#lineMobile").hide();
		$("#timeDate").hide();
		$("#address").hide();
	}
	if(value=="活动"){
		$("#articleForm").hide();
		$("#lineMobile").show();
		$("#timeDate").show();
		$("#address").show();
	}
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 新闻编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${article.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>区分分类:
				</th>
				<td>
					[#if article.articleClass == "文章"]
					<input type="radio" name="articleClass" value="文章" id="news" checked="checked"/>文章
					<input type="radio" name="articleClass" value="活动" id="huodong" />活动
					[#elseif article.articleClass == "活动"]
					<input type="radio" name="articleClass" value="文章" id="news" />文章
					<input type="radio" name="articleClass" value="活动" id="huodong" checked="checked" />活动
					[#else]
					<input type="radio" name="articleClass" value="文章" id="news" checked="checked"/>文章
					<input type="radio" name="articleClass" value="活动" id="huodong" />活动
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.title")}:
				</th>
				<td>
					<input type="text" name="title" class="text" value="${article.title}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Article.articleCategory")}:
				</th>
				<td>
					<select name="articleCategoryId" id="articleCategoryId" >
						<option value="">请选择</option>
						[#list articleCategorys as articleCategory]
							<option value="${articleCategory.id}" [#if articleCategory.id == article.articleCategory.id] selected="selected"[/#if]>
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
					<input type="text" name="author" class="text" value="${article.author}" maxlength="200" />
				</td>
			</tr>
			<tr id="articleForm">
				<th>
					文章来源:
				</th>
				<td>
					<input type="text" name="articleForm" class="text" value="${article.articleForm}" maxlength="200" />
				</td>
			</tr>
			<tr id="lineMobile" style="display: none;">
				<th>
					联系方式:
				</th>
				<td>
					<input type="text" name="lineMobile" class="text" value="${article.lineMobile}" maxlength="200" />
				</td>
			</tr>
			<tr id="timeDate" style="display: none;">
				<th>
					时间:
				</th>
				<td>
					<input type="text" name="timeDate" class="text" value="${article.timeDate}" maxlength="200" onfocus="WdatePicker();"/>
				</td>
			</tr>
			<tr id="address" style="display: none;">
				<th>
					地点:
				</th>
				<td>
					<input type="text" name="address" class="text" value="${article.address}" maxlength="200" />
				</td>
			</tr>
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
					${message("Article.tags")}:
				</th>
				<td>
					[#list tags as tag]
						<label>
							<input type="checkbox" name="tagIds" value="${tag.id}"[#if article.tags?seq_contains(tag)] checked="checked"[/#if] />${tag.name}
						</label>
					[/#list]
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
			<tr>
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