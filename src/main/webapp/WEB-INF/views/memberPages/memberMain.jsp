<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-09
  Time: 오전 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>마이페이지</title>
  <link rel="stylesheet" href="/resources/css/main.css">
  <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>


</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>

<div id="section">
  ${sessionScope.loginEmail} 님 마이페이지 입니다 <br>



  <button onclick="update()">회원정보수정</button>
  <button onclick="member_delete()">회원탈퇴</button>

</div>



<%@include file="../component/footer.jsp"%>
</body>
<script>

  const update = () => {
    location.href = "/member/update";

  }
  const member_delete = () => {
    location.href = "/member/delete";
  }



</script>
</html>