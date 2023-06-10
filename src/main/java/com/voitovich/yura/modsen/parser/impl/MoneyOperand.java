package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Operand;

public abstract class MoneyOperand implements Operand {
    private double val;

    public MoneyOperand() {
    }

    public double getVal() {
        return val;
    }

    public MoneyOperand(double val) {
        this.val = val;
    }
}
