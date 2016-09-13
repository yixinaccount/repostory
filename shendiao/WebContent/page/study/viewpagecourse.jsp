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
	
	
	
 	function getaa(){
 		
 		var courseType = $("#courseTypeHidden").val();
		 
		 var type2 = document.getElementById("courseType");
		 type2.value = courseType;
		
		
		 var isFree = $("#isFreeHidden").val();
		//alert(isFree); 
		
		 var type = document.getElementById("isFree");
		 type.value=isFree;
 		
 		
 	}
 	
 	
	</script>
	<style type="text/css">
		.formtd {
			font-size: 12px;
		}
	</style>
  </head>
  
<body>
<form method="post" id="editForm" action="addMatch.do" enctype="multipart/form-data">
<div style="padding-left: 42% ;font-size:18px;color:red" >查看${courseMap.course_name  }</div>
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">课程名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="course_name" value="${courseMap.course_name }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">课程类型：</td>
			<td>
				<select id="courseType" class="easyui-combobox" panelHeight="auto" style="width:250px" name="course_type" data-options="required:true" disabled="disabled">
					
					<c:forEach items="${courseTypeList }" var="n">
					<option value="${n.supplier_type}">${n.supplier_name}</option>
					</c:forEach>
					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">课程简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="course_info"  data-options="required:true" readonly="readonly">${courseMap.course_info}</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">课程图片：</td>
			<td><img  alt="" src="${basePath }${courseMap.course_pic}" width="200px" height="100px"></td>
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名开始日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_begin_date" value="${courseMap.apply_begin_date }" data-options="required:true" readonly="readonly"></input>
			<%-- <input class="easyui-textbox" type="text" style="width:300px" name="title" value="${industryNewsMap.INDUSTRY_NEWS_TITLE }" data-options="required:true"> --%></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名截止日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_end_date" value="${courseMap.apply_end_date }" data-options="required:true" readonly="readonly"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">年龄范围：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="course_desc" value="${courseMap.course_desc}" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">基础报名人数：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="base_num" value="${courseMap.base_num }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">是否免费：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:100px" name="is_free" data-options="required:true" disabled="disabled">

					<option value="1">是</option>
					<option value="0" >否</option>

					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">原始报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old" value="${courseMap.entry_fee_old }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">优惠报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee" value="${courseMap.entry_fee }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">课程详情：</td>
			<td colspan="3">
			
			<textarea name="course_detail" id="myEditor" data-options="required:true">${courseMap.course_detail }</textarea>
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
<script >
	getaa();
</script>
</form>
</body>
</html>
