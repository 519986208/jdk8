package com.cly.example.lib;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import com.cly.bean.Person;

public class libTest {

    //Predicate 断言
    //接收T对象并返回boolean
    @Test
    public void predicate() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo"); // true
        predicate.negate().test("foo"); // false
        Predicate<Object> nonNull = Objects::nonNull;
        System.out.println("nonNull: " + nonNull.test("fdsfda"));

        Predicate<Object> isNull = Objects::isNull;
        System.out.println("isNull: " + isNull.test(1));

        Predicate<String> isEmpty = String::isEmpty;
        System.out.println("isEmpty: " + isEmpty.test("sss"));

        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println("isNotEmpty: " + isNotEmpty.test("fff"));
    }

    //Function 接口
    //接收T对象，返回R对象
    @Test
    public void function() {
        Function<String, Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("135"));
    }

    //Supplier 接口
    //Supplier<T>——提供T对象（例如工厂），不接收值
    @Test
    public void supplier() {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get(); // new Person
        System.out.println(person.publicMethod());
    }

    //Consumer 接口
    //接收T对象，不返回值
    @Test
    public void consumer() {
        Consumer<Person> consumer = (p) -> System.out.println("Hello, " + p.getFirstName());
        consumer.accept(new Person("Luke", "Skywalker"));
    }

    /////////////////////////
    @Test
    public void adder() {
        //        DoubleAdder
        LongAdder longAdder = new LongAdder();
        longAdder.add(12);
        longAdder.add(2);
        System.out.println(longAdder.longValue());
    }

    //ThreadLocal.withInitial
    @Test
    public void withInitial() {
        ThreadLocal<ArrayList<String>> threadLocal = ThreadLocal.withInitial(ArrayList<String>::new);
        System.out.println(threadLocal.get());

        ThreadLocal<Integer> threadLocal2 = ThreadLocal.withInitial(() -> 1);
        System.out.println(threadLocal2.get());
    }

    //……………………………………

}
