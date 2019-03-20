package com.dragon.xiaomi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ActiveUtils {
	
	public static String createActiveCode() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String s1=sdf.format(date);
		String s2=Integer.toHexString((int)(Math.random()*900)+100);
		return s1+s2;
	}
	
	/**
	 * 产生订单号
	 * @return
	 */
	public static String createOrderId() {
		return UUID.randomUUID().toString().replaceAll("-","").substring(0, 16);
	}
}
