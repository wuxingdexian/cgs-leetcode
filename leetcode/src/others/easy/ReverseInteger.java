package others.easy;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reverse-integer/description/" />
 * 7. Reverse Integer
 * Reverse digits of an integer.

 Example1: x = 123, return 321
 Example2: x = -123, return -321

 click to show spoilers.

 Have you thought about this?
 Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

 If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

 Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

 For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

 Note:
 The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
 * <p>
 * 0. 本质：排列
 * 1. 建模：
 * 将数字反过来，用栈
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class ReverseInteger {
    public int reverse(int x) {
        Stack<Integer> stack = new Stack();

        int tmp = Math.abs(x);
        while(tmp>0) {
            stack.push(tmp % 10);
            tmp = tmp / 10;
        }

        // 去掉顶部连续的0
        while(!stack.isEmpty() && stack.peek() == 0) {
            stack.pop();
        }

        long result = 0;
        // i < stack.size() 不能使用该终止条件，因为stack动态变动
        for(int i = 0; !stack.isEmpty(); i++) {
            result += stack.pop() * Math.pow(10, i);
        }

        if(result > Integer.MAX_VALUE) {
            return 0;
        }
        return x < 0? -(int)result: (int)result;
    }

    public static void main(String[] args) {
        int reverse = new ReverseInteger().reverse(123);
        System.out.println(reverse);
    }
}
