<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>小米网后台主页-会员信息页面</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function deleteUser(id){
            var res = confirm("是否删除");
            if(res==true){
                window.location.href="${pageContext.request.contextPath }/admin/deleteuser.action?id="+id;
            }
        }
        //条件查询
        $(function(){
            //给查询按钮 添加 点击事件
            $("#search").click(function(){
                var username = $("input[name='username']").val();
                var genders = $("input[name='gender']");
                var gender = "";
                for(var i=0;i<genders.length;i++){
                    if(genders[i].checked){
                        gender += genders[i].value;
                    }
                }
                //使用ajax 进行异步交互
                $.ajax({
                    url:"${pageContext.request.contextPath}/admin/searchuserlist.action?username="+username+"&gender="+gender,
                    method:"post",
                    success:function(data){
                        if(data==0){
                            //alert("未找到指定内容");
                            $("#tb_list").html("<tr class='tr_head'><td>编号</td><td>邮箱</td><td>姓名</td><td>性别</td><td>类别</td><td>操作</td></tr>");
                            //$("input[name='username']").val("");
                            $("input[name='gender']").removeAttr("checked");
                        }else{
                            //showMsg(data);
                            var list = data;
                            $("#tb_list").html("<tr class='tr_head'><td>编号</td><td>邮箱</td><td>姓名</td><td>性别</td><td>类别</td><td>操作</td></tr>");
                            var i = 1;
                            for (var u in list) {
                                //声明 tr  td  对象
                                var tr = $("<tr></tr>");
                                var td1 = $("<td>" + (i++) + "</td>");
                                var td2 = $("<td>" + list[u].email + "</td>");
                                var td3 = $("<td>" + list[u].username + "</td>");
                                var td4 = $("<td>" + list[u].gender + "</td>");
                                var td5 = $("<td>" + (list[u].role == 0 ? "管理员" : "会员") + "</td>");
                                var td6 = $("<td><a href='javascript:delUser(" + list[u].id + ")' class='btn btn-primary btn-xs'>删除</a></td>");

                                //将td 添加到tr中
                                tr.append(td1);
                                tr.append(td2);
                                tr.append(td3);
                                tr.append(td4);
                                tr.append(td5);
                                tr.append(td6);
                                $("#tb_list").append(tr);
                            }
                        }
                    },
                    error:function(XMLHttpRequest,textStatus,errorThrown){
                        alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
                    }
                })
            })
        })
    </script>
</head>
<body>

<div class="row" style="width:98%;margin-left: 1%;">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                会员列表
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                        <div class="form-group form-inline">
                            <span>用户姓名</span>
                            <input type="text" name="username" class="form-control">
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <div class="form-group form-inline">
                            <span>性别</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="radio-inline">
                                <input type="radio" name="gender" value="男"> 男&nbsp;&nbsp;&nbsp;&nbsp;
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <button type="button" class="btn btn-primary" id="search"><span
                                class="glyphicon glyphicon-search"></span></button>
                    </div>
                </div>
                <div style="height: 400px;overflow: scroll;">
                    <table id="tb_list" class="table table-striped table-hover table-bordered">
                        <tr>
                            <td>编号</td>
                            <td>邮箱</td>
                            <td>姓名</td>
                            <td>性别</td>
                            <td>类别</td>
                            <td>操作</td>
                            <c:forEach items="${userList}" var="user" varStatus="i">
                        <tr>
                            <td>${i.count}</td>
                            <td>${user.email}</td>
                            <td>${user.username}</td>
                            <td>${user.gender}</td>
                            <td>${user.role==0?"管理员":"会员"}</td>
                            <td>
                                <%--<button onclick="updateUser(${user.id})" class="btn btn-danger btn-sm">修改</button>&nbsp;&nbsp;--%>
                                <button onclick="deleteUser(${user.id})" class="btn btn-danger btn-sm">删除</button>&nbsp;&nbsp;
                                <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal${user.id}">修改</button>&nbsp;&nbsp;
                                <!-- 弹出模态框 -->

                                <div class="modal fade" tabindex="-1" role="dialog" id="myModal${user.id}">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span>&times;</span>
                                                </button>
                                                <h4 class="modal-title">修改用户</h4>
                                            </div>
                                            <form action="${pageContext.request.contextPath }/admin/updateuser.action" method="post" class="form-horizontal" style="padding:10px">
                                                <div class="motal-body">
                                                    <div class="form-group">
                                                        <input type="hidden" name="id" value="${user.id}">
                                                        <label class="col-sm-2 control-label">邮箱</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" name="email" class="form-control" value="${user.email}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">用户名</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" name="username" class="form-control" value="${user.username}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">用户角色</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" name="role" class="form-control" value="${user.role==0?'管理员':'会员'}">
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="motal-footer text-center">
                                                    <button type="submit" class="btn btn-primary">修改</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>