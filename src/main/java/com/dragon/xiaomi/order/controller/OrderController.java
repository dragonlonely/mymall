package com.dragon.xiaomi.order.controller;

import com.dragon.xiaomi.address.pojo.Address;
import com.dragon.xiaomi.address.service.AddressService;
import com.dragon.xiaomi.cart.pojo.Cart;
import com.dragon.xiaomi.cart.service.CartService;
import com.dragon.xiaomi.order.pojo.Order;
import com.dragon.xiaomi.order.pojo.OrderDetail;
import com.dragon.xiaomi.order.service.OrderService;
import com.dragon.xiaomi.user.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/19 16:40
 * @Classname OrderController
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger=Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;

    @RequestMapping("/account")
    public String account(HttpServletRequest request, Model model) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        List<Cart> carts = cartService.getCartById(user.getId());
        List<Address> addList=addressService.findByUserId(user.getId());

        model.addAttribute("carts",carts);
        model.addAttribute("addList",addList);
        logger.info("结算");
        return "/order";
    }

    @RequestMapping("/addorder")
    public String addOrder(HttpServletRequest request,Model model){
        //判断用户是否登录
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        List<Cart> carts = cartService.getCartById(user.getId());
        String _aid = request.getParameter("aid");
        int addid=Integer.parseInt(_aid);
        Order order=orderService.submitOrder(carts,addid,user.getId());
        model.addAttribute("order",order);
        logger.info("添加订单"+order.toString());
        return "/orderSuccess";
    }

    @RequestMapping("/getorderlist")
    public String getOrderList(HttpServletRequest request,Model model){
        //判断用户是否登录
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        List<Order> orders=orderService.findOrderByUserId(user.getId());
        model.addAttribute("orderList",orders);
        logger.info("获取订单列表"+orders.toString());
        return "/orderList";
    }

    @RequestMapping("/getorderdetail")
    public String getOrderDetail(HttpServletRequest request,Model model){
        String oid = request.getParameter("oid");
        List<OrderDetail> orderDetails=orderService.findByOid(oid);
        Order order=orderService.findByOrderid(oid);
        Address address=addressService.findByAid(order.getAid());
        model.addAttribute("orderDetails",orderDetails);
        model.addAttribute("order",order);
        model.addAttribute("address",address);
        logger.info("获取订单详情"+order.toString());
        return "/orderDetail";
    }

    @RequestMapping("/changestatus")
    public String changeStatus(HttpServletRequest request){
        String orderId = request.getParameter("oid");
        orderService.updateStatus(orderId,"4");
        logger.info("收到货后，改变状态，订单id为："+orderId);
        return "forward:getorderlist.action";
    }

}
