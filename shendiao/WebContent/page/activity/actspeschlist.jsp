<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>afas</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	$(function(){
			var activityId = $("#activityId").val();

        $("#main").datagrid({
        	url:'listActSpeSch.do',
        	queryParams:{
        		activityId:activityId
        		}
        });//参数
    })
	

	function showState(val, rec) {
		if (val == '1') {
			return '<span style="color:green">正常</span>'
		} else {
			return '<span style="color:red">禁用</span>'   
		}
	}
	
	function showOpt(val, rec) {
		var str = '';
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.id+'\', \'0\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.id+'\', \'1\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		  
		/*  str += '<a href="#" style="color:blue" onclick="addSpeSch(\''+rec.act_id+'\')">添加特价票</a>'; */
		 
		return str;
			
	}
	
	
	
	
	function setState(id, state) {
	
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		
		$.messager.confirm('状态', "你确定要" + str +"该时间段特价票吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/activity/stateSpeSch.do?id='+id+'&state='+state,
					dataType:'json',
					type:'post',
					cache:false,
					success:function(data){
						if (data.code == '0') {							
						$('.easyui-datagrid').datagrid('reload');
						} else {
							$.messager.alert('消息', data.msg);
						}
					}
				})
			}
		});
	}
	
	
	
	function back(){
		var activityId = $("#activityId").val();
		location.href = "${basePath }/activity/actSchedulegoto.do?activityId="+activityId;
	}

	
	</script>
  </head>
  
<body>
<input value="${id}" type="hidden" id="actSpeSchId">
<input value="${activityId}" type="hidden" id="activityId">
<table id="main" class="easyui-datagrid lines-bottom" title="特价票列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'act_name',sortable:true">活动名称</th>
					<th data-options="field:'ticket_name'">活动票类型</th>
					<th data-options="field:'schedule_date',width:130,align:'center',sortable:true">特价时间</th>
					<th data-options="field:'price'">特价票价格</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'dt_create',width:130,align:'center',sortable:true">创建时间</th>
					<th data-options="field:'opt',width:220,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<!-- <!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="exportActApply()">添加活动时间</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="back()">返回</a>
	
	<!-- <td colspan="4" align="right">
			   <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="window.history.back(-1); ">返回</a>
			</td> -->
</div>

</body>
</html>
