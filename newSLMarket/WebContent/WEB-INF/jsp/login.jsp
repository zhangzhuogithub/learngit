<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!--
		Charisma v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="utf-8">
	<title>Free HTML5 Bootstrap Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">

	<!-- The styles -->
	<link id="bs-css" href="<%=path%>/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="<%=path%>/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="<%=path%>/css/charisma-app.css" rel="stylesheet">
	<link href="<%=path%>/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='<%=path%>/css/fullcalendar.css' rel='stylesheet'>
	<link href='<%=path%>/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='<%=path%>/css/chosen.css' rel='stylesheet'>
	<link href='<%=path%>/css/uniform.default.css' rel='stylesheet'>
	<link href='<%=path%>/css/colorbox.css' rel='stylesheet'>
	<link href='<%=path%>/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='<%=path%>/css/jquery.noty.css' rel='stylesheet'>
	<link href='<%=path%>/css/noty_theme_default.css' rel='stylesheet'>
	<link href='<%=path%>/css/elfinder.min.css' rel='stylesheet'>
	<link href='<%=path%>/css/elfinder.theme.css' rel='stylesheet'>
	<link href='<%=path%>/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='<%=path%>/css/opa-icons.css' rel='stylesheet'>
	<link href='<%=path%>/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="img/favicon.ico">
		
</head>

<body>
		<div class="container-fluid">
		<div class="row-fluid">
		
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>SL会员商城</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info">
						Please login with your Username and Password.
					</div>
					
					<div class="form-horizontal">
						<fieldset>
							<div class="input-prepend" title="登陆账号" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span>
								<input autofocus class="input-large span10" name="logincode" id="logincode" type="text" value="admin" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="密码" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span>
								<input class="input-large span10" name="password" id="password" type="password" value="admin" />
							</div>
							<div class="clearfix"></div>

							<!--<div class="input-prepend">
							<label class="remember" for="remember"><input type="checkbox" id="remember" />Remember me</label>
							</div>  -->
							<div class="clearfix"></div>
							
							<ul id="formtip"></ul>
							
							<p class="center span5">
							<button type="submit" class="btn btn-primary" id="loginbtn">登录</button>
							</p>
						</fieldset>
					</div>
					
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="<%=path%>/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="<%=path%>/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="<%=path%>/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="<%=path%>/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="<%=path%>/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="<%=path%>/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="<%=path%>/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="<%=path%>/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="<%=path%>/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="<%=path%>/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="<%=path%>/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="<%=path%>/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="<%=path%>/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="<%=path%>/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="<%=path%>/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="<%=path%>/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='<%=path%>/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path%>/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="<%=path%>/js/excanvas.js"></script>
	<script src="<%=path%>/js/jquery.flot.min.js"></script>
	<script src="<%=path%>/js/jquery.flot.pie.min.js"></script>
	<script src="<%=path%>/js/jquery.flot.stack.js"></script>
	<script src="<%=path%>/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="<%=path%>/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="<%=path%>/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="<%=path%>/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="<%=path%>/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path%>/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="<%=path%>/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=path%>/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=path%>/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=path%>/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=path%>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path%>/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path%>/js/charisma.js"></script>
	<script  src="<%=path%>/js/localjs/local.js"></script>
		
</body>
</html>
