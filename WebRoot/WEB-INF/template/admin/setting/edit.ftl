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
<script type="text/javascript" src="${base}/resources/admin2/custom/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/custom/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/webupload/webuploader.js"></script>
<script type="text/javascript" src="${base}/resources/admin2/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $browserButton = $("input.browserButton");
	var $smtpFromMail = $("#smtpFromMail");
	var $smtpHost = $("#smtpHost");
	var $smtpPort = $("#smtpPort");
	var $smtpUsername = $("#smtpUsername");
	var $smtpPassword = $("#smtpPassword");
	var $toMailWrap = $("#toMailWrap");
	var $toMail = $("#toMail");
	var $mailTest = $("#mailTest");
	var $mailTestStatus = $("#mailTestStatus");
	
	[@flash_message /]
	$browserButton.browser();
	// 邮件测试
	$mailTest.click(function() {
		var $this = $(this);
		if ($this.val() == "${message("admin.setting.mailTest")}") {
			$this.val("${message("admin.setting.sendMail")}");
			$toMailWrap.show();
		} else {
			function valid(element) {
				return $inputForm.validate().element(element);
			}
			$.ajax({
				url: "mail_test.jhtml",
				type: "POST",
				data: {smtpFromMail: $smtpFromMail.val(), smtpHost: $smtpHost.val(), smtpPort: $smtpPort.val(), smtpUsername: $smtpUsername.val(), smtpPassword: $smtpPassword.val(), toMail: $toMail.val()},
				dataType: "json",
				cache: false,
				beforeSend: function() {
					if (valid($smtpFromMail) & valid($smtpHost) & valid($smtpPort) & valid($smtpUsername) & valid($toMail)) {
						$mailTestStatus.html('<span class="loadingIcon">&nbsp;<\/span>${message("admin.setting.sendMailLoading")}');
						$this.prop("disabled", true);
					} else {
						return false;
					}
				},
				success: function(message) {
					$mailTestStatus.empty();
					$this.prop("disabled", false);
					$.message(message);
				}
			});
		}
	});
	
	$.validator.addMethod("compareLength", 
		function(value, element, param) {
			return this.optional(element) || $.trim(value) == "" || $.trim($(param).val()) == "" || parseFloat(value) >= parseFloat($(param).val());
		},
		"${message("admin.setting.compareLength")}"
	);
	
	// 表单验证
	$inputForm.validate({
		rules: {
			siteName: "required",
			siteUrl: "required",
			logo: "required",
			email: "email",
			siteCloseMessage: "required",
			watermarkAlpha: {
				required: true,
				digits: true,
				max: 100
			},
			watermarkImageFile: {
				extension: "${setting.uploadImageExtension}"
			},
			usernameMinLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117
			},
			usernameMaxLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117,
				compareLength: "#usernameMinLength"
			},
			passwordMinLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117
			},
			passwordMaxLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117,
				compareLength: "#passwordMinLength"
			},
			registerAgreement: "required",
			accountLockCount: {
				required: true,
				integer: true,
				min: 1
			},
			accountLockTime: {
				required: true,
				digits: true
			},
			safeKeyExpiryTime: {
				required: true,
				digits: true
			},
			uploadMaxSize: {
				required: true,
				digits: true
			},
			imageUploadPath: "required",
			flashUploadPath: "required",
			mediaUploadPath: "required",
			fileUploadPath: "required",
			smtpFromMail: {
				required: true,
				email: true
			},
			smtpHost: "required",
			smtpPort: {
				required: true,
				digits: true
			},
			smtpUsername: "required",
			toMail: {
				required: true,
				email: true
			},
			stockAlertCount: {
				required: true,
				digits: true
			},
			cookiePath: "required"
		}
	});
		   
});	
</script>


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
        
        .table th{
    	    text-align: right;
		    background-color: #f5f5f5;
		    width: 200px;
        }
    </style>

    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <body class=""> 
    
    
    <div class="content" style="left:-162px;width: 95%">
        
                <ul class="breadcrumb">
            <li><a href="${base}/admin/common/index.jhtml">首页</a> <span class="divider">/</span></li>
            <li class="active">${message("admin.setting.edit")}</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="well">
	<ul class="nav nav-tabs">
      <li class="active"><a href="#tab1" data-toggle="tab">基本设置</a></li>
      <li><a href="#tab2" data-toggle="tab">显示设置</a></li>	
      <li><a href="#tab3" data-toggle="tab" id="liA3">注册与安全</a></li>	
      <li><a href="#tab5" data-toggle="tab">其它设置</a></li>	
    </ul>

