package org.learn.datastructure;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.learn.datastructure.recursion.RecursionExample;

import static org.junit.jupiter.api.Assertions.*;

class RecursionExampleTest {


    @ParameterizedTest
    @CsvSource({
            "12342, 12",
            "7, 7",
            "0, 0",
            "123456789, 45",
            "1001, 2",
            "99999, 45"
    })
    void digitSum(int input, int expected) {
        assertEquals(expected, RecursionExample.digitSum(input));
    }


    @ParameterizedTest
    @CsvSource({
            "48, 18, 6",
            "56, 98, 14",
            "101, 10, 1",
            "1000, 1000, 1000",
            "0, 0, 0",
            "1, 1, 1"
    })
    void gcd(int input1, int input2, int expected) {
        assertEquals(expected, RecursionExample.gcd(input1, input2));
    }

    @ParameterizedTest
    @CsvSource({
            "0, '0'",
            "1, '1'",
            "2, '10'",
            "3, '11'",
            "4, '100'",
            "256, '100000000'",
            "511, '111111111'",
            "512, '1000000000'",
            "1023, '1111111111'",
            "1024, '10000000000'",
            "2047, '11111111111'",
            "2048, '100000000000'",
            "4095, '111111111111'",
            "4096, '1000000000000'",
    })
    void decimalToBinary(int input, String expected) {
        assertEquals(expected, RecursionExample.decimalToBinary(input));
    }
}