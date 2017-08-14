package bitmanipulation.easy;

/**
 * <p>
 * 背景描述：
 * 461. Hamming Distance
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

 Given two integers x and y, calculate the Hamming distance.

 Note:
 0 ≤ x, y < 231.

 Example:

 Input: x = 1, y = 4

 Output: 2

 Explanation:
 1   (0 0 0 1)
 4   (0 1 0 0)
 ?   ?

 The above arrows point to positions where the corresponding bits are different.
 * <p>
 * 1. 建模：异或运算
 * 2. 算法范式：
 * 3. 算法：异或运算后，按bit逐渐右移1位，统计；或者<code>Integer#bitCount</code>
 * 4. 数据结构：
 * 5. 改进：后续看下{@link Integer#bitCount(int)}
 * 再看下这个算法，该算法的思想如下：
 * 每次将该数与该数减一后的数值相与，从而将最右边的一位1消掉
 * 或者执行位移
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    int singleOnes(int num) {
        int sum = 0;
        while(num != 0) {
            sum++;
            num &= num - 1;
        }
        return sum;
    }
}
