package com.dragon.xiaomi.goods.service;


import com.dragon.xiaomi.goods.pojo.Goods;
import com.dragon.xiaomi.goods.pojo.GoodsType;
import com.dragon.xiaomi.vo.PageBean;

import java.util.List;

/**
 * Created by jackiechan on 10/16/18/7:24 PM
 *
 * @Author jackiechan
 */
public interface GoodsService {

    List<GoodsType> getGoodsTypeList();

    PageBean getGoodsByTypeId(Integer typeId, Integer pageNum, Integer pageSize);

    Goods getGoodsDetailById(Integer id);

    Goods findById(int id);

    List<Goods> searchByName(String goodsname);

    void addGoods(Goods goods);

    List<GoodsType> findAllGoodsType();

    String getParentNameByParentId(int parent);

    List<Goods> fingAllGoods();

    String getGoodsTypeName(int typeid);

    void addGoodsType(GoodsType goodsType);

    List<Goods> queryGoods(String name, String pubdate);

    void deleteGoodsById(int goodsid);

    int getGoodstyidByTypeName(String typeName);

    void updateGoods(Goods goods);
}
