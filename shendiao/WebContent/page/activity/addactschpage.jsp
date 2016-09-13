<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<jsp:include page="/common/ueditor.jsp"></jsp:include>
	<script type="text/javascript">
	

	/* 
	          $(document).ready(function () {

            	$("#ticketType").combobox({

            	onChange: function (n,o) {
            		
            		
            		
            		var data = $("#ticketTypeList").val();
            		
            		var is_free = $("#ticketType").combobox('getValue');
            		
            		  for(var i=0; i<data.length; i++){
            			  
                          var tt = data[i].ticket_type;
                          if(is_free==tt){
                       
                        	  $("#priceTicket").textbox('setValue',data[i].price);
                          }
                      } 
            		
            		
   
            	}

            	});
            	
            	});
 	
	function getaa(){
		var data1 = ${ticketTypeList};
		var data = $("#ticketTypeList").val();
		alert(data)
		
		
		var is_free = $("#ticketType option:selected").val();
		alert(is_free)
		alert(data[0])
		  for(var i=0; i<data.length; i++){
			  
              var tt = data[i].ticket_type;
              alert(tt)
              if(is_free==tt){
            	  $("#priceTicket").textbox('setValue',data[i].price)
              }
          } 
	}
	 */
	
	function addActSch(){
		
			var activityId = $("#activityId").val();
			if(activityId==null || activityId.trim() == ""){
				alert("活动id不能为空！");
				return false;
			}
			//var scheduleDate = $("#scheduleDate").val();
			var scheduleDate = $("input[name='scheduleDate']").val();
			
			if(scheduleDate==null || scheduleDate.trim() == ""){
				alert("活动时间不能为空！");
				return false;
			}
			
			 var dataForm = new FormData();
		 	  dataForm.append('activityId',activityId);
			  dataForm.append('scheduleDate',scheduleDate);
		 
 	
			$.ajax({
	 	        url: '${basePath}/activity/addActSch.do',
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
							 location.href = "${basePath }/activity/actSchedulegoto.do?activityId="+data.actId;
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
<form method="post" id="editForm" action=""  target="ifm">
<input value="${activityId}" type="hidden" id="activityId">
<div style="padding-left: 42% ;font-size:18px;color:red" >添加活动时间</div>
	<table cellpadding="5" align="center">
	
	
		<tr>
		
			<td align="right" width=90 class="formtd">活动时间：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:150px " name="scheduleDate" data-options="required:true"></input>
		
		</tr>
		

		<tr>
			<td colspan="4" align="right">
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="addActSch()">保存</a> 
 			</td>
 			
 			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
			
		</tr>
				
</table>
<!-- <script >
	getaa();
</script> -->
</form>
</body>
</html>
