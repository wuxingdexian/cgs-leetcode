package twopointers.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reverse-string/description/" />
 * 344. Reverse String
 * Write a function that takes a string as input and returns the string reversed.

 Example:
 Given s = "hello", return "olleh".
 * <p>
 * 0. 本质：排列 序列 函数
 * 1. 建模：首尾互换
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class ReverseString {
    public String reverseString(String s) {
        char[] chars = s.toCharArray();

        for(int i = 0; i < (chars.length >> 1); i++) {
            char tmp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = tmp;
        }
        return String.valueOf(chars);
    }
}
