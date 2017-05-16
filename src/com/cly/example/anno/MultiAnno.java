package com.cly.example.anno;

import org.junit.Test;

import com.cly.anno.Hint;
import com.cly.anno.Hints;
import com.cly.bean.Person;

public class MultiAnno {

    @Test
    public void testMultiAnnotation() {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint); // null
        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length); // 2
        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length); // 2
    }

}
