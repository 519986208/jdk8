package com.cly.interfaces;

//@FunctionalInterface
public interface Converter2<Input1, Input2, Output> {

    Output convert(Input1 input1, Input2 input2);

}
