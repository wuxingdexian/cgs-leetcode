package breadthfirstsearch.medium;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/perfect-squares/description/" />
 * 279. Perfect Squares
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * <p>
 * 1. 建模：
 * （1）决策树
 * 以n为根，从小于n的最大平方x开始，依次作为n的左节点，左节点即为叶子节点，右节点为n-x，若n-x不是平方数，则递归解决这个子问题。当叶子节点都是平方数时得到答案
 * 记录下叶子节点（即）平方数的个数，取个数最小的那个。
 * min(n) = min(1 + min(n-i))
 * （2）树
 * 构造和上面一样的树，使用深度来间接表示需要的平方数，当深度越低，则需要的平方数肯定越少
 * 设定有序对(level, root, minLevel, minLeaveNum)
 * （3）recurrence relation
 * min_count(n) = 1，如果 n = sqrt(n) * sqrt(n)
 * min_count(n) = 1 + min(min_count((n-sqrt(n) * (n-sqrt(n)), min_count((n-sqrt(n) - 1) * (n-sqrt(n) - 1)), ...)，如果n != sqrt(n) * sqrt(n)
 * sqrt(n)为n的平方根
 * 这题动态编程感觉会更容易
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：// FIXME: 17/08/2017 从构建决策树或树的结构时，应该能有所启示，看出是否存在子问题求解，从而判断是否是dynamic programming
 * 7. jdk知识：{@link Math#sqrt(double)}返回double，要明确强转为int
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 17/08/2017
 * @see
 * @since cgs-leetcode on  17/08/2017
 */
public class PerfectSquares {
    public int numSquares(int n) {

        int[] minSquare = new int[n + 1];
        minSquare[0] = 1;
        Arrays.fill(minSquare, Integer.MAX_VALUE);
        for(int i = 1; i <= n; i++) {
            int sqrt = (int)Math.sqrt(i);
            if(sqrt * sqrt == i) {
                minSquare[i] = 1;
            } else {
                for(int j = sqrt; j > 0; j--) {
                    minSquare[i] = Math.min(1 + minSquare[i - j * j], minSquare[i]);
                }
            }
        }
        return minSquare[n];
    }

}
