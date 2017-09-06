package others.medium;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-increasing-subsequence/description/" />
 * 300. Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * 0. 本质：
 * 1. 建模：
 * * recurrence relation
 * ----------------FIXME 有点绕-----------------------
 * 设count(i, max_num)为截止第i个元素的最大数变化个数。
 * 有关系：
 * count(i, max_num(i)) = count(i-1,max_num(i-1))+1, where max_num(i) = a[i], when a[i] > a[i-1]
 * count(i, max_num(i)) = count(x, max_num(x))+1, where max_num(i) = a[i], when a[i] > a[x] and x < i FIXME "when a[i] > a[x]" 应该改为"when a[i] > max(a[x])"
 * count(i, max_num(i)) = 1, when no elements less than it
 *
 * --------------------TODO 参考-----------------------
 * http://www.geeksforgeeks.org/longest-increasing-subsequence/
 * Optimal Substructure:
 Let arr[0..n-1] be the input array and L(i) be the length of the LIS ending at index i such that arr[i] is the last element of the LIS.
 Then, L(i) can be recursively written as:
 L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
 L(i) = 1, if no such j exists.
 To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.
 * --------------------TODO 参考-----------------------
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 05/09/2017
 * @see
 * @since cgs-leetcode on  05/09/2017
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] length = new int[nums.length];
        Arrays.fill(length, 1);

        // 这一步bottom up的方式，比我想得6
        for(int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i] && length[i] < length[j] + 1) {
                    length[i] = length[j] + 1;

                }
            }
        }

        int maxLength = length[0];
        for (int i = 0; i < length.length; i++) {
            maxLength = Math.max(maxLength, length[i]);
        }
        return maxLength;
    }

}
