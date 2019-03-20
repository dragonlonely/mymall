package com.dragon.xiaomi.order.service;
/**
 * @Classname OrderService
 * @Description TODO
 * @Date 2018/10/19 16:47
 * @Created by Administrator
 */


import com.dragon.xiaomi.cart.pojo.Cart;
import com.dragon.xiaomi.order.pojo.Order;
import com.dragon.xiaomi.order.pojo.OrderDetail;
import com.dragon.xiaomi.vo.OrderVo;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/19 16:47
 * @Classname OrderService
 */
public interface OrderService {

    Order submitOrder(List<Cart> carts, int addid, int uid);

    List<Order> findOrderByUserId(int id);

    List<OrderDetail> findByOid(String oid);

    Order findByOrderid(String oid);

    void updateStatus(String orderId, String status);

    List<OrderVo> getAllOrder();

    List<OrderVo> findOrderByUseridOrStatus(String username, String status);
}
