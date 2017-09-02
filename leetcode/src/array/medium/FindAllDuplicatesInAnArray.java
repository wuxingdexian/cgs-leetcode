package array.medium;

import array.easy.FindAllNumbersDisappearedInAnArray;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-all-duplicates-in-an-array/description/" />
 * 442. Find All Duplicates in an Array
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements that appear twice in this array.

 Could you do it without extra space and in O(n) runtime?

 Example:
 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [2,3]
 * <p>
 * 0. 本质：函数 单射
 * 1. 建模：{@link FindAllNumbersDisappearedInAnArray} 一个思路
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
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> solutions = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = -1 * nums[Math.abs(nums[i]) - 1];
            if(nums[Math.abs(nums[i]) - 1] > 0) {
                solutions.add(Math.abs(nums[i]));
            }
        }
        return solutions;
    }
}
