<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-11
  Time: 오후 2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>글목록</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>

<div id = "section">
    <table>
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성시간</th>
            <th>조회수</th>
        </tr>
        <c:forEach items="${boardList}" var="board">
            <tr>
                <td>${board.id}</td>
                <td><a href="/board?id=${board.id}">${board.boardTitle}</a></td>
                <td>${board.boardWriter}</td>
                <td>${board.boardCreatedDate}</td>
                <td>${board.boardHits}</td>
            </tr>
        </c:forEach>
    </table>




</div>




<%@include file="../component/footer.jsp"%>

</body>
</html>
