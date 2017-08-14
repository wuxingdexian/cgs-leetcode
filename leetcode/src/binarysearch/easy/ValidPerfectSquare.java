package binarysearch.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/valid-perfect-square/discuss/" />
 * 367. Valid Perfect Square
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Returns: True
 Example 2:

 Input: 14
 Returns: False
 * <p>
 * 1. 建模：
 * （1）公式 1 + 3 + 5 + ... + (2(n-1) + 1) = n * n
 * （2）二叉搜索查询
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since cgs-leetcode on  14/08/2017
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {

        long low = 0, high = num, middle = 0, product = 0;
        while(low <= high) {
            middle = (low + high) >>> 1;
            product = middle * middle;
            if(product == num) {
                return true;
            } else if(product > num) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return false;
    }
}

