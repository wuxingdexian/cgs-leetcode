package binarysearch.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/sqrtx/description/" />
 * 69. Sqrt(x)
 * Implement int sqrt(int x).

 Compute and return the square root of x.
 * <p>
 * 0. 本质：序列
 * 1. 建模：在递增序列中定位
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：// FIXME 关键点 （1）大整数的乘法很容易溢出，改为逆操作“除法”；（2）start = 1 取1不取0，因为除法中0不能作为被除数。（3）最后“start <= x / start? start: start - 1;”
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class SqrtX {
    public int mySqrt(int x) {

        int start = 1;
        int end = x;
        while(start < end) {
            int middle = start + ((end - start) >>1);
            if(middle > x / middle) {
                end = middle - 1;
            } else if(middle < x / middle) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return start <= x / start? start: start - 1;
    }

    public static void main(String[] args) {
        int test = Integer.MAX_VALUE;
        int i = new SqrtX().mySqrt(2147483647);
        System.out.println(i);
    }
}
