<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<jsp:include page="/common/ueditor.jsp"></jsp:include>
	<script type="text/javascript">
	
	$(function(){
		UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
		UE.getEditor('myEditor', {
			 toolbars:[['fullscreen', 'source', 'undo', 'redo', 
			               'bold', 'italic', 'underline', 'fontborder', 
			               'backcolor', 'fontsize', 'fontfamily', 'justifyleft', 
			               'justifyright', 'justifycenter', 'justifyjustify', 
			               'strikethrough', 'superscript', 'subscript', 'removeformat', 
			               'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 
			               'forecolor', 'backcolor', 'insertorderedlist', 
			               'insertunorderedlist', 'selectall', 'cleardoc', 
			               'link', 'unlink', 'emotion', 'help']],
			initialFrameWidth: 500,
			initialFrameHeight: 500,
			readonly:true
		});
	}) 
	
	
	</script>
	<style type="text/css">
	.formtd {
		font-size: 12px;
	}
	</style>
  </head>
  
<body>
<form method="post" id="editForm" >

	<div style="padding-left: 42% ;font-size:18px;color:red">查看${supplierMap.name }</div>
	<table cellpadding="5" align="center">
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="name" value="${supplierMap.name}" data-options="required:true" readonly="readonly"></td>
		
		</tr>
	
	  <tr>
			<td align="right" width=90 class="formtd">机构类型：</td>
			<td>
				<select id="supplierType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="type" data-options="required:true" disabled="disabled">
					
					<c:forEach items="${supplierTypeList }" var="n">
					
						<c:if test="${supplierMap.type == n.supplier_type }">
							<option value="${n.supplier_type}">${n.supplier_name}</option>
						</c:if>

				</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="sup_info"  data-options="required:true" readonly="readonly">${supplierMap.sup_info}</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">机构图片：</td>
			<td><img  alt="" src="${basePath }${supplierMap.sup_pic}" width="200px" height="100px"></td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">对象说明：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:120px" name="type_info" value="${supplierMap.type_info }" data-options="required:true" readonly="readonly"></td>
			<!-- <td >-</td>
			<td colspan="1"><input class="easyui-textbox" type="text" style="width:100px" name="type_info2"  data-options="required:true"></td>
		 -->
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构简称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="short_name" value="${supplierMap.short_name }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构电话：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="telephone" value="${supplierMap.telephone }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">预约次数：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="order_num" value="${supplierMap.order_num }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
	
	 <tr>
			<td align="right" width=90 class="formtd">所在市：</td>
			<td>
				<select id="shiType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="addr_city" data-options="required:true" disabled="disabled">
					
					<option value="2">上海市</option>
				</select>
			</td>
		</tr>
	
		
	  <tr>
			<td align="right" width=90 class="formtd">所在区：</td>
			<td>
				<select id="countyType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="addr_district" data-options="required:true" disabled="disabled">
					
					<c:forEach items="${countyList }" var="n">
					
						<c:if test="${supplierMap.addr_district == n.id }">
							<option value="${n.id}">${n.name}</option>
						</c:if>
						
					</c:forEach>
				</select>
			</td>
		</tr>
				
		<tr>
		
			<td align="right" width=90 class="formtd">机构地址：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="address" data-options="required:true" disabled="disabled"> ${supplierMap.address}</textarea>
		
		</tr>
				
				
		<tr>
		
			<td align="right" width=90 class="formtd">经度：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="longitude" value="${supplierMap.longitude }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">纬度：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="latitude" value="${supplierMap.latitude }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">联系人：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="contacts" value="${supplierMap.contacts }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">联系电话：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="mobile" value="${supplierMap.mobile }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">机构详情：</td>
			<td colspan="3">
			
			<textarea name="sup_detail" id="myEditor" data-options="required:true">${supplierMap.sup_detail }</textarea>
				<!-- <script type="text/javascript">
					//var editor = new UE.ui.Editor();
					//editor.render("myEditor");
					//1.2.4以后可以使用一下代码实例化编辑器
					UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
					UE.getEditor('myEditor', {
						initialFrameWidth: 850,
						initialFrameHeight: 500
					});
					 
				</script>  -->
			<!-- <input id="realText" type="hidden" name="match_detail" data-options="required:true"></td> -->
			
		</tr>
		
		
		<tr>
			<td colspan="4" align="right">
				   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
		</tr>
</table>

</form>
</body>
</html>
