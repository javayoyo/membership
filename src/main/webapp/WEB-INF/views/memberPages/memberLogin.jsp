<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-10
  Time: 오전 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id = "section">

    <form action="/member/login" method="post" >
        <input type="text" name="memberEmail" placeholder="이메일을 입력하세요" id="member-email"> <br>
        <input type="text" name="memberPassword" placeholder="비밀번호를 입력하세요"> <br>

        <input type="submit" value="로그인">
    </form>


</div>
<%@include file="../component/footer.jsp"%>


</body>
</html>