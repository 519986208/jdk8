package com.cly.interfaces;

@FunctionalInterface
public interface Converter<Input, Output> {

    Output convert(Input from);

}
