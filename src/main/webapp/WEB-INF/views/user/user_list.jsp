<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원관리 프로그램</title>

    <style>

        .header {
            height: 10vh;

        }

        .content_area {
            height: 80vh;
        }

        .main-footer {
            height: 5vh;
        }

        .user_table > thead > tr > td {
            text-align: center;
        }
    </style>
</head>
<body>


<jsp:include page="../include_page/header.jsp"></jsp:include>

<div class="content_area">
    <div style="width: 100%;">
        <div class="col-xl-12" style="margin-bottom: 30px;">
            <input class="form-control" placeholder="아이디" id="id" style="width: 150px; display: inline-block">
            <input class="form-control" placeholder="이름" id="name"style="width: 150px; display: inline-block">
            <input class="form-control" placeholder="나이" id="age"style="width: 150px; display: inline-block">
            <input class="form-control" placeholder="암호" id="passwd" style="width: 150px; display: inline-block">

            <button class="btn btn-primary" onclick="user_create()">회원 생성</button>
        </div>


        <table class="table user_table">
            <colgroup>
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
            </colgroup>
            <thead>
            <tr>
                <td>아이디</td>
                <td>이름</td>
                <td>나이</td>
                <td>암호</td>
                <td>관리</td>
            </tr>

            </thead>
            <tbody>

            <!------------->


            <c:forEach var="user" items="${user_list}">
                <tr align="center"  class="odd">
                    <td class="td_id">
                            ${user.id}
                    </td>
                    <td class="td_name">
                            ${user.name}
                    </td>
                    <td class="td_age">
                            ${user.age}
                    </td>
                    <td class="td_passwd">
                            ${user.passwd}
                    </td>

                    <td>
                        <button class="btn"
                                style="font-size:15px;background-color:#ff0000;color:#ffffff" onclick="user_delete('${user.id}')">삭제
                        </button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include_page/footer.jsp"></jsp:include>

<jsp:include page="../include_page/user_modal.jsp"></jsp:include>

</body>
<script>
    function user_delete(user_id) {

        if(confirm(user_id+"회원님을 삭제 하시겠습니까?")){

            $.ajax({
                type: 'DELETE',
                url : '/user/delete/'+user_id,
                async	: false,
                global	: false,
                success:function () {
                    alert("삭제완료");
                    window.location.reload();
                },error:function () {
                    alert("삭제오류");
                }

            })

        }else{

        }
    }


    function user_create() {

        let user={
            id:$("#id").val(),
            name:$("#name").val(),
            age:$("#age").val(),
            passwd:$("#passwd").val()
        }
        console.log(user)

        $.ajax({
            type: 'POST',
            url : '/user/create/',
            async	: false,
            global	: false,
            data:user,
            success:function () {
                alert("생성완료");
                window.location.reload();
            },error:function () {
                alert("생성오류");
            }

        })
    }
</script>
</html>