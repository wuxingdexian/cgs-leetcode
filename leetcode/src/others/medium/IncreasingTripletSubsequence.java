package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/increasing-triplet-subsequence/description/" />
 * 334. Increasing Triplet Subsequence
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

 Formally the function should:
 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 Your algorithm should run in O(n) time complexity and O(1) space complexity.

 Examples:
 Given [1, 2, 3, 4, 5],
 return true.

 Given [5, 4, 3, 2, 1],
 return false.
 * <p>
 * 0. 本质：关系，ordered-3-tuples 有序三元组，满足：arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1
 * 1. 建模：
 * 将一个序列划分为三个元素组成的子序列，找出是否存在升序的排列的子序列
 * 2. 算法范式：


 * 注意：TODO 题设没有要求相邻的连续性，如果是连续的单调性，用栈比较好解决，这里是离散的，栈不好弄。
 * 3. 算法：
 *
 * 4. 数据结构：
 * stack 保存截止到目前为止最小
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
public class IncreasingTripletSubsequence {


}
