package com.dragon.xiaomi.admin.controller;

import com.dragon.xiaomi.goods.pojo.Goods;
import com.dragon.xiaomi.goods.service.GoodsService;
import com.dragon.xiaomi.utils.FastDfsUtils;
import com.dragon.xiaomi.vo.ShowGoodsVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @Date 2019/1/5 21:43
 * @Classname ShowGoodsController
 */
@Controller
@RequestMapping("/goods")
public class ShowGoodsController {
    private static final Logger logger=Logger.getLogger(ShowGoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping("/getgoodslist")
    public String getGoodsList(HttpServletRequest request){
        List<Goods> goodsList = goodsService.fingAllGoods();
        //List<Goods> goodsList = pageInfo.getList();
        List<ShowGoodsVo> showGoodsVoList=new ArrayList<>();
        for (Goods goods : goodsList) {
            ShowGoodsVo showGoodsVo=new ShowGoodsVo();
            showGoodsVo.setId(goods.getId());
            showGoodsVo.setName(goods.getName());
            showGoodsVo.setPrice(goods.getPrice());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String pubdate = sdf.format(goods.getPubdate());
            showGoodsVo.setPubdate(pubdate);
            showGoodsVo.setIntro(goods.getIntro());
            String typeName = goodsService.getGoodsTypeName(goods.getTypeid());
            showGoodsVo.setTypeName(typeName);
            showGoodsVoList.add(showGoodsVo);
        }
        request.getSession().setAttribute("goodsList", showGoodsVoList);
        logger.info("获取所有商品成功!==>"+showGoodsVoList.toString());
        return "redirect:/admin/showGoods.jsp";
    }

    @RequestMapping("/searchgoodslist")
    @ResponseBody
    public List<ShowGoodsVo> searchGoodsList(HttpServletRequest request){
        String name = request.getParameter("name");
        String pubdate = request.getParameter("pubdate");
        List<Goods> goodsList=goodsService.queryGoods(name,pubdate);
        List<ShowGoodsVo> showGoodsVoList=new ArrayList<>();
        for (Goods goods : goodsList) {
            ShowGoodsVo showGoodsVo=new ShowGoodsVo();
            showGoodsVo.setId(goods.getId());
            showGoodsVo.setName(goods.getName());
            showGoodsVo.setPrice(goods.getPrice());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String pubdatetime = sdf.format(goods.getPubdate());
            showGoodsVo.setPubdate(pubdatetime);
            showGoodsVo.setIntro(goods.getIntro());
            String typeName = goodsService.getGoodsTypeName(goods.getTypeid());
            showGoodsVo.setTypeName(typeName);
            showGoodsVoList.add(showGoodsVo);
        }
        logger.info("查询商品!===>"+showGoodsVoList.toString());
        return showGoodsVoList;
    }

    @RequestMapping("/deleteGoods")
    public String deleteGoods(HttpServletRequest request){
        String _id = request.getParameter("id");
        int goodsid = Integer.parseInt(_id);
        goodsService.deleteGoodsById(goodsid);
        logger.info("商品下架!==>"+goodsid);
        return "forward:getgoodslist.action";
    }

    @PostMapping("/updategoods")
    public String updateGoods(HttpServletRequest request, MultipartFile file){

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String star = request.getParameter("star");
        String typeName = request.getParameter("typeName");
        String intro = request.getParameter("intro");
        //获取商品类型Id
        int typeid=goodsService.getGoodstyidByTypeName(typeName);
        Goods goods=new Goods();
        goods.setId(Integer.parseInt(id));
        goods.setName(name);
        goods.setPrice(Double.parseDouble(price));
        goods.setPubdate(new Date());
        goods.setStar(Integer.parseInt(star));
        goods.setTypeid(typeid);
        goods.setIntro(intro);
        String picture=null;
        try {
            FastDfsUtils fastDfsUtils = new FastDfsUtils("classpath:conf.properties");
            String filename = file.getOriginalFilename();
            String ext_name = filename.substring(filename.lastIndexOf(".") + 1);
            String path = fastDfsUtils.fileUpload(file.getBytes(), ext_name);
            picture = IMAGE_SERVER_URL + path;
            goods.setPicture(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        goodsService.updateGoods(goods);
        logger.info("修改商品==>"+goods.toString());
        return "forward:getgoodslist.action";
    }

}
