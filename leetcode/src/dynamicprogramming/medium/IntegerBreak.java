package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/integer-break/description/" />
 * 343. Integer Break
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

 Note: You may assume that n is not less than 2 and not larger than 58.
 * <p>
 * 1. 建模：recurrence relation；relation ordered pairs
 * 定义这样的有序对，P_n是整数n时满足条件的有序对
 * 设P_n=(n, pair(i, n-i),maxMunProduct_n). maxMunProduct_n = max(max(i, maxMunProduct_i) * max(n-i, maxMunProduct_n-i)), 1<=i<=n-1;
 * maxMunProduct_n为整数n分解时获取的最大乘积。
 * 初始条件：P_2=(2, pair(1,1), 1), P_3=(3, (1,2), 2)
 * 思路：从简单的拆为两位开始，一下想很多位容易陷入
 * 2. 算法范式：dynamic programming
 * 3. 算法：bottom-up
 * 4. 数据结构：创建含n个有序对个数的数组
 * 5. 改进：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        // discard the headmost two elements.
        int[] maxinumProduct = new int[n + 1];
        // 1*1
        maxinumProduct[2] = 1;
        for(int i = 3; i <= n; i++) {
            /*
            局部变量声明以后，Java 虚拟机不会自动的为它初始化为默认值。
            因此对于局部变量，必须先经过显示的初始化，才能使用它。
            如果编译器确认一个局部变量在使用之前可能没有被初始化，编译器将报错。
             */
            int maxProduct = 0;
            for(int j = 1; j <= i/2; j++) {
                int tmpa = Math.max(j, maxinumProduct[j]);
                int tmpb = Math.max(i-j, maxinumProduct[i-j]);
                maxProduct = Math.max(tmpa * tmpb, maxProduct);
            }
            maxinumProduct[i] = maxProduct;
        }
        return maxinumProduct[n];
    }
}
