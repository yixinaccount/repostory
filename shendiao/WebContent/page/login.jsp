<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">


if (window != top){
	top.location.href = "${basePath}";
}
	function setFocus() {
		document.getElementById('username').focus();
	}
	function cancel() {
		document.forms[0].reset();
	}

	document.onkeydown = function(e) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			mySubmit("login");
		}
	}

	function mySubmit(url) {
		var name = document.getElementById("username").value;
		var re = /^[^'‘’ 　]+$/;
		if (!re.test(name)) {
			alert("用户名或密码错误！");
			return false;
		}
		document.forms[0].method = "post";
		document.forms[0].action = url;
		document.forms[0].submit();
	}
</script>

</head>
<body onLoad="javascript:setFocus();">

	
	<form action="login" method="post">

		<div id="login_box">

			<div id="mess">
				<font color="red"> ${login_message } </font>
			</div>
			<div id="login_text">
			用户名：
				<input id="username" name="username" type="text" class="gray_border" />
				<br /> <br /> &nbsp;&nbsp;&nbsp;&nbsp;密码：<input name="password" type="password"
					class="gray_border" />
			</div>
			<div id="login_button">
				<a href="#" onclick="mySubmit('login')">
				
				<input type="button" value="登录" style="margin-left: 100px;margin-top: 10px"/>
				<!-- <img
					src="upload/img/1.jpg" width="70" border="0" style="padding-left: 60px"
					height="58" /> --></a>

			</div>


		</div>
	</form>

</body>
</html>