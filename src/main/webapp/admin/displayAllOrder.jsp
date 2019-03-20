<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>后台 订单列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript">
        function sendOrder(id){
            var res = confirm("确认发货");
            if(res==true){
                window.location.href="${pageContext.request.contextPath }/order/sendOrder.action?oid="+id;
            }
        }
        $(function(){
            $("#search").click(function(){
                var username = $("input[name='username']").val();
                var status = $("select[name='status'] option:selected").val();
                //alert(username+"..."+status);
                location.href="${pageContext.request.contextPath}/order/searchOrder.action?username="+username+"&status="+status+"&num="+Math.random();
            })
        })
	</script>
</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;margin-top: 5px;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				订单列表
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>用户姓名</span>
							<input type="text" name="username" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>订单状态</span>
							<select name="status" class="form-control">
								<option value="">----请选择----</option>
								<option value="1" ${status==1?"selected":"" }>未支付</option>
								<option value="2" ${status==2?"selected":"" }>已支付,待发货</option>
								<option value="3" ${status==3?"selected":"" }>已发货,待收货</option>
								<option value="4" ${status==4?"selected":"" }>完成订单</option>
							</select>
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>

				<table id="tb_list" class="table table-striped table-hover table-bordered table-condensed">
					<tr>
						<td>序号</td>
						<td>订单编号</td>
						<td>总金额</td>
						<td>订单状态</td>
						<td>订单时间</td>
						<td>用户姓名</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${pageBean.data}" var="order" varStatus="i">
						<tr>
							<td>${i.count}</td>
							<td>${order.id}</td>
							<td>${order.money}</td>
							<td>
								<c:if test="${order.status eq 1}">
									未支付
								</c:if>
								<c:if test="${order.status eq 2}">
									已支付,待发货
								</c:if>
								<c:if test="${order.status eq 3}">
									已发货,待收货
								</c:if>
								<c:if test="${order.status eq 4}">
									订单完成
								</c:if>
							</td>
							<td>${order.time}</td>
							<td>${order.username}</td>
							<td>
								<c:if test="${order.status eq 2}">
									<button type="button" class="btn btn-danger btn-sm" onclick="sendOrder('${order.id}')">发货</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>
				<nav aria-label="..." class="text-center">
					<ul class="pagination">

						<c:if test="${pageBean.pageNum<=1 }">
							<li class="disabled"><span aria-hidden="true">«</span></li>
						</c:if>
						<c:if test="${pageBean.pageNum>1 }">
							<li><a href="${pageContext.request.contextPath }/order/getallorder.action?pageNum=${pageBean.pageNum-1}&pageSize=${pageBean.pageSize}&username=${username}&status=${status}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
						</c:if>

						<c:forEach var="pn" begin="${pageBean.startPage }" end="${pageBean.endPage }" step="1">
							<c:if test="${pn==pageBean.pageNum }">
								<li class="active"><a href="#">${pn }<span class="sr-only">(current)</span></a></li>
							</c:if>
							<c:if test="${pn!=pageBean.pageNum }">
								<li ><a href="${pageContext.request.contextPath }/order/getallorder.action?pageNum=${pn }&pageSize=${pageBean.pageSize}&username=${username}&status=${status}">${pn }</a></li>
							</c:if>
						</c:forEach>

						<c:if test="${pageBean.pageNum==pageBean.totalPage }">
							<li class="disabled"><span aria-hidden="true">»</span></li>
						</c:if>
						<c:if test="${pageBean.pageNum<pageBean.totalPage }">
							<li><a href="${pageContext.request.contextPath }/order/getallorder.action?pageNum=${pageBean.pageNum+1}&pageSize=${pageBean.pageSize}&username=${username}&status=${status}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						</c:if>

					</ul>
				</nav>

			</div>
		</div>
	</div>
</div>
</body>
</html>