package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-average-subarray-i/description/" />
 * 643. Maximum Average Subarray I
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

 Example 1:
 Input: [1,12,-5,-6,50,3], k = 4
 Output: 12.75
 Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 Note:
 1 <= k <= n <= 30,000.
 Elements of the given array will be in the range [-10,000, 10,000].
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 连续子序列使得值最大，过程为滑动窗口；
 * 该序列存在重复子问题，recurrence relation：
 * max_sum = max(sum(i, i+3),sum(i+1, i+4)), where i={1,2,...,n-3}。
 * sum(i+1, i+4) = max(sum(i, i+3) + a[i+4] - a[i]
 * 2. 算法范式：dynamic programming
 * 3. 算法：
 * （0）若数组长度小于k，则直接退出；
 * （1）先计算前面四个的值；设定为最大值；
 * （2）窗口依次后移，计算当前窗口的和，若比对大值大，则更新最大值
 * （3）重复，知道窗口到最后一个元素。然后返回 最大值/k
 * 4. 数据结构：临时遍历即可，不需要保存到整个数组
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * // FIXME: 01/09/2017 <code>double preSum = 0;</code>基本类型也要付初值，否则ide显示错误
 * // TODO: 01/09/2017 多去看下JVM体系，方法区
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 01/09/2017
 * @see
 * @since cgs-leetcode on  01/09/2017
 */
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        if(nums.length < k) {
            return 0;
        }

        double maxSum;
        double preSum = 0;
        for(int i = 0; i < k; i++) {
            preSum += nums[i];
        }
        maxSum = preSum;

        for(int i = k; i < nums.length; i++) {
            // 经常容易忘记这一行，dp
            preSum = preSum + nums[i] - nums[i - k];
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum / k;

    }
}
