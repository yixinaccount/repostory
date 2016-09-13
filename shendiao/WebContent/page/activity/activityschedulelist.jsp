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
        	url:'listSchedule.do',
        	queryParams:{
        		activityId:activityId
        		}
        });//参数
    })
	
    
	function addActSch(){
		
		var activityId = $("#activityId").val();
		
		location.href = "${basePath}/activity/addActSchgoto.do?activityId="+activityId; 
		
		
			/* $('#dlg').dialog('open').dialog('setTitle', '密码修改');
			$('#fm').form('clear'); */
		
		
		
		
	}
	
	
	function selSpeSch(){
		
		var activityId = $("#activityId").val();
		
		location.href = "${basePath}/activity/actSpeSchgoto.do?activityId="+activityId;
		
	}
	
	
	function selCommonSch(){
		
		var activityId = $("#activityId").val();
		
		location.href = "${basePath}/activity/actCommonSchgoto.do?activityId="+activityId;
		
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
			str += '<a href="#" style="color:red" onclick="setState(\''+rec.act_id+'\', \'0\')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
			str += '<a href="#" style="color:blue" onclick="addSpeSch(\''+rec.act_id+'\',\''+rec.id+'\',\''+rec.schedule_date+'\')">添加特价票</a>';
		} else {
			str += '<a href="#" style="color:green" onclick="setState(\''+rec.act_id+'\', \'1\')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;'
		}
		  
		
		 
		return str;
			
	}
	
	
	
	function addSpeSch(actId,scheduleId,scheduleDate){
		
		/* alert(scheduleId)
		alert(typeof(scheduleId)) */
		location.href = "${basePath }/activity/addSpeSchgoto.do?actId="+actId+"&scheduleId="+scheduleId+"&scheduleDate="+scheduleDate;
		
	}
	
	
	
	
	function setState(id, state) {
	
		var str = '';
		if (state == '0') {
			str = '禁用';
		} else {
			str = '启用';
		}
		
		$.messager.confirm('状态', "你确定要" + str +"该时间吗？", function(r){
			if (r){
				$.ajax({
					url: '${basePath}/activity/stateSchedule.do?id='+id+'&state='+state,
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
	
	
	function saveuser(){
		/* if($('#newpw').val()!=$('#renewpw').val()){
			$.messager.alert("", "新密码两次输入不一致，请修改", "warning");
			return;
		} */
		var activityId = $("#activityId").val();
		var scheduleDate = $("#scheduleDate").val();
		
		
		 var dataForm = new FormData();
	 	  dataForm.append('activityId',activityId);
		  dataForm.append('scheduleDate',scheduleDate);
		  alert(activityId)
		  
		  /*  $("#fm").form("submit", {
                url: "${basePath}/activity/addActSch.do",
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                	alert(result)
                    if (result.code == "0") {
                        $.messager.alert("提示信息", "操作成功");
                        $("#dlg").dialog("close");
                        $("#main").datagrid("load");
                    }
                    else {
                        $.messager.alert("提示信息", "操作失败");
                    }
                }
            }); 
		   */
		  
		   $.ajax({
	 	        url: '${basePath}/activity/addActSch.do',
	 	        type: 'POST',
	 	        data: dataForm,
	 	        cache: false,
	 	      	timeout: 60000,
	 	      	async:true,
	 	      	dataType:'json',
	 	        contentType: false,
	 	        //不可缺
	 	        processData: false,
	 	        //不可缺	  
	 	       success:function(data){
					if (data.code == '0') {
						
						//$.messager.alert('消息', data.msg);
						 $.messager.alert('消息', data.msg,'操作成功',function () {  
							 location.href = "${basePath }/activity/listActSpeSch.do?activityId="+data.actId;
						 });
						
					} else {
						$.messager.alert('消息', data.msg);
					}
				}
			}) 
		   
		  
		/* var fmurl = "${basePath}/activity/addActSch.do";
		$('#fm').form('submit', {
			url : fmurl,
			data: dataForm,
			dataType : "json",
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close');
					$.messager.show({ // show error message  
						title : '成功',
						msg : result.msg
					});
				

				} else {
					$.messager.alert("提示",  result.msg, "error");
					return;

				}

			}
		}); */
	}

	
	function back(){
		location.href = "${basePath }/activity/index.do";
	}
	
	
	</script>
  </head>
  
<body>
<input value="${activityId}" type="hidden" id="activityId">
<table id="main" class="easyui-datagrid lines-bottom" title="活动时间列表" style="width:100%"
		data-options="rownumbers:true,singleSelect:true,querysmethod:'get',toolbar:'#tb',pagination:true,pageNumber:1,
				fitColumns:false,striped:true,fit:true,border:false,pageSize:10,pageList:[5,10,15],remoteSort:true">
	<thead>
			<thead data-options="frozen:true">
				<tr>
					<!-- <th data-options="field:'act_id'">活动id</th> -->
					<th data-options="field:'schedule_date',sortable:true">活动时间</th>
					<th data-options="field:'dt_create',width:130,align:'center',sortable:true">创建时间</th>
					<th data-options="field:'state',width:60,align:'center',sortable:true,formatter:showState">状态</th>
					<th data-options="field:'opt',width:220,align:'center',formatter:showOpt">操作</th>
				</tr>
			</thead>
	</thead>
</table>
<div id="tb" style="padding:2px 5px;">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addActSch()">添加活动时间</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="selSpeSch()">查看特价票</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="selCommonSch()">查看常规票</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="back()">返回活动列表</a>
</div>


		<!-- <form id="fm" method="post">
		
		<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: auto; padding: 0px 20px"
		data-options="closed:true,iconCls:'icon-add',shadow :true,modal : true,
		buttons:[{text:'确认',iconCls:'icon-ok',handler:function(){submit();}},
		{text:'取消',iconCls:'icon-cancel',handler:function(){$('#dlg').dialog('close');}}
		]">

			<div class="fitem">

				<label>请选择活动时间：</label> 
				<input class="easyui-datetimebox" style="width:200px " id="scheduleDate" data-options="required:true"></input>
				<input id="renewpw" name="renewpw"
					class="easyui-validatebox" data-options="required:true" />
			</div>
		</div>
		</form> -->
		

<%-- 		
		<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px;"
       closed="true" buttons="#dlg-buttons">
       <div class="ftitle">
           信息编辑
       </div>
       <form id="fm" method="post">
       <div class="fitem">
    
          <label>请选择活动时间：</label> 
				<input class="easyui-datetimebox" style="width:200px " name="scheduleDate" data-options="required:true"></input>
			
       </div>
       <input value="${activityId}" type="hidden" name="activityId">
       </form>
   </div> 
		
		
		
		
		
		
		<div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveuser()" iconcls="icon-save">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
            iconcls="icon-cancel">取消</a>
    </div>  --%>



</body>
</html>
