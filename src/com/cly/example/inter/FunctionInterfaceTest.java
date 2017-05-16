package com.cly.example.inter;

import org.junit.Test;

import com.cly.interfaces.Formula;

public class FunctionInterfaceTest {

    @Test
    public void test() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        Formula.staticMethod();
        System.out.println("default method " + formula.sqrt(16)); // 4.0
        System.out.println("abstract method " + formula.calculate(100)); // 100.0
    }

}
