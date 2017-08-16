package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/partition-equal-subset-sum/description/" />
 * 416. Partition Equal Subset Sum
 *Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 Note:
 Each of the array element will not exceed 100.
 The array size will not exceed 200.
 Example 1:

 Input: [1, 5, 11, 5]

 Output: true

 Explanation: The array can be partitioned as [1, 5, 5] and [11].
 Example 2:

 Input: [1, 2, 3, 5]

 Output: false

 Explanation: The array cannot be partitioned into equal sum subsets.
 * <p>
 * 1. 建模：recurrence relation
 * （1）recurrence relation
 * {@link https://leetcode.com/problems/partition-equal-subset-sum/discuss/}
 * 首先集合分为两部分，两部分相加后相等，即两边得值都分别为 sum/2，此时问题即化解为0-1背包问题
 * 设有序对V(i, w_sum(i), W)为截止i的最高总价值；W为限定最高总重量；i为第i个元素，w_sum(i)为截止i的实际最高总价值，w_sum(i)<=W；wi为第i个元素的重量。
 * 有如下recurrence relation
 *
 * 提示：理解为二维数组V[i][w]为第i个元素时的最大价值，i为第i个元素，w为限制重量，取w小于等于限制重量W；
 * 建模：
 * 有i个元素，对应重量为w[i]
 * 其中w为重量，w <= W；W为总重量；V(i, w)为第i个元素是在满足总重量不超过w时的总价值。
 * V(i, w) = V(i-1, w(i))，如果w(i) < w；
 * V(i, w) = max(V(i-1, w(i-1)), V(i-1, w(i-1)-w(i)))，如果w(i) >= w。
 * 初始条件：V(0, w(0))=0
 *
 * 判断条件，因为V[i][w]，每个元素表示在到达第i个元素并限制总重量在w时的最大价值，是从前i个元素中挑选组合。
 *
 * 在本题，价值即重量。最后V[i][w]的元素值若等于目标值，即表示能找到组合，使得集合划分为相等的两半；
 *
 * （2）树
 * // TODO: 15/08/2017 用backtracking的dfs尝试
 *
 * 2. 算法范式：
 * （1）dynamic programming
 *
 * （2）backtracking
 *
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since DiscreteMathematics on  14/08/2017
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int weightSum = 0;
        for(int i = 0; i < nums.length; i++) {
            weightSum += nums[i];
        }
        if((weightSum & 1 )!= 0) {
           return false;
        }
        int average = weightSum >>> 1;
        int[][] values = new int[nums.length + 1][average + 1];

        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j < average + 1; j++) {
                if(nums[i - 1] > j) {
                    values[i][j] = values[i - 1][j];
                } else {
                    values[i][j] = Math.max(values[i - 1][j], values[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }
        return values[nums.length][average] == average;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 5, 11, 5};
//        int[] nums = {1, 2, 3, 5};
        int[] nums = {1, 4, 11, 5, 7, 8};
        boolean b = new PartitionEqualSubsetSum().canPartition(nums);
        System.out.println(b);
    }
}
