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
		var articleType = $("#articleType").combobox('getValue');
		
		//var sign = $("input[name='sign']").val();
		//alert(matchId);
    $("#main").datagrid({
    	url:'listRearMethod.do',
    	queryParams:{
    		articleType:articleType
    		}
    });//参数
})



		
	$(document).ready(function () {
		
		$("#articleType").combobox({
			onChange: function (n,o) {
			var articleType1 = $("#articleType").combobox('getValue');

				 $("#main").datagrid({
				    	url:'listRearMethod.do',
				    	queryParams:{
				    		articleType:articleType1
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
		str += '<a href="#" style="color:blue" onclick="editArticle(\''+rec.article_id+'\');">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		str += '<a href="#" style="color:blue" onclick="viewArticle(\''+rec.article_id+'\');">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.article_id+'\', \'0\', \''+rec.article_name+'\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.article_id+'\', \'1\', \''+rec.article_name+'\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		return str;
			
	}
	
	
	function viewArticle(id){
		
		location.href = "viewArticlegoto.do?id="+id;
	}
	
	function editArticle(id){
		
		location.href = "editArticlegoto.do?id="+id;
	}
	
	
	function setState(id, state, name) {
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "文章'"+name+"' 吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/rearMethod/updateArticleState.do?id='+id+'&state='+state,
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

	function addArticle() {
			
		location.href = "${basePath }/rearMethod/addArticlegoto.do";
	}
	

	</script>
  </head>
  
<body >
<table id="main" class="easyui-datagrid lines-bottom" title="育儿有方列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'article_name',sortable:true">文章名称</th>
					<th data-options="field:'article_type_name'">文章类型</th>
					<th data-options="field:'article_info'">文章简介</th>
					<th data-options="field:'click_num'">点击数</th>
					<th data-options="field:'thumbs_up_num'">点赞数</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">添加时间</th>
					<th data-options="field:'opt',width:300,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addArticle()">添加文章</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按文章类型查询：
				<select id="articleType" class="easyui-combobox" panelHeight="auto" style="width:100px" name="articleType" data-options="required:true" >
					
					<c:forEach items="${articleTypeList }" var="n">
					<option value="${n.article_type}">${n.article_name}</option>
					</c:forEach>

					
				</select>
	
	
	
		
	
</div>
</body>
</html>
