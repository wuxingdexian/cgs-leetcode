package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/remove-element/description/" />
 * 27. Remove Element
 * Given an array and a value, remove all instances of that value in place and return the new length.

 Do not allocate extra space for another array, you must do this in place with constant memory.

 The order of elements can be changed. It doesn't matter what you leave beyond the new length.

 Example:
 Given input array nums = [3,2,2,3], val = 3

 Your function should return length = 2, with the first two elements of nums being 2.

 * <p>
 * 0. 本质：function
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：TODO {@link RemoveDuplicatesFromSortedArray} 类似，题目虽然简单，但是考察队数组的移动操作、指针操作，还有有点弱啊
 * 把数组画出来，模拟下两个指针的移动，即能使思路清晰
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 01/09/2017
 * @see
 * @since cgs-leetcode on  01/09/2017
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
