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
        $(document).ready(function(){
            loadGoods();
        })
        //连接servlet 获取 数据
        function loadGoods(){
            $.ajax({
                url:"${pageContext.request.contextPath}/goods/getgoodslist.action",
                type:"get",
                dataType:"json",
                success:function(data){
                    showMsg(data);
                },
                error:function(XMLHttpRequest,textStatus,errorThrown){
                    alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
                }
            });
        }
        //显示用户信息
        function showMsg(data){
            //var list = JSON.parse(data);
            var list=data;
            $("#tb_list").html("<tr class='tr_head'><td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>描述</td><td>删除操作</td><td>修改操作</td></tr>");
            var i = 1;
            for(var g in list){
                //声明 tr  td  对象
                var tr = $("<tr></tr>");
                var td1 = $("<td>"+(i++)+"</td>");
                var td2 = $("<td>"+list[g].name+"</td>");
                var td3 = $("<td>"+list[g].price+"</td>");
                var td4 = $("<td>"+list[g].pubdate+"</td>");
                var td5 = $("<td>"+list[g].typeName+"</td>");
                var td6 = $("<td>"+list[g].intro+"</td>");
                var td7 = $("<td><a href='javascript:delGoods("+list[g].id+")' class='btn btn-primary btn-xs'>删除</a></td>");
                var td8 = $("<td><a href='javascript:updateGoods("+list[g].id+")' class='btn btn-primary btn-xs'>修改</a></td>");

                //将td 添加到tr中
                tr.append(td1);
                tr.append(td2);
                tr.append(td3);
                tr.append(td4);
                tr.append(td5);
                tr.append(td6);
                tr.append(td7);
                tr.append(td8);
                $("#tb_list").append(tr);
            }
        }
        //删除用户
        function delGoods(id){
            if(confirm("确认要删除吗?")){
                $.ajax({
                    url:"${pageContext.request.contextPath}/goods/deletegoods.action?id="+id,
                    method:"get",
                    success:function(data){
                        loadGoods();
                    },
                    error:function(XMLHttpRequest,textStatus,errorThrown){
                        alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
                    }
                })
            }
        }
        //条件查询
        $(function(){
            //给查询按钮 添加 点击事件
            $("#search").click(function(){
                var name = $("input[name='name']").val();
                var pubdate = $("input[name='pubdate']").var();

                //使用ajax 进行异步交互
                $.ajax({
                    url:"${pageContext.request.contextPath}/goods/searchgoodslist.action?name="+name+"&pubdate="+pubdate,
                    method:"post",
                    success:function(data){
                        if(data==0){
                            //alert("未找到指定内容");
                            $("#tb_list").html("<tr class='tr_head'><td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>操作</td></tr>");
                            //$("input[name='username']").val("");
                            //$("input[name='gender']").removeAttr("checked");
                        }else{
                            showMsg(data);
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
<div class="row" style="width: 100%;">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">会员列表</div>
			<div class="panel-body">
				<!-- 条件查询 -->
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="name" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>上架时间</span>
							<input type="text" readonly="readonly"  name="pubdate" class="form-control" onclick="setday(this)">
						</div>
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<!-- 列表显示 -->
				<table id="tb_list" class="table table-striped table-hover table-bordered">

				</table>


			</div>
		</div>
	</div>
</div>
</body>
</html>