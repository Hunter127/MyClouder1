<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>






	<div id="queue"></div>
	<!-- 上传文件存放的地方，ID为queue -->
	<input id="imageify" name="filename" type="file" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#imageify").uploadify({
				'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
				method : 'get', //以get方式上传

				'buttonText' : '上传', //上传按钮的文字
				'fileTypeDesc' : 'All Files', //可上传文件格式的描述
				'fileTypeExts' : '*.gif; *.jpg; *.png;*.*', //可上传的文件格式 
				'auto' : true, //选择一个文件是否自动上传
				'multi' : true, //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
				swf : 'uploadify/uploadify.swf', //指定swf文件的，默认是uploadify.swf
				uploader : 'dB_upload.action;jsessionid=${pageContext.session.id}', //服务器响应地址
				'formData' : {
					'name' : 'lmy',
				    

				}, //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
				'onUploadStart' : function(file) { //上传前触发的事件

					//在这里添加  $('#imageify').uploadify('cancel'); 可以取消上传
					//$("#imageify").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
				},
				'onUploadSuccess' : function(file, data, response) { //上传成功后触发的事件
					alert("上传成功");
				}
			});
		});
	</script>

</html>