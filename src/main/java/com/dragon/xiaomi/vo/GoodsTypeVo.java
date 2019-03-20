package com.dragon.xiaomi.vo;

/**
 * @author Administrator
 * @Date 2019/1/5 20:49
 * @Classname GoodsTypeVo
 */
public class GoodsTypeVo {
    private int id;
    private String name;
    private int level;
    private String parentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
