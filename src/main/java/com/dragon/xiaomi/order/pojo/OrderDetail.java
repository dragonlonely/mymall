package com.dragon.xiaomi.order.pojo;

import com.dragon.xiaomi.goods.pojo.Goods;

/**
 * @author Administrator
 * @Date 2018/10/19 16:40
 * @Classname OrderDetail
 */
public class OrderDetail {
    private int id;
    private String oid;
    private int pid;
    private int num;
    private double money;

    private Goods goods;

    public OrderDetail() {
    }

    public OrderDetail(int id, String oid, int pid, int num, double money, Goods goods) {
        this.id = id;
        this.oid = oid;
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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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
        return "OrderDetail{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", pid=" + pid +
                ", num=" + num +
                ", money=" + money +
                ", goods=" + goods +
                '}';
    }
}
