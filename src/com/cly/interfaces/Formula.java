package com.cly.interfaces;

//公式
@FunctionalInterface
public interface Formula {

    //抽象方法
    double calculate(int a);

    //    double aaa();

    //default方法
    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    //静态方法
    public static void staticMethod() {
        System.out.println("static method");
    }
}
