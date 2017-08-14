package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * 70. Climbing Stairs
 * You are climbing a stair case. It takes n steps to reach to the top.

 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * 1. 建模：recurrence relation: Sum_n=Sum_n-1 + Sum_n-2
 * 2. 算法范式：dynamic programming
 * 3. 算法：bottom-up
 * 4. 数据结构：三个数相加赋值
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/08/2017
 * @see
 * @since DiscreteMathematics on  07/08/2017
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n==0) {
            return 0;
        }
        int sum1=1;
        int sum2=2;
        if(n==1) {
            return sum1;
        }
        if(n==2) {
            return sum2;
        }
        int sumn=0;
        for(int i=2;i<n;i++) {
            sumn=sum1+sum2;
            sum1=sum2;
            sum2=sumn;
        }
        return sumn;
    }
}