<div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="tab1">
    <form id="inputForm" action="update.jhtml" class="form-inline" method="post" enctype="multipart/form-data">
        <table class="layui-table" id="">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.siteName")}:
				</th>
				<td>
					<input type="text" name="siteName" class="text" value="${setting.siteName}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.siteUrl")}:
				</th>
				<td>
					<input type="text" name="siteUrl" class="text" value="${setting.siteUrl}" maxlength="200" />
				</td>
			</tr>
			<!--<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.logo")}:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="logo" class="text" value="${setting.logo}" maxlength="200" />
						<input type="button" class="button browserButton" value="${message("admin.browser.select")}" />
						<a href="${setting.logo}" target="_blank">${message("admin.common.view")}</a>
					</span>
				</td>
			</tr>-->
			<!--<tr>
				<th>
					${message("Setting.hotSearch")}:
				</th>
				<td>
					<input type="text" name="hotSearch" class="text" value="${setting.hotSearch}" maxlength="200" title="${message("admin.setting.hotSearchTitle")}" />
				</td>
			</tr>-->
			<tr>
				<th>
					${message("Setting.address")}:
				</th>
				<td>
					<input type="text" name="address" class="text" value="${setting.address}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					客服电话:
				</th>
				<td>
					<input type="text" name="phone" class="text" value="${setting.phone}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					结算比例:
				</th>
				<td>
					<input type="text" name="feeProportion" class="text" value="${setting.feeProportion}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					听单范围:
				</th>
				<td>
					<input type="text" name="receiveScope" class="text" value="${setting.receiveScope}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					司机取消次数:
				</th>
				<td>
					<input type="text" name="driverCancellNum" class="text" value="${setting.driverCancellNum}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					乘客取消次数:
				</th>
				<td>
					<input type="text" name="memberCancellNum" class="text" value="${setting.memberCancellNum}" maxlength="200" />
				</td>
			</tr>
			
			<!--<tr>
				<th>
					${message("Setting.zipCode")}:
				</th>
				<td>
					<input type="text" name="zipCode" class="text" value="${setting.zipCode}" maxlength="200" />
				</td>
			</tr>-->
			<!--<tr>
				<th>
					${message("Setting.email")}:
				</th>
				<td>
					<input type="text" name="email" class="text" value="${setting.email}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.certtext")}:
				</th>
				<td>
					<input type="text" name="certtext" class="text" value="${setting.certtext}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isSiteEnabled")}:
				</th>
				<td>
					<input type="checkbox" name="isSiteEnabled" value="true"[#if setting.isSiteEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isSiteEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.siteCloseMessage")}:
				</th>
				<td>
					<textarea name="siteCloseMessage" class="text">${setting.siteCloseMessage?html}</textarea>
				</td>
			</tr>-->
		</table>

      </div>
      
      <div class="tab-pane fade" id="tab2">
      <table class="layui-table" id="">
    <tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.uploadMaxSize")}:
				</th>
				<td>
					<input type="text" name="uploadMaxSize" class="text" value="${setting.uploadMaxSize}" maxlength="9" title="${message("admin.setting.uploadMaxSizeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.uploadImageExtension")}:
				</th>
				<td>
					<input type="text" name="uploadImageExtension" class="text" value="${setting.uploadImageExtension}" maxlength="200" title="${message("admin.setting.uploadImageExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.uploadFlashExtension")}:
				</th>
				<td>
					<input type="text" name="uploadFlashExtension" class="text" value="${setting.uploadFlashExtension}" maxlength="200" title="${message("admin.setting.uploadFlashExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.uploadMediaExtension")}:
				</th>
				<td>
					<input type="text" name="uploadMediaExtension" class="text" value="${setting.uploadMediaExtension}" maxlength="200" title="${message("admin.setting.uploadMediaExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.uploadFileExtension")}:
				</th>
				<td>
					<input type="text" name="uploadFileExtension" class="text" value="${setting.uploadFileExtension}" maxlength="200" title="${message("admin.setting.uploadFileExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.imageUploadPath")}:
				</th>
				<td>
					<input type="text" name="imageUploadPath" class="text" value="${setting.imageUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.flashUploadPath")}:
				</th>
				<td>
					<input type="text" name="flashUploadPath" class="text" value="${setting.flashUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.mediaUploadPath")}:
				</th>
				<td>
					<input type="text" name="mediaUploadPath" class="text" value="${setting.mediaUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.fileUploadPath")}:
				</th>
				<td>
					<input type="text" name="fileUploadPath" class="text" value="${setting.fileUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.watermarkAlpha")}:
				</th>
				<td>
					<input type="text" name="watermarkAlpha" class="text" value="${setting.watermarkAlpha}" maxlength="9" title="${message("admin.setting.watermarkAlphaTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.watermarkImage")}:
				</th>
				<td>
					<span class="fieldSet">
						<input type="file" name="watermarkImageFile" />
						<a href="${base}${setting.watermarkImage}" target="_blank">${message("admin.common.view")}</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.watermarkPosition")}:
				</th>
				<td>
					<select name="watermarkPosition">
						[#list watermarkPositions as watermarkPosition]
							<option value="${watermarkPosition}"[#if watermarkPosition == setting.watermarkPosition] selected="selected"[/#if]>${message("Setting.WatermarkPosition." + watermarkPosition)}</option>
						[/#list]
					</select>
				</td>
			</tr>
		</table>
      </div>
      
      <div class="tab-pane fade" id="tab3">
    <table class="layui-table">
<tr>
				<th>
					${message("Setting.isRegisterEnabled")}:
				</th>
				<td>
					<input type="checkbox" name="isRegisterEnabled" value="true"[#if setting.isRegisterEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isRegisterEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isDuplicateEmail")}:
				</th>
				<td>
					<input type="checkbox" name="isDuplicateEmail" value="true"[#if setting.isDuplicateEmail] checked="checked"[/#if] />
					<input type="hidden" name="_isDuplicateEmail" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.disabledUsername")}:
				</th>
				<td>
					<input type="text" name="disabledUsername" class="text" value="${setting.disabledUsername}" maxlength="200" title="${message("admin.setting.disabledUsernameTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.usernameMinLength")}:
				</th>
				<td>
					<input type="text" id="usernameMinLength" name="usernameMinLength" class="text" value="${setting.usernameMinLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.usernameMaxLength")}:
				</th>
				<td>
					<input type="text" name="usernameMaxLength" class="text" value="${setting.usernameMaxLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.passwordMinLength")}:
				</th>
				<td>
					<input type="text" id="passwordMinLength" name="passwordMinLength" class="text" value="${setting.passwordMinLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.passwordMaxLength")}:
				</th>
				<td>
					<input type="text" name="passwordMaxLength" class="text" value="${setting.passwordMaxLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.registerPoint")}:
				</th>
				<td>
					<input type="text" name="registerPoint" class="text" value="${setting.registerPoint}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.registerAgreement")}:
				</th>
				<td>
					<textarea name="registerAgreement" class="text">${setting.registerAgreement?html}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isEmailLogin")}:
				</th>
				<td>
					<input type="checkbox" name="isEmailLogin" value="true"[#if setting.isEmailLogin] checked="checked"[/#if] />
					<input type="hidden" name="_isEmailLogin" value="false" />
				</td>
			</tr>
			
			<tr>
				<th>
					${message("Setting.accountLockTypes")}:
				</th>
				<td>
					[#list accountLockTypes as accountLockType]
						<label>
							<input type="checkbox" name="accountLockTypes" value="${accountLockType}"[#if setting.accountLockTypes?? && setting.accountLockTypes?seq_contains(accountLockType)] checked="checked"[/#if] />${message("Setting.AccountLockType." + accountLockType)}
						</label>
					[/#list]
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.accountLockCount")}:
				</th>
				<td>
					<input type="text" name="accountLockCount" class="text" value="${setting.accountLockCount}" maxlength="9" title="${message("admin.setting.accountLockCountTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.accountLockTime")}:
				</th>
				<td>
					<input type="text" name="accountLockTime" class="text" value="${setting.accountLockTime}" maxlength="9" title="${message("admin.setting.accountLockTimeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.safeKeyExpiryTime")}:
				</th>
				<td>
					<input type="text" name="safeKeyExpiryTime" class="text" value="${setting.safeKeyExpiryTime}" maxlength="9" title="${message("admin.setting.safeKeyExpiryTimeTitle")}" />
				</td>
			</tr>
</table>
      </div>
      
      <div class="tab-pane fade" id="tab4">
    <table class="layui-table">
		<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.smtpFromMail")}:
				</th>
				<td>
					<input type="text" id="smtpFromMail" name="smtpFromMail" class="text" value="${setting.smtpFromMail}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.smtpHost")}:
				</th>
				<td>
					<input type="text" id="smtpHost" name="smtpHost" class="text" value="${setting.smtpHost}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.smtpPort")}:
				</th>
				<td>
					<input type="text" id="smtpPort" name="smtpPort" class="text" value="${setting.smtpPort}" maxlength="9" title="${message("admin.setting.smtpPorteTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.smtpUsername")}:
				</th>
				<td>
					<input type="text" id="smtpUsername" name="smtpUsername" class="text" value="${setting.smtpUsername}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.smtpPassword")}:
				</th>
				<td>
					<input type="password" id="smtpPassword" name="smtpPassword" class="text" maxlength="200" title="${message("admin.setting.smtpPasswordTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.setting.mailTest")}:
				</th>
				<td>
					<span class="fieldSet">
						<span id="toMailWrap" class="hidden">
							${message("admin.setting.toMail")}: <br />
							<input type="text" id="toMail" name="toMail" class="text ignore" maxlength="200" />
						</span>
						<input type="button" id="mailTest" class="button" value="${message("admin.setting.mailTest")}" />
						<span id="mailTestStatus">&nbsp;</span>
					</span>
				</td>
			</tr>
