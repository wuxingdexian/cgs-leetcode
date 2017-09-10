package others.medium;

import java.util.Arrays;

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
 *
 * （1）recurrence relation
 * 将使用最长递增子序列的DP解，O(n*n) time complexity and O(n) space complexity.
 * L(i) = 1 + Max(L(j)), where j < i and arr[i] > arr[j]
 * 当L(i) == 3时返回
 *
 * （2）// FIXME: 09/09/2017 O(n) time complexity and O(1) space complexity.
 * 执行patiency sort的第一阶段
 *
 * 2. 算法范式：
 * 注意：TODO 题设没有要求相邻的连续性，如果是连续的单调性，用栈比较好解决，这里是离散的，栈不好弄。
 * （1）dynamic programming
 *
 * （2）greedy algorithm
 * 3. 算法：
 * （1）DP解
 *
 * （2）使用patiency sort第一阶段的变种：
 * 只是验证是否存在长度为3的递增子序列，只需提前设定三个堆piles（用变量表示即可，无序真正使用栈），当有数据落入第三个堆piles时，即done。
 * 因为是leftmost原则，依次比较top值，所以只需要记录栈顶top元素，无需关注栈内元素，那么用临时变量即可。 666
 *
 * 4. 数据结构：
 * （1）DP解，使用数组
 *
 * （2）patiency sort，这里只是为了证明是否存在，无序使用单调递减栈，只需使用三个临时变量模拟堆pile即可。
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


    //
    public boolean increasingTriplet(int[] nums) {

        int[] cache = new int[nums.length];
        Arrays.fill(cache, 1);

        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j] && cache[j]+1 > cache[i]) {
                    cache[i] = cache[j] + 1;
                }
                if(cache[i] >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    // patiency sort第一阶段的变种
    public boolean increasingTripletImprovement(int[] nums) {
        int minMal = Integer.MAX_VALUE;
        int middle = Integer.MAX_VALUE;

        for(int num: nums) {
            // compare from left-most
            if(num <= minMal) {
                minMal = num;
            } else if(num <= middle) {
                middle = num;
            } else {
                return true;
            }
        }
        return false;
    }

}
