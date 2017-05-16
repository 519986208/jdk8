package com.cly.example.stream;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MapTest {

    static Map<Integer, String> map = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            //如果不存在key，则put到map中
            map.putIfAbsent(i, "val" + i);
        }
    }

    @Test
    public void foreach() {
        map.forEach((id, val) -> System.out.println("key: " + id + " value: " + val));
    }

    @Test
    public void computeIfPresent() {
        //如果存在key并且从当前map获取的value不为空，如果新的value不为空，怎更新，如果为空，删除key
        System.out.println("put之前get 3=" + map.get(3));//val3
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println("put之后get 3=" + map.get(3)); // val33

        System.out.println("========================");

        System.out.println("put之前get 9=" + map.get(9));
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println("put之后get 9=" + map.containsKey(9));// false

        System.out.println("========================");

        System.out.println("put之前get 14=" + map.get(14));
        map.computeIfPresent(14, (num, val) -> val);
        System.out.println("put之后get 14=" + map.containsKey(14));
    }

    @Test
    public void computeIfAbsent() {
        //如果不存在
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23)); // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3)); // val33
    }

    @Test
    public void remove() {
        //remove(key,value)必须key和value都等于,新版本删除
        map.remove(3, "val33");
        System.out.println(map.get(3)); // val3

        map.remove(3, "val3");
        System.out.println(map.get(3)); // null

        //remove(key)，老版本的remove
        map.remove(4);
        System.out.println(map.get(4));//null
    }

    @Test
    public void defaultValue() {
        System.out.println(map.getOrDefault(3, "fdasfdsabbbbb"));
        System.out.println(map.getOrDefault(643, "erfasfdsadsaf"));
    }

    @Test
    public void merge() {
        //对Map的元素更新
        System.out.println(map.get(9));
        map.merge(9, "322", (value, newValue) -> value.concat(newValue));
        //        map.put(key, value)
        System.out.println(map.get(9));
    }

}