</table>
      </div>
      
      <div class="tab-pane fade" id="tab5">
    <table class=" layui-table">
			<tr>
				<th>
					${message("Setting.isDevelopmentEnabled")}:
				</th>
				<td>
					<label title="${message("admin.setting.isDevelopmentEnabledTitle")}">
						<input type="checkbox" name="isDevelopmentEnabled" value="true"[#if setting.isDevelopmentEnabled] checked="checked"[/#if] />
						<input type="hidden" name="_isDevelopmentEnabled" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.cookiePath")}: 
				</th>
				<td>
					<input type="text" name="cookiePath" class="text" value="${setting.cookiePath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.cookieDomain")}: 
				</th>
				<td>
					<input type="text" name="cookieDomain" class="text" value="${setting.cookieDomain}" maxlength="200" />
				</td>
			</tr>
</table>
      </div>
     
      <table class="table">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;<input type="submit" value="${message("admin.common.submit")}" class="layui-btn layui-btn-sm"/>
					&nbsp;<input type="button" value="${message("admin.common.back")}" class="layui-btn layui-btn-sm" onclick="location.href='../common/index.jhtml'"/>
				</td>
			</tr>
		</table>
  </div>

</div>

                    
		</div>	<!--class="row-fluid"-->
	</div> <!--class="container-fluid"-->
</div><!--class="content"-->
    
    
  </body>
</html>