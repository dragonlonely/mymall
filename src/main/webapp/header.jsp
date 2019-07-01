 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/goods/getgoodstypelist.action",
			type:"GET",
			success:function(data){
				for(var i in data){//i是索引
					var a = $("<a href='${pageContext.request.contextPath}/goods/getgoodsbytypeid.action?typeId="+data[i].id+"'>"+data[i].name+"</a>");
					$("#goodsType").append(a);
					
				}
			},
			dataType:"json",
			error:function(){
				alert("失败");
			}
		})
	})
</script>
		
 <div id="top">
    	<div id="topdiv">
            <span>
                <a href="${pageContext.request.contextPath}/index.jsp" id="a_top" target="_blank">小米商城</a>
                <li>|</li>
                <a href="" id="a_top">小米商城移动版</a>
                <li>|</li>
                <a href="" id="a_top">问题反馈</a>
            </span>
            <span style="float:right">
           		<c:if test="${empty user}">
	                <a href="/login.jsp" id="a_top">登录</a>
	                <li>|</li>
	                <a href="/register.jsp" id="a_top">注册</a>
           		</c:if>
       			<c:if test="${not empty user}">
       				<a href="${pageContext.request.contextPath}/self_show.jsp" id="a_top">${user.username}</a>
       				<li>|</li>
       				<a href="${pageContext.request.contextPath}/user/logout.action" onclick="return confirm('确定要注销吗?')" id="a_top">注销</a>
       				<li>|</li>
       				<a href="${pageContext.request.contextPath}/order/getorderlist.action" id="a_top">我的订单</a>
       				<li>|</li>
       				<a href="${pageContext.request.contextPath}/address/getaddress.action" id="a_top">地址管理</a>
       			</c:if>
                <li>|</li>
                <a href="https://account.xiaomi.com/pass/serviceLogin?callback=https%3A%2F%2Forder.mi.com%2Flogin%2Fcallback%3Ffollowup%3Dhttps%253A%252F%252Forder.mi.com%252Fmessage%252Flist%26sign%3DMjEyY2NkOTg5NjAzZmMyNWUyMDBhNzE2MDczNWJkYzFkMTg1Yjc0MQ%2C%2C&sid=mi_eshop&_bannerBiz=mistore&_qrsize=180" id="a_top">消息通知</a>
                <a href="${pageContext.request.contextPath}/cart/getcart.action" id="shorpcar">购物车</a>
            </span>
        </div>
 </div>
<div id="second">
	    <a href="" id="seimg" style=" margin-top:23px;"><img id="logo" src="${pageContext.request.contextPath}/image/logo_top.png" width="55" height="54"/></a>
        <a href="" id="seimg" style=" margin-top:17px;"><img id="gif" src="${pageContext.request.contextPath}/image/yyymix.gif" width="180" height="66" /></a>
        <p id="goodsType">
			<!-- 根据ajax 回调函数 填写数据 到此id中 -->
        </p>
       <form class="form-inline pull-right" style="margin-top: 40px;margin-right: 10px;" action="${pageContext.request.contextPath }/goods/searchgoods.action" method="post">
		
		  <div class="form-group">
		    <%--<input type="hidden" name="method" value="searchgoods.action">--%>
		    <input type="text" class="form-control" style="width: 400px"  placeholder="搜索一下好东西..." name="goodsname">
		    
		  </div>
		  <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索</button>
	  </form>
</div>
