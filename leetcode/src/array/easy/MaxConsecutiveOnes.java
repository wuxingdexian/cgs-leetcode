package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/max-consecutive-ones/description/" />
 * 485. Max Consecutive Ones
 * Given a binary array, find the maximum number of consecutive 1s in this array.

 Example 1:
 Input: [1,1,0,1,1,1]
 Output: 3
 Explanation: The first two digits or the last three digits are consecutive 1s.
 The maximum number of consecutive 1s is 3.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000
 * <p>
 * 0. 本质：序列
 * 1. 建模：划分子序列，保证连续性
 * recurrence relation：max_length(i) = max(pre_max_length, max_length(i))
 * 2. 算法范式：
 * 3. 算法：
 * （1）指针和临时变量
 * （2）栈
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLength = 0;
        int startIndex = getStartIndex(nums, 0);

        for(int i = startIndex; i < nums.length; i++) {
            if(nums[i] == 0) {
                maxLength = Math.max(maxLength, i - startIndex);
                startIndex = getStartIndex(nums, i + 1);
                i = startIndex - 1;
            }
            if(i == nums.length - 1) {
                maxLength = Math.max(maxLength, i - startIndex + 1);
            }
        }
        return maxLength;
    }

    // get start index with 1
    int getStartIndex(int[] nums, int start) {
        for(int i = start; i < nums.length; i++) {
            if(nums[i] == 1) {
                return i;
            }
        }
        return nums.length;
    }
}
