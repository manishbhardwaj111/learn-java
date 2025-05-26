package org.learn.datastructure.stack;

import java.util.Stack;

public class Questions {
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i< expression.length(); i++ ) {
            char ch = expression.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                try {
                    char ch2 = stack.pop();
                    if(ch == ')' && ch2 != '(') {
                        return false;
                    }else if(ch == '}' && ch2 != '{') {
                        return false;
                    }else if(ch == ']' && ch2 != '[') {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("{[()]})"));
    }
}