package com.dragon.xiaomi.cart.service;
/**
 * @Classname CartService
 * @Description TODO
 * @Date 2018/10/19 14:10
 * @Created by Administrator
 */


import com.dragon.xiaomi.cart.pojo.Cart;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/19 14:10
 * @Classname CartService
 */
public interface CartService {

    Cart findByUserIdAndGoodsId(int id, int pid);

    void add(Cart cart);

    void update(Cart cart);

    List<Cart> getCartById(int id);

    void remove(int id, int goodsId);

    void clear(int id);
}
