package com.dragon.xiaomi.mapper;
/**
 * @Classname OrderMapper
 * @Description TODO
 * @Date 2018/10/19 16:48
 * @Created by Administrator
 */


import com.dragon.xiaomi.order.pojo.Order;
import com.dragon.xiaomi.order.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/19 16:48
 * @Classname OrderMapper
 */
public interface OrderMapper {

    void add(Order order);

    void addOrderDetail(OrderDetail orderDetail);

    List<Order> findOrderByUserId(Integer id);

    List<OrderDetail> findByOid(String oid);

    Order findByOrderid(String oid);

    void updateStatus(@Param("orderId") String orderId,@Param("status") String status);


    List<Order> getOrderList();

    List<Order> getOrder(@Param("userid") int userid,@Param("status") String status);
}
