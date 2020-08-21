package com.chp07tddwithfunc.reversepolishnotation;

public class NotReversePolishNotationError extends RuntimeException{
    public NotReversePolishNotationError(){
        super("Not a reverse polish notation");
    }
}
