package com.jsh;

import java.util.*;
import java.util.function.Predicate;

public interface IFormula {

    double calculate(int a);

    // 拆平方
    default double sqrt(int a) {
        return Math.sqrt(a);
    }


    public static void main(String[] args) {

        /** 1.8之前 */
        IFormula iFormula = new IFormula() {
            @Override
            public double calculate(int a) {
                return a * a;
            }
        };
        System.out.println(iFormula.calculate(2));
        System.out.println(iFormula.sqrt(2));

        /** 1.8 */
        // 入参a 和 实现
        IFormula formula = a -> a * a;
        System.out.println(formula.calculate(2));
        System.out.println(formula.sqrt(2));


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                System.out.println(a);
                System.out.println(b);
                return b.compareTo(a);
            }
        });

    }

}