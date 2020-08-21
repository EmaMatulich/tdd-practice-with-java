package com.chp07tddwithfunc.reversepolishnotation;

import java.util.Stack;
import java.util.function.BinaryOperator;

public class ReversePolishNotation {

    private final String ADD_OPERATOR = "+";
    private final String SUBTRACT_OPERATOR = "-";
    private final String MULTIPLY_OPERATOR = "*";
    private final String DIVIDE_OPERATOR = "/";

    private static final BinaryOperator<Integer> ADD = (a, b) -> a + b;
    private static final BinaryOperator<Integer> SUBTRACT = (a, b) -> a - b;
    private static final BinaryOperator<Integer> MULTIPLY = (a, b) -> a * b;
    private static final BinaryOperator<Integer> DIVIDE = (a, b) -> a / b;

    int compute(String expression) {
        Stack<Integer> stack = new Stack<>();
        for (String element : expression.trim().split(" ")) {
            switch (element) {
                case ADD_OPERATOR:
                    applyOperation(stack, ADD);
                    break;
                case SUBTRACT_OPERATOR:
                    applyOperation(stack, SUBTRACT);
                    break;
                case MULTIPLY_OPERATOR:
                    applyOperation(stack, MULTIPLY);
                    break;
                case DIVIDE_OPERATOR:
                    applyOperation(stack, DIVIDE);
                    break;
                default:
                    stack.push(parseInt(element));
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new NotReversePolishNotationError();
        }
    }

    private int parseInt(String number) {
        try {
            return new Integer(number);
        } catch (NumberFormatException e) {
            throw new NotReversePolishNotationError();
        }
    }

    private static void applyOperation(Stack<Integer> stack,
                                       BinaryOperator<Integer> operation) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(operation.apply(a, b));
    }


}

