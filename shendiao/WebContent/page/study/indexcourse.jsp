<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>课程管理</title>
	<script type="text/javascript">
	
	$(function(){
		var supplierName = $("input[name='supplierName']").val().trim();
		//var sign = $("input[name='sign']").val();
		//alert(matchId);
    $("#main").datagrid({
    	url:'list.do',
    	queryParams:{
    		supplierName:supplierName
    		//sign:sign
    		}
    });//参数
})

function selListByName(){
	var supplierName = $("input[name='supplierName']").val().trim();
	location.href = "${basePath }/study/index.do?supplierName="+encodeURI(encodeURI(supplierName));
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
		str += '<a href="#" style="color:blue" onclick="editCourse(\''+rec.course_id+'\', \''+rec.supplier_id+'\');">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		str += '<a href="#" style="color:blue" onclick="viewCourse(\''+rec.course_id+'\', \''+rec.supplier_id+'\');">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.course_id+'\', \'0\', \''+rec.course_name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
			str += '<a href="#" style="color:red" onclick="addCourseRecom(\''+rec.course_id+'\')">添加推荐</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.course_id+'\', \'1\', \''+rec.course_name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		
		str += '<a href="#" style="color:blue" onclick="selCouAppList(\''+rec.course_id+'\')">查看报名</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		str += '<a href="#" style="color:blue" onclick="selCouCommList(\''+rec.course_id+'\')">查看评论</a>';
		return str;
			
	}
	
	function addCourseRecom(id){
		location.href = "${basePath }/study/addCourseRecomgoto.do?id="+id;
	}
	
	
	function selCouAppList(id){
		
		location.href = "${basePath }/study/courseApplygoto.do?courseId="+id;
	}
	
	function selCouCommList(id){
		
		location.href = "${basePath }/study/courseCommgoto.do?courseId="+id;
	}
	
	function editCourse(id,supplierId){
		
		location.href = "editCoursegoto.do?courseId="+id+"&supplierId="+supplierId;
	}
	
	function viewCourse(id,supplierId){
		
		location.href = "viewCoursegoto.do?courseId="+id+"&supplierId="+supplierId;
	}
	
	
	function setState(id, state, name) {
		
		var supplierName = $("input[name='supplierName']").val().trim();
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "课程 '"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/study/state.do?courseId='+id+'&state='+state,
					dataType:'json',
					type:'post',
					cache:false,
					success:function(data){
						if (data.code == '0') {
							location.href = "${basePath }/study/index.do?supplierName="+encodeURI(encodeURI(supplierName));
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
<table id="main" class="easyui-datagrid lines-bottom" title="课程列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'course_name',sortable:true">课程名称</th>
					<th data-options="field:'supplier_name'">课程类型</th>
					<th data-options="field:'name'">机构名称</th>
					
					<!-- <th data-options="field:'course_info'">课程简介</th> -->
					<!-- <th data-options="field:'base_num'">基础报名人数</th> -->
					<th data-options="field:'real_num'">实际报名人数</th>
					<th data-options="field:'course_desc'">年龄范围</th>
				<!-- 	<th data-options="field:'entry_fee_old'">报名费用</th>
					<th data-options="field:'entry_fee'">优惠报名费用</th> -->
					<th data-options="field:'comment_num'">评论总数</th>
					<th data-options="field:'comment_total'">评论总分</th>
					
					<!-- <th data-options="field:'apply_begin_date',width:130,align:'center',sortable:true">开始报名时间</th> -->
					<!-- <th data-options="field:'apply_end_date',width:130,align:'center',sortable:true">结束报名时间</th> -->
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">添加时间</th>
					<th data-options="field:'opt',width:300,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<td align="right" width=90 class="formtd">&nbsp;&nbsp;&nbsp;按机构名称查询：</td>
	<input class="easyui-textbox" type="text" style="width:200px" name="supplierName"  value="${supplierName }" data-options="required:true">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selListByName()">查询</a>  
	
</div>
</body>
</html>
