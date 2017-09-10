package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/sort-colors/description/" />
 * 75. Sort Colors
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.
 * <p>
 * 0. 本质：序列-》集合/序列
 * 1. 建模：
 * 将相同的归集到一起，同时保证0，1，2的顺序
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）使用JDK的默认升序比较器很容易实现。
 * （2）既然只有0、1、2三种可能，那么每种可能计算有多少个，然后重写数组即可
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/09/2017
 * @see
 * @since cgs-leetcode on  10/09/2017
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        int counter0 = 0;
        int counter1 = 0;
        for(int num: nums) {
            if(num == 0) {
                counter0++;
            } else if(num == 1) {
                counter1++;
            }
        }
        int index = 0;
        for(;index < counter0; index++) {
            nums[index] = 0;
        }
        for(;index < counter0 + counter1; index++) {
            nums[index] = 1;
        }
        for(;index < nums.length; index++) {
            nums[index] = 2;
        }
    }
}
