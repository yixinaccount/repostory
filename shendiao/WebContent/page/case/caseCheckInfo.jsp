<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/common/easyui.jsp"></jsp:include>
<jsp:include page="/common/ueditor.jsp"></jsp:include>
<script type="text/javascript">


	var searchParam = {};//查试卷用
	var searchParam2 = {};//获取以保存的回答用
	var examPaperUserInfo = {};
	var thisUploadDiv = '';//用于存图片path的时候获取div名
	var examUploadImg = [];//存放图片path
	var paperReqState = [];//用来检验每到题是否已完成

	$(function() {

		var taskId = $("#taskId").val();

		$.ajax({
			url : '${basePath }/office/selTaskQuesList.do?taskId=' + taskId,
			type : 'POST',
			cache : false,
			timeout : 60000,
			async : true,
			dataType : 'json',
			contentType : false,
			//不可缺
			processData : false,
			//不可缺	  
			success : function(data) {
				if (data.code == '0') {
				
					getExamData(data);


				} else {

					$.messager.alert('消息', data.msg);
				}
			}
		})

	})

	//获取试卷信息并开始渲染
	function getExamData(data) {
	

		var quesData = data.taskQuesList;
		
		var answData = data.taskQuesItemList;
	
		for (var i = 0; i < quesData.length; i++) {
			
			setOneQuestion(quesData[i], answData);
		}

		var dataForm = new FormData();

		var taskId = $("#taskId").val();
		var userId = $("#userId").val();
		var userCaseId = $("#userCaseId").val();

		dataForm.append('taskId', taskId);
		dataForm.append('userId', userId);
		dataForm.append('userCaseId', userCaseId);

		$.ajax({
			url : '${basePath }/office/selUserSurveyAnswer.do',
			type : 'POST',
			data : dataForm,
			cache : false,
			timeout : 60000,
			async : true,
			dataType : 'json',
			contentType : false,
			//不可缺
			processData : false,
			//不可缺	  
			success : function(data) {
				if (data.code == '0') {
					
					setSurveyAnswer(data);

				} else {

					$.messager.alert('消息', data.msg);
				}
			}
		})

	}

	// 加载已做的题目
	function setSurveyAnswer(data) {
		
		if (data.surveyAnswerList.length == 0) {
			return false;
		} else {
			var sData = data.surveyAnswerList;
			
			for (var i = 0; i < sData.length; i++) {
				var qDivName = "ques_" + sData[i]['question_id'];//id="ques_2"
				var textDivName = "text_" + sData[i]['question_id'];//id="text_2"
				var imgDivName = "imgDiv_" + sData[i]['question_id'];//id="imgDiv_2"

				var theSelect = sData[i]['select_answer'];

				var theText = sData[i]['text_answer'];
				var theImg = sData[i]['photo_answer'];
				var qId = sData[i]['question_id'];
				//加载选择题
				if (theSelect != '') {
					var selArr = theSelect.split(",");
					for (var j = 0; j < selArr.length; j++) {
						$("#item_" + selArr[j]).attr('checked', 'checked');
					}
				}
				//加载填空题
				$("#text_" + qId).val(theText);

				
				//加载图片
				if (theImg != '') {

					var imgArr = theImg.split(",");
					for (var j = 0; j < imgArr.length; j++) {
						
						var imgUrl = "${basePath }" + imgArr[j];
						
						// var imgId="imgDiv_"+qId+"_"+(j+1);
						var oneImg = "<img style=\"width: 200px;height: 400px;margin-left:10px;margin-top:10px;\"  src=\""+imgUrl+"\"  data-imglink=\""+imgArr[j]+"\">";
						$("#imgDiv_" + qId).append(oneImg);
						//加图片数据存到全局数组中
						var picInfo = {
							"imgDiv" : imgDivName,
							"picPath" : imgArr[j]
						};
						// var picInfo={"imgDiv":imgDivName,"picPath":imgArr[j],'imgId':imgId};
						examUploadImg.push(picInfo);

					}
				
				}

			

			}

		}
		
	}

	//渲染每一道题目
	function setOneQuestion(oneQues, answData) {

		var sort = oneQues['sort'];//排序

		var task_id = oneQues['task_id'];//案件id
		var paper_id = oneQues['paper_id'];//调研问卷id
		var question_id = oneQues['question_id'];//试题id
		var question_title = oneQues['question_title'];//标题
		// var question_tag=oneQues['question_tag'];//题目分类-拓展
		// var question_type=oneQues['question_type'];//题目类型-备用
		var select_type = oneQues['select_type'];//选择题类型 -1无 1多选 2多选
		var text_num = oneQues['text_num'];//填空题类型 -1无 其他数字表示至少多少字
		var photo_num = oneQues['photo_num'];//是否需要照片 -1不需要 其他数字表示最少照片数
		// var remark=oneQues['remark'];//备注
		var create_date = oneQues['create_date'];
		examPaperUserInfo['paper_id'] = paper_id;

		storeQuesRequire(question_id, select_type, text_num, photo_num);//储存题目需求

		var str = '';
		str += " <div class=\"question\" id=\"ques_"+question_id+"\">";
		str += "    <p class=\"q_title\">";
		str += "    " + sort + "、" + question_title + "";
		str += "    </p>";
		str = getSelectStr(str, question_id, select_type, answData);//加载选择题
		str = getTextStr(str, question_id, text_num);//加载输入框
		str = getImgStr(str, question_id, photo_num);//加载输入框
		str += "</div>";
		$("#paper").append(str);

	}

	//渲染单多选框
	function getSelectStr(str, question_id, select_type, answData) {
		switch (select_type) {
		case -1:
			break;
		case 1://单选
			str += "<div class=\"choose\" id=\"selDiv_"+question_id+"\">";
			for (var i = 0; i < answData.length; i++) {
				if (answData[i]['question_id'] == question_id) {
					var item_id = answData[i]['item_id'];
					var question_id = answData[i]['question_id'];
					var item_content = answData[i]['item_content'];
					str += "<input type=\"radio\" id=\"item_"+item_id+"\" name=\"ques_"+question_id+"\" value=\""+item_id+"\" disabled=\"disabled\">";
					str += "<label class=\"choose_label\" for=\"item_"+item_id+"\">"
							+ item_content + "</label>";
					str += "<br>";
				}
			}
			str += "</div>";
			break;
		case 2://多选
			str += "<div class=\"choose\" id=\"selDiv_"+question_id+"\">";
			for (var i = 0; i < answData.length; i++) {
				if (answData[i]['question_id'] == question_id) {
					var item_id = answData[i]['item_id'];
					var question_id = answData[i]['question_id'];
					var item_content = answData[i]['item_content'];
					str += "<input type=\"checkbox\" id=\"item_"+item_id+"\" name=\"ques_"+question_id+"\" value=\""+item_id+"\" disabled=\"disabled\">";
					str += "<label class=\"choose_label\" for=\"item_"+item_id+"\">"
							+ item_content + "</label>";
					str += "<br>";
				}
			}
			str += "</div>";
			break;
		}

		return str;
	}

	//渲染textarea
	function getTextStr(str, question_id, text_num) {
		if (text_num == -1) {
			return str;
		} else {
			str += "        <div class=\"wordCount\" id=\"textDiv_"+question_id+"\" style=\"margin-top:10px;\">";
			str += "            <textarea  style=\"width: 400px;height: 200px;\"  id=\"text_"+question_id+"\" class=\"txt\" placeholder=\"请大人批注...\" disabled=\"disabled\"></textarea>";
			
			str += "        </div>";
			return str;
		}
	}

	//渲染图片上传框
	function getImgStr(str, question_id, photo_num) {
		if (photo_num == -1) {
			return str;
		} else {
			str += "<div class=\"imgUpload\" >";
			str += "	<div class=\"img_div\" id=\"imgDiv_"+question_id+"\" >";
			str += "		</div><div class=\"add_div fileInputContainer\">";
			str += "	</div>";
			str += "</div>";
			return str;
		}

	}

	//保存题目的要求参数
	function storeQuesRequire(question_id, select_type, text_num, photo_num) {
		var oneReq = {
			"question_id" : question_id,
			"select_type" : select_type,
			"text_num" : text_num,
			"photo_num" : photo_num
		};
		paperReqState.push(oneReq);
	}

	
	//通过
	function checkCasePass() {

		
		var checkCaseId = $("#checkCaseId").val();

		$.ajax({
			url : '${basePath }/task/checkCasePass.do?checkCaseId=' + checkCaseId,
			type : 'POST',
			cache : false,
			timeout : 60000,
			async : true,
			dataType : 'json',
			contentType : false,
			//不可缺
			processData : false,
			//不可缺	  
			success : function(data) {
				if (data.code == '0') {

					//$.messager.alert('消息', data.msg);
					$.messager.alert('消息', data.msg, '操作成功', function() {
						location.href = "${basePath }/task/caseCheckIndex.do";
					});

				} else {
					$.messager.alert('消息', data.msg);
				}
			}
		})

	}

	//不通过
	function checkCaseNoPass() {
	 
		$('#mydialog').show();
		$('#mydialog')
				.dialog(
						{
							collapsible : true,
							minimizable : true,
							maximizable : true,
							buttons : [
									{
										text : '确定',
										iconCls : 'icon-ok',
										handler : function() {

											var xiaoxi = $(
													"textarea[name='xiaoxi']")
													.val();

											/* alert(xiaoxi);
											
											alert('提交数据'); */

											var checkCaseId = $("#checkCaseId").val();

											$.ajax({
														url : '${basePath }/task/checkCaseNoPass.do?checkCaseId='
																+ checkCaseId
																+ '&checkContent='
																+encodeURI(encodeURI(xiaoxi)),
														type : 'POST',
														cache : false,
														timeout : 60000,
														async : true,
														dataType : 'json',
														contentType : false,
														
														//不可缺
														processData : false,
														//不可缺	  
														success : function(data) {
															if (data.code == '0') {

																//$.messager.alert('消息', data.msg);
																$.messager
																		.alert(
																				'消息',
																				data.msg,
																				'操作成功',
																				function() {
																					location.href = "${basePath }/task/caseCheckIndex.do";
																				});

															} else {
																$.messager
																		.alert(
																				'消息',
																				data.msg);

																$('#mydialog')
																		.dialog(
																				'close');
															}
														}
													})

										}
									}

									, {
										text : '取消',
										iconCls : 'icon-cancel',
										handler : function() {
											$('#mydialog').dialog('close');
										}
									} ]
						});
	}
