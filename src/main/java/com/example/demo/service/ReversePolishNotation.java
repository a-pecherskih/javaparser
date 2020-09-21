package com.example.demo.service;

import java.util.Stack;
import java.util.stream.Stream;

/**
 * 111
 */
public class ReversePolishNotation {

    // 222
    public static int ONE_BILLION = 1000000000;
    public static int TWO_BILLION = 2_000_000_000;

    private double memory = 0;

    /**
     * 333
     *
     * @param input mathematical expression in the reverse Polish notation format
     * @return the calculation result 444
     */
    public Double calc(String input) {
        String[] tokens = input.split(" ");
        Stack<Double> numbers = new Stack<>();

        Stream.of(tokens).forEach(t -> {
            double a;
            double b;
            switch (t) {
                case "+":
                    b = numbers.pop();
                    a = numbers.pop();
                    numbers.push(a + b);
                    break;
                case "/":
                    b = numbers.pop();
                    a = numbers.pop();
                    numbers.push(a / b);
                    break;
                case "-":
                    b = numbers.pop();
                    a = numbers.pop();
                    numbers.push(a - b);
                    break;
                case "*":
                    b = numbers.pop();
                    a = numbers.pop();
                    numbers.push(a * b);
                    break;
                default:
                    numbers.push(Double.valueOf(t));
            }
        });
        return numbers.pop();
    }

    /**
     * 555
     *
     * @return the double 666
     */
    public double memoryRecall(){
        return memory;
    }

    /**
     * 777
     */
    public void memoryClear(){
        memory = 0;
    }

    public void memoryStore(double value) {
        memory = value;
    }
}
/* 888 */
