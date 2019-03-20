package com.dragon.xiaomi.cart.controller;

import com.dragon.xiaomi.cart.pojo.Cart;
import com.dragon.xiaomi.cart.service.CartService;
import com.dragon.xiaomi.goods.pojo.Goods;
import com.dragon.xiaomi.goods.service.GoodsService;
import com.dragon.xiaomi.user.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/19 13:06
 * @Classname CartController
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    private static final Logger logger=Logger.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addcart")
    public String addCart(HttpServletRequest request){
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        String _goodsId = request.getParameter("goodsId");
        int goodsId=Integer.parseInt(_goodsId);

        Cart cart=cartService.findByUserIdAndGoodsId(user.getId(),goodsId);

        System.out.println("===>"+cart);
        //获取商品
        Goods goods=goodsService.findById(goodsId);

        if(cart==null){
            //向购物车中加入数据
            cartService.add(new Cart(user.getId(),goodsId,1,goods.getPrice(),null));
        }else{
            //更新
            //更新数量
            cart.setNum(cart.getNum()+1);
            cart.setMoney(cart.getNum()*goods.getPrice());
            cartService.update(cart);
        }
        logger.info("成功加入购物车的商品id为："+goodsId);
        return "/cartSuccess";
    }

    @RequestMapping("/getcart")
    public String getCart(HttpServletRequest request, Model model){
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        List<Cart> carts=cartService.getCartById(user.getId());
        model.addAttribute("carts",carts);
        logger.info("获取购物车==>"+carts.toString());
        return "/cart";
    }

    @RequestMapping("/addcartajax")
    @ResponseBody
    public String addCartAjax(HttpServletRequest request){
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        int goodsId=Integer.parseInt(request.getParameter("goodsId"));
        int number=Integer.parseInt(request.getParameter("number"));

        Cart cart=cartService.findByUserIdAndGoodsId(user.getId(),goodsId);
        //获取商品
        Goods goods=goodsService.findById(goodsId);

        if(number!=0){
            cart.setNum(cart.getNum()+number);
            cart.setMoney(cart.getNum()*goods.getPrice());
            cartService.update(cart);
            logger.info("更新购物车中选中商品的总数量和总钱数");
        }
        else{
            logger.info("将商品移除购物车==>"+goodsId);
            cartService.remove(user.getId(),goodsId);
        }
        return null;
    }

    @RequestMapping("/clearcartajax")
    @ResponseBody
    public String clearCartAjax(HttpServletRequest request){
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        User user = (User) object;
        cartService.clear(user.getId());
        logger.info("清空购物车");
        return null;
    }


}
