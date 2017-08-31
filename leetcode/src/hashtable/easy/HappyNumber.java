package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/happy-number/description/" />
 * 202. Happy Number
 * Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example: 19 is a happy number

 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1
 * <p>
 * 0. 本质：函数function
 * 1. 建模：截取不同位的数，求平方，然后相加，不断循环，util the result contain only one digit.
 * 2. 算法范式：backtracking + dynamic programming 子问题重叠
 * 3. 算法：
 * 4. 数据结构：map缓存每个数的平方结果
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class HappyNumber {
    Map<Integer, Integer> cache = new HashMap();
    public boolean isHappy(int n) {
        if(n < 10 && n == 1) {
            return true;
        }

        int sum = 0;
        while(n > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        return isAppearBefore(sum)? false: isHappy(sum);
    }

    // map缓存乘积没那么必要，但是用来缓存重复出现的问题就很必要了
    boolean isAppearBefore(int sum) {
        if(cache.containsKey(sum)) {
            return true;
        } else {
            cache.put(sum, 1);
            return false;
        }

    }
}
