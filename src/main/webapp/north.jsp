<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<TABLE id=header cellSpacing=0 cellPadding=0 width="100%" align=center
	border=0>
	<TBODY>
	
		<div class="easyui-panel" style="padding: 10px; " >
		<div style="float:left">
		  <h1>集群监控</h1>
		</div>
		<div style="float:right">
		 <a id="reportsMenuButton" class="easyui-menubutton"
				data-options="menu:'#reportsMenu',iconCls:'icon-search'">消息</a> 
			<a id="dashboard" class="easyui-menubutton"
				data-options="menu:'#controlMenu', iconCls:'icon-ok'">${ uname}</a>
		</div>
			
		</div>
		
		<div id="controlMenu" style="width: 60px;">
			<div data-options="iconCls:'icon-edit'" onclick="changePassword();">
				修改密码
			</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-undo'" onclick="logout();">
				退出登录
			</div>
		</div>
		
		<div id="reportsMenu" style="width: 60px;">
			<div data-options="iconCls:'icon-search'" onclick="countDeptStaff();">
				
			</div>
			<div data-options="iconCls:'icon-search'" onclick="countStaffGender();">
				
			</div>
		</div>
		
	</TBODY>
</TABLE>




