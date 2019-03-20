package com.dragon.test;

/**
 * @author Administrator
 * @Date 2019/1/22 22:35
 * @Classname ParamTest
 */
public class ParamTest {
    public static void main(String[] args) {

        ParamTest pt=new ParamTest();
        User hollis=new User();
        hollis.setName("Hollis");
        hollis.setGender("Male");
        pt.pass(hollis);
        System.out.println("main>>>"+hollis);
    }

    public void pass(User user){
        user=new User();
        user.setName("hollischuang");
        user.setGender("Male");
        System.out.println("pass>>>"+user);
    }

}
