<%@ page import="com.dragon.xiaomi.user.pojo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>个人中心-收货地址页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login2.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function deleteAddr(id) {
		var res = confirm("是否删除");
		if (res == true) {
			window.location.href = "${pageContext.request.contextPath}/address/deleteAddress.action?id=" + id;
		}
	}
	function defaultAddr(id) {
		var res = confirm("是否设为默认");
		if (res == true) {
			window.location.href = "${pageContext.request.contextPath}/address/defaultAddress.action?id=" + id;

		}
	}
</script>
</head>
<body>

	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
	%>

	<%@ include file="header.jsp"%>
	<!--网站中间内容开始-->
	<div id="dingdanxiangqing_body">
		<div id="dingdanxiangqing_body_big">
			<div id="big_left">
				<p style="font-size: 18px; margin-top: 15px">订单中心</p>
				<a id="big_left_a" href="${pageContext.request.contextPath}/order/getorderlist.action">&nbsp;&nbsp;我的订单</a><br />
				<a id="big_left_a" href="">&nbsp;&nbsp;意外保</a><br /> <a
					id="big_left_a" href="">&nbsp;&nbsp;团购订单</a><br /> <a
					id="big_left_a" href="">&nbsp;&nbsp;评价晒单</a><br />
				<p style="font-size: 18px; margin-top: 15px" href="">个人中心</p>
				<a id="big_left_a" href="${pageContext.request.contextPath}/self_show.jsp">&nbsp;&nbsp;个人资料</a><br /> <a
					id="big_left_a" href="">&nbsp;&nbsp;消息通知</a><br /> <a
					id="big_left_a" href="">&nbsp;&nbsp;优惠券</a><br /> <a
					id="big_left_a" href="${pageContext.request.contextPath}/self_info.jsp">&nbsp;&nbsp;收货地址</a><br />
			</div>
			<div id="big_right" style="height: 500px; overflow: scroll;">

				<div style="margin: 0 20px;">
					<h3>个人资料</h3>
					<hr>
					<table class="table table-striped table-hover table-bordered">
						<tr>

							<td>用户名:</td>
							<td>${user.username }</td>
						</tr>
						<tr>

							<td>邮箱</td>
							<td>${user.email }</td>
						</tr>
						<tr>

							<td>性别</td>
							<td>${user.gender }</td>
						</tr>
						<tr>

							<td>用户状态</td>
							<td>${user.flag==0?"未激活":user.flag==1?"正常":"失效" }</td>
						</tr>
						<tr>

							<td>用户角色</td>
							<td>${user.role==0?"管理员":"普通用户" }</td>
						</tr>
						<tr>

							<td colspan="2"><input type="button" value="修改密码"
								class="btn btn-success" data-toggle="modal" data-target="#myModal">
								${msg }	
							</td>

						</tr>

					</table>
					<div class="modal fade" tabindex="-1" role="dialog"
						id="myModal">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span>&times;</span>
									</button>
									<h4 class="modal-title">修改密码</h4>
								</div>
								<form action="${pageContext.request.contextPath}/user/modifypassword.action" method="post"
									class="form-horizontal" style="padding: 10px">
									<div class="motal-body">
										<div class="form-group">
											<label class="col-sm-2 control-label">原始密码</label>
											<div class="col-sm-10">

												<input type="text" name="password" class="form-control"
													value="">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">新密码</label>
											<div class="col-sm-10">
												<input type="text" name="newpassword" class="form-control"
													value="">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">确认密码</label>
											<div class="col-sm-10">
												<input type="text" name="renewpassword" class="form-control"
													value="">
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
				</div>
				<br>

			</div>
		</div>
	</div>

	<!-- 底部 -->
	<%@ include file="footer.jsp"%>

</body>
</html>