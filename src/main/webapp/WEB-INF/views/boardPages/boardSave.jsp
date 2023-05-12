<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-09
  Time: 오후 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>글작성</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id = "section">
    <form action="/board/save" method="post" name="saveForm" enctype="multipart/form-data">
        <input type="text" name="boardTitle" placeholder="제목을 입력하세요"> <br>
        <input type="text" name="boardWriter" value="${sessionScope.loginEmail}"> <br>

        <textarea name="boardContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea> <br>
        <input type="file" name="boardFile" multiple> <br>
        <input type="submit" value="작성">
    </form>

</div>




<%@include file="../component/footer.jsp"%>

</body>
</html>
