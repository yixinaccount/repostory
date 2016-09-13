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
<form method="post" id="editForm" >

	<div style="padding-left: 42% ;font-size:18px;color:red">查看${matchInfoMap.match_name }</div>
<input value="${matchInfoMap.match_id }" type="hidden" name="match_id">
<input value="${picketType }" type="hidden" id ="picketTypeHidden" >
<input value="${picketPrice }" type="hidden" id ="picketPriceHidden" >
	<table cellpadding="5" align="center">
	
	<tr>
		
			<td align="right" width=90 class="formtd">比赛标题：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_name"  value="${matchInfoMap.match_name }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">机构名称：</td>
			<td>
				<select id="supplierId" class="easyui-combobox" panelHeight="auto" style="width:250px" name="supplier_name" data-options="required:true" disabled="disabled">
					
				
					<option >${matchInfoMap.supplier_name}</option>
					
					
					
					<!-- <option value="1">aa</option>
					<option value="2">bb</option>
					<option value="3">cc</option> -->
					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛简介：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="match_info" data-options="required:true" disabled="disabled">${matchInfoMap.match_info }</textarea>
			<!-- <input class="easyui-textbox" type="text" style="width:300px" name="match_id"  data-options="required:true"> --></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">票种类型：</td>
			<td colspan="3">
			
				<!-- <label>	<input  type="checkbox"  name="picketType" value="1" disabled="disabled">一大一小
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType1" readonly="readonly">
				</label><br>
				
				<label>	<input  type="checkbox"  name="picketType" value="2" disabled="disabled">两大一小
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType2" readonly="readonly">
				</label><br>
				
				<label>	<input  type="checkbox"  name="picketType" value="3" disabled="disabled">家庭票
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType3" readonly="readonly">
				</label><br>
				
				<label>	<input  type="checkbox"  name="picketType" value="4" disabled="disabled">通票
					<input class="easyui-textbox" type="text" style="width:100px" id="picketType4" readonly="readonly">
				</label> -->
				
				<c:forEach items="${ticketList }" var="n">
				
					<label>	<input  type="checkbox"  name="picketType" value="${n.ticket_type}" disabled="disabled">${n.ticket_name}
						<input class="easyui-textbox" type="text" style="width:100px" id="picketType${n.ticket_type}" readonly="readonly">
					</label><br>
					
				</c:forEach>
				
			</td>
			
		</tr>
		
		
		
		<tr>
			<td align="right" width=90 class="formtd">比赛图片：</td>
			<td><img  alt="" src="${basePath }${matchInfoMap.match_pic}" width="200px" height="100px"></td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名开始日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_begin_date" data-options="required:true" value="${ matchInfoMap.apply_begin_date}" readonly="readonly"></input>
			<%-- <input class="easyui-textbox" type="text" style="width:300px" name="title" value="${industryNewsMap.INDUSTRY_NEWS_TITLE }" data-options="required:true"> --%></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名截止日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="apply_end_date" data-options="required:true" value="${ matchInfoMap.apply_end_date}" readonly="readonly"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛开始日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="match_begin_date" data-options="required:true" value="${ matchInfoMap.match_begin_date}" readonly="readonly"></input>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛截止日期：</td>
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="match_end_date" data-options="required:true" value="${matchInfoMap.match_end_date }" readonly="readonly"></input>
		
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">比赛地址：</td>
			<td colspan="3">
			<textarea rows="" cols="" style="width: 250px" name="match_address" data-options="required:true" disabled="disabled">${matchInfoMap.match_address }</textarea>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">年龄范围：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_Age_range"  data-options="required:true" value="${ matchInfoMap.match_Age_range}" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">基础报名人数：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="base_num"  value=" ${matchInfoMap.base_num}" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">是否免费：</td>
			<td>
				<select id="isFree" class="easyui-combobox" panelHeight="auto" style="width:250px" name="is_free" data-options="required:true"  onchange="setaa();"  >

					
					<c:if test="${matchInfoMap.is_free == '1' }">
						<option value="1">是</option>
					</c:if>
					
					<c:if test="${matchInfoMap.is_free == '0' }">
						<option value="0">否</option>
					</c:if>
					
					<%-- <option value="1" selected="<c:if test='${matchInfoMap.is_free == "1" }'> selected
					</c:if>">是是是</option>
					
					<option value="0" selected="<c:if test='${matchInfoMap.is_free == "0" }'> selected
					</c:if>">否否否</option> --%>
					
				</select>
			</td>
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">原始报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old"  data-options="required:true" value="${matchInfoMap.entry_fee_old }" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">优惠报名费用：</td>
			<td colspan="3">
			<input class="easyui-textbox" type="text" style="width:300px" name="entry_fee"  data-options="required:true" value="${matchInfoMap.entry_fee }" readonly="readonly"></td>
		
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
