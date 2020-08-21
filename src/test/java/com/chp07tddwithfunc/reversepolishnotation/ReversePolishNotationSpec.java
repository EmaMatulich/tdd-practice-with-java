package com.chp07tddwithfunc.reversepolishnotation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReversePolishNotationSpec {

    private ReversePolishNotation notation;

    @Before
    public void setUp(){
        notation = new ReversePolishNotation();
    }

    /**
     * Requirements:
     *  On invalid input (not a RPN), an error message should be thrown
     *  it gets an arithmetic expression writeen using RPN and computes the result
     */

    //First part support only one number in expression

    @Test(expected = NotReversePolishNotationError.class)
    public void whenEmptyExpressionThenThrowsError(){
        notation.compute("");
    }

    @Test(expected = NotReversePolishNotationError.class)
    public void whenNotANumberThenThrowsError(){
        notation.compute("a");
    }

    @Test
    public void whenOnlyOneDigitThenReturnDigit(){
        assertEquals(7, notation.compute("7"));
    }

    /*
        This test is a false positive
     */
/*    @Test
    public void whenMoreThanOneDigitThenReturnDigits(){
        assertEquals(17, notation.compute("17"));
    }*/


    //Second part, support a single operation add, subtract, multiply, divide
    //a b +
    //a b -
    //a b *
    //a b /

    @Test
    public void whenAddOperationThenNumbersAdded(){
        assertEquals(10, notation.compute("4 6 +"));
    }

    @Test
    public void whenSubtractOperationThenNumbersSubtracted(){
        assertEquals(2, notation.compute("6 4 -"));
    }

    @Test
    public void whenMultiplyOperationThenNumbersMultiplied(){
        assertEquals(8, notation.compute("2 4 *"));
    }

    @Test
    public void whenDivideOperationThenNumbersDivided(){
        assertEquals(2, notation.compute("4 2 /"));
    }

    @Test
    public void multipleAddOperationsReturnCorrectValue(){
        assertEquals(8, notation.compute("1 2 5 + +"));
    }

    @Test
    public void multipleDifferentOperationsReturnCorrectValue(){
        assertEquals(14, notation.compute("5 12 + 3 -"));
    }

    @Test
    public void complexOperationsReturnCorrectValue(){
        assertEquals(14, notation.compute("5 1 2 + 4 * + 3 -"));
    }

}
