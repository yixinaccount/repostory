<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>afas</title>
	<script type="text/javascript">
	
	
	
	$(function(){
		var merchantId = $("input[name='merchantId']").val();
		
    $("#main").datagrid({
    	url:'selShopList.do',
    	queryParams:{
    		merchantId:merchantId
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
		//str += '<a href="#" style="color:blue" onclick="checkUserInfo(\''+rec.id+'\');">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		 str += '<a href="#" style="color:blue" onclick="viewShop(\''+rec.shop_id+'\');">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		/* if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.act_id+'\', \'0\', \''+rec.act_name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
			str += '<a href="#" style="color:red" onclick="addActRecom(\''+rec.act_id+'\')">添加推荐</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.act_id+'\', \'1\', \''+rec.act_name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} */
		/* str += '<a href="#" style="color:red" onclick="delUser('+rec.act_id+', \''+rec.act_name+'\')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;'; */
		/* str += '<a href="#" style="color:blue" onclick="selActAppList(\''+rec.act_id+'\')">查看报名</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		str += '<a href="#" style="color:blue" onclick="selMatSchList(\''+rec.act_id+'\')">查看场次</a>';  */
		return str;
			
	}
	
	
	
	
	function checkUserInfo(id){
		location.href = "${basePath }/user/checkUsergoto.do?checkId="+id;
	}
	
	
	
	function selActAppList(id){
		location.href = "${basePath }/activity/activityApplygoto.do?activityId="+id;
	}
	
	function selMatSchList(id){
		location.href = "${basePath }/activity/actSchedulegoto.do?activityId="+id;
	}
	
	function addActRecom(id){
		location.href = "${basePath }/activity/addActRecomgoto.do?activityId="+id;
	}
	
	
	function viewAct(id){
		location.href = "viewActgoto.do?id="+id;
	}
	
	
	function editAct(id){
		/* $('#aff').dialog({    
		    title: name,    
		    width: 800,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: str,    
		    modal: true   
		});    
		  $('#aff').dialog('refresh', str);   */
		  
		location.href = "editActgoto.do?id="+id;
	}
	
	
	function setState(id, state, name) {
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "活动 '"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/activity/state.do?id='+id+'&state='+state,
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

		var merchantId = $("input[name='merchantId']").val();
		
		location.href = "${basePath }/merchant/addMerShopgoto.do?merchantId="+merchantId;
				
	}
	

	function delUser(id, name) {
		$.messager.confirm('删除', "你确定要删除活动'"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/activity/del.do?id='+id,
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
<input value="${merchantId}" type="hidden" name="merchantId">

<table id="main" class="easyui-datagrid lines-bottom" title="门店列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,method:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>

			<thead data-options="frozen:true">
				<tr>
					<!-- <th data-options="field:'act_id'">ID</th> -->
					<th data-options="field:'shop_name',sortable:true">门店名称</th>
					<th data-options="field:'short_name',sortable:true">门店简称</th>
					<!-- <th data-options="field:'merchant_shortname'">商家简称</th>
					<th data-options="field:'merchantTypeName'">商家类型</th>
					<th data-options="field:'merchar_desc',width:200">商家简介</th> -->
					<th data-options="field:'shop_address',width:300">门店地址</th>
					
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					
					<!-- <th data-options="field:'act_info'">活动简介</th> -->
					
					<!-- <th data-options="field:'act_detail'">活动详情</th> -->
					<!-- <th data-options="field:'act_address'">活动地点</th>
					<th data-options="field:'act_Age_range'">年龄范围</th>
					<th data-options="field:'real_num',width:100">实际报名人数</th>
					<th data-options="field:'entry_fee_old'">报名费用</th>
					
					
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">添加时间</th> -->
					<th data-options="field:'opt',width:200,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="openAddDialog()">添加门店</a>
</div>
</body>
</html>
