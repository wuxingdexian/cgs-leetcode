package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/" />
 * 26. Remove Duplicates from Sorted Array
 * <p>
 * 0. 本质：function
 * 1. 建模：找相邻相等的，并且把将不重复的前移。
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 01/09/2017
 * @see
 * @since cgs-leetcode on  01/09/2017
 */
public class RemoveDuplicatesFromSortedArray {


    public int removeDuplicates(int[] nums) {
        if(nums.length < 2) {
            return nums.length;
        }
        int length = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[length] != nums[i]) {
                nums[++length] = nums[i];
            }
        }
        return length + 1;
    }

    // ------------------写得太垃圾了-------------------
//    public int removeDuplicates(int[] nums) {
//        int length = 0;
//        int size = 0;
//        for(int i = 0; i < nums.length - size;i++) {
//            length++;
//            int from = getNextIndex(nums, i + 1, nums[i]);
//            move(nums, from , i + 1);
//            size = from - i - 1;
//        }
//        return length;
//    }
//
//    int getNextIndex(int[] nums, int index, int value) {
//        for(; index < nums.length; index++) {
//            if(nums[index] != value) {
//                break;
//            }
//        }
//        return index;
//    }
//
//    void move(int[] nums, int from, int to) {
//        for(; from < nums.length; to++, from++) {
//            nums[to] = nums[from];
//        }
//    }
    // ------------------写得太垃圾了-------------------

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        int i = new RemoveDuplicatesFromSortedArray().removeDuplicates(nums);
        System.out.println(i);
    }
}
