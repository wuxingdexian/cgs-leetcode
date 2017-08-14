package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * 198. House Robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * 1. 建模：MaxSum_n = max(MaxSum_n-1, MaxSum_n-2 + Pn)。判断数组最后两位，若MaxSum_n-1时最后n-1位已经包含在内，在MaxSum_n的n位不能包含；若不包含，则计算MaxSum_n时取第n位
 * 2. 算法范式：dynamic programming
 * 3. 算法：bottom-up
 * 4. 数据结构：两个数组，（1）原始数据；（2）累加结果
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/08/2017
 * @see
 * @since DiscreteMathematics on  07/08/2017
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if(null==nums || nums.length==0) {
            return 0;
        }
        if(nums.length==1) {
            return nums[0];
        }

        int[] maxSum = new int[nums.length];
        maxSum[0] = nums[0];
        maxSum[1] = Math.max(nums[0], nums[1]);
        for(int i=2;i<nums.length;i++) {
            maxSum[i] = Math.max(maxSum[i-1], maxSum[i-2]+nums[i]);
        }
        return maxSum[nums.length-1];
    }
}
