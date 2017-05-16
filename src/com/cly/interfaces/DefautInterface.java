package com.cly.interfaces;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import com.cly.anno.Hint;
import com.cly.anno.Hints;
import com.cly.bean.Person;

//http://www.jb51.net/article/48304.htm
public class DefautInterface {

    //接口的默认方法
    @Test
    public void defaultInterface() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.sqrt(16)); // 4.0
        System.out.println(formula.calculate(100)); // 100.0

        //        formula.
        //        Formula f=()->(a)sqrt(int a) ;

        //        Formula f = (int a) -> {
        //            return sqrt(a * 100);
        //        };

        //        Formula f = Formula::sqrt;

    }

    //Lambda表达式
    @Test
    public void lambda() {
        List<String> list1 = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(list1, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println("1111111111111");
        for (String name : list1) {
            System.out.print(name + "\t");
        }
        System.out.println();
        System.out.println();

        List<String> list2 = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(list2, (String a, String b) -> {
            return b.compareTo(a);
        });

        System.out.println("2222222222222");
        for (String name : list2) {
            System.out.print(name + "\t");
        }
        System.out.println();
        System.out.println();

        List<String> list3 = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(list3, (a, b) -> b.compareTo(a));

        System.out.println("3333333333333");
        for (String name : list3) {
            System.out.print(name + "\t");
        }

        System.out.println();
        System.out.println();

        //        FileFilter fileFilter = new FileFilter() {
        //            @Override
        //            public boolean accept(File pathname) {
        //                return false;
        //            }
        //        };

        FileFilter fileFilter = (f) -> f.isFile();
        Supplier<Runnable> c = () -> (() -> System.out.println("hi"));

        try {
            boolean flag = true;
            Callable<Integer> call = flag ? (() -> 23) : (() -> 42);
            Integer callValue = call.call();
            System.out.println(callValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object o = (Runnable) () -> System.out.println("hi");
        Runnable o2 = (Runnable) o;
        System.out.print("o : ");
        o2.run();
    }

    public static void main(String[] args) {
        //        Thread t1 = new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                while (true) {
        //                    System.out.println(new Date().toLocaleString());
        //                }
        //            }
        //        });
        //        t1.start();

        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(new Date().toLocaleString());
            }
        });
        t1.start();
    }

    @Test
    public void lambda2() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(new Date().toLocaleString());
                }
            }
        });
        t1.start();
    }

    @Test
    public void thisMethod() {
        Runnable r1 = () -> System.out.println(this.getClass().getName());
        Runnable r2 = () -> System.out.println(toString());

        r1.run();
        r2.run();

        try {
            System.out.println(helloCallable("cheng").call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Callable<String> helloCallable(String name) {
        String hello = "Hello";
        return () -> (hello + ", " + name);
    }

    ////////////////////////////////////////////////////////////////////////////

    //使用 :: 关键字来传递方法或者构造函数引用

    //指向一个静态方法
    @Test
    public void functionalInterface() {
        Converter<String, Integer> converter = (input) -> Integer.valueOf(input);
        Integer value1 = converter.convert("123");
        System.out.println(value1); // 123

        Converter2<String, Integer, Integer> converter2 = Integer::valueOf;
        Integer value2 = converter2.convert("123", 16);
        System.out.println(value2); // 291
    }

    //构造函数
    @Test
    public void construct() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("a", "b");
        System.out.println(person.getFirstName());
    }

    //Lambda 作用域
    @Test
    public void fieldEffect() {
        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        String convert = stringConverter.convert(2); // 3
        System.out.println(convert);
        //                num = 988;
    }

    static int outerStaticNum;
    int        outerNum;

    @Test
    public void fads() {
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
    }

    //Predicate接口
    @Test
    public void predicate() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        //        Predicate<String> p = (s) -> {
        //            return s.length() > 0;
        //        };

        predicate.test("foo"); // true
        predicate.negate().test("foo"); // false
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    //Function 接口
    @Test
    public void function1() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123"); // "123"
    }

    //Supplier 接口
    @Test
    public void supplier() {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get(); // new Person
    }

    //Consumer 接口
    @Test
    public void consumer() {
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter.accept(new Person("Luke", "Skywalker"));
    }

    //Comparator 接口
    @Test
    public void comparator() {
        Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        comparator.compare(p1, p2); // > 0
        comparator.reversed().compare(p1, p2); // < 0
    }

    //Optional 接口
    @Test
    public void option() {
        Optional<String> optional = Optional.of("bam");
        optional.isPresent(); // true
        optional.get(); // "bam"
        optional.orElse("fallback"); // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
    }

    //Stream 接口
    @Test
    public void stream() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        //foreach 迭代
        //        stringCollection.stream().forEach(System.out::println);

        //Sort 排序
        //        stringCollection.stream().sorted().forEach(System.out::println);

        //Filter 过滤
        //        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        //        Map 映射
        //        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
        //                .forEach(System.out::println);

        //Match 匹配
        //        boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        //        System.out.println(anyStartsWithA); // true
        //        boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        //        System.out.println(allStartsWithA); // false
        //        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
        //        System.out.println(noneStartsWithZ); // true

        //Count 计数
        //        long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        //        System.out.println(startsWithB); // 3

        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "," + s2);
        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        System.out.println("=========================");
        System.out.println(stringCollection);
    }

    //并行Streams
    @Test
    public void parallelStream() {
        int max = 200_0000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        values.stream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        long t3 = System.nanoTime();
        values.parallelStream().sorted().count();
        long t4 = System.nanoTime();
        long millis2 = TimeUnit.NANOSECONDS.toMillis(t4 - t3);
        System.out.println(String.format("parallel sort took: %d ms", millis2));
    }

    //Map
    @Test
    public void map() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println("key: " + id + " value: " + val));

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3)); // val33
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));// false

        //如果存在key，并且map中已存在的value不为空
        map.computeIfPresent(14, (num, val) -> val);
        System.out.println(map.containsKey(14));

        //如果不存在
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));
        // true
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));
        // val33

        System.out.println("==================================");
        System.out.println(map);

        map.remove(3, "val3");
        System.out.println(map.get(3)); // val33
        map.remove(3, "val33");
        System.out.println(map.get(3)); // null
        map.remove(4);
        System.out.println(map.get(4));

        System.out.println(map.getOrDefault(643, "erfasfdsadsaf"));

        //对Map的元素做合并
        map.merge(9, "322", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));// val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9)); // val9concat
    }

    //使用多重注解
    @Test
    public void multiAnno() {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint); // null
        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length); // 2
        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length); // 2
    }

    //
    @Test
    public void thl() {
        ThreadLocal<ArrayList<String>> threadLocal = ThreadLocal.withInitial(ArrayList<String>::new);
        System.out.println(threadLocal.get());

        ThreadLocal<Integer> threadLocal2 = ThreadLocal.withInitial(() -> 1);
        System.out.println(threadLocal2.get());
    }

    @Test
    public void fasf() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(String.valueOf(i), String.valueOf(i) + "value");
        }
        Function<String, String> searchFunction = value -> {
            System.out.println("aaaaaaa: " + value);
            if ("value540".equals(value)) {
                return "abc";
            }
            return "value532";
        };
        String searchValues = map.searchValues(3, searchFunction);

        System.out.println("result: " + searchValues);
    }

    @Test
    public void parallelArray() {
        long[] arrayOfLong = new long[20];
        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();

        //        System.out.println();
        for (long l : arrayOfLong) {
            System.out.println(l);
        }

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    //类依赖分析器jdeps

    //PermGen空间被移除了，取而代之的是Metaspace（JEP 122）。
    //JVM选项-XX:PermSize与-XX:MaxPermSize分别被-XX:MetaSpaceSize与-XX:MaxMetaspaceSize所代替。

}
