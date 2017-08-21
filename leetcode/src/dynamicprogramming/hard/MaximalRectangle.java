package dynamicprogramming.hard;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximal-rectangle/description/" />
 * 85. Maximal Rectangle
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 Return 6.
 * <p>
 * 1. 建模：
 * （1）暴力破解，依次遍历整个数组。思路是从左上角向右下角方向逐渐收缩
 * （2）recurrence relation
 * 设M(i,j)为矩阵在(i,j)位置的值，0或者1
 * L(i,j)为矩阵(i,j)位置包括自己的左边含有连续为1的元素的最长个数；H(i,j)为矩阵(i,j)位置包括自己的上面连续为1的元素的最长个数；
 * 当M(i,j)=1时，有递推关系L(i,j)=1+L(i,j-1), H(i,j)=1+H(i,j)
 * 计算以(i,j)为矩形右下角时得到的最大矩形。// TODO: 19/08/2017 这个复杂度还是有点高~
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/08/2017
 * @see
 * @since cgs-leetcode on  19/08/2017
 */
public class MaximalRectangle {
}
