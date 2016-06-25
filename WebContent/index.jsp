<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html manifest="/cache-manifest">
<head>
<meta http-equiv='X-UA-Compatible' content='IE=edge' />
<title>Hello World</title>
<script id='sap-ui-bootstrap' src='resources/sap-ui-core.js'
	data-sap-ui-theme="sap_bluecrystal"
	data-sap-ui-libs='sap.ui.commons,sap.m'
	type="text/javascript"></script>
<script>
sap.ui.localResources("firstdemo");
//Register your application js
jQuery.sap.registerModulePath('Application', 'Application');
// Load your application js
jQuery.sap.require("Application");
//Create an object of the Application class and set content as root
//the DIV content will be used to put the views in
var app = new Application({ root : "content" });
</script>
</head>

<body class="sapUiBody" role="application">
		<div id="content"></div>
</body>
</html>
