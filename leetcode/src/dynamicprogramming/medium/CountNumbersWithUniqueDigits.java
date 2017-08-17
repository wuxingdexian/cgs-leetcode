package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/description/" />
 * 357. Count Numbers with Unique Digits
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

 Example:
 Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 * <p>
 * 1. 建模：recurrence relation
 * 用到排列组合
 * 共有[0、1、2、3、4、5、6、7、8、9]十个数来排列组合形成正整数
 * f(1) = A(10,1)
 * f(2) = f(1) + (A(10,2) - A(9,1)) = f(1) + 9 * (10 - 1) = f(1) + 81，其中A(10,2) - A(9,1)要去掉两位数排列是0在最开始的情况
 * f(3) = f(2) + (A(10,3) - A(9,2)) = f(2) + 9 * 8 * (10 - 1) = f(3) + 9 * C(9,2)
 * ....
 * f(11) = 0
 *
 * 2. 算法范式：
 * dynamic programming
 *
 * 3. 算法：
 * bottom-up
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/08/2017
 * @see
 * @since cgs-leetcode on  16/08/2017
 */
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        if(n < 0) {
            return 0;
        }

        n = n > 10? 10: n;

        int[] counts = new int[n + 1];
        counts[0] = 1;
        for(int i = 1; i <= n; i++) {
            int permutations = 9;
            for(int j = 9; j > 10 - i; j--) {
                permutations *= j;
            }
            counts[i] = permutations + counts[i - 1];
        }
        return counts[counts.length - 1];
    }

    public static void main(String[] args) {
        int i = new CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(2);
        System.out.println(i);
    }
}
