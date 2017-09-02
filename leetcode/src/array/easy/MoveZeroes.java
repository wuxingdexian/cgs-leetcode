package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/move-zeroes/description/" />
 * 283. Move Zeroes
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 * <p>
 * 0. 本质：combinatorics permutation
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 两个指针，p1：永远指向要被更新的位置，慢；p2：找不等于0的数，快
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
public class MoveZeroes {
    public void moveZeroes(int[] nums) {

        int p1 = 0;
        // find the first 0
        for(; p1 < nums.length; p1++) {
            if(nums[p1] == 0) {
                break;
            }
        }
        for(int p2 = p1 +1;p2 < nums.length;p2++) {
            if(nums[p2] != 0) {
                nums[p1++] = nums[p2];
            }
        }

        // fill with 0
        for(;p1 < nums.length; p1++) {
            nums[p1] = 0;
        }

    }
}
