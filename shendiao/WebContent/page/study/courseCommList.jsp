<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>课程评论管理</title>
	<script type="text/javascript">
	
	$(function(){
		var courseId = $("#courseId").val();
		
		//var sign = $("input[name='sign']").val();
			//alert(matchId);
	    $("#main").datagrid({
	    	url:'listComment.do',
	    	queryParams:{
	    		courseId:courseId
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
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.id+'\', \'0\', \''+rec.username+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.id+'\', \'1\', \''+rec.username+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		return str;
			
	}
	
	
	
	
	function setState(id, state, name) {
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "用户'"+name+"' 的评论吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/user/commentState.do?commentId='+id+'&state='+state,
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
		location.href = "${basePath }/study/index.do";
	}
	

	</script>
  </head>
  
<body >
<input value="${courseId }" type="hidden" id="courseId">
<table id="main" class="easyui-datagrid lines-bottom" title="课程评论列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'course_name',sortable:true">课程名称</th>
					<th data-options="field:'username'">评论人姓名</th>
					<th data-options="field:'content'">评论内容</th>
					<th data-options="field:'comment_score'">评论分数</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">添加时间</th>
					<th data-options="field:'opt',width:100,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="back()">返回课程列表</a>
	
	
</div>
</body>
</html>
