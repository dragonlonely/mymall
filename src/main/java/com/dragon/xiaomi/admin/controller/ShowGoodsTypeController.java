package com.dragon.xiaomi.admin.controller;

import com.dragon.xiaomi.goods.pojo.GoodsType;
import com.dragon.xiaomi.goods.service.GoodsService;
import com.dragon.xiaomi.vo.GoodsTypeVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Date 2019/1/5 19:58
 * @Classname ShowGoodsTypeController
 */
@Controller
@RequestMapping("/goods")
public class ShowGoodsTypeController {
    private static final Logger logger=Logger.getLogger(ShowGoodsTypeController.class);

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getgoodstype")
    public String getGoodsType(HttpServletRequest request){
        List<GoodsTypeVo> goodsTypeList=new ArrayList<>();
        List<GoodsType> allGoodsType = goodsService.findAllGoodsType();
        for (GoodsType goodsType : allGoodsType) {
            GoodsTypeVo goodsTypeVo=new GoodsTypeVo();
            String parentName=null;
            if(goodsType.getParent()==0){
                parentName="没有父";
            }else {
                parentName = goodsService.getParentNameByParentId(goodsType.getParent());
            }
            goodsTypeVo.setId(goodsType.getId());
            goodsTypeVo.setName(goodsType.getName());
            goodsTypeVo.setLevel(goodsType.getLevel());
            goodsTypeVo.setParentName(parentName);
            goodsTypeList.add(goodsTypeVo);
        }
        request.getSession().setAttribute("goodsTypeList", goodsTypeList);
        logger.info("获取商品类型!");
        return "redirect:/admin/showGoodsType.jsp";
    }

    @RequestMapping("/addgoodstype")
    public String addGoodsType(GoodsType goodsType,HttpServletRequest request){
        String name = request.getParameter("typename");
        String _goodsParent = request.getParameter("goodsParent");
        int parent = Integer.parseInt(_goodsParent);
        goodsType.setName(name);
        goodsType.setLevel(2);
        goodsType.setParent(parent);
        goodsService.addGoodsType(goodsType);
        logger.info("添加商品类型!");
        return "forward:getgoodstype.action";
    }

}
