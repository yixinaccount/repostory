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
	
	function editAct() {
		
		coffee=document.forms[0].picketType;
		 
	    txt="";
	    txt2="";
	    for (i=0;i<coffee.length;++ i){
	        if (coffee[i].checked){
	            txt=txt + coffee[i].value + "#";
	            var tt= $("#picketType"+coffee[i].value).val();
	            txt2 = txt2 + tt + "#"; 
	           
	        }
	    }
		
		var act_id = $("#act_id").val();
	
		var act_name = $("input[name='act_name']").val();
		if(act_name==null || act_name.trim() == ""){
			alert("活动名不能为空！");
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
		
		//var is_free = $("#isFree option:selected").val();
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
		 	/* alert(is_free);
		 	alert(supplier_id); */
		
		//创建FormData对象
 	    var dataForm = new FormData();
 	    //为FormData对象添加数据
 	    $.each($('#doc')[0].files,
 	    function(i, file) {
 	    	dataForm.append('multipartFile', file);
 	    });
 	    
 	    dataForm.append('picketType',txt);
	    dataForm.append('picketPrice',txt2);
 	   	dataForm.append('act_id',act_id);
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
	 	        url: '${basePath }/activity/editActivity.do',
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
						 $.messager.alert('消息',data.msg,'操作失败');
					}
				}
			}) 
		

		/* var act_name = $("input[name='act_name']").val();
		var act_info = $("textarea[name='act_info']").val();  
		$.ajax({
			url: '${basePath }/activity/editActivity.do',
			data: $('#addForm').serialize(),
			dataType:'json',
			     data: {
                    'act_name': act_name,
                    'act_info': act_info,
                },  
			type:'post',
			cache:false,
			success:function(data){
				if (data.code == '0') {
					$.messager.alert('消息', data.msg);
					location.href = "index.do";
				} else {
					$.messager.alert('消息', data.msg);
				}
			}
		})  */
		
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
		
		 	var isFree = $("#isFreeHidden").val();
			//alert(isFree); 
			
			 var type = document.getElementById("isFree");
			 type.value=isFree;
			 
			 
			//当页面加载完成的时候，自动调用该方法
	         //window.onload=function(){
	             //获得所要回显的值，此处为：100,1001,200,1400
	             //var checkeds = $("#meidaHidden").val();
	             var checkeds =$("#picketTypeHidden").val();
	             var checkedss = $("#picketPriceHidden").val();
	             //拆分为字符串数组
	             var checkArray =checkeds.split(",");
	             var checkArrayss =checkedss.split(",");
	            //获得所有的复选框对象
	            var checkBoxAll = $("input[name='picketType']");
	            //获得所有复选框（新闻,微信,论坛，问答，博客，平媒）的value值，然后，用checkArray中的值和他们比较，如果有，则说明该复选框被选中
	            for(var i=0;i<checkArray.length;i++){
	                //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中
	                $.each(checkBoxAll,function(j,checkbox){
	                    //获取复选框的value属性
	                   var checkValue=$(checkbox).val();
	                   if(checkArray[i]==checkValue){
	                        $(checkbox).attr("checked",true);
	                        $("#picketType"+checkArray[i]).val(checkArrayss[i]);
	                    }
	                })
	            }
			 
		
	}
	
	
	
	
	</script>
	<style type="text/css">
	.formtd {
		font-size: 12px;
	}
	</style>
  </head>
  
<body>
<form method="post" id="editForm" action="${basePath }/activity/editActivity.do">

	<div style="padding-left: 42% ;font-size:18px;color:red">修改${actInfoMap.act_name }</div>
	<input value="${actInfoMap.act_id }" type="hidden" id="act_id">
	<input value="${actInfoMap.is_free }" type="hidden" id = "isFreeHidden">
	<input value="${picketType }" type="hidden" id ="picketTypeHidden" >
    <input value="${picketPrice }" type="hidden" id ="picketPriceHidden" >
	<table cellpadding="5" align="center">
	
		<tr>
		
			<td align="right" width=90 class="formtd">活动标题：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_name" value="${ actInfoMap.act_name}" data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="act_info"  data-options="required:true">${actInfoMap.act_name }</textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">票种类型：</td>
			<td colspan="3">
			
				<c:forEach items="${ticketList }" var="n">
				
					<label>	<input  type="checkbox"  name="picketType" value="${n.ticket_type}" >${n.ticket_name}
						<input class="easyui-textbox" type="text" style="width:100px" id="picketType${n.ticket_type}" >
					</label><br>
					
				</c:forEach>
				
			</td>
			
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">比赛图片：</td>
			<td><img  alt="" src="${basePath }${actInfoMap.act_pic}" width="200px" height="100px"></td>
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
			<input class="easyui-datetimebox" style="width:180px " name="apply_begin_date" value="${actInfoMap.apply_begin_date }"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名结束日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_end_date" value="${actInfoMap.apply_end_date }"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动开始日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="act_begin_date" value="${actInfoMap.act_begin_date }"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动结束日期：</td>
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="act_end_date" value="${actInfoMap.act_end_date }"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动地点：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="act_address" data-options="required:true">${actInfoMap.act_address}</textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动年龄范围：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="act_Age_range"  data-options="required:true" value="${actInfoMap.act_Age_range }"></td>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动基础人数：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="base_num"  data-options="required:true" value="${actInfoMap.base_num }"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">是否免费：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:100px" name="is_free" data-options="required:true" >

					<option value="1">是</option>
					<option value="0" >否</option>

					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">原始报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old"  data-options="required:true" value="${actInfoMap.entry_fee_old }"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">优惠报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee"  data-options="required:true" value="${actInfoMap.entry_fee}"></td>
		
		</tr>
		
		<!-- <tr>
			<td align="right" width=90 class="formtd">是否置顶：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:100px" name="sort" data-options="required:true" >
					<option value="1">是</option>
					<option value="0" >否</option>
				</select>
			</td>
		</tr> -->
		
		
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">活动详情：</td>
			<td colspan="3">
			
			<textarea name="act_detail" id="myEditor" data-options="required:true"> ${actInfoMap.act_detail }</textarea>
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
			    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-save" onclick="editAct()">保存</a> 
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
