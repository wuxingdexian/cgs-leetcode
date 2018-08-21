package stack.medium;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2018
 * @see
 * @since cgs-leetcode on  20/08/2018
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack1 = new Stack<>();

        for (String token: tokens) {
            if (token.equals("+")) {
                Integer popInt1 = stack1.pop();
                Integer popInt2 = stack1.pop();
                stack1.push(popInt1 + popInt2);
            } else if (token.equals("-")) {
                Integer popInt1 = stack1.pop();
                Integer popInt2 = stack1.pop();
                stack1.push(popInt2 - popInt1);
            } else if (token.equals("*")) {
                Integer popInt1 = stack1.pop();
                Integer popInt2 = stack1.pop();
                stack1.push(popInt1 * popInt2);
            } else if (token.equals("/")) {
                Integer popInt1 = stack1.pop();
                Integer popInt2 = stack1.pop();
                stack1.push(popInt2 / popInt1);
            } else {
                stack1.push(Integer.valueOf(token));
            }
        }
        return stack1.pop();

    }

}
