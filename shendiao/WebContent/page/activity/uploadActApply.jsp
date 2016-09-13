<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<title>上传报名活动</title>
	<script type="text/javascript">


//上传报名活动信息
function uploadTestPaper(){
	if($("#excelFile").val()==""){
		showMsg("提示","请先选择文件！");
		return false;
	}else{
		$("#uploadTestPaperForm").submit(function () {  
	        $("#uploadTestPaperForm").ajaxSubmit({
	            type: "post",
	            async:false,
	            url: "serverTestSetting/answerUpload",
	            success: function (body) {
	            		    /*  alert(body.data); */
	            			if(typeof(body.data)=='undefined'){
	            				showMsg("提示", body.desc, 3);
	            			}else{
	            				$("#upload").attr({"href":body.data}).html("下载");
	    	            		showMsg("提示", body.desc, 3);
	            			}
	            	
	            	$("#uploadTestPaperForm").resetForm(); 
	            	(".cur_list_2").html();
	            },
	            error: function () {
	            	showMsg("提示", "访问异常，稍后重试!");    
	            }
	        });
	        return false;
	    });
	}
	 
    $("#uploadTestPaperForm").submit();
    
}

</script>
</head>
<body>

	<div>
		<form id="uploadActApply">
			<div>
				 
	        	 选择上传报名文件:
	       	   <input type="file" name="excelFile" id="excelFile">
	       	    <a id="upload" style="font-size:14px;"></a>
	       	 
		        <div> 
		        	<input type="button" name="button" id="cur_btn" onclick="uploadTestPaper();" value="提交">
		        </div>
			</div>
		</form>
	</div>

</body>
</html>