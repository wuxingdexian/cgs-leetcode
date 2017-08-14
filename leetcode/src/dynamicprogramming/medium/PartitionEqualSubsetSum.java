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
 * {@link https://leetcode.com/problems/partition-equal-subset-sum/discuss/}
 * 首先集合分为两部分，两部分相加后相等，即两边得值都分别为 sum/2，此时问题即化解为0-1背包问题
 *
 *
 * 2. 算法范式：
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
}
