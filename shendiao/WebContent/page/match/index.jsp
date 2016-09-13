<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>afas</title>
	<script type="text/javascript">
	
	function showState(val, rec) {
		if (val == '1') {
			return '<span style="color:green">正常</span>'
		} else {
			return '<span style="color:red">禁用</span>'   
		}
	}
	

	function showOpt(val, rec) {
		var str = '';
		str += '<a href="#" style="color:blue" onclick="editMatch(\''+rec.match_id+'\');">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		str += '<a href="#" style="color:blue" onclick="viewMatch(\''+rec.match_id+'\');">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.match_id+'\', \'0\', \''+rec.match_name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
			str += '<a href="#" style="color:red" onclick="addMatchRecom(\''+rec.match_id+'\')">添加推荐</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.match_id+'\', \'1\', \''+rec.match_name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		/* str += '<a href="#" style="color:red" onclick="delUser('+rec.match_id+', \''+rec.match_name+'\')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;'
	 */	
		str += '<a href="#" style="color:blue" onclick="selMatAppList(\''+rec.match_id+'\')">查看报名</a>';
		return str;
			
	}
	
	
	function selMatAppList(id){
		location.href = "${basePath }/match/matchApplygoto.do?matchId="+id;
	}
	
	function viewMatch(id){
		location.href = "viewMatchgoto.do?id="+id;
	}
	
	function addMatchRecom(id){
		location.href = "addMatchRecomgoto.do?id="+id;
	}
	
	function editMatch(id){
		  
		location.href = "editMatchgoto.do?id="+id;
	}
	
	
	function setState(id, state, name) {
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "比赛 '"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/match/state.do?id='+id+'&state='+state,
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

	function openAddDialog() {
		
				location.href = "${basePath }/match/addMatchgoto.do";
		 
	}
	

	function delUser(id, name) {
		$.messager.confirm('删除', "你确定要删除比赛'"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/match/del.do?id='+id,
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
	
	</script>
  </head>
  
<body >
<table id="main" class="easyui-datagrid lines-bottom" title="去比赛" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,url:'list.do',method:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<!-- <th data-options="field:'match_id'">ID</th> -->
					<th data-options="field:'match_name',sortable:true">比赛名称</th>
					<!-- <th data-options="field:'match_info'">比赛简介</th> -->
					<!-- <th data-options="field:'supName'">机构名称</th> -->
					
					<!-- <th data-options="field:'match_detailkk'">赛事详情</th> -->
					<th data-options="field:'match_address'">比赛地点</th>
					<th data-options="field:'match_Age_range'">年龄范围</th>
					<th data-options="field:'real_num',width:100">实际报名人数</th>
					<th data-options="field:'entry_fee_old'">报名费用</th>

					
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date_format',width:130,align:'center',sortable:true">添加时间</th>
					<th data-options="field:'opt',width:250,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="openAddDialog()">添加比赛</a>
	
	<!-- &nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="openAddDialog()">导出比赛信息</a>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<td align="right" width=90 class="formtd">按比赛名称查询：</td>
	<input class="easyui-textbox" type="text" style="width:100px" name="remark"  data-options="required:true">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>   -->
		
	
</div>
</body>
</html>
