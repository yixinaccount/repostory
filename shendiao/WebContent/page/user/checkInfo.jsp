<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<jsp:include page="/common/ueditor.jsp"></jsp:include>
	<script type="text/javascript">
	
	
		function checkPass(){
				
				
			//alert("fasfs");
			// location.href = "${basePath }/user/checkIndex.do";
			
			var userId = $("#userId").val();
			
			$.ajax({
			        url: '${basePath }/user/checkPass.do?userId='+userId,
			        type: 'POST',
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
							 location.href = "${basePath }/user/checkIndex.do";
						 });
						
					} else {
						$.messager.alert('消息', data.msg);
					}
				}
			}) 
			
		
		 	}
				
			
			
		function checkNoPass() {
			$('#mydialog').show();
			$('#mydialog').dialog({
			collapsible: true,
			minimizable: true,
			maximizable: true,
			/* toolbar: [{
			text: '添加',
			iconCls: 'icon-add',
			handler: function() {
			alert('添加数据')
			}
			},{
			text: '保存',
			iconCls: 'icon-save',
			handler: function() {
			alert('保存数据')
			}
			}], */
			buttons: [{
			text: '确定',
			iconCls: 'icon-ok',
			handler: function() {
				
			var xiaoxi = $("textarea[name='xiaoxi']").val();  
				
				/* alert(xiaoxi);
				
			alert('提交数据'); */
			
			
			var userId = $("#userId").val();
			
			$.ajax({
			        url: '${basePath }/user/checkNoPass.do?userId='+userId+'&checkContent='+encodeURI(encodeURI(xiaoxi)),
			        type: 'POST',
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
							 location.href = "${basePath }/user/checkIndex.do";
						 });
						
					} else {
						$.messager.alert('消息', data.msg);
						
						$('#mydialog').dialog('close');
					}
				}
			}) 
				
			}
			}
			
			/* , {
			text: '取消',
			handler: function() {
			 $('#mydialog').dialog('取消'); 
			 $('#mydialog').close();
			}
			} */
			
			,
			{text:'取消',iconCls:'icon-cancel',handler:function(){$('#mydialog').dialog('close');}}
			]
			});
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

<%-- 	<div style="padding-left: 42% ;font-size:18px;color:red">查看${actInfoMap.act_name }</div> --%>
<input value="${checkDeatil.user_id }" type="hidden" id="userId">

	<table cellpadding="5" align="center">
		
		<tr>
		
			<td align="right" width=90 class="formtd">用户真实姓名：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_name" value="${ checkDeatil.realname}" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">用户身份证号：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_name" value="${ checkDeatil.IDCard}" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">身份证正面照：</td>
			<td><img  alt="" src="${basePath }${checkDeatil.IDCard_front_pic}" width="200px" height="100px"></td>
		</tr>
		
		
		<tr>
			<td align="right" width=100 class="formtd">身份证反面照：</td>
			<td><img  alt="" src="${basePath }${checkDeatil.IDCard_back_pic}" width="200px" height="100px"></td>
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">手持身份证照片：</td>
			<td><img  alt="" src="${basePath }${checkDeatil.IDHead_pic}" width="200px" height="100px"></td>
		</tr>
		
		
		
		<tr>
			<td  align="right">
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="checkPass()">审核通过</a> 
 			</td>
 			
 			<td  colspan="2" align="right">
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="checkNoPass()">审核不通过</a> 
 			</td>
 			
 			<!-- <td  colspan="2" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td> -->
			
		</tr>
		
	
</table>




<!-- <span onclick="Open_Dialog()">弹框</span> -->
<div id="mydialog" style="display:none;padding:5px;width:400px;height:200px;" title="审核不通过">
<input id="txRoleID" type="hidden" runat="server" value="0" />
<label class="lbInfo">不通过原因：</label><br>
<textarea rows="aa" cols="bb" name="xiaoxi" style="width: 80%; margin-left:10%;height: 100px"></textarea>
<!-- <input id="txRolename" type="text" class="easyui-validatebox" required="true" runat="server" /><br />
<label class="lbInfo"> </label><input type="submit" onserverclick="saveRole" value="保存" runat="server" /> -->
<label id="lbmsg" runat="server"></label>
</div> 


</form>
</body>
</html>
