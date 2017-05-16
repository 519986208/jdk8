package com.cly.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class StreamTest {

    static List<String> stringCollection = new ArrayList<>();

    static {
        stringCollection.add("ddd422");
        stringCollection.add("aaa22");
        stringCollection.add("bbbdf1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb334");
        stringCollection.add("cccfaf");
        stringCollection.add("bbbfdasf2");
        stringCollection.add("ddd2331");
    }

    //    @Before
    //    public void before() {
    //        System.out.println("当前list中的值：");
    //        stringCollection.stream().forEach((str) -> {
    //            System.out.print(str + "\t");
    //        });
    //        System.out.println();
    //        System.out.println();
    //    }

    @Test
    public void foreach() {
        //        Collection.stream.foreach()
        stringCollection.stream().forEach(System.out::println);
    }

    @Test
    public void sort() {
        //Sort 排序
        stringCollection.stream()
                /*
                 * (s1, s2) -> { return s1.compareTo(s2); }
                 */
                .sorted().forEach(System.out::println);

        System.out.println(stringCollection);

    }

    @Test
    public void filter() {
        //Filter 过滤
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
    }

    @Test
    public void map() {
        //Map 映射
        Set<String> collect = stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toSet());
        //                .forEach(System.out::println);

        System.out.println(collect);
    }

    @Test
    public void maxLength() {

        IntStream mapToInt = stringCollection.stream().mapToInt(String::length);
        System.out.println(mapToInt.toString());
        //        mapToInt.collect(Collectors.toList())
        mapToInt.forEach(System.out::println);

        int a = stringCollection.stream().mapToInt(String::length).max().getAsInt();
        System.out.println(a);
    }

    @Test
    public void count() {
        //Count 计数
        long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(startsWithB);
    }

    @Test
    public void stream() {
        //规约
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "," + s2);
        reduced.ifPresent(System.out::println);
    }

    //Match 匹配
    @Test
    public void match() {
        //anyMatch  至少有一个匹配
        boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA); // true

        //allMatch 所有匹配
        boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA); // false

        //noneMatch 没有一个能匹配
        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ); // true
    }

}
