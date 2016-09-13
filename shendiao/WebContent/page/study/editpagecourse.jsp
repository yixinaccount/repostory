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
	
	
	
	function editCourse(){
 		
 		
 		
 		var course_id = $("#courseId").val();
 		 if(course_id==null || course_id.trim() == ""||isNaN(course_id)){
				alert("课程id不能为空！");
				return false;
			}
	    
		var course_name = $("input[name='course_name']").val();
		
		if(course_name==null || course_name.trim() == ""){
			alert("课程名称不能为空！");
			return false;
		}
		
		var course_type =  $("#courseType").combobox('getValue');
		
		  if(course_type==null || course_type.trim() == ""||isNaN(course_type)){
				alert("课程类型不能为空！");
				return false;
			}
		
		
		var course_info = $("textarea[name='course_info']").val(); 
		
		if(course_info==null || course_info.trim() == ""){
			alert("课程简介不能为空！");
			return false;
		}
		
		var apply_begin_date = $("input[name='apply_begin_date']").val();
		
		if(apply_begin_date==null || apply_begin_date.trim() == ""){
			
			alert("课程报名开始时间不能为空！");
			return false;
		}
		
		var apply_end_date = $("input[name='apply_end_date']").val();
		
		if(apply_end_date==null || apply_end_date.trim() == ""){
			
			alert("课程报名结束时间不能为空！");
			return false;
		}
		
		var course_desc = $("input[name='course_desc']").val();
		
		if(course_desc==null || course_desc.trim() == ""){
			
			alert("课程年龄范围不能为空！");
			return false;
		 }
		
		var base_num = $("input[name='base_num']").val();
		
		if(base_num==null || base_num.trim() == ""||isNaN(base_num)){
			alert("课程基础人数不能为空！");
			return false;
		}
		
		var is_free = $("#isFree").combobox('getValue');
		if(is_free==null || is_free.trim() == ""||isNaN(is_free)){
			alert("课程是否免费不能为空！");
			return false;
		}
		
		var entry_fee_old = $("input[name='entry_fee_old']").val();
		if(entry_fee_old==null || entry_fee_old.trim() == ""||isNaN(entry_fee_old)){
			alert("课程原始价格不能为空！");
			return false;
		}
		var entry_fee = $("input[name='entry_fee']").val();
		if(entry_fee==null || entry_fee.trim() == ""||isNaN(entry_fee)){
			alert("课程优惠价格不能为空！");
			return false;
		}
		
		
		var course_detail = $("textarea[name='course_detail']").val(); 
		if(course_detail==null || course_detail.trim() == ""){
			alert("课程详情不能为空！");
			return false;
		 }
		
		 	/* alert(is_free);
		 	alert(supplier_id); */
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    //为FormData对象添加数据
 	    $.each($('#doc')[0].files,
 	    function(i, file) {
 	    	dataForm.append('multipartFile', file);
 	    });
 	    
 	    
 	    dataForm.append('course_id',course_id);
 	   	dataForm.append('course_name',course_name);
 	  	dataForm.append('course_type',course_type);
 	 	dataForm.append('course_info',course_info);
	 	dataForm.append('apply_begin_date',apply_begin_date);
	 	dataForm.append('apply_end_date',apply_end_date);
	 	
	 	dataForm.append('course_desc',course_desc);
	 	dataForm.append('base_num',base_num);
	 	dataForm.append('is_free',is_free);
	 	dataForm.append('entry_fee_old',entry_fee_old);
	 	dataForm.append('entry_fee',entry_fee);
	 	dataForm.append('course_detail',course_detail);
			//alert("dsfa");
		$.ajax({
	 	        url: '${basePath }/study/editCourse.do',
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
							 location.href = "${basePath }/study/index.do";
						 });
						
					} else {
						$.messager.alert('消息', data.msg);
					}
				}
			}) 
 	}
	
 	
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
<input value="${courseMap.course_id }" type="hidden" id="courseId">
<input value="${courseMap.is_free }" type="hidden" id = "isFreeHidden">
<input value="${courseMap.course_type }" type="hidden" id = "courseTypeHidden">
<div style="padding-left: 42% ;font-size:18px;color:red" >修改${courseMap.course_name  }</div>
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">课程名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="course_name" value="${courseMap.course_name }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">课程类型：</td>
			<td>
				<select id="courseType" class="easyui-combobox" panelHeight="auto" style="width:250px" name="course_type" data-options="required:true">
					
					<c:forEach items="${courseTypeList }" var="n">
					<option value="${n.supplier_type}">${n.supplier_name}</option>
					</c:forEach>
					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">课程简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="course_info"  data-options="required:true">${courseMap.course_info}</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">课程图片：</td>
			<td><img  alt="" src="${basePath }${courseMap.course_pic}" width="200px" height="100px"></td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">重新上传：</td>
			<td colspan="3">
			<input type=file name="multipartFile" id="doc" onchange="javascript:setImagePreview();">
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">上传预览：</td>
			<td colspan="3">
		
			<p><div id="localImag"><img id="preview" name="multipartFile" width=-1 height=-1 style="diplay:none" /></div></p> 
			
			</td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名开始日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_begin_date" value="${courseMap.apply_begin_date }" data-options="required:true"></input>
			<%-- <input class="easyui-textbox" type="text" style="width:300px" name="title" value="${industryNewsMap.INDUSTRY_NEWS_TITLE }" data-options="required:true"> --%></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名截止日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_end_date" value="${courseMap.apply_end_date }" data-options="required:true"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">年龄范围：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="course_desc" value="${courseMap.course_desc}" data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">基础报名人数：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="base_num" value="${courseMap.base_num }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">是否免费：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:100px" name="is_free" data-options="required:true"  onchange="setaa();">

					<option value="1">是</option>
					<option value="0" >否</option>

					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">原始报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old" value="${courseMap.entry_fee_old }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">优惠报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee" value="${courseMap.entry_fee }" data-options="required:true"></td>
		
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
			<td colspan="4" align="right" >
			   <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="editCourse()">保存</a>
			</td>
			
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
