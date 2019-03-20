package com.dragon.test;

import java.util.Random;

/**
 * @author Administrator
 * @Date 2019/1/22 22:06
 * @Classname TestMath
 */
public class TestMath {

    public static void main(String[] args) {
        Random random=new Random();
        while (true){
            int A= random.nextInt(9);
            System.out.println(A);
            int S=A+20+A*100+7000;
            if (S%5==0){
                System.out.println(S);
            }
        }
    }
}
