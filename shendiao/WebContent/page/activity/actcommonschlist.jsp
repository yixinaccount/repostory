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
        	url:'listActCommonSch.do',
        	queryParams:{
        		activityId:activityId
        		}
        });//参数
    })
	

	
	function back(){
		var activityId = $("#activityId").val();
		location.href = "${basePath }/activity/actSchedulegoto.do?activityId="+activityId;
	}

	
	</script>
  </head>
  
<body>
<input value="${activityId}" type="hidden" id="activityId">
<table id="main" class="easyui-datagrid lines-bottom" title="常规票列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'act_name',sortable:true">活动名称</th>
					<th data-options="field:'ticket_name'">活动票类型</th>
					<th data-options="field:'price'">活动票价格</th>
					<th data-options="field:'dt_create',width:130,align:'center',sortable:true">创建时间</th>
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
