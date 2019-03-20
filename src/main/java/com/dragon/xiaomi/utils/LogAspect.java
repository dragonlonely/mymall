package com.dragon.xiaomi.utils;

import com.dragon.xiaomi.commons.annotation.LogAnnotation;
import com.dragon.xiaomi.log.pojo.TbLog;
import com.dragon.xiaomi.log.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Administrator
 * @Date 2019/1/7 19:36
 * @Classname LogAspect
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("execution(* com.dragon..controller.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Exception {
        //获取正在被拦截的方法
        String name = joinPoint.getTarget().getClass().getName();
        String name1 = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //找方法上的注解
        Class<?> clazz = Class.forName(name);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(name1)) { // 当前遍历到的方法和正在使用的方法是一样的
                LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
                //获取注解的值
                if (logAnnotation != null) {
                    String opertionname = logAnnotation.opertionName();
                    String operationtype = logAnnotation.operationType();

                    String loginname=StringUtil.getRandomString(10);
                    System.out.println(opertionname + "被执行了(" + operationtype + "),参数是:" + Arrays.toString(args) + ( "执行的用户是:===>" + loginname));

                    //添加到日志
                    TbLog tbLog=new TbLog();
                    tbLog.setLoginname(loginname);
                    tbLog.setOperationname(opertionname);
                    tbLog.setOperationtype(operationtype);
                    tbLog.setCreatetime(new Date());
                    tbLog.setResult(null);
                    logService.addLog(tbLog);
                }
            }
        }
    }

    @AfterReturning(pointcut = "pointCut()",returning = "object")
    public void after(JoinPoint joinPoint,Object object) throws Exception {
        //获取正在被拦截的方法
        String name = joinPoint.getTarget().getClass().getName();
        String name1 = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();


        //找方法上的注解
        Class<?> clazz = Class.forName(name);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(name1)) {
                LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
                //获取注解的值
                if (logAnnotation != null) {
                    String opertionname = logAnnotation.opertionName();
                    String operationtype = logAnnotation.operationType();
                    String loginname=StringUtil.getRandomString(10);
                    System.out.println(opertionname + "被执行了(" + operationtype + "),参数是:" + Arrays.toString(args) + ( "执行的用户是:===>" + loginname));

                    //添加到日志
                    TbLog tbLog=new TbLog();
                    tbLog.setLoginname(loginname);
                    tbLog.setOperationname(opertionname);
                    tbLog.setOperationtype(operationtype);
                    tbLog.setCreatetime(new Date());
                    tbLog.setResult(object.toString());
                    logService.addLog(tbLog);
                }
            }
        }
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e) throws Exception {
        System.out.println("捕获了一个异常");
        //获取正在被拦截的方法
        String name = joinPoint.getTarget().getClass().getName();
        String name1 = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        //找方法上的注解
        Class<?> clazz = Class.forName(name);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(name1)) {
                LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
                //获取注解的值
                if (logAnnotation != null) {
                    String opertionname = logAnnotation.opertionName();
                    String operationtype = logAnnotation.operationType();

                    String loginname=StringUtil.getRandomString(10);
                    System.out.println(opertionname + "被执行了(" + operationtype + "),参数是:" + Arrays.toString(args) + ( "执行的用户是:===>" + loginname)+"结果失败了!");

                    //添加到日志
                    TbLog tbLog=new TbLog();
                    tbLog.setLoginname(loginname);
                    tbLog.setOperationname(opertionname);
                    tbLog.setOperationtype(operationtype);
                    tbLog.setCreatetime(new Date());
                    tbLog.setResult(e.getMessage());
                    logService.addLog(tbLog);
                }
            }
        }

    }
}
