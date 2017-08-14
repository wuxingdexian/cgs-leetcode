package bitmanipulation.easy;

/**
 * <p>
 * 背景描述：
 * 268. Missing Number
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 For example,
 Given nums = [0, 1, 3] return 2.

 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

 * <p>
 * 1. 建模：异或运算的性质，主要用到自反性；
 * 2. 算法范式：穷举
 * 3. 算法：遍历
 * 4. 数据结构：直接在操作数的异或运算
 * 5. 改进：可以在一个循环里面搞定
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {

        int target = 0;
        for(int i = 0; i < nums.length + 1; i++) {
            target ^= i;
        }
        for(int i = 0; i < nums.length; i++) {
            target ^=nums[i];
        }
        return target;
    }
}
