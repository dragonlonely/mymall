<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login2.css">
    <script type="text/javascript">

        $(function () {
            $("#username").change(function () {
                //使用ajax 做username 的异步验证 checkUsername?username=xxxx
                $.get("${pageContext.request.contextPath}/user/checkusername.action?username=" + this.value + "&num=" + Math.random(), function (data) {
                    //alert(data);
                    if (data != "0") {
                        $("#usernameMsg").html("用户名已经存在").css("color", "red");
                        $("#registerBtn").attr("disabled", true);
                    } else {
                        $("#usernameMsg").html("用户名可用").css("color", "green");
                        $("#registerBtn").removeAttr("disabled");
                    }
                })
            });


            $("#form1").validate({
                rules: {
                    username: "required",
                    password: {
                        required: true,
                        rangelength: [6, 12]
                    },
                    repassword: {
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    }


                },
                messages: {
                    username: null,
                    password: {
                        required: "密码不能为空",
                        rangelength: "密码长度{0}-{1}位"
                    },
                    repassword: {
                        equalTo: "两次密码不一致"
                    },
                    email: {
                        required: "邮箱不能空",
                        email: "邮箱格式不正确"
                    }
                }

            });

            //发送短信验证码
            $("#validCode").click(function(){
                var phone = $("input[name='phone']").val();
                if(phone==""){
                    alert("手机号不能为空");
                }

                //使用ajax 进行异步交互
                $.ajax({
                    url:"${pageContext.request.contextPath}/sms/msg.action?phone="+phone,
                    method:"post",
                    success:function(data){
                        if(data.length>0){
                            alert("验证码发送成功");
                        }
                    }
                })
            });

        });

    </script>
    <title>注册</title>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
            <div class="right fr">
                <a href="index.jsp" target="_black">小米商城</a>
            </div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="center-block" style="margin-top: 30px;">
            <form id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/user/register.action"
                  method="post">

                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" id="username" name="username" class="form-control col-sm-10"
                               placeholder="Username"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span class="help-block " id="usernameMsg"></span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" name="password" id="password"
                               class="form-control col-sm-10" placeholder="Password"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger">
                            <label id="password-error" class="error" for="password"></label>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" name="repassword" class="form-control col-sm-10"
                               placeholder="Password Again"/>
                    </div>
                    <div class="col-sm-2">
                        <%--<p class="text-danger"><label id="repassword-error" class="error" for="repassword"></label></p>--%>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="email" name="email" id="email" class="form-control col-sm-10"
                               placeholder="Email"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><label id="email-error" class="error" for="email"></label></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" name="phone" id="phone" class="form-control col-sm-10"
                               placeholder="Phone"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><label id="phone-error" class="error" for="phone"></label></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机验证码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" name="validCode" class="form-control col-sm-10" placeholder="请输入验证码"/>&nbsp;&nbsp;
                        <%--<input type="button" class="btn-nobordbig c-normal js-sendvcode-btn" value="获取手机验证码"--%>
                               <%--onclick="sendMessage()"/>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">--%>
                        <input type="button" value="获取手机验证码" class="btn btn-primary" id="validCode"></input>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-8" style="width: 40%">
                        <label class="radio-inline"> <input type="radio"
                                                            name="gender" value="男" checked="checked"> 男
                        </label> <label class="radio-inline"> <input type="radio"
                                                                     name="gender" value="女"> 女
                    </label>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock" class="help-block ">你是帅哥 还是美女</span></p>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-sm-7 col-sm-push-2">
                        <input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg"
                               style="width: 200px;"/> &nbsp; &nbsp;
                        <input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"/>
                    </div>
                </div>
                <div class="text-center" style="color:red">${registerMsg}</div>
            </form>

        </div>
    </div>
</div>

</body>
</html>