</script>
<style type="text/css">
.formtd {
	font-size: 12px;
}
</style>
</head>



<body>

	<form method="post" id="editForm">


		<input value="${checkCaseId }" type="hidden" id="checkCaseId">
		<input value="${userId }" type="hidden" id="userId"> 
		
		<input value="${userCaseId }" type="hidden" id="userCaseId">
		<input value="${taskId}" type="hidden" id="taskId">

		<div class="big_div">


			<section id="paper"> </section>

			<div style="margin-top: 20px">
				<div class="btn2" style="display: inline-block;">

					<a href="#" style="text-align: center;" class="easyui-linkbutton"
						id="saveBtn" iconCls="icon-save" onclick="checkCasePass()">审核通过</a>

				</div>

				<div class="btn2" style="display: inline-block;">

					<a href="#" style="text-align: center;" class="easyui-linkbutton"
						id="saveBtn" iconCls="icon-save" onclick="checkCaseNoPass()">审核不通过</a>

				</div>
			</div>

		</div>


		<div id="mydialog"
			style="display: none; left:200px; top:100px; padding: 5px; width: 500px; height: 300px;"
			title="审核不通过">
			<input id="txRoleID" type="hidden" runat="server" value="0" /> <label
				class="lbInfo">不通过原因：</label><br>
			<textarea rows="aa" cols="bb" name="xiaoxi"
				style="width: 80%; margin-left:10%; height: 200px"></textarea>
					<!-- <input id="txRolename" type="text" class="easyui-validatebox" required="true" runat="server" /><br />
					<label class="lbInfo"> </label><input type="submit" onserverclick="saveRole" value="保存" runat="server" /> -->
			<label id="lbmsg" runat="server"></label>
		</div>


	</form>
</body>
</html>
