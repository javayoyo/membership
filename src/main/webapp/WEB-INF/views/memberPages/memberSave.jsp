<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-09
  Time: 오후 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>


</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id = "section">

    <form action="/member/save" method="post"  name="saveForm">
        <input type="text" name="memberEmail" placeholder="이메일을 입력하세요" id="member-email" onblur="email_check()"> <br>
        <p id="email-check-result"></p>
        <input type="text" name="memberPassword" placeholder="비밀번호를 입력하세요" id="member-password" onblur="password_check()"> <br>
        <p id="password-check-result"></p>
        <input type="text" name="memberName" placeholder="이름을 입력하세요" id="member-name" onblur="name_check()"> <br>
        <p id="name-check-result"></p>
        <input type="text" name="memberMobile" placeholder="전화번호를 입력하세요" id="member-mobile" onblur="mobile_check()"> <br>
        <p id="mobile-check-result"></p>

        <input type="button" value="회원가입" onclick="save_check()">
    </form>
</div>

<%@include file="../component/footer.jsp"%>

</body>
<script>
    const email_check = () => {
        const email = document.getElementById("member-email").value;
        const result = document.getElementById("email-check-result");

        $.ajax({
            type: "post",
            url: "/member/email-check",
            data: {
                "memberEmail": email
            },

            success: function () {

                result.innerHTML = "사용가능한 이메일입니다.";
                result.style.color = "green";

            },
            error: function (err) {
                console.log(err);

                if(err.status == "409") {
                    result.innerHTML = "이미 사용 중인 이메일 입니다.";
                    result.style.color = "red";



                }else if(err.status == "404"){
                    result.innerHTML = "이메일을 입력해주세요.";
                    result.style.color = "red";


                }

            }

        });
    }

    const mobile_check = () => {
        const mobile = document.getElementById("member-mobile").value;
        const result = document.getElementById("mobile-check-result");
        console.log(mobile);
        const exp = /^\d{3}-\d{4}-\d{4}$/;
        if(mobile.match(exp)) {
            result.innerHTML = "전화번호 입력 형식에 일치합니다.";
            result.style.color = "green";

        }else {
            result.innerHTML = "000-0000-0000 형식으로 입력해주세요.";
            result.style.color = "red";


        }

    }
    // 영어소문자. 숫자 필수의 5-10글자 비밀번호
    const password_check = () => {
        const password = document.getElementById("member-password").value;
        const result = document.getElementById("password-check-result");
        console.log(password);
        const exp =/^(?=.*[a-z])(?=.*\d)[a-z\d]{5,10}$/;
        if(password.match(exp)) {
            result.innerHTML = "안전한 형식의 비밀번호 입니다.";
            result.style.color = "green";

        }else {
            result.innerHTML = "영어소문자,숫자 필수포함 5~10글자 내로 입력해주세요.";
            result.style.color = "red";


        }

    }

    const name_check = () => {
        const name = document.getElementById("member-name").value;
        const result = document.getElementById("name-check-result");
        console.log(name);
        const exp = /^(?=.*[ㄱ-ㅎ가-힣])[ㄱ-ㅎ가-힣]{1,10}$/;
        if(name.match(exp)) {
            result.innerHTML = "멋진 이름이네요.";
            result.style.color = "green";

        } else {
            result.innerHTML = "한글 이름 10글자 내로 입력해주세요.";
            result.style.color = "red";



        }
    }

    const save_check = () => {
        // const saveForm = document.getElementById("save-form");
        // const email = document.getElementById("member-email");
        // const password = document.getElementById("member-password").value;
        // const name = document.getElementById("member-name").value;
        // const mobile = document.getElementById("member-mobile").value;


        const result1 = document.getElementById("email-check-result");
        const result2 = document.getElementById("mobile-check-result");
        const result3 = document.getElementById("password-check-result");
        const result4 = document.getElementById("name-check-result");


        if(saveForm.memberEmail.value.length== 0 || result1.style.color === "red"){
            alert("이메일 형식에 맞지 않습니다.");
            saveForm.memberEmail.focus();
            return false;
        } else if(saveForm.memberPassword.value.length== 0 || result2.style.color === "red") {
            alert("비밀번호 형식에 맞지 않습니다.");
            saveForm.memberPassword.focus();
            return false;

        }else if(saveForm.memberName.value.length== 0 || result3.style.color === "red" ) {
            alert("이름 형식에 맞지 않습니다.");
            saveForm.memberName.focus();
            return false; }
        else if(saveForm.memberMobile.value.length== 0 || result4.style.color === "red"){
            alert("전화번호 형식에 맞지 않습니다.");
            saveForm.memberMobile.focus();
            return false;
        }else {
            alert("회원가입이 완료되었습니다.");
            document.saveForm.submit();
            return true;
        }
    }

    // const save_check = () => {
    //     const email = document.getElementById("member-email");
    //     const password = document.getElementById("member-password");
    //     const name = document.getElementById("member-name");
    //     const mobile = document.getElementById("member-mobile");
    //
    // if(!email_check()) {
    //     email.focus();
    //     return false;
    // }else if (!password_check()){
    //     password.focus();
    //     return false;
    // }else if (!name_check()){
    //     name.focus();
    //     return false;
    // }else if(!mobile_check()){
    //     mobile.focus();
    //     return false;
    // }else {
    //     return true;
    // }
    //
    // }


</script>
</html>