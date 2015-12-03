<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>集群监控</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<link rel="stylesheet" type="text/css" href="css/uploadify.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript" src="js/loginuser.js"></script>
<script type="text/javascript" src="js/recommend.js"></script>
<script type="text/javascript" src="js/runCluster.js"></script>
<!-- <style>.datagrid-cell{line-height:24px}</style> -->
<style type="text/css">
.datagrid-row {
	height: 32px;
}
</style>
</head>




<body class="easyui-layout">

	<div data-options="region:'north',border:false"
		style="height: 60px; overflow: hidden;">
		<div class="top-bg">
			<%@ include file="north.jsp"%>
		</div>
	</div>

	<div data-options="region:'west',split:true,title:'目录'"
		style="width: 190px; padding: 10px;">
		<ul id="navid" class="easyui-tree"
			data-options="url:'json/tree_data.json',method:'get',animate:true,dnd:true"></ul>
	</div>



	<div region="south" border="true" split="true"
		style="overflow: hidden; height: 40px;">
		<br />
		<%@ include file="sourth.jsp"%>
	</div>

	<div data-options="region:'center',title:'  '">
		<div id="layout_center_tabs" class="easyui-tabs"
			data-options="fit:true">
			<div title="首页" data-options="href:'myabout.html',iconCls:'icon-tip'"></div>
		</div>
	</div>
	<jsp:include page="login/login.jsp"></jsp:include>
</body>
</html>