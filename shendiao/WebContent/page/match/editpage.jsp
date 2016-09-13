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
			 /* var isFree = $("#isFreeHidden").val();
			
		var type = document.getElementById("isFree");
		alert(type)
		  for(i = 0;i<=type.options.length;i++){
		
		alert(type.options[i].value == isFree);
		if(type.options[i].value == isFree){
		alert(333)
		
		  type.options[i].selected = true; 
		
		
		}
		} */
		
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
	
	
	
	function editMatch() {
		
		
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
		
		var match_id = $("#match_id").val();
		var match_name = $("input[name='match_name']").val();
		if(match_name==null || match_name.trim() == ""){
			alert("比赛名字不能为空！");
			return false;
		}
		
		var supplier_id = $("#supplierId").combobox('getValue');
		
		 if(supplier_id==null || supplier_id.trim() == ""||isNaN(supplier_id)){
				alert("机构名称不能为空！");
				return false;
			}
		 
		var match_info = $("textarea[name='match_info']").val();  
		
		if(match_info==null || match_info.trim() == ""){
			alert("比赛简介不能为空！");
			return false;
		}
		
		var apply_begin_date = $("input[name='apply_begin_date']").val();
        if(apply_begin_date==null || apply_begin_date.trim() == ""){
			
			alert("比赛报名开始时间不能为空！");
			return false;
		}
		
		var apply_end_date = $("input[name='apply_end_date']").val();
		
		if(apply_end_date==null || apply_end_date.trim() == ""){
			
			alert("比赛报名结束时间不能为空！");
			return false;
		}
		var match_begin_date = $("input[name='match_begin_date']").val();
		 if(match_begin_date==null || match_begin_date.trim() == ""){
				
				alert("比赛开始时间不能为空！");
				return false;
			 }
		 
		var match_end_date = $("input[name='match_end_date']").val();
		if(match_end_date==null || match_end_date.trim() == ""){
			
			alert("比赛结束时间不能为空！");
			return false;
		 }
		var match_address = $("textarea[name='match_address']").val(); 
		if(match_address==null || match_address.trim() == ""){
			
			alert("比赛地址不能为空！");
			return false;
		 }
		
		var match_Age_range = $("input[name='match_Age_range']").val();
		if(match_Age_range==null || match_Age_range.trim() == ""){
			
			alert("比赛年龄范围不能为空！");
			return false;
		 }
		 
		var base_num = $("input[name='base_num']").val();
		if(base_num==null || base_num.trim() == ""||isNaN(base_num)){
			alert("比赛基础人数不能为空！");
			return false;
		}
		//var is_free = $("#isFree option:selected").val();
		var is_free = $("#isFree").combobox('getValue');
		if(is_free==null || is_free.trim() == ""||isNaN(is_free)){
			alert("比赛是否免费不能为空！");
			return false;
		}
		
		var entry_fee_old = $("input[name='entry_fee_old']").val();
		if(entry_fee_old==null || entry_fee_old.trim() == ""||isNaN(entry_fee_old)){
			alert("比赛原始价格不能为空！");
			return false;
		}
		var entry_fee = $("input[name='entry_fee']").val();
		if(entry_fee==null || entry_fee.trim() == ""||isNaN(entry_fee)){
			alert("比赛优惠价格不能为空！");
			return false;
		}
		
		var match_detail = $("textarea[name='match_detail']").val(); 
		if(match_detail==null || match_detail.trim() == ""){
			alert("比赛详情不能为空！");
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
 	    dataForm.append('match_id',match_id);
 	   	dataForm.append('match_name',match_name);
 	  	dataForm.append('supplier_id',supplier_id);
 	 	dataForm.append('match_info',match_info);
	 	dataForm.append('apply_begin_date',apply_begin_date);
	 	dataForm.append('apply_end_date',apply_end_date);
	 	dataForm.append('match_begin_date',match_begin_date);
	 	dataForm.append('match_end_date',match_end_date);
	 	dataForm.append('match_address',match_address);
	 	dataForm.append('match_Age_range',match_Age_range);
	 	dataForm.append('base_num',base_num);
	 	dataForm.append('is_free',is_free);
	 	dataForm.append('entry_fee_old',entry_fee_old);
	 	dataForm.append('entry_fee',entry_fee);
	 	dataForm.append('match_detail',match_detail);
			//alert("dsfa");
		$.ajax({
	 	        url: '${basePath }/match/editMatch.do',
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
							 location.href = "${basePath }/match/index.do";
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
 	
	
	function getaa() { 
		
		var supplierId = $("#supplierIdHidden").val();
		 
		 var type2 = document.getElementById("supplierId");
		 type2.value = supplierId;
		
		
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
  
<body >
<form method="post" id="editForm" action="${basePath }/match/editMatch.do" enctype="multipart/form-data">
<input value="${matchInfoMap.is_free }" type="hidden" id = "isFreeHidden">
<input value="${matchInfoMap.supplier_id }" type="hidden" id ="supplierIdHidden" >
<input value="${picketType }" type="hidden" id ="picketTypeHidden" >
<input value="${picketPrice }" type="hidden" id ="picketPriceHidden" >
	<div style="padding-left: 42% ;font-size:18px;color:red">修改${matchInfoMap.match_name }</div>
<input value="${matchInfoMap.match_id }" type="hidden" id="match_id">
	<table cellpadding="5" align="center">
	
	
	
	<tr>
		
			<td align="right" width=90 class="formtd">比赛标题：</td>
			<td colspan="3">
			<%-- <textarea rows="" cols="" style="width: 250px;height: 30px" name="match_info" data-options="required:true" >${matchInfoMap.match_name}</textarea> --%>
			<input class="easyui-textbox" type="text" style="width:300px" name="match_name"  value="${matchInfoMap.match_name }" data-options="required:true" ></td> 
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">机构名称：</td>
			<td>
				<select id="supplierId" class="easyui-combobox" panelHeight="auto" style="width:250px" name="supplier_name" data-options="required:true" >
					
					<c:forEach items="${matchInfoMap.supplierList }" var="n">
					<option value="${n.supplier_id}">${n.name}</option>
					</c:forEach>
					<%-- <option >${matchInfoMap.supplier_name}</option> --%>
					
					
					
					<!-- <option value="1">aa</option>
					<option value="2">bb</option>
					<option value="3">cc</option> -->
					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="match_info" data-options="required:true" >${matchInfoMap.match_info }</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
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
			<td><img  alt="" src="${basePath }${matchInfoMap.match_pic}" width="200px" height="100px"></td>
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
			<input class="easyui-datetimebox" style="width:180px " name="apply_begin_date" data-options="required:true" value="${ matchInfoMap.apply_begin_date}" ></input>
			<%-- <input class="easyui-textbox" type="text" style="width:300px" name="title" value="${industryNewsMap.INDUSTRY_NEWS_TITLE }" data-options="required:true"> --%></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名截止日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_end_date" data-options="required:true" value="${ matchInfoMap.apply_end_date}" ></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛开始日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="match_begin_date" data-options="required:true" value="${ matchInfoMap.match_begin_date}" ></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛截止日期：</td>
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="match_end_date" data-options="required:true" value="${matchInfoMap.match_end_date }" ></input>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛地址：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="match_address" data-options="required:true" >${matchInfoMap.match_address }</textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">年龄范围：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_Age_range"  data-options="required:true" value="${ matchInfoMap.match_Age_range}" ></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">基础报名人数：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="base_num"  value=" ${matchInfoMap.base_num}" ></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">是否免费：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:100px" name="is_free"  >

					<option value="1" >是</option>
					<option value="0" >否</option>

				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">原始报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old"  data-options="required:true" value="${matchInfoMap.entry_fee_old }" ></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">优惠报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee"  data-options="required:true" value="${matchInfoMap.entry_fee }" ></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd" valign="top">比赛详情：</td>
			<td colspan="3">
			
			<textarea name="match_detail" id="myEditor" data-options="required:true">${matchInfoMap.match_detail}</textarea>
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
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="editMatch()">保存</a>
			</td>
			
			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td>
		
		</tr>
		
	
	
	
	
	<%-- 	<tr>
		
			<td align="right" width=90 class="formtd">标题：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_name" value="${matchInfoMap.match_name }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛编号：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_id" value="${matchInfoMap.match_id }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">比赛图片：</td>
			<td><img  alt="" src="${basePath }${matchInfoMap.match_pic}" width="200px" height="100px"></td>
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
		
			<td align="right" width=90 class="formtd">比赛编号：</td>
		<td><img alt="" src="${basePath}/upload/img/1.jpg"></td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_name" value="${matchInfoMap.match_name }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名费用：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old" value="${matchInfoMap.entry_fee_old }" data-options="required:true"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名截止日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="create_date" value="${matchInfoMap.create_date}"></input>
			<input class="easyui-textbox" type="text" style="width:300px" name="title" value="${industryNewsMap.INDUSTRY_NEWS_TITLE }" data-options="required:true"></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">分类：</td>
			<td>
				<select id="edittype" class="easyui-combobox" panelHeight="auto" style="width:120px" name="match_pic" data-options="required:true">
					
					<option>aa</option>
					<option>bb</option>
					<option>cc</option>
				</select>
			</td>
		</tr>

		<tr>
			<td align="right" width=90 class="formtd" valign="top">内容：</td>
			<td colspan="3">
			
			<textarea name="match_info" id="myEditor" >${matchInfoMap.match_detail}</textarea> 
			<!-- 	<script type="text/javascript"> 
					//var editor = new UE.ui.Editor();
					//editor.render("myEditor");
					//1.2.4以后可以使用一下代码实例化编辑器
					UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
					UE.getEditor('myEditor', {
						initialFrameWidth: 850,
						initialFrameHeight: 500
					});
				</script> -->
			
		</tr>
		<tr>
			<td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="editMatch()">保存</a>
			</td>
		</tr> --%>

</table>
<script >
	getaa();
</script>
</form>
</body>
</html>
