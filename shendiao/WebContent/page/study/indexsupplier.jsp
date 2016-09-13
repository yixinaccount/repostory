<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>机构管理</title>
	<script type="text/javascript">
	
	$(function(){
		var supplierType = $("#supplierType").combobox('getValue');
		
		//var sign = $("input[name='sign']").val();
		//alert(matchId);
    $("#main").datagrid({
    	url:'listSupplier.do',
    	queryParams:{
    		supplierType:supplierType
    		}
    });//参数
})



		
	$(document).ready(function () {
		
		$("#supplierType").combobox({
			onChange: function (n,o) {
			var supplierType1 = $("#supplierType").combobox('getValue');

				 $("#main").datagrid({
				    	url:'listSupplier.do',
				    	queryParams:{
				    		supplierType:supplierType1
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
	

	function showOpt(val, rec) {
		var str = '';
		str += '<a href="#" style="color:blue" onclick="editSupplier(\''+rec.supplier_id+'\');">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		str += '<a href="#" style="color:blue" onclick="viewSupplier(\''+rec.supplier_id+'\');">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.supplier_id+'\', \'0\', \''+rec.name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
			str += '<a href="#" style="color:blue" onclick="addCourse(\''+rec.supplier_id+'\',\''+rec.type+'\')">添加课程</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.supplier_id+'\', \'1\', \''+rec.name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		str += '<a href="#" style="color:blue" onclick="selSupCommList(\''+rec.supplier_id+'\')">查看评论</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		str += '<a href="#" style="color:blue" onclick="selSupOrderList(\''+rec.supplier_id+'\')">查看预约</a>';
		return str;
			
	}
	
	function addCourse(id,type){
		location.href = "${basePath }/study/addCoursegoto.do?supplierId="+id+"&type="+type;
	}
	
	function viewSupplier(id){
		
		location.href = "viewSuppliergoto.do?id="+id;
	}
	
	function editSupplier(id){
		
		location.href = "editSuppliergoto.do?id="+id;
	}
	
	function selSupCommList(id){
		location.href = "${basePath }/supplier/supplierCommgoto.do?supplierId="+id;
	}
	
	function selSupOrderList(id){
		location.href = "${basePath }/supplier/supAppointgoto.do?supplierId="+id;
	}
	
	
	function setState(id, state, name) {
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "机构'"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/supplier/state.do?id='+id+'&state='+state,
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

	function addSupplier() {
			
		location.href = "${basePath }/supplier/addSuppliergoto.do";
	}
	

	</script>
  </head>
  
<body >
<table id="main" class="easyui-datagrid lines-bottom" title="机构列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'name',sortable:true">机构名称</th>
					<th data-options="field:'type_info'">对象说明</th>
					<th data-options="field:'telephone'">机构电话</th>
					<!-- <th data-options="field:'longitude'">经度</th>
					<th data-options="field:'latitude'">纬度</th> -->
					<th data-options="field:'address'">机构地址</th>
					<th data-options="field:'supplier_name'">机构类型</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">添加时间</th>
					<th data-options="field:'opt',width:300,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addSupplier()">添加机构</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按机构类型查询：
				<select id="supplierType" class="easyui-combobox" panelHeight="auto" style="width:100px" name="supplierType" data-options="required:true" >
					
					<!-- <option value="0">全部</option>
					<option value="1">幼儿早教</option>
					<option value="2">少儿外语</option>
					<option value="3">才艺表演</option>
					<option value="4">学科辅导</option>
					<option value="5">运动健身</option>
					<option value="6">少儿音乐</option>
					<option value="7">其他</option> -->
					
					<c:forEach items="${supplierTypeList }" var="n">
					<option value="${n.supplier_type}">${n.supplier_name}</option>
					</c:forEach>

					
				</select>
	
	
	
		
	
</div>
</body>
</html>
