<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-10
  Time: 오전 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>관리자회원목록</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>
<body>
<%@include file="../component/header.jsp" %>
<%@include file="../component/nav.jsp" %>

<div id="section">
    <table>
        <tr>
            <th>id</th>
            <th>email</th>
            <th>name</th>
            <th>mobile</th>
            <th>회원삭제</th>
        </tr>
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td>${member.id}</td>
                <td>${member.memberEmail}</td>
                <td>${member.memberName}</td>
                <td>${member.memberMobile}</td>
                <td>
                    <button onclick="member_delete('${member.id}')">회원삭제</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="../component/footer.jsp" %>
</body>
<script>

    const member_delete = () => {

        location.href = "/member/delete";
    }
</script>
</html>