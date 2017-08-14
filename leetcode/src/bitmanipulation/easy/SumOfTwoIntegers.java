package bitmanipulation.easy;

/**
 * <p>
 * 背景描述：
 * 371. Sum of Two Integers
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

 Example:
 Given a = 1 and b = 2, return 3.
 * <p>
 * 1. 建模：本位运算：异或拿到本位相加后的值；进位运算：与运算得到下一位的进位值，需要左移一位。循环或递归此步骤
 * http://www.cnblogs.com/newpanderking/p/3925056.html
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int xor = a ^ b;
        int carry = (a & b) << 1;
        if(carry == 0) {
            return xor;
        }
        return getSum(xor, carry);
    }
}
