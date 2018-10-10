package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/min-cost-climbing-stairs/description/" />
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

 Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

 Example 1:
 Input: cost = [10, 15, 20]
 Output: 15
 Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 Example 2:
 Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 Output: 6
 Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 Note:
 cost will have a length in the range [2, 1000].
 Every cost[i] will be an integer in the range [0, 999].
 * <p>
 * 0. 本质：组合吧
 * 1. 建模：
 * （1）recurrence relation -》（2）关系式底层其实都有一个directed acyclic graph（DAG）
 * 其中recurrence relation 和归纳法有些类似和关系
 * 2. 算法范式：dp
 * 关键找到子问题，设子问题为r(i)为刚好到i阶梯所需要的最小总cost。则若cost数组长度为n，则解为找r(n+1)
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：找到子问题，参考《algorithm》、《离散数学》
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/10/2018
 * @see
 * @since cgs-leetcode on  09/10/2018
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        if (null == cost || cost.length == 0) {
            return 0;
        }

        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        int[] resultCost = new int[cost.length];

        resultCost[0] = cost[0];
        resultCost[1] = cost[1];

        for (int i = 2; i < resultCost.length; i++) {
            resultCost[i] = Math.min(resultCost[i - 1], resultCost[i - 2]) + cost[i];
        }
        return Math.min(resultCost[resultCost.length - 1], resultCost[resultCost.length - 2]);
    }
}
