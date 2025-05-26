package org.learn.regex;

public class RegexApp {
    public static void main(String[] args) {
        String input =  "Hello, Java";
        System.out.println(input.replaceAll("[a-zA-Z]",""));
        System.out.println(input.replaceAll("[^a-zA-Z]",""));
    }
}
