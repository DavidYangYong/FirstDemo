<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta charset="UTF-8">
		
		<title>Table Select Dialog</title>
		
		<script id="sap-ui-bootstrap"
			src="${pageContext.request.contextPath}/resources/sap-ui-core.js"
			data-sap-ui-libs="sap.m"
			data-sap-ui-theme="sap_bluecrystal"
			data-sap-ui-xx-bindingSyntax="complex"
			data-sap-ui-preload="async"
			data-sap-ui-compatVersion="edge" 
			data-sap-ui-resourceroots='{"sap.m.sample.TableSelectDialog": "././", "sap.ui.demo.mock": "mockdata"}'>
		</script>
		
		<!-- Application launch configuration -->
		<script>
		
			sap.ui.getCore().attachInit(function() {
				new sap.m.App ({
				    pages: [
				        new sap.m.Page({
				            title: "Table Select Dialog", 
				            enableScrolling : true,
    					    content: [ new sap.ui.core.ComponentContainer({
    						    name : "sap.m.sample.TableSelectDialog"
    					    })]
    					})
    				]
				}).placeAt("content");
			});

		</script>
	</head>
	
	<!-- UI Content -->
	<body class="sapUiBody" id="content" role="application">
	</body>
	
</html>
