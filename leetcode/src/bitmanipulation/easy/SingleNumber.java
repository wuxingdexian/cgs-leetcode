package bitmanipulation.easy;

/**
 * <p>
 * 背景描述：
 * 136. Single Number
 * Given an array of integers, every element appears twice except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 * <p>
 * 1. 建模：公式，异或的运算
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
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int singleOne = 0;
        for(int i = 0; i < nums.length; i++) {
            singleOne ^= nums[i];
        }
        return singleOne;
    }
}
