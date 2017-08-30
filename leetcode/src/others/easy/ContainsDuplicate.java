package others.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/contains-duplicate/description/" />
 * 217. Contains Duplicate
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

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
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            if(numMap.get(nums[i]) != null) {
                return true;
            }
            numMap.put(nums[i], i);
        }
        return false;
    }
}
