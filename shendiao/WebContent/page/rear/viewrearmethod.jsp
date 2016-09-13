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
	
	function getaa() { 
		
		var articleType= $("#articleTypeHidden").val();
		 
		 var type2 = document.getElementById("articleType");
		 type2.value = articleType;
	
	 
}  

	

	</script>
	<style type="text/css">
	.formtd {
		font-size: 12px;
	}
	</style>
  </head>
  
<body>
<form method="post" id="editForm" enctype="multipart/form-data">

<div style="padding-left: 42% ;font-size:18px;color:red" >查看${articleMap.article_name }</div>
		<input value="${articleMap.article_type }" type="hidden" id="articleTypeHidden">
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">文章名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="article_name" value="${articleMap.article_name }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
	
	  <tr>
			<td align="right" width=90 class="formtd">文章类型：</td>
			<td>
				<select id="articleType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="article_type" disabled="disabled" data-options="required:true">
					
					<c:forEach items="${articleTypeList }" var="n">
					<option value="${n.article_type}">${n.article_name}
					</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">文章简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="article_info" readonly="readonly" data-options="required:true">${articleMap.article_info }</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">文章图片：</td>
			<td><img  alt="" src="${basePath }${articleMap.article_pic}" width="200px" height="100px"></td>
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">文章详情：</td>
			<td colspan="3">
			
			<textarea name="article_detail" id="myEditor" data-options="required:true">${articleMap.article_detail }</textarea>
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
