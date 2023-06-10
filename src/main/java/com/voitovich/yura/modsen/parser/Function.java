package com.voitovich.yura.modsen.parser;

public interface Function extends Operator {
    @Override
    default boolean isFunction() {
        return true;
    }
}
