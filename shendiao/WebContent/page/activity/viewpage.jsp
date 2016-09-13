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

	<div style="padding-left: 42% ;font-size:18px;color:red">查看${actInfoMap.act_name }</div>
<input value="${actInfoMap.act_id }" type="hidden" name="act_id">
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
			<td><img  alt="" src="${basePath }${actInfoMap.act_pic}" width="200px" height="100px"></td>
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

					<c:if test="${actInfoMap.is_free == '1' }">
						<option value="1">是</option>
					</c:if>
					
					<c:if test="${actInfoMap.is_free == '0' }">
						<option value="0">否</option>
					</c:if>

					
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
				   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
				</td>
				
			</tr>
		
	
	
	
		<%-- <tr>
		
			<td align="right" width=90 class="formtd">标题：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_name" value="${actInfoMap.act_name }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动编号：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_id" value="${actInfoMap.act_id }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">活动名称：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="match_name" value="${actInfoMap.act_name }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
			<td align="right" width=90 class="formtd">活动图片：</td>
			<td><img  alt="" src="${basePath}/upload/img/1.jpg" width="200px" height="100px"></td>
		</tr>
		
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名费用：</td>
			<td colspan="3"><input class="easyui-textbox" type="text" style="width:300px" name="entry_fee_old" value="${actInfoMap.entry_fee_old }" data-options="required:true" readonly="readonly"></td>
		
		</tr>
		
		<tr>
		
			<td align="right" width=90 class="formtd">报名截止日期：</td>
			
			
			<td colspan="3">
			<input class="easyui-datetimebox" style="width:180px " name="create_date" value="${actInfoMap.create_date}" readonly="readonly"></input>
			<input class="easyui-textbox" type="text" style="width:300px" name="title" value="${industryNewsMap.INDUSTRY_NEWS_TITLE }" data-options="required:true"></td>
		
		</tr>
		
		
		<tr>
			<td align="right" width=90 class="formtd">分类：</td>
			<td>
				<select id="edittype" class="easyui-combobox" panelHeight="auto" style="width:120px" name="match_pic" data-options="required:true" disabled="disabled">
					
					<option>aa</option>
					<option>bb</option>
					<option>cc</option>
				</select>
			</td>
		</tr>

		<tr>
			<td align="right" width=90 class="formtd" valign="top">内容：</td>
			<td colspan="3">
			
			<textarea name="match_info" id="myEditor" >${actInfoMap.act_detail}</textarea> 
			
			
		</tr>
 --%>
</table>
<script >
	getaa();
</script>
</form>
</body>
</html>
