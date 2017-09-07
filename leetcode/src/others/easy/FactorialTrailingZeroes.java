package others.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/factorial-trailing-zeroes/description/" />
 * 172. Factorial Trailing Zeroes
 Given an integer n, return the number of trailing zeroes in n!.

 Note: Your solution should be in logarithmic time complexity.

 * <p>
 * 0. 本质：number theory
 * 1. 建模：
 * 末尾为0，只有2*5=10才能得到，由于5的个数比2少，只关注5的个数即可
 * 一共有多少个5：sum(5) = n/5
 * 特殊有多少个5*5=25：sum(25) = n/25
 * 特殊有多少个5*5*5=125：sum(125) = n/125
 * ....
 * 最后一共有sum(5)+sum(25)+sum(125)+....
 * 注意：集合的include关系
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：// FIXME: 07/09/2017 注意溢出，<code>long i = 5</code>
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int counter = 0;
        for(long i = 5; i <= n; i = 5*i) {
            counter += n / i;
        }
        return counter;
    }
}
