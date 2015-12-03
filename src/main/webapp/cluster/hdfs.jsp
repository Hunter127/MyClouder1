<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<div>
		<div style="padding: 5px;">

			<a id="a9" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',toggle:true" onclick="append();">添加</a>
			<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',toggle:true">刷新</a> <a href="#"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',size:'small' ">删除</a>
				<a href="#"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-save',size:'small' " onclick="getRow();">下载</a>
				

		</div>

		<!-- 上传文件使用模态框，显然不合理 -->
		<div id="w" class="easyui-window" title="上传窗口"
			data-options="closed:true,iconCls:'icon-save'"
			style="width: 500px; height: 200px; padding: 10px;">
			<%@ include file="../Test1.jsp"%>
		</div>

		<table id="dg" title="文件">

			<!-- <thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="filename" width="680">文件名</th>
					<th field="size" width="200">大小</th>
					<th field="creationDate" width="200" align="right">日期</th>
				</tr>
			</thead> -->
		</table>

		<script>
		function getRow(){
			var row = $('#dg').datagrid('getSelected');
			// window.location.href= "路径"; 
			window.location = 'downloads.action?url='+row.url+'&filename='+row.filename;
			 /* if (row){
				$.messager.alert('系统提示', row.url+":"+row.filename+":"+row.size+":"+row.creationDate);
			}  */
		}
		
		    //弹出上传窗
			function append() {
				$('#w').window('open')
			}

			$('#dg').datagrid({
				title : '文件',
				iconCls : 'icon-edit',//图标 
				width : 700,
				height : 'auto',
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,//是否可折叠的 
				fit : true,//自动大小 
				url : 'dB_FingFile.action',
				//sortName: 'code', 
				//sortOrder: 'desc', 
				remoteSort : false,
				idField : 'fldId',
				singleSelect : true, //是否单选 
				pagination : true,//分页控件 
				rownumbers : true,//行号 
				columns: [[
                           { field: 'url', title: 'url', width: 50, align: 'left', hidden:"true"},
				           { field: 'filename', title: '文件名', width: 680, align: 'left' },
				           { field: 'size', title: '大小(单位：M)', width: 200, align: 'left' },
				           { field: 'creationDate', title: '日期', width: 200, align: 'left',
				        	   formatter:function(value,row,index){
				                   var unixTimestamp = new Date(value);
				                   return unixTimestamp.toLocaleString();}   
				           }
				         
				       ]],
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],

			});
			//设置分页控件 
			var p = $('#dg').datagrid('getPager');
			$(p).pagination({
				pageSize : 10,//每页显示的记录条数，默认为10 
				pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表 
				beforePageText : '第',//页数文本框前显示的汉字 
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			/*onBeforeRefresh:function(){
			    $(this).pagination('loading');
			    alert('before refresh');
			    $(this).pagination('loaded');
			}*/
			});
		</script>
	</div>


