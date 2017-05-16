package com.cly.bean;

import java.util.UUID;

import com.cly.anno.Hint;
import com.cly.anno.Hints;

@Hints({ @Hint("hint1"), @Hint("hint2") })
//@Hint("hint1")
//@Hint("hint2")
public class Person {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    public String publicMethod() {
        return UUID.randomUUID().toString();
    }

}
