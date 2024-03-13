package org.example.utils;

import org.nfunk.jep.JEP;

public class CalculatorUtil {

    public static double calculateExpression(String expression) {
        JEP jep = new JEP();
        jep.parseExpression(expression);
        return jep.getValue();
    }
}
