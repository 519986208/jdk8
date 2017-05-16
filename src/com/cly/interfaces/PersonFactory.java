package com.cly.interfaces;

import com.cly.bean.Person;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
