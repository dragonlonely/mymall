package com.dragon.xiaomi.vo;

/**
 * @author Administrator
 * @Date 2019/1/5 18:23
 * @Classname GoodsVo
 */
public class GoodsVo {

    private String name;
    private double price;
    private int star;
    private String intro;
    private int typeid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
}
