package others.medium;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/string-to-integer-atoi/description/" />
 * 8. String to Integer (atoi)
 * Implement atoi to convert a string to an integer.

 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

 Update (2015-02-10):
 The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
 * <p>
 * 0. 本质：序列、number theory
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：栈
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：{@link String#trim()}去掉收尾空白
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/09/2017
 * @see
 * @since cgs-leetcode on  18/09/2017
 */
public class StringToInteger_atoi {
    public int myAtoi(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        if(str.length() == 0) {
            return 0;
        }

        Stack<Character> stack = new Stack();
        for(int i = 0; i < str.length(); i++) {
            if( i == 0 && (str.charAt(0) == '+' || str.charAt(0) == '-')) {
                stack.push(str.charAt(i));
            } else if(str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            } else {
                stack.push(str.charAt(i));
            }
        }

        int sign = 1;
        long sum = 0;
        int i = 0;
        while(!stack.isEmpty()) {
            char c = stack.pop();
            if(c == '+' || c == '-') {
                sign = c == '+'? 1: -1;
            } else {
                sum += (c - '0') * Math.pow(10, i);
            }
            i++;
        }

        sum *= sign;

        if(sum > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if(sum < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int)sum;
    }
}
