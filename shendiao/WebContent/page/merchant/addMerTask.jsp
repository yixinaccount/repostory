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
		
		
	}) 
	
	
	
	function addMerchant(){
		
		
		coffee=document.forms[0].merchantType;
		 
	    txt="";
	    txt2="";
	    for (i=0;i<coffee.length;++ i){
	        if (coffee[i].checked){
	            txt=txt + coffee[i].value + "#";
	            /* var tt= $("#picketType"+coffee[i].value).val();
	            txt2 = txt2 + tt + "#";  */
	           
	        }
	    }
		 
		 
		 
		
		var merchant_name = $("input[name='merchant_name']").val();
		
		if(merchant_name==null || merchant_name.trim() == ""){
			alert("商家名称不能为空！");
			return false;
		}
		
		var merchant_shortname = $("input[name='merchant_shortname']").val();
		
		if(merchant_shortname==null || merchant_shortname.trim() == ""){
			alert("商家简称不能为空！");
			return false;
		}
		
		var merchar_desc = $("textarea[name='merchar_desc']").val();  
		
		if(merchar_desc==null || merchar_desc.trim() == ""){
			alert("商家简介不能为空！");
			return false;
		}
		
		
		var address = $("textarea[name='address']").val();  
		
		if(address==null || address.trim() == ""){
			alert("商家地址不能为空！");
			return false;
		}
		
		
		var owner_name = $("input[name='owner_name']").val();
		
		if(owner_name==null || owner_name.trim() == ""){
			alert("法人名称不能为空！");
			return false;
		}
		
		
		var phone = $("input[name='phone']").val();
		
		if(phone==null || phone.trim() == ""){
			alert("商家电话不能为空！");
			return false;
		}
		
		
		var post_code = $("input[name='post_code']").val();
		
		if(post_code==null || post_code.trim() == ""){
			alert("邮政编码不能为空！");
			return false;
		}
		
		
		
		
		var act_end_date = $("input[name='act_end_date']").val();
		
		if(act_end_date==null || act_end_date.trim() == ""){
			alert("活动结束时间不能为空！");
			return false;
		}
		
		
		var merchar_info = $("textarea[name='merchar_info']").val();   
		if(merchar_info==null || merchar_info.trim() == ""){
			alert("商家详情不能为空！");
			return false;
		}
		 	
		
		
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    //为FormData对象添加数据
 	    $.each($('#doc')[0].files,
 	    function(i, file) {
 	    	dataForm.append('multipartFile', file);
 	    });
 	    
 	    dataForm.append('merchantType',txt);
 	    dataForm.append('merchant_name',merchant_name);
 	   	dataForm.append('merchant_shortname',merchant_shortname);
 	 	dataForm.append('merchar_info',merchar_info);
	 	dataForm.append('merchar_desc',merchar_desc);
	 	dataForm.append('address',address);
	 	dataForm.append('owner_name',owner_name);
	 	dataForm.append('phone',phone);
	 	dataForm.append('post_code',post_code);
	 
		$.ajax({
	 	        url: '${basePath }/merchant/addMerchant.do',
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
							 location.href = "${basePath }/merchant/merchantIndex.do";
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

<input value="${merchantId}" type="hidden" name="merchantId">

<div style="padding-left: 42% ;font-size:18px;color:red" >添加任务</div>
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">任务名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="task_name"  data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">任务范围描述：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="task_range" data-options="required:true"></textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">任务简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="task_desc" data-options="required:true"></textarea>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">接任务开始日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="accept_begin_date" ></input>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">接任务结束日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="accept_end_date" ></input>
		
		</tr>
		
		
		
		
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">任务范围描述：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="task_range"  data-options="required:true"></td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">商家类型：</td>
			<td colspan="3">
			
				
				<c:forEach items="${merTypeList }" var="n">
				
					<label>	<input  type="checkbox"  name="merchantType" value="${n.merchant_type}" >${n.merchant_name}
						<%-- <input class="easyui-textbox" type="text" style="width:100px" id="picketType${n.merchant_type}" > --%>
					</label><br>
					
				</c:forEach>
				
			</td>
			
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">商家简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="merchar_desc" data-options="required:true"></textarea>
		
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
		
			<td align="right" width=90 class="formtd">商家地址：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="address" data-options="required:true"></textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">法人姓名：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="owner_name"  data-options="required:true"></td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">电话：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="phone"  data-options="required:true"></td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">邮政编码：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="post_code"  data-options="required:true"></td>
		
		</tr>
		
		
		
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">商家详情：</td>
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
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="addMerchant()">保存</a> 
 			</td>
 			
 			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
			
		</tr>
				
</table>

</form>
</body>
</html>
