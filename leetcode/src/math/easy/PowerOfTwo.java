package math.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/power-of-two/description/" />
 * 231. Power of Two
 * Given an integer, write a function to determine if it is a power of two.
 * <p>
 * 1. 建模：2的指数，那只有一个bit为1.
 * 2. 算法范式：
 * 3. 算法：（1）n & (n-1)判断bit为1的个数；（2）{@link Integer#bitCount(int)}读取bit为1的个数，其中会把符号位读取出来，要去掉；
 * （3）遍历，无符号右移，判断bit为1的个数
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：要注意符号位。 // TODO: 11/08/2017 了解下java中补码
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if(Integer.bitCount(n) == 1) {
            if((n & 0x80000000) != 0) {
                return false;
            }
            return true;
        }
        return false;
    }
}
