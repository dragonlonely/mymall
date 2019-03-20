package com.dragon.xiaomi.admin.controller;

import com.dragon.xiaomi.goods.pojo.Goods;
import com.dragon.xiaomi.goods.service.GoodsService;
import com.dragon.xiaomi.utils.FastDfsUtils;
import com.dragon.xiaomi.vo.GoodsVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Administrator
 * @Date 2019/1/5 16:58
 * @Classname AddGoodsController
 */
@Controller
@RequestMapping("/backadd")
public class AddGoodsController {
    private static final Logger logger=Logger.getLogger(AddGoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @PostMapping("/addgoods")
    public String addGoods(GoodsVo goodsVo, MultipartFile file, HttpServletRequest request) {
        String picture = null;
        try {
            FastDfsUtils fastDfsUtils = new FastDfsUtils("classpath:conf.properties");
            String filename = file.getOriginalFilename();
            String ext_name = filename.substring(filename.lastIndexOf(".") + 1);
            String path = fastDfsUtils.fileUpload(file.getBytes(), ext_name);
            picture = IMAGE_SERVER_URL + path;
            System.out.println(picture);

            Goods goods = new Goods();
            goods.setName(goodsVo.getName());
            goods.setPrice(goodsVo.getPrice());
            goods.setStar(goodsVo.getStar());
            goods.setIntro(goodsVo.getIntro());
            goods.setPubdate(new Date());
            goods.setPicture(picture);
            goods.setTypeid(goodsVo.getTypeid());
            goodsService.addGoods(goods);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("添加商品失败!");
        }
        logger.info("添加商品成功!");
        return "/admin/addGoods";
    }

}
