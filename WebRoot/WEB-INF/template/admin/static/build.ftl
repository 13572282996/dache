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

<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $buildType = $("#buildType");
	var $articleCategoryTr = $("#articleCategoryTr");
	var $articleCategoryId = $("#articleCategoryId");
	var $beginDateTr = $("#beginDateTr");
	var $beginDate = $("#beginDate");
	var $endDateTr = $("#endDateTr");
	var $endDate = $("#endDate");
	var $count = $("#count");
	var $statusTr = $("#statusTr");
	var $status = $("#status");
	var $submit = $(":submit");
	
	var first;
	var buildCount;
	var buildTime;
	var buildType;
	var articleCategoryId;
	var beginDate;
	var endDate;
	var count;

	$buildType.change(function() {
		var $this = $(this);
		if ($this.val() == "article") {
			$articleCategoryTr.show();
			$beginDateTr.show();
			$endDateTr.show();
		} else {
			$articleCategoryTr.hide();
			$beginDateTr.hide();
			$endDateTr.hide();
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			count: {
				required: true,
				integer: true,
				min: 1
			}
		},
		submitHandler: function(form) {
			first = 0;
			buildCount = 0;
			buildTime = 0;
			buildType = $buildType.val();
			articleCategoryId = $articleCategoryId.val();
			beginDate = $beginDate.val();
			endDate = $endDate.val();
			count = parseInt($count.val());
			$buildType.prop("disabled", true);
			$articleCategoryId.prop("disabled", true);
			$beginDate.prop("disabled", true);
			$endDate.prop("disabled", true);
			$count.prop("disabled", true);
			$submit.prop("disabled", true);
			$statusTr.show();
			build();
		}
	});
	
	function build() {
		$.ajax({
			url: "build.jhtml",
			type: "POST",
			data: {buildType: buildType, articleCategoryId: articleCategoryId,  beginDate: beginDate, endDate: endDate, first: first, count: count},
			dataType: "json",
			cache: false,
			success: function(data) {
				buildCount += data.buildCount;
				buildTime += data.buildTime;
				if (!data.isCompleted) {
					if (buildType == "article") {
						first = data.first;
						$status.text("${message("admin.static.building")} [" + first + " - " + (first + count) + "]");
					} else {
						$status.text("${message("admin.static.building")}");
					}
					build();
				} else {
					$buildType.prop("disabled", false);
					$articleCategoryId.prop("disabled", false);
					$beginDate.prop("disabled", false);
					$endDate.prop("disabled", false);
					$count.prop("disabled", false);
					$submit.prop("disabled", false);
					$statusTr.hide();
					$status.empty();
					var time;
					if (buildTime < 60000) {
						time = (buildTime / 1000).toFixed(2) + "${message("admin.static.second")}";
					} else {
						time = (buildTime / 60000).toFixed(2) + "${message("admin.static.minute")}";
					}
					$.message("success", "${message("admin.static.success")} [${message("admin.static.buildCount")}: " + buildCount + " ${message("admin.static.buildTime")}: " + time + "]");
				}
			}
		});
	}

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
            <li class="active">${message("admin.static.build")}</li> 
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            
<form id="inputForm" action="build.jhtml" method="POST">
                    
<div class="btn-toolbar" style="margin-top: 1px;">
    
	
</div>

<div class="well" style="margin-bottom: 0px;padding-bottom: 0px;">
    <table class="table" id="addEditTable">
   <tr>
				<th>
					${message("admin.static.buildType")}:
				</th>
				<td>
					<select id="buildType" name="buildType" class="ui_select01">
						[#list buildTypes as buildType]
							<option value="${buildType}">${message("admin.static." + buildType)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr id="articleCategoryTr" style="display:none">
				<th>
					${message("Article.articleCategory")}:
				</th>
				<td>
					<select id="articleCategoryId" name="articleCategoryId" class="ui_select01">
						<option value="">${message("admin.static.emptyOption")}</option>
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
			</tr>
			
			
			<tr id="beginDateTr" style="display:none">
				<th>
					${message("admin.static.beginDate")}:
				</th>
				<td>
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" value="${defaultBeginDate?string("yyyy-MM-dd")}" title="${message("admin.static.beginDateTitle")}" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
				</td>
			</tr>
			<tr id="endDateTr" style="display:none">
				<th>
					${message("admin.static.endDate")}:
				</th>
				<td>
					<input type="text" id="endDate" name="endDate" class="text Wdate" value="${defaultEndDate?string("yyyy-MM-dd")}" title="${message("admin.static.endDateTitle")}" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.static.count")}:
				</th>
				<td>
					<input type="text" id="count" name="count" class="text" value="50" maxlength="9" />
				</td>
			</tr>
			<tr id="statusTr" style="display:none">
				<th>
					&nbsp;
				</th>
				<td>
					<span class="loadingBar">&nbsp;</span>
					<div id="status"></div>
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