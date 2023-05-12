<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-12
  Time: 오전 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글수정</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>

<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
    <form action="/board/update" method="post" name="updateForm">
        <input type="text" name="id" value="${board.id}" readonly> <br>
        <input type="text" name="boardTitle" value="${board.boardTitle}"> <br>
        <input type="text" name="boardWriter" value="${board.boardWriter}" readonly> <br>
        <textarea name="boardContents" cols="30" rows="10">${board.boardContents}</textarea> <br>
        <button type="submit">수정</button>
    </form>
</div>

<%@include file="../component/footer.jsp"%>


</body>
</html>
