<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.resource.edit")} ${setting.siteName}</title>
<meta name="author" content="${setting.siteName}" />
<meta name="copyright" content="${setting.siteName}" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]

	// 表单验证
	$inputForm.validate({
		rules: {
			groupName: "required",
			name: {
				required: true,
				remote: {
					url: "check_name.jhtml?previousName=${resource.name}",
					cache: false
				}
			},
			mark: {
				required: true,
				remote: {
					url: "check_mark.jhtml?previousMark=${resource.mark}",
					cache: false
				}
			},
			url: {
				required: true,
				remote: {
					url: "check_resourceurl.jhtml?previousUrl=${resource.url?url}",
					cache: false
				}
			},
			order: "digits"
		},
		messages: {
			name: {
				remote: "${message("admin.resource.name.exist")}"
			},
			mark: {
				remote: "${message("admin.resource.mark.exist")}"
			},
			url: {
				remote: "${message("admin.resource.url.exist")}"
			},
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.resource.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${resource.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>资源组名称:
				</th>
				<td>
					<input type="text"  name="groupName" value="${resource.groupName}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>资源名称:
				</th>
				<td>
					<input type="text"  name="name" value="${resource.name}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>资源标识:
				</th>
				<td>
					<input type="text"  name="mark" value="${resource.mark}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>资源url:
				</th>
				<td>
					<input type="text"  name="url" value="${resource.url}" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${resource.order}" maxlength="9" />
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