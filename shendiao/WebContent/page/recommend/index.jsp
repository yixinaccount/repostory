<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>推荐管理</title>
	<script type="text/javascript">
	
	$(function(){
		var recommendType = $("#recommendType").combobox('getValue');
		
		
		//var sign = $("input[name='sign']").val();
		//alert(matchId);
    $("#main").datagrid({
    	url:'listRecom.do',
    	queryParams:{
    		recommendType:recommendType
    		}
    });//参数
})



		
	$(document).ready(function () {
		
		$("#recommendType").combobox({
			onChange: function (n,o) {
			var recommendType1 = $("#recommendType").combobox('getValue');
			
				 $("#main").datagrid({
				    	url:'listRecom.do',
				    	queryParams:{
				    		recommendType:recommendType1
				    		}
				    });

				}
			});

		});
	
	
	
	
	function showState(val, rec) {
		if (val == '1') {
			return '<span style="color:green">正常</span>'
		} else {
			return '<span style="color:red">禁用</span>'   
		}
	}
	
	function showProgramaType(val,rec){
		
		if (val == '1') {
			return '<span style="color:green">比赛</span>'
		} else if(val == '2'){
			return '<span style="color:blue">活动</span>'   
		}else{
			return '<span style="color:red">课程</span>' 
		}
		
	}
	

	function showOpt(val, rec) {
		var str = '';
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.id+'\', \'0\', \''+rec.programa_name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.id+'\', \'1\', \''+rec.programa_name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
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
		$.messager.confirm('状态', "你确定要" + str + name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/recommend/state.do?id='+id+'&state='+state,
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
<table id="main" class="easyui-datagrid lines-bottom" title="推荐列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'programa_name',sortable:true">项目名称</th>
					<th data-options="field:'programa_type',width:100,align:'center',sortable:true,formatter:showProgramaType">推荐类型</th>
					<th data-options="field:'recommended_date',width:130,align:'center',sortable:true">推荐时间</th>
					<th data-options="field:'begin_date',width:130,align:'center',sortable:true">推荐开始时间</th>
					<th data-options="field:'end_date',width:130,align:'center',sortable:true">推荐结束时间</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">添加时间</th>
					<th data-options="field:'opt',width:100,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	&nbsp;&nbsp;&nbsp;按推荐类型查询：
	<select id="recommendType" class="easyui-combobox" panelHeight="auto" style="width:100px" name="recommendType" data-options="required:true" >
					
					<option value="0">全部</option>
					<option value="1">比赛</option>
					<option value="2">活动</option>
					<option value="3">课程</option>
					
				</select>
	
</div>
</body>
</html>
