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
<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $type = $("#type");
	var $contentTr = $("#contentTr");
	var $pathTr = $("#pathTr");
	var $path = $("#path");
	var $browserButton = $("#browserButton");
	
	[@flash_message /]
	
	[#if ad.type != "text"]
		$browserButton.browser({
			type: "${ad.type}"
		});
	[/#if]
	
	// “类型”修改
	$type.change(function() {
		if ($type.val() == "text") {
			$contentTr.show();
			$pathTr.hide();
			$path.prop("disabled", true)
		} else {
			$contentTr.hide();
			$pathTr.show();
			$path.prop("disabled", false)
			$browserButton.unbind().browser({
				type: $type.val()
			});
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			adPositionId: "required",
			path: "required",
			order: "digits"
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
    
    
    <div class="content">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.ad.edit")}</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
<form id="inputForm" action="update.jhtml" class="form-inline" method="post">
<input type="hidden" name="id" value="${ad.id}" />

    <table class="table" id="addEditTable">
    	<tr>
				<th>
					<span class="requiredField">*</span>${message("Ad.title")}:
				</th>
				<td>
					<input type="text" name="title" class="text" value="${ad.title}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Ad.type")}:
				</th>
				<td>
					<select id="type" name="type" class="ui_select01">
						[#list types as type]
							<option value="${type}"[#if type == ad.type] selected="selected"[/#if]>${message("Ad.Type." + type)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("Ad.adPosition")}:
				</th>
				<td>
					<select name="adPositionId" class="ui_select01">
						[#list adPositions as adPosition]
							<option value="${adPosition.id}"[#if adPosition == ad.adPosition] selected="selected"[/#if]>${adPosition.name} [${adPosition.width} × ${adPosition.height}]</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr id="contentTr"[#if ad.type != "text"] class="hidden"[/#if]>
				<th>
					${message("Article.content")}:
				</th>
				<td>
					<textarea id="editor" name="content" class="editor" style="width: 100%;">${ad.content?html}</textarea>
				</td>
			</tr>
			<tr id="pathTr"[#if ad.type == "text"] style="display: none;"[/#if]>
				<th>
					<span class="requiredField">*</span>${message("Ad.path")}:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" id="path" name="path" class="text" value="${ad.path}" maxlength="200"[#if ad.type == "text"] disabled="disabled"[/#if] />
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					</span>
				</td>
			</tr>
			<tr>
				<th>
					${message("Ad.beginDate")}:
				</th>
				<td>
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" value="[#if ad.beginDate??]${ad.beginDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Ad.endDate")}:
				</th>
				<td>
					<input type="text" id="endDate" name="endDate" class="text Wdate" value="[#if ad.endDate??]${ad.endDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Ad.url")}:
				</th>
				<td>
					<input type="text" name="url" class="text" value="${ad.url}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${ad.order}" maxlength="9" />
				</td>
			</tr>
				<tr>
					<th>&nbsp;</th>
					<td>
						&nbsp;<input type="submit" value="${message("admin.common.submit")}" class="btn"/>
						&nbsp;<input type="button" value="${message("admin.common.back")}" class="btn" onclick="location.href='list.jhtml'"/>
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