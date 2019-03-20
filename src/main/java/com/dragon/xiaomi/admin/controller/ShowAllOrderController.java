package com.dragon.xiaomi.admin.controller;

import com.dragon.xiaomi.order.service.OrderService;
import com.dragon.xiaomi.user.service.UserService;
import com.dragon.xiaomi.vo.OrderVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * @Date 2019/1/6 17:46
 * @Classname ShowAllOrderController
 */
@Controller
@RequestMapping("/order")
public class ShowAllOrderController {
    private static final Logger logger=Logger.getLogger(ShowAllOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getallorder")
    public String getAllOrder(HttpServletRequest request) {
        List<OrderVo> orderList = orderService.getAllOrder();
        request.getSession().setAttribute("orderList", orderList);
        logger.info("展示所有订单!==>"+orderList.toString());
        return "/admin/showAllOrder";
    }

    @RequestMapping("/sendOrder")
    public String sendOrder(HttpServletRequest request) {
        String orderId = request.getParameter("oid");
        orderService.updateStatus(orderId, "3");
        logger.info("发货成功!==>"+orderId);
        return "forward:getallorder.action";
    }

    @RequestMapping("/searchOrder")
    public String searchOrder(HttpServletRequest request){
        String username = request.getParameter("username");
        String status = request.getParameter("status");

        List<OrderVo> orderList=orderService.findOrderByUseridOrStatus(username,status);
        request.getSession().setAttribute("orderList", orderList);
        logger.info("查询订单!==>"+orderList.toString());
        return "/admin/showAllOrder";
    }

}
