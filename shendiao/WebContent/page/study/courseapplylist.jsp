<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>课程报名列表</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	$(function(){
			var courseId = $("input[name='courseId']").val();
			var username = $("input[name='username']").val().trim();
			//var sign = $("input[name='sign']").val();
			//alert(matchId);
        $("#main").datagrid({
        	url:'listApply.do',
        	queryParams:{
        		courseId:courseId,
        		username:username
        		//sign:sign
        		}
        });//参数
    })
	
	function exportMatchApply(){
		
		location.href = "${basePath}/match/exportMatchAppExcel.do?matchId=1";
	}
	
	
	function selListByName(){
		var courseId = $("input[name='courseId']").val();
		var username = $("input[name='username']").val().trim();
		
		location.href = "${basePath }/study/courseApplygoto.do?courseId="+courseId+"&&username="+encodeURI(encodeURI(username));
	}
	

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
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.id+'\', \'0\', \''+rec.username+'\',\''+rec.course_name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.id+'\', \'1\', \''+rec.username+'\',\''+rec.course_name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		
		return str;
			
	}
	
	function setState(id, state, parentname,name) {
		var courseId = $("input[name='courseId']").val();
		var username = $("input[name='username']").val().trim();
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str +"家长  '"+parentname+"' 报名的  '"+name+"'课程 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/study/stateApply.do?courseAppId='+id+'&state='+state,
					dataType:'json',
					type:'post',
					cache:false,
					success:function(data){
						if (data.code == '0') {

							location.href = "${basePath }/study/courseApplygoto.do?courseId="+courseId+"&&username="+encodeURI(encodeURI(username));
							//location.href = encodeURI("${basePath }/match/matchApplygoto.do?matchId="+matchId+"&&username="+username,"utf-8");
						//	$('.easyui-datagrid').datagrid('reload');
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
  
<body>
<input value="${courseId}" type="hidden" name="courseId">

<table id="main" class="easyui-datagrid lines-bottom" title="课程报名列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'apply_id'">报名编号</th>
					<th data-options="field:'id'">申请编号</th>
					<th data-options="field:'username',sortable:true">家长名称</th>
					<th data-options="field:'telephone'">家长电话</th>
					
					<th data-options="field:'babyname'">宝贝姓名</th>
					<th data-options="field:'school_name'">学校名称</th>
					<th data-options="field:'birthday'">出生日期</th>
					<th data-options="field:'sex',width:100">性别</th>
					<th data-options="field:'remark'">备注</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">报名时间时间</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'opt',width:220,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="exportMatchApply()">导出比赛报名人员</a> -->
	<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	<td align="right" width=90 class="formtd">按家长姓名查询：</td>
	<input class="easyui-textbox" type="text" style="width:100px" name="username"  value="${username }" data-options="required:true">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selListByName()">查询</a>  
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="back()">返回课程列表</a>
	
	
	
	
</div>

</body>
</html>
