package com.dragon.xiaomi.pay.controller;

import com.dragon.xiaomi.order.service.OrderService;
import com.dragon.xiaomi.pay.pojo.WeiXinResult;
import com.dragon.xiaomi.utils.PaymentUtil;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @Date 2018/10/20 13:22
 * @Classname PayController
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    private static final Logger logger=Logger.getLogger(PayController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping("/paymoney")
    public String payMoney(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //接受数据
        String orderid=request.getParameter("orderid");
        double money=Double.parseDouble(request.getParameter("money"));
        String pd_FrpId=request.getParameter("pd_FrpId");

        //按照第三方需要的数据

        String p0_Cmd = "Buy";
        String p1_MerId ="10001126856";
        String p2_Order = orderid;
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "xiaomi";
        String p6_Pcat = "xiaomi";
        String p7_Pdesc = "xiaomi";
        // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问 myssmxiaomiproject/
        // 第三方支付可以访问网址
        String p8_Url = "http://localhost:8090/pay/callback.action";
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        // 加密hmac 需要密钥
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue);

        //请求
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        response.sendRedirect(sb.toString());

        return null;

    }

    @RequestMapping("/callback")
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");
        // 身份校验 --- 判断是不是支付公司通知你
        String hmac = request.getParameter("hmac");
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

        // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
        boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);
        if (isValid) {
            // 响应数据有效
            if (r9_BType.equals("1")) {
                // 浏览器重定向
                System.out.println("111");
                request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
                logger.info("您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
            } else if (r9_BType.equals("2")) {
                // 服务器点对点 --- 支付公司通知你
                logger.info("付款成功！222");
                // 修改订单状态 为已付款
                // 回复支付公司
                response.getWriter().write("success");
            }
            //修改订单状态
            orderService.updateStatus(r6_Order,"2");

        } else {
            // 数据无效
            logger.info("数据被篡改！");
            request.setAttribute("msg", "数据被篡改,支付失败");
        }

        return "/message";
    }

    @RequestMapping("/weixincallback")
    @ResponseBody
    public String weixincallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson=new Gson();
        //接受微信支付回调的参数
        String json=request.getParameter("result");
        //使用Fastjson
        WeiXinResult weixin= gson.fromJson(json, WeiXinResult.class);

        if(weixin!=null) {
            String orderid=weixin.getResult().getOut_trade_no();
            String money=weixin.getResult().getCash_fee();
            //判断结果
            if(weixin.getResult().getResult_code().equals("SUCCESS")) {
                if(weixin.getType()==0) {
                    System.out.println("微信返回0.........");
                    request.setAttribute("msg", "您的订单号为:"+orderid+",金额为:"+money+"已经支付成功,等待发货~~");
                    logger.info("您的订单号为:"+orderid+",金额为:"+money+"已经支付成功,等待发货~~");
                }else if(weixin.getType()==1) {
                    logger.info("微信返回1........");
                    response.getWriter().write("success");
                }
                //处理订单状态
                orderService.updateStatus(orderid, "2");
            }else {
                logger.info("支付失败");
                request.setAttribute("msg", "支付失败");
            }
        }
        logger.info("回调执行了..................");

        return "/message";
    }

}
