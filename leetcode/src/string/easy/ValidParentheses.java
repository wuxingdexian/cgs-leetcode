package string.easy;

import java.util.LinkedList;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/valid-parentheses/description/" />
 * 20. Valid Parentheses
 * <p>
 * 1. 建模：
 * 栈
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link LinkedList#removeLast()}如果空，则抛异常的。
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class ValidParentheses {
    public boolean isValid(String s) {

        LinkedList<Character> stack = new LinkedList();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.add(s.charAt(i));
            } else {
                if(stack.size() == 0) {
                    return false;
                }
                if(s.charAt(i) == ')') {
                    Character parentheses = stack.removeLast();
                    if(parentheses != '(') {
                        return false;
                    }
                }
                if(s.charAt(i) == ']') {
                    Character parentheses = stack.removeLast();
                    if(parentheses != '[') {
                        return false;
                    }
                }
                if(s.charAt(i) == '}') {
                    Character parentheses = stack.removeLast();
                    if(parentheses != '{') {
                        return false;
                    }
                }
            }
        }

        if(stack.size() != 0) {
            return false;
        }
        return true;
    }
}
