package array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/" />
 * 167. Two Sum II - Input array is sorted
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution and you may not use the same element twice.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
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
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class TwoSumII_InputArraysSorted {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> cache = new HashMap();

        int[] solution = new int[2];
        for(int i = 0; i < numbers.length; i++) {
            if(cache.containsKey(target - numbers[i])) {
                solution[0] = cache.get(target - numbers[i]) + 1;
                solution[1] = i + 1;
                return solution;
            }
            cache.put(numbers[i], i);
        }
        return solution;
    }
}
