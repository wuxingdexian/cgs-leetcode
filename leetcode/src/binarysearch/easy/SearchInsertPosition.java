package binarysearch.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/search-insert-position/description/" />
 * 35. Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Here are few examples.
 [1,3,5,6], 5 → 2
 [1,3,5,6], 2 → 1
 [1,3,5,6], 7 → 4
 [1,3,5,6], 0 → 0
 * <p>
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
 * @author changgan on 15/08/2017
 * @see
 * @since cgs-leetcode on  15/08/2017
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int middle = 0;
        while(start <= end ) {
            middle = (start + end) >>> 1;
            if(nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = (middle + 1);
            } else {
                end = (middle - 1);
            }
        }

        return nums[middle] > target? middle: middle + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int i = new SearchInsertPosition().searchInsert(nums, 1);
        System.out.println(i);
    }
}
