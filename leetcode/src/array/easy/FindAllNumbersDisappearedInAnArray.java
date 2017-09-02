package array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/" />
 * 448. Find All Numbers Disappeared in an Array
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]
 * <p>
 * 0. 本质：函数 单调函数
 * 1. 建模：参考OneNote 《数组 2017.9.2》“数组天然具备function/ sequence 单射属性 2017.9.2！！！”
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
public class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = 0 - Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        List<Integer> solutions = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                solutions.add(i + 1);
            }
        }
        return solutions;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = new FindAllNumbersDisappearedInAnArray().findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers);
    }
}
