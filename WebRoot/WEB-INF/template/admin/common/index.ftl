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

<link href="${base}/resources/admin2/custom/common.css" rel="stylesheet" type="text/css" />

<div class="content"  style="left: -162px;width: 95%;height: 90%">
<!--       
        <div class="header">
            
            <h1 class="page-title">Users</h1>
        </div>
-->        
                <ul class="breadcrumb">
            <li><a href="index.jhtml">${message("admin.index.title")}</a> <!-- <span class="divider">/</span></li>
            <li class="active">Users</li> -->
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">

<!--                   
<div class="btn-toolbar">
    <button class="btn btn-primary"><i class="icon-plus"></i> New User</button>
    <button class="btn">Import</button>
    <button class="btn">Export</button>
  <div class="btn-group">
  </div>
</div>
-->

<div class="well">
    <table class="table">
      <tr>
			<th>
				${message("admin.index.systemName")}:
			</th>
			<td>
				${systemName}
			</td>
			<th>
				${message("admin.index.systemVersion")}:
			</th>
			<td>
				${systemVersion}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.official")}:
			</th>
			<td>
				<a target="_blank"></a>
			</td>
			<th>
				${message("admin.index.bbs")}:
			</th>
			<td>
				<a target="_blank"></a>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				&nbsp;
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.javaVersion")}:
			</th>
			<td>
				${javaVersion}
			</td>
			<th>
				${message("admin.index.javaHome")}:
			</th>
			<td>
				${javaHome}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.osName")}:
			</th>
			<td>
				${osName}
			</td>
			<th>
				${message("admin.index.osArch")}:
			</th>
			<td>
				${osArch}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.serverInfo")}:
			</th>
			<td>
				${serverInfo}
			</td>
			<th>
				${message("admin.index.servletVersion")}:
			</th>
			<td>
				${servletVersion}
			</td>
		</tr>
    </table>
</div>


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


 </body>
</html>