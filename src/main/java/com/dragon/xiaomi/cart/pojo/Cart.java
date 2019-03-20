package com.dragon.xiaomi.cart.pojo;


import com.dragon.xiaomi.goods.pojo.Goods;

/**
 * @author Administrator
 * @Date 2018/10/19 13:06
 * @Classname Cart
 */
public class Cart {
    private int id;
    private int pid;
    private int num;
    private double money;
    private Goods goods;

    public Cart() {
    }

    public Cart(int id, int pid, int num, double money, Goods goods) {
        this.id = id;
        this.pid = pid;
        this.num = num;
        this.money = money;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", pid=" + pid +
                ", num=" + num +
                ", money=" + money +
                ", goods=" + goods +
                '}';
    }
}
