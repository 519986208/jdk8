package com.cly.example.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Test;

import com.cly.interfaces.Converter;

public class LambdaTest {

    @Test
    public void testComparator() {
        List<String> list = Arrays.asList("aa", "ab", "ba", "ed", "cd", "da", "ac", "bw");
        //        Collections.sort(list, new Comparator<String>() {
        //            @Override
        //            public int compare(String a, String b) {
        //                return b.compareTo(a);
        //            }
        //        });

        Comparator<String> c = (s1, s2) -> s2.compareTo(s1);

        Collections.sort(list, c);

        for (String str : list) {
            System.out.println(str);
        }

    }

    @Test
    public void testFileFilter() {
        FileFilter fileFilter = (f) -> f.isFile();
        File file = new File("d:\\");
        File[] listFiles = file.listFiles(fileFilter);
        for (File f : listFiles) {
            System.out.println(f.getName());
        }
    }

    @Test
    public void testCallable() throws Exception {
        boolean flag = false;
        Callable<Integer> call = flag ? (() -> 23) : (() -> 42);
        Integer callValue = call.call();
        System.out.println(callValue);
    }

    @Test
    public void testRunnable() {
        Object o = (Runnable) () -> System.out.println("hi");
        Runnable o2 = (Runnable) o;
        System.out.print("o : ");
        o2.run();
    }

    @Test
    public void testThis() throws Exception {
        Runnable r1 = () -> System.out.println(this.getClass().getName());
        Runnable r2 = () -> System.out.println(toString());
        r1.run();
        r2.run();

        System.out.println(helloCallable("zhongan").call());
    }

    private Callable<String> helloCallable(String name) {
        String hello = "Hello";
        return () -> (hello + ", " + name);
    }

    static int outerStaticNum;
    int        outerNum;

    //Lambda 作用域
    @Test
    public void fieldEffect() {
        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        String convert = stringConverter.convert(2); // 3
        System.out.println(convert);
        //        num = 988;

        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
        System.out.println(outerStaticNum);
        System.out.println(outerNum);
        System.out.println(stringConverter1.convert(12));
        System.out.println(stringConverter2.convert(13));
        System.out.println(outerStaticNum);
        System.out.println(outerNum);
    }

}
