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
		UEDITOR_CONFIG.UEDITOR_HOME_URL = '../../ueditor/';
		UE.getEditor('myEditor', {
			 toolbars:[['fullscreen', 'source', 'undo', 'redo', 
			               'bold', 'italic', 'underline', 'fontborder', 
			               'backcolor', 'fontsize', 'fontfamily', 'justifyleft', 
			               'justifyright', 'justifycenter', 'justifyjustify', 
			               'strikethrough', 'superscript', 'subscript', 'removeformat', 
			               'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 
			               'forecolor', 'backcolor', 'insertorderedlist', 
			               'insertunorderedlist', 'selectall', 'cleardoc', 
			               'link', 'unlink', 'emotion', 'help'/* ,'insertimage','simpleupload' */]],
			initialFrameWidth: 500,
			initialFrameHeight: 500
		});
		
		
		// 初始化上传按钮
		ImageEditor.initText('imgText', 'selectImg', 'showcover');
		
		alert(111)
	}) 
	
	
	
	// 定义私有函数
	var ImageEditor = {
 			
 			
 			
		editor : null,
		// 初始化编辑器，主要是隐藏主题框架，给特定的文本框复制
		initText : function(id, btn, showcover) {
			$(document.body).append('<div id="auto_ueditor_init" width=500></div>');
			var editor = this.getEditor('auto_ueditor_init');
			editor.ready(function(){
				editor.addListener('beforeInsertImage', function (t, args) {
					$("#"+id).textbox('setValue', args[0].src);
					$("#"+showcover).attr('src', args[0].src);
				});
				editor.setDisabled();
				editor.hide();
			});
			$('#' + btn).click(function() {
				editor.getDialog("insertimage").open();
			});
		},
		// 获取图片上传所依赖的编辑器
		getEditor : function(id) {
			this.editor = UE.getEditor(id);
			return this.editor;
		}
	};
	
	
	
	function uploadImage(){
 		var multipartFile = $("input[name='multipartFile']").val();
 		
 		 //location.href = "${basePath }/image/uploadImg.do?multipartFile="+multipartFile;
 	 	$.ajax({
 			
 			url: '${basePath }/image/uploadimg.do?multipartFile='+multipartFile,
			data: $('#addForm').serialize(),
			dataType:'json',
			type:'post',
			cache:false,
			success:function(data){
				if (data.code == '0') {
					//$.messager.alert('消息', data.msg);
					$.messager.alert('消息', data.msg);
					
				} else {
					 
					$.messager.alert('消息', data.msg);
				}
			}
 			
 		}) 
 	}
 	
 	
	function saveReport() {
		
		 $("#editForm").ajaxSubmit({  
             type: 'post',  
             dataType:"xml",
             url: '${basePath }/activity/addActivity.do' ,  
             success: function(data){  
                /*  alert( "success");  
                 alert(data); */
                 
                 if (data.code == '0') {
     				//$.messager.alert('消息', data.msg);
     				 $.messager.alert('消息', data.msg,'操作成功',function () {  
     					 location.href = "${basePath }/activity/index.do";
     				 });
     				
     			} else {
     				 
     				$.messager.alert('消息', data.msg);
     			}
                    
             },  
             error: function(XmlHttpRequest, textStatus, errorThrown){  
                 alert( "error");  
             }  
         });  
          
         return false;
		
		
		
		/* // jquery 表单提交
		$("#editForm").ajaxSubmit(function(data) {
			
			if (data.code == '0') {
				//$.messager.alert('消息', data.msg);
				 $.messager.alert('消息', data.msg,'操作成功',function () {  
					 location.href = "${basePath }/activity/index.do";
				 });
				
			} else {
				 
				$.messager.alert('消息', data.msg);
			}
		// 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容
		});
		
		return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
		 */
	}
	
	
	function addActivity(){
		
		
		/*  var qx = $("input[name='picketType']:checked").map(function () {
             return $(this).val();
         }).get().join('#'); */
        /*  var qx = $("input[name='picketType']:checked").val();
		 alert(qx) */
		 
		coffee=document.forms[0].picketType;
		 
	    txt="";
	    txt2="";
	    for (i=0;i<coffee.length;++ i){
	        if (coffee[i].checked){
	            txt=txt + coffee[i].value + "#";
	            var tt= $("#picketType"+coffee[i].value).val();
	            txt2 = txt2 + tt + "#"; 
	            /* if(coffee[i].value==1){
	            	 var tt= $("#picketType"+coffee[i].value).val();
	            	 txt2 = txt2 + tt + "#";
	            }
	            
	            if(coffee[i].value==2){
	            	var tt= $("#picketType"+coffee[i].value).val();
	            	txt2 = txt2 + tt + "#";
	            }
	            
	            if(coffee[i].value==3){
	            	var tt= $("#picketType"+coffee[i].value).val();
	            	txt2 = txt2 + tt + "#";
	            }
	            
	            if(coffee[i].value==4){
	            	var tt= $("#picketType"+coffee[i].value).val();
	            	txt2 = txt2 + tt + "#";
	            }
	            */
	        }
	    }
		 
		 
		 
		
		var act_name = $("input[name='act_name']").val();
		
		if(act_name==null || act_name.trim() == ""){
			alert("活动标题不能为空！");
			return false;
		}
		
		var act_info = $("textarea[name='act_info']").val();  
		
		if(act_info==null || act_info.trim() == ""){
			alert("活动简介不能为空！");
			return false;
		}
		
		var apply_begin_date = $("input[name='apply_begin_date']").val();
		
		if(apply_begin_date==null || apply_begin_date.trim() == ""){
			alert("活动报名开始时间不能为空！");
			return false;
		}
		
		var apply_end_date = $("input[name='apply_end_date']").val();
		
		if(apply_end_date==null || apply_end_date.trim() == ""){
			alert("活动报名结束时间不能为空！");
			return false;
		}
		
		var act_begin_date = $("input[name='act_begin_date']").val();
		
		if(act_begin_date==null || act_begin_date.trim() == ""){
			alert("活动开始时间不能为空！");
			return false;
		}
		
		
		var act_end_date = $("input[name='act_end_date']").val();
		
		if(act_end_date==null || act_end_date.trim() == ""){
			alert("活动结束时间不能为空！");
			return false;
		}
		
		var act_address = $("textarea[name='act_address']").val();  
		
		if(act_address==null || act_address.trim() == ""){
			alert("活动地址不能为空！");
			return false;
		}
		
		var act_Age_range = $("input[name='act_Age_range']").val();
		
		if(act_Age_range==null || act_Age_range.trim() == ""){
			alert("活动年龄范围不能为空！");
			return false;
		}
		var base_num = $("input[name='base_num']").val();
		if(base_num==null || base_num.trim() == ""||isNaN(base_num)){
			alert("活动基础人数不能为空！");
			return false;
		}
		
		
		var is_free = $("#isFree").combobox('getValue');
		
		if(is_free==null || is_free.trim() == ""){
			alert("活动是否免费不能为空！");
			return false;
		}
		
		var entry_fee_old = $("input[name='entry_fee_old']").val();
		if(entry_fee_old==null || entry_fee_old.trim() == "" ||isNaN(entry_fee_old)){
			alert("活动原始报名费用不能为空！");
			return false;
		}
		
		var entry_fee = $("input[name='entry_fee']").val();
		if(entry_fee==null || entry_fee.trim() == "" ||isNaN(entry_fee)){
			alert("活动优惠报名费用不能为空！");
			return false;
		}
		
		var act_detail = $("textarea[name='act_detail']").val();   
		if(act_detail==null || act_detail.trim() == ""){
			alert("活动详情不能为空！");
			return false;
		}
		 	
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    //为FormData对象添加数据
 	    $.each($('#doc')[0].files,
 	    function(i, file) {
 	    	dataForm.append('multipartFile', file);
 	    });
 	    
 	    dataForm.append('picketType',txt);
 	    dataForm.append('picketPrice',txt2);
 	   	dataForm.append('act_name',act_name);
 	 	dataForm.append('act_info',act_info);
	 	dataForm.append('apply_begin_date',apply_begin_date);
	 	dataForm.append('apply_end_date',apply_end_date);
	 	dataForm.append('act_begin_date',act_begin_date);
	 	dataForm.append('act_end_date',act_end_date);
	 	dataForm.append('act_address',act_address);
	 	dataForm.append('act_Age_range',act_Age_range);
	 	dataForm.append('base_num',base_num);
	 	dataForm.append('is_free',is_free);
	 	dataForm.append('entry_fee_old',entry_fee_old);
	 	dataForm.append('entry_fee',entry_fee);
	 	dataForm.append('act_detail',act_detail);
			//alert("dsfa");
		$.ajax({
	 	        url: '${basePath }/activity/addActivity.do',
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
	
	
	
	
	</script>
	<style type="text/css">
	.formtd {
		font-size: 12px;
	}
	</style>
  </head>
  
<body>
<form method="post" id="editForm" action="" enctype="multipart/form-data" target="ifm">

<div style="padding-left: 42% ;font-size:18px;color:red" >添加活动</div>
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">活动标题：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_name"  data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="act_info" data-options="required:true"></textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">票种类型：</td>
			<td colspan="3">
			
				<!-- <label>	<input  type="checkbox"  name="picketType" value="1" >一大一小
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType1" >
				</label><br>
				
				<label>	<input  type="checkbox"  name="picketType" value="2" >两大一小
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType2" >
				</label><br>
				
				<label>	<input  type="checkbox"  name="picketType" value="3" >家庭票
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType3" >
				</label><br>
				
				<label>	<input  type="checkbox"  name="picketType" value="4" >通票
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType4" >
				</label> -->
				
				<c:forEach items="${ticketList }" var="n">
				
					<label>	<input  type="checkbox"  name="picketType" value="${n.ticket_type}" >${n.ticket_name}
						<input class="easyui-textbox" type="text" style="width:100px" id="picketType${n.ticket_type}" >
					</label><br>
					
				</c:forEach>
				
			</td>
			
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">上传图片：</td>
			<td colspan="3">
			<input type=file name="multipartFile" id="doc" onchange="javascript:setImagePreview();">
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">图片预览：</td>
			<td colspan="3">
		
			<p><div id="localImag"><img id="preview" name="multipartFile" width=-1 height=-1 style="diplay:none" /></div></p> 
			
			</td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名开始日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_begin_date" ></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名结束日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_end_date" ></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动开始日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="act_begin_date" ></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动结束日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="act_end_date" ></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动地点：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="act_address" data-options="required:true"></textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动年龄范围：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_Age_range"  data-options="required:true"></td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动基础人数：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="base_num"  data-options="required:true"></td>
		
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
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old"  data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">优惠报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee"  data-options="required:true"></td>
		
		</tr>
		
	<!-- 	<tr>
			<td align="right" width=90 class="formtd">是否置顶：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:100px" name="sort" data-options="required:true"  onchange="setaa();">
					<option value="1">是</option>
					<option value="0" >否</option>
				</select>
			</td>
		</tr> -->
		
		
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">活动详情：</td>
			<td colspan="3">
			
			<textarea name="act_detail" id="myEditor" data-options="required:true"></textarea>
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
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="addActivity()">保存</a> 
 			</td>
 			
 			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
			
		</tr>
				
</table>

</form>
</body>
</html>
