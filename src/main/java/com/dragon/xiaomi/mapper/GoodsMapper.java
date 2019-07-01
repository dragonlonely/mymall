package com.dragon.xiaomi.mapper;

//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//  


import com.dragon.xiaomi.goods.pojo.Goods;
import com.dragon.xiaomi.goods.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jackiechan on 10/16/18/7:25 PM
 *
 * @Author jackiedragon
 */
public interface GoodsMapper {

    List<GoodsType> getGoodsTypeList();

    List<Goods> getGoodsByTypeId(@Param("typeId") Integer typeId,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    Goods getGoodsDetailById(Integer id);

    GoodsType getGoodsListByTypeId(Integer typeId);

    Goods findById(Integer id);

    List<Goods> findByName(String goodsname);

    void addGoods(Goods goods);

    List<GoodsType> findAllGoodsType();

    String getParentNameByParentId(int parent);

    List<Goods> fingAllGoods();

    String findGoodsTypeName(int typeid);

    void addGoodsType(GoodsType goodsType);

    List<Goods> queryGoods(@Param("name") String name,@Param("pubdate") String pubdate);

    void deleteGoodsById(int goodsid);

    int getGoodstyidByTypeName(String typeName);

    void updateGoods(Goods goods);

    long getCount(Integer typeId);
}
