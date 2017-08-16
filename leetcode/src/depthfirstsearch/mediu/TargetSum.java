package depthfirstsearch.mediu;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/target-sum/description/" />
 * 494. Target Sum
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.
 * <p>
 * 1. 建模：
 * （1）决策树，因为叶子节点时能判断是否得到答案。但是这个相当于穷举了所有情况，并且是递归进行。效率很满。
 * // FIXME: 15/08/2017 https://leetcode.com/problems/target-sum/discuss/这里提到了记录前面的值来优化的
 * （2）recurrence relation // TODO: 15/08/2017 使用动态规划来进行
 * 2. 算法范式：
 * （1）backtracking
 * （2）dynamic programming
 * 3. 算法：
 * （1）深度遍历
 * （2）bottom-up，对应dynamic programming
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * // TODO: 15/08/2017 将Integer传入函数，在里面修改它的值，结果没用。为什么？
 * 因为{@link Integer#value}值为final，如果在函数内执行++或--的操作，得到的应该将是一个新的integer。此时应该用<code>AtomicInteger</code>
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/08/2017
 * @see
 * @since cgs-leetcode on  15/08/2017
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int S) {
        if(null == nums || nums.length == 0) {
            return 0;
        }
        AtomicInteger count = new AtomicInteger(0);
        dfs(nums, S, 0, 0, count);
        return count.intValue();
    }

    void dfs(int[] nums, int S, int tmpSum, int index, AtomicInteger count) {

        if(index == nums.length) {
            if(S == tmpSum) {
                count.set(count.intValue() + 1);
            }
            return;
        }

        dfs(nums, S, tmpSum + nums[index], index + 1, count);
        dfs(nums, S, tmpSum - nums[index], index + 1, count);
    }

    public static void main(String[] args) {
//        int[] nums = {1,1};
        int[] nums = {1,1,1,1,1};
        int targetSumWays = new TargetSum().findTargetSumWays(nums, 3);
        System.out.println(targetSumWays);
    }
}
