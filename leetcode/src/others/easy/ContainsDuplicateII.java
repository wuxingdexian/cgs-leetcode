package others.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/contains-duplicate-ii/description/" />
 * 219. Contains Duplicate II
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 29/08/2017
 * @see
 * @since cgs-leetcode on  29/08/2017
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numMap = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            if(numMap.get(nums[i]) != null && i - numMap.get(nums[i]) <= k) {
                return true;
            }

            // 因为是逐渐往后遍历，当相同时，允许覆盖
            numMap.put(nums[i], i);
        }
        return false;
    }
}
