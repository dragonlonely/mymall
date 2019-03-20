<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<title>商品分类</title>
	<script type="text/javascript">

        function deleteGoodsType(id){
            var res = confirm("是否删除");
            if(res==true){
                window.location.href="${pageContext.request.contextPath }/goods/deleteGoodsType.action?id="+id;
            }
        }

	</script>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				商品类型
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品等级</span>
							<input type="text" name="level" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="goodsname" class="form-control">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
				<table id="tb_list" class="table table-striped table-hover table-bordered">
					<tr>
						<td>序号</td><td>类型</td><td>等级</td><td>所属类型</td><td>操作</td>
					</tr>
					<c:forEach items="${goodsTypeList}" var="gtype" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td>${gtype.name}</td>
						<td>${gtype.level}</td>
						<td>${gtype.parentName}</td>
						<td>
							<button onclick="deleteGoodsType(${gtype.id})" class="btn btn-danger btn-sm">删除</button>&nbsp;&nbsp;
							<button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal${gtype.id}">修改</button>&nbsp;&nbsp;
							<!-- 弹出模态框 -->

							<div class="modal fade" tabindex="-1" role="dialog" id="myModal${gtype.id}">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span>&times;</span>
											</button>
											<h4 class="modal-title">修改用户</h4>
										</div>
										<form action="${pageContext.request.contextPath }/goods/updategoodstype.action" method="post" class="form-horizontal" style="padding:10px">
											<div class="motal-body">
												<div class="form-group">
													<input type="hidden" name="id" value="${gtype.id}">
													<label class="col-sm-2 control-label">类型名</label>
													<div class="col-sm-10">
														<input type="text" name="name" class="form-control" value="${gtype.name}">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">用户名</label>
													<div class="col-sm-10">
														<input type="text" name="level" class="form-control" value="${gtype.level}">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">用户角色</label>
													<div class="col-sm-10">
														<input type="text" name="parent" class="form-control" value="${gtype.parentName}">
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