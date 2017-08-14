package math.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/power-of-three/description/" />
 * 326. Power of Three
 *Given an integer, write a function to determine if it is a power of three.

 Follow up:
 Could you do it without using any loop / recursion?
 * <p>
 * 1. 建模：公式
 * log(x,y) = log(a, x) / log(b, y)换底
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：一般使用辗转相除来解决；但使用3的最大值幂方来除n，只要n是3的幂方，就一定能整除3的最大次幂。
 * 7. jdk知识：下取整{@link Math#floor(double)}，幂方函数{@link Math#pow(double, double)}，自然对数函数{@link Math#log(double)}
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        return n > 0 && Math.pow(3, Math.floor(Math.log(Integer.MAX_VALUE) / Math.log(3))) % n == 0;
    }
}
