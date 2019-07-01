package com.dragon.xiaomi.order.service.impl;

import com.dragon.xiaomi.cart.pojo.Cart;
import com.dragon.xiaomi.goods.pojo.Goods;
import com.dragon.xiaomi.mapper.*;
import com.dragon.xiaomi.order.pojo.Order;
import com.dragon.xiaomi.order.pojo.OrderDetail;
import com.dragon.xiaomi.order.service.OrderService;
import com.dragon.xiaomi.utils.ActiveUtils;
import com.dragon.xiaomi.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/19 16:48
 * @Classname OrderServiceImpl
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public Order submitOrder(List<Cart> carts, int addid, int uid) {

        double totalmoney=0;
        for(Cart cart:carts){
            totalmoney+=cart.getMoney();
        }
        System.out.println(totalmoney);
        //创建订单对象，添加对象
        String orderid= ActiveUtils.createOrderId();
        Order order=new Order(orderid,uid,totalmoney,"1",new Date(),addid,null);
        orderMapper.add(order);

        //创建订单详情，添加订单详情
        for(Cart cart:carts){
            OrderDetail orderDetail=new OrderDetail(0,orderid,cart.getPid(),cart.getNum(),cart.getMoney(),null);
            orderMapper.addOrderDetail(orderDetail);
        }

        //清空购物车
        cartMapper.deleteByUserId(uid);

        return order;
    }


    public List<Order> findOrderByUserId(int id) {
        return orderMapper.findOrderByUserId(id);
    }


    public List<OrderDetail> findByOid(String oid) {
        List<OrderDetail> orderDetails = orderMapper.findByOid(oid);
        for(OrderDetail orderDetail:orderDetails){
            Goods goods = goodsMapper.findById(orderDetail.getPid());
            orderDetail.setGoods(goods);
        }
        return orderDetails;
    }


    public Order findByOrderid(String oid) {
        return orderMapper.findByOrderid(oid);
    }


    public void updateStatus(String orderId, String status) {
        orderMapper.updateStatus(orderId,status);
    }


    public List<OrderVo> getAllOrder() {
        //PageHelper.startPage(pageNum, pageSize);
        List<OrderVo> orderVoList=new ArrayList<OrderVo>();
        List<Order> orders=orderMapper.getOrderList();
        for (Order order : orders) {
            OrderVo orderVo=new OrderVo();
            orderVo.setId(order.getId());
            orderVo.setMoney(order.getMoney());
            orderVo.setStatus(order.getStatus());
            orderVo.setTime(order.getTime());
            System.out.println(order.getUid());
            String username=userMapper.findUserNameById(order.getUid());
            orderVo.setUsername(username);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }


    public List<OrderVo> findOrderByUseridOrStatus( String username, String status) {
        List<OrderVo> orderVoList=new ArrayList<OrderVo>();
        int userid = userMapper.getIdByUsername(username);
        List<Order> orders=orderMapper.getOrder(userid,status);
        for (Order order : orders) {
            OrderVo orderVo=new OrderVo();
            orderVo.setId(order.getId());
            orderVo.setMoney(order.getMoney());
            orderVo.setStatus(order.getStatus());
            orderVo.setTime(order.getTime());
            orderVo.setUsername(username);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }
}
