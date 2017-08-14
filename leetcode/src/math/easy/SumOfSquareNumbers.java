package math.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/sum-of-square-numbers/description/" />
 * 633. Sum of Square Numbers
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

 Example 1:
 Input: 5
 Output: True
 Explanation: 1 * 1 + 2 * 2 = 5
 Example 2:
 Input: 3
 Output: False
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：==相等判断
 *  == 的作用：
 　　基本类型：比较的就是值是否相同
 　　引用类型：比较的就是地址值是否相同
 equals 的作用:
 　　引用类型：默认情况下，比较的是地址值。
 注：不过，我们可以根据情况自己重写该方法。一般重写都是自动生成，比较对象的成员变量值是否相同


 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        for(int i = 0; i <= Math.sqrt(c); i++) {
            double b = c - Math.pow(i, 2);;
            b = Math.sqrt(b);
            if(b == (int)b) {
                return true;
            }
        }
        return false;
    }
}
