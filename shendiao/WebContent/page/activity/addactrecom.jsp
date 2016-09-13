<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<jsp:include page="/common/ueditor.jsp"></jsp:include>
	<script type="text/javascript">
	
 	
	
	
	
	function addActRecom(){
 		
		var programa_id = $("#activityId").val();
		var begin_date = $("input[name='begin_date']").val();
		if(begin_date==null || begin_date.trim() == ""){
			alert("推荐开始不能为空！");
			return false;
		}
		var end_date = $("input[name='end_date']").val();
		if(end_date==null || end_date.trim() == ""){
			alert("推荐结束不能为空！");
			return false;
		}
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    
 	    dataForm.append('programa_id',programa_id);
	    dataForm.append('begin_date',begin_date);
 	   	dataForm.append('end_date',end_date);
		$.ajax({
	 	        url: '${basePath }/activity/addActRecom.do',
	 	        type: 'POST',
	 	        data: dataForm,
	 	        cache: false,
	 	      	timeout: 60000,
	 	      	async:true,
	 	      	dataType:'json',
	 	        contentType: false,
	 	        //不可缺
	 	        processData: false,
	 	        //不可缺	  
	 	       success:function(data){
					if (data.code == '0') {
						
						//$.messager.alert('消息', data.msg);
						 $.messager.alert('消息', data.msg,'操作成功',function () {  
							 location.href = "${basePath }/activity/index.do";
						 });
						
					} else {
						$.messager.alert('消息', data.msg);
					}
				}
			}) 
 	}
	
 	
 	
 	
 	
 	

 	
	</script>
	<style type="text/css">
		.formtd {
			font-size: 12px;
		}
	</style>
  </head>
  
<body>
<form method="post" id="editForm" >
<input value="${activityId }" type="hidden" id ="activityId" >
<div style="padding-left: 42% ;font-size:18px;color:red" >添加活动推荐</div>
	<table cellpadding="5" align="center">
	
		
		<tr>
		
			<td align="right" width=90 class="formtd">推荐开始日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="begin_date" data-options="required:true"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">推荐截止日期：</td>
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="end_date" data-options="required:true"></input>
		
		</tr>
		
		
		<tr>
		
		<tr>
			<td colspan="4" align="right" >
			   <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="addActRecom()">保存</a>
			</td>
			
			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
			
		</tr>
				
</table>

</form>
</body>
</html>
