package org.example.w2.bmi;

import java.lang.reflect.Method;

public class Pig {
    public static void main(String[] args) throws Exception {


    /*Pig obj= new  Pig();
    obj.eat();*/

        Class clz = Class.forName("org.example.bmi.Pig");


        Object obj = clz.newInstance();

        Method method = clz.getDeclaredMethod("eat");

        method.invoke(obj);

    }

}