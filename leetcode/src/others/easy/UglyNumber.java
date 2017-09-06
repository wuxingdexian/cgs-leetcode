package others.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/ugly-number/description/" />
 * 263. Ugly Number
 * Write a program to check whether a given number is an ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

 Note that 1 is typically treated as an ugly number.
 * <p>
 * 0. 本质：number theory
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
 * @author changgan on 06/09/2017
 * @see
 * @since cgs-leetcode on  06/09/2017
 */
public class UglyNumber {
    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1) {
            return true;
        }

        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
//        return num == 1 ? true : false; 啰嗦
        return num == 1;
    }
}
