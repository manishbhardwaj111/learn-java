package org.learn.streams;

import java.util.stream.Collectors;

public class RemoveVowelsFromString {
    public static void main(String[] args) {
        System.out.println(removeVowels("manish"));
        System.out.println(removeVowels("beautiful"));
    }

    private static String removeVowels(String text) {
        return text.toLowerCase().chars()
                .mapToObj(x->String.valueOf((char)x))
                .filter(x->!"aeiou".contains(x))
                .collect(Collectors.joining());
    }
}
