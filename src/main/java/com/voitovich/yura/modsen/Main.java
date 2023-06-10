package com.voitovich.yura.modsen;

import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Parser;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Operand operand = parser.parse("toRubles(($3 + $2) + toDollars(737р + toRubles($85.4 + $2) + 65р))");
        System.out.println(operand);
    }
}