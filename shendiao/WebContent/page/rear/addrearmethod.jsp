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
			initialFrameHeight: 500
		});
	}) 
	
	
	function setImagePreview() {

		var docObj=document.getElementById("doc");
		var imgObjPreview=document.getElementById("preview");
		if(docObj.files && docObj.files[0]){
				//火狐下，直接设img属性
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '200px';
				imgObjPreview.style.height = '100px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		}else{
				//IE下，使用滤镜
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("localImag");
				//必须设置初始大小
				localImagId.style.width = "250px";
				localImagId.style.height = "200px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try{
					localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			}catch(e){
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
			}
				imgObjPreview.style.display = 'none';
				document.selection.empty();
		}
			return true;
	}
	
	
	
	function addArticle(){
		
		
		var article_name = $("input[name='article_name']").val();
		if(article_name==null || article_name.trim() == ""){
			alert("文章名称不能为空！");
			return false;
		}
		var article_type = $('#articleType').combobox('getValue');
		if(article_type==null || article_type.trim() == ""||isNaN(article_type)){
			alert("文章类型不能为空！");
			return false;
		}
		
		var article_info = $("textarea[name='article_info']").val(); 
		
		if(article_info==null || article_info.trim() == ""){
			alert("文章简介不能为空！");
			return false;
		}
		
		var article_detail = $("textarea[name='article_detail']").val(); 
		if(article_detail==null || article_detail.trim() == ""){
			alert("文章详情不能为空！");
			return false;
		 }
		
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    //为FormData对象添加数据
 	    $.each($('#doc')[0].files,
 	    function(i, file) {
 	    	dataForm.append('multipartFile', file);
 	    });
 	    
 	    
 	   	dataForm.append('article_name',article_name);
 	 	dataForm.append('article_type',article_type);
 	 	dataForm.append('article_info',article_info);
	 	dataForm.append('article_detail',article_detail);
	 	
		$.ajax({
	 	        url: '${basePath }/rearMethod/addArticle.do',
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
							 location.href = "${basePath }/rearMethod/indexRearMethod.do";
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
<form method="post" id="editForm" enctype="multipart/form-data">

<div style="padding-left: 42% ;font-size:18px;color:red" >添加文章</div>
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">文章名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="article_name"  data-options="required:true"></td>
		
		</tr>
	
	  <tr>
			<td align="right" width=90 class="formtd">文章类型：</td>
			<td>
				<select id="articleType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="article_type" data-options="required:true">
					
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
			<textarea rows="" cols="" style="width: 250px" name="article_info" data-options="required:true"></textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">上传图片：</td>
			<td colspan="3">
			<input type=file name="multipartFile" id="doc" onchange="javascript:setImagePreview();" data-options="required:true">
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">图片预览：</td>
			<td colspan="3">
		
			<p><div id="localImag"><img id="preview" name="multipartFile" width=-1 height=-1 style="diplay:none" /></div></p> 
			
			</td>
		
		</tr>	
		
		
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">文章详情：</td>
			<td colspan="3">
			
			<textarea name="article_detail" id="myEditor" data-options="required:true"></textarea>
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
			   <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="addArticle()">保存</a>
			</td>
			
			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
			
		</tr>
				
</table>

</form>
</body>
</html>
