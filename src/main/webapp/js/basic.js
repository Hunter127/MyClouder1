
// 全局函数
//logout
function logout() {
    $.messager.confirm('确认', '您确定要退出本系统吗？',
    function(r) {
        if (r) {
            $.post('LoginServlet?operation=toLogout',
            function(data) { 
                if (data[0].success) {
                    window.location.href = 'LoginServlet?operation=toLogin';
                }
            }, 'json');
        }
    });
}

//change password
function changePassword() {
    $("body").append("<div id='changePasswordDialog' style='padding:10px;'></div>");
    $("#changePasswordDialog").dialog({
        modal: true,
        href: 'changePassword.jsp',
        title: '修改密码',
        width: 300,
        height: 200,
        onClose: function() {
            $("this").dialog('destroy');
        }
    });
}
function submit() {
	var id = $("input[name='id']").val();
	var userName = $("input[name='userName']").val();
	var oldPassword = $("input[name='oldPassword']").val();
	var password = $("input[name='password']").val();
	var con_password = $("input[name='con_password']").val();
	var data =
	{
		"id" : id,
		"userName" : userName,
		"oldPassword" : oldPassword,
		"password" : password,
		"con_password" : con_password,
	};
    $.ajax(
	  {
		url: "AdminServlet?operation=toChangePassword",
		type : 'POST',
		data : data,
		dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
		success : function(data)
		{
			if (data && data.result) {
                $.messager.alert('提示信息', data.msg, 'info');
                $('#changePasswordDialog').dialog('close'); 
            } else {
                $.messager.alert('提示信息', data.msg, 'error');
            }
		},
	});
}

//change password
function reset() {
	$("input[name='oldPassword']").val('');
    $("input[name='password']").val('');
    $("input[name='con_password']").val('');
}

// 弹出progressBar
// 
function popupProgressbar(titleStr,msgStr,intervalStr){
	var win = $.messager.progress({
        title:titleStr,
        msg:msgStr,
        interval:intervalStr    //设置时间间隔较长 
    });
}
// 关闭progressBar
function closeProgressbar(){
	$.messager.progress('close');
}


// 调用ajax异步提交
// 任务返回成功，则提示成功，否则提示失败的信息
function callByAJax(url,data_){
	$.ajax({
		url : url,
		data: data_,
		async:true,
		dataType:"json",
		context : document.body,
		success : function(data) {
//			$.messager.progress('close');
			closeProgressbar();
			console.info("data.flag:"+data.flag);
			var retMsg;
			if("true"==data.flag){
				retMsg='操作成功！';
			}else{
				retMsg='操作失败！失败原因：'+data.msg;
			}
			$.messager.show({
				title : '提示',
				msg : retMsg
			});
			
			if("true"==data.flag&&"true"==data.monitor){// 添加监控页面
				// 使用单独Tab的方式
				layout_center_addTabFun({
					title : 'MR算法监控',
					closable : true,
					// iconCls : node.iconCls,
					href : 'cluster/monitor_one.jsp'
				});
			}
			
		}
	});
}

function layout_center_addTabFun(opts) {
		var t = $('#layout_center_tabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
		console.info("打开页面："+opts.title);
}

$(function(){
	$('#navid').tree({
		onClick: function(node){
//			alert(node.text+","+node.url);  // alert node text property when clicked
			console.info("click:"+node.text);
			if(node.attributes.folder=='1'){
				return ;
			}
			console.info("open url:"+node.attributes.url)	
			var url;
			if (node.attributes.url) {
				url = node.attributes.url;
			} else {
				url = '404.jsp';
			}
			console.info("open "+url);
			layout_center_addTabFun({
				title : node.text,
				closable : true,
				iconCls : node.iconCls,
				href : url
			});
		}
	});	
	

});
