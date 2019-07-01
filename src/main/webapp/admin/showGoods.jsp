<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script src="../js/DatePicker.js"></script>
<title>商品列表</title>

	<script type="text/javascript">

        function deleteGoods(id){
            var res = confirm("是否删除");
            if(res==true){
                window.location.href="${pageContext.request.contextPath }/goods/deleteGoods.action?id="+id;
            }
        }

        //条件查询
        $(function(){
            //给查询按钮 添加 点击事件
            $("#search").click(function(){
                var name = $("input[name='name']").val();
                var pubdate = $("input[name='pubdate']").val();

                //使用ajax 进行异步交互
                $.ajax({
                    url:"${pageContext.request.contextPath}/goods/searchgoodslist.action?name="+name+"&pubdate="+pubdate,
                    method:"post",
                    success:function(data){
                        if(data==0){
                            //alert("未找到指定内容");
                            $("#tb_list").html("<tr class='tr_head'><td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>描述</td><td>操作</td></tr>");

                        }else{
                            //showMsg(data);
                            var list = data;
                            $("#tb_list").html("<tr class='tr_head'><td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>描述</td><td>操作</td></tr>");
                            var i = 1;
                            for (var g in list) {
                                //声明 tr  td  对象
                                var tr = $("<tr></tr>");
                                var td1 = $("<td>" + (i++) + "</td>");
                                var td2 = $("<td>"+list[g].name+"</td>");
                                var td3 = $("<td>"+list[g].price+"</td>");
                                var td4 = $("<td>"+list[g].pubdate+"</td>");
                                var td5 = $("<td>"+list[g].typeName+"</td>");
                                var td6 = $("<td>"+list[g].intro+"</td>");

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
				商品列表
			</div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath }/goods/searchgoodslist.action" method="post">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="name" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>上架时间</span><!--  readonly="readonly"   onclick="setday(this)" -->
							<input type="text" name="pubdate" class="form-control">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<%--<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>--%>
							<button type="submit" class="btn btn-primary">查询</button>
					</div>

				</div>
				</form>
				<div style="height: 400px;overflow: scroll;">
					<table id="tb_list" class="table table-striped table-hover table-bordered">
						<tr>
							<td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>描述</td><td>操作</td>
						</tr>
						<c:forEach items="${goodsList}" var="goods" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${goods.name}</td>
								<td>${goods.price}</td>
								<td>${goods.pubdate}</td>
								<td>${goods.typeName}</td>
								<td>${goods.intro}</td>
								<td style="width: 130px">
									<button onclick="deleteGoods(${goods.id})" class="btn btn-danger btn-sm">删除</button>&nbsp;&nbsp;
									<button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal${goods.id}">修改</button>&nbsp;&nbsp;
									<!-- 弹出模态框 -->

									<div class="modal fade" tabindex="-1" role="dialog" id="myModal${goods.id}">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span>&times;</span>
													</button>
													<h4 class="modal-title">修改商品</h4>
												</div>
												<form action="${pageContext.request.contextPath }/goods/updategoods.action" method="post" enctype="multipart/form-data" class="form-horizontal" style="padding:10px">
													<div class="motal-body">
														<div class="form-group">
															<input type="hidden" name="id" value="${goods.id}">
															<label class="col-sm-2 control-label">商品名</label>
															<div class="col-sm-10">
																<input type="text" name="name" class="form-control" value="${goods.name}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">价格</label>
															<div class="col-sm-10">
																<input type="text" name="price" class="form-control" value="${goods.price}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">日期</label>
															<div class="col-sm-10">
																<input type="text" readonly="readonly" name="pubdate" class="form-control" value="${goods.pubdate}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">商品类名</label>
															<div class="col-sm-10">
																<input type="text" name="typeName" class="form-control" value="${goods.typeName}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">商品星级</label>
															<div class="col-sm-10">
																<input type="text" name="star" class="form-control">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">商品图片</label>
															<div class="col-sm-10">
																<input type="file" name="file" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">商品描述</label>
															<div class="col-sm-10">
																<input type="text" name="intro" class="form-control" value="${goods.intro}">
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