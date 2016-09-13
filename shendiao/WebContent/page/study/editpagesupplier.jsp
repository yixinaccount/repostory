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
	
	

	/* $(document).ready(function () {
		
		$("#supplierType").combobox({
			onChange: function (n,o) {
				
				var supplierType1 = $("#supplierType").combobox('getValue');
				alert(supplierType1)
				var supplierType= $("#supplierTypeHidden").val();
				alert(supplierType)
				 
				 var type2 = document.getElementById("supplierType");
				 type2.value = supplierType;

				}
			});
		
		$("#countyType").combobox({
			onChange: function (n,o) {
				 var countyType = $("#countyTypeHidden").val();
					//alert(isFree); 
					
					 var type = document.getElementById("countyType");
					 type.value=countyType;

				}
			});

		}); */
	
	
	
	function editSupplier() {
		
		var supplier_id = $("#supplierIdHidden").val();
		var name = $("input[name='name']").val();
		if(name==null || name.trim() == ""){
			alert("机构名称不能为空！");
			return false;
		}
		//var type = $("#supplierType option:selected").val();
		var type = $("#supplierType").combobox('getValue');
		if(type==null || type.trim() == ""||isNaN(type)){
			alert("机构类型不能为空！");
			return false;
		}
		var sup_info = $("textarea[name='sup_info']").val(); 
		
		if(sup_info==null || sup_info.trim() == ""){
			alert("机构简介不能为空！");
			return false;
		}
		
		var type_info = $("input[name='type_info']").val();
		if(type_info==null || type_info.trim() == ""){
			alert("机构对象说明不能为空！");
			return false;
		}
		var short_name = $("input[name='short_name']").val();
		if(short_name==null || short_name.trim() == ""){
			alert("机构简称不能为空！");
			return false;
		}
		var telephone = $("input[name='telephone']").val();
		if(telephone==null || telephone.trim() == ""){
			alert("机构电话不能为空！");
			return false;
		}
		var addr_city = $("#shiType").combobox('getValue');
		if(addr_city==null || addr_city.trim() == ""||isNaN(addr_city)){
			alert("城市不能为空！");
			return false;
		}
		//var addr_district = $("#countyType option:selected").val();
		var addr_district = $("#countyType").combobox('getValue');
		if(addr_district==null || addr_district.trim() == ""||isNaN(addr_district)){
			alert("区不能为空！");
			return false;
		}
		var address = $("textarea[name='address']").val();  
		if(address==null || address.trim() == ""){
			alert("机构地址不能为空！");
			return false;
		}
		var longitude = $("input[name='longitude']").val();
		if(longitude==null || longitude.trim() == ""){
			alert("机构经度不能为空！");
			return false;
		}
		var latitude = $("input[name='latitude']").val();
		if(latitude==null || latitude.trim() == ""){
			alert("机构纬度不能为空！");
			return false;
		}
		var contacts = $("input[name='contacts']").val();
		if(contacts==null || contacts.trim() == ""){
			alert("机构联系人不能为空！");
			return false;
		}
		var mobile = $("input[name='mobile']").val();
		if(mobile==null || mobile.trim() == ""){
			alert("联系人电话不能为空！");
			return false;
		}
		
		var sup_detail = $("textarea[name='sup_detail']").val(); 
		if(sup_detail==null || sup_detail.trim() == ""){
			alert("机构详情不能为空！");
			return false;
		 }
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    //为FormData对象添加数据
 	    $.each($('#doc')[0].files,
 	    function(i, file) {
 	    	dataForm.append('multipartFile', file);
 	    });
 	    
 	    
 	    dataForm.append('supplier_id',supplier_id);
 	   	dataForm.append('name',name);
 	 	dataForm.append('type',type);
 	 	dataForm.append('sup_info',sup_info);
	 	dataForm.append('type_info',type_info);
	 	dataForm.append('short_name',short_name);
	 	dataForm.append('telephone',telephone);
	 	dataForm.append('addr_city',addr_city);
	 	dataForm.append('addr_district',addr_district);
	 	dataForm.append('address',address);
	 	dataForm.append('longitude',longitude);
	 	dataForm.append('latitude',latitude);
	 	dataForm.append('contacts',contacts);
	 	dataForm.append('mobile',mobile);
	 	dataForm.append('sup_detail',sup_detail);
	 	
		$.ajax({
	 	        url: '${basePath }/supplier/editSupplier.do',
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
							 location.href = "${basePath }/supplier/indexSupplier.do";
						 });
						
					} else {
						$.messager.alert('消息', data.msg);
					}
				}
			}) 
		

	}
	
	
	
	 function getaa() { 
		
			var supplierType= $("#supplierTypeHidden").val();
			 
			 var type2 = document.getElementById("supplierType");
			 type2.value = supplierType;
			
			
			 var countyType = $("#countyTypeHidden").val();
			//alert(isFree); 
			
			 var type = document.getElementById("countyType");
			 type.value=countyType;
		
		 
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

	<div style="padding-left: 42% ;font-size:18px;color:red">修改${supplierMap.name }</div>
		<input value="${supplierMap.supplier_id }" type="hidden" id="supplierIdHidden">
		<input value="${supplierMap.type }" type="hidden" id="supplierTypeHidden">
		<input value="${supplierMap.addr_district }" type="hidden" id="countyTypeHidden">
	<table cellpadding="5" align="center">
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="name" value="${supplierMap.name}" data-options="required:true" ></td>
		
		</tr>
	
	  <tr>
			<td align="right" width=90 class="formtd">机构类型：</td>
			<td>
				<select id="supplierType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="type" data-options="required:true" >
					
					<c:forEach items="${supplierTypeList }" var="n">
						<option value="${n.supplier_type}">${n.supplier_name}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="sup_info"  data-options="required:true">${supplierMap.sup_info}</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">机构图片：</td>
			<td><img  alt="" src="${basePath }${supplierMap.sup_pic}" width="200px" height="100px"></td>
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
		
			<td align="right" width=90 class="formtd">对象说明：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:120px" name="type_info" value="${supplierMap.type_info }" data-options="required:true"></td>
			<!-- <td >-</td>
			<td colspan="1"><input class="easyui-textbox" type="text" style="width:100px" name="type_info2"  data-options="required:true"></td>
		 -->
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构简称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="short_name" value="${supplierMap.short_name }" data-options="required:true" ></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">机构电话：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="telephone" value="${supplierMap.telephone }" data-options="required:true" ></td>
		
		</tr>
	
	 <tr>
			<td align="right" width=90 class="formtd">所在市：</td>
			<td>
				<select id="shiType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="addr_city" data-options="required:true" >
					
					<option value="2">上海市</option>
				</select>
			</td>
		</tr>
	
		
	  <tr>
			<td align="right" width=90 class="formtd">所在区：</td>
			<td>
				<select id="countyType" class="easyui-combobox" panelHeight="auto" style="width:120px" name="addr_district" data-options="required:true" >
					
					<c:forEach items="${countyList }" var="n">
					<option value="${n.id}">${n.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
				
		<tr>
		
			<td align="right" width=90 class="formtd">机构地址：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="address" data-options="required:true" > ${supplierMap.address}</textarea>
		
		</tr>
				
				
		<tr>
		
			<td align="right" width=90 class="formtd">经度：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="longitude" value="${supplierMap.longitude }" data-options="required:true" ></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">纬度：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="latitude" value="${supplierMap.latitude }" data-options="required:true" ></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">联系人：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="contacts" value="${supplierMap.contacts }" data-options="required:true" ></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">联系电话：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="mobile" value="${supplierMap.mobile }" data-options="required:true" ></td>
		
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
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="editSupplier()">保存</a>
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
