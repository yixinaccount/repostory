<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>提问管理</title>
	<script type="text/javascript">
	
	$(function(){
		var questionName = $("input[name='questionName']").val().trim();
		//var sign = $("input[name='sign']").val();
		//alert(matchId);
    $("#main").datagrid({
    	url:'listRearExchange.do',
    	queryParams:{
    		questionName:questionName
    		//sign:sign
    		}
    });//参数
})

function selListByName(){
	var questionName = $("input[name='questionName']").val().trim();
	location.href = "${basePath }/rearExchange/indexRearExchange.do?questionName="+encodeURI(encodeURI(questionName));
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
		str += '<a href="#" style="color:blue" onclick="viewQuestion(\''+rec.bbs_id+'\');">查看问题</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		if (rec.state == '1') {
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.bbs_id+'\', \'0\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.bbs_id+'\', \'1\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		
		str += '<a href="#" style="color:blue" onclick="selQuestionReply(\''+rec.bbs_id+'\')">查看回复</a>';
		return str;
			
	}
	
	
	
	
	function selQuestionReply(id){
		
		location.href = "${basePath }/study/courseApplygoto.do?courseId="+id;
	}
	
	
	
	function viewQuestion(id){
		
		location.href = "${basePath}/rearExchange/viewQuestiongoto.do?bbsId="+id;
	}
	
	
	function setState(id, state) {
		
		var questionName = $("input[name='questionName']").val().trim();
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		$.messager.confirm('状态', "你确定要" + str + "该问题吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/rearExchange/updateQuestionState.do?bbsId='+id+'&state='+state,
					dataType:'json',
					type:'post',
					cache:false,
					success:function(data){
						if (data.code == '0') {
							location.href = "${basePath }/rearExchange/indexRearExchange.do?questionName="+encodeURI(encodeURI(questionName));
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
<table id="main" class="easyui-datagrid lines-bottom" title="提问列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'username',sortable:true">提问人姓名</th>
					
					<th data-options="field:'bbs_content',width:130,">问题内容</th>
					<th data-options="field:'answer_num'">回复数量</th>
					<th data-options="field:'thumbs_up_num'">点赞数量</th>
					<th data-options="field:'click_num'">浏览数量</th>
					
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'create_date',width:130,align:'center',sortable:true">提问时间</th>
					<th data-options="field:'opt',width:300,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<td align="right" width=90 class="formtd">&nbsp;&nbsp;&nbsp;按问题内容查询：</td>
	<input class="easyui-textbox" type="text" style="width:200px" name="questionName"  value="${questionName }" data-options="required:true">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selListByName()">查询</a>  
	
</div>
</body>
</html>
