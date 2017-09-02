package array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/k-diff-pairs-in-an-array/description/" />
 * 532. K-diff Pairs in an Array
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

 Example 1:
 Input: [3, 1, 4, 1, 5], k = 2
 Output: 2
 Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 Although we have two 1s in the input, we should only return the number of unique pairs.
 Example 2:
 Input:[1, 2, 3, 4, 5], k = 1
 Output: 4
 Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 Example 3:
 Input: [1, 3, 1, 5, 4], k = 0
 Output: 1
 Explanation: There is one 0-diff pair in the array, (1, 1).
 Note:
 The pairs (i, j) and (j, i) count as the same pair.
 The length of the array won't exceed 10,000.
 All the integers in the given input belong to the range: [-1e7, 1e7].
 * <p>
 * 0. 本质：relation
 * 1. 建模：找关系对，满足
 * 2. 算法范式：
 * 3. 算法：
 * （1）HashMap缓存访问过的元素。
 * （2）从HashMap中找是否有nums[i]-k或nums[i]+k的数，并且HashMap中没有(i, j) and (j, i)，则计数+1，同时把关系对保存到hashMap
 * （3）把当前元素保存到hashMap
 *
 * // TODO: 02/09/2017 排序也可以
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：注意临界条件。如绝对值没有负数
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        if(k < 0 || nums == null || nums.length == 0) {
            return 0;
        }
        Map<String, Integer> cache = new HashMap();
        int counter = 0;
        for(int i = 0; i < nums.length; i++) {
            if(cache.containsKey(String.valueOf(nums[i] - k)) && !cache.containsKey(getKey(nums[i], nums[i] - k))) {
                counter++;
                cache.put(getKey(nums[i], nums[i] - k), 1);
            }
            if(cache.containsKey(String.valueOf(nums[i] + k)) && !cache.containsKey(getKey(nums[i], nums[i] + k))) {
                counter++;
                cache.put(getKey(nums[i], nums[i] + k), 1);
            }
            cache.put(String.valueOf(nums[i]), 1);
        }
        return counter;
    }

    private String getKey(int i, int j) {
        return i > j? i + "-" + j: j + "-" + i;
    }
}
