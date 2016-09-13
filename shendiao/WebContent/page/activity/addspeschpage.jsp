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
	
	function addSpeSch(){
		
		
		var spe_price = $("input[name='spe_price']").val();
		
		if(spe_price==null || spe_price.trim() == "" ||isNaN(spe_price)){
			alert("特价票价格不能为空！");
			return false;
		}
	
		var act_ticket_id = $('#ticketType').combobox('getValue');
		
		if(act_ticket_id==null || act_ticket_id.trim() == "" ||isNaN(act_ticket_id)){
			alert("活动票类型不能为空！");
			return false;
		}
		
		var act_schedule_id = $("#scheduleId").val();
		var act_schedule_id = $("#scheduleId").val();
		var act_id = $("#activityId").val();
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	
 	    
 	    dataForm.append('price',spe_price);
	    dataForm.append('act_ticket_id',act_ticket_id);
 	    dataForm.append('act_schedule_id',act_schedule_id);
 	    dataForm.append('act_id',act_id);
 	
		$.ajax({
	 	        url: '${basePath }/activity/addSpeSch.do',
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
							 location.href = "${basePath }/activity/actSpeSchgoto.do?activityId="+data.actId;
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
<input value="${scheduleDate}" type="hidden" id="scheduleDate">
<input value="${scheduleId}" type="hidden" id="scheduleId">
<div style="padding-left: 42% ;font-size:18px;color:red" >添加特殊票</div>
	<table cellpadding="5" align="center">
	
	<!-- 	<tr>
		
			<td align="right" width=90 class="formtd">活动标题：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_name"  data-options="required:true"></td>
		
		</tr> -->
		
		 <tr>
			<td align="right" width=90 class="formtd">票种类型：</td>
			<td>
				<select id="ticketType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="type" data-options="required:true">
					
					<c:forEach items="${ticketTypeList }" var="n">
					<option value="${n.id}">${n.ticket_name}
					</option>
				</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
		
			<td align="right" width=90 class="formtd">活动时间：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:150px " name="scheduleDate" value="${scheduleDate}" readonly="readonly"></input>
		
		</tr>
		
		<!-- <tr>
		
			<td align="right" width=90 class="formtd">原始价格：</td>
			<td colspan="3"><input id ="priceTicket" class="easyui-textbox" type="text" style="width:300px" name="old_price"  data-options="required:true"></td>
		
		</tr> -->
		
		<tr>
		
			<td align="right" width=90 class="formtd">特殊价格：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:150px" name="spe_price"  data-options="required:true"></td>
		
		</tr>
		
	
		<tr>
			<td colspan="4" align="right">
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="addSpeSch()">保存</a> 
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
