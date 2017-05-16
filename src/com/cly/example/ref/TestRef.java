package com.cly.example.ref;

import org.junit.Test;

import com.cly.bean.Person;
import com.cly.interfaces.Converter;
import com.cly.interfaces.Converter2;
import com.cly.interfaces.Receive;

//引用
public class TestRef {

    @Test
    public void staticRef1() {
        Converter<String, Integer> converter = (input) -> Integer.valueOf(input);
        Integer value1 = converter.convert("123");
        System.out.println(value1); // 123
    }

    @Test
    public void staticRef2() {
        Converter2<String, Integer, Integer> converter2 =
                //Integer::valueOf;
                (s, i) -> Integer.valueOf(s, i);
        Integer value2 = converter2.convert("123", 16);
        System.out.println(value2); // 291
    }

    @Test
    public void testClassRef() {
        //Function
        Converter<String, Boolean> c = String::isEmpty;
        Boolean convert = c.convert("");
        System.out.println(convert);
    }

    @Test
    public void testInstanceRef() {
        Person person = new Person();
        person.setFirstName("abc");
        person.setLastName("xyz");
        //        PersonFactory<Person> personFactory = Person::new;//构造方法引用
        //        Person person = personFactory.create("abc", "xyz");
        Receive<String> r = person::getFirstName;
        System.out.println(r.apply());
    }

    @Test
    public void testSuperRef() {
        Receive<String> r = super::toString;
        System.out.println(r.apply());
    }

}
