package array.easy;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/array-partition-i/description/" />
 * 561. Array Partition I
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

 Example 1:
 Input: [1,4,3,2]

 Output: 4
 Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 Note:
 n is a positive integer, which is in the range of [1, 10000].
 All the integers in the array will be in the range of [-10000, 10000].
 * <p>
 * 1. 建模：recurrence relation
 * 首先假设数组已经按非递减排序：a_1 < a_2 < ... < a_n < ... < a_2n
 * 假设在满足条件的前提下，依次从最小的值开始选择。现在已经指定到第k步，使得max_k最大。要使得max_k+1最大，则k+1次选择pick_k+1(a_i, a_j)=a_i，
 * a_i是目前剩余最小的值，要使得结果，那么和其搭配的应该是剩下值中第二小，才能使得剩余数保持最大值可能。
 * 2. 算法范式：dynamic programming
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/08/2017
 * @see
 * @since DiscreteMathematics on  10/08/2017
 */
public class ArrayPartitionI {
    public int arrayPairSum(int[] nums) {

        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i = i + 2) {
            sum += nums[i];
        }
        return sum;
    }
}
