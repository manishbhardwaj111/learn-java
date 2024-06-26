package org.learn.algorithms;

import java.util.LinkedHashMap;

public class PalindromeString {
    public static void main(String[] args) {
        System.out.println(isPalindromeString("madam"));
        System.out.println(isPalindromeString("aa"));

        LinkedHashMap k;

    }



    private static boolean isPalindromeString(String text) {
        if (text!=null && !text.trim().isEmpty()) {
            for (int index = 0; index < text.length()/2; index++) {
                if (text.charAt(index) != text.charAt(text.length()-index-1))
                    return false;
            }
        }
        return true;
    }
}